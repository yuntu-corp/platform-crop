<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
	<head>  
		<script type="text/javascript">
			Namespace.register("com.yunt.taskType.input");
			com.yunt.taskType.input = {
				doInput : function(dialogId) {
					if(!$("#${pageId}theForm").valid()){
						return false;
					}
					var formData = $("#${pageId}theForm").serialize();
					$.ajax({
					 	type: "POST",
					  	url: "${path}/taskType/saveOrUpdate?inputKind=${inputKind}",
					  	data: formData,
					  	success: function(date){
					  		$.successTips();
					   		art.dialog.list[dialogId].close();
					   		com.yunt.taskType.index.refresh();
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
		<div style="padding: 20px;">
			<form action="" method="" id="${pageId}theForm" class="BB">
				<div class="pl100 lh50">
			     	<label class="labelTitle"><em style="color: red;">*</em>类型标识：</label>
			     	<input type="hidden" name="model.id" value="${model.id }"  />
			     	<input type="hidden" name="model.state" value="${model.state }"  />
			     	<input type="text" name="model.typeKey" value="${model.typeKey }" class="required" maxlength="50"/>
				</div>
				<div class="pl100 lh50">
			     	<label class="labelTitle"><em style="color: red;">*</em>类型名称:</label>
					<input type="text" name="model.typeName" value="${model.typeName }" class="required" />
				</div>
			</form>
		</div>
	</body>
</html>