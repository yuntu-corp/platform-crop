<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
	<head>
		<meta name="viewport" content="width=320,user-scalable=no,target-densitydpi=medium-dpi" />
		<meta name="viewport" content="width=device-width,user-scalable=no,target-densitydpi=medium-dpi" />
		<link href="${path}/platform/theme/default/component/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet">
		<link href="${path}/platform/theme/distributionSystem/css/myPromotion.css" type="text/css" rel="stylesheet">
		<title>我的推广</title>
		<script type="text/javascript">
			
		</script>
	</head>
	<body>
		<!--header-->
    	<div id="1" class="myListings_header">
        	<a href="javascript:history.go(-1);"><span class="glyphicon glyphicon-menu-left" aria-hidden="true"></span></a>
            <span class="title">我的推广</span>
		</div>
        <!--banner-->
        <img src="${path}/platform/theme/distributionSystem/images/myPromotion_banner.jpg" class="mylistings_banner">
        
        <!--代理-->
        <ul style="display: block;">
        	<c:forEach items="${promotionList}" var="customer">
				<li class="Mypromotion">
					<img src="${customer.headimgurl}" class="image_border_circle">
					<span>${customer.name}</span>
				</li>
			</c:forEach>
		</ul>
	</body>
</html>