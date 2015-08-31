<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
	<head>
		<script src="${path}/platform/common/js/jquery-1.11.1.js"></script>
		<meta name="viewport" content="width=320,user-scalable=no,target-densitydpi=medium-dpi" />
		<meta name="viewport" content="width=device-width,user-scalable=no,target-densitydpi=medium-dpi" />
		<link href="${path}/platform/theme/default/component/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet">
		<link href="${path}/platform/theme/distributionSystem/css/appraisal.css" type="text/css" rel="stylesheet">
		<title>评价商品</title>
		<script type="text/javascript">
			function doSave() {
				if($("#content").val().trim()=="") {
					alert("内容不能为空！");
					return;
				}
				$("#form").submit();
			}
		</script>
	</head>
	<body>
		<form id="form" action="${path}/weChat/doSaveAppraisal" method="post">
			<input type="hidden" name="evaluation.customer.id" value="${customerId}" />
			<input type="hidden" name="evaluation.goods.id" value="${orderGoods.goods.id}" />
			<input type="hidden" name="orderGoodsId" value="${orderGoods.id}">
	        <!--header-->
			<div class="appraisal-header">
	        	<a href="javascript:history.go(-1);"><span class="glyphicon glyphicon-menu-left" aria-hidden="true"></span></a>
				<span class="title">评价商品</span>
			</div>
	        <!--基本信息-->
			<div class="reviews">
	            <img src="${path}/platform/images/${orderGoods.goods.picUrls[0]}" class="reviews-img">
				<div class="reviews-information">
					<div class="reviews-name">${orderGoods.goods.name}</div>
					<div class="reviews-price">价格<span class="reviews-amount">￥${orderGoods.goods.price}</span></div>
				</div>
			</div>
	        <!--评价-->
	        <div class="evaluation-content">
	        	<textarea id="content" cols="50" rows="6" name="evaluation.content" value="" placeholder="在这里输入内容..."></textarea>
	        	<!-- <input type="text" size="" name="evaluation.content" value="" placeholder="在这里输入内容..." /> -->
	        </div>
	        <!--提交评价-->
	        <div class="rating">
	        	<a href="javascript:void(0);" onclick="doSave();" class="submit-rating">发表评论</a>
			</div>
		</form>
	</body>
</html>