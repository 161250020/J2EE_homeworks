package service;

import java.io.IOException;
import java.util.List;

import javax.ejb.Remote;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Remote
public interface UserManageService {

	//��ȡ�û�������
	public List getPW(String username);
	
	//��ȡ�û��Ľ��
	public List getSumMoney(String username);
	
	//�޸��û��Ľ��¶���֮��
	public void changeSumMoney(String username, int summoney);
	
	//others
	public void sentErrorMessage(String message,HttpServletRequest req) 
			throws ServletException,IOException;

	public void sentMessage(String message,HttpServletRequest req) 
			throws ServletException,IOException;

	public void forwardPage(String page,HttpServletRequest req,HttpServletResponse resp) 
			throws ServletException,IOException;
	
}
