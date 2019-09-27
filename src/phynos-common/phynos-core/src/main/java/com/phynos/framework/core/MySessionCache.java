package com.phynos.framework.core;

import java.io.Serializable;
import java.util.Date;

/**
 * 登录缓存（与登录会话绑定）
 * @author lupc
 *
 */
public class MySessionCache implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1804297910601164065L;

	public static final String KEY_SESSION_LOGIN = "key_session_login";
	
	/**
	 * 绑定一个用户id
	 */
	private long id;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 登录时间
	 */
	private Date loginDate;
	/**
	 * 是否登录
	 */
	private boolean loginState;
	
	
	/**
	 * 登录验证码超时时间：5分钟
	 */
	private static final int LOGIN_CAPTCHA_TIMEOUT = 5 * 60 * 1000;
	/**
	 * 是否需要验证验证码
	 */
	private boolean checkCaptcha;
	/**
	 * 登录验证码 生成时间
	 */
	private Date loginCaptchaTime;	
	/**
	 * 登录验证码字符串
	 */
	private String loginCaptchaString;
	
	public MySessionCache(){
		loginState = false;
		checkCaptcha = false;
	}
	
	public void setLogin(long id,String username){
		this.id = id;
		this.username = username;
		loginState = true;
	}
	
	public boolean checkLogin(){
		return loginState;
	}
	
	/**
	 * 验证 登录验证码是否正确
	 * @param captcha
	 * @return
	 */
	public boolean checkLoginCaptcha(String captcha){
		if(loginCaptchaTime == null 
				|| loginCaptchaString == null
				|| captcha == null)
			return false;
		//
		if((new Date().getTime() - loginCaptchaTime.getTime()) > LOGIN_CAPTCHA_TIMEOUT){
			return false;
		}
		if(captcha.equalsIgnoreCase(loginCaptchaString))
			return true;
		else 
			return false;
	}
	
	public void saveLoginCaptcha(String captcha){
		checkCaptcha = true;
		this.loginCaptchaString = captcha;
		this.loginCaptchaTime = new Date();
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public long getId() {
		return id;
	}

	public boolean isCheckCaptcha() {
		return checkCaptcha;
	}

	public String getUsername() {
		return username;
	}
	
}
