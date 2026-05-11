<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>个人资料设置</title>
    <style>
        .container{width:450px;margin:50px auto;}
        .line{margin:15px 0;}
        label{display:inline-block;width:100px;}
        input,select{width:220px;padding:6px;}
        button{padding:8px 20px;background:#0099ff;color:#fff;border:none;border-radius:4px;}
    </style>
</head>
<body>
<div class="container">
    <h3>个人资料设置</h3>
    <form action="updateUser" method="post">
        <div class="line">
            <label>头像链接：</label>
            <input type="text" name="avatar" value="${loginUser.avatar}">
        </div>
        <div class="line">
            <label>昵称：</label>
            <input type="text" name="nickname" value="${loginUser.nickname}">
        </div>
        <div class="line">
            <label>个性签名：</label>
            <input type="text" name="signature" value="${loginUser.signature}">
        </div>
        <div class="line">
            <label>性别：</label>
            <select name="gender">
                <option value="0" ${loginUser.gender==0?"selected":""}>保密</option>
                <option value="1" ${loginUser.gender==1?"selected":""}>男</option>
                <option value="2" ${loginUser.gender==2?"selected":""}>女</option>
            </select>
        </div>
        <div class="line">
            <label>生日：</label>
            <input type="date" name="birthday" value="${loginUser.birthday}">
        </div>
        <div class="line">
            <button type="submit">保存修改</button>
        </div>
    </form>
</div>
</body>
</html>