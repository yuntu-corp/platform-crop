<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
	<head>  
		<script type="text/javascript">
			Namespace.register("com.yunt.taskCheck.index");
			com.yunt.taskCheck.index = {
				loadContent : function(href) {
					$("body").mask("数据正在加载中，请稍后···");
					$("#content").load(href,function(){
						$("body").unmask();
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
					com.yunt.taskCheck.index.loadContent("${path}/task/taskChecklist?model.title=" + encodeURI(encodeURI($("#${pageId}taskCheckcontent").val()))); 
				}
			};
			$(document).ready(function(){
				com.yunt.taskCheck.index.refresh();
				var height=$(window).height()-160;
				$(".details").css("height",height);
			});
		</script>
	</head>
	<body>
		<div class="cont">
			<div class="title">审核管理</div>
			<div class="details">
				<div class="details_operation clearfix">
					<div class="fr">
	                	<form method="" action="" class="box">
							<label for="pass" style="line-height:30px;padding-left:10px;">关键词:</label>
	                    	<input id="${pageId}taskCheckcontent" class="search" type="text" placeholder="关键词" />
	               			<input type="button" value="搜索" class="searchCoin" onclick="com.yunt.taskCheck.index.refresh();">
						</form>
					</div>
				</div>
				<div id="content" />
		    </div>
		</div>
	</body>
</html>
