package com.phynos.framework.kalin.dal;

public class OrderOption {

	private EOrderOptionEnum orderOption;
	private String column;
	
	public EOrderOptionEnum getOrderOption(){
		return orderOption;
	}
	
	public String getColumn(){
		return column;
	}
	
	public OrderOption(String column, EOrderOptionEnum orderOption){
		this.column = column;
		this.orderOption = orderOption;
	}
}
