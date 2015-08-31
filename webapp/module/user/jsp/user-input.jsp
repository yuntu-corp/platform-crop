<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
	<head>  
		<script type="text/javascript">
			Namespace.register("com.yunt.user.userInput");
			com.yunt.user.userInput = {
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
					  	url: "${path}/user/saveOrUpdate",
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
		<form id="${pageId}theForm" method="post" class="BB">
			<input type="hidden" name="model.id" value="${model.id}" />
			<input type="hidden" name="model.username" value="${model.username}" />
			<input type="hidden" name="model.password" value="${model.password}" />
			<input type="hidden" name="model.type" value="${model.type}" />
			<input type="hidden" name="model.accessToken" value="${model.accessToken}" />
			<input type="hidden" name="model.jsapiTicket" value="${model.jsapiTicket}" />
			<input type="hidden" name="model.expiresTime" value="${model.expiresTime}" />
			<div style="padding: 20px;">
				<div class="pl100 lh50">
			     	<label class="labelTitle"><em style="color: red;">*</em>名称：</label>
			     	<input id="${pageId}name" name="model.name" type='text' maxlength="50" class="required" value="${model.name}"/>
				</div>
				<div class="pl100 lh50">
			     	<label class="labelTitle"><em style="color: red;">*</em>微信号:</label>
			     	<input id="${pageId}weChatNum" name="model.weChatNum" type='text' maxlength="50" class="required" value="${model.weChatNum}"/>
				</div>
				<div class="pl100 lh50">
			     	<label class="labelTitle"><em style="color: red;">*</em>appId:</label>
			     	<input id="${pageId}appId" name="model.appId" type='text' maxlength="50" class="required" value="${model.appId}"/>
				</div>
				<div class="pl100 lh50">
			     	<label class="labelTitle"><em style="color: red;">*</em>appSecret:</label>
			     	<input id="${pageId}appSecret" name="model.appSecret" type='text' maxlength="50" class="required" value="${model.appSecret}"/>
				</div>
				<div class="pl100 lh50">
			     	<label class="labelTitle"><em style="color: red;">*</em>返单折扣:</label>
			     	<input id="${pageId}appSecret" name="model.discount" type='text' maxlength="50" class="required" value="${model.discount}"/>
				</div>
				<div class="pl100 lh50">
			     	<label class="labelTitle"><em style="color: red;">*</em>客服中心url:</label>
			     	<input id="${pageId}customerServiceUrl" name="model.customerServiceUrl" type='text' maxlength="128" class="required" value="${model.customerServiceUrl}"/>
				</div>
				<%-- <div class="pl100 lh50">
			     	<label class="labelTitle"><em style="color: red;">*</em>财富模式url:</label>
			     	<input id="${pageId}wealthModeUrl" name="model.wealthModeUrl" type='text' maxlength="128" class="required" value="${model.wealthModeUrl}"/>
				</div> --%>
				<div class="pl100 lh50">
			     	<label class="labelTitle"><em style="color: red;">*</em>企业文化url:</label>
			     	<input id="${pageId}businessCultureUrl" name="model.businessCultureUrl" type='text' maxlength="128" class="required" value="${model.businessCultureUrl}"/>
				</div>
				<!-- <div class="pl100 pr100">
					<div id="fileDiv"></div>
				</div> -->
			</div>
		</form>
	</body>
</html>