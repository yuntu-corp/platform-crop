<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
	<head>  
		<script type="text/javascript">
			Namespace.register("com.yunt.level.input");
			com.yunt.level.input = {
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
					  	url: "${path}/level/saveOrUpdate?inputKind=${inputKind}",
					  	data: formData,
					  	success: function(data){
					  		$.successTips();
					   		art.dialog.list[dialogId].close();
					   		com.yunt.level.index.refresh();
					  	}
					});
				}
			};
		</script>
	</head>
	<body>
		<form id="${pageId}theForm" method="post" class="BB">
			<input type="hidden" name="model.id" value="${model.id}" />
			<input type="hidden" name="model.name" value="${model.name}" />
			<input type="hidden" name="model.kind" value="${model.kind}" />
			<input type="hidden" name="model.level" value="${model.level}" />
			<input type="hidden" name="model.user.id" value="${model.user.id}" />
			<div style="padding: 10px;">
				<div class="pl100 lh50">
			     	<label class="labelTitle">名称:</label>
			     	<div class="view_detail">
			     		${model.name}
			     	</div>
				</div>
				<div class="pl100 lh50">
			     	<label class="labelTitle">类型:</label>
			     	<div class="view_detail">
			     		${model.kindValue}
			     	</div>
				</div>
				<div class="pl100 lh50">
			     	<label class="labelTitle">层级:</label>
			     	<div class="view_detail">
			     		${model.level}
			     	</div>
				</div>
				<div class="pl100 lh50">
			     	<label class="labelTitle"><em style="color: red;">*</em>比列:</label>
			     	<input type='text' name="model.proportion" value="${model.proportion}" maxlength="50" class="required"/>
				</div>
			</div>
		</form>
	</body>
</html>