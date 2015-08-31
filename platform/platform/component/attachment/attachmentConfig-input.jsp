<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<script>
	Namespace.register('com.ue.platform.module.attachment.attachmentConfig.input');
	com.ue.platform.module.attachment.attachmentConfig.input = {
		doAdd : function(dialogId){
			if(!$.hz.validate.valid("${pageId}theForm")){
				return;
			}
			$.post("${path}/sys/attachmentConfig/ajaxCheckRepeatName.do",{code:$("#code${pageId}").val(),id:'${model.id}'},function(data){
				if(data == "OK"){
					var formData=$("#${pageId}theForm").serialize();
					 $.ajax({
					 	type: "POST",
					  	url: "${path}/sys/attachmentConfig/save.do",
					  	data:formData,
					  	success: function(data){
					  		$.successTips();
					   		art.dialog.list[dialogId].close();
					  	}
					 });
				}else{
					alert("编码已存在，请修改");
					$("#code${pageId}").focus();
				}
			},'text');
		}
	}

	$(document).ready(function() {
		$.hz.validate.init("${pageId}theForm");
	});
</script>
<uc:form action="attachmentConfig!save.action" id="${pageId}theForm" method="post">
	<uc:hidden name="model.id" value="${model.id }"></uc:hidden>
	<div class="formLayout01">
		<div class="formItem">
		     <label class="tit"><em>*</em>编码</label>
		     <div class="bdmain">
		     	<c:if test="${model.code eq 'default' }">
	     		<uc:textfield id="code${pageId}" name="model.code" value="${model.code}" maxlength="25" cssClass="inputStyle" required="true" readonly="true" />
	     		</c:if>
		     	<c:if test="${model.code ne 'default' }">
	     		<uc:textfield id="code${pageId}" name="model.code" value="${model.code}" maxlength="25" required="true" cssClass="inputStyle" />
	     		</c:if>
	     		编码确定后请勿修改
		     </div>
		</div>
		<div class="formItem">
		     <label class="tit"><em>*</em>保存目录</label>
		     <div class="bdmain">
		     	<uc:textfield name="model.saveDir" value="${model.saveDir}" maxlength="100" required="true" cssClass="inputStyle"/>已存在附件后请勿修改。直接填写绝对路径，例如：c:/temp/aaa/
		     </div>
		</div>
		<div class="formItem">
		     <label class="tit"><em>*</em>目录层级</label>
		     <div class="bdmain">
		     	<uc:select name="model.dirLevel" value="${model.dirLevel }" list="{1:'/年/',2:'/年/月/',3:'/年/月/日/' }" cssClass="selectStyle w200"></uc:select>已存在附件后请勿修改。
		     </div>
		</div>
		<div class="formItem">
		     <label class="tit">文件大小</label>
		     <div class="bdmain">
		     	<uc:integer name="model.fileSize" value="${model.fileSize}" cssClass="inputStyle"></uc:integer>单位为：M，不填写表示不限制
		     </div>
		</div>
		<div class="formItem">
		     <label class="tit">文件个数</label>
		     <div class="bdmain">
		     	<uc:integer name="model.fileCount" value="${model.fileCount}" cssClass="inputStyle"></uc:integer>不填写表示不限制
		     </div>
		</div>
		<div class="formItem">
		     <label class="tit">文件扩展名</label>
		     <div class="bdmain">
		     	<uc:textfield name="model.extension" value="${model.extension}" maxlength="100" cssClass="inputStyle"/>使用分号（;）分割，不填写表示不限制。例如：gif;txt;doc;
		     </div>
		</div>
		<div class="formItem">
		     <label class="tit">文件描述</label>
		     <div class="bdmain">
		     	<uc:textfield name="model.description" value="${model.description}" maxlength="100" cssClass="inputStyle"/>
		     </div>
		</div>
	</div>
</uc:form>