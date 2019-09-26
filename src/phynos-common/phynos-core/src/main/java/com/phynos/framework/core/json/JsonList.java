package com.phynos.framework.core.json;


import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonList {

	/**
	 * 总数量量
	 */
	private int total;

	/**
	 * 当前页码，从1开始
	 */
	private int pageIndex;

	/**
	 * 每页数据 
	 */
	private int pageSize;

	/**
	 * 总页数
	 */
	private int totalPages;

	/**
	 * 当前页第一条数据数据索引
	 */
	private int currentDataStartIndex;

	/**
	 * 当前页最后一条数据索引
	 */
	private int currentDataEndIndex;

	@JsonProperty("rows")
	private Object data;

	private JsonList(){

	}

	public static JsonList create(
			int total,
			int pageIndex,
			int pageSize,
			Object data
			){
		JsonList jl = new JsonList();
		jl.setTotal(total);
		jl.setPageIndex(pageIndex);
		jl.setPageSize(pageSize);
		jl.setData(data);
		//计算总页数
		int t1 = total % pageSize;
		int t2 = total / pageSize;
		int totalPages = t1 == 0? t2:(t2+1);
		jl.setTotalPages(totalPages);
		//计算当前页第一条数据数据索引
		int currentDataStartIndex = (pageIndex - 1) * pageSize + 1;
		jl.setCurrentDataStartIndex(currentDataStartIndex);
		//判断是不是最后一页
		if(pageIndex == totalPages) {
			int t3 = total - (totalPages - 1) * pageSize;
			int currentDataEndIndex = currentDataStartIndex + t3 - 1;
			jl.setCurrentDataEndIndex(currentDataEndIndex);	
		} else {
			int currentDataEndIndex = currentDataStartIndex + pageSize - 1;
			jl.setCurrentDataEndIndex(currentDataEndIndex);	
		}				
		return jl;
	}

	public static JsonList createEmpty(
			int total,
			int pageIndex,
			int pageSize
			){
		JsonList jl = new JsonList();
		jl.setTotal(total);
		jl.setPageIndex(pageIndex);
		jl.setPageSize(pageSize);
		jl.setData(null);
		//计算总页数
		int t1 = total % pageSize;
		int t2 = total / pageSize;
		int totalPages = t1 == 0? t2:(t2+1);
		jl.setTotalPages(totalPages);
		//计算当前页第一条数据数据索引
		int currentDataStartIndex = (pageIndex - 1) * pageSize + 1;
		jl.setCurrentDataStartIndex(currentDataStartIndex);
		//判断是不是最后一页
		if(pageIndex == totalPages) {
			int t3 = total - (totalPages - 1) * pageSize;
			int currentDataEndIndex = currentDataStartIndex + t3 - 1;
			jl.setCurrentDataEndIndex(currentDataEndIndex);	
		} else {
			int currentDataEndIndex = currentDataStartIndex + pageSize - 1;
			jl.setCurrentDataEndIndex(currentDataEndIndex);	
		}
		//如果请求页数超过最大页数，则将请求页数置为最大页
		if(jl.getPageIndex() != 1 && jl.getPageIndex() > jl.getTotalPages()){
			jl.setPageIndex(jl.getTotalPages());
		}
		return jl;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getCurrentDataStartIndex() {
		return currentDataStartIndex;
	}

	public void setCurrentDataStartIndex(int currentDataStartIndex) {
		this.currentDataStartIndex = currentDataStartIndex;
	}

	public int getCurrentDataEndIndex() {
		return currentDataEndIndex;
	}

	public void setCurrentDataEndIndex(int currentDataEndIndex) {
		this.currentDataEndIndex = currentDataEndIndex;
	}	


}
