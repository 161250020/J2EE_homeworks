<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="commodity" uri="/WEB-INF/tlds/commodityInfo.tld" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
<title>choose commodities</title>
</head>
<body>


<%
String username="";
Cookie cookie=null;
Cookie cookies[]=request.getCookies();
	if(null!=cookies) {
	for(int i=0;i<cookies.length;i++) {
		cookie=cookies[i];
		if(cookie.getName().equals("lastUsername")) {
			username=cookie.getValue();
			break;
		}
	}
}

out.println("<!DOCTYPE html>\r\n" + 
		"<html lang=\"en\">\r\n" + 
		"<head>\r\n" + 
		"    <meta charset=\"UTF-8\">\r\n" + 
		"    <title>chooseCommodities</title>\r\n" + 
		"</head>\r\n" + 
		"<body style=\"text-align:center;\" background=\""+request.getContextPath()+"/image/com.png\">\r\n" + 
		"<h4>用户名："+username+"</h4>\r\n" + 
		"<hr>\r\n" + 
		"    <div align=\"left\">\r\n" + 
		"        <form method=\"get\" action=\"/homework02/chooseCommodities\">\r\n" + 
		"            <input type=\"submit\" name=\"chooseCom\" value=\"选择商品\" style=\"font-family:楷体;font-size:18px;width:84px;height:30px;background-color: lightgreen;border-radius: 2px;border:0px;\">\r\n" + 
		"        </form>\r\n" + 
		"        &nbsp;&nbsp;\r\n" + 
		"        <form method=\"get\" action=\"/homework02/logout\">\r\n" + 
		"            <input type=\"submit\" name=\"logout\" value=\"退出登录\" style=\"font-family:楷体;font-size:18px;width:84px;height:30px;background-color: lightgreen;border-radius: 2px;border:0px;\">\r\n" + 
		"        </form>\r\n" + 
		"    </div>\r\n" + 
		"\r\n" + 
		"    <form method=\"post\" action=\"/homework02/noMoreThanStores\">\r\n" + 
		"        <hr>\r\n" + 
		"        <div align=\"center\">\r\n" );
%>
		<commodity:commodityInfo/>
<%
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

%>
</body>
</html>