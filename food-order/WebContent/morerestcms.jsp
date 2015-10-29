<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page import="java.util.*" %>
<%@ page import="model.*" %>
<%@ page import="service.*" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%
	WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
	ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());
	DataService dataService = (DataService) ctx.getBean("dataService");
	String currpages = (String) request.getParameter("currpages");
	int restcmpages = (Integer) session.getAttribute("restcmpages");
	if (currpages == null) {
		currpages = "0";
	}
	if (Integer.parseInt(currpages) <= restcmpages) {
		currpages = Integer.toString(Integer.parseInt(currpages)+1);
	}
	
	out.print("<script>parent.changepage('" + currpages + "');</script>");
	
	Long cid = (Long) session.getAttribute("cid");
	Long rid = (Long) session.getAttribute("rid");
	List moreList = new ArrayList();
	String more = "";
	if (Integer.parseInt(currpages) <= restcmpages) {
		moreList = dataService.getCmByRidLimit(rid, Integer.parseInt(currpages) * 5, 5);
		for (int i = 0; i < moreList.size(); i++) {
			Long pts = ((Comment)moreList.get(i)).getPts();
			more = more + "<div class=\"row\">";
			more = more + "<div class=\"col-md-3\" align=\"center\"><div class=\"row\"><img src=\"cusLogos/" + ((Comment)moreList.get(i)).getCus().getCusimg() + "\" onerror=\"this.src=" + "resources/images/error.jpg" + "\" height=\"120\" width=\"120\" onload=\"ResizeImage(this, 120, 120)\"/></div>";
			more = more + "<div class=\"row\"><div class=\"cmsstar\">";
			if (pts == 1) { more = more + "<div class=\"onestar\"></div>";}
			else if (pts == 2) {more = more + "<div class=\"onestar\"></div>";} 
			else if (pts == 3) {more = more + "<div class=\"onestar\"></div>";} 
			else if (pts == 4) {more = more + "<div class=\"onestar\"></div>";} 
			else if (pts == 5) {more = more + "<div class=\"onestar\"></div>";} 
			more = more + "</div></div>";
			more = more + "<div class=\"row\"><font color=\"red\">" + ((Comment)moreList.get(i)).getCus().getUserName() + "</font>给了<font color=\"red\">" + pts + "</font>星</div></div>";
			more = more + "<div id=\"dishinfo\" class=\"col-md-9\"><p><strong>评论详情：</strong><font size=\"3\">" + ((Comment)moreList.get(i)).getContent() + "</font></p>";
			if (((Comment)moreList.get(i)).getCmimg() != null) { 
				more = more + "<p><img src=\"upload/" + ((Comment)moreList.get(i)).getCmimg() +"\"  height=\"120\" width=\"120\" onload=\"ResizeImage(this, 120, 120)\" onclick=\"upload/" + ((Comment)moreList.get(i)).getCmimg() + "\"/></p>";
			} 
			more = more + "<h6 style=\"color:blue;font-style:italic;\">发表于 " + ((Comment)moreList.get(i)).getTime() + "</h6></div></div><br>";
		}
		out.print("<script>parent.showmore('" + more + "')</script>"); 
	} else {
		
	}

%>