package com.phynos.solar.common.annotation;

import java.lang.annotation.*;

@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OpLog {
	
	/** 模块 */
	String module() default "";

	/** 功能 */
	String action() default "";

	/** 渠道 */
	String channel() default "OperatorType.MANAGE";

	/** 是否保存请求的参数 */
	boolean isSaveRequestData() default true;
	
}
