<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
	<head>  
		<script type="text/javascript">
			Namespace.register("com.yunt.order.view");
			com.yunt.order.view = {
			};
		</script>
	</head>
	<body>
		<form id="${pageId}theForm" method="post" class="BB">
			<div style="padding: 10px;">
				<div class="Information_top">
					<span>基本信息</span>
					<div class="Line-style"></div>
				</div>
				<div class="pl50 lh50">
			     	<label class="labelTitle">订单号:</label>
			     	<div class="view_detail2">
			     		${model.code}
			     	</div>
			     	<label class="labelTitle">类型:</label>
			     	<div class="view_detail2">
			     		${model.kindValue}
			     	</div>
				</div>
				<div class="pl50 lh50">
			     	<label class="labelTitle">客户:</label>
			     	<div class="view_detail2">
			     		${model.customer.name}
			     	</div>
			     	<label class="labelTitle">收件人:</label>
			     	<div class="view_detail2">
			     		${model.receiver}
			     	</div>
				</div>
				<div class="pl50 lh50">
			     	<label class="labelTitle">收件人电话:</label>
			     	<div class="view_detail2">
			     		${model.receiverPhoneNum}
			     	</div>
			     	<label class="labelTitle">收件人地址:</label>
			     	<div class="view_detail2">
			     		${model.receiveAddress}
			     	</div>
				</div>
				<div class="pl50 lh50">
			     	<label class="labelTitle">日期:</label>
			     	<div class="view_detail2">
			     		${model.date}
			     	</div>
			     	<label class="labelTitle">状态:</label>
			     	<div class="view_detail2">
			     		${model.stateValue}
			     	</div>
				</div>
				<div class="pl50 lh50">
			     	<label class="labelTitle">物流名称:</label>
			     	<div class="view_detail2">
			     		${model.logisticName}
			     	</div>
			     	<label class="labelTitle">物流单号:</label>
			     	<div class="view_detail2">
			     		${model.logisticNum}
			     	</div>
				</div>
				<div class="clear" />
				<div class="Information_top">
					<span>购买商品</span>
					<div class="Line-style"></div>
				</div>
				<div class="pt20">
					<table class="Information_Table">
						<tr>
							<th>商品</th>
							<th>数量</th>
							<th>价格</th>
						</tr>
						<c:forEach items="${model.orderGoodsList}" var="orderGoods">
							<tr>
								<td>${orderGoods.goods.name}</td>
								<td>${orderGoods.count}</td>
								<td>${orderGoods.totalPrice}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</form>
	</body>
</html>
