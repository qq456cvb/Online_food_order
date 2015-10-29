<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page import="java.util.*" %>
<%@ page import="model.*" %>
<%@ page import="service.*" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ include file="restIndex.jsp"%>
<%
	WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
	ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());
	WaresService waresService = (WaresService) ctx.getBean("waresService");
	Long cid = (Long) session.getAttribute("cid");
	Long rid = (Long) session.getAttribute("rid");
	waresService.reqVip(cid, rid);
	out.print("<script>alert('提交成功，请等待商家批准')</script>"); 
%>
