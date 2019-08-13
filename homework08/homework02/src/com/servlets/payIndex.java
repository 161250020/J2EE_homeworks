package com.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.UUID;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.models.commodity;
import com.models.preferencialstrategy;
import com.models.user;
import com.service.*;


/**
 * Servlet implementation class payIndex
 */
@WebServlet("/payIndex")
public class payIndex extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ApplicationContext appliationContext;
    
	private static UserManageService userManageService;
	private static CommodityManageService commodityManageService;
	private static OrdersManageService ordersManageService;
	private static OrderUserManageService orderUserManageService;
	private static PreferencialStrategiesManageService preferencialStrategiesManageService;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public payIndex() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init() throws ServletException {  
    	super.init();
    	appliationContext=new ClassPathXmlApplicationContext("applicationContext.xml"); 
    	userManageService=(UserManageService)appliationContext.getBean("UserManageService");
    	commodityManageService=(CommodityManageService)appliationContext.getBean("CommodityManageService");
    	ordersManageService=(OrdersManageService)appliationContext.getBean("OrdersManageService");
    	orderUserManageService=(OrderUserManageService)appliationContext.getBean("OrderUserManageService");
    	preferencialStrategiesManageService=(PreferencialStrategiesManageService)appliationContext.getBean("PreferencialStrategiesManageService");
    }  
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//����û�����Ҫ֧���Ľ��
		Cookie cookie_username=null;//�û�����cookie
		Cookie cookie_sumMoney=null;//�û���Ҫ֧���Ľ���cookie
		Cookie[] cookies=request.getCookies();
		if(cookies!=null) {
			for(int i=0;i<cookies.length;i++) {
				Cookie cookie=cookies[i];
				if(cookie.getName().equals("lastUsername")) {
					cookie_username=cookie;
				}
				if(cookie.getName().equals("sumMoney")) {
					cookie_sumMoney=cookie;
				}
			}
		}
		String username=cookie_username.getValue();
		int sumMoney=Integer.parseInt(cookie_sumMoney.getValue());
		
		//����û��Ľ��
		int userMoney=enoughForPay(username);
		System.out.println("userMoney:"+userMoney);
		//�ж��û��Ľ���Ƿ��㹻֧��
		boolean canPay=true;
		if(userMoney<sumMoney) {//�û��Ľ���֧��
			canPay=false;
		}
		
		//����㹻֧������ȥ�������Ʒ���������û���Ҫ֧���Ľ���ת��֧���ɹ��Ľ���
		if(canPay) {
			int changeToMoney=userMoney-sumMoney;
			pay(username, changeToMoney, request, response);
		}
		//������㹻֧������ת��֧��ʧ�ܵĽ���
		else {
			payErr(request, response);
		}
	}

	//�����û��Ľ��
	private int enoughForPay(String username) {
		// TODO Auto-generated method stub
		int userMoney=0;
		
		userMoney=(int) userManageService.getSumMoney(username).get(0);
		
		System.out.println("pay index---enoughForPay");
		
		return userMoney;
	}

	//֧���ɹ�
	private void pay(String username, int changeToMoney, HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		
		//�޸Ŀ������Ʒ������
		HttpSession session = req.getSession(true);
		ArrayList<commodity> coms=(ArrayList<commodity>) session.getAttribute("commodities");
		ArrayList<Integer> comsNum=(ArrayList<Integer>) session.getAttribute("comsNum");
		
		for(int i=0;i<coms.size();i++) {
			int changeToNum=coms.get(i).getStoredSum()-comsNum.get(i);
			String name=coms.get(i).getName();
			commodityManageService.changeStoredSumOfCommodity(name, changeToNum);
		}
		System.out.println("pay index---line 114---�޸���Ʒ���");
	
		//�޸��û��Ľ��
		userManageService.changeSumMoney(username, changeToMoney);
		System.out.println("pay index---line 118---�޸��û����");
		
		//���������ݿ�ļ�¼
		//order��ID---UUID
		String uuid=UUID.randomUUID().toString();
		//table orders
		for(int i=0;i<coms.size();i++) {
			if(comsNum.get(i)!=0) {//ѡ�����Ʒ������Ϊ0
				String uuid2=UUID.randomUUID().toString();
				ordersManageService.addOrdersInfo(uuid2, uuid, coms.get(i).getName(), comsNum.get(i));
			}
		}
		System.out.println("pay index---line 129---����µĶ�����Ʒ��Ϣ");
		
		//table orderuser
		orderUserManageService.addOrderUser(uuid, username);
		System.out.println("pay index---line 133---����µĶ�����������Ϣ");
		
		//table preferencialstrategies
		Cookie[] cookies=req.getCookies();
		Cookie cookie=null;
		if(cookies!=null) {
			for(int i=0;i<cookies.length;i++) {
				cookie=cookies[i];
				if(cookie.getName().equals("preferencialstrategies_id")) {
					cookie=cookie;
					break;
				}
			}
		}
		String preferencialstrategies_id=cookie.getValue();
		//������������Ż�����
		if(!preferencialstrategies_id.equals("")) {
			preferencialStrategiesManageService.addOrderPS(uuid, preferencialstrategies_id);
		}
		System.out.println("pay index---line 152---����µĶ����Żݲ���");
		
		//֧���ɹ�
		//��ת��֧���ɹ���ҳ��
		try {
			resp.sendRedirect(req.getContextPath() + "/paySuccess");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//֧��ʧ��
	private void payErr(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			response.sendRedirect(request.getContextPath() + "/payErr");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
