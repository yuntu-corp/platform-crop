<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>

<div id="tipboxDiv" class="container">
	<div class="succes-big"></div>
	<div class="error-big"></div>
</div>
<script>
	$(document).ready(function(){
		$("#tipboxDiv").tipbox();
	});
</script>