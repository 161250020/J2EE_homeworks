package com.service.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.PreferencialStrategiesDao;
import com.service.PreferencialStrategiesManageService;
@Service
public class PreferencialStrategiesManageServiceBean implements PreferencialStrategiesManageService{

	@Autowired
	private PreferencialStrategiesDao preferencialStrategiesDao;

	@Override
	public void addOrderPS(String orderId, String preferencialstrategyId) {
		// TODO Auto-generated method stub
		preferencialStrategiesDao.insert(orderId, preferencialstrategyId);
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
