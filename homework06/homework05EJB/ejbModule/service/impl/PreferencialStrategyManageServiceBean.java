package service.impl;

import java.io.IOException;
import java.util.List;

import javax.ejb.Stateless;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PreferencialStrategyDao;
import factory.EJBFactory;
import service.PreferencialStrategyManageService;

@Stateless
public class PreferencialStrategyManageServiceBean implements PreferencialStrategyManageService{

	PreferencialStrategyDao preferencialStrategyDao=(PreferencialStrategyDao) EJBFactory
			.getEJB("ejb:/homework05EJB/PreferencialStrategyDaoBean!dao.PreferencialStrategyDao");
	
	public List getAllPS() {
		// TODO Auto-generated method stub
		return preferencialStrategyDao.findAllPreferencialStrategy();
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
