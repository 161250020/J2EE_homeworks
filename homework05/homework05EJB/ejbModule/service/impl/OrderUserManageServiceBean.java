package service.impl;

import java.io.IOException;

import javax.ejb.Stateless;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OrderUserDao;
import factory.EJBFactory;
import service.OrderUserManageService;

@Stateless
public class OrderUserManageServiceBean implements OrderUserManageService{

	OrderUserDao orderUserDao=(OrderUserDao) EJBFactory
			.getEJB("ejb:/homework05EJB/OrderUserDaoBean!dao.OrderUserDao");
	
	public void addOrderUser(String orderId, String username) {
		// TODO Auto-generated method stub
		orderUserDao.insert(orderId, username);
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
