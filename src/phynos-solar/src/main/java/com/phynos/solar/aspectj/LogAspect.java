package com.phynos.solar.aspectj;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.phynos.solar.annotation.OperationRecord;
import com.phynos.solar.json.JsonResult;
import com.phynos.solar.json.ResultCodeEnum;
import com.phynos.solar.util.ServletUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 切面处理类：操作日志 AOP处理类
 *
 * @author phynos
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

    // 切点
    @Pointcut("@annotation(com.phynos.solar.annotation.OperationRecord)")
    public void logPointCut() {

    }

    /**
     * 返回通知
     *
     * @param joinPoint 连接点
     * @param rvt       函数返回值
     */
    @AfterReturning(returning = "rvt", pointcut = "logPointCut()")
    public void afterReturning(JoinPoint joinPoint, Object rvt) {
        handleLog(joinPoint, rvt, null);
    }

    /**
     * 异常通知
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(value = "logPointCut()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Exception e) {
        handleLog(joinPoint, null, e);
    }

    @Async
    protected void handleLog(
            final JoinPoint joinPoint,
            final Object rvt,
            final Exception e) {
        try {
            logger.debug("当前系统日志处理线程名称：" + Thread.currentThread().getName());
            // 获得注解
            OperationRecord controllerLog = getAnnotationLog(joinPoint);
            if (controllerLog == null) {
                return;
            }
            // 异常
            if (e != null) {
                //异常信息
                String errorMsg = e.getMessage();
            } else {
                if (rvt != null && rvt instanceof JsonResult) {
                    JsonResult jr = (JsonResult) rvt;
                    String msg = jr.getMsg();
                    boolean result = jr.getStatus() == ResultCodeEnum.OK.getCode();

                } else {
                    //进入这里，表示返回结果不是json数据
                    logger.warn("暂时无法处理 返回结果不是json的情况");

                }
            }
            // 设置方法名称
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            String fullMethodName = className + "." + methodName + "()";
            // 处理设置注解上的参数
            getControllerMethodDescription(controllerLog);
            //
        } catch (Exception exp) {
            // 记录本地异常日志
            logger.error("==前置通知异常==");
            logger.error("异常信息:{}" + exp.getMessage());
        }
    }

    private void initData() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //URL
        request.getRequestURI();
        //IP
        ServletUtil.getIpAddress(request);
        //请求的参数（这里暂时直接用url参数）
        getHttpRequestParamString(request);
        //
        //IUserSession<Long> session = ShiroUtil.getLoginInfo(true, null);
        //操作用户
        //record.setOperUserId(session.getUserId());
        //操作地点
    }

    /**
     * 将url参数序列化成json（只保留250个字符）
     *
     * @param request
     * @return
     */
    private String getHttpRequestParamString(HttpServletRequest request) {
        Map<String, String[]> map = request.getParameterMap();
        String result = "";
        try {
            String params = JSON_MAPPER.writeValueAsString(map);
            if (params != null) {
                if (params.length() > 250)
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
     *
     * @param log
     * @throws Exception
     */
    public void getControllerMethodDescription(
            OperationRecord log) throws Exception {
        //设置模块
        log.module();
        //设置功能
        log.action();
        // 是否需要保存request，参数和值
        if (!log.isSaveRequestData()) {
            // 获取参数的信息，传入到数据库中。
        }
    }

    /**
     * 是否存在注解，如果存在就获取
     */
    private OperationRecord getAnnotationLog(JoinPoint joinPoint) throws Exception {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        if (method != null) {
            return method.getAnnotation(OperationRecord.class);
        }
        return null;
    }

}
