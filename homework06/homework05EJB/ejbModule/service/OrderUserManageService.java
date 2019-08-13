package service;

import java.io.IOException;

import javax.ejb.Remote;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Remote
public interface OrderUserManageService {

	//添加订单的购买者的信息
	public void addOrderUser(String orderId, String username);

	//others
	public void sentErrorMessage(String message,HttpServletRequest req) 
			throws ServletException,IOException;

	public void sentMessage(String message,HttpServletRequest req) 
			throws ServletException,IOException;

	public void forwardPage(String page,HttpServletRequest req,HttpServletResponse resp) 
			throws ServletException,IOException;
	
}
