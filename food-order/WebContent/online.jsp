<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.util.*" %>
<%@ page import="model.Customer"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%List allusers = (List) application.getAttribute("allusers");
List usernames = new ArrayList();
%>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr><td height="32" align="center" class="word_orange ">欢迎来到聊天室！</td></tr>
  <tr>
  <td height="23" align="center">所有人</td>
  </tr>  
 <%	for(int i=0;i<allusers.size();i++){
		Customer user =(Customer)allusers.get(i);
		if (usernames.contains(user.getUserName())) {
			continue;
		}
		usernames.add(user.getUserName());
		%>
  <tr>
    <td height="23" align="center"><a href="#" onclick="set('<%=user.getUserName()%>')"><%=user.getUserName()%></a></td>
  </tr>
<%}%>
<tr><td height="30" align="center">当前在线[<font color="#FF6600"><%=usernames.size()%></font>]人</td></tr>
</table>