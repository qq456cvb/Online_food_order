<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page import="java.util.*" %>
<%@ page import="model.*" %>
<%@ page import="service.*" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%
	WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
	ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());
	WaresService waresService = (WaresService) ctx.getBean("waresService");
	Long rid = (Long.parseLong(request.getParameter("rid")));
	Long cid = (Long) session.getAttribute("cid");
	session.setAttribute("rid", rid);
	session.setAttribute("restname", waresService.getRestById(rid).getRestName());
	session.setAttribute("nowrest", waresService.getRestById(rid));
	List<Dish> dishes = waresService.getMenuDish(rid);
	session.setAttribute("menudishes", dishes);
	int idlesize = waresService.getCusRestIdlesSize(rid, cid);
	int pages = idlesize / 5;
	List idleList = new ArrayList();
	if (pages == 0) {
		idleList = waresService.getCusRestIdlesLimit(rid, cid, 0, idlesize);
	} else {
		idleList = waresService.getCusRestIdlesLimit(rid, cid, 0, 5);
	}
	session.setAttribute("idleList", idleList);
	session.setAttribute("pages", pages);
	response.sendRedirect("idles.jsp");
%>