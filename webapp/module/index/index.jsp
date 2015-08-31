<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/platform/common/jsp/common.jsp"%>
		<%@ include file="/platform/common/jsp/baseJs.jsp"%>
		<link href="${path}/platform/theme/distributionSystem/css/backstage.css" rel="stylesheet">
		<script type="text/javascript">
			Namespace.register("com.yunt.index");
			com.yunt.index = {
				pageId:"${pageId}",
				loadContent : function(href) {
					$("#${pageId}content").load(href);
				},
				resetPwd : function(id) {
					$.dialog.load("${path}/user/toChangePassword?id="+id, {
						title : "修改密码",
						width: 800,
						height: 200,
						fixed: true,
						id: '${pageId}toChangePassword',
						lock: true,
						ok: function(){
							com.yunt.user.changePassword.doInput("${pageId}toChangePassword");
							return false;
					    },
						cancel : function(){
							
						}
					});
				},
				userInput : function(id) {
					$.dialog.load("${path}/user/userInput?id="+id, {
						title : "个人设置",
						width: 800,
						height: 500,
						fixed: true,
						id: '${pageId}userInput',
						lock: true,
						ok: function(){
							com.yunt.user.userInput.doInput("${pageId}userInput");
							return false;
					    },
						cancel : function(){
							
						}
					});
				}
			};
			$(document).ready(function(){
				com.yunt.index.loadContent('${path}/goods/index');
				$(".mList li").each(function(){
					$(this).click(function(){
						$(".mList li").each(function(){
							$(this).removeClass("left_list_selected");
						})
						$(this).addClass("left_list_selected");
					})
				});
			});
		</script>
	</head>
	<body>
		<div id="header_container">
	        <span class="welcome02">Welcome</span>
	        <img src="${path}/platform/theme/distributionSystem/images/bias.png" class="bias">
	        
	        <div class="system">
	            <span class="system01">后台管理系统</span>
	            <span class="system02">Background management system</span>
	        </div>
	      
	        <div class="close">
	            <a href="${path}/spring_logout"><img src="${path}/platform/theme/distributionSystem/images/close.png">
	            	<span>注销登录</span>
	            </a>
	            <a href="javascript:void(0);" onclick="com.yunt.index.resetPwd('${user.id}');"><img src="${path}/platform/theme/distributionSystem/images/coin2.png">
	            	<span>修改密码</span>
	            </a>
	        	<a href="javascript:void(0);" onclick="com.yunt.index.userInput('${user.id}');"><img src="${path}/platform/theme/distributionSystem/images/coin2.png">
	            	<span>个人设置</span>
	            </a>
	        </div>
		</div>
	    <div class="content clearfix">
	        <div id="${pageId}content" class="main"></div>
	        <!--左侧列表-->
	        <div class="menu">
	            <div class="cont">
	                <ul class="mList">
	                    <li onclick="com.yunt.index.loadContent('${path}/goods/index');" class="left_list_selected">
	                        <img src="${path}/platform/theme/distributionSystem/images/home.png">商品管理
	                    </li>
	                    <li onclick="com.yunt.index.loadContent('${path}/order/index');">
	                        <img src="${path}/platform/theme/distributionSystem/images/coin3.png">订单管理  
	                    </li>
	                    <li onclick="com.yunt.index.loadContent('${path}/withdrawCash/index');">
	                        <img src="${path}/platform/theme/distributionSystem/images/coin7.png">提现管理
	                    </li>
	                    <li onclick="com.yunt.index.loadContent('${path}/customer/index')">
	                        <img src="${path}/platform/theme/distributionSystem/images/coin4.png">客户管理
	                    </li>
	                    <li onclick="com.yunt.index.loadContent('${path}/propaganda/index')">
	                        <img src="${path}/platform/theme/distributionSystem/images/coin5.png">销售话术
	                    </li>
	                    <li onclick="com.yunt.index.loadContent('${path}/level/index')">
	                        <img src="${path}/platform/theme/distributionSystem/images/level.png">分销层级
	                    </li>
	                     <li onclick="com.yunt.index.loadContent('${path}/user/index')">
	                        <img src="${path}/platform/theme/distributionSystem/images/coin4.png">用户管理
	                    </li>
	                </ul>
	            </div>
	        </div>
	    </div>
	</body>
</html>