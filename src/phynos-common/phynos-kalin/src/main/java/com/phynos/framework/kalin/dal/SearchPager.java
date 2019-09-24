package com.phynos.framework.kalin.dal;

import java.util.ArrayList;
import java.util.List;

public class SearchPager {

	private int pageIndex;
	private int pageSize;
	
	private Integer pageCount = null;
	
	public void setPageIndex(Integer value){
		this.pageIndex = value;
	}
	
	public int getPageIndex(){
		return pageIndex;
	}
	
	public void setPageSize(Integer value){
		this.pageSize = value;
	}
	
	public int getPageSize(){
		return pageSize;
	}
	
	public int getPageCount(){
		if(pageCount == null)
			throw new RuntimeException("Please invoke reset method before getPageCount invoked.");
		
		return pageCount;
	}
	
	public void reset(int dataCount){
		
		if(dataCount > 0){
			this.pageCount = dataCount / pageSize;
			if(dataCount % pageSize > 0)
				this.pageCount++;
			
			if(this.pageIndex > this.pageCount)
				this.pageIndex = this.pageCount;
		}else{
			this.pageCount = 1;
			this.pageIndex = 1;
		}
	}
	
	public SearchPager(int pageIndex, int pageSize){
		if(pageIndex < 1)
			throw new IllegalArgumentException("Argument pageIndex can not less than 1");
		if(pageSize < 1)
			throw new IllegalArgumentException("Argument pageSize can not less than 1");
		
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
	}
	
	public Integer[] getIndexArray(){
		if(this.pageCount == null || this.pageCount.intValue() == 0)
			return new Integer[0];
		
		List<Integer> list = new ArrayList<Integer>(this.pageCount.intValue());
		for(int i=1; i<=this.pageCount.intValue(); i++){
			list.add(i);
		}
		
		return list.toArray(new Integer[0]);
	}
	
	public SearchPager(){
		// this(1, Integer.MAX_VALUE / 10);
		this(1, 20);
	}
}
