<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.util.*" errorPage="" %>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ page import="model.Customer" %>
<%
String message=request.getParameter("message");

if(message!=null){
	message = message.replaceAll("<", "&lt");
	message = message.replaceAll(">", "&gt");
	if(application.getAttribute("message")==null){	//第一个人说话时
		application.setAttribute("message","<br>"+"<font color='blue'>"+
		"<strong>"+session.getAttribute("username")+"</strong>"+"</font>" + "说：" + message);
	}else{
		application.setAttribute("message","<br>"+"<font color='blue'>"+"<strong>"+session.getAttribute("username")+"</strong>"+"</font>" + "说：" + message + application.getAttribute("message"));
	}
	out.println(application.getAttribute("message")+"<br>");
}else{
	if(application.getAttribute("message")==null){
		out.println("<font color='#cc0000'>"+((Customer)application.getAttribute("latestuser")).getUserName()+"</font>"+"<font color='green'>"+"走进了网络聊天室"+"</font>");
		out.println("<br>"+"<center>"+"<font color='#aa0000''>"+"请各位聊友注意聊天室的规则,不要在本聊天室内发表反动言论及对他人进行人身攻击，不要随意刷屏。"+"</font>"+"</center>");
	}else{
	out.println(application.getAttribute("message")+"<br>");
	}
}
%>