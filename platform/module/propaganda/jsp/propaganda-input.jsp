<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
	<head>  
		<script type="text/javascript">
			Namespace.register("com.yunt.propaganda.input");
			com.yunt.propaganda.input = {
				loadContent : function(href) {
					$("#${pageId}content").load(href);
				},
				doInput : function(dialogId) {
					if(!$("#${pageId}theForm").valid()){
						return false;
					}
					var formData = $("#${pageId}theForm").serialize();
					$.ajax({
					 	type: "POST",
					  	url: "${path}/propaganda/saveOrUpdate?inputKind=${inputKind}",
					  	data: formData,
					  	success: function(data){
					  		$.successTips();
					   		art.dialog.list[dialogId].close();
					   		com.yunt.propaganda.index.refresh();
					  	}
					});
				}
			};
		</script>
	</head>
	<body>
		<form id="${pageId}theForm" method="post" class="BB">
			<c:if test="${inputKind eq 'update'}">
				<input type="hidden" name="model.id" value="${model.id}" />
				<input type="hidden" name="user.id" value="${user.id}" />
			</c:if>
			<div style="padding: 20px;padding-left: 150px;">
				<uc:UEditor id="editor2" name="model.content" value="${model.content}" style="width:600px;height:300px;"></uc:UEditor>
				<%-- <div class="pl100 lh50">
			     	<label class="labelTitle"><em style="color: red;">*</em>内容:</label>
			     	<input type='text' name="model.content" value="${model.content}" maxlength="50" class="required"/>
			     	
				</div> --%>
			</div>
		</form>
	</body>
</html>