<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
	<head>
		<meta name="viewport" content="width=320,user-scalable=no,target-densitydpi=medium-dpi" />
		<meta name="viewport" content="width=device-width,user-scalable=no,target-densitydpi=medium-dpi" />
		<link href="${path}/platform/theme/distributionSystem/css/spred.css" type="text/css" rel="stylesheet">
	</head>
	<body style="font-size:20px; color:#217106;width:370px;height:600px;background-image:${path}/platform/theme/distributionSystem/images/spread_bg.jpg;">
		<br/>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		${name}
		<br/><p/><br/><p/><br/><p/><br/><p/><br/><p/><br/><br/><br/>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<img src="${qrcodeUrl}" width="160" height="160" >
	</body>
</html>