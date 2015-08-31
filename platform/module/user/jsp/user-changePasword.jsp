<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
	<head>  
		<script type="text/javascript">
			Namespace.register("com.yunt.user.changePassword");
			com.yunt.user.changePassword = {
				doInput : function(dialogId) {
					if(!$("#${pageId}theForm").valid()){
						return false;
					}
					$.ajax({
					 	type: "POST",
					  	url: "${path}/user/changePassword?id=${model.id}&password="+$("#${pageId}password").val(),
					  	processData:true,
					  	success: function(data){
					  		$.successTips();
					   		art.dialog.list[dialogId].close();
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
			<div style="padding: 20px;">
				<div class="pl100 lh50">
			     	<label class="labelTitle"><em style="color: red;">*</em>新密码:</label>
			     	<input id="${pageId}password" name="password" type='text' maxlength="50" class="required"/>
				</div>
				<div class="pl100 lh50">
			     	<label class="labelTitle"><em style="color: red;">*</em>确认密码:</label>
			     	<input id="${pageId}comfirmPassword" name="comfirmPassword" type='text' maxlength="50" class="required"/>
				</div>
			</div>
		</form>
	</body>
</html>
