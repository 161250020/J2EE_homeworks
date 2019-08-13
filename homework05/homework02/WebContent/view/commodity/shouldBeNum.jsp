<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
<title>shouldBeNum</title>
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
		"    <title>payError</title>\r\n" + 
		"</head>\r\n" + 
		"<body style=\"text-align:center;\" background=\""+request.getContextPath()+"/image/pays.png\">\r\n" + 
		"<h4>用户名："+username+"</h4>\r\n" + 
		"<hr>\r\n" + 
		"<div>\r\n" + 
		"    <div align=\"left\">\r\n" + 
		"        <form method=\"get\" action=\"/homework02/chooseCommodities\">\r\n" + 
		"            <input type=\"submit\" name=\"chooseCom\" value=\"选择商品\" style=\"font-family:楷体;font-size:18px;width:84px;height:30px;background-color: lightgreen;border-radius: 2px;border:0px;\">\r\n" + 
		"        </form>\r\n" + 
		"        &nbsp;&nbsp;\r\n" + 
		"        <form method=\"get\" action=\"/homework02/login\">\r\n" + 
		"            <input type=\"submit\" name=\"logout\" value=\"退出登录\" style=\"font-family:楷体;font-size:18px;width:84px;height:30px;background-color: lightgreen;border-radius: 2px;border:0px;\">\r\n" + 
		"        </form>\r\n" + 
		"    </div>\r\n" + 
		"    <hr>\r\n" + 
		"    选择商品的数量必须是数字！\r\n" + 
		"</div>\r\n" + 
		"<br>\r\n" + 
		"        <br>\r\n" + 
		"        <div align=\"left\" style=\"width: 400px\">\r\n" + 
		"            <label style=\"font-size: 14px\">统计在线人数：</label>\r\n" + 
		"            <br>\r\n" + 
		"            <label style=\"font-size: 12px\">\r\n" + 
		"            总人数："+session.getAttribute("sum")+"；\r\n" + 
		"            已登录用户人数："+session.getAttribute("countUsers")+"；\r\n" + 
		"            游客人数："+session.getAttribute("countVisitors")+"；\r\n" + 
		"            </label>\r\n" + 
		"        </div>"+
		"</body>\r\n" + 
		"</html>");
%>
</body>
</html>