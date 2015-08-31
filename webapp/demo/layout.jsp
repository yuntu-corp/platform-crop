<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>

<div id="layoutPanel" class="container">
	<div class="ui-layout-center" style="background-color: #b0dcf6">
		center
	</div>
	<div class="ui-layout-west">
		west
	</div>
	<div class="ui-layout-north">
		north
	</div>
	<div class="ui-layout-south">
		south
	</div>
	<div class="ui-layout-east">
		east
	</div>
</div>
<script>
	$(document).ready(function(){
		$('#layoutPanel').layout({
			west__size: 50,
			south__size:50
		});
	})
</script>