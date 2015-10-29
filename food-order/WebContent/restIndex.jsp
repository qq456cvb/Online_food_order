<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="model.*" %>
<%@ page import="service.*" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Foodstore</title>

    <!-- Bootstrap core CSS -->
    <link href="./resources/bootstrap-3.2.0-dist/css/bootstrap.css" rel="stylesheet">
    <link href="./resources/bootstrap-3.2.0-dist/css/bootstrap-theme.css" rel="stylesheet">


    <!-- Custom styles for this template -->
	<link href="./resources/css/style.css" rel="stylesheet">
	
	<script src="//unslider.com/unslider.js"></script>
	<script src="./resources/js/Chart.js"></script>

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
        body{
        	margin:0px;
  			padding-top: 70px;
  			padding-bottom: 30px; 
        	background-attachment:fixed;
        	background-color:#777;
        	background-size:cover;
        	background-repeat:no-repeat;
        	font-family:Helvetica Neue,Microsoft Yahei,Hiragino Sans GB,Microsoft Sans Serif,WenQuanYi Micro Hei,sans-serif;
        	color:rgb(0, 0, 0);
        	}
        body.default-bg{background-image:url(./resources/images/bg.jpg)}
        #searchForm {	margin: 12px 0;	background: #BBBBBB;	border-radius: 10px;}
        #search-text {	width: auto;	padding: 0 12px;	height: 26px;	background: #BBBBBB;	vertical-align: middle;	border-radius: 10px;	border: 0;}
        input#search-text:focus {	border-radius: 10px;}
    	.banner { position: relative; overflow: auto; }
    	.banner li { list-style: none; }
    	.banner ul li { float: left; }
    	#dishinfo { font-size: 20px; }
    </style>
    
    <script>
    function showmore(more) {
		var newDiv=document.createElement("div");
//		newDiv.className = "row";
		newDiv.innerHTML = more;
		document.getElementById("more").appendChild(newDiv);
	}
	
	function changepage(currpages) {
		document.getElementById("moreform").action="morerestcms.jsp?currpages=" + currpages;
	}
    </script>

  </head>
  
  <body class="default-bg" role="document">
	<div style="position: fixed; top: 800px; right: 10px;"><a href="#top" style="color: #ff0000;"><font size="5">返回顶部</font></a></div>
    <a id="top"></a>
    <!-- Fixed navbar -->
    <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Bootstrap theme</a>
        </div>
        <div>
          <ul class="nav navbar-nav navbar-left">
            <li class="active"><a href="index.jsp">主页</a></li>
            <li><a href="enterForum.action">聊天室</a></li>
            <li><a href="feedback.jsp">建议反馈</a></li>
            <li class="dropdown">
              <a href="" class="dropdown-toggle" data-toggle="dropdown">Dropdown <span class="caret"></span></a>
              <ul class="dropdown-menu" role="menu">
                <li><a href="#">Action</a></li>
                <li><a href="#">Another action</a></li>
                <li><a href="#">Something else here</a></li>
                <li class="divider"></li>
                <li class="dropdown-header">Nav header</li>
                <li><a href="#">Separated link</a></li>
                <li><a href="#">One more separated link</a></li>
              </ul>
            </li>
          </ul>
        </div><!--/.nav-collapse -->
        
 		<form action="alterRest" method="post" class="navbar-form navbar-left" role="search" id="searchForm">
 				
 		<div class="form-group">
 			<button id="searchbtn" type="submit" class="btn btn-link"><span class="glyphicon glyphicon-search"></span></button>
 			<input type="text" class="form-control" name="type" value="search" style="display:none;">
 			<input id="search-text" type="text" class="form-control" placeholder=Search name="rname">
 		</div></form>
 		<div>
 		<%
 			String username = (String) session.getAttribute("username");
 			String auth = (String) session.getAttribute("auth");
 			if (username != null) {
	 			
	 			out.println("<ul class='nav navbar-nav navbar-right'>");
	 			out.println("<li class='dropdown'>");
	 			out.println("<a href='' class='dropdown-toggle' data-toggle='dropdown'>");
	 			out.println(username);
	 			out.println("<b class='caret'></b></a>");
	 			out.println("<ul class='dropdown-menu'>");
	 			out.println("<li><a href=");
	 			if (auth.equals("customer")) {
	 				out.println("'cusIndex.jsp'>");
	 			} else if (auth.equals("saler")){
	 				out.println("'salerIndex.jsp'>");
	 			} else if (auth.equals("manager")) {
	 				out.println("'mgrIndex.jsp'>");
	 			}
	 			out.println("个人中心</a></li>");
	 			out.println("<li class='divider'></li>");
	 			out.println("<li><a href='logout.action'>登出</a></li></ul></li></ul>");
 			}
 		%>
 		</div>
      </div>
    </div>
    <%
    DecimalFormat dcmFmt = new DecimalFormat("0.0");
    if (username == null) {
    	out.println("<div class='alert alert-warning' role='alert'>");
    	out.println("<strong>Warning!</strong> 你尚未登录，<a href='login.jsp'>登录/注册</a></div>");
    } else if (auth.equals("customer")){
    	Integer vip = (Integer) session.getAttribute("vip");
    	if (vip == null || vip == 0) {
    		out.println("<div class='alert alert-warning' role='alert'>");
        	out.println("<strong>Warning!</strong> 你还不是本店会员，<a href='vip.jsp'>申请成为会员</a></div>");
    	} else {
    		out.println("<div class='alert alert-success' role='alert'>");
        	out.println("<strong>Warning!</strong> 您是本店会员，可享受美食优惠</div>");
    	}
    }
    %>
    <div class="banner">
			<ul>
				<li style="background-image: url('resources/images/bg1.jpg');">
					<div class="inner">
						<h1>这里有你想要的</h1>
						<p>加入进来，享受美妙的美食体验。</p>
						
						<a class="btn" href="feedback.jsp">用户反馈</a>

					</div>
				</li>

				<li style="background-image: url('resources/images/bg2.jpg');">
					<div class="inner">
						<h1>美味、可口、安全</h1>
						<p>各种各样的美食在等着你</p>
						
						<a class="btn" href="feedback.jsp">用户反馈</a>
					</div>
				</li>

				<li style="background-image: url('resources/images/bg3.jpg');">
					<div class="inner">
						<h1>优惠活动</h1>
						<p>现在注册，新用户将有机会获得抵用券</p>

						<a class="btn" href="login.jsp">现在注册</a>
					</div>
				</li>

			</ul>
		</div>
    
    <div class="container" role="main">
    <br>
    <h4>您当前的位置:	<a href="index.jsp">首页 </a>	>	餐厅详情</h4>
    
    <div class="section">
    	<s:iterator value="imageFileName" status="length">                                                       
                                                                                                               
	        <div style="padding: 3px; border: solid 1px #cccccc; text-align: center">                                 
	            <img src='restLogos/<s:property value ="imageFileName" /> ' />                                    
	            <br />                                                                                               
	            <s:property value="caption" />                                                                       
	        </div>                                                                                                 
        </s:iterator>
    </div>

	
	<div id="nowrest" class="section">
	<div class="page-header">
      <h1>餐厅信息</h1>
    </div>
	<div class="row" align="center">
		<div class="col-md-6">
			<img src="restLogos/<%=((Restaurant)session.getAttribute("nowrest")).getRestimg()%>" onerror="this.src='resources/images/error.jpg'" height='220' width='220' onload='ResizeImage(this, 220, 220)'/>
			<br><br>
			<h3>商家评分:<font color="red"><%=dcmFmt.format(session.getAttribute("restpt")) %></font></h3>
			<h3>共<font color="red"><%=session.getAttribute("restcmssize") %></font>人评价</h3>
			<s:form id="restimg" cssClass="form-inline" action="fileUpload" method="post" enctype ="multipart/form-data" theme="simple">  
  				<div class="col-md-6">
                <s:fielderror />  
                	<s:file name ="myFile" label ="Image File1"/>
                </div>  
                <div class="col-md-6">
					<s:submit cssClass="btn btn-primary"/>  
					<s:hidden name="type" value="rest"></s:hidden>
				</div>
  
           </s:form>  
		</div>
		<div class="col-md-6">
			<form id="restform" action="alterRest" method="post" class="form-horizontal" role="form">
				<div class="form-group">
					<label class="col-md-3 control-label" for="mail">
						    餐厅名
					</label>
					<div class="col-md-5">
					<input id="restname" type="text" name="restBean.restName" check-type="required" class="form-control" maxlength="10" placeholder="请输入餐厅名" readonly>	
					</div>		
				</div>
				<hr>
				<div class="form-group">
					<label class="col-md-3 control-label" for="restplace">
						    地址
					</label>
					<div class="col-md-5">
					<input id="restplace" type="text" name="restBean.place" check-type="required" class="form-control" maxlength="20" placeholder="请输入地址" readonly>	
					</div>		
				</div>
				<hr>
				<div class="form-group">
					<label class="col-md-3 control-label" for="contact">
						    联系方式
					</label>
					<div class="col-md-5">
					<input id="contact" type="text" name="restBean.contact" check-type="required number" class="form-control" maxlength="20" placeholder="请输入联系方式" readonly>	
					</div>	
				</div>	
				<hr>
				<div class="input-group">
					<span class="input-group-addon">说明</span>
					<textarea id="info" name="restBean.info" class="form-control" rows="6" readonly></textarea>	
					<input type="text" class="form-control" name="type" value="update" style="display:none;">	
				</div>
				<hr>
				<div class="row">
					<div class="col-md-2">
					</div>
					<div class="col-md-10">
						<button id="managerVip" type="button" class="btn btn-primary" onclick="window.location.href='mgrVip.jsp'">会员管理</button>
						<button id="managerRes" type="button" class="btn btn-primary" onclick="window.location.href='mgrRes.jsp'">预订管理</button>
						<button id="editMenu" type="button" class="btn btn-primary" onclick="window.location.href='mgrMenu.jsp'">菜单管理</button>
						<button id="reserve" type="button" class="btn btn-primary" onclick="window.location.href='reserve.jsp'">预订</button>
						<button id="edit" type="button" class="btn btn-danger" onclick="editrest()">编辑</button>
						<button id="submit" type="submit" class="btn btn-primary">保存</button>
						<script>
							var owned = '<%=request.getAttribute("owned")%>';
						    if (owned == 'null') {
						    	document.getElementById("edit").style.display = "none";
						    	document.getElementById("restimg").style.display = "none";
						    	document.getElementById("submit").style.display = "none";
						    	document.getElementById("managerRes").style.display = "none";
						    	document.getElementById("managerVip").style.display = "none";
						    	document.getElementById("editMenu").style.display = "none";
						    }
						    var auth = '<%=session.getAttribute("auth")%>';
						    if (auth == 'null') {
						    	document.getElementById("reserve").style.display = "none";
						    } else if (auth != "customer"){
						    	document.getElementById("reserve").style.display = "none";
						    }
							
						</script>
					</div>
				</div>
			</form>
		</div>
	</div>
	</div>
	<%
		List<Dish> menudishes = (List) session.getAttribute("menudishes");	
		List<Comment> restcms = (List) session.getAttribute("restcms");
	%>
	<div>
		<div class="bs-example bs-example-tabs">
    			<ul id="myTab" class="nav nav-tabs" role="tablist">
      				<li class="active"><a href="#menu" role="tab" data-toggle="tab">详细菜单</a></li>
      				<li class=""><a href="#comment" role="tab" data-toggle="tab">查看评论</a></li>
    			</ul>
    			<div id="myTabContent" class="tab-content">
    				<div class="tab-pane fade active in" id="menu">
    					<%for(int i = 0; i < menudishes.size(); i++) { %>
    					<br>
						<div class="row">
							<div class="col-md-4">
							<img src="restdishes/<%=menudishes.get(i).getDishimg() %>" onerror="this.src='resources/images/error.jpg'" height='150' width='150' onload='ResizeImage(this, 150, 150)'/>
							</div>
							<div id="dishinfo" class="col-md-8"><strong>品名：</strong><%=menudishes.get(i).getDishName() %><br>
							<strong>价格：</strong><%=menudishes.get(i).getPrice() %><br>
							<strong>简介：</strong><%=menudishes.get(i).getInfo() %>
							</div>
						</div>
						<%} %>
      				</div>
      				<div class="tab-pane fade" id="comment">
    					<%for(int i = 0; i < restcms.size(); i++) { %>
    					<br>
						<div class="row">
							<div class="col-md-3" align="center">
								<div class="row"><img src="cusLogos/<%=restcms.get(i).getCus().getCusimg() %>" onerror="this.src='resources/images/error.jpg'" height='120' width='120' onload='ResizeImage(this, 120, 120)'/></div>
								<div class="row"><div class="cmsstar">
								<%Long pts = restcms.get(i).getPts();
								if (pts == 1) { %><div class="onestar"></div><%}
								else if (pts == 2) {%><div class="twostar"></div><%} 
								else if (pts == 3) {%><div class="threestar"></div><%} 
								else if (pts == 4) {%><div class="fourstar"></div><%} 
								else if (pts == 5) {%><div class="fivestar"></div><%} %>
								</div></div>
								<div class="row"><font color="red"><%=restcms.get(i).getCus().getUserName() %></font>给了<font color="red"><%=restcms.get(i).getPts() %></font>星</div>
							</div>
							<div id="dishinfo" class="col-md-9"><p><strong>评论详情：</strong><font size="3"><%=restcms.get(i).getContent() %></font></p>
							<%if (restcms.get(i).getCmimg() != null) { %><p><img src="upload/<%=restcms.get(i).getCmimg() %>"  height='120' width='120' onload='ResizeImage(this, 120, 120)' onclick="upload/<%=restcms.get(i).getCmimg() %>"/></p><%} %>
							<h6 style="color:blue;font-style:italic;">发表于 <%=restcms.get(i).getTime() %></h6></div>
						</div>
						<%} %>
						
						<div id="more"></div>
						<form id="moreform" name="form2" method="post" action="morerestcms.jsp" target="hidden_frame">  
					         		<br>
							<button type="submit" class="btn btn-primary btn-lg">
						  		显示更多
						  	</button>
						</form>
					</div>
						
      			</div>
      		</div>
    	</div>
    	
    	<% if (auth == null){} else if (auth.equals("manager") || auth.equals("saler")) {%>
    	<div class="section">
			<div class="page-header">
      		<h1>近一周的订单信息</h1>
    		</div>
		<div class="row">
			<canvas id="myChart" width="800" height="600"></canvas>
		</div>
		<%} %>
	</div>
		
      
    <iframe name='hidden_frame' id="hidden_frame" style="display:none"></iframe> 
      
	<a class="bshareDiv" href="http://www.bshare.cn/share">分享按钮</a><script type="text/javascript" charset="utf-8" src="http://static.bshare.cn/b/buttonLite.js#uuid=&amp;style=5&amp;fs=4&amp;bgcolor=LightBlue"></script>
              
	<footer class="footer">
      <p>本网站由尤洋编写，访问我的 <a href="http://qq456cvb.github.io" target="_blank">个人主页 </a>。</p>
      <p>Powered By <a href="http://www.bootcss.com/" title="Bootstrap中文网">Bootstrap</a> | <a href="http://less.bootcss.com/" title="Less中文文档">Less</a> | <a href="http://sass.bootcss.com/" title="Sass中文文档">Sass</a> | <a href="http://www.gruntjs.net/" title="Grunt中文网">Grunt</a> | <a href="http://www.ghostchina.com/" target="_blank" title="Ghost中文网">Ghost</a></p>
      <p class="hidden"><a href="http://koa.bootcss.com/" title="Koa中文文档">Koa</a> | <a href="http://jekyll.bootcss.com/" title="Jekyll中文文档">Jekyll</a></p>
      <p><a href="https://www.upyun.com" target="_blank">又拍云</a>为本站提供CDN加速服务</p>
      <p>
        <a href="http://www.miibeian.gov.cn/" target="_blank">京ICP备11008151号</a> | 京公网安备11010802014853
      </p>
    </footer>
    
    <!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>-->
    <script src="./resources/js/jquery-1.10.2.js"></script>
    <script src="./resources/bootstrap-3.2.0-dist/js/bootstrap.js"></script>
	<script src="./resources/js/docs.min.js"></script>
	<script src="./resources/js/bootstrap3-validation.js"></script>
	<script src="./resources/js/lrtk.js"></script>
	
	<script src="http://www.bootcss.com/p/unslider/jquery.event.move.js"></script>
	<script src="http://www.bootcss.com/p/unslider/jquery.event.swipe.js"></script>

	<script src="http://www.bootcss.com/p/unslider/unslider.min.js"></script>
	
	<script>
		if(window.chrome) {
			$('.banner li').css('background-size', '100% 100%');
		}

		$('.banner').unslider({
			arrows: true,
			fluid: true,
			dots: true
		});
		
		$("#restform").validation();
		
		$("#submit").on('click',function(event){
		      // 2.最后要调用 valid()方法。
		      //  valide(object,msg),提示信息显示，object位置后面增加提示信息。如不填object 则自动找最后一个button submit.
		      //  valide(msg)
		      if ($("#restform").valid(this,'格式错误')==false){
		        return false;
		      }
		    });
		$(document).ready(function() {
			document.getElementById("nowrest").scrollIntoView();
			document.getElementById("restname").value = "<%=((Restaurant)session.getAttribute("nowrest")).getRestName()%>";
			document.getElementById("restplace").value = "<%=((Restaurant)session.getAttribute("nowrest")).getPlace()%>";
			document.getElementById("contact").value = "<%=((Restaurant)session.getAttribute("nowrest")).getContact()%>";
			document.getElementById("info").value = "<%=((Restaurant)session.getAttribute("nowrest")).getInfo()%>";
		       });
			//  Find any element starting with a # in the URL
			//  And listen to any click events it fires
			
		function editrest() {
			document.getElementById("restname").removeAttribute("readonly");
			document.getElementById("restplace").removeAttribute("readonly");
			document.getElementById("contact").removeAttribute("readonly");
			document.getElementById("info").removeAttribute("readonly");
			
		}
		$('a[href^="#"]').click(function() {
				//  Find the target element
			var target = $($(this).attr('href'));

				//  And get its position
			var pos = target.offset(); // fallback to scrolling to top || {left: 0, top: 0};

				//  jQuery will return false if there's no element
				//  and your code will throw errors if it tries to do .offset().left;
			if(pos) {
					//  Scroll the page
				$('html, body').animate({
						scrollTop: pos.top,
						scrollLeft: pos.left
				}, 1000);
			}

				//  Don't let them visit the url, we'll scroll you there
			return false;
		});
		
		$('#myTab a').click(function (e) {
			  e.preventDefault();
			  $(this).tab('show');
			});
		

			
		  function ResizeImage(obj, MaxW, MaxH) {
			  if (obj != null) imageObject = obj;
			  var state=imageObject.readyState;
			  if(state!='complete') {
			  	setTimeout("ResizeImage(null,"+MaxW+","+MaxH+")",50);
			  	return;
			  }
			  var oldImage = new Image();
			  oldImage.src = imageObject.src;
			  var dW=oldImage.width;
			  var dH=oldImage.height;
			  if(dW>MaxW || dH>MaxH)
			  { a=dW/MaxW; b=dH/MaxH; if(b>a) a=b; dW=dW/a; dH=dH/a; }
			  if(dW > 0 && dH > 0)
			  { imageObject.width=dW; imageObject.height=dH; }
			}
		  
		  Date.prototype.format = function(fmt)
		  { //author: meizz
		    var o = {
		      "M+" : this.getMonth()+1,                 //月份
		      "d+" : this.getDate(),                    //日
		      "h+" : this.getHours(),                   //小时
		      "m+" : this.getMinutes(),                 //分
		      "s+" : this.getSeconds(),                 //秒
		      "q+" : Math.floor((this.getMonth()+3)/3), //季度
		      "S"  : this.getMilliseconds()             //毫秒
		    };
		    if(/(y+)/.test(fmt))
		      fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
		    for(var k in o)
		      if(new RegExp("("+ k +")").test(fmt))
		    fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
		    return fmt;
		  }
		  
		  function getDate(day){
			    var zdate=new Date();
			    var sdate=zdate.getTime()-(1*24*60*60*1000);
			    var edate=new Date(sdate-(day*24*60*60*1000)).format("yyyy-MM-dd");;
			    return edate;
			 
			}
		  
		  var opt = {
					scaleFontColor : "#000",
			}
		  <% WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
			ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());
			WaresService waresService = (WaresService) ctx.getBean("waresService");
			Long rid = (Long) session.getAttribute("rid");
			Calendar   a   =   Calendar.getInstance();
			Calendar   b   =   Calendar.getInstance();
			Calendar   c   =   Calendar.getInstance();
			Calendar   d   =   Calendar.getInstance();
			Calendar   e   =   Calendar.getInstance();
			Calendar   f   =   Calendar.getInstance();
			Calendar   g   =   Calendar.getInstance();
			a.add(Calendar.DAY_OF_MONTH, -6);
			b.add(Calendar.DAY_OF_MONTH, -5);
			c.add(Calendar.DAY_OF_MONTH, -4);
			d.add(Calendar.DAY_OF_MONTH, -3);
			e.add(Calendar.DAY_OF_MONTH, -2);
			f.add(Calendar.DAY_OF_MONTH, -1);
			g.add(Calendar.DAY_OF_MONTH, 0);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");     
			String at = formatter.format(a.getTime()); 
			String bt = formatter.format(b.getTime());  
			String ct = formatter.format(c.getTime());  
			String dt = formatter.format(d.getTime());  
			String et = formatter.format(e.getTime());  
			String ft = formatter.format(f.getTime());  
			String gt = formatter.format(g.getTime());  
			String str1 = at.substring(0, 10);
			String str2 = bt.substring(0, 10);
			String str3 = ct.substring(0, 10);
			String str4 = dt.substring(0, 10);
			String str5 = et.substring(0, 10);
			String str6 = ft.substring(0, 10);
			String str7 = gt.substring(0, 10);
			%>
		  var data = {
					labels : [getDate(5),getDate(4),getDate(3),getDate(2),getDate(1),getDate(0),getDate(-1)],
					datasets : [
						{
							fillColor : "rgba(151,187,205,0.5)",
							strokeColor : "rgba(151,187,205,1)",
							pointColor : "rgba(151,187,205,1)",
							pointStrokeColor : "#fff",
							data : [<%=waresService.getOrderByDate(rid, str1).size()%>,<%=waresService.getOrderByDate(rid, str2).size()%>,<%=waresService.getOrderByDate(rid, str3).size()%>,
							        <%=waresService.getOrderByDate(rid, str4).size()%>,<%=waresService.getOrderByDate(rid, str5).size()%>,<%=waresService.getOrderByDate(rid, str6).size()%>
							        ,<%=waresService.getOrderByDate(rid, str7).size()%>]
						}
					]
				}
			
			var ctx = document.getElementById("myChart").getContext("2d");
			var ds = new Chart(ctx);
			new Chart(ctx).Line(data, opt);
			
	</script>
    </body>
</html>