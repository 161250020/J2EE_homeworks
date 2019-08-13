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
		
		Cookie cookie_username=null;//�û�����cookie
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
		
		ServletContext context=getServletContext();
		int countVisitors=(int) context.getAttribute("countVisitors");
		int countUsers=(int) context.getAttribute("countUsers");
		int sum=countVisitors+countUsers;
		
		//�������ƣ���������<0��
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
				"    <title>payError</title>\r\n" + 
				"</head>\r\n" + 
				"<body style=\"text-align:center;\" background=\""+request.getContextPath()+"/image/pays.png\">\r\n" + 
				"<h4>�û�����"+username+"</h4>\r\n" + 
				"<hr>\r\n" + 
				"<div>\r\n" + 
				"<div>\r\n" + 
				"    <div align=\"left\">\r\n" + 
				"        <form method=\"get\" action=\"/homework02/chooseCommodities\">\r\n" + 
				"            <input type=\"submit\" name=\"chooseCom\" value=\"ѡ����Ʒ\" style=\"font-family:����;font-size:18px;width:84px;height:30px;background-color: lightgreen;border-radius: 2px;border:0px;\">\r\n" + 
				"        </form>\r\n" + 
				"        &nbsp;&nbsp;\r\n" + 
				"        <form method=\"get\" action=\"/homework02/logout\">\r\n" + 
				"            <input type=\"submit\" name=\"logout\" value=\"�˳���¼\" style=\"font-family:����;font-size:18px;width:84px;height:30px;background-color: lightgreen;border-radius: 2px;border:0px;\">\r\n" + 
				"        </form>\r\n" + 
				"    </div>\r\n" + 
				"    <hr>\r\n" + 
				"    �¶���ʧ�ܣ��û�����֧����\r\n" + 
				"</div>\r\n" + 
				"<br>\r\n" + 
				"        <br>\r\n" + 
				"        <div align=\"left\" style=\"width: 400px\">\r\n" + 
				"            <label style=\"font-size: 14px\">ͳ������������</label>\r\n" + 
				"            <br>\r\n" + 
				"            <label style=\"font-size: 12px\">\r\n" + 
				"            ��������"+sum+"��\r\n" + 
				"            �ѵ�¼�û�������"+countUsers+"��\r\n" + 
				"            �ο�������"+countVisitors+"��\r\n" + 
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
