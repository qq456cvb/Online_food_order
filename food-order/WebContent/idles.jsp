<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Restaurant" %>
<%@ page import="model.Idle" %>
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


    <!-- Custom styles for this template -->
	<link href="./resources/css/style.css" rel="stylesheet">
	
	<script src="//unslider.com/unslider.js"></script>

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
    </style>
	
	<script src="./resources/js/AjaxRequest.js"></script>
	<script>
	function showImg(url){
		document.getElementById("cm_add").action="cm_add.action?fname=" + url;
		document.getElementById("cmimg").innerHTML= "<img src=\"upload/" + url + "\" onerror='this.src='resources/images/error.jpg'' height='100' width='100' onload='ResizeImage(this, 100, 100)'/>";
	}
	function check() {
		if (document.getElementById("descript").innerHTML == "" || document.getElementById("content").value == "") {
			alert("尚未完成评分！");
			return false;
		}
	}
	function showmore(more) {
		var newDiv=document.createElement("div");
//		newDiv.className = "row";
		newDiv.innerHTML = more;
		document.getElementById("more").appendChild(newDiv);
	}
	
	function changepage(currpages) {
		document.getElementById("moreform").action="moreidle.jsp?currpages=" + currpages;
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
 			if (username != null) {
	 			String auth = (String) session.getAttribute("auth");
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
    if (username == null) {
    	out.println("<div class='alert alert-warning' role='alert'>");
    	out.println("<strong>Warning!</strong> 你尚未登录，<a href='login.jsp'>登录/注册</a></div>");
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
    <h4>您当前的位置:	<a href="index.jsp">首页 </a>><a href="jumpRest.jsp">餐厅详情 </a>>我的预订</h4>
    <hr>
    <div class="section" align="center"><h1>欢迎光临<font color="red"><strong><%=session.getAttribute("restname") %></strong></font></h1></div>
	<hr>
	<div class="page-header">
        <h1>预订信息</h1>
      </div>

      
      <%
      List idleList = (List) session.getAttribute("idleList");
      for (int i = 0; i < idleList.size(); i++) {%>
    	  <div class="row" align="left">
    	  	<div class="col-md-4"><div class="row"><strong>预订ID号：</strong><%=((Idle)idleList.get(i)).getIdleId() %></div>
    	  	<div class="row"><strong>预订时间：</strong><%=((Idle)idleList.get(i)).getIdleTime() %></div>
    	  	</div>
    	  	<div class="col-md-4"><strong>预订状态：</strong><font size="6" color="red"><%if(((Idle)idleList.get(i)).getStatus() == 0) {out.println("待处理");}
    	  	else if(((Idle)idleList.get(i)).getStatus() == 1) {out.println("已完成");}
    	  	else if(((Idle)idleList.get(i)).getStatus() == 2) {out.println("商家已接受");}
    	  	else if(((Idle)idleList.get(i)).getStatus() == 3) {out.println("被拒绝");}%></font></div>
    	  
    	  <div class="col-md-4" align="right">
    	  <%if(((Idle)idleList.get(i)).getStatus() == 0 || ((Idle)idleList.get(i)).getStatus() == 2) {%>
    	  	<button type="button" class="btn btn-primary" onclick="window.location.href='orderFood.action?iid=<%=((Idle)idleList.get(i)).getIdleId()%>'">提前点餐</button>
    	  <%} if(((Idle)idleList.get(i)).getStatus() == 1) { %><button class='btn btn-danger' data-toggle='modal' data-target='#comment'>发表评论</button><%} %>
    	  </div>
    	  </div>
      		<hr>
      <%} %>
      <div id="more"></div>
      <form id="moreform" name="form2" method="post" action="moreidle.jsp" target="hidden_frame">  
         		<br>
		<button type="submit" class="btn btn-primary btn-lg">
	  		显示更多
	  	</button>
	  </form>
      
     
</div>
    
    <div class="modal fade" id="comment" aria-hidden="true">
		<div class="modal-dialog">
		    <div class="modal-content">
	     	
	            <div class="modal-header">
	            <button type="button" class="close" data-dismiss="modal">×</button>
	            <h3>发表评论</h3>
	            </div>
	            <div class="row">
		            <div class="col-md-6" align="center"><h3>上传图片（暂时支持一张）</h3></div>
		            <div class="col-md-6">
		            	<form name="form1" method="post" action="sendimg.jsp" target="hidden_frame" enctype="multipart/form-data">  
		            		<br>
							<input type="file" name="myFile" /><br>
							<input type="submit" value="确定提交" name="submit" /> <br>
						</form>
		            </div>
		            
				</div>
	            <form id="cm_add" action="cm_add" method="post" onSubmit="return check()">
	            <div class="modal-body">
	            	
		            <div class="row">
		            	<div id="xzw_starSys">
							<div id="xzw_starBox">
								<ul class="star" id="star">
									<li><a href="javascript:void(0)" title="1" class="one-star" onclick="Set(this)">1</a></li>
									<li><a href="javascript:void(0)" title="2" class="two-stars" onclick="Set(this)">2</a></li>
									<li><a href="javascript:void(0)" title="3" class="three-stars" onclick="Set(this)">3</a></li>
									<li><a href="javascript:void(0)" title="4" class="four-stars" onclick="Set(this)">4</a></li>
									<li><a href="javascript:void(0)" title="5" class="five-stars" onclick="Set(this)">5</a></li>
									
								</ul>
								<div class="current-rating" id="showb"></div>
							</div>
							<!--评价文字-->
							<div id="descript" class="description"></div>
						</div>
					</div>
					<input id="pts" type="hidden" name="pts" value=""/>
					<div class="row">
						<div class="col-md-2" align="right"><h4>内容：</h4></div>
						<div class="col-md-8"><textarea id="content" name="content" class="form-control" check-type="required" rows="4" onKeyDown="textdown(event)" onKeyUp="textup()" placeholder="说些什么吧。。。"></textarea></div>
						<div class="col-md-2"></div>
					</div>
					<br>
	            	<div id="cmimg" class="row" align="center"></div>
	            </div>
	            <div class="modal-footer">
	            	<button id="submit" type="submit" class="btn btn-primary" >确定</button>
	            	
	            </div>
	            </form>
	            
	             
	           
	    </div>
	    </div>
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
    <script src="./resources/bootstrap-3.2.0-dist/js/bootstrap.min.js"></script>
	<script src="./resources/js/docs.min.js"></script>
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

		$(document).ready(function() {
//			alert("${stat}");
		       });
			//  Find any element starting with a # in the URL
			//  And listen to any click events it fires
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
		
		
			
		function leave() {
			window.open("leaveIdle.jsp"); 
		}
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
		  
		  function Set(obj) {
			  document.getElementById("pts").value = obj.innerHTML;
		  }
			
		  function textdown(e)   
		  {  
		  	textevent = e ;   
		  	if(textevent.keyCode == 8)   
		  	{   
		  		return;   
		  	}   
		  	if(document.getElementById('text').value.length >= 140)    
		  	{   
		  		alert("最多输入30个字")    
		  		if(!document.all)   
		  		{   
		  			textevent.preventDefault();   
		  		}   
		  		else   
		  		{   
		  			textevent.returnValue = false;   
		  		}   
		  	}   
		  }   
		  function textup()   
		  {   
		  	var s = document.getElementById('feedback').value;   
		  //判断ID为text的文本区域字数是否超过1000个   
		  	if(s.length > 140)    
		  	{   
		  	document.getElementById('feedback').value=s.substring(0,140);   
		  	}   
		  }
	</script>
    </body>
</html>