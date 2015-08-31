<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
	<head>
		<meta name="viewport" content="width=320,user-scalable=no,target-densitydpi=medium-dpi" />
		<meta name="viewport" content="width=device-width,user-scalable=no,target-densitydpi=medium-dpi" />
		<link href="${path}/platform/theme/default/component/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet">
		<title>销售话术</title>
		<style>
			.header_img{
				width:100%;
				}
			.scripts{
				width:94%;
				margin:14px 3% 10px 3%;
				line-height:20px;
				}
			.interval{
				width:100%;
				}
		</style>
	</head>
	<body>
		<img src="${path}/platform/theme/distributionSystem/images/sentence_img.jpg" class="header_img">
		<c:forEach items="${propagandaList}" var="propaganda">
		    <div class="scripts">${propaganda.content}</div>
		    <img src="${path}/platform/theme/distributionSystem/images/interval.jpg" class="interval">
	    </c:forEach>
	</body>
</html>