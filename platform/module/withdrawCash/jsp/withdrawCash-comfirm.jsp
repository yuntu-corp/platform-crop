<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
	<head>  
		<script type="text/javascript">
			Namespace.register("com.yunt.withdrawCash.comfirm");
			com.yunt.withdrawCash.comfirm = {
				doInput : function(dialogId) {
					var formData = $("#${pageId}theForm").serialize();
					$.ajax({
					 	type: "POST",
					  	url: "${path}/withdrawCash/doComfirm",
					  	data: formData,
					  	success: function(data){
					  		$.successTips();
					   		art.dialog.list[dialogId].close();
					   		com.yunt.withdrawCash.index.refresh();
					  	}
					});
				}
			};
		</script>
	</head>
	<body>
		<form id="${pageId}theForm" method="post" class="BB">
			<div style="padding: 10px;">
				<input type="hidden" name="model.id" value="${model.id}" />
				<input type="hidden" name="model.code" value="${model.code}" />
				<input type="hidden" name="model.money" value="${model.money}" />
				<input type="hidden" name="model.customer.id" value="${model.customer.id}" />
				<input type="hidden" name="model.date" value="${model.date}" />
				<input type="hidden" name="model.state" value="1" />
				
				<div class="pl100 lh50">
			     	<label class="labelTitle">订单号:</label>
			     	<div class="view_detail">
			     		${model.code}
			     	</div>
				</div>
				<div class="pl100 lh50">
			     	<label class="labelTitle">开户人:</label>
			     	<div class="view_detail">
			     		${model.accountName}
			     	</div>
				</div>
				<div class="pl100 lh50">
			     	<label class="labelTitle">账号:</label>
			     	<div class="view_detail">
			     		${model.accountNum}
			     	</div>
				</div>
				<div class="pl100 lh50">
			     	<label class="labelTitle">开户银行:</label>
			     	<div class="view_detail">
			     		${model.accountBank}
			     	</div>
				</div>
				<div class="pl100 lh50">
			     	<label class="labelTitle">金额:</label>
			     	<div class="view_detail">
			     		${model.money}
			     	</div>
				</div>
				<div class="pl100 lh50">
			     	<label class="labelTitle">客户:</label>
			     	<div class="view_detail">
			     		${model.customer.name}
			     	</div>
				</div>
				<div class="pl100 lh50">
			     	<label class="labelTitle">日期:</label>
			     	<div class="view_detail">
			     		${model.date}
			     	</div>
				</div>
				<div class="pl100 lh50">
			     	<label class="labelTitle">状态:</label>
			     	<div class="view_detail">
			     		${model.stateValue}
			     	</div>
				</div>
			</div>
		</form>
	</body>
</html>