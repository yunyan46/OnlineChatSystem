<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h3>搜索结果</h3>
<%
  java.util.List<com.im.pojo.User> list = (java.util.List<com.im.pojo.User>) request.getAttribute("list");
  for(com.im.pojo.User u : list){
%>
<p><%=u.getNickname()%> (<%=u.getAccount()%>)
  <a href="sendRequest?to=<%=u.getId()%>">发送申请</a></p>
<% } %>
<a href="mainInit">返回主页</a>
</body>
</html>