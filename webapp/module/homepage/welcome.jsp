<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp"%>
<!-- 登录页不需要引baseJs.jsp，只引下边这点就行 -->
<!-- 原生扩展 start -->
<script src="${path}/platform/common/js/prototype-ext.js"></script>
<!-- jQuery -->
<script src="${path}/platform/common/js/jquery-1.11.1.js"></script>
<script src="${path}/platform/common/js/jquery-ext.js"></script>
<script src="${path}/platform/theme/default/component/bootstrap/js/bootstrap.min.js"></script>
<html>
	<head>
		<title>${productTitle}</title>
		<link rel="shortcut icon" type="image/x-icon" href="${path}/platform/theme/login/default/images/favicon.ico">
		<link href="${path}/platform/theme/default/component/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
		<link href="${path}/platform/theme/distributionSystem/css/module.css" rel="stylesheet" />
		<script>
	  		Namespace.register('com.ue.courseplatform.welcome');
	  		com.ue.courseplatform.welcome = {
	  			
	  			login: function(loginName, password){
	  				if(loginName==""||password==""){
	  					$(".cytip").html("用户名或者密码不能为空");
	  					return;
	  				}
	  				$.getJSON("${path}/bd/welcome/ajaxValidationUser", {"loginName":loginName, "password":password}, function(info){
						if(info.successStatus=="false"){
							$(".cytip").html("用户名或者密码错误");
							return;
						}
						$(".cytip").html("");
		  				$('#${pageId}welcomeForm').submit();
					});
	  			},
	  			
	  			submit: function(){
	  				var space = this;
		  			var loginName=$("#${pageId}loginName").val().trim();
		  			var password=$("#${pageId}password").val();
		  			var img = $('#${pageId}imgjudge').val();
		  			if(loginName==""||password==""){
	  					$(".cytip").html("用户名或者密码不能为空");
	  					return;
	  				}
// 		  			if(!img){
// 		  				$(".cytip").html("请输入验证码");
// 						return;
// 		  			}
//		  			var content = '';
//		  			$.ajax({
//						type : "GET",
//						async: false,
//						url : "${path}/bd/welcome/getSessionPatchca?r="+new Date().getTime(),
//						success : function(data) {
//							content = data;
//						}
//					});
// 		  			if(img!=content){
// 		  				$(".cytip").html("验证码输入错误");
// 						return;
// 		  			}
// 		  			$(".cytip").html("");
		  			space.login(loginName, password);
	  			},
	  			
	  			autoEnterSubmit: function(e){
	  				var keynum;
					if(window.event) {
						keynum = e.keyCode;
					}else if(e.which) {
						keynum = e.which;
					}
					if (keynum && keynum == 13){
						this.submit();
					}
		  		},
		  		
		  		findPassword:function(){
		  			alert("程序开发中..");
		  			return;
		  			var loginName=$("#${pageId}loginName").val().trim();
		  			$.dialog.load("${path}/bd/welcome!findPassword.action?loginName="+loginName, {
						title : "找回密码",
						width : 800,
						height : 400,
						fixed : true,
						id : '${pageId}',
						lock : true,
						resize : false,
						drag: true
					});
		  		},
		  		
		  		register : function(){
		  			window.location.href = "${path}/bd/school/register";
		  		}
		  		
	  		};
	  		
	  		$(document).ready(function(){
		 		//$('#${pageId}validate').click(function(){
		 			//$('#${pageId}validate').attr("src", "${path}/bd/welcome/validatePatchca?randomNum="+new Date().getTime());
		 		//});
		 	});
	  	</script>
	</head>
	<body class="ovauto login-body" onkeydown="com.ue.courseplatform.welcome.autoEnterSubmit(event);">
	
		<div id="header_container">
			<div id="header">                                                              
				<span class="welcome01">Welcome</span>
				<div class="border">
				<span class="border01">后台管理系统</span>
					<span class="border02">Background management system</span>
				</div>
			</div>
		</div>
		<div id="login_container">
			<uc:form id="${pageId}welcomeForm" cssClass="box" method="post" action="${path}/j_spring_security_check">
				<input type="hidden" id="${pageId}spring-security-redirect" name="spring-security-redirect"/>
				<div class="username_container">
					<img src="${path}/platform/theme/distributionSystem/images/username.png">
					<input class="username" type="text" id="${pageId}loginName" placeholder="用户名" tabindex="1" name="j_username" value="btjmrh"/>
				</div>
				<div class="pass_container">
					<img src="${path}/platform/theme/distributionSystem/images/passworld.png">
					<input class="pass" type="password" id="${pageId}password" placeholder="密码" tabindex="2" name="j_password" value="000000"/>
				</div>
				<input class="login" type="button" value="登录" onclick="com.ue.courseplatform.welcome.submit();" tabindex="4"/>
			</uc:form>
		</div>
	
	
		<%-- <div id="webContainer">
			<div class="webBody">
				<!--顶部条-->
				<div class="logintop">
					<div class="loginWrap">
						<!-- <img width="200px" height="200px" alt="asdsa" src="https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQEI8DoAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL09VaUdkaGpsdFNuTUJDVTR6MkJGAAIEbaxdVQMEAAAAAA%3d%3d" /> -->
						<i class="icon-search"></i>
						<!-- <h1 class="loginlogo"></h1>
						<div class="loginzhuce">
							第一次使用选排课？<a href="javascript:void(0);" onclick="com.ue.courseplatform.welcome.register();">立即注册</a>
						</div> -->
					</div>
				</div>
				<!--banner图和登录框-->
				<div class="loginbanner">
					<div class="loginWrap">
						<div class="loginform">
							<uc:form id="${pageId}welcomeForm" cssClass="ztag" method="post" action="${path}/j_spring_security_check">
								<input type="hidden" id="${pageId}spring-security-redirect" name="spring-security-redirect"/>
								<ul>
									<li class="strongtitle">会员登录<span class="glyphicon glyphicon-search" aria-hidden="true"></span></li>
									<li><input class="input" type="text" id="${pageId}loginName" placeholder="用户名" tabindex="1" name="j_username" value="administrator"/></li>
									<li><input class="input" type="password" id="${pageId}password" placeholder="密码" tabindex="2" name="j_password" value="000000"/></li>
									<li class="yanzheng">
					                    <input class="input" type="text" placeholder="验证码" id="${pageId}imgjudge" tabindex="3"/>
					                	<img id="${pageId}validate" width="80px" height="30px" src="${path}/bd/welcome/validatePatchca?randomNum=1" title="看不清，点击图片更换验证码" />
									</li>
									<li class="miandenglu"><input type="checkbox" id="twoweek" name="_spring_security_remember_me"/><label for="twoweek">两周内免登录</label><a href="#">忘记密码？</a></li>
									<li><a href="javascript:void(0);" tabindex="4" hidefocus="true" class="loginbtn" onclick="com.ue.courseplatform.welcome.submit();">登录</a></li>
									<li class="cytip" style="color: red; margin-top:15px;"></li>
								</ul>
							</uc:form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<%@ include file="/platform/common/jsp/footer.jsp"%>
		
		<script>
			//IE6,7升级提示
			if($.browser.msie && $.browser.version < 8){
				var ie_css_string = '.update_browser {height: 350px;left: 50%;margin-left: -320px;margin-top: -175px;position: absolute;top: 50%;width: 640px;}.update_browser .subtitle {color: #777777;font-family: 微软雅黑;font-size: 14px;height: 36px;line-height: 18px;width: 640px;}.update_browser .title {color: #009AD9;font-family: 微软雅黑;font-size: 48px;height: 106px;line-height: 96px;text-align: center;width: 640px;}.update_browser .title span {color: #FF3333;font-size: 60px;}.update_browser .browser {background: url("${path}/platform/theme/login/default/images/browser.png") no-repeat scroll 0 0 transparent;height: 128px;overflow: hidden;width: 640px;}.update_browser .browser a {display: block;float: left;height: 128px;text-indent: -999em;width: 128px;}.update_browser .bottomtitle {color: #777777;font-family: 微软雅黑;font-size: 14px;height: 78px;line-height: 78px;text-align: center;width: 640px;}.update_browser .bottomtitle a {color: #777777;}.update_browser .bottomtitle a:hover {color: #999999;}';
				$("head").append('<style type="text\/css">' + ie_css_string + '<\/style>'); 
				$('.login-body').css({background: "white" }).html('<div class="update_browser">'+
				'<div class="subtitle">您正在使用的IE浏览器版本过低，<br>我们建议您升级或者更换浏览器，以便体验顺畅、兼容、安全的互联网。</div>'+
				'<div class="title">选择一款<span>新</span>浏览器吧</div>'+
				'<div class="browser">'+
				'<a href="http://windows.microsoft.com/zh-CN/internet-explorer/downloads/ie" class="ie" target="_blank" title="ie浏览器">ie浏览器</a>'+
				'<a href="http://www.google.cn/chrome/intl/zh-CN/landing_chrome.html" class="chrome" target="_blank" title="谷歌浏览器">谷歌浏览器</a>'+
				'<a href="http://www.firefox.com.cn" class="firefox" target="_blank" title="火狐浏览器">火狐浏览器</a>'+
				'<a href="http://www.opera.com" class="opera" target="_blank" title="opera浏览器">opera浏览器</a>'+
				'<a href="http://www.apple.com.cn/safari" class="safari" target="_blank" title="safari浏览器">safari浏览器</a>'+
				'</div>'+
				'<div class="bottomtitle">[&nbsp;对IE6说再见&nbsp;]</div>'+
				'</div>');
			}
		</script> --%>
	</body>
</html>