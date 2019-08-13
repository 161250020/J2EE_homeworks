package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		HttpSession session=request.getSession(false);
		Cookie cookie=null;
		Cookie[] cookies=request.getCookies();
		
		//如果已经退出登录，就结束原先的session，使得新的session=null
		if(null!=request.getParameter("logout")) {
			System.out.println("logout");
			if(session!=null) {
				session.invalidate();
				session=null;
			}
		}
		
		//如果session存在，就转到chooseCommodities的页面
		if(session!=null) {
			response.sendRedirect(request.getContextPath()+"/chooseCommodities");
		}
		else {
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
			
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out=response.getWriter();
			out.println("<!DOCTYPE html>\r\n" + 
					"<html lang=\"en\">\r\n" + 
					"<head>\r\n" + 
					"    <meta charset=\"UTF-8\">\r\n" + 
					"    <title>login</title>\r\n" + 
					"</head>\r\n" + 
					"<body style=\"text-align:center;\" background=\""+request.getContextPath()+"/image/back.png\">\r\n" + 
					"<form method=\"post\" action=\""+response.encodeURL(request.getContextPath()+"/loginIndex")+"\">\r\n" + 
					"    <div align=\"center\">\r\n" + 
					"        <h4>登录</h4>\r\n" + 
					"        <hr>\r\n" + 
					"        <div align=\"left\" style=\"width: 250px\">\r\n" + 
					"            username:\r\n" + 
					"            <input type=\"text\" name=\"login\" value=\""+username+"\">\r\n" + 
					"            <br>\r\n" + 
					"            <br>\r\n" + 
					"            password:\r\n" + 
					"            <input type=\"password\" name=\"password\" value=\"\">\r\n" + 
					"            <br>\r\n" + 
					"            <br>\r\n" + 
					"            <div align=\"center\">\r\n" + 
					"                <input type=\"submit\" name=\"Submit\" value=\"登录\" style=\"font-family:楷体;font-size:18px;width:60px;height:30px;background-color: lightgreen;border-radius: 2px;border:0px;\">\r\n" + 
					"            </div>\r\n" + 
					"        </div>\r\n" + 
					"    </div>\r\n" + 
					"</form>\r\n" + 
					"</body>\r\n" + 
					"</html>");
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
