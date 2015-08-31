<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
	<head>  
		<script type="text/javascript">
			Namespace.register("com.yunt.withdrawCash.input");
			com.yunt.withdrawCash.input = {
				doInput : function(dialogId) {
					if(!$("#${pageId}theForm").valid()){
						return false;
					}
					var formData = $("#${pageId}theForm").serialize();
					$.ajax({
					 	type: "POST",
					  	url: "${path}/withdrawCash/saveOrUpdate?inputKind=${inputKind}",
					  	data: formData,
					  	success: function(data){
					  		$.successTips();
					   		art.dialog.list[dialogId].close();
					   		com.yunt.withdrawCash.index.refresh();
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
		<form method="post" id="${pageId}theForm">
			<c:if test="${inputKind eq 'update'}">
				<input type="hidden" name="model.id" value="${model.id}" />
				<input type="hidden" name="model.customer.id" value="${model.customer.id}" />
				
			</c:if>
			<div>
		     	<em style="color: red;">*</em>订单号：<input type='text' name="model.code" value="${model.code}" maxlength="50" class="inputStyle w200 required"/>
			</div>
			<div>
		     	<em style="color: red;">*</em>金额：<input type='text' name="model.money" value="${model.money}" maxlength="50" class="inputStyle w200 required"/>
			</div>
			<div>
		     	<em style="color: red;">*</em>客户：<input type='text' name="model.customer.name" value="${model.customer.name}" maxlength="50" class="inputStyle w200 required"/>
			</div>
			<div>
		     	<em style="color: red;">*</em>日期：<input type='text' name="model.date" value="${model.date}" maxlength="50" class="inputStyle w200 required"/>
			</div>
			<div>
		     	<em style="color: red;">*</em>状态：<input type='text' name="model.state" value="${model.state}" maxlength="50" class="inputStyle w200 required"/>
			</div>
		</form>
	</body>
</html>