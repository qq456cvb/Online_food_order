<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page import="java.util.List" %>
<%@ page import="model.*" %>
<%
	Long rid = (Long) session.getAttribute("rid");
	response.sendRedirect("enterRest.action?rid="+Long.toString(rid));
%>