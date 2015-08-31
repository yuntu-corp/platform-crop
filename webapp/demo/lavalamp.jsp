<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>

<div id="lavalamp" class="container box_header">
	<h2 class="box_title tc">教材</h2>
	<div class="box_nav">
		<ul>
			<li><a href="javascript:void(0);">教材库</a></li>
			<li><a href="javascript:void(0);">出版社</a></li>
		</ul>
	</div>
</div>
<div id="content" class="abs l0 b0 r0" style="top:65px;"></div>
<script>
	$(document).ready(function(){
		$("#lavalamp").find("div.box_nav ul").lavaLamp({speed : 200});
	});
</script>