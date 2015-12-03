<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
	<head>  
		<script type="text/javascript">
			Namespace.register("com.yunt.task.index");
			com.yunt.task.index = {
				loadContent : function(href) {
					$("body").mask("数据正在加载中，请稍后···");
					$("#content").load(href,function(){
						$("body").unmask();
					});
				},
				input : function(href, title) {
					$.dialog.load(href,{
						id: '${pageId}taskInput',
						title:title,
						width:700,
						height:500,
						ok:function(){
							com.yunt.task.input.doInput('${pageId}taskInput');
							return false;
						},
						cancel: function(){
		
						}
					});
				},
				view : function(href, title) {
					$.dialog.load(href,{
						id: '${pageId}taskInput',
						title:title,
						width:600,
						height:500,
						ok:function(){
						},
						cancel: function(){
		
						}
					});
				},
				refresh : function () {
					com.yunt.task.index.loadContent("${path}/task/list?model.title=" + encodeURI(encodeURI($("#${pageId}content").val())));
				}
			};
			$(document).ready(function(){
				com.yunt.task.index.refresh();
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
<%-- 				    <div class="bui_select">
				    	<img src="${path}/platform/theme/distributionSystem/images/coin.png">
						<input type="button" value="添 加" class="add hand" onclick="com.yunt.task.index.input('${path}/task/input?inputKind=save', '添加');">
					</div> --%>
					<div class="fr">
	                	<form method="" action="" class="box">
							<label for="pass" style="line-height:30px;padding-left:10px;">关键词:</label>
	                    	<input id="${pageId}content" class="search" type="text" placeholder="关键词" />
	               			<input type="button" value="搜索" class="searchCoin" onclick="com.yunt.task.index.refresh();">
						</form>
					</div>
				</div>
				<div id="content" />
		    </div>
		</div>
	</body>
</html>
