<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>仿QQ主界面</title>
    <style>
        *{margin:0;padding:0;box-sizing:border-box;font-family: "微软雅黑";}
        body{width:100vw;height:100vh;display:flex;background:#fafafa;}
        .left{width:300px;border-right:1px solid #ddd;background:white;}
        .user-info{padding:20px;display:flex;align-items:center;background:#f5f6f7;cursor:pointer;}
        .avatar{width:50px;height:50px;border-radius:50%;margin-right:12px;}
        .name{font-size:16px;font-weight:bold;margin-bottom:4px;}
        .sign{font-size:12px;color:#666;}
        .item{padding:12px 20px;border-bottom:1px solid #eee;font-size:14px;cursor:pointer;}
        .item:hover{background:#f0f7ff;}
        .right{flex:1;display:flex;align-items:center;justify-content:center;color:#999;}
        .search{padding:10px;}
        input{width:100%;padding:8px;}
        button{padding:6px 10px;background:#0099ff;color:white;border:none;margin-top:5px;width:100%;}
        .logout{margin:15px 20px;text-align:center;}
        .logout a{color:red;text-decoration:none;}
    </style>
</head>
<body>

<div class="left">
    <div class="user-info" onclick="location.href='userInfo.jsp'">
        <img src="${loginUser.avatar}" class="avatar">
        <div>
            <div class="name">${loginUser.nickname}</div>
            <div class="sign">${loginUser.signature}</div>
        </div>
    </div>

    <!-- 退出登录按钮 -->
    <div class="logout">
        <a href="logout">🚪 退出登录</a>
    </div>

    <div class="search">
        <form action="searchUser" method="post">
            <input name="key" placeholder="输入账号/昵称搜索加好友" required>
            <button>搜索加好友</button>
        </form>
    </div>

    <div class="item" onclick="location.href='friendRequest.jsp'">🔔 好友申请</div>
    <div class="item">📋 好友列表</div>

    <%
        com.im.dao.FriendDao dao = new com.im.dao.FriendDao();
        java.util.List<com.im.pojo.User> friends = dao.getMyFriends(((com.im.pojo.User)session.getAttribute("loginUser")).getId());
        for(com.im.pojo.User f : friends){
    %>
    <div class="item">
        <%=f.getNickname()%> - <%=f.getSignature()%>
    </div>
    <% } %>
</div>

<div class="right">选择好友开始聊天</div>

</body>
</html>