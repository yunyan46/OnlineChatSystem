<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
<h3>即时通讯-登录</h3>
<p style="color:red">${msg}</p>
<form action="user?method=login" method="post">
    账号：<input type="text" name="account"><br><br>
    密码：<input type="password" name="password"><br><br>
    <button type="submit">登录</button>
</form>
</body>
</html>