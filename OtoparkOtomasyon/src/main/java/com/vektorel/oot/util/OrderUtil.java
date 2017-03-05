package com.vektorel.oot.util;

import org.primefaces.model.SortOrder;

public class OrderUtil {
	
	private String field;
	private OrderType orderType;
	
	public OrderUtil(String sortField, SortOrder sortOrder) {
		if(sortOrder==SortOrder.ASCENDING){
			this.field=sortField;
			this.orderType=OrderType.ASC;
		}else{
			this.field=sortField;
			this.orderType=OrderType.DESC;
		}
	}
	
	public enum OrderType{
		ASC,
		DESC
	}
	
	public String getField() {
		return field;
	}
	
	public OrderType getOrderType() {
		return orderType;
	}
}
