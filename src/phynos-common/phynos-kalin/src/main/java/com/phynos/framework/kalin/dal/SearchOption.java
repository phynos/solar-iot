package com.phynos.framework.kalin.dal;

public class SearchOption {

	private boolean lockTable;
	private ESearchOptionEnum searchOptionEnum;
	private String column;
	private Object value;

	public ESearchOptionEnum getSearchOptionEnum() {
		return searchOptionEnum;
	}
	
	public String getColumn(){
		return column;
	}
	
	public Object getValue() {
		return value;
	}
	
	public boolean isLockTable(){
		return lockTable;
	}
	
	public SearchOption(ESearchOptionEnum searchOptionEnum, String column, Object value){
		this.searchOptionEnum = searchOptionEnum;
		this.column = column;
		this.value = value;
		this.lockTable = false;
	}
	
	public SearchOption(boolean lockTable){
		this.searchOptionEnum = null;
		this.column = null;
		this.value = null;
		this.lockTable = lockTable;
	}
}
