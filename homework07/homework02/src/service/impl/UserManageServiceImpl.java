package service.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import factory.DaoFactory;
import service.UserManageService;

public class UserManageServiceImpl implements UserManageService{

	private static UserManageService userManageService=new UserManageServiceImpl();
	
	public static UserManageService getInstance()
	{
		return userManageService;
	}
	
	@Override
	public List getPW(String username) {
		// TODO Auto-generated method stub
		return DaoFactory.getUserDao().findPasswordByUsername(username);
	}

	@Override
	public List getSumMoney(String username) {
		// TODO Auto-generated method stub
		return DaoFactory.getUserDao().findSummoneyByUsername(username);
	}

	@Override
	public void changeSumMoney(String username, int summoney) {
		// TODO Auto-generated method stub
		DaoFactory.getUserDao().updateSummoneyByUsername(username, summoney);
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
