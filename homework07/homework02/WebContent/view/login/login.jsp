<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>login</title>
</head>
<body style="text-align:center;" background="<%=request.getContextPath() + "/image/back.png"%>">
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
		"    <title>login</title>\r\n" + 
		"</head>\r\n" + 
		"<body style=\"text-align:center;\" background=\""+request.getContextPath()+"/image/back.png\">\r\n" + 
		"<form method=\"post\" action=\""+response.encodeURL(request.getContextPath()+"/loginIndex")+"\">\r\n" + 
		"    <div align=\"center\">\r\n" + 
		"        <h4>登录</h4>\r\n" + 
		"        <hr>\r\n" + 
		"        <div align=\"left\" style=\"width: 250px\">\r\n" + 
		"            username:\r\n" + 
		"            <input type=\"text\" name=\"login\" value=\""+username+"\">\r\n" + 
		"            <br>\r\n" + 
		"            <br>\r\n" + 
		"            password:\r\n" + 
		"            <input type=\"password\" name=\"password\" value=\"\">\r\n" + 
		"            <br>\r\n" + 
		"            <br>\r\n" + 
		"            <div align=\"center\">\r\n" + 
		"                <input type=\"submit\" name=\"Submit\" value=\"登录\" style=\"font-family:楷体;font-size:18px;width:60px;height:30px;background-color: lightgreen;border-radius: 2px;border:0px;\">\r\n" + 
		"            </div>\r\n" + 
		"</form>\r\n" + 
		"<br>"+
		"<br>"+
		"<hr>"+
		"<form method=\"post\" action=\""+response.encodeURL(request.getContextPath()+"/visitorIndex")+"\">\r\n"+
		"            <div align=\"center\">\r\n" + 
		"                <input type=\"submit\" name=\"Submit2\" value=\"游客登录\" style=\"font-family:楷体;font-size:18px;width:120px;height:30px;background-color: lightgreen;border-radius: 2px;border:0px;\">\r\n" + 
		"            </div>\r\n"+
		"</form>\r\n" + 
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
		"        </div>\r\n" + 
		"    </div>\r\n" + 
		"</body>\r\n" + 
		"</html>");
%>

</body>
</html>