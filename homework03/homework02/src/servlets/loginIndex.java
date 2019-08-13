package servlets;

import java.io.IOException;
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


/**
 * Servlet implementation class loginIndex
 */
@WebServlet("/loginIndex")
public class loginIndex extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataSource datasource = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginIndex() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init() {
    	InitialContext jndiContext=null;
    	
    	Properties properties=new Properties();
    	properties.put(javax.naming.Context.PROVIDER_URL, "jnp:///");
    	properties.put(javax.naming.Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");
    	
    	try {
			jndiContext=new InitialContext(properties);
			datasource=(DataSource) jndiContext.lookup("java:comp/env/jdbc/homework02");
			System.out.println("got context");
			System.out.println("About to get ds---loginIndex");
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
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub

		HttpSession session = req.getSession(false);
		boolean cookieFound_lastUsername=false;//找到lastUsername的cookie
		String lastUsername="";//登录的username
		String lastPassword="";//登录的用户的密码
		System.out.println("request"+req.getParameter("lastUsername"));
		Cookie cookie=null;//存储名为lastUsername的输入的内容
		Cookie[] cookies=req.getCookies();
		
		if(null!=cookies) {
			for(int i=0;i<cookies.length;i++) {
				cookie=cookies[i];
				if(cookie.getName().equals("lastUsername")) {
					cookieFound_lastUsername=true;
					break;
				}
			}
		}
		
		//由于无论是否事件注销，都需要验证密码，因为你永远不知道数据库当中的内容是否已经改变了
		//如果事件已注销
		if(null==session) {
			String submitUsername=req.getParameter("login");//获得提交的username
			System.out.println("login:"+submitUsername);
			String submitPassword=req.getParameter("password");//获得提交的password
			
			System.out.println("loginIndex --- session null");
			//如果提交的用户名不为空
			if(!submitUsername.equals("")) {
				//查看登录情况
				Connection connection = null;
				PreparedStatement stmt = null;
				ResultSet result = null;
				ArrayList<String> list = new ArrayList();
				
				try {
					connection = datasource.getConnection();
				} catch (SQLException e) {
					e.printStackTrace();
				}

				try {
					stmt = connection.prepareStatement("select password from users where username = ?");
					stmt.setString(1, submitUsername);
					result = stmt.executeQuery();
					while (result.next()) {
						list.add(result.getString("password"));
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
				
				//如果该用户存在
				if(list.size()!=0) {
					//获得数据库返回的输入的用户名的密码
					String retPass=list.get(0);
					System.out.println("password:"+retPass);
					
					//如果输入的密码正确
					if(retPass.equals(submitPassword)) {
						//此时修改ServletContext的countUsers
						ServletContext context=getServletContext();
						int countUsers=(int) context.getAttribute("countUsers");
						countUsers=countUsers+1;
						context.setAttribute("countUsers", countUsers);
						System.out.println("change countUsers(+) "+countUsers);
						
						//解决名为lastUsername的cookie值的问题
						//如果lastUsername的cookie已存在
						if(cookieFound_lastUsername) {
							//如果名为lastUsername的cookie的值和输入的值不相同,
							//即：另外一个用户名登录了，则修改名为lastUsername的cookie的值为当前登录的用户名；
							if(!submitUsername.equals(cookie.getValue())) {
								System.out.println("Change cookie lastUsername");
								cookie.setValue(submitUsername);
								resp.addCookie(cookie);
							}
						}
						//如果名为lastUsername的cookie不存在
						else {
							System.out.println("Add cookie lastUsername");
							cookie=new Cookie("lastUsername", submitUsername);
							cookie.setMaxAge(Integer.MAX_VALUE);
							resp.addCookie(cookie);
						}
						
						//进行登录成功的页面的显示
						System.out.println("显示登录成功的首页！");
						try {
							//将登录的用户名添加到session当中
							session=req.getSession(true);
							session.setAttribute("lastUsername", submitUsername);
							
							//记录浏览器状态
							session.setAttribute("state", cookie);
							
							//转到显示商品的首页
							resp.sendRedirect(req.getContextPath() + "/chooseCommodities");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					//如果密码错误
					else {
						System.out.println("密码错误！");
						try {
							resp.sendRedirect(req.getContextPath() + "/loginErrPass");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				//如果该用户不存在
				else {
					System.out.println("该用户不存在！");
					try {
						resp.sendRedirect(req.getContextPath() + "/loginErrNoUsername");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			else {
				System.out.println("登录的用户名不可为空！");
				// Display the login page. If the cookie exists, set login
				try {
					resp.sendRedirect(req.getContextPath() + "/login");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		//如果事件未注销，重新加载页面，session不为空
		else {
			//获得session中的lastUsername---但是获取的数值是没有用处的
			//String session_lastUsername=(String) session.getAttribute("lastUsername");
			//System.out.println("session not null, lastUsername:"+session_lastUsername);
			
			try {
				//转到显示商品的首页
				resp.sendRedirect(req.getContextPath() + "/chooseCommodities");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}
