package com.phynos.framework.core.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.phynos.framework.core.IUserSession;
import com.phynos.framework.core.json.JsonResult;
import com.phynos.framework.core.util.ShiroUtil;
import com.phynos.framework.core.util.UuidUtil;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Lupc
 * @date 2019/9/30 10:33
 **/
public class BaseServiceImpl {

    protected static final ObjectMapper JSON_MAPPER = new ObjectMapper();
    static {
        //允许 json字符串有未知的属性
        JSON_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                false);
    }

    public static final JsonResult OK = JsonResult.ok();

    public static boolean checkReg(String val,String regEx){
        return checkReg(val,regEx,false);
    }

    public static boolean checkNullAndLength(String val,int min,int max){
        if(val == null || val.length() < min || val.length() > max)
            return false;
        return true;
    }

    /**
     *
     * @param val
     * @param regEx
     * @param allowNull 是否允许为空
     * @return
     */
    public static boolean checkReg(String val,String regEx,boolean allowNull){
        if(!allowNull && val == null)
            return false;
        else if(allowNull && val == null)
            return true;
        // 编译正则表达式
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(val);
        // 字符串是否与正则表达式相匹配
        boolean rs = matcher.matches();
        return rs;
    }

    /**
     * 检查值是否 在（非标准）枚举中的一个
     * @param val 值
     * @param array 枚举数组
     * @return 如果是，则返回true；否则，返回false
     */
    public static boolean checkIntEnum(Integer val,int...array){
        if(val == null || array == null || array.length == 0)
            return false;
        for (int i : array) {
            if(val.intValue() == i)
                return true;
        }
        return false;
    }

    public static String formatDatetime(Date date){
        if(date == null)
            return "";
        String result = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            result = sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取会话信息
     * 注意：你必须确保你在web环境下调用此函数，在非web环境下（单元测试）会出错
     * @return
     */
    protected IUserSession<Long> getSessionInfo(){
        return getSessionInfo(null);
    }

    /**
     * 获取会话信息
     * @param req
     * @return
     */
    protected IUserSession<Long> getSessionInfo(HttpServletRequest req){
        return ShiroUtil.getLoginInfo(true, req);
    }

    /**
     * 获取http请求
     * @return
     */
    protected HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    protected String uid() {
        return UuidUtil.uid();
    }

    protected Map<String,Object> convertObjToMap(Object obj){
        Map<String,Object> result = new HashMap<>();
        if (obj == null)
            return result;
        try {
            Class<?> objClass = obj.getClass();
            while(objClass != null){
                Field[] fields = objClass.getDeclaredFields();
                for(int i=0;i<fields.length;i++){
                    try {
                        Field f = objClass.getDeclaredField(fields[i].getName());
                        f.setAccessible(true);
                        Object o = f.get(obj);
                        result.put(fields[i].getName(), o);
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                objClass = objClass.getSuperclass();
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return result;
    }

}

