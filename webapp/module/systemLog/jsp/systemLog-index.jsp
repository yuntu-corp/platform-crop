<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
	<head>  
		<script type="text/javascript">
			Namespace.register("com.yunt.systemLog.index");
			com.yunt.systemLog.index = {
				loadContent : function(href) {
					$("body").mask("数据正在加载中，请稍后···");
					$("#content").load(href,function(){
						$("body").unmask();
					});
				},
				view : function(href, title) {
					$.dialog.load(href,{
						id: '${pageId}systemLogInput',
						title:title,
						width:600,
						height:500,
						ok:function(){
						},

					});
				},
				refresh : function () {
					com.yunt.systemLog.index.loadContent("${path}/systemLog/list?model.operatorName=" + $("#${pageId}content").val());
				}
			};
			$(document).ready(function(){
				com.yunt.systemLog.index.refresh();
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
					<div class="fr">
	                	<form method="" action="" class="box">
							<label for="pass" style="line-height:30px;padding-left:10px;">操作人:</label>
	                    	<input id="${pageId}content" class="search" type="text" placeholder="关键词" />
	               			<input type="button" value="搜索" class="searchCoin" onclick="com.yunt.systemLog.index.refresh();">
						</form>
					</div>
				</div>
				<div id="content" />
		    </div>
		</div>
	</body>
</html>
