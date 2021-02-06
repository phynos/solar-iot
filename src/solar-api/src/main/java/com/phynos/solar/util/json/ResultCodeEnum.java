package com.phynos.solar.util.json;

public enum ResultCodeEnum {

	OK(0,"ok"),

	SYSTEM_UNKNOWN_ERROR(10001,"系统内部错误"),
	ILLEGAL_REQUEST(10002,"非法请求或资源不存在"),
	OPERATION_FAILED(10003,"操作失败"),
	OBJECT_UN_EXIST(10004,"数据不存在"),
	NOT_HAVE_PERMISSION(10005,"没有权限"),

	LOGIN_FIRST(20001,"请登录后再访问"),
	USERNAME_PASSWORD_MISMATCH(20002,"用户名或密码错误"),
	USER_DISABLED(20003,"用户被禁用"),
	VALID_CODE_ERROR(20004,"验证码错误"),
	IMAGE_VALID_CODE_REQUIRED(20005,"请提交图片验证码"),
	SMS_VALID_CODE_REQUIRED(20006,"请提交短信验证码"),
	EMAIL_VALID_CODE_REQUIRED(20007,"请提交邮件验证码"),
	REGISTER_NAME_ERROR(20008,"用户名格式错误或已经被注册"),
	VALID_CODE_OUTDATE(20009,"验证码过期或没有获取验证码"),
	OLD_PASSWORD_ERROR(20010,"原密码错误"),
	STATION_UNIT_OWN_COLLECTOR(20011,"不能删除有采集器的电站单元"),
	STATION_UNIT_NOT_EXIST(20012,"分组不存在"),
	SN_INVALID(20013,"序列号不存在或已经注册"),
	SN_INVALID_2(20014,"序列号不合法"),
	INVALID_EDIT_SHARE_STATION(20015,"不能修改共享电站"),
	REACHED_MAX_LIMIT(20016,"当前已达上限，不能再进行新增操作"),
	MOBILE_NUM_EXIST(20017,"当前手机号已经被使用"),

	PARAMETER_ERROR(30001,"请求参数错误"),
	JSON_READ_ERROR(30002,"json格式错误"),
	DATA_REPEAT_ERROR(30003,"数据重复错误，请检查！"),
	PARAMETER_ERROR_2(30004,"请求参数错误"),//前端拦截了30001，新增一个用于特殊处理
	DATA_EXPIRED(30005,"数据已经过期"),//数据已经过期
	LOCK_REQUIRED(30006,"其他人在进行相同操作，请过一段时间再尝试！"),//数据已经过期

	PLEASE_UPDATE_CLIENT(40001,"请使用最新客户端"),
	UNSURPORT(40002,"暂不支持此功能"),
	DEPRECATED(40003,"该接口已经废弃，请使用最新接口"),

	ACTIVITI_STARTED(50001,"流程已经启动"),
	ACTIVITI_TASK_NOT_FOUND(50002,"办理或审核已经完成，无法重复进行"),
	ACTIVITI_NOT_FOND(50003,"没有对应的流程"),
	ACTIVITI_EXCEPTION(50004,"流程执行异常！"),

	CA_LOGIN_CERT_UNVALID(60001,"登录失败，证书验证失败"),
	CA_LOGIN_NONE_USER(60002,"登录失败，无法找到证书对应的用户"),

	E70001(70001,"请联系管理员配置【案件字】"),
	E70002(70002,"必填文书不是完成状态"),
	E70003(70003,"当前办理已经完成，无法重复办理"),
	E70004(70004,"警告：不能办理其他人的办理"),
	E70005(70005,"案件类型没有配置流程"),
	E70006(70006,"当前用户没有上传签名，请上传！"),
	E70007(70007,"案件已经结案或不予处理，不允许再修改！"),
	E70008(70008,"案件文书不能重名！"),
	E70009(70009,"文书没有配置，请先配置文书！"),
	E70010(70010,"简易程序案件必须先保存文书！"),
	E70011(70011,"没有下个办理任务了！"),
	E70012(70012,"UKEY不匹配或没插入UKEY！"),
	E70013(70013,"必须先确定当事人！");
	
	private int code;
	
	private String msg;
	
	private ResultCodeEnum(int code,String msg){
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
