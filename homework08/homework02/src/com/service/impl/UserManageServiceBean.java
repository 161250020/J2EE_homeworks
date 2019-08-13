package com.service.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.UserDao;
import com.service.UserManageService;
@Service
public class UserManageServiceBean implements UserManageService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public List getPW(String username) {
		// TODO Auto-generated method stub
		return userDao.findPasswordByUsername(username);
	}

	@Override
	public List getSumMoney(String username) {
		// TODO Auto-generated method stub
		return userDao.findSummoneyByUsername(username);
	}

	@Override
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
