package com.phynos.solar.json;


import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonList {

	/**
	 * 总数量量
	 */
	private long total;

	/**
	 * 当前页码，从1开始
	 */
	private long pageIndex;

	/**
	 * 每页数据
	 */
	private long pageSize;

	/**
	 * 总页数
	 */
	private long totalPages;

	/**
	 * 当前页第一条数据数据索引
	 */
	private long currentDataStartIndex;

	/**
	 * 当前页最后一条数据索引
	 */
	private long currentDataEndIndex;

	@JsonProperty("rows")
	private Object data;

	private JsonList(){

	}

	public static JsonList create(
			long total,
			long pageIndex,
			long pageSize,
			Object data
	){
		JsonList jl = new JsonList();
		jl.setTotal(total);
		jl.setPageIndex(pageIndex);
		jl.setPageSize(pageSize);
		jl.setData(data);
		//计算总页数
		long t1 = total % pageSize;
		long t2 = total / pageSize;
		long totalPages = t1 == 0? t2:(t2+1);
		jl.setTotalPages(totalPages);
		//计算当前页第一条数据数据索引
		long currentDataStartIndex = (pageIndex - 1) * pageSize + 1;
		jl.setCurrentDataStartIndex(currentDataStartIndex);
		//判断是不是最后一页
		if(pageIndex == totalPages) {
			long t3 = total - (totalPages - 1) * pageSize;
			long currentDataEndIndex = currentDataStartIndex + t3 - 1;
			jl.setCurrentDataEndIndex(currentDataEndIndex);
		} else {
			long currentDataEndIndex = currentDataStartIndex + pageSize - 1;
			jl.setCurrentDataEndIndex(currentDataEndIndex);
		}
		return jl;
	}

	public static JsonList createEmpty(
			long total,
			int pageIndex,
			int pageSize
	){
		JsonList jl = new JsonList();
		jl.setTotal(total);
		jl.setPageIndex(pageIndex);
		jl.setPageSize(pageSize);
		jl.setData(null);
		//计算总页数
		long t1 = total % pageSize;
		long t2 = total / pageSize;
		long totalPages = t1 == 0? t2:(t2+1);
		jl.setTotalPages(totalPages);
		//计算当前页第一条数据数据索引
		int currentDataStartIndex = (pageIndex - 1) * pageSize + 1;
		jl.setCurrentDataStartIndex(currentDataStartIndex);
		//判断是不是最后一页
		if(pageIndex == totalPages) {
			long t3 = total - (totalPages - 1) * pageSize;
			long currentDataEndIndex = currentDataStartIndex + t3 - 1;
			jl.setCurrentDataEndIndex(currentDataEndIndex);
		} else {
			long currentDataEndIndex = currentDataStartIndex + pageSize - 1;
			jl.setCurrentDataEndIndex(currentDataEndIndex);
		}
		//如果请求页数超过最大页数，则将请求页数置为最大页
		if(jl.getPageIndex() != 1 && jl.getPageIndex() > jl.getTotalPages()){
			jl.setPageIndex(jl.getTotalPages());
		}
		return jl;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public long getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(long pageIndex) {
		this.pageIndex = pageIndex;
	}

	public long getPageSize() {
		return pageSize;
	}

	public void setPageSize(long pageSize) {
		this.pageSize = pageSize;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public long getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(long totalPages) {
		this.totalPages = totalPages;
	}

	public long getCurrentDataStartIndex() {
		return currentDataStartIndex;
	}

	public void setCurrentDataStartIndex(long currentDataStartIndex) {
		this.currentDataStartIndex = currentDataStartIndex;
	}

	public long getCurrentDataEndIndex() {
		return currentDataEndIndex;
	}

	public void setCurrentDataEndIndex(long currentDataEndIndex) {
		this.currentDataEndIndex = currentDataEndIndex;
	}


}