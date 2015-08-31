<!DOCTYPE html>
<%@page import="com.unitever.platform.util.UUID"%>
<%@page import="com.unitever.demo.model.TBUser"%>
<%@page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
	<head>  
    <title>平台</title>
    <%@ include file="/platform/common/jsp/baseJs.jsp"%>
	</head>
	<script type="text/javascript">
		
		function goUserList(){
			var url = "${path}/dm/user/list";
			$.dialog.load(url,{
				title:"用户列表",
				width:700,
				height:600
// 				ok:function(){
// 					com.ue.platform.module.user.doAdd();
// 				}
			})
		}
		
		
		$(document).ready(function(){
		})
		
	</script>
	<body>
	  	<h3><a href="${path}/dm/user/list">用户列表</a></h3>
	  	<h3><a href="${path}/dm/user/other">用户其他</a></h3>
	</body>
</html>
