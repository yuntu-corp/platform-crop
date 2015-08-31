<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp"%>
<script type="text/javascript">
	Namespace.register('com.ue.platform.module.user');
	com.ue.platform.module.user = {
		doAdd : function(){
			if(!$('#${pageId }theForm').validationEngine("validate")){
				return false;
			}
			if(!$("#${pageId}fileDiv").swfupload("validateAttachment")){
				return false;
			}
			var formData=$("#${pageId}theForm").serialize();
			$.ajax({
			 	type: "POST",
			  	url: "${path}/dm/user/ajaxSave.do",
			  	data:formData,
			  	success: function(data){
			  		$.successTips();
			  	}
			});
		}
	}
	
	$(document).ready(function() {
		$('#${pageId}theForm').validationEngine();
		<c:if test="${empty model.id}">
			$("#${pageId }fileDiv").swfupload({classFieldName:"com.unitever.platform.demo.model.User,id"});
		</c:if>
		<c:if test="${not empty model.id}">
			$("#${pageId }fileDiv").swfupload({classFieldName:"com.unitever.platform.demo.model.User,id",idOwner:"${model.id}"});
		</c:if>
	})
</script>
<div class="innerWrap" style="bottom:50px!important;">
	<uc:form action="${path}/component/attachmentSetting/save.do" id="${pageId}theForm" method="post">
		<c:if test="${not empty model.id}">
			<uc:hidden name="model.id" value="${model.id}"></uc:hidden>
		</c:if>
		<div class="formLayout01">
			<div class="formItem">
			     <label class="tit">名称：</label>
			     <div class="bdmain">
			     	<uc:textfield name="model.name" value="${model.name}" maxlength="250" cssClass="inputStyle w200"/>
			     </div>
			</div>
			<div class="formItem">
			     <label class="tit">email：<em class="red">*</em></label>
			     <div class="bdmain">
			     	<uc:textfield name="model.email" value="${model.email}" maxlength="250" cssClass="inputStyle w200"/>
			     </div>
			</div>
			<div class="formItem">
			     <label class="tit">附件：<em class="red">*</em></label>
<%-- 			     <uc:attachmentUpload id="${pageId}fileDiv" model="${model}" fieldName="id"></uc:attachmentUpload> --%>
			     <div class="bdmain" id="${pageId }fileDiv">
			     
			     </div>
			</div>
			<div class="formButton">
				<a onclick="com.ue.platform.module.user.doAdd();" class="button" href="javascript:void(0);">保存</a>
			</div>
		</div>
	</uc:form>
	<c:if test="${not empty model.id}">
		<uc:attachmentUrl var="attachments" model="${model}" ></uc:attachmentUrl>
		<c:if test="${not empty attachments}">
			<c:forEach items="${attachments}" var="attachment">
				<a href="${attachment.downloadUrl}" >${attachment.name}</a>
			</c:forEach>
		</c:if>
		
		<br/>
		<uc:attachmentView model="${model}" ></uc:attachmentView>
	</c:if>
</div>