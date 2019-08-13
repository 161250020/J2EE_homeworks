package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.UUID;

import javax.ejb.EJB;
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

import models.commodity;
import models.preferencialstrategy;
import models.user;
import service.CommodityManageSevice;
import service.OrderUserManageService;
import service.OrdersManageService;
import service.PreferencialStrategiesManageService;
import service.UserManageService;

/**
 * Servlet implementation class payIndex
 */
@WebServlet("/payIndex")
public class payIndex extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB UserManageService userManageService;
    @EJB CommodityManageSevice commodityManageSevice;
	@EJB OrdersManageService ordersManageService;
	@EJB OrderUserManageService orderUserManageService;
	@EJB PreferencialStrategiesManageService preferencialStrategiesManageService;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public payIndex() {
        super();
        // TODO Auto-generated constructor stub
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
			commodityManageSevice.changeStoredSumOfCommodity(name, changeToNum);
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
				ordersManageService.addOrdersInfo(uuid, coms.get(i).getName(), comsNum.get(i));
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
