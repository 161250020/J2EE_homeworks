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
 * Servlet implementation class logout
 */
@WebServlet("/logout")
public class logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public logout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//�޸�ServletContext��countUsers
		ServletContext context=getServletContext();
		int countUsers=(int) context.getAttribute("countUsers");
		countUsers=countUsers-1;
		context.setAttribute("countUsers", countUsers);
		System.out.println("change countUsers(-) "+countUsers);
		
		//����Ѿ��˳���¼���ͽ���ԭ�ȵ�session��ʹ���µ�session=null
		HttpSession session=request.getSession(false);
		if(null != request.getParameter("logout")) {
			if(session!=null) {
				session.invalidate();
				session=null;
				System.out.println("session null-----------------");
			}
		}
		
		//ת��loginҳ�棬׼�����µ�¼
		response.sendRedirect(request.getContextPath()+"/login");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
