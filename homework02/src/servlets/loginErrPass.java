package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class loginErrPass
 */
@WebServlet("/loginErrPass")
public class loginErrPass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginErrPass() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>\r\n" + 
				"<html lang=\"en\">\r\n" + 
				"<head>\r\n" + 
				"    <meta charset=\"UTF-8\">\r\n" + 
				"    <title>loginErrorPassword</title>\r\n" + 
				"</head>\r\n" + 
				"<body style=\"text-align:center;\" background=\""+request.getContextPath()+"/image/back.png\">\r\n" + 
				"<form method=\"post\" action=\"/homework02/login\">\r\n" + 
				"    <div align=\"center\">\r\n" + 
				"        <h4>µ«¬º ß∞‹£°</h4>\r\n" + 
				"        <hr>\r\n" + 
				"        <h5> ß∞‹‘≠“Ú£∫√‹¬Î¥ÌŒÛ£°</h5>\r\n" + 
				"        <br>\r\n" + 
				"        <input type=\"submit\" name=\"reLogin\" value=\"÷ÿ–¬µ«¬º\" style=\"font-family:ø¨ÃÂ;font-size:18px;width:84px;height:30px;background-color: lightgreen;border-radius: 2px;border:0px;\">\r\n" + 
				"    </div>\r\n" + 
				"</form>\r\n" + 
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
