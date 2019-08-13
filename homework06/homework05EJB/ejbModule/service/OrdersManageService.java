package service;

import java.io.IOException;

import javax.ejb.Remote;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Remote
public interface OrdersManageService {

	//添加新的订单信息
	public void addOrdersInfo(String id, String orderId, String commodityName, int commodityNum);

	//others
	public void sentErrorMessage(String message,HttpServletRequest req) 
			throws ServletException,IOException;

	public void sentMessage(String message,HttpServletRequest req) 
			throws ServletException,IOException;

	public void forwardPage(String page,HttpServletRequest req,HttpServletResponse resp) 
			throws ServletException,IOException;
	
}
