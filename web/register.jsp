<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>注册</title>
</head>
<body>
<div style="width:300px;margin:100px auto;">
    <h3>注册</h3>
    <form action="register" method="post">
        <div>账号：<input name="account" required></div>
        <div style="margin:10px 0;">密码：<input name="password" type="password" required></div>
        <div>昵称：<input name="nickname" required></div>
        <div style="margin-top:10px;">
            <button type="submit">注册</button>
        </div>
    </form>
    <div style="margin-top:20px;">
        已有账号？<a href="login.jsp">去登录</a>
    </div>
</div>
</body>
</html>