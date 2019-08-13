package com.service.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.CommodityDao;
import com.service.CommodityManageService;
@Service
public class CommodityManageServiceBean implements CommodityManageService{
	
	@Autowired
	private CommodityDao commodityDao;
	
	@Override
	public List getAllCommodities() {
		// TODO Auto-generated method stub
		return commodityDao.findAllCommodities();
	}

	@Override
	public void changeStoredSumOfCommodity(String name, int storedSum) {
		// TODO Auto-generated method stub
		commodityDao.updateStoredSumByName(name, storedSum);
	}

	//others
	public void sentErrorMessage(String message,HttpServletRequest req) 
			throws ServletException,IOException
	{
		req.setAttribute("message",message);
	}

	public void sentMessage(String message,HttpServletRequest req) 
			throws ServletException,IOException
	{
		req.setAttribute("message",message);
	}

	public void forwardPage(String page,HttpServletRequest req,HttpServletResponse resp) 
			throws ServletException,IOException
	{
		RequestDispatcher dispater=req.getRequestDispatcher(resp.encodeURL(page));
		dispater.forward(req,resp);
	}


}
