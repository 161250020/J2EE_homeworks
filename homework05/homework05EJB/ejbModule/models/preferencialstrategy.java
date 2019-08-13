package models;

import java.io.Serializable;

public class preferencialstrategy implements Serializable{
	private String id;
	private int reachMoney;
	private int cutMoney;
	private String comment;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getReachMoney() {
		return reachMoney;
	}
	public void setReachMoney(int reachMoney) {
		this.reachMoney = reachMoney;
	}
	public int getCutMoney() {
		return cutMoney;
	}
	public void setCutMoney(int cutMoney) {
		this.cutMoney = cutMoney;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
