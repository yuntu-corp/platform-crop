<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
	<head>
		<script src="${path}/platform/common/js/jquery-1.11.1.js"></script>
		<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
		<meta name="viewport" content="width=320,user-scalable=no,target-densitydpi=medium-dpi" />
		<meta name="viewport" content="width=device-width,user-scalable=no,target-densitydpi=medium-dpi" />
		<link href="${path}/platform/theme/default/component/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet">
		<link href="${path}/platform/theme/distributionSystem/css/clearing.css" type="text/css" rel="stylesheet">
		<title>结算中心</title>
		<script type="text/javascript">
			function doSave() {
				if($("#receiver").val().trim()=="") {
					alert("收货人不能为空！");
					return;
				} else if ($("#receiverPhoneNum").val().trim()=="") {
					alert("手机号码不能为空！");
					return;
				} else if($("#receiveAddress").val().trim()=="") {
					alert("收货地址不能为空！");
					return;
				}
				var receiver = $("#receiver").val();
				var receiverPhoneNum = $("#receiverPhoneNum").val();
				var receiveAddress = $("#receiveAddress").val();
				$.ajax({
				 	type: "GET",
				 	url: "${path}/weChat/orderSave?customerId=${shoppingCartVo.customer.id}&receiver="+encodeURI(encodeURI(receiver))
				 			+"&receiverPhoneNum="+encodeURI(encodeURI(receiverPhoneNum))+"&receiveAddress="+encodeURI(encodeURI(receiveAddress)),
				  	processData:true,
				  	success: function(data){
				  		var obj=eval('('+data+')');  
						if (typeof WeixinJSBridge == "undefined"){
						   if(document.addEventListener){
						       document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
						   }else if (document.attachEvent){
						       document.attachEvent('WeixinJSBridgeReady', onBridgeReady); 
						       document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
						   }
						}else{
							WeixinJSBridge.invoke(
								'getBrandWCPayRequest', {
									"appId" : obj.appId,     //公众号名称，由商户传入     
							        "timeStamp" : obj.timeStamp,         //时间戳，自1970年以来的秒数     
							        "nonceStr" : obj.nonceStr, //随机串     
							        "package" : "prepay_id="+obj.prepayId,     
							        "signType" : "MD5",         //微信签名方式:     
							        "paySign" : obj.paySign //微信签名 
							    },
							    function(res){    
							        if(res.err_msg == "get_brand_wcpay_request:ok" ) {
							        	window.location.href="${path}/weChat/wcPersonalCenter?customerId=${shoppingCartVo.customer.id}";
							        } else {
							        	window.location.href="${path}/weChat/wcPaymentFail?customerId=${shoppingCartVo.customer.id}";
							        }    // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。 
							    }
							);
						}
				  	}
				});
			}
		</script>
	</head>
	<body>
		<!--header-->
    	<div class="clearing-header">
        	<a href="${path}/weChat/shoppingCart?customerId=${shoppingCartVo.customer.id}"><span class="glyphicon glyphicon-menu-left" aria-hidden="true"></span></a>
            <span class="title">结算中心</span>
		</div>
		<!---->
        <div class="shipping-address">
        	 <div class="address-header">收货地址</div>
             <div class="contacts"><img src="${path}/platform/theme/distributionSystem/images/men.png" />收货人<input type="text" id="receiver" class="shoping-text"></div>
             <div class="contacts"><img src="${path}/platform/theme/distributionSystem/images/mic.png" />手机号码<input type="text" id="receiverPhoneNum" class="shoping-text"></div>
             <div class="contacts"><img src="${path}/platform/theme/distributionSystem/images/pin.png" />详细地址<input type="text" id="receiveAddress" class="shoping-text"></div>
        </div>
        <!--购物单-->
        <div class="clearing-list">
        	<div class="clearing-prompt">商品信息</div>
        	<c:forEach items="${shoppingCartVo.shoppingCartList}" var="shoppingCart" >
	            <div class="clearing-product">
	            	<img src="${path}/platform/images/${shoppingCart.goods.picUrls[0]}" class="clearing-img">
	                <div class="clearing-information">
	                	<div class="clearing-name">${shoppingCart.goods.name}</div>
	                    <div class="clearing-specification">${shoppingCart.goods.size}</div>
	                </div>
	                <div class="clearing-pice">
	                	<div class="clearing-money">￥${shoppingCart.goods.price}</div>
	                    <div class="clearing-quantity">×${shoppingCart.count}</div>
	                </div>
	            </div>
            </c:forEach>
		</div> 
        <div class="clearing-shipment">
			<span class="shipment">运费</span>
			<span class="clearing-reduce">快递：￥0（已减免￥15）</span>
		</div>
		<c:if test="${shoppingCartVo.customer.user.discount ne ''}">
			<div class="clearing-calculate">
				<span class="clearing-total">折扣</span>
				<span class="total-money">${shoppingCartVo.customer.user.discount/10}折</span>
			</div>
		</c:if>
        
        <!--购物结算-->
        <div class="item-settlement">
        	<div class="item-information">
            	<div class="total-price">合计<span class="item-price">&nbsp;￥${shoppingCartVo.price}</span></div>
            	<div class="item-quantity">微信支付</div>
            </div>
            <c:if test="${fn:length(shoppingCartVo.shoppingCartList)>0}">
            	<a href="javascript:void(0);" onclick="doSave();" class="clearing">提交</a>
            </c:if>
        </div>
	</body>
</html>