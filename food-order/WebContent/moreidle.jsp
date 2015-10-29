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
	String currpages = (String) request.getParameter("currpages");
	int pages = (Integer) session.getAttribute("pages");
	if (currpages == null) {
		currpages = "0";
	}
	if (Integer.parseInt(currpages) <= pages) {
		currpages = Integer.toString(Integer.parseInt(currpages)+1);
	}
//	request.setAttribute("currpages", currpages);
	out.print("<script>parent.changepage('" + currpages + "');</script>");
	
	Long cid = (Long) session.getAttribute("cid");
	Long rid = (Long) session.getAttribute("rid");
	List moreList = new ArrayList();
	String more = "";
	if (Integer.parseInt(currpages) <= pages) {
		moreList = waresService.getCusRestIdlesLimit(rid, cid, Integer.parseInt(currpages) * 5, 5);
		for (int i = 0; i < moreList.size(); i++) {
			more = more + "<div class=\"row\">";
			more = more + "<div class=\"col-md-4\"><div class=\"row\"><strong>预订ID号：</strong>" + ((Idle)moreList.get(i)).getIdleId() + "</div>";
			more = more + "<div class=\"row\"><strong>预订时间：</strong>" + ((Idle)moreList.get(i)).getIdleTime() + "</div></div>";
			more = more + "<div class=\"col-md-4\"><strong>预订状态：</strong><font size=\"6\" color=\"red\">";
			if(((Idle)moreList.get(i)).getStatus() == 0) {more = more + ("待处理");}
    	  	else if(((Idle)moreList.get(i)).getStatus() == 1) {more = more + ("已完成");}
    	  	else if(((Idle)moreList.get(i)).getStatus() == 2) {more = more + ("商家已接受");}
    	  	else if(((Idle)moreList.get(i)).getStatus() == 3) {more = more + ("被拒绝");}
			more = more + "</font></div><div class=\"col-md-4\" align=\"right\">";
	    	if(((Idle)moreList.get(i)).getStatus() == 0 || ((Idle)moreList.get(i)).getStatus() == 2) {
	    	  	more = more + "<button type=\"button\" class=\"btn btn-primary\" onclick=\"window.location.href=\"orderFood.action?iid=" +((Idle)moreList.get(i)).getIdleId()+ "\"\">提前点餐</button>";
			} if(((Idle)moreList.get(i)).getStatus() == 1) { 
				more = more + "<button class=\"btn btn-danger\" data-toggle=\"modal\" data-target=\"#comment\">发表评论</button>";
			} 
			more = more + "</div></div><hr>";
	    	  
//			more = more +"more";
		}
		out.print("<script>parent.showmore('" + more + "')</script>"); 
	} else {
		
	}

%>