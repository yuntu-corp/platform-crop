<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp"%>
<html>
<head>
<script type="text/javascript">
Namespace.register("com.yunt.employee.employeeInput");
com.yunt.employee.employeeInput = {
	doInput : function(dialogId) {
		if(!$("#${pageId}theForm").valid()){
			return false;
		}
		var formData = $("#${pageId}theForm").serialize();
		$.ajax({
		 	type: "POST",
		  	url: "${path}/employee/doSave",
		  	data: formData,
		  	success: function(data){
		  		if("success"==data){
		  			$.successTips();
			   		art.dialog.list[dialogId].close();
			   		com.yunt.employee.index.refresh();
		  		} else {
		  			alert(data);
		  		}
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
		<input type="hidden" name="model.bitcoinIncome" value="0">
		<input type="hidden" name="model.bitcoinConsume" value="0">
		<input type="hidden" name="model.status" value="1">
		<input type="hidden" name="model.evaluationLevel" value="0">
		<input type="hidden" name="model.taskCount" value="0">
		<input type="hidden" name="model.publishTaskCount" value="0">
		<div style="padding: 20px;">
			<div class="pl100 lh50">
		     	<label class="labelTitle"><em style="color: red;">*</em>姓名：</label>
		     	<input id="${pageId}name" name="model.name" type='text' isChinese="true"   maxlength="64" class="required" />
			</div>
			<div class="pl100 lh50">
		     	<label class="labelTitle"><em style="color: red;">*</em>性别：</label>
		     	<uc:select id="${pageId}level" list="{1:'男',2:'女'}" name="model.wxCpUser.gender" cssClass="search" />
			</div>
			<div class="pl100 lh50">
		     	<label class="labelTitle"><em style="color: red;">*</em>部门：</label>
		     	<div class="view_detail2" style="width: 315px;">
			     	<c:forEach items="${model }" var="depart" varStatus="i">
						<span >
							<input type="checkbox"  style="width: auto;height: auto;" value="${depart.id  }" name="departStr" required="true"/>
							<label class="black">${depart.name}</label>
						</span>
						<c:if test="${i.count%4==0 }"><br/></c:if>
	    			</c:forEach>
		     	</div>
			</div>
			<div class="pl100 lh50">
		     	<label class="labelTitle"><em style="color: red;">*</em>员工ID：</label>
		     	<input id="${pageId}name" name="model.userId" type='text' ennum="true" maxlength="64" class="required" />
			</div>
			<div class="pl100 lh50">
		     	<label class="labelTitle"><em style="color: red;">*</em>职位：</label>
		     	<input id="${pageId}position" name="model.wxCpUser.position" type='text' maxlength="64" class="required" />
			</div>
			<div class="pl100 lh50">
		     	<label class="labelTitle"><em style="color: red;">*</em>微信账号：</label>
		     	<input id="${pageId}name" name="model.wxCpUser.weiXinId" type='text' class="required" ennum="true"/>
			</div>
			<div class="pl100 lh50">
		     	<label class="labelTitle"><em style="color: red;">*</em>手机号：</label>
		     	<input id="${pageId}name" name="model.wxCpUser.mobile" type='text' class="required" isMobile="true"/>
			</div>
			<div class="pl100 lh50">
		     	<label class="labelTitle"><em style="color: red;">*</em>邮箱：</label>
		     	<input id="${pageId}name" name="model.wxCpUser.email" type='text' maxlength="64" class="required email" />
			</div>
			<div class="pl100 lh50">
		     	<label class="labelTitle"><em style="color: red;">*</em>每月基础币：</label>
		     	<input id="${pageId}baseBitcoin" name="model.baseBitcoin" type='text' maxlength="50" class="required" digits="true" />
			</div>
		</div>
	</form>
</body>
</html>