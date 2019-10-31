package com.phynos.framework.core.aspectj;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.phynos.framework.core.MySessionCache;
import com.phynos.framework.core.annotation.OperationRecord;
import com.phynos.framework.core.json.JsonResult;
import com.phynos.framework.core.json.ResultCodeEnum;
import com.phynos.framework.core.util.ServletUtil;
import com.phynos.framework.core.util.ShiroUtil;
import com.phynos.framework.dao.mapper.OperationLogMapper;
import com.phynos.framework.dao.model.OperationLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

/**
 * 切面处理类：操作日志 AOP处理类
 * @author phynos
 *
 */
@Aspect
@Component
@EnableAsync
public class LogAspect {

	Logger logger = LoggerFactory.getLogger(getClass());

	private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
	static {
		//允许 json字符串有未知的属性
		JSON_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
				false);
	}

	@Autowired
	OperationLogMapper operationLogMapper;

	// 切点
	@Pointcut("@annotation(com.phynos.framework.core.annotation.OperationRecord)")
	public void logPointCut()
	{

	}

	/**
	 * 返回通知
	 *
	 * @param joinPoint 连接点
	 * @param rvt 函数返回值
	 */
	@AfterReturning(returning="rvt",pointcut = "logPointCut()")
	public void doBefore(JoinPoint joinPoint,Object rvt)
	{
		handleLog(initData(),joinPoint,rvt, null);
	}

	/**
	 * 异常通知
	 * 
	 * @param joinPoint
	 * @param e
	 */
	@AfterThrowing(value = "logPointCut()", throwing = "e")
	public void doAfter(JoinPoint joinPoint, Exception e)
	{
		handleLog(initData(),joinPoint,null, e);
	}

	@Async
	protected void handleLog(
			final OperationLog record,
			final JoinPoint joinPoint, 
			final Object rvt,
			final Exception e)
	{
		try
		{
            logger.debug("当前系统日志处理线程名称：" + Thread.currentThread().getName());
			// 获得注解
			OperationRecord controllerLog = getAnnotationLog(joinPoint);
			if (controllerLog == null)
			{
				return;
			}
			// 异常
			if (e != null)
			{
				//异常信息
				String errorMsg = e.getMessage();
				record.setErrorMsg(errorMsg);
				//设置状态
				record.setStatus(false);
			} else {
				if(rvt != null && rvt instanceof JsonResult){
					JsonResult jr = (JsonResult)rvt;
					String msg = jr.getMsg();
					boolean result = jr.getStatus() == ResultCodeEnum.OK.getCode();
					record.setStatus(result);
					record.setErrorMsg(msg);
				} else {
					//进入这里，表示返回结果不是json数据
					logger.warn("暂时无法处理 返回结果不是json的情况");
					record.setStatus(true);
					record.setErrorMsg("");
				}				
			}
			// 设置方法名称
			String className = joinPoint.getTarget().getClass().getName();
			String methodName = joinPoint.getSignature().getName();
			String fullMethodName = className + "." + methodName + "()";
			record.setMethodName(fullMethodName);
			// 处理设置注解上的参数
			getControllerMethodDescription(controllerLog,record);
			//
			operationLogMapper.insert(record);	
		}
		catch (Exception exp)
		{
			// 记录本地异常日志
            logger.error("==前置通知异常==");
            logger.error("异常信息:{}" + exp.getMessage());
		}
	}

	private OperationLog initData(){
		OperationLog record = new OperationLog();
		record.setOperationDatetime(new Date());
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		//URL
		record.setOperUrl(request.getRequestURI());
		//IP
		record.setOperIp(ServletUtil.getIpAddress(request));
		//请求的参数（这里暂时直接用url参数）		
		record.setMethodParams(getHttpRequestParamString(request));
		//
		MySessionCache sc = ShiroUtil.getLoginInfo(true,null);
		//操作用户
		record.setOperUserId(sc.getId());
		//操作地点
		record.setOperLocation("");
		//
		return record;
	}

	/**
	 * 将url参数序列化成json（只保留250个字符）
	 * @param request
	 * @return
	 */
	private String getHttpRequestParamString(HttpServletRequest request){
		Map<String, String[]> map = request.getParameterMap();
		String result = "";
		try {
			String params = JSON_MAPPER.writeValueAsString(map);
			if(params != null){
				if(params.length() > 250)
					result = params.substring(0, 250);
				else
					return params;
			}
		} catch (JsonProcessingException e) {
            logger.error(e.getMessage(), e);
		}        
		return result;
	}

	/**
	 * 获取注解中对方法的描述信息 用于Controller层注解
	 * @param log
	 * @param record
	 * @throws Exception
	 */
	public void getControllerMethodDescription(
			OperationRecord log,
			OperationLog record) throws Exception
	{
		//设置模块
		record.setModuleTitle(log.module());
		//设置功能
		record.setActionName(log.action());
		// 是否需要保存request，参数和值
		if (!log.isSaveRequestData())
		{			
			// 获取参数的信息，传入到数据库中。
			record.setMethodParams("");
		}
	}

	/**
	 * 是否存在注解，如果存在就获取
	 */
	private OperationRecord getAnnotationLog(JoinPoint joinPoint) throws Exception
	{
		Signature signature = joinPoint.getSignature();
		MethodSignature methodSignature = (MethodSignature) signature;
		Method method = methodSignature.getMethod();

		if (method != null)
		{
			return method.getAnnotation(OperationRecord.class);
		}
		return null;
	}

}
