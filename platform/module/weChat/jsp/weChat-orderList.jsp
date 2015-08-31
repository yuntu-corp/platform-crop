<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
	<head>
		<meta name="viewport" content="width=320,user-scalable=no,target-densitydpi=medium-dpi" />
		<meta name="viewport" content="width=device-width,user-scalable=no,target-densitydpi=medium-dpi" />
		<link href="${path}/platform/theme/default/component/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet">
		<link href="${path}/platform/theme/distributionSystem/css/myCommission.css" type="text/css" rel="stylesheet">
		<title>订单管理</title>
		<script type="text/javascript">
			
		</script>
	</head>
	<body>
		<!--header-->
    	<div class="myCommission-header">
        	<a href="javascript:history.go(-1);"><span class="glyphicon glyphicon-menu-left" aria-hidden="true"></span></a>
            <span class="title">订单管理</span>
		</div>
        <!--订单详情-->
        <c:forEach items="${orderList}" var="order">
	        <div class="order">
	        	<div class="logisticsNum">
	        		<c:if test="${order.logisticNum ne null}">
		        		物流单号：${order.logisticNum}&nbsp;&nbsp;
		        		<span class="logistics">${order.logisticName}</span>
	        		</c:if>&nbsp;
	        		<span style="float: right;">${order.stateValue}</span>
        		</div>
	        	<c:forEach items="${order.orderGoodsList}" var="orderGoods">
		            <div class="order_details">
		            	<img src="${path}/platform/images/${orderGoods.goods.picUrls[0]}" class="order_img">
		                <div class="order_information">
		                	<div class="orderProduct_Name">${orderGoods.goods.name}</div>
		                    <div class="orderProduct_specifications">商品规格：${orderGoods.goods.size}</div>
		                </div>
		                <!--<div class="dividing_line"></div>-->
		                <div class="price_tag">
		                	<div class="myCommission_Price">￥${orderGoods.price}</div>
		                    <div class="quantity">×${orderGoods.count}</div>
		                    <c:if test="${'3' eq order.state}">
		                    	<c:if test="${orderGoods.state eq '0'}">
			                    	<a href="${path}/weChat/appraisalInput?customerId=${customerId}&orderGoodsId=${orderGoods.id}&orderId=${order.id}" class="order_Evaluation"><span class="glyphicon glyphicon-education" aria-hidden="true">评价</span></a>
		                    	</c:if>
		                    	<c:if test="${orderGoods.state eq '1'}">
		                    		<a href="javascript:void(0);" class="order_Evaluation"><span class="glyphicon glyphicon-education" aria-hidden="true">已评价</span></a>
		                    	</c:if>
		                    </c:if>
		                    <c:if test="${'2' eq order.state}">
		                    	<a href="${path}/weChat/confirmReceipt?customerId=${customerId}&orderId=${order.id}" class="order_Evaluation">确认收货</span></a>
		                    </c:if>
		                </div>
		            </div>
	            </c:forEach>
	            <div class="disbursements">
	            	<div class="productsNum">共${order.totalCount}件商品&nbsp;&nbsp;<span class="paid">实付：</span><span class="calculation_Amount">￥${order.totalPrice}</span></div>
	            </div>
	        </div>
        </c:forEach>
	</body>
</html>