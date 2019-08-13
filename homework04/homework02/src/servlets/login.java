package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import factory.ServiceFactory;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username="";
		HttpSession session=request.getSession(true);
		
		Cookie cookie=null;
		Cookie[] cookies=request.getCookies();
		
		//如果已登陆过，获得已登陆过的cookie的username
		if(null!=cookies) {
			System.out.print("not null");
			for(int i=0;i<cookies.length;i++) {
				cookie=cookies[i];
				if(cookie.getName().equals("lastUsername")) {
					System.out.print("equal");
					username=cookie.getValue();
					System.out.print("username:"+username);
					break;
				}
			}
		}
		
		//如果事件未注销---即session的username的attribute存在
		boolean exUser=false;
		String[] sessionAttNames=session.getValueNames();
		for(int i=0;i<sessionAttNames.length;i++) {
			if(sessionAttNames[i].equals("username")) {
				exUser=true;
			}
		}
				
		//session的username的attribute存在，且不为游客
		if((!username.equals("游客"))&&(exUser)) {
			try {
				//转到显示商品的首页
				response.sendRedirect(request.getContextPath() + "/chooseCommodities");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//session的username的attribute存在，且为游客
		else if((username.equals("游客"))&&(exUser)) {
			//清除session，重新登录
			session.invalidate();
			session=null;
			
			try {
				//转到登录页面
				response.sendRedirect(request.getContextPath() + "/login");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			//如果cookie记录的上次登录的值是游客
			if(username.equals("游客")) {
				//修改名称为lastUsername的cookie
				cookie.setValue("");
				response.addCookie(cookie);
				
				username="";
			}
			
			ServletContext context=getServletContext();
			context.setAttribute("countVisitors", 0);
			context.setAttribute("countUsers", 0);
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
			
			session=request.getSession(true);
			session.setAttribute("sum", sum);
			session.setAttribute("countUsers", countUsers);
			session.setAttribute("countVisitors", countVisitors);
			context.getRequestDispatcher("/view/login/login.jsp").forward(request, response);
			
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
