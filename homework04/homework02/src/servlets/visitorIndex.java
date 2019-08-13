package servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class visitorIndex
 */
@WebServlet("/visitorIndex")
public class visitorIndex extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public visitorIndex() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session=request.getSession(false);
		
		if(request.getParameter("Submit2")!=null) {//接收的是login页面的“游客登录”，用来countVisitors++（且避免转到visitorLogin页面之后刷新带来的问题）
			//先修改ServletContext的countVisitors
			ServletContext context=getServletContext();
			int countVisitors=(int) context.getAttribute("countVisitors");
			countVisitors=countVisitors+1;
			context.setAttribute("countVisitors", countVisitors);
			System.out.println("change countVisitors(+) "+countVisitors);
			
			//后转到/visitorLogin
			response.sendRedirect(request.getContextPath()+"/visitorLogin");
		}
		else {//接受的是visitorLogin页面的游客“去登陆”，用来countVisitors--（且避免转到login页面之后刷新带来的问题）
			//先修改ServletContext的countVisitors
			ServletContext context=getServletContext();
			int countVisitors=(int) context.getAttribute("countVisitors");
			countVisitors=countVisitors-1;
			context.setAttribute("countVisitors", countVisitors);
			System.out.println("change countVisitors(-) "+countVisitors);
			
			//清除session
			session.invalidate();
			session=null;
			
			//后转到/visitorLogin
			response.sendRedirect(request.getContextPath()+"/login");
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
