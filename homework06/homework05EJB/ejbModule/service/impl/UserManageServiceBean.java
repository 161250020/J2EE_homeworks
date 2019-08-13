package service.impl;

import java.io.IOException;
import java.util.List;

import javax.ejb.Stateless;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import factory.EJBFactory;
import service.UserManageService;

@Stateless
public class UserManageServiceBean implements UserManageService{

	UserDao userDao=(UserDao) EJBFactory
			.getEJB("ejb:/homework05EJB/UserDaoBean!dao.UserDao");
	
	public List getPW(String username) {
		// TODO Auto-generated method stub
		return userDao.findPasswordByUsername(username);
	}

	public List getSumMoney(String username) {
		// TODO Auto-generated method stub
		return userDao.findSummoneyByUsername(username);
	}

	public void changeSumMoney(String username, int summoney) {
		// TODO Auto-generated method stub
		userDao.updateSummoneyByUsername(username, summoney);
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
