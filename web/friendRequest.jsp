<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.im.dao.FriendDao,com.im.pojo.User,com.im.pojo.FriendRequest" %>
<html>
<body>
<h3>好友申请</h3>
<%
  User me = (User)session.getAttribute("loginUser");
  FriendDao dao = new FriendDao();
  for(FriendRequest r : dao.getRequests(me.getId())){
%>
<p>申请人ID：<%=r.getFromUid()%>
  <a href="agree?id=<%=r.getId()%>&from=<%=r.getFromUid()%>&to=<%=me.getId()%>">同意</a></p>
<% } %>
<a href="mainInit">返回</a>
</body>
</html>