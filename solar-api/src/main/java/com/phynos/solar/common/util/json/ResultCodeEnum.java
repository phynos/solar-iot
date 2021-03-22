package com.phynos.solar.common.util.json;

public enum ResultCodeEnum {


	OK(0,"ok"),

	//通用型-服务器错误
	SYSTEM_UNKNOWN_ERROR(1001,"系统内部错误"),
	ILLEGAL_REQUEST(1002,"非法请求或资源不存在"),
	OPERATION_FAILED(1003,"操作失败"),
	OBJECT_UN_EXIST(1004,"数据不存在"),
	NOT_HAVE_PERMISSION(1005,"没有权限"),

	//通用型-客户端错误
	PARAMETER_ERROR(2001,"请求参数错误"),
	JSON_READ_ERROR(2002,"json格式错误"),
	DATA_REPEAT_ERROR(2003,"数据重复错误，请检查！"),
	DATA_EXPIRED(2004,"数据已经过期"),//数据已经过期
	LOCK_REQUIRED(2005,"其他人在进行相同操作，请过一段时间再尝试！"),//数据已经过期
	REACHED_MAX_LIMIT(2006,"当前已达上限，不能再进行新增操作"),

	//版本错误
	PLEASE_UPDATE_CLIENT(3001,"请使用最新客户端"),
	UNSURPORT(3002,"暂不支持此功能"),
	DEPRECATED(3003,"该接口已经废弃，请使用最新接口"),

	//认证和权限错误
	LOGIN_FIRST(4001,"请登录后再访问"),
	USERNAME_PASSWORD_MISMATCH(4002,"用户名或密码错误"),
	USER_DISABLED(4003,"用户被禁用"),
	VALID_CODE_ERROR(4004,"验证码错误"),
	IMAGE_VALID_CODE_REQUIRED(4005,"请提交图片验证码"),
	SMS_VALID_CODE_REQUIRED(4006,"请提交短信验证码"),
	EMAIL_VALID_CODE_REQUIRED(4007,"请提交邮件验证码"),
	REGISTER_NAME_ERROR(4008,"用户名格式错误或已经被注册"),
	VALID_CODE_OUTDATE(4009,"验证码过期或没有获取验证码"),
	OLD_PASSWORD_ERROR(4010,"原密码错误"),
	MOBILE_NUM_EXIST(4011,"当前手机号已经被使用"),
	CA_LOGIN_CERT_UNVALID(4012,"登录失败，证书验证失败"),
	CA_LOGIN_NONE_USER(4013,"登录失败，无法找到证书对应的用户"),

	//11XXX 业务1-逻辑错误
	STATION_UNIT_NOT_EXIST(20012,"分组不存在"),
	SN_INVALID(20013,"序列号不存在或已经注册"),
	SN_INVALID_2(20014,"序列号不合法");

	//12XXX 业务2-逻辑错误

	//13XXX 业务3-逻辑错误

	//14XXX 业务4-逻辑错误

	private int code;

	private String msg;

	ResultCodeEnum(int code,String msg){
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
