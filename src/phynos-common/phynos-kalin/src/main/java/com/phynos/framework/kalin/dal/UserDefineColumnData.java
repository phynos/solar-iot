package com.phynos.framework.kalin.dal;

public class UserDefineColumnData {

	private UserDefineColumn columnDefine;
	private Object columnValue;
	
	public UserDefineColumn getColumnDefine() {
		return columnDefine;
	}
	public Object getColumnValue() {
		return columnValue;
	}
	
	public UserDefineColumnData(UserDefineColumn columnDefine, Object columnValue){
		this.columnDefine = columnDefine;
		this.columnValue = columnValue;
	}
}
