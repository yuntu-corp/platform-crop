<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
	<head>  
		<script type="text/javascript">
			
		</script>
	</head>
	<body>
		<div style="padding: 20px;">
			<form action="" method="" id="theForm" class="BB">
				<div class="pl120 lh50">
			     	<label class="labelTitle"><em style="color: red;">*</em>分发虚拟币：</label>
			     	<input type="text" name="model.bitcoin"  class="required" value="${model.bitcoin }" disabled="disabled"/>
				</div>
				<div class="pl120 lh50">
			     	<label class="labelTitle"><em style="color: red;">*</em>理由：</label>
			     	<%-- <input type="text" name="model.reason"  class="required" value="${model.reason }" disabled="disabled"/> --%>
					<textarea   class="txtfield"  style="width: 300px;padding-left:10px"  disabled="disabled">${model.reason }</textarea>
				</div>
			</form>
		</div>
	</body>
</html>