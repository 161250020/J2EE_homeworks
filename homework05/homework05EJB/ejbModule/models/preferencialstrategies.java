package models;

import java.io.Serializable;
public class preferencialstrategies implements Serializable{
	private String orderId;
	private int cutMoney;
	
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public int getCutMoney() {
		return cutMoney;
	}
	public void setCutMoney(int cutMoney) {
		this.cutMoney = cutMoney;
	}
	
}
