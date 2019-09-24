package com.phynos.framework.kalin.dal;

import java.lang.annotation.*;

@Target(ElementType.TYPE)  
@Retention(RetentionPolicy.RUNTIME)  
@Documented 
public @interface Entity {

	/**
	 * 表名
	 * @return 表名
	 */
	public String table();
}

