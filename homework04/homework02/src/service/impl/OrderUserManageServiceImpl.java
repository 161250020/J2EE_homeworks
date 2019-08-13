package service.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import factory.DaoFactory;
import service.OrderUserManageService;

public class OrderUserManageServiceImpl implements OrderUserManageService{
	
	private static OrderUserManageService orderUserService=new OrderUserManageServiceImpl();
	
	public static OrderUserManageService getInstance()
	{
		return orderUserService;
	}
	
	@Override
	public void addOrderUser(String orderId, String username) {
		// TODO Auto-generated method stub
		DaoFactory.getOrderUserDao().insert(orderId, username);
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
