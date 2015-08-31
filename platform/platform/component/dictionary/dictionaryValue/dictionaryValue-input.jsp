<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp"%>
<script>
Namespace.register('com.ue.platform.module.dictvalue.input');
com.ue.platform.module.dictvalue.input={
	pageId:"${pageId}",
	doAdd:function(){
		this.saveSubmit("add");
	},
	doEdit:function(){
		this.saveSubmit("edit");
	},
	saveSubmit:function(option){
		if(!$("#${pageId}theForm").valid()){
			return;
		}
 		var formData=$("#${pageId}theForm").serialize();
		 $.ajax({
		 	type: "POST",
		  	url: "${path}/sys/dictionaryValue/save",
		  	processData:true,
		  	data:formData,
		  	success: function(data){
				com.ue.platform.module.dictvalue.refresh();
		   		art.dialog.list[com.ue.platform.module.dictvalue.pageId].close();
		  	}
		 });
	},
	init:function(){
		var validateOptions = {
				messages : {
					"model.value" : {
						remote : "字典值存在，请重新输入！"
					},
					"model.code" : {
						remote : "字典编码存在，请重新输入！"
					}
				}
			}
		$.hz.validate.init("${pageId}theForm",validateOptions);
	}
};

$(document).ready(function() {
	com.ue.platform.module.dictvalue.input.init();
})
</script>
		<uc:form id="${pageId}theForm" method="post">
			<c:if test="${not empty model.id}">
			  	<uc:hidden name="model.id" value="${model.id}"/>
			  	<uc:hidden name="model.enableFlag" value="${model.enableFlag}"/>
			  	<uc:hidden name="model.num" value="${model.num}"/>
			</c:if>
			<div class="formLayout01">
		
				<div class="formItem">
				     <label class="tit w3">字典条目</label>
				     <div class="bdmain detail_right w6">
				     	<uc:hidden name="model.dictionary.id" value="${model.dictionary.id}"/>
				     	${model.dictionary.name}
				     </div>
				</div>
	       
				<div class="formItem">
				     <label class="tit w3"><em>*</em>字典值显示</label>
				     <div class="bdmain w6">
				     	<uc:textfield name="model.value" value="${model.value}" maxlength="25" required="true" cssClass="inputStyle" remote="${path}/sys/dictionaryValue/ajaxCheckValue?dictId=${model.dictionary.id}&excludeValue=${model.value}"/>
				     </div>
				</div>
	       
				<div class="formItem">
				     <label class="tit w3"><em>*</em>字典值编码</label>
				     <div class="bdmain w6">
				     	<uc:hidden id="${pageId}codeOld" name="codeOld" value="${model.code}"/>
						<uc:textfield id="${pageId}code" name="model.code" value="${model.code}" maxlength="20" required="true" remote="${path}/sys/dictionaryValue/ajaxCheckCode?dictId=${model.dictionary.id}&excludeCode=${model.code}" cssClass="inputStyle"/>
				     </div>
				</div>
				
				<div class="formItem">
				     <label class="tit w3">字典值英文</label>
				     <div class="bdmain w6">
				     	<uc:textfield name="model.valueI18n" value="${model.valueI18n}" maxlength="100" cssClass="inputStyle"/>
				     </div>
				</div>
			</div>
		</uc:form>
