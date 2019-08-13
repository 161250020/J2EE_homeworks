package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class payErr
 */
@WebServlet("/payErr")
public class payErr extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public payErr() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Cookie cookie_username=null;//用户名的cookie
		Cookie[] cookies=request.getCookies();
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
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>\r\n" + 
				"<html lang=\"en\">\r\n" + 
				"<head>\r\n" + 
				"    <meta charset=\"UTF-8\">\r\n" + 
				"    <title>payError</title>\r\n" + 
				"</head>\r\n" + 
				"<body style=\"text-align:center;\" background=\""+request.getContextPath()+"/image/pays.png\">\r\n" + 
				"<h4>用户名："+username+"</h4>\r\n" + 
				"<hr>\r\n" + 
				"<div>\r\n" + 
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
				"    下订单失败！用户金额不足支付！\r\n" + 
				"</div>\r\n" + 
				"</body>\r\n" + 
				"</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
