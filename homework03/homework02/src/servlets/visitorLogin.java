package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

import models.commodity;

/**
 * Servlet implementation class visitorLogin
 */
@WebServlet("/visitorLogin")
public class visitorLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataSource datasource = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public visitorLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

    //��ʼ�����ݿ�
    public void init() {
    	InitialContext jndiContext=null;
    	
    	Properties properties=new Properties();
    	properties.put(javax.naming.Context.PROVIDER_URL, "jnp:///");
    	properties.put(javax.naming.Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");
    	
    	try {
			jndiContext=new InitialContext(properties);
			datasource=(DataSource) jndiContext.lookup("java:comp/env/jdbc/homework02");
			System.out.println("got context");
			System.out.println("About to get ds---visitor login");
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
		
		ArrayList<commodity> storedCommodities=new ArrayList();
		String username="�ο�";
		
		//�޸�cookie���û���---����moodle�������ʹ���ο���ݵ�¼���ͻ����cookie
		Cookie cookie=null;//�洢��ΪlastUsername�����������
		Cookie[] cookies=request.getCookies();
		boolean cookieFound_lastUsername=false;
		
		if(null!=cookies) {
			for(int i=0;i<cookies.length;i++) {
				cookie=cookies[i];
				if(cookie.getName().equals("lastUsername")) {
					cookieFound_lastUsername=true;

					//�޸ĵ�ǰcookie��lastUsername��ֵΪ���ο͡�
					cookie.setValue(username);
					response.addCookie(cookie);
					
					break;
				}
			}
		}
		
		storedCommodities=getCommodities();
		//����Ʒ����Ϣ��ӵ�session����
		HttpSession session = request.getSession(true);
		session.setAttribute("commodities", storedCommodities);
		
		html(request, response, storedCommodities, username);
	}


	//������е���Ʒ����Ϣ
	private ArrayList<commodity> getCommodities() {
		// TODO Auto-generated method stub
		ArrayList<commodity> ret=new ArrayList();
		
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		try {
			connection = datasource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			stmt = connection.prepareStatement("select * from commodities ");
			result = stmt.executeQuery();
			while (result.next()) {
				commodity com=new commodity();
				com.setName(result.getString("name"));
				com.setPrice(result.getInt("price"));
				com.setStoredSum(result.getInt("storedSum"));
				ret.add(com);
				System.out.println("commodity name: "+com.getName());
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
		
		return ret;
	}
	
	//��ʾҳ��--�����û�������Ʒ�б�������Ҫ�������ݵĵط���
	public void html(HttpServletRequest req, HttpServletResponse resp, ArrayList<commodity> storedCommodities, String username) {
		// TODO Auto-generated method stub
		
		//resp.setContentType("text/html;charset=utf-8");
		try {
			PrintWriter out = resp.getWriter();
			out.println("<!DOCTYPE html>\r\n" + 
					"<html lang=\"en\">\r\n" + 
					"<head>\r\n" + 
					"    <meta charset=\"UTF-8\">\r\n" + 
					"    <title>chooseCommodities</title>\r\n" + 
					"</head>\r\n" + 
					"<body style=\"text-align:center;\" background=\""+req.getContextPath()+"/image/com.png\">\r\n" + 
					"<h4>�û�����"+username+"</h4>\r\n" + 
					"<hr>\r\n" + 
					"    <div align=\"left\">\r\n" + 
					"        &nbsp;&nbsp;\r\n" + 
					"        <form method=\"get\" action=\"/homework02/visitorIndex\">\r\n" + 
					"            <input type=\"submit\" name=\"gotoLogin\" value=\"ȥ��¼\" style=\"font-family:����;font-size:18px;width:84px;height:30px;background-color: lightgreen;border-radius: 2px;border:0px;\">\r\n" + 
					"        </form>\r\n" + 
					"    </div>\r\n" + 
					"\r\n" + 
					"    <form method=\"post\" action=\"/homework02/noMoreThanStores\">\r\n" + 
					"        <hr>\r\n" + 
					"        <div align=\"center\">\r\n" );
			
					//��ӡ�б��е���Ʒ
					//ÿ5����Ʒ��ӡ��һҳ����
					int size=storedCommodities.size();
					int rest=size;//��û�д�ӡ������
					int index=0;//��ӡ����index
					int pageNum=0;//һ���ּ�ҳ
					while(rest>0) {//���������Ʒδ��ӡ
						pageNum=pageNum+1;
						String display="none";
						if(pageNum==1) {//����ǵ�һҳ��displayΪblock������Ϊnone
							display="block";
						}
						if(rest>=5) {//���ʣ�����Ʒ>5---���Դ�ӡ��һҳ������5��ӡ
							out.println("            <table id=\"p"+pageNum+"\" style=\"border: 1px solid green;display: "+display+"\">\r\n" + 
									"                <tr>\r\n" + 
									"                    <th>��Ʒ����</th>\r\n" + 
									"                    <th>���ۣ�Ԫ��</th>\r\n" + 
									"                    <th>���</th>\r\n" + 
									"                </tr>\r\n");
							
							for(int i=0;i<5;i++) {
								commodity com=storedCommodities.get(index);
								out.println(
										"                <tr>\r\n" + 
										"                    <td>\r\n" + 
										"                       "+com.getName()+"\r\n" + 
										"                    </td>\r\n" + 
										"                    <td>\r\n" + 
										"                        "+com.getPrice()+"\r\n" + 
										"                    </td>\r\n" + 
										"                    <td>\r\n" + 
										"                        "+com.getStoredSum()+"\r\n" + 
										"                    </td>\r\n" + 
										"                </tr>\r\n");
								index=index+1;
							}
							
							out.println("</table>\r\n");
							
							rest=rest-5;
						}
						else {//���ʣ�����Ʒ<5---�����Դ�ӡ��һҳ������rest��ӡ
							out.println("            <table id=\"p"+pageNum+"\" style=\"border: 1px solid green;display: "+display+"\">\r\n" + 
									"                <tr>\r\n" + 
									"                    <th>��Ʒ����</th>\r\n" + 
									"                    <th>���ۣ�Ԫ��</th>\r\n" + 
									"                    <th>���</th>\r\n" + 
									"                </tr>\r\n");
							
							for(int i=0;i<rest;i++) {
								commodity com=storedCommodities.get(index);
								out.println("                <tr>\r\n" + 
										"                    <td>\r\n" + 
										"                       "+com.getName()+"\r\n" + 
										"                    </td>\r\n" + 
										"                    <td>\r\n" + 
										"                        "+com.getPrice()+"\r\n" + 
										"                    </td>\r\n" + 
										"                    <td>\r\n" + 
										"                        "+com.getStoredSum()+"\r\n" + 
										"                    </td>\r\n" + 
										"                </tr>\r\n");
								index=index+1;
							}
							
							out.println("</table>\r\n");
							rest=0;
						}
					}
					
					//���ҳ���İ�ť
					for(int i=1;i<=pageNum;i++) {
						out.println("<a href=\"javascript:showpage("+i+");\">��"+i+"ҳ</a>");
					}
					
					out.println("            <br>\r\n" + 
					"        </div>\r\n" + 
					"    </form>\r\n" + 
					"</div>\r\n");

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
					
					//���script
					out.println("<br>\r\n" + 
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
							"<script>\r\n" + 
							"    function showpage(page){\r\n" + 
							"        for(var i=1;i<=3;i++) {\r\n" + 
							"            if (page==i){\r\n" + 
							"                document.getElementById(\"p\"+i).style.display=\"block\";\r\n" + 
							"            } else {\r\n" + 
							"                document.getElementById(\"p\"+i).style.display=\"none\";\r\n" + 
							"            }\r\n" + 
							"        }\r\n" + 
							"    }\r\n" + 
							"</script>");
					
					
					out.println("</body>\r\n" + 
					"</html>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
