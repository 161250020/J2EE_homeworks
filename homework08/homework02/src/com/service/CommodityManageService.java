package com.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommodityManageService {

	//获取库存中所有的商品
	public List getAllCommodities();
	
	//购买商品之后，根据商品名称改变商品库存
	public void changeStoredSumOfCommodity(String name, int storedSum);
	
	//others
	public void sentErrorMessage(String message,HttpServletRequest req) 
			throws ServletException,IOException;

	public void sentMessage(String message,HttpServletRequest req) 
			throws ServletException,IOException;

	public void forwardPage(String page,HttpServletRequest req,HttpServletResponse resp) 
			throws ServletException,IOException;
	
}
