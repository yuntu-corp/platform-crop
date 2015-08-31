<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
	<head>  
    <title>平台</title>
    <%@ include file="/platform/common/jsp/baseJs.jsp"%>
	</head>
	<body>
		${aaa }<hr>${bbb }<hr><%=request.getAttribute("aaa") %><hr>${pageId }
	</body>
</html>
