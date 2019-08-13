package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;


/**
 * Servlet implementation class loginIndex
 */
@WebServlet("/loginIndex")
public class loginIndex extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataSource datasource = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginIndex() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init() {
    	InitialContext jndiContext=null;
    	
    	Properties properties=new Properties();
    	properties.put(javax.naming.Context.PROVIDER_URL, "jnp:///");
    	properties.put(javax.naming.Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");
    	
    	try {
			jndiContext=new InitialContext(properties);
			datasource=(DataSource) jndiContext.lookup("java:comp/env/jdbc/homework02");
			System.out.println("got context");
			System.out.println("About to get ds---loginIndex");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub

		HttpSession session = req.getSession(false);
		boolean cookieFound_lastUsername=false;//�ҵ�lastUsername��cookie
		String lastUsername="";//��¼��username
		String lastPassword="";//��¼���û�������
		System.out.println("request"+req.getParameter("lastUsername"));
		Cookie cookie=null;//�洢��ΪlastUsername�����������
		Cookie[] cookies=req.getCookies();
		
		if(null!=cookies) {
			for(int i=0;i<cookies.length;i++) {
				cookie=cookies[i];
				if(cookie.getName().equals("lastUsername")) {
					cookieFound_lastUsername=true;
					break;
				}
			}
		}
		
		//���������Ƿ��¼�ע��������Ҫ��֤���룬��Ϊ����Զ��֪�����ݿ⵱�е������Ƿ��Ѿ��ı���
		//����¼���ע��
		if(null==session) {
			String submitUsername=req.getParameter("login");//����ύ��username
			System.out.println("login:"+submitUsername);
			String submitPassword=req.getParameter("password");//����ύ��password
			
			System.out.println("loginIndex --- session null");
			//����ύ���û�����Ϊ��
			if(!submitUsername.equals("")) {
				//�鿴��¼���
				Connection connection = null;
				PreparedStatement stmt = null;
				ResultSet result = null;
				ArrayList<String> list = new ArrayList();
				
				try {
					connection = datasource.getConnection();
				} catch (SQLException e) {
					e.printStackTrace();
				}

				try {
					stmt = connection.prepareStatement("select password from users where username = ?");
					stmt.setString(1, submitUsername);
					result = stmt.executeQuery();
					while (result.next()) {
						list.add(result.getString("password"));
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					result.close();
					stmt.close();
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//������û�����
				if(list.size()!=0) {
					//������ݿⷵ�ص�������û���������
					String retPass=list.get(0);
					System.out.println("password:"+retPass);
					
					//��������������ȷ
					if(retPass.equals(submitPassword)) {
						//��ʱ�޸�ServletContext��countUsers
						ServletContext context=getServletContext();
						int countUsers=(int) context.getAttribute("countUsers");
						countUsers=countUsers+1;
						context.setAttribute("countUsers", countUsers);
						System.out.println("change countUsers(+) "+countUsers);
						
						//�����ΪlastUsername��cookieֵ������
						//���lastUsername��cookie�Ѵ���
						if(cookieFound_lastUsername) {
							//�����ΪlastUsername��cookie��ֵ�������ֵ����ͬ,
							//��������һ���û�����¼�ˣ����޸���ΪlastUsername��cookie��ֵΪ��ǰ��¼���û�����
							if(!submitUsername.equals(cookie.getValue())) {
								System.out.println("Change cookie lastUsername");
								cookie.setValue(submitUsername);
								resp.addCookie(cookie);
							}
						}
						//�����ΪlastUsername��cookie������
						else {
							System.out.println("Add cookie lastUsername");
							cookie=new Cookie("lastUsername", submitUsername);
							cookie.setMaxAge(Integer.MAX_VALUE);
							resp.addCookie(cookie);
						}
						
						//���е�¼�ɹ���ҳ�����ʾ
						System.out.println("��ʾ��¼�ɹ�����ҳ��");
						try {
							//����¼���û�����ӵ�session����
							session=req.getSession(true);
							session.setAttribute("lastUsername", submitUsername);
							
							//��¼�����״̬
							session.setAttribute("state", cookie);
							
							//ת����ʾ��Ʒ����ҳ
							resp.sendRedirect(req.getContextPath() + "/chooseCommodities");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					//����������
					else {
						System.out.println("�������");
						try {
							resp.sendRedirect(req.getContextPath() + "/loginErrPass");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				//������û�������
				else {
					System.out.println("���û������ڣ�");
					try {
						resp.sendRedirect(req.getContextPath() + "/loginErrNoUsername");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			else {
				System.out.println("��¼���û�������Ϊ�գ�");
				// Display the login page. If the cookie exists, set login
				try {
					resp.sendRedirect(req.getContextPath() + "/login");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		//����¼�δע�������¼���ҳ�棬session��Ϊ��
		else {
			//���session�е�lastUsername---���ǻ�ȡ����ֵ��û���ô���
			//String session_lastUsername=(String) session.getAttribute("lastUsername");
			//System.out.println("session not null, lastUsername:"+session_lastUsername);
			
			try {
				//ת����ʾ��Ʒ����ҳ
				resp.sendRedirect(req.getContextPath() + "/chooseCommodities");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}
