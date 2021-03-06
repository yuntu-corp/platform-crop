<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
	<head>  
		<script type="text/javascript">
			Namespace.register("com.yunt.employee.updateBaseBitcoin");
			com.yunt.employee.updateBaseBitcoin = {
				doUpdateBaseBitcoin : function(dialogId) {
					if(!$("#${pageId}theForm").valid()){
						return false;
					}
					var formData = $("#${pageId}theForm").serialize();
					$.ajax({
					 	type: "POST",
					  	url: "${path}/employee/updateBaseBitcoin",
					  	data: formData,
					  	success: function(){
					  		$.successTips();
					   		art.dialog.list[dialogId].close();
					   		com.yunt.employee.index.refresh();
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
			<input type="hidden" name="model.id" value="${model.id}" />
			<input type="hidden" name="model.userId" value="${model.userId}" />
			<input type="hidden" name="model.headimgurl" value="${model.headimgurl}" />
			<input type="hidden" name="model.user.id" value="${model.user.id}" />
			<input type="hidden" name="model.name" value="${model.name}" />
			<div style="padding: 20px;">
				<div class="pl100 lh50">
			     	<label class="labelTitle"><em style="color: red;">*</em>姓名：</label>
			     	<div class="view_detail2">${model.name}</div>
			     	<%-- <input id="${pageId}name" name="model.name" type='text' maxlength="50" class="required" value="${model.name}"/>  --%>
				</div>
<%-- 				<div class="pl100 lh50">
			     	<label class="labelTitle"><em style="color: red;">*</em>职位:</label>
			     	<input id="${pageId}position" name="model.wxCpUser.position" type='text' maxlength="50" class="required" value="${model.wxCpUser.position}"/>
				</div> --%>
				<div class="pl100 lh50">
			     	<label class="labelTitle"><em style="color: red;">*</em>每月基础币:</label>
			     	<input id="${pageId}baseBitcoin" name="model.baseBitcoin" type='text' maxlength="50"  digits="true" class="required" value="<fmt:formatNumber type="number" value="${model.baseBitcoin}" maxFractionDigits="2"/>"/>
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
<%-- 				<div class="pl100 lh50">
			     	<label class="labelTitle"><em style="color: red;">*</em>员工状态:</label>
					<uc:select id="${pageId}status" value="${model.status}" list="{1:'正常',0:'删除'}" name="model.status" cssClass="search" headerKey="" headerValue="请选择" />
				</div> --%>
			</div>
		</form>
	</body>
</html>