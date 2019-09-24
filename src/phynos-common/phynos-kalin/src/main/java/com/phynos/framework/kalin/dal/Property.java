package com.phynos.framework.kalin.dal;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target( value = ElementType.FIELD)
@Retention ( value = RetentionPolicy.RUNTIME )
public @interface Property {

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
	
	/**
	 * 在插入、更新、查询语句中，字段的序号
	 * @return
	 */
	public int sn();
}
