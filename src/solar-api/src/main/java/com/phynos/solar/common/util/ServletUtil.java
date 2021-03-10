package com.phynos.solar.common.util;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * 一些servlet的工具类
 * @author lupc
 *
 */
public class ServletUtil {

	   /** 
     * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址, 
*  
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？ 
     * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。 
     *  
     * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130, 
     * 192.168.1.100 
     *  
     * 用户真实IP为： 192.168.1.110 
     *  
     * @param request 
     * @return 
     */  
    public static String getIpAddress(HttpServletRequest request) {  
        String ip = request.getHeader("x-forwarded-for");  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("X-Forward-For");  
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("x-forward-for");  
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("Proxy-Client-IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("WL-Proxy-Client-IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("HTTP_CLIENT_IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getRemoteAddr();  
        }  
        return ip;  
    }  
    
    /**
     * 判断请求是不是ajax请求
     * 注意：只有浏览器会主动加上这个标记，其他底层库可能不会加，注意使用场景
     * @param request
     * @return
     */
    public static boolean isAjax(HttpServletRequest request){
    	String x1 = request.getHeader("x-requested-with");
    	if (x1 != null && "XMLHttpRequest".equalsIgnoreCase(x1)) {
    	    return true;
    	}
    	String x2 = request.getHeader("X-Requested-With");
    	if (x2 != null && "XMLHttpRequest".equalsIgnoreCase(x2)) {
    	    return true;
    	}
        return false;
    }
    
    /**
     * 获取一个webapps下的目录 引用；如果该目录不存在，则创建该目录
     * <ul>
     * 	<li>子目录名称注意不要和应用的URL重名，否则会导致URL失效，建议目录以_开头</li>
     *  <li>创建的目录一般喝进程所属用户权限相同</li>
     * </ul>
     * @param request
     * @param foldName 子目录名称
     * @return
     */
	public static File getWebappsSubFoldDir(HttpServletRequest request,String foldName){
		String contextPath = request.getServletContext().getRealPath("/");
		File dirProject = new File(contextPath);
		File dirWebapp = dirProject.getParentFile();
		File dir = new File(dirWebapp,foldName);		
		if(!dir.exists()){
			dir.mkdir();
		}
		return dir;
	} 
    
	
}

