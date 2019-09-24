package com.phynos.framework.kalin.dal;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target ( value = ElementType.FIELD )
@Retention ( value = RetentionPolicy.RUNTIME )
public @interface PrimaryKey {

	/**
	 * 序列名称，对 Oracle，需要一个序列用于管理自增长的id
	 * @return 序列名称
	 */
	public String sequence() default "";
	
	/**
	 * 数据库中的列名
	 * @return 数据库中的列名
	 */
	public String column();
	
	/**
	 * 实体类中的私有字段类型
	 * @return 实体类中的私有字段类型
	 */
	public PropertyType type();
}

