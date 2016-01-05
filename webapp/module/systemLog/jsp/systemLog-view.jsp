<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
	<head>  
		<script type="text/javascript">
			Namespace.register("com.yunt.systemLog.systemLogView");
			com.yunt.systemLog.systemLogView = {
			};
			$(document).ready(function() {
				$.hz.validate.init("${pageId}theForm");
			});
		</script>
	</head>
	<body>
		<div style="padding: 20px;">
			<div class="pl100 lh50">
		     	<label class="labelTitle"><em style="color: red;">*</em>操作人：</label>
		     	<div class="view_detail2">${model.operatorName}</div>
			</div>
			<div class="pl100 lh50">
		     	<label class="labelTitle"><em style="color: red;">*</em>操作动作：</label>
		     	<div class="view_detail2">${model.operateValue}</div>
			</div>
			<div class="pl100 lh50">
		     	<label class="labelTitle"><em style="color: red;">*</em>操作记录:</label>
		     	<div class="view_detail2">${model.content}</div>
			</div>
			<div class="pl100 lh50">
		     	<label class="labelTitle"><em style="color: red;">*</em>操作人类别:</label>
		     	<div class="view_detail2">${model.isAdminValue}</div>
			</div>
			<div class="pl100 lh50">
		     	<label class="labelTitle"><em style="color: red;">*</em>操作时间:</label>
		     	<div class="view_detail2"><fmt:formatDate value="${model.createTime}" type="date" pattern="yyyy-MM-dd hh:mm:ss"/></div>
			</div>
		</div>
	</body>
</html>