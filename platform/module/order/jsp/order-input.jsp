<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
	<head>  
		<script type="text/javascript">
			Namespace.register("com.yunt.order.input");
			com.yunt.order.input = {
				doInput : function(dialogId) {
					if(!$("#${pageId}theForm").valid()){
						return false;
					}
					var formData = $("#${pageId}theForm").serialize();
					$.ajax({
					 	type: "POST",
					  	url: "${path}/order/saveOrUpdate?inputKind=${inputKind}",
					  	data: formData,
					  	success: function(data){
					  		$.successTips();
					   		art.dialog.list[dialogId].close();
					   		com.yunt.order.index.refresh();
					  	}
					});
				}
			};
		</script>
	</head>
	<body>
		<form method="post" id="${pageId}theForm">
			<c:if test="${inputKind eq 'update'}">
				<input type="hidden" name="model.id" value="${model.id}" />
				<input type="hidden" name="model.customer.id" value="${model.customer.id}" />
				
			</c:if>
			<div>
		     	<em style="color: red;">*</em>订单号：<input type='text' name="model.code" value="${model.code}" maxlength="50" class="inputStyle w200 required"/>
			</div>
			<div>
		     	<em style="color: red;">*</em>类型：<input type='text' name="model.kind" value="${model.kind}" maxlength="50" class="inputStyle w200 required"/>
			</div>
			<div>
		     	<em style="color: red;">*</em>客户：<input type='text' name="model.customer.name" value="${model.customer.name}" maxlength="50" class="inputStyle w200 required"/>
			</div>
			<div>
		     	<em style="color: red;">*</em>收件人：<input type='text' name="model.receiver" value="${model.receiver}" maxlength="50" class="inputStyle w200 required"/>
			</div>
			<div>
		     	<em style="color: red;">*</em>收件人电话：<input type='text' name="model.receiverPhoneNum" value="${model.receiverPhoneNum}" maxlength="50" class="inputStyle w200 required"/>
			</div>
			<div>
		     	<em style="color: red;">*</em>收件人地址：<input type='text' name="model.receiveAddress" value="${model.receiveAddress}" maxlength="50" class="inputStyle w200 required"/>
			</div>
			<div>
		     	<em style="color: red;">*</em>日期：<input type='text' name="model.date" value="${model.date}" maxlength="50" class="inputStyle w200 required"/>
			</div>
			<div>
		     	<em style="color: red;">*</em>状态：<input type='text' name="model.state" value="${model.state}" maxlength="50" class="inputStyle w200 required"/>
			</div>
			<div>
		     	<em style="color: red;">*</em>物流单号：<input type='text' name="model.logisticNum" value="${model.logisticNum}" maxlength="50" class="inputStyle w200 required"/>
			</div>
		</form>
	</body>
</html>
