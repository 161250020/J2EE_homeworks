package service.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import factory.DaoFactory;
import service.OrdersManageService;

public class OrdersManageServiceImpl implements OrdersManageService{

	private static OrdersManageService ordersService=new OrdersManageServiceImpl();
	
	public static OrdersManageService getInstance()
	{
		return ordersService;
	}
	
	@Override
	public void addOrdersInfo(String Id, String orderId, String commodityName, int commodityNum) {
		// TODO Auto-generated method stub
		DaoFactory.getOrdersDao().insert(Id, orderId, commodityName, commodityNum);
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
