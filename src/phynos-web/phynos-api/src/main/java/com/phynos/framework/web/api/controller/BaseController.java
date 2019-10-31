package com.phynos.framework.web.api.controller;

import com.github.pagehelper.PageHelper;
import com.phynos.framework.core.json.JsonResult;
import com.phynos.framework.core.json.ResultCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: Lupc
 * @Date: 2019/10/31 9:23
 **/
public class BaseController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @ExceptionHandler(org.apache.shiro.authz.UnauthorizedException.class)
    public JsonResult handlerUnauthorizedException(
            org.apache.shiro.authz.UnauthorizedException ex){
        logger.warn("无权限的访问：" + ex.getMessage());
        return JsonResult.code(ResultCodeEnum.NOT_HAVE_PERMISSION);
    }

    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    public JsonResult handlerException(RuntimeException ex){
        logger.error(ex.getMessage(), ex);
        PageHelper.clearPage();//一定要清理：运行时异常一定要清理掉
        return JsonResult.code(ResultCodeEnum.SYSTEM_UNKNOWN_ERROR,ex.getMessage());
    }

    //参数类型不匹配
    @ExceptionHandler({TypeMismatchException.class})
    @ResponseBody
    public JsonResult requestTypeMismatch(TypeMismatchException ex){
        logger.error(ex.getMessage(), ex);
        return JsonResult.code(ResultCodeEnum.PARAMETER_ERROR,"参数类型不匹配,参数" + ex.getPropertyName() + "类型应该为" + ex.getRequiredType());
    }

    //缺少参数异常
    @ExceptionHandler({MissingServletRequestParameterException.class})
    @ResponseBody
    public JsonResult requestMissingServletRequest(MissingServletRequestParameterException ex){
        logger.error(ex.getMessage(), ex);
        return JsonResult.code(ResultCodeEnum.PARAMETER_ERROR,"缺少必要参数,参数名称为" + ex.getParameterName());
    }

}
