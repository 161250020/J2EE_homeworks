package service.impl;

import java.io.IOException;

import javax.ejb.Stateless;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OrdersDao;
import factory.EJBFactory;
import service.OrdersManageService;

@Stateless
public class OrdersManageServiceBean implements OrdersManageService{
	OrdersDao ordersDao=(OrdersDao) EJBFactory
			.getEJB("ejb:/homework05EJB/OrdersDaoBean!dao.OrdersDao");
	
	public void addOrdersInfo(String id, String orderId, String commodityName, int commodityNum) {
		// TODO Auto-generated method stub
		ordersDao.insert(id, orderId, commodityName, commodityNum);
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
