<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
</head>
<body>
<h3>即时通讯-注册</h3>
<p style="color:red">${msg}</p>
<form action="user?method=register" method="post">
    账号：<input type="text" name="account" placeholder="6-20位字母数字下划线"><br><br>
    密码：<input type="password" name="password" placeholder="字母+数字 6-20位"><br><br>
    昵称：<input type="text" name="nickname" placeholder="2-15位"><br><br>
    <button type="submit">注册</button>
</form>
</body>
</html>