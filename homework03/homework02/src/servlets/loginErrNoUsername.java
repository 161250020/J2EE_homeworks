package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class loginErrNoUsername
 */
@WebServlet("/loginErrNoUsername")
public class loginErrNoUsername extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginErrNoUsername() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>\r\n" + 
				"<html lang=\"en\">\r\n" + 
				"<head>\r\n" + 
				"    <meta charset=\"UTF-8\">\r\n" + 
				"    <title>loginErrorNoUsername</title>\r\n" + 
				"</head>\r\n" + 
				"<body style=\"text-align:center;\" background=\""+request.getContextPath()+"/image/back.png\">\r\n" + 
				"<form method=\"post\" action=\"/homework02/login\">\r\n" + 
				"    <div align=\"center\">\r\n" + 
				"        <h4>登录失败！</h4>\r\n" + 
				"        <hr>\r\n" + 
				"        <h5>失败原因：该用户不存在！</h5>\r\n" + 
				"        <br>\r\n" + 
				"        <input type=\"submit\" name=\"reLogin\" value=\"重新登录\" style=\"font-family:楷体;font-size:18px;width:84px;height:30px;background-color: lightgreen;border-radius: 2px;border:0px;\">\r\n" + 
				"    </div>\r\n" + 
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
