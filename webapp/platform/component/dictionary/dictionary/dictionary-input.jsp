<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>

<script>
Namespace.register('com.ue.platform.module.dict.input');
com.ue.platform.module.dict.input={
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
		  	url: "${path}/sys/dictionary/save",
		  	processData:true,
		  	data:formData,
		  	success: function(id){
		  		var dictType=null;
		  		<c:if test="${empty model.id}">
					dictType=$("select[name='model.kind']").val();
				</c:if>
				<c:if test="${not empty model.id}">
					dictType="${model.kind}";
				</c:if>
				com.ue.platform.module.dict.refresh(dictType,id);
		  	}
		 });
	},
	init:function(){
		var validateOptions = {
				errorPlacement: function(error, element){
					if(element.is(":radio")){
						error.appendTo(element.parent("td").next());
					}else{
						error.insertAfter(element);
					}
				},
				messages : {
					"model.code" : {
						remote : "字典条目编码已存在，请重新输入！"
					}
				}
			}
		$.hz.validate.init("${pageId}theForm",validateOptions);
	}
};

$(document).ready(function() {
	com.ue.platform.module.dict.input.init();
})
</script>
		<uc:form  action="dict!save.action" id="${pageId}theForm" method="post">
			<c:if test="${not empty model.id}">
			  	<uc:hidden name="model.id" value="${model.id}"></uc:hidden>
			</c:if>
			<div class="formLayout01">
				<div class="formItem">
			    	<label class="tit w3"><em>*</em>字典类型</label>
		     		<c:if test="${empty model.id}">
			     		<div class="bdmain w6">
							<uc:select name="model.kind" list="${DictKindMap}"  cssClass="selectStyle"/>
						</div>
					</c:if>
					<c:if test="${not empty model.id}">
						<div class="bdmain detail_right w6">
							<uc:hidden name="model.kind" value="${model.kind}"/>
							&nbsp;${DictKindMap[model.kind]}
					     </div>
					</c:if>
				</div>
	       
				<div class="formItem">
				     <label class="tit w3"><em>*</em>字典条目名称</label>
				     <div class="bdmain w6">
				     	<uc:textfield name="model.name" value="${model.name}" maxlength="100" required="true" cssClass="inputStyle w8"/>
				     </div>
				</div>
	       
				<div class="formItem">
				     <label class="tit w3"><em>*</em>字典条目编码</label>
				     <div class="bdmain w6">
				     	<uc:textfield id="${pageId}code" name="model.code" value="${model.code}" maxlength="20" required="true" remote="${path}/sys/dictionary/ajaxCheckCode?excludeCode=${model.code}" cssClass="inputStyle w8"/>
				     </div>
				</div>
			
				<div class="formItem">
				     <label class="tit w3"><em>*</em>是否允许编辑字典值</label>
				     <div class="bdmain w6">
				     	<div class="selStyle fl h30 lh30 w8">
				     		<uc:radiolist list="${canEditFlagMap}" name="model.editableFlag" required="true" value="${model.editableFlag}" cssClass="radioStyle"/>
				     	</div>
				     </div>
				</div>
			</div>
		</uc:form>
		<div class="formButton">
			<c:if test="${empty model.id}">
				<a href="javascript:void(0);" class="button" onclick="com.ue.platform.module.dict.input.doAdd();">保存</a>
			</c:if>
			<c:if test="${not empty model.id}">
				<a href="javascript:void(0);" class="button" onclick="com.ue.platform.module.dict.input.doEdit();">保存</a>
			</c:if>
		</div>
