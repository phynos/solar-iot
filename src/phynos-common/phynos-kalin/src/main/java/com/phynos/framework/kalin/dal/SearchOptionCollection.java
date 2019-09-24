package com.phynos.framework.kalin.dal;

import java.util.LinkedList;
import java.util.List;

public class SearchOptionCollection {

	private List<SearchOption> items = new LinkedList<SearchOption>();
	
	public SearchOption[] getItems(){
		return items.toArray(new SearchOption[0]);
	}
	
	public SearchOptionCollection lessThan(String column, Object value){
		items.add(new SearchOption(ESearchOptionEnum.LessThan, column, value));
		return this;
	}
	
	public SearchOptionCollection lessThanEquals(String column, Object value){
		items.add(new SearchOption(ESearchOptionEnum.LessThanEquals, column, value));
		return this;
	}
	
	public SearchOptionCollection equals(String column, Object value){
		items.add(new SearchOption(ESearchOptionEnum.Equals, column, value));
		return this;
	}
	
	public SearchOptionCollection greatThan(String column, Object value){
		items.add(new SearchOption(ESearchOptionEnum.GreatThan, column, value));
		return this;
	}
	
	public SearchOptionCollection greatThanEquals(String column, Object value){
		items.add(new SearchOption(ESearchOptionEnum.GreatThanEquals, column, value));
		return this;
	}
	
	public SearchOptionCollection notEquals(String column, Object value){
		items.add(new SearchOption(ESearchOptionEnum.NotEquals, column, value));
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public SearchOptionCollection in(String column, List<? extends Object> values){
		
		SearchOption item = null;
		
		for(SearchOption so : items){
			if(so.getColumn().equalsIgnoreCase(column) 
					&& so.getSearchOptionEnum() == ESearchOptionEnum.In){
				
				item = so;
				break;
			}
		}
		if(item == null){
			item = new SearchOption(ESearchOptionEnum.In, column, values);
			this.items.add(item);
		}else{
			List<Object> list = (List<Object>)item.getValue();
			list.addAll(values);
		}
		
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public SearchOptionCollection notIn(String column, List<? extends Object> values){
		
		SearchOption item = null;
		
		for(SearchOption so : items){
			if(so.getColumn().equalsIgnoreCase(column) 
					&& so.getSearchOptionEnum() == ESearchOptionEnum.NotIn){
				
				item = so;
				break;
			}
		}
		if(item == null){
			item = new SearchOption(ESearchOptionEnum.NotIn, column, values);
			this.items.add(item);
		}else{
			List<Object> list = (List<Object>)item.getValue();
			list.addAll(values);
		}
		
		return this;
	}
	
	public SearchOptionCollection isNull(String column){
		items.add(new SearchOption(ESearchOptionEnum.IsNull, column, null));
		return this;
	}
	
	public SearchOptionCollection isNotNull(String column){
		items.add(new SearchOption(ESearchOptionEnum.IsNotNull, column, null));
		return this;
	}
	
	public SearchOptionCollection lockTable(){
		items.add(new SearchOption(true));
		return this;
	}
}
