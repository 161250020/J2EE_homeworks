package service.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import factory.DaoFactory;
import service.PreferencialStrategiesManageService;

public class PreferencialStrategiesManageServiceImpl implements PreferencialStrategiesManageService{

	private static PreferencialStrategiesManageService preferencialStrategiesService=new PreferencialStrategiesManageServiceImpl();
	
	public static PreferencialStrategiesManageService getInstance()
	{
		return preferencialStrategiesService;
	}
	
	@Override
	public void addOrderPS(String orderId, String preferencialstrategyId) {
		// TODO Auto-generated method stub
		DaoFactory.getPreferencialStrategiesDao().insert(orderId, preferencialstrategyId);
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
