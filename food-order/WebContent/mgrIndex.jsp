<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Restaurant" %>
<%@ page import="model.Feedback" %>
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
	
	<script src="./resources/js/unslider.js"></script>
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
    </style>

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
    <h4>您当前的位置:	<a href="index.jsp">首页 </a>	>	管理员服务</h4>
	<div class="section">
	<div class="page-header">
	<h1>商家管理</h1></div>
	<form action="submitOrder" method="post" role="form">
     <div class="row">
     <table id="table" class="table">
	   <thead>
	      <tr>
	         <th>ID</th>
	         <th>用户名</th>
	         <th>密码</th>
	         <th>邮件</th>
	      </tr>
	   </thead>
	   <tbody>
	      <s:iterator value="#session.salers" id="saler" status="st">  
	          <tr name="trs">  
	          <td name="sid"><s:property value="#saler.id"/></td> 
	          <td name="susername"><s:property value="#saler.userName"/></td> 
	          <td name="spwd"><s:property value="#saler.passWord"/></td>  
	          <td name="smail"><s:property value="#saler.email"/></td>
	          <td><a href="javascript:void(0)" onclick="update(this)"><span class="glyphicon glyphicon-pencil"></span></a>
	          <a href="manageSaler.action?type=delete&sid=<s:property value="#saler.id"/>"><span class="glyphicon glyphicon-remove"></span></a>
	          </td>
	          <td>
	          <div id="<s:property value="#saler.id"/>">
	          <button id="yes" type="button" class="btn btn-primary" onclick="window.location.href='manageSaler.action?type=yes&sid=<s:property value="#saler.id"/>'">同意</button>
	          <button id="no" type="button" class="btn btn-danger" onclick="window.location.href='manageSaler.action?type=no&sid=<s:property value="#saler.id"/>'">拒绝</button>
	          <script>
	          	var valid = ${saler.stat};
	          	if (valid == 2) {
	          		document.getElementById('<s:property value="#saler.id"/>').innerHTML = "<button type='button' class='btn btn-primary' disabled>已接受</button>";
	          	}
	          	if (valid == 1) {
	          		document.getElementById('<s:property value="#saler.id"/>').innerHTML = "<button type='button' class='btn btn-danger' disabled>已拒绝</button>";
	          	}
	          </script>
	          </div>
	          </td>
	          <td name="restnum">TA注册了<font color="red"><s:property value="#saler.rests.size()"/></font>个门店</td>
	          </tr>  
	      </s:iterator>  
	   </tbody>
	</table>
	</div>
	</form>
	<div class="row">
		<div class="col-md-2">
		<a href="javascript:void(0)" onclick="add()"><span class="glyphicon glyphicon-plus"></span></a>
		<a href="javascript:void(0)" onclick="sear()"><span class="glyphicon glyphicon-search"></span></a>
		<a href="javascript:void(0)" onclick="refresh()"><span class="glyphicon glyphicon-refresh"></span></a>
		</div>
	</div>
	
	</div>
	
	<div class="section">
		<div class="page-header"><h1>
			会员统计
		</h1></div>
		<canvas id="myChart" width="800" height="600"></canvas>
	</div>
	<div class="section">
		<div class="page-header"><h1>
			用户反馈
		</h1></div>
		<button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#feedback">
	 	查看反馈
		</button>
	</div>
	
	</div>

	<div class="modal fade" id="update" aria-hidden="true">
	<div class="modal-dialog">
	    <div class="modal-content">
     	<form id="form2" action="manageSaler" method="post" class="form-horizontal" role="form">
            <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">×</button>
            <h3>修改</h3>
            </div>
            <div class="modal-body">
            	<hr>
            	<div class="form-group">
                <label class="col-md-3 control-label" for="username">
			         用户名
			    </label>
			    <div class="col-md-6">
			       <input id="username" type="text" name="personBean.userName" class="form-control" check-type="required" minlength="6" maxlength="12" placeholder="用户名长度6-12位">
			    </div>
			    </div>
			    <hr>
			    <div class="form-group">
			    <label class="col-md-3 control-label" for="password">
			         密码
			    </label>
			    <div class="col-md-6">
			       <input id="password" type="password" name="personBean.passWord" class="form-control" check-type="required" minlength="6" maxlength="12" onpaste="return false" placeholder="密码长度6-12位">
			    	<input type="text" name="type" value="update"  style="display:none;"/>
					<input id="sid" type="text" name="sid" style="display:none;"/>
			    </div>
			    </div>
			    <hr>
			    <div class="form-group">
			    <label class="col-md-3 control-label" for="mail">
			         邮箱
			    </label>
			    <div class="col-md-6">
			       <input id="mail" type="text" name="personBean.email" class="form-control" check-type="mail" placeholder="请输入邮箱">
			       
			    </div>
			    </div>
			    <hr>
            </div>
            
            <div class="modal-footer">
            	<button id="submit2" type="submit" class="btn btn-primary">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </form>
    </div>
    </div>
    </div>

	<div class="modal fade" id="add" aria-hidden="true">
	<div class="modal-dialog">
	    <div class="modal-content">
     	<form id="form1" action="manageSaler" method="post" class="form-horizontal" role="form">
            <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">×</button>
            <h3>增加</h3>
            </div>
            <div class="modal-body">
            	<hr>
            	<div class="form-group">
                <label class="col-md-3 control-label" for="username">
			         用户名
			    </label>
			    <div class="col-md-6">
			       <input type="text" name="personBean.userName" class="form-control" check-type="required" minlength="6" maxlength="12" placeholder="用户名长度6-12位">
			    </div>
			    </div>
			    <hr>
			    <div class="form-group">
			    <label class="col-md-3 control-label" for="password">
			         密码
			    </label>
			    
			    <div class="col-md-6">
			       <input type="password" name="personBean.passWord" class="form-control" check-type="required" minlength="6" maxlength="12" onpaste="return false" placeholder="密码长度6-12位">
			    </div>
			    </div>
			    <hr>
			    <div class="form-group">
			    <label class="col-md-3 control-label" for="mail">
			         邮箱
			    </label>
			    <div class="col-md-6">
			       <input type="text" name="personBean.email" class="form-control" check-type="mail" placeholder="请输入邮箱">
			       <input type="text" name="type" value="add"  style="display:none;"/>
			    </div>
			    </div>
			    <hr>
            </div>
            <div class="modal-footer">
            	<button id="submit1" type="submit" class="btn btn-primary">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </form>
    </div>
    </div>
    </div>
    
    <div class="modal fade" id="search" aria-hidden="true">
	<div class="modal-dialog">
	    <div class="modal-content">
            <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">×</button>
            <h3>查询</h3>
            </div>
            <div class="modal-body">
            	<div class="input-group">
            		<div class="col-md-5">
				      <select id="typeController" class="form-control">
				         <option>用户ID</option>
				         <option>用户名</option>
				         <option>密码</option>
				         <option>邮箱</option>
				      </select>
				    </div>
				    <div class="col-md-7">
			      <input id="searchtext" type="text" class="form-control"/>
			      </div>
			    </div><!-- /input-group -->
            </div>
            <div class="modal-footer">
            	<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="searchdata()">确认</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
    </div>
    </div>
    </div>
    
    <div class="modal fade" id="feedback" aria-hidden="true">
	<div class="modal-dialog">
	    <div class="modal-content">
            <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">×</button>
            <h3>用户反馈</h3>
            </div>
            <div class="modal-body">
            	<% 
            	List<Feedback> fbs = (List) session.getAttribute("fbs");
            		for (int i = 0; i < fbs.size(); i ++) {
            			if (fbs.get(i).getCus() == null) {%>
            				<p><font color="red">匿名</font> 说：<%=fbs.get(i).getContent()%></p>
            				<h6 style="font-style:italic;">发表于 <%=fbs.get(i).getTime() %></h6><br>
            			<% } else {%>
            				<p><font color="red"><%=fbs.get(i).getCus().getUserName() %></font> 说：<%= fbs.get(i).getContent()%></p>
            				<h6 style="font-style:italic;">发表于 <%=fbs.get(i).getTime() %></h6><br>
            			<% }} %>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">确认</button>
            </div>
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
    
    <!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>-->
    <script src="./resources/js/jquery-1.10.2.js"></script>
    <script src="./resources/bootstrap-3.2.0-dist/js/bootstrap.js"></script>
	<script src="./resources/js/docs.min.js"></script>
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
		
		$("#form1").validation();
		$("#form2").validation();
		
		$("#submit2").on('click',function(event){
		      // 2.最后要调用 valid()方法。
		      //  valide(object,msg),提示信息显示，object位置后面增加提示信息。如不填object 则自动找最后一个button submit.
		      //  valide(msg)
		      if ($("#form2").valid(this,'格式错误')==false){
		        return false;
		      }
		    });
		
		$("#submit1").on('click',function(event){
		      // 2.最后要调用 valid()方法。
		      //  valide(object,msg),提示信息显示，object位置后面增加提示信息。如不填object 则自动找最后一个button submit.
		      //  valide(msg)
		      if ($("#form1").valid(this,'格式错误')==false){
		        return false;
		      }
		    });
		
		$(document).ready(function() {
//			$('#myModal').modal();
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
		
		
		function update(obj){
			var tds=$(obj).parent().parent().find('td');
			$('#sid').val(tds.eq(0).text());
			$('#username').val(tds.eq(1).text());
			$('#password').val(tds.eq(2).text());
			$('#mail').val(tds.eq(3).text());
			$('#update').modal('show');
		}
		
		function add(){
			$('#add').modal('show');
		};
		
		function sear() {
			$('#search').modal('show');
		}
			
		function refresh() {
			var trs = document.getElementsByName("trs");
			for(var i=0; i < trs.length; i++)
		    {
				trs[i].style.display="";
		    }
		}
		function searchdata() {
//			var objTable=document.getElementById("table");
			var t = document.getElementById("typeController"); 
			var type = t.options[t.selectedIndex].value;
//			alert($("#searchtext")[0].value);
			var tr;
			if (type == "用户ID") {
				var tds = document.getElementsByName("sid");
			} else if(type == "用户名") {
				var tds = document.getElementsByName("susername");
			} else if(type == "密码") {
				var tds = document.getElementsByName("spwd");
			} else if(type == "邮箱") {
				var tds = document.getElementsByName("smail");
			}
			 for(var i=0; i < tds.length; i++)
		     {
//				 alert(tds[i].innerHTML);
				if (type == "用户ID") {
		        	if(tds[i].innerHTML != $('#searchtext')[0].value)
		        	{
		        		tr = tds[i].parentNode;
		            	tr.style.display='none';
		        	}
		     	} else {
//		     		alert("enter");
		     		if(tds[i].innerHTML.indexOf($('#searchtext')[0].value) == -1)
		        	{
		        		tr = tds[i].parentNode;
		            	tr.style.display='none';
		        	}
		     	}
		     }
		};
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

			//图表创建
			var opt = {
					scaleFontColor : "#000",
					barValueSpacing : 50
			}
			var data = {
				labels : [<%
					List rests = (List)session.getAttribute("rests");
				    Iterator i1 = rests.iterator();//先迭代出来  
				    List restVipsize = new ArrayList();
        			while(i1.hasNext()){
        				Restaurant rest = (Restaurant)i1.next();
        				restVipsize.add(rest.getVips().size());
        		%>"<%=rest.getRestName()%>",<%}%>],
				datasets : [
					{
						fillColor : "rgba(155,200,205,1)",
						strokeColor : "rgba(220,220,220,1)",
						data : [<%	Iterator i2 = restVipsize.iterator();
								while(i2.hasNext()){
								%> <%=(Integer)i2.next()%>,<%}%>
	        						]
					}
				]
			}
			
			var ctx = document.getElementById("myChart").getContext("2d");
			var ds = new Chart(ctx);
			new Chart(ctx).Bar(data, opt);
	</script>
    </body>
</html>