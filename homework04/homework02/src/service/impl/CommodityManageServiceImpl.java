package service.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import factory.DaoFactory;
import service.CommodityManageSevice;

public class CommodityManageServiceImpl implements CommodityManageSevice{
	
	private static CommodityManageSevice commodityService=new CommodityManageServiceImpl();
	
	public static CommodityManageSevice getInstance()
	{
		return commodityService;
	}
	
	@Override
	public List getAllCommodities() {
		// TODO Auto-generated method stub
		return DaoFactory.getCommodityDao().findAllCommodities();
	}

	@Override
	public void changeStoredSumOfCommodity(String name, int storedSum) {
		// TODO Auto-generated method stub
		DaoFactory.getCommodityDao().updateStoredSumByName(name, storedSum);
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
