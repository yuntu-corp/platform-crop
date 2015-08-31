<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>

<div class="container" style="margin-top: 10px;margin-left: 10px;margin-right: 10px;width: 90%">
	<script id="editor" type="text/plain" style="width:600px;height:300px;"></script>
</div>
<script>
	$(document).ready(function(){
// 		UE.getEditor('editor');
		$("#editor").ueditor({
			name:"model.name",
			mode:"simple"	//default,simple
		});
	});
</script>