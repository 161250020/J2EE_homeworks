package com.models;

import java.io.Serializable;

import com.models.preferencialstrategies;

public class orders implements Serializable{
	private String orderId;
	private String commodityName;
	private int commodityNum;
	private preferencialstrategies ps;
	
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getCommodityName() {
		return commodityName;
	}
	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	public int getCommodityNum() {
		return commodityNum;
	}
	public void setCommodityNum(int commodityNum) {
		this.commodityNum = commodityNum;
	}
	public preferencialstrategies getPs() {
		return ps;
	}
	public void setPs(preferencialstrategies ps) {
		this.ps = ps;
	}

}
