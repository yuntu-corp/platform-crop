<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
	<head>  
		<script type="text/javascript">
			Namespace.register("com.yunt.customer.index");
			com.yunt.customer.index = {
				loadContent : function(href) {
					$("body").mask("数据正在加载中，请稍后···");
					$("#content").load(href,function(){
						$("body").unmask();
					});
				},
				input : function() {
					$.ajax({
					 	type: "POST",
					 	url: "${path}/customer/saveOrUpdate?inputKind=save",
					  	processData:true,
					  	success: function(data){
					  		com.yunt.customer.index.refresh();
					  	}
					});
				},
				initCustomer : function() {
					$("body").mask("数据正在加载中，请稍后···");
					$.ajax({
					 	type: "POST",
					 	url: "${path}/customer/initCustomer",
					  	processData:true,
					  	success: function(){
					  		$("body").unmask();
					  		com.yunt.customer.index.refresh();
					  		$.successTips();
					  	}
					});
				},
				weChatTestPost : function() {
					$.ajax({
					 	type: "POST",
					  	url: "${path}/weChat/payment",
					  	processData:true,
					  	success: function(data){
					  		alert(data);
					  	}
					});
				},
				refresh : function () {
					com.yunt.customer.index.loadContent("${path}/customer/list?model.name="+encodeURI(encodeURI($("#${pageId}name").val()))+"&model.kind="+$("#${pageId}kind").val());
				}
			};
			$(document).ready(function(){
				com.yunt.customer.index.refresh();
				var height=$(window).height()-160;
				$(".details").css("height",height);
			});
		</script>
	</head>
	<body>
		<div class="cont">
			<div class="title">客户管理</div>
			<div class="details">
				<div class="details_operation clearfix">
				    <div class="bui_select">
				    	<img src="${path}/platform/theme/distributionSystem/images/coin.png">
						<input type="button" value="初始化用户" class="add hand" onclick="com.yunt.customer.index.initCustomer();">
						
					</div>
					<div class="bui_select">
				    	<img src="${path}/platform/theme/distributionSystem/images/coin.png">
						<input type="button" value="weChatTestPost" class="add hand" onclick="com.yunt.customer.index.weChatTestPost();">
						
					</div>
					
					<div class="fr">
	                	<form method="" action="" class="box">
							<label for="pass" style="line-height:30px;padding-left:10px;">客户姓名:</label>
	                    	<input id="${pageId}name" class="search" type="text" placeholder="客户姓名" />
	                    	<label style="line-height:30px;padding-left:10px;">类型:</label>
	                    	<uc:select id="${pageId}kind" list="{1:'代理',0:'非代理'}" name="kind" cssClass="search" headerKey="" headerValue="请选择" />
	               			<input type="button" value="搜索" class="searchCoin" onclick="com.yunt.customer.index.refresh();">
						</form>
					</div>
				</div>
				<div id="content" />
		    </div>
		</div>
	</body>
</html>