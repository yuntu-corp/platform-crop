<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
	<head>  
		<script type="text/javascript">
			Namespace.register("com.yunt.goods.index");
			com.yunt.goods.index = {
				pageId : "${pageId}",
				loadContent : function(href) {
					$("body").mask("数据正在加载中，请稍后···");
					$("#content").load(href,function(){
						$("body").unmask();
					});
				},
				input : function(href,title) {
					$.dialog.load(href,{
						id: '${pageId}goodsInput',
						title:title,
						width:720,
						height:720,
						lock:true,
						ok:function(){
							com.yunt.goods.input.doInput('${pageId}goodsInput');
							return false;
						},
						cancel: function(){
		
						}
					})
				},
				refresh : function () {
					com.yunt.goods.index.loadContent("${path}/goods/list?model.name="+$("#${pageId}name").val());
				}
			};
			$(document).ready(function(){
				com.yunt.goods.index.refresh();
				var height=$(window).height()-160;
				$(".details").css("height",height);
				
			});
		</script>
	</head>
	<body>
		<div class="cont">
			<div class="title">产品管理</div>
			<div class="details">
				<div class="details_operation clearfix">
				    <div class="bui_select">
				    	<img src="${path}/platform/theme/distributionSystem/images/coin.png" class="hand">
						<input type="button" value="添加商品" class="add" onclick="com.yunt.goods.index.input('${path}/goods/input?inputKind=save', '添加');">
					</div>
					<div class="fr">
	                	<form method="" action="" class="box">
							<label for="pass" style="line-height:30px;padding-left:10px;">商品名称:</label>
	                    	<input id="${pageId}name" class="search" type="text" placeholder="商品名称" />
	               			<input type="button" value="搜索" class="searchCoin" onclick="com.yunt.goods.index.refresh();">
						</form>
					</div>
				</div>
				<div id="content" />
		    </div>
		</div>
	</body>
</html>
