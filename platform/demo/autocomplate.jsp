<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>

<div class="container">
	<uc:form>
		<input type="text" id="autocomplateId"></input>
		<input type="text" id="autocomplateIdResult"></input>
	</uc:form>
</div>

<script>
	$(document).ready(function(){
		$("#autocomplateId").autocomplete(autocomplateData,{
			formatMatch: function(row, i, max) {
				return row.match;//显示在输入框中的
			},
			formatItem: function(row, i, max){
				return row.viewName;
			},
			formatResult: function(row) {
				return row.viewName;
			}
		}).result(function(event, data, formatted) {
			$("#autocomplateIdResult").val(data.id);
		});
	});
</script>