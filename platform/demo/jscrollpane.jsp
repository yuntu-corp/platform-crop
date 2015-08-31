<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>

<div class="container scroll-pane" id="scrollDivId" style="height: 300px">
	<ul>
		<c:forEach var="aa" begin="0" end="100">
		<li>${aa }</li>
		</c:forEach>
	</ul>
</div>

<script>
	$(document).ready(function(){
		$("#scrollDivId").jScrollPane();
	});
</script>