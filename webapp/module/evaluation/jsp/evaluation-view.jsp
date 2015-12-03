<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
	<head>  
		<script type="text/javascript">
			Namespace.register("com.yunt.evaluation.view");
			com.yunt.evaluation.evaluationInput = {
				doInput : function(dialogId) {
					if(!$("#${pageId}theForm").valid()){
						return false;
					}
					/* if(!$.hz.swfupload.validateAttachment("fileDiv")){
						return false;
					} */
					var formData = $("#${pageId}theForm").serialize();
					$.ajax({
					 	type: "POST",
					  	url: "${path}/evaluation/saveOrUpdate",
					  	data: formData,
					  	success: function(){
					  		$.successTips();
					   		art.dialog.list[dialogId].close();
					  	}
					});
				}
			};
			$(document).ready(function() {
				$.hz.validate.init("${pageId}theForm");
				/* <c:if test="${empty model.id}">
					$.hz.swfupload.init("fileDiv",{classFieldName:"com.unitever.module.goods.model.Goods,id",required:true});
				</c:if>
				<c:if test="${not empty model.id}">
					$.hz.swfupload.init("fileDiv",{classFieldName:"com.unitever.module.goods.model.Goods,id",idOwner:"${model.id}",required:true});
				</c:if> */
			});
		</script>
	</head>
	<body>
		<div style="padding: 20px;">
			<div class="pl100 lh50">
		     	<label class="labelTitle"><em style="color: red;">*</em>任务名称：</label>
		     	<div class="view_detail2">${model.task.title}</div>
			</div>
			<div class="pl100 lh50">
		     	<label class="labelTitle"><em style="color: red;">*</em>评价人:</label>
		     	<div class="view_detail2">${model.publisher.name}</div>
			</div>
			<div class="pl100 lh50">
		     	<label class="labelTitle"><em style="color: red;">*</em>被评价人:</label>
		     	<div class="view_detail2">${model.receiver.name}</div>
			</div>
			<div class="pl100 lh50">
		     	<label class="labelTitle"><em style="color: red;">*</em>评价内容:</label>
		     	<div class="view_detail2">${model.content}</div>
			</div>
			<div class="pl100 lh50">
		     	<label class="labelTitle"><em style="color: red;">*</em>评价日期:</label>
		     	<div class="view_detail2">
		     		<fmt:formatDate value="${model.createTime}" type="date" pattern="yyyy-MM-dd"/>
		     	</div>
			</div>
			<div class="pl100 lh50">
		     	<label class="labelTitle"><em style="color: red;">*</em>评价星级:</label>
		     	<div class="view_detail2">${model.level}</div>
			</div>
		</div>
	</body>
</html>