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

import factory.ServiceFactory;
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
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ArrayList<commodity> storedCommodities=new ArrayList();
		String username="游客";
		
		//修改cookie的用户名---类似moodle上面如果使用游客身份登录，就会清空cookie
		Cookie cookie=null;//存储名为lastUsername的输入的内容
		Cookie[] cookies=request.getCookies();
		boolean cookieFound_lastUsername=false;
		
		if(null!=cookies) {
			for(int i=0;i<cookies.length;i++) {
				cookie=cookies[i];
				if(cookie.getName().equals("lastUsername")) {
					cookieFound_lastUsername=true;

					//修改当前cookie的lastUsername的值为“游客”
					cookie.setValue(username);
					response.addCookie(cookie);
					
					break;
				}
			}
		}
		
		storedCommodities=getCommodities();
		//将商品的信息添加到session当中
		HttpSession session = request.getSession(true);
		session.setAttribute("commodities", storedCommodities);
		
		html(request, response, storedCommodities, username);
	}


	//获得所有的商品的信息
	private ArrayList<commodity> getCommodities() {
		// TODO Auto-generated method stub
		ArrayList<commodity> ret=new ArrayList();
		
		ret=(ArrayList<commodity>) ServiceFactory.getCommodityManageSevice().getAllCommodities();
		
		System.out.println("visitor login---getCommodities");
		
		return ret;
	}
	
	//显示页面--包含用户名，商品列表（两处需要插入数据的地方）
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
					"<h4>用户名："+username+"</h4>\r\n" + 
					"<hr>\r\n" + 
					"    <div align=\"left\">\r\n" + 
					"        &nbsp;&nbsp;\r\n" + 
					"        <form method=\"get\" action=\"/homework02/visitorIndex\">\r\n" + 
					"            <input type=\"submit\" name=\"gotoLogin\" value=\"去登录\" style=\"font-family:楷体;font-size:18px;width:84px;height:30px;background-color: lightgreen;border-radius: 2px;border:0px;\">\r\n" + 
					"        </form>\r\n" + 
					"    </div>\r\n" + 
					"\r\n" + 
					"    <form method=\"post\" action=\"/homework02/noMoreThanStores\">\r\n" + 
					"        <hr>\r\n" + 
					"        <div align=\"center\">\r\n" );
			
					//打印列表当中的商品
					//每5个商品打印在一页上面
					int size=storedCommodities.size();
					int rest=size;//还没有打印的数量
					int index=0;//打印到的index
					int pageNum=0;//一共分几页
					while(rest>0) {//如果还有商品未打印
						pageNum=pageNum+1;
						String display="none";
						if(pageNum==1) {//如果是第一页，display为block，否则为none
							display="block";
						}
						if(rest>=5) {//如果剩余的商品>5---可以打印满一页，按照5打印
							out.println("            <table id=\"p"+pageNum+"\" style=\"border: 1px solid green;display: "+display+"\">\r\n" + 
									"                <tr>\r\n" + 
									"                    <th>商品名称</th>\r\n" + 
									"                    <th>单价（元）</th>\r\n" + 
									"                    <th>库存</th>\r\n" + 
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
						else {//如果剩余的商品<5---不可以打印满一页，按照rest打印
							out.println("            <table id=\"p"+pageNum+"\" style=\"border: 1px solid green;display: "+display+"\">\r\n" + 
									"                <tr>\r\n" + 
									"                    <th>商品名称</th>\r\n" + 
									"                    <th>单价（元）</th>\r\n" + 
									"                    <th>库存</th>\r\n" + 
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
					
					//添加页数的按钮
					for(int i=1;i<=pageNum;i++) {
						out.println("<a href=\"javascript:showpage("+i+");\">第"+i+"页</a>");
					}
					
					out.println("            <br>\r\n" + 
					"        </div>\r\n" + 
					"    </form>\r\n" + 
					"</div>\r\n");

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
					
					//添加script
					out.println("<br>\r\n" + 
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
