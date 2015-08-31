<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp"%>
<script type="text/javascript">
	Namespace.register('com.ue.platform.module.attachment.attachmentSetting');
	com.ue.platform.module.attachment.attachmentSetting = {
		doAdd : function(){
			if($('#${pageId }theForm').find("input[name='model.type']:checked").val() == "1"){
				if($.trim($("#${pageId }ftpAddress").val()) == ""){
					alert("请填写ftp地址！");
					return;
				}
			}
			if(!$('#${pageId }theForm').validationEngine("validate")){
				return false;
			}
			var formData=$("#${pageId}theForm").serialize();
			$.ajax({
			 	type: "POST",
			  	url: "${path}/sys/attachmentSetting/ajaxSave.do",
			  	data:formData,
			  	success: function(data){
			  		$.successTips();
			  	}
			});
		},
		doTest : function(){
			if($('#${pageId }theForm').find("input[name='model.type']:checked").val() == "1"){
				if($.trim($("#${pageId }ftpAddress").val()) == ""){
					alert("请填写ftp地址！");
					return;
				}
			}
			if(!$('#${pageId }theForm').validationEngine("validate")){
				return false;
			}
			$("body").mask("正在测试。。。");
			var formData=$("#${pageId}theForm").serialize();
			$.ajax({
			 	type: "POST",
			  	url: "${path}/sys/attachmentSetting/ajaxTest.do",
			  	data:formData,
			  	success: function(data){
			  		$("body").unmask();
			  		if(data == ""){
			  			alert("配置正确。");
			  		}else{
			  			alert(data);
			  		}
			  	}
			});
		}
	}
	
	$(document).ready(function() {
		$('#${pageId}theForm').validationEngine();
	})
</script>
<div class="innerWrap" style="bottom:50px!important;">
	<uc:form action="${path}/component/attachmentSetting/save.do" id="${pageId}theForm" method="post">
		<div class="formLayout01">
			<div class="formItem">
			     <label class="tit">禁止的文件类型：</label>
			     <div class="bdmain">
			     	<uc:textfield name="model.limitFileExtention" value="${model.limitFileExtention}" maxlength="250" cssClass="inputStyle w200"/>多个使用分号（;）分割
			     </div>
			</div>
			<div class="formItem">
			     <label class="tit">文件保存方式：<em class="red">*</em></label>
			     <div class="bdmain">
			     	<uc:radio required="true" fieldValue="0" label="本地保存" value="${model.type }" name="model.type"></uc:radio>
			     	<uc:radio required="true" fieldValue="1" label="ftp保存" value="${model.type }" name="model.type"></uc:radio>
			     </div>
			</div>
			<div class="formItem">
			     <label class="tit">ftp地址：</label>
			     <div class="bdmain">
			     	<uc:textfield id="${pageId }ftpAddress" name="model.ftpAddress" value="${model.ftpAddress}" maxlength="250" cssClass="inputStyle w200"/>
			     </div>
			</div>
			<div class="formItem">
			     <label class="tit">ftp端口：</label>
			     <div class="bdmain">
			     	<uc:integer name="model.ftpPort" value="${model.ftpPort}" cssClass="inputStyle w200" maxlength="10"/>默认为21
			     </div>
			</div>
			<div class="formItem">
			     <label class="tit">ftp路径：</label>
			     <div class="bdmain">
			     	<uc:textfield name="model.ftpPath" value="${model.ftpPath}" maxlength="500" cssClass="inputStyle w200"/>默认为根路径
			     </div>
			</div>
			<div class="formItem">
			     <label class="tit">ftp用户名：</label>
			     <div class="bdmain">
			     	<uc:textfield name="model.ftpUserName" value="${model.ftpUserName}" maxlength="50" cssClass="inputStyle w200"/>默认为匿名
			     </div>
			</div>
			<div class="formItem">
			     <label class="tit">ftp密码：</label>
			     <div class="bdmain">
			     	<uc:textfield name="model.ftpPassword" value="${model.ftpPassword}" maxlength="50" cssClass="inputStyle w200"/>
			     </div>
			</div>
			<div class="formButton">
				<a onclick="com.ue.platform.module.attachment.attachmentSetting.doTest();" class="button" href="javascript:void(0);">测试</a>
				<a onclick="com.ue.platform.module.attachment.attachmentSetting.doAdd();" class="button" href="javascript:void(0);">保存</a>
			</div>
		</div>
	</uc:form>
</div>