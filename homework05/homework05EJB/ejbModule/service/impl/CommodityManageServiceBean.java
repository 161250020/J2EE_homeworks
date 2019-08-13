package service.impl;

import java.io.IOException;
import java.util.List;

import javax.ejb.Stateless;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CommodityDao;
import factory.EJBFactory;
import service.CommodityManageSevice;

@Stateless
public class CommodityManageServiceBean implements CommodityManageSevice{
	
	CommodityDao commodityDao=(CommodityDao) EJBFactory
			.getEJB("ejb:/homework05EJB/CommodityDaoBean!dao.CommodityDao");
	
	public List getAllCommodities() {
		// TODO Auto-generated method stub
		return commodityDao.findAllCommodities();
	}

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
