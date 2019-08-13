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

/**
 * Servlet implementation class payIndex
 */
@WebServlet("/payIndex")
public class payIndex extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataSource datasource = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public payIndex() {
        super();
        // TODO Auto-generated constructor stub
    }

  //初始化数据库
    public void init() {
    	InitialContext jndiContext=null;
    	
    	Properties properties=new Properties();
    	properties.put(javax.naming.Context.PROVIDER_URL, "jnp:///");
    	properties.put(javax.naming.Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");
    	
    	try {
			jndiContext=new InitialContext(properties);
			datasource=(DataSource) jndiContext.lookup("java:comp/env/jdbc/homework02");
			System.out.println("got context");
			System.out.println("About to get ds---payIndex");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//获得用户和需要支付的金额
		Cookie cookie_username=null;//用户名的cookie
		Cookie cookie_sumMoney=null;//用户需要支付的金额的cookie
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
		
		//获得用户的金额
		int userMoney=enoughForPay(username);
		System.out.println("userMoney:"+userMoney);
		//判断用户的金额是否足够支付
		boolean canPay=true;
		if(userMoney<sumMoney) {//用户的金额不够支付
			canPay=false;
		}
		
		//如果足够支付，减去库存中商品的数量，用户需要支付的金额，跳转到支付成功的界面
		if(canPay) {
			int changeToMoney=userMoney-sumMoney;
			pay(username, changeToMoney, request, response);
		}
		//如果不足够支付，跳转到支付失败的界面
		else {
			payErr(request, response);
		}
	}

	//返回用户的金额
	private int enoughForPay(String username) {
		// TODO Auto-generated method stub
		int userMoney=0;
		
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		try {
			connection = datasource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			stmt = connection.prepareStatement("select summoney from users where username = ? ");
			stmt.setString(1, username);
			result = stmt.executeQuery();
			while (result.next()) {
				userMoney=result.getInt("summoney");
				System.out.println("userMoney: "+userMoney);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return userMoney;
	}

	//支付成功
	private void pay(String username, int changeToMoney, HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		
		Connection connection = null;
		PreparedStatement stmt = null;
		Statement sm = null;
		try {
			connection = datasource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			//修改库存中商品的数量
			HttpSession session = req.getSession(true);
			ArrayList<commodity> coms=(ArrayList<commodity>) session.getAttribute("commodities");
			ArrayList<Integer> comsNum=(ArrayList<Integer>) session.getAttribute("comsNum");
			stmt = connection.prepareStatement("update commodities set storedSum = ? where name = ? ");
			for(int i=0;i<coms.size();i++) {
				int changeToNum=coms.get(i).getStoredSum()-comsNum.get(i);
				String name=coms.get(i).getName();
				stmt.setInt(1, changeToNum);
				stmt.setString(2, name);
				stmt.executeUpdate();
			}
		
			//修改用户的金额
			stmt = connection.prepareStatement("update users set summoney = ? where username = ? ");
			stmt.setInt(1, changeToMoney);
			stmt.setString(2, username);
			stmt.executeUpdate();
			
			//订单的数据库的记录
			//order的ID---UUID
			String uuid=UUID.randomUUID().toString();
			//table orders
			stmt = connection.prepareStatement("insert into orders(orderId, commodityName, commodityNum) values (?, ?, ?) ");
			for(int i=0;i<coms.size();i++) {
				if(comsNum.get(i)!=0) {//选择的商品数量不为0
					stmt.setString(1, uuid);
					stmt.setString(2, coms.get(i).getName());
					stmt.setInt(3, comsNum.get(i));
					stmt.executeUpdate();
				}
			}
			//table orderuser
			stmt = connection.prepareStatement("insert into orderuser(orderId, username) values (?, ?) ");
			stmt.setString(1, uuid);
			stmt.setString(2, username);
			stmt.executeUpdate();
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
			//如果订单存在优惠政策
			if(!preferencialstrategies_id.equals("")) {
				stmt = connection.prepareStatement("insert into preferencialstrategies(orderId, preferencialstrategyId) values (?, ?) ");
				stmt.setString(1, uuid);
				stmt.setString(2, preferencialstrategies_id);
				stmt.executeUpdate();
			}
			
			try {
				stmt.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//支付成功
			//跳转到支付成功的页面
			try {
				resp.sendRedirect(req.getContextPath() + "/paySuccess");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//支付失败
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
