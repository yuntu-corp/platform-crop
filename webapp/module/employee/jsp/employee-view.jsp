<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
	<head>  
		<script type="text/javascript">
			Namespace.register("com.yunt.employee.employeeInput");
			com.yunt.employee.employeeInput = {
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
					  	url: "${path}/employee/saveOrUpdate",
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
		     	<label class="labelTitle"><em style="color: red;">*</em>姓名：</label>
		     	<div class="view_detail2">${model.name}</div>
			</div>
			<%-- <div class="pl100 lh50">
		     	<label class="labelTitle"><em style="color: red;">*</em>职位:</label>
		     	<div class="view_detail2">${model.wxCpUser.position}</div>
			</div> --%>
			<div class="pl100 lh50">
		     	<label class="labelTitle"><em style="color: red;">*</em>每月基础币:</label>
		     	<div class="view_detail2"><fmt:formatNumber type="number" value="${model.baseBitcoin}" maxFractionDigits="2"/></div>
			</div>
			<div class="pl100 lh50">
		     	<label class="labelTitle"><em style="color: red;">*</em>当月消耗币:</label>
		     	<div class="view_detail2"><fmt:formatNumber type="number" value="${model.bitcoinConsume}" maxFractionDigits="2"/></div>
			</div>
			<div class="pl100 lh50">
		     	<label class="labelTitle"><em style="color: red;">*</em>当月收入币:</label>
		     	<div class="view_detail2"><fmt:formatNumber type="number" value="${model.bitcoinIncome}" maxFractionDigits="2"/></div>
			</div>
			<div class="pl100 lh50">
		     	<label class="labelTitle"><em style="color: red;">*</em>当前余额:</label>
		     	<div class="view_detail2"><fmt:formatNumber type="number" value="${model.bitcoinSurplus}" maxFractionDigits="2"/></div>
			</div>
			<div class="pl100 lh50">
		     	<label class="labelTitle"><em style="color: red;">*</em>总体评价:</label>
		     	<div class="view_detail2">${model.evaluationLevel}</div>
			</div>
		</div>
	</body>
</html>