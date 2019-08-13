<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta charset="UTF-8">
<title>loginErrorNoUsername</title>
</head>
<body style="text-align:center;" background="<%=request.getContextPath() + "/image/back.png"%>">
<form method="post" action="/homework02/login">
    <div align="center">
        <h4>登录失败！</h4>
        <hr>
        <h5>失败原因：该用户不存在！</h5>
        <br>
        <input type="submit" name="reLogin" value="重新登录" style="font-family:楷体;font-size:18px;width:82px;height:30px;background-color: lightgreen;border-radius: 2px;border:0px;">
    </div>
</form>

 		<br>
        <br>
        <div align="left" style="width: 250px">
            <label style="font-size: 14px">统计在线人数：</label>
            <br>
            <label style="font-size: 12px">
            总人数：<%=session.getAttribute("sum")%>；
            已登录用户人数：<%=session.getAttribute("countUsers")%>；
            游客人数：<%=session.getAttribute("countVisitors")%>；
            </label>
        </div>
        
</body>
</html>