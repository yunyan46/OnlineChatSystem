<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>登录</title>
</head>
<body>
<div style="width:300px;margin:100px auto;">
    <h3>登录</h3>
    <form action="login" method="post">
        <div>账号：<input name="account" required></div>
        <div style="margin:10px 0;">密码：<input name="password" type="password" required></div>
        <button type="submit">登录</button>
    </form>
    <div style="margin-top:20px;">
        没有账号？<a href="register.jsp">去注册</a>
    </div>
</div>
</body>
</html>