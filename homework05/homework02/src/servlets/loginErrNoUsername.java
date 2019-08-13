package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

		HttpSession session=request.getSession(true);
		session.setAttribute("sum", sum);
		session.setAttribute("countUsers", countUsers);
		session.setAttribute("countVisitors", countVisitors);
		context.getRequestDispatcher("/view/login/loginErrNoUsername.jsp")
		.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
