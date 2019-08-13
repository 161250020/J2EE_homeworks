package com.models;

import java.io.Serializable;

public class commodity implements Serializable{
	private String name;
	private int price;
	private int storedSum;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getStoredSum() {
		return storedSum;
	}
	public void setStoredSum(int storedSum) {
		this.storedSum = storedSum;
	}
	
}
