<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
	<head>  
		<script type="text/javascript">
			Namespace.register("com.yunt.order.deliver");
			com.yunt.order.deliver = {
				doInput : function(dialogId) {
					if(!$("#${pageId}theForm").valid()){
						return false;
					}
					var formData = $("#${pageId}theForm").serialize();
					$.ajax({
					 	type: "POST",
					  	url: "${path}/order/doDeliver",
					  	data: formData,
					  	success: function(data){
					  		$.successTips();
					   		art.dialog.list[dialogId].close();
					   		com.yunt.order.index.refresh();
					  	}
					});
				}
			};
			$(document).ready(function() {
				$.hz.validate.init("${pageId}theForm");
			});
		</script>
	</head>
	<body>
		<form id="${pageId}theForm" method="post" class="BB">
			<div style="padding: 10px;">
				<input type="hidden" name="model.id" value="${model.id}" />
				<input type="hidden" name="model.code" value="${model.code}" />
				<input type="hidden" name="model.kind" value="${model.kind}" />
				<input type="hidden" name="model.customer.id" value="${model.customer.id}" />
				<input type="hidden" name="model.receiver" value="${model.receiver}" />
				<input type="hidden" name="model.receiverPhoneNum" value="${model.receiverPhoneNum}" />
				<input type="hidden" name="model.receiveAddress" value="${model.receiveAddress}" />
				<input type="hidden" name="model.date" value="${model.date}" />
				<input type="hidden" name="model.state" value="2" />
				
				<div class="Information_top">
					<span>基本信息</span>
					<div class="Line-style"></div>
				</div>
				<div class="pl10 lh50">
			     	<label class="labelTitle">订单号:</label>
			     	<div class="view_detail2">
			     		${model.code}
			     	</div>
			     	<label class="labelTitle">类型:</label>
			     	<div class="view_detail2">
			     		${model.kindValue}
			     	</div>
				</div>
				<div class="pl10 lh50">
			     	<label class="labelTitle">客户:</label>
			     	<div class="view_detail2">
			     		${model.customer.name}
			     	</div>
			     	<label class="labelTitle">收件人:</label>
			     	<div class="view_detail2">
			     		${model.receiver}
			     	</div>
				</div>
				<div class="pl10 lh50">
			     	<label class="labelTitle">收件人电话:</label>
			     	<div class="view_detail2">
			     		${model.receiverPhoneNum}
			     	</div>
			     	<label class="labelTitle">收件人地址:</label>
			     	<div class="view_detail2">
			     		${model.receiveAddress}
			     	</div>
				</div>
				<div class="pl10 lh50">
			     	<label class="labelTitle">日期:</label>
			     	<div class="view_detail2">
			     		${model.date}
			     	</div>
			     	<label class="labelTitle">状态:</label>
			     	<div class="view_detail2">
			     		${model.stateValue}
			     	</div>
				</div>
				<div class="pl10 lh50 fl" style="width: 365px;">
			     	<label class="labelTitle"><em style="color: red;">*</em>物流名称:</label>
			     	<input type='text' name="model.logisticName" value="${model.logisticName}" maxlength="50" class="required w200"/>
				</div>
				<div class="pl10 lh50 fl">
			     	<label class="labelTitle"><em style="color: red;">*</em>物流单号:</label>
			     	<input type='text' name="model.logisticNum" value="${model.logisticNum}" maxlength="50" class="required w200"/>
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
