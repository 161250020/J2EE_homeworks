package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
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
		
		//如果session存在，且cookie中lastUsername非“游客”---已登陆过
		if((session!=null)&&(!username.equals("游客"))) {
			try {
				//转到显示商品的首页
				response.sendRedirect(request.getContextPath() + "/chooseCommodities");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//如果session存在，且为游客登录
		else if((session!=null)&&(username.equals("游客"))) {
			//清除session
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
			
			//response.setContentType("text/html;charset=utf-8");
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
					"</form>\r\n" + 
					"<br>"+
					"<br>"+
					"<hr>"+
					"<form method=\"post\" action=\""+response.encodeURL(request.getContextPath()+"/visitorIndex")+"\">\r\n"+
					"            <div align=\"center\">\r\n" + 
					"                <input type=\"submit\" name=\"Submit2\" value=\"游客登录\" style=\"font-family:楷体;font-size:18px;width:120px;height:30px;background-color: lightgreen;border-radius: 2px;border:0px;\">\r\n" + 
					"            </div>\r\n"+
					"</form>\r\n" + 
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
					"        </div>\r\n" + 
					"    </div>\r\n" + 
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
