<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp"%>
<script>
Namespace.register('com.ue.platform.module.dictvaluetree.input');
com.ue.platform.module.dictvaluetree.input={
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
		  	url: "${path}/sys/dictionaryValue/saveTree?option="+option,
		  	processData:true,
		  	data:formData,
		  	success: function(id){
				com.ue.platform.module.dictvaluetree.refresh(id);
		   		art.dialog.list[com.ue.platform.module.dictvaluetree.pageId].close();
		  	}
		 });
	},
	selParent:function(setIdHead){
		$.dialog.open("${path}/sys/dictionaryValue/getParentTree?idHead="+setIdHead+"&dictId=${model.dictionary.id}&isFullPath=true&hideId=${model.id}",{
				title : '父级字典值',
				width: 300,
				height: 500,
				fixed: true,
				id: 'dictTree',
				lock: true,
				ok: function(){
					var myObj = this.iframe.contentWindow;
					myObj.ok();
			    },
			    cancel: function(){
			    	var myObj = this.iframe.contentWindow;
					myObj.clearInput();
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
	com.ue.platform.module.dictvaluetree.input.init();
})
</script>
		<uc:form action="dictValue!save" id="${pageId}theForm" method="post">
			<s:token />
			<c:if test="${not empty model.id}">
				<uc:hidden name="model.id" value="${model.id}"/>
			  	<uc:hidden name="model.enableFlag" value="${model.enableFlag}"/>
			  	<uc:hidden name="model.num" value="${model.num}"/>
			</c:if>
			<div class="formLayout01">
		
				<div class="formItem">
				     <label class="tit">字典条目</label>
				     <div class="bdmain detail_right w6">
				     	<uc:hidden name="model.dictionary.id" value="${model.dictionary.id}"/>
				     	${model.dictionary.name}
				     </div>
				</div>
	       
				<div class="formItem">
				     <label class="tit"><em>*</em>字典值显示</label>
				     <div class="bdmain">
				        	<uc:hidden id="${pageId}valueOld" name="" value="${model.value}"/>
							<uc:textfield id="${pageId}value" name="model.value" value="${model.value}" maxlength="25" required="true" remote="${path}/sys/dictionaryValue/ajaxCheckValue?dictId=${model.dictionary.id}&excludeValue=${model.value}" cssClass="inputStyle"/>
				     </div>
				</div>
	       
				<div class="formItem">
				     <label class="tit"><em>*</em>字典值编码</label>
				     <div class="bdmain">
						 <uc:textfield id="${pageId}code" name="model.code" value="${model.code}" maxlength="20" required="true" remote="${path}/sys/dictionaryValue/ajaxCheckCode?dictId=${model.dictionary.id}&excludeCode=${model.code}" cssClass="inputStyle"/>
				     </div>
				</div>
			
				<div class="formItem">
				     <label class="tit">父级节点</label>
				     <div class="bdmain">
				         	<uc:hidden id="parentValue" name="model.parentId" value="${model.parentId}"/>
				         	<c:if test="${not empty model.parentId}">
								<input type="text" id="parentText"　name="parentName"
									value="" class="inputStyle"  style="float:left;"/>
				         	</c:if>
				         	<c:if test="${empty model.parentId}">
								<input type="text" id="parentText"　name="parentName"
									value="顶级" class="inputStyle"  style="float:left;"/>
				         	</c:if>
							<a href="#" class="button btnWhite"  onclick="com.ue.platform.module.dictvaluetree.input.selParent('parent');" style="float:left;">选择</a>
				     </div>
				</div>
			</div>
		</uc:form>
