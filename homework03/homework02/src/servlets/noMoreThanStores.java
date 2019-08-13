package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
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

/**
 * Servlet implementation class noMoreThanStores
 */
@WebServlet("/noMoreThanStores")
public class noMoreThanStores extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataSource datasource = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public noMoreThanStores() {
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
			System.out.println("About to get ds---no more than stores");
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
		HttpSession session=request.getSession(false);
		ArrayList<commodity> coms=(ArrayList<commodity>) session.getAttribute("commodities");
//		for(int i=0;i<coms.size();i++) {
//			System.out.println("noMoreThanStores --- commodity:"+coms.get(i).getName());
//		}
		
		//获得提交的选择的商品的数量
		ArrayList<Integer> comsNum=new ArrayList();
		boolean moreThanStore=false;//提交的商品的数量是否超过库存的数量
		boolean isNum=true;//提交的商品的数量是否超过库存的数量
		for(int i=0;i<coms.size();i++){
			String in=request.getParameter(coms.get(i).getName());
			int num=0;//获得提交的商品的数量
			if(!(in.equals("")||in.equals("0"))) {//输入值非空且非0
				try {
					num=Integer.parseInt(in);
					//判断商品的数量是否超过库存的数量
					int store_num=coms.get(i).getStoredSum();
					if(store_num<num) {
						moreThanStore=true;
						break;
					}
				}catch(Exception e) {
					isNum=false;
					break;
				}
			}
			comsNum.add(num);
			System.out.println("num:"+num);
		}
		
		//如果提交的商品的数量超过库存
		if(moreThanStore) {
			errPage(request, response);
		}
		//如果提交的商品的数量没有超过库存
		else {
			//如果输入的商品数量不是数字
			if(!isNum) {
				errPage2(request, response);
			}
			else {
				session = request.getSession(true);
				session.setAttribute("comsNum", comsNum);
				payPage(request, response, coms, comsNum);
			}
		}
	}

	//数量超标的页面
	public void errPage(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		Cookie cookie_username=null;//用户名的cookie
		Cookie[] cookies=req.getCookies();
		if(cookies!=null) {
			for(int i=0;i<cookies.length;i++) {
				Cookie cookie=cookies[i];
				if(cookie.getName().equals("lastUsername")) {
					cookie_username=cookie;
					break;
				}
			}
		}
		
		String username=cookie_username.getValue();
		
		//resp.setContentType("text/html;charset=utf-8");
		try {
			ServletContext context=getServletContext();
			int countVisitors=(int) context.getAttribute("countVisitors");
			int countUsers=(int) context.getAttribute("countUsers");
			int sum=countVisitors+countUsers;
			
			//保护机制（人数不会<0）
			if(sum<0) {
				sum=0;
			}
			if(countUsers<0) {
				countUsers=0;
			}
			if(countVisitors<0) {
				countVisitors=0;
			}
			
			PrintWriter out = resp.getWriter();
			out.println("<!DOCTYPE html>\r\n" + 
					"<html lang=\"en\">\r\n" + 
					"<head>\r\n" + 
					"    <meta charset=\"UTF-8\">\r\n" + 
					"    <title>payError</title>\r\n" + 
					"</head>\r\n" + 
					"<body style=\"text-align:center;\" background=\""+req.getContextPath()+"/image/pays.png\">\r\n" + 
					"<h4>用户名："+username+"</h4>\r\n" + 
					"<hr>\r\n" + 
					"<div>\r\n" + 
					"    <div align=\"left\">\r\n" + 
					"        <form method=\"get\" action=\"/homework02/chooseCommodities\">\r\n" + 
					"            <input type=\"submit\" name=\"chooseCom\" value=\"选择商品\" style=\"font-family:楷体;font-size:18px;width:84px;height:30px;background-color: lightgreen;border-radius: 2px;border:0px;\">\r\n" + 
					"        </form>\r\n" + 
					"        &nbsp;&nbsp;\r\n" + 
					"        <form method=\"get\" action=\"/homework02/logout\">\r\n" + 
					"            <input type=\"submit\" name=\"logout\" value=\"退出登录\" style=\"font-family:楷体;font-size:18px;width:84px;height:30px;background-color: lightgreen;border-radius: 2px;border:0px;\">\r\n" + 
					"        </form>\r\n" + 
					"    </div>\r\n" + 
					"    <hr>\r\n" + 
					"    选择商品的数量不可以超过库存的数量！\r\n" + 
					"</div>\r\n" + 
					"<br>\r\n" + 
					"        <br>\r\n" + 
					"        <div align=\"left\" style=\"width: 400px\">\r\n" + 
					"            <label style=\"font-size: 14px\">统计在线人数：</label>\r\n" + 
					"            <br>\r\n" + 
					"            <label style=\"font-size: 12px\">\r\n" + 
					"            总人数："+sum+"；\r\n" + 
					"            已登录用户人数："+countUsers+"；\r\n" + 
					"            游客人数："+countVisitors+"；\r\n" + 
					"            </label>\r\n" + 
					"        </div>"+
					"</body>\r\n" + 
					"</html>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//输入错误的页面
	public void errPage2(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		Cookie cookie_username=null;//用户名的cookie
		Cookie[] cookies=req.getCookies();
		if(cookies!=null) {
			for(int i=0;i<cookies.length;i++) {
				Cookie cookie=cookies[i];
				if(cookie.getName().equals("lastUsername")) {
					cookie_username=cookie;
					break;
				}
			}
		}
		
		String username=cookie_username.getValue();
		
		//resp.setContentType("text/html;charset=utf-8");
		try {
			ServletContext context=getServletContext();
			int countVisitors=(int) context.getAttribute("countVisitors");
			int countUsers=(int) context.getAttribute("countUsers");
			int sum=countVisitors+countUsers;
			
			//保护机制（人数不会<0）
			if(sum<0) {
				sum=0;
			}
			if(countUsers<0) {
				countUsers=0;
			}
			if(countVisitors<0) {
				countVisitors=0;
			}
			
			PrintWriter out = resp.getWriter();
			out.println("<!DOCTYPE html>\r\n" + 
					"<html lang=\"en\">\r\n" + 
					"<head>\r\n" + 
					"    <meta charset=\"UTF-8\">\r\n" + 
					"    <title>payError</title>\r\n" + 
					"</head>\r\n" + 
					"<body style=\"text-align:center;\" background=\""+req.getContextPath()+"/image/pays.png\">\r\n" + 
					"<h4>用户名："+username+"</h4>\r\n" + 
					"<hr>\r\n" + 
					"<div>\r\n" + 
					"    <div align=\"left\">\r\n" + 
					"        <form method=\"get\" action=\"/homework02/chooseCommodities\">\r\n" + 
					"            <input type=\"submit\" name=\"chooseCom\" value=\"选择商品\" style=\"font-family:楷体;font-size:18px;width:84px;height:30px;background-color: lightgreen;border-radius: 2px;border:0px;\">\r\n" + 
					"        </form>\r\n" + 
					"        &nbsp;&nbsp;\r\n" + 
					"        <form method=\"get\" action=\"/homework02/login\">\r\n" + 
					"            <input type=\"submit\" name=\"logout\" value=\"退出登录\" style=\"font-family:楷体;font-size:18px;width:84px;height:30px;background-color: lightgreen;border-radius: 2px;border:0px;\">\r\n" + 
					"        </form>\r\n" + 
					"    </div>\r\n" + 
					"    <hr>\r\n" + 
					"    选择商品的数量必须是数字！\r\n" + 
					"</div>\r\n" + 
					"<br>\r\n" + 
					"        <br>\r\n" + 
					"        <div align=\"left\" style=\"width: 400px\">\r\n" + 
					"            <label style=\"font-size: 14px\">统计在线人数：</label>\r\n" + 
					"            <br>\r\n" + 
					"            <label style=\"font-size: 12px\">\r\n" + 
					"            总人数："+sum+"；\r\n" + 
					"            已登录用户人数："+countUsers+"；\r\n" + 
					"            游客人数："+countVisitors+"；\r\n" + 
					"            </label>\r\n" + 
					"        </div>"+
					"</body>\r\n" + 
					"</html>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	//支付的页面
	public void payPage(HttpServletRequest req, HttpServletResponse resp, ArrayList<commodity> coms, ArrayList<Integer> comsNum) {
		// TODO Auto-generated method stub
		Cookie cookie_username=null;//用户名的cookie
		Cookie[] cookies=req.getCookies();
		if(cookies!=null) {
			for(int i=0;i<cookies.length;i++) {
				Cookie cookie=cookies[i];
				if(cookie.getName().equals("lastUsername")) {
					cookie_username=cookie;
					break;
				}
			}
		}
		
		String username=cookie_username.getValue();
		
		//resp.setContentType("text/html;charset=utf-8");
		try {
			PrintWriter out = resp.getWriter();
			out.println("<!DOCTYPE html>\r\n" + 
					"<html lang=\"en\">\r\n" + 
					"<head>\r\n" + 
					"    <meta charset=\"UTF-8\">\r\n" + 
					"    <title>choosedCommodities</title>\r\n" + 
					"</head>\r\n" + 
					"<body style=\"text-align:center;\" background=\""+req.getContextPath()+"/image/com.png\">\r\n" + 
					"<h4>用户名："+username+"</h4>\r\n" + 
					"<hr>\r\n" + 
					"<div>\r\n" + 
					"    <div align=\"left\">\r\n" + 
					"        <form method=\"get\" action=\"/homework02/chooseCommodities\">\r\n" + 
					"            <input type=\"submit\" name=\"chooseCom\" value=\"选择商品\" style=\"font-family:楷体;font-size:18px;width:84px;height:30px;background-color: lightgreen;border-radius: 2px;border:0px;\">\r\n" + 
					"        </form>\r\n" + 
					"        &nbsp;&nbsp;\r\n" + 
					"        <form method=\"get\" action=\"/homework02/login\">\r\n" + 
					"            <input type=\"submit\" name=\"logout\" value=\"退出登录\" style=\"font-family:楷体;font-size:18px;width:84px;height:30px;background-color: lightgreen;border-radius: 2px;border:0px;\">\r\n" + 
					"        </form>\r\n" + 
					"    </div>\r\n" + 
					"\r\n" + 
					"    <form method=\"post\" action=\"/homework02/payIndex\">\r\n" + 
					"        <hr>\r\n" + 
					"        <div align=\"center\">\r\n" + 
					"            <div style=\"width: auto;\">\r\n" + 
					"                <table style=\"border: 1px solid green;\">\r\n" + 
					"                    <tr>\r\n" + 
					"                        <th>商品名称</th>\r\n" + 
					"                        <th>单价（元）</th>\r\n" + 
					"                        <th>数量</th>\r\n" + 
					"                        <th>单项总价</th>\r\n" + 
					"                    </tr>\r\n");
			
			//展示输入的的数量大于0 的商品
			int sumMoney=0;
			for(int i=0;i<coms.size();i++) {
				if(comsNum.get(i)>0) {
					int tempSum=comsNum.get(i)*coms.get(i).getPrice();
					sumMoney=sumMoney+tempSum;
					out.println(" <tr>\r\n" + 
					"                        <td>\r\n" + 
					"                            "+coms.get(i).getName()+"\r\n" + 
					"                        </td>\r\n" + 
					"                        <td>\r\n" + 
					"                            "+coms.get(i).getPrice()+"\r\n" + 
					"                        </td>\r\n" + 
					"                        <td>\r\n" + 
					"                           "+comsNum.get(i)+"\r\n" + 
					"                        </td>\r\n" + 
					"                        <td>\r\n" + 
					"                           "+tempSum+"\r\n" + 
					"                        </td>\r\n" + 
					"                    </tr>\r\n");
				}
			}
					
			out.println("                </table>\r\n" + 
					"                <br>\r\n" + 
					"            </div>\r\n" + 
					"            <hr>\r\n" + 
					"            <div align=\"left\" style=\"width: 360px\">\r\n" + 
					"                <h4>总价："+sumMoney+"</h4>\r\n" + 
					"                 最优的优惠政策：\r\n");
			//获得所有优惠政策
			ArrayList<preferencialstrategy> pss=getPS();
			
			//判断当前符合的优惠政策（选择最优惠的那一个）
			String comment="";//符合的策略的comment
			int reachMoney=0;
			int cutMoney=0;
			String preferencialstrategies_id="";
			for(int i=0;i<pss.size();i++) {
				if(pss.get(i).getReachMoney()<=sumMoney) {//可以使用该优惠
					if(pss.get(i).getCutMoney()>cutMoney) {//该优惠优惠的更加多，使用该优惠
						reachMoney=pss.get(i).getReachMoney();
						cutMoney=pss.get(i).getCutMoney();
						comment=pss.get(i).getComment();
						preferencialstrategies_id=pss.get(i).getId();
					}
				}
			}
			
			sumMoney=sumMoney-cutMoney;
			
			//设置cookie---sumMoney
			String str_sumMoney=""+sumMoney;
			Cookie cookie = new Cookie("sumMoney", str_sumMoney);
			cookie.setMaxAge(Integer.MAX_VALUE);
			System.out.println("Add cookie");
			resp.addCookie(cookie);
			
			//设置cookie---preferencialstrategies_id
			String str_preferencialstrategies_id=preferencialstrategies_id;
			Cookie cookie2 = new Cookie("preferencialstrategies_id", str_preferencialstrategies_id);
			cookie2.setMaxAge(Integer.MAX_VALUE);
			System.out.println("Add cookie");
			resp.addCookie(cookie2);
			
			ServletContext context=getServletContext();
			int countVisitors=(int) context.getAttribute("countVisitors");
			int countUsers=(int) context.getAttribute("countUsers");
			int sum=countVisitors+countUsers;
			
			//保护机制（人数不会<0）
			if(sum<0) {
				sum=0;
			}
			if(countUsers<0) {
				countUsers=0;
			}
			if(countVisitors<0) {
				countVisitors=0;
			}
			
			out.println(" "+comment+"\r\n" + 
					"                <h4>需要支付的价格："+sumMoney+"（此处为优惠后的金额）</h4>\r\n" + 
					"            </div>\r\n" + 
					"            <hr>\r\n" + 
					"            <div align=\"center\">\r\n" + 
					"                <input type=\"submit\" name=\"buyCommodities\" value=\"支付\" style=\"font-family:楷体;font-size:18px;width:84px;height:30px;background-color: lightgreen;border-radius: 2px;border:0px;\">\r\n" + 
					"            </div>\r\n" + 
					"        </div>\r\n" + 
					"    </form>\r\n" + 
					"</div>\r\n" + 
					"<br>\r\n" + 
					"        <br>\r\n" + 
					"        <div align=\"left\" style=\"width: 400px\">\r\n" + 
					"            <label style=\"font-size: 14px\">统计在线人数：</label>\r\n" + 
					"            <br>\r\n" + 
					"            <label style=\"font-size: 12px\">\r\n" + 
					"            总人数："+sum+"；\r\n" + 
					"            已登录用户人数："+countUsers+"；\r\n" + 
					"            游客人数："+countVisitors+"；\r\n" + 
					"            </label>\r\n" + 
					"        </div>"+
					"</body>\r\n" + 
					"</html>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private ArrayList<preferencialstrategy> getPS() {
		// TODO Auto-generated method stub
		ArrayList<preferencialstrategy> ret=new ArrayList();
		
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		try {
			connection = datasource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			stmt = connection.prepareStatement("select * from preferencialstrategy ");
			result = stmt.executeQuery();
			while (result.next()) {
				preferencialstrategy ps=new preferencialstrategy();
				ps.setId(result.getString("id"));
				ps.setReachMoney(result.getInt("reachMoney"));
				ps.setCutMoney(result.getInt("cutMoney"));
				ps.setComment(result.getString("comment"));
				ret.add(ps);
				System.out.println("ps comment: "+ps.getComment());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			result.close();
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
