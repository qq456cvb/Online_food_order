<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="java.util.*" %>
<%@ page import="model.*" %>
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
    <link href="./resources/css/doc.min.css" rel="stylesheet">


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
    	.pinned { margin:70px; }
    	.pinned > li { font-size: 20px; }
    	.pinned > li > a { color: #363636; }
    </style>

  </head>
  
  <body class="default-bg" role="document">
	<div style="position: fixed; top: 800px; right: 10px;"><a href="#top" style="color: #ff0000;"><font size="5">返回顶部</font></a></div>
	<% int size = ((List)session.getAttribute("cusIdles")).size(); %>
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
    <div id= "top" class="banner">
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
    
    <div class="container">
    <br>
    <h4>您当前的位置:	<a href="index.jsp">首页 </a>	>	个人中心</h4>
    <div class="row">
    <div class="col-md-10" role="main">
    <div id="favor" class="section">
        
    <div class="row">
    	<p>您当前的账户余额为  元</p>
    </div>
    <div class="page-header">
    <h1>我的收藏</h1>
    </div>
        <div class="image-collection">
    	<%
    	List<Restaurant> favorList = (List<Restaurant>) session.getAttribute("favors");
    	if (favorList == null) {
   		
    	} else {
    		for (int i = 0;i < favorList.size(); i ++){
				out.println("<div>");
				out.println("<a href='enterRest.action?rid=");
				out.println(favorList.get(i).getId()+"'>");

				out.println("<img src='restLogos/");
				out.println(favorList.get(i).getRestimg() + "' onerror=\"this.src='resources/images/error.jpg'\" height='121' width='220' onload='ResizeImage(this, 121, 220)'/></a>");
				out.println("<div class='overlay'>");
				out.println("<a href='removeFavor.action?rid="+favorList.get(i).getId()+"'>取消收藏</a></div>");
				out.println("<div class='img_description'><a href='enterRest.action?rid=");
				out.println(favorList.get(i).getId()+"'>");
				out.println(favorList.get(i).getRestName()+"</a>");
				out.println("</div></div>");
    		}
    	}
    	%>
    </div>
    
    <div id="info" class="section">
	<div class="page-header">
      <h1>个人信息</h1>
    </div>
	<div class="row">
		<div class="col-md-6">
			<img src="cusLogos/<%=((Customer)session.getAttribute("cus")).getCusimg() %>" onerror="this.src='resources/images/error.jpg'" height='220' width='220' onload='ResizeImage(this, 220, 220)' />
			<hr>
			<s:form cssClass="form-inline" action="fileUpload" method="post" enctype ="multipart/form-data" theme="simple">  
  				<div class="col-md-6">
                  <s:fielderror />  
                  <s:file name ="myFile" label ="Image File1"/>
                  </div>  
                  <div class="col-md-6">
					<s:submit cssClass="btn btn-primary"/> 
					<s:hidden name="type" value="cus"></s:hidden> 
				</div>
  
           </s:form> 
		</div>
		<div class="col-md-6">
			<form id="form" action="editUser" method="post" class="form-horizontal" role="form">
				<div class="input-group">
					<span class="input-group-addon">姓名</span>
					<input id="name" type="text" name="name" class="form-control" check-type="required" minlength="2" maxlength="6" placeholder="请输入姓名" readonly>			
				</div>
				<hr>
				<div class="input-group">
					<span class="input-group-addon">年龄</span>
					<input id="age" type="text" name="age" class="form-control" check-type="number" maxlength="3" placeholder="请输入年龄" readonly>
				</div>
				<hr>
				<div class="input-group">
					<span class="input-group-addon">用户名</span>
					<input id="userName" type="text" name="username" check-type="required" minlength="6" maxlength="12" class="form-control" placeholder="请输入用户名" readonly>			
				</div>
				<hr>
				<div class="input-group">
					<span class="input-group-addon">密码</span>
					<input id="passWord" type="password" name="password" class="form-control" check-type="required" minlength="6" maxlength="12" placeholder="请输入密码" onpaste="return false" readonly>
				</div>
				<hr>
				<div class="input-group">
					<span class="input-group-addon">邮箱</span>
					<input id="email" type="text" name="email" class="form-control" check-type="mail" placeholder="请输入邮箱" readonly>			
				</div>
				<hr>
				<div class="row">
					<div class="col-md-6">
					</div>
					<div class="col-md-6">
						<button id="edit" type="button" class="btn btn-danger" onclick="edituser()">编辑</button>
						<button id="submit" type="submit" class="btn btn-primary">保存</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	</div>
	</div>

	<div class="section">
	<div id="order" class="page-header">
      <h1>我的订单（分类）</h1>
    </div>
    <div class="image-collection light">
    	<%
    	List<Idle> cusIdles = (List) session.getAttribute("cusIdles");
    	List<Restaurant> rests = new ArrayList();
    	Set<Restaurant> diffrests = new HashSet();
    	for (int i = 0; i < cusIdles.size(); i++) {
    		rests.add(cusIdles.get(i).getRest());
    	}
    	diffrests.addAll(rests);
    	Iterator i = diffrests.iterator();//先迭代出来  
        	while(i.hasNext()){
        		Restaurant rest = (Restaurant)i.next();
    	%>
    	<div><a href="enterRest.action?rid=<%=rest.getId()%>">
    	<img src="restLogos/<%=rest.getId() %>.jpg" 
    	onerror="this.src='resources/images/error.jpg'" height='121' width='220' onload='ResizeImage(this, 121, 220)'/></a>
    	<div class="overlay"><a href="addFavor.action?rid=<%=rest.getId() %>">添加收藏</a></div>
    	<div class="img_description"><a href="enterRest.action?rid=<%=rest.getId() %>"><%=rest.getRestName() %></a>
    	<br><h5>您有<a href="jumpIdle.jsp?rid=<%=rest.getId() %>">
    	<%=Collections.frequency(rests, rest) %></a>份订单</h5></div>
    	</div>
    	<%} %>
    </div>
    </div>
    

	<div class="row">
		<div class="col-md-5"></div>
		<div class="col-md-7"><a href="javascript:void(0)" onclick="left()"><span class="glyphicon glyphicon-chevron-left"></span></a>
		page <input type="text" style="width:40px;" name="pgnum" id="pgnum" value="1" onkeyup="changepg();"> / of <span><%=(size%10!=0)?(size/10+1):(size/10)%></span>
		<a href="javascript:void(0)" onclick="right()"><span class="glyphicon glyphicon-chevron-right"></span></a></div>
	</div>
     </div>
	</div>
	</div>
	
	

    <div class="col-md-2">
    	<div class="pin-wrapper" style="height: auto;">
    	<ul class="nav pinned" style="margin: 100px 0 0; width: auto;">
          <li><a href="#favor">我的收藏</a></li>
          <li><a href="#info">个人信息</a></li>
          <li><a href="#order">我的订单</a></li>
        </ul></div>
    </div>
    </div>
    </div>
    
      
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
	
    <script src="./resources/js/jquery-1.10.2.js"></script>
    <script src="./resources/bootstrap-3.2.0-dist/js/bootstrap.js"></script>
	<script src="./resources/js/docs.min.js"></script>
	<script src="./resources/js/jquery.pin.js"></script>
	<script src="./resources/js/bootstrap3-validation.js"></script>
	
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
		
		$("#form").validation();
		
		$("#submit").on('click',function(event){
		      // 2.最后要调用 valid()方法。
		      //  valide(object,msg),提示信息显示，object位置后面增加提示信息。如不填object 则自动找最后一个button submit.
		      //  valide(msg)
		      if ($("#form").valid(this,'格式错误')==false){
		        return false;
		      }
		    });
		
	    $(".pinned").pin({containerSelector: ".container", minWidth: 940});
	    
		$(document).ready(function() {
			var trs = document.getElementsByTagName("tr");
//			alert(trs.length);
			for(var i=11; i < trs.length; i++) {
				trs[i].style.display="none";
			}
			document.getElementById("pgnum").value="1";
//			document.getElementById("nowrest").scrollIntoView();
//			alert("aa");
			document.getElementById("name").value = "<%=((Customer)session.getAttribute("cus")).getName()%>";
			document.getElementById("age").value = "<%=Long.toString(((Customer)session.getAttribute("cus")).getAge())%>";
			document.getElementById("userName").value = "<%=((Customer)session.getAttribute("cus")).getUserName()%>";
			document.getElementById("passWord").value = "<%=((Customer)session.getAttribute("cus")).getPassWord()%>";
			document.getElementById("email").value = "<%=((Customer)session.getAttribute("cus")).getEmail()%>";
		       });
			//  Find any element starting with a # in the URL
			//  And listen to any click events it fires
			
		function left() {
			var trs = document.getElementsByTagName("tr");
			var leftnum = parseInt(document.getElementById("pgnum").value) - 1;
			if (leftnum <= 0) {
				return;
			} else {
				for(var i = 1; i < trs.length; i++) {
					trs[i].style.display="";
				}
				for(var i = 1; i < leftnum * 10 - 9; i++) {
					trs[i].style.display="none";					
				}
				for(var i = leftnum * 10 + 1; i < trs.length; i++) {
					trs[i].style.display="none";					
				}
				document.getElementById("pgnum").value = leftnum + "";
			}
		}
			
		function right() {
			var trs = document.getElementsByTagName("tr");
			
			var rightnum = parseInt(document.getElementById("pgnum").value) + 1;
			var totalpg = <%=(size%10!=0)?(size/10+1):(size/10)%>;
			if (rightnum > totalpg) {
				return;
			} else {
				for(var i = 1; i < trs.length; i++) {
					trs[i].style.display="";
				}
				for(var i = 1; i < rightnum * 10 - 9; i++) {
					trs[i].style.display="none";					
				}
				for(var i = right * 10 + 1; i < trs.length; i++) {
					trs[i].style.display="none";					
				}
				document.getElementById("pgnum").value = rightnum + "";
			}
		}
		
		function changepg() {
			
			if (event.keyCode==13) {
				var trs = document.getElementsByTagName("tr");
				var totalpg = <%=(size%10!=0)?(size/10+1):(size/10)%>;
				var pgnum = parseInt(document.getElementById("pgnum").value);
				if (pgnum <= 0 || pgnum > totalpg) {				
					return;
				} else {
					for(var i = 1; i < trs.length; i++) {
						trs[i].style.display="none";
					}
					for(var i = pgnum * 10 - 9; i < pgnum * 10 + 1; i++) {
						trs[i].style.display="";					
					}
				}
			}
		}
		function edituser() {
			document.getElementById("name").removeAttribute("readonly");
			document.getElementById("age").removeAttribute("readonly");
			document.getElementById("userName").removeAttribute("readonly");
			document.getElementById("passWord").removeAttribute("readonly");
			document.getElementById("email").removeAttribute("readonly");
			
		}
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
			
	</script>
    </body>
</html>