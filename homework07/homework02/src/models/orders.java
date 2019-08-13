package models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import models.preferencialstrategies;

@Entity
@Table(name="orders")
public class orders implements Serializable{
	private String orderId;
	private String commodityName;
	private int commodityNum;
	private String Id;
	
	@Id
	public String getId() {
		return Id;
	}
	public void setId(String Id) {
		this.Id = Id;
	}
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
}
