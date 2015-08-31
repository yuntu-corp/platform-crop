<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
	<head>
		<script src="${path}/platform/common/js/jquery-1.11.1.js"></script>
		<meta name="viewport" content="width=320,user-scalable=no,target-densitydpi=medium-dpi" />
		<meta name="viewport" content="width=device-width,user-scalable=no,target-densitydpi=medium-dpi" />
		
		<link href="${path}/platform/theme/default/component/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet">
		<link href="${path}/platform/theme/distributionSystem/css/shoipping_cart.css" type="text/css" rel="stylesheet">
		<title>购物车</title>
		<script type="text/javascript">
			function minus(shoppingCartId, price) {
				var count = $("#"+shoppingCartId+"count").val();
				if(count>1){
					$("#"+shoppingCartId+"count").val(--count);
					var totalCount = $("#totalCount").val();
					$("#totalCount").val(--totalCount);
					var totalPrice = $("#totalPrice").val();
					$("#totalPrice").val(parseFloat(totalPrice)-parseFloat(price)+".00");
				}
			};
			function plus(shoppingCartId, price) {
				var count = $("#"+shoppingCartId+"count").val();
				$("#"+shoppingCartId+"count").val(++count);
				var totalCount = $("#totalCount").val();
				$("#totalCount").val(++totalCount);
				var totalPrice = $("#totalPrice").val();
				$("#totalPrice").val(parseFloat(totalPrice)+parseFloat(price)+".00");
			};
			function deleteGoods(shoppingCartId, price) {
				$.ajax({
				 	type: "POST",
				 	url: "${path}/weChat/shoppingCartDelete?id="+shoppingCartId,
				  	processData:true,
				  	success: function(){
				  		var count = $("#"+shoppingCartId+"count").val();
						var totalCount = $("#totalCount").val();
						$("#totalCount").val(parseInt(totalCount)-parseInt(count));
						var totalPrice = $("#totalPrice").val();
						$("#totalPrice").val(parseFloat(totalPrice)-(parseFloat(price)*parseInt(count))+".00");
						$("#"+shoppingCartId+"goodsDetail").remove();
				  	}
				});
			};
			function submit() {
				$("#form").submit();
			};
		</script>
	</head>
	<body>
		<!--hrader-->
    	<div class="myCommission-header">
            <span class="title">购物车</span>
		</div>
        <!--订单详情-->
        <form id="form" action="${path}/weChat/wcOrderInput" method="post">
        	<input type="hidden" name="customerId" value="${shoppingCartVo.customer.id}" />
        	<input type="hidden" name="shoppingCartIds" value="${shoppingCartVo.shoppingCartIds}">
	        <div class="order">
	        	<div class="logistics-num">商品数量有限，请尽快结算</div>
	        	<c:forEach items="${shoppingCartVo.shoppingCartList}" var="shoppingCart" varStatus="i">
		            <div id="${shoppingCart.id}goodsDetail" class="order-details">
		            	<img src="${path}/platform/images/${shoppingCart.goods.picUrls[0]}" class="order-img">
		                <div class="order-information">
		                	<div class="orderProduct-name">${shoppingCart.goods.name}</div>
		                    <div class="orderProduct-specifications">￥${shoppingCart.goods.price}</div>
		                    <div style="float: right;margin:5px 0 6px 50px;" ><img src="${path}/platform/theme/distributionSystem/images/Bin.png" style="width: 13px" onclick="deleteGoods('${shoppingCart.id}', '${shoppingCart.goods.price}')" /></div>
		                    <div class="quantity">
		                    	<span class="symbol" onclick="minus('${shoppingCart.id}','${shoppingCart.goods.price}')">－</span>
		                    	<input id="${shoppingCart.id}count" name="${shoppingCart.id}countName" type="text" value="${shoppingCart.count}" readonly="readonly" style="padding: 0 5px;width: 30px;text-align: center;border: none;">
		                    	<span class="symbol" onclick="plus('${shoppingCart.id}','${shoppingCart.goods.price}')">＋</span>
		                    </div>
		                </div>
		            </div>
	            </c:forEach>
	            <img src="${path}/platform/theme/distributionSystem/images/item.png" class="order-bg">        
	        </div>
	        <!--购物结算-->
	        <div class="item-settlement">
	        	<div class="item-information">
	            	<div class="total-price">合计&nbsp;
	            		<span class="item-price">￥<input id="totalPrice" type="text" value="${shoppingCartVo.originalPrice}" readonly="readonly" style="padding: 0 5px;width: 70px;text-align: center;border: none;"></span>
            		</div>
	            	<div class="item-quantity">共&nbsp;<input id="totalCount" type="text" value="${shoppingCartVo.count}" readonly="readonly" style="padding: 0 5px;width: 30px;text-align: center;border: none;">&nbsp;件</div>
	            </div>
	            <a href="javascript:void(0);" class="clearing" onclick="submit();">去结算</a>
	        </div>
        </form>
        <!--导航-->
        <div class="main-nav">
        	<a href="${path}/weChat/wcGoodsList?customerId=${shoppingCartVo.customer.id}" class="nav-icon">
            	<img src="${path}/platform/theme/distributionSystem/images/home1.png" />
            	<span>首页</span>
            </a>
            <div class="navLine"></div>
            <a href="javascript:void(0);" class="nav-icon">
            	<img src="${path}/platform/theme/distributionSystem/images/cart01.png" />
            	<span>购物车</span>
            </a>
            <div class="navLine"></div>
            <a href="${path}/weChat/wcPersonalCenter?customerId=${shoppingCartVo.customer.id}" class="nav-icon">
            	<img src="${path}/platform/theme/distributionSystem/images/women.png" />
            	<span>个人中心</span>
            </a>
        </div>
	</body>
</html>