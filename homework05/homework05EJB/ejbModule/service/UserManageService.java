package service;

import java.io.IOException;
import java.util.List;

import javax.ejb.Remote;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Remote
public interface UserManageService {

	//获取用户的密码
	public List getPW(String username);
	
	//获取用户的金额
	public List getSumMoney(String username);
	
	//修改用户的金额（下订单之后）
	public void changeSumMoney(String username, int summoney);
	
	//others
	public void sentErrorMessage(String message,HttpServletRequest req) 
			throws ServletException,IOException;

	public void sentMessage(String message,HttpServletRequest req) 
			throws ServletException,IOException;

	public void forwardPage(String page,HttpServletRequest req,HttpServletResponse resp) 
			throws ServletException,IOException;
	
}
