<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.im.pojo.User" %>
<html>
<head>
    <title>主界面</title>
</head>
<body>
<%
    User user = (User) session.getAttribute("loginUser");
    if(user == null){
        response.sendRedirect("login.jsp");
        return;
    }
%>
<h3>欢迎你：<%=user.getNickname()%></h3>
<p>账号：<%=user.getAccount()%></p>

<hr>
<h4>功能菜单</h4>
<div>好友列表 | 群聊 | 个人中心 | 消息记录</div>
</body>
</html>