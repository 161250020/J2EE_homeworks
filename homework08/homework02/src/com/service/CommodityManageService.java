package com.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommodityManageService {

	//��ȡ��������е���Ʒ
	public List getAllCommodities();
	
	//������Ʒ֮�󣬸�����Ʒ���Ƹı���Ʒ���
	public void changeStoredSumOfCommodity(String name, int storedSum);
	
	//others
	public void sentErrorMessage(String message,HttpServletRequest req) 
			throws ServletException,IOException;

	public void sentMessage(String message,HttpServletRequest req) 
			throws ServletException,IOException;

	public void forwardPage(String page,HttpServletRequest req,HttpServletResponse resp) 
			throws ServletException,IOException;
	
}
