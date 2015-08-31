<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
	<head>
		<meta name="viewport" content="width=320,user-scalable=no,target-densitydpi=medium-dpi" />
		<meta name="viewport" content="width=device-width,user-scalable=no,target-densitydpi=medium-dpi" />
		<link href="${path}/platform/theme/default/component/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet">
		<link href="${path}/platform/theme/distributionSystem/css/distribution.css" rel="stylesheet">
		<title>商城首页</title>
		<script type="text/javascript">
		</script>
	</head>
	<body>
		<!--header-->
		<div class="product-head">
			<img alt="" src="${path}/platform/theme/distributionSystem/images/head.jpg" width="100%">
		</div>
		<div class="product-container">
			<div style="margin: 8px;border-bottom: 1px solid #ccc;padding: 8px 0;">全部商品</div>
			<c:forEach items="${goodsList}" var="goods">
				<div class="product-info">
					<a href="${path}/weChat/wcGoodsView?id=${goods.id}&customerId=${customer.id}"><img src="${path}/platform/images/${goods.picUrls[0]}" class="product"></a>
				    <div class="product-message">
				    	<span>${goods.name}</span>
				    </div>
				    <div>
				        <span class="price">￥${goods.price}</span>
				        <c:if test="${goods.originalPrice ne ''}">
				        	<span class="original-price">原价￥${goods.originalPrice}</span>
				        </c:if>
				        <span class="total-count">销量：${goods.totalCount}</span> 
				    </div>
				    <div class="line"></div>
				</div>
			</c:forEach>
		</div>
		<!--导航-->
        <div class="main-nav">
        	<a href="javascript:void(0);" class="nav-icon">
            	<img src="${path}/platform/theme/distributionSystem/images/home01.png" />
            	<span>首页</span>
            </a>
            <div class="navLine"></div>
            <a href="${path}/weChat/shoppingCart?customerId=${customer.id}" class="nav-icon">
            	<img src="${path}/platform/theme/distributionSystem/images/cart.png" />
            	<span>购物车</span>
            </a>
            <div class="navLine"></div>
            <a href="${path}/weChat/wcPersonalCenter?customerId=${customer.id}" class="nav-icon">
            	<img src="${path}/platform/theme/distributionSystem/images/women.png" />
            	<span>个人中心</span>
            </a>
        </div>
	</body>
</html>