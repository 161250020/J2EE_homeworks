package service.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import factory.DaoFactory;
import service.PreferencialStrategyManageService;

public class PreferencialStrategyManageServiceImpl implements PreferencialStrategyManageService{

	private static PreferencialStrategyManageService preferencialStrategyService=new PreferencialStrategyManageServiceImpl();
	
	public static PreferencialStrategyManageService getInstance()
	{
		return preferencialStrategyService;
	}
	
	@Override
	public List getAllPS() {
		// TODO Auto-generated method stub
		return DaoFactory.getPreferencialStrategyDao().findAllPreferencialStrategy();
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
