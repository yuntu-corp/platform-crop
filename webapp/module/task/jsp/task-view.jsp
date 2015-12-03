<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
	<head>  
		<script type="text/javascript">
			Namespace.register("com.yunt.task.view");
			com.yunt.task.taskInput = {
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
					  	url: "${path}/task/saveOrUpdate",
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
		     	<label class="labelTitle"><em style="color: red;">*</em>标题：</label>
		     	<div class="view_detail2">${model.title}</div>
			</div>
			<div class="pl100 lh50">
		     	<label class="labelTitle"><em style="color: red;">*</em>任务类型：</label>
		     	<div class="view_detail2">${model.taskType.typeName}</div>
			</div>
			<div class="pl100 lh50">
		     	<label class="labelTitle"><em style="color: red;">*</em>任务描述:</label>
		     	<div class="view_detail2">${model.content}</div>
			</div>
			<div class="pl100 lh50">
		     	<label class="labelTitle"><em style="color: red;">*</em>发布人:</label>
		     	<div class="view_detail2">${model.publisher.name}</div>
			</div>
			<c:if test="${model.status ne '0' }">
				<div class="pl100 lh50">
			     	<label class="labelTitle"><em style="color: red;">*</em>接受人:</label>
			     	<div class="view_detail2">${model.receiver.name}</div>
				</div>
			</c:if>
			<div class="pl100 lh50">
		     	<label class="labelTitle"><em style="color: red;">*</em>任务金币:</label>
		     	<c:if test="${model.status eq '0' }">
			     	<div class="view_detail2">${model.bitcoinConsume}</div>
		     	</c:if>
		     	<c:if test="${model.status ne '0' }">
			     	<div class="view_detail2">${model.finalBitcoin}</div>
		     	</c:if>
			</div>
			<div class="pl100 lh50">
		     	<label class="labelTitle"><em style="color: red;">*</em>发布日期:</label>
		     	<div class="view_detail2">
		     		<fmt:formatDate value="${model.createTime}" type="date" pattern="yyyy-MM-dd"/>
		     	</div>
			</div>
			<div class="pl100 lh50">
		     	<label class="labelTitle"><em style="color: red;">*</em>结束日期:</label>
		     	<div class="view_detail2">
			     	<fmt:formatDate value="${model.finishDate}" type="date" pattern="yyyy-MM-dd"/>
		     	</div>
			</div>
			<div class="pl100 lh50">
		     	<label class="labelTitle"><em style="color: red;">*</em>任务状态:</label>
		     	<div class="view_detail2">${model.statusVal}</div>
			</div>
		</div>
	</body>
</html>