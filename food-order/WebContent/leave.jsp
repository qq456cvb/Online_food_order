<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ page import="java.util.List"%>
<%@ page import="model.Customer"%>
<%
	List allusers =(List)application.getAttribute("allusers");
	for(int i=0;i<allusers.size();i++){
		Customer nowuser = (Customer) allusers.get(i);
		if(nowuser.getUserName().equals(session.getAttribute("username"))){
			allusers.remove(i);
			application.setAttribute("allusers", allusers);
		}
		if(allusers.size()==0){
				application.removeAttribute("message");
		}
	}
	response.sendRedirect("index.jsp");
%>