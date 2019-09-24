package com.phynos.framework.kalin.dal;

import java.util.LinkedList;
import java.util.List;

public class OrderOptionCollection {

	private List<OrderOption> list = new LinkedList<OrderOption>();
	
	public void asc(String column){
		this.list.add(new OrderOption(column, EOrderOptionEnum.Asc));
	}
	
	public void desc(String column){
		this.list.add(new OrderOption(column, EOrderOptionEnum.Desc));
	}
	
	public OrderOption[] getItems(){
		return list.toArray(new OrderOption[0]);
	}
}
