<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
	<head>
    <title>平台</title>
    <%@ include file="/platform/common/jsp/baseJs.jsp"%>
	</head>
	<script type="text/javascript">
	
		function commit0(){
			var formData=$("#form0").serialize();
			 $.ajax({
			 	type: "POST",
			  	url: "${path}/dm/user/save.do",
			  	data:formData,
			  	success: function(data){
			  		alert(data);
			  	}
			 });
		}
		
		function commit1(){
			var formData=$("#form1").serialize();
			 $.ajax({
			 	type: "POST",
			  	url: "${path}/dm/user/save.do",
			  	data:formData,
			  	success: function(data){
			  		alert(data);
			  	}
			 });
		}
		
		function commit2(){
			var formData=$("#form2").serialize();
			 $.ajax({
			 	type: "POST",
			  	url: "${path}/dm/user/commitMap.do",
			  	data:formData,
			  	success: function(data){
			  		alert(data);
			  	}
			 });
		}
		
		function commit2_2(){
			var formData=$("#form2_2").serialize();
			 $.ajax({
			 	type: "POST",
			  	url: "${path}/dm/user/commitMap2.do",
			  	data:formData,
			  	success: function(data){
			  		alert(data);
			  	}
			 });
		}
		
		function addRow(){
			var count = $("#form3").find("input[type=text]").size();
			$("#form3").append("<input type='text' name='nameList[" + count + "]'>");	
		}
		
		function commit3(){
			var formData=$("#form3").serialize();
			 $.ajax({
			 	type: "POST",
			  	url: "${path}/dm/user/commitList.do",
			  	data:formData,
			  	success: function(data){
			  		alert(data);
			  	}
			 });
		}
		
		function commit4(){
			var formData=$("#form4").serialize();
			 $.ajax({
			 	type: "POST",
			  	url: "${path}/dm/user/commitUserList.do",
			  	data:formData,
			  	success: function(data){
			  		alert(data);
			  	}
			 });
		}
		
		function commit5(){
			var formData=$("#form5").serialize();
			 $.ajax({
			 	type: "POST",
			  	url: "${path}/dm/user/commitJson.do",
			  	data:formData,
			  	success: function(data){
			  		alert(data);
			  	}
			 });
		}
	</script>
	<body>
		<form id="form0">
			<input name="name" placeholder="名称">
			<input name="email" placeholder="email">
			<input name="dept.name" placeholder="部门名称">
			<input type="button" onclick="commit0()" value="提交">
		</form>
		<hr>
		<form id="form1">
			<input name="user.name" placeholder="名称">
			<input name="user.email" placeholder="email">
			<input name="user.dept.name" placeholder="部门名称">
			<input type="button" onclick="commit1()" value="model提交">
		</form>
		<hr>
		<form id="form2">
			<input name="valMap['name']" placeholder="名称">
			<input name="valMap['email']" placeholder="email">
			<input name="dept.name" placeholder="部门名称">
			<input type="button" onclick="commit2()" value="map提交">
		</form>
		<hr>
		<form id="form2_2">
			<input name="valMap['student'].name" placeholder="学生名称">
			<input name="valMap['student'].email" placeholder="学生email">
			<input name="valMap['student'].dept.name" placeholder="学生部门">
			<input name="valMap['teacher'].name" placeholder="教师名称">
			<input type="button" onclick="commit2_2()" value="map提交2">
		</form>
		<hr>
		<form id="form3">
			<input type="button" onclick="addRow()" value="添加">
			<input type="button" onclick="commit3()" value="提交简单List">
			<input type="text" name="nameList[0]">
		</form>
		<hr>
		<form id="form4">
			<input type="button" onclick="commit4()" value="提交复杂List">
			<input type="text" name="userList[0].name">
			<input type="text" name="userList[0].dept.name">
			<input type="text" name="userList[1].name">
			<input type="text" name="userList[1].dept.name">
			<input type="text" name="userList[2].name">
			<input type="text" name="userList[2].dept.name">
		</form>
		<hr>
	</body>
</html>
