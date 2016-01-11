<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
	<head>  
		<script type="text/javascript">
			Namespace.register("com.yunt.distribute.input");
			com.yunt.distribute.input = {
				doInput : function(dialogId) {
					 if(!$("#theForm").valid()){
						return false;
					}
					var formData = $("#theForm").serialize();
					$.ajax({
					 	type: "POST",
					  	url: "${path}/distribute/doDistribute",
					  	data: formData,
					  	success: function(date){
					  		$.successTips();
					   		art.dialog.list[dialogId].close();
					   		com.yunt.distribute.index.refresh();
					  	}
					});
				}
			};
			$(document).ready(function() {
				$.hz.validate.init("#theForm");
				//$("${pageId}theForm").Validform();
			});
		</script>
	</head>
	<body>
		<div style="padding: 20px;">
			<form action="" method="" id="theForm" class="BB">
				<div class="pl120 lh50">
			     	<label class="labelTitle"><em style="color: red;">*</em>分发虚拟币：</label>
			     	<input type="hidden" name="model.employeeId" value="${employeeId}"  />
			     	<input type="text" name="model.bitcoin"  class="required" maxlength="15"  decimal2="true"/>
				</div>
				<div class="pl120 lh50">
			     	<label class="labelTitle"><em style="color: red;">*</em>理由：</label>
					<textarea id="model.reason"   class="txtfield required"  name="model.reason" style="width: 300px;padding-left:10px" placeholder="请输入分发虚拟币原因" minlength="5"  maxlength="100" />
				</div>
			</form>
		</div>
	</body>
</html>