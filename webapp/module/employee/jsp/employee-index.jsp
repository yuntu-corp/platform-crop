<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
	<head>  
		<script type="text/javascript">
			Namespace.register("com.yunt.employee.index");
			com.yunt.employee.index = {
				loadContent : function(href) {
					$("body").mask("数据正在加载中，请稍后···");
					$("#content").load(href,function(){
						$("body").unmask();
					});
				},
				input : function(href, title) {
					$.dialog.load(href,{
						id: '${pageId}employeeInput',
						title:title,
						width:700,
						height:600,
						ok:function(){
							com.yunt.employee.employeeInput.doInput('${pageId}employeeInput');
							return false;
						},
						cancel: function(){
		
						}
					});
				},
				updateBaseBitcoin : function(href, title) {
					$.dialog.load(href,{
						id: '${pageId}employeeUpdate',
						title:title,
						width:600,
						height:500,
						ok:function(){
							com.yunt.employee.updateBaseBitcoin.doUpdateBaseBitcoin('${pageId}employeeUpdate');
							return false;
						},
						cancel: function(){
		
						}
					});
				},
				updateEmployee : function(href, title) {
					$.dialog.load(href,{
						id: '${pageId}employeeUpdate',
						title:title,
						width:600,
						height:500,
						ok:function(){
							com.yunt.employee.updateEmployee.doUpdate('${pageId}employeeUpdate');
							return false;
						},
						cancel: function(){
		
						}
					});
				},
				view : function(href, title) {
					$.dialog.load(href,{
						id: '${pageId}employeeInput',
						title:title,
						width:600,
						height:500,
						ok:function(){
						}
					});
				},
				refresh : function () {
					com.yunt.employee.index.loadContent("${path}/employee/list?model.name=" + $("#${pageId}content").val());
				}
			};
			$(document).ready(function(){
				com.yunt.employee.index.refresh();
				var height=$(window).height()-160;
				$(".details").css("height",height);
			});
		</script>
	</head>
	<body>
		<div class="cont">
			<div class="title">用户管理</div>
			<div class="details">
				<div class="details_operation clearfix">
				    <div class="bui_select">
				    	<img src="${path}/platform/theme/distributionSystem/images/coin.png">
						<input type="button" value="添 加" class="add hand" onclick="com.yunt.employee.index.input('${path}/employee/goInput', '添加');">
					</div>
					<div class="fr">
	                	<form method="" action="" class="box">
							<label for="pass" style="line-height:30px;padding-left:10px;">关键词:</label>
	                    	<input id="${pageId}content" class="search" type="text" placeholder="关键词" />
	               			<input type="button" value="搜索" class="searchCoin" onclick="com.yunt.employee.index.refresh();">
						</form>
					</div>
				</div>
				<div id="content" />
		    </div>
		</div>
	</body>
</html>
