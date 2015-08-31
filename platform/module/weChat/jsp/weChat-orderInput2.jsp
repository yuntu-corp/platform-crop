<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
	<head>
		<script src="${path}/platform/common/js/jquery-1.11.1.js"></script>
		<meta name="viewport" content="width=320,user-scalable=no,target-densitydpi=medium-dpi" />
		<meta name="viewport" content="width=device-width,user-scalable=no,target-densitydpi=medium-dpi" />
		<link href="${path}/platform/theme/default/component/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet">
		<link href="${path}/platform/theme/distributionSystem/css/a.css" type="text/css" rel="stylesheet">
		<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
		<title>商品支付</title>
		<script type="text/javascript">
			function minus() {
				var count = $("#count").val();
				if(count>1){
					$("#count").val(--count);
					var price = ${model.price};
					$("#price").html(count*price+".00+￥0.00运费");
					$("#finalPrice").html("需付：￥"+count*price+".00");
				}
			};
			function plus() {
				var count = $("#count").val();
				$("#count").val(++count);
				var price = ${model.price};
				$("#price").html(count*price+".00+￥0.00运费");
				$("#finalPrice").html("需付：￥"+count*price+".00");
			};
			function submit() {
				var formData = $("#${pageId}theForm").serialize();
				$.ajax({
				  	url: "${path}/weChat/orderSave?goodsId=${model.id}&count="+$("#count").val(),
				 	type: "POST",
				  	data: formData,
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
							/* WeixinJSBridge.invoke(
								'getBrandWCPayRequest', {
									"appId" : obj.appId,     //公众号名称，由商户传入     
							        "timeStamp" : obj.timeStamp,         //时间戳，自1970年以来的秒数     
							        "nonceStr" : obj.nonceStr, //随机串     
							        "package" : "prepay_id="+obj.prepayId,     
							        "signType" : "MD5",         //微信签名方式:     
							        "paySign" : obj.paySign //微信签名 
							    },
							    function(res){    
							    	alert(res.err_msg);
							        if(res.err_msg == "get_brand_wcpay_request:ok" ) {
							        	alert(6);
							        	window.location.href="${path}/weChat/wcPaymentSuccess?orderId="+obj.orderId;
							        	
							        }     // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。 
							    }
							);  */
							WeixinJSBridge.invoke(
						       'getBrandWCPayRequest', {
						           "appId" : "wx2421b1c4370ec43b",     //公众号名称，由商户传入     
						           "timeStamp":" 1395712654",         //时间戳，自1970年以来的秒数     
						           "nonceStr" : "e61463f8efa94090b1f366cccfbbb444", //随机串     
						           "package" : "prepay_id=u802345jgfjsdfgsdg888",     
						           "signType" : "MD5",         //微信签名方式:     
						           "paySign" : "70EA570631E4BB79628FBCA90534C63FF7FADD89" //微信签名 
						       },
						       function(res){     
						           if(res.err_msg == "get_brand_wcpay_request:ok" ) {
						        	   window.location.href="${path}/weChat/wcPaymentSuccess?orderId="+obj.orderId;
						           }     // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。 
						       }
						   ); 
						}
				  		/* if(data){
					  		$.ajax({
							  	url: "${path}/weChat/payment?orderId="+data,
							 	type: "POST",
							  	data: formData,
							  	success: function(){
							  		window.location.href="${path}/weChat/wcPaymentSuccess?orderId="+data;
							  	}
							});
				  		} */
				  	}
				});
			};
			window.onload=function(){
				var count = $("#count").val();
				var price = ${model.price};
				$("#price").html(count*price+".00+￥0.00运费");
				$("#finalPrice").html("需付：￥"+count*price+".00");
			}
		</script>
	</head>
	<body>
		<div class="msg_container">
			<div class="msg_bg">
		        <img src="${path}/platform/images/${model.picUrls[0]}" class="msg_phpto">
		        <div class="msg">
		            <h4>${model.name}</h4>
		            <p>规格：${model.size}</p>
		            <div style="margin: 5px 0;">
			            <span class="moneycoin">￥</span>
			            <span class="money">${model.price}</span>
		            </div>
		            <span class="number">
	                    <a href="javascript:void(0);" onclick="minus();">-</a>
	                    <input type="text" id="count" value="1" class="form-count" readonly="readonly" style="">
	                    <a href="javascript:void(0);" onclick="plus();">+</a>
		            </span>
		        </div>
			</div>
		</div>
		<form id="${pageId}theForm" method="post">
			<input type="hidden" name="model.customer.id" value="${customer.id}" />
			<input type="hidden" name="model.user.id" value="${customer.user.id}" />
			<div class="form-group">
		        <label for="exampleInputEmail1">收件人姓名：</label>
		        <input type="text" class="form-control" id="receiver" name="model.receiver" placeholder="请输入收件人姓名">
			</div>	
			<div class="form-group">
		        <label for="exampleInputEmail1">电话：</label>
		        <input type="text" class="form-control" id="receiverPhoneNum" name="model.receiverPhoneNum" placeholder="请输入联系电话">
			</div><div class="form-group">
		        <label for="exampleInputEmail1">收货地址：</label>
		        <input type="text" class="form-control" id="receiveAddress" name="model.receiveAddress" placeholder="请输入详细收货地址">
			</div>
			<div class="note">请填写真实姓名，提现时只可绑定该姓名的银行卡。</div>
		</form>
		<div class="check_container">
			<p id="price">￥${model.price}+￥0.00运费</p>
		    <p id="finalPrice" class="check">需付：￥${model.price}</p>
		</div>
		<div class="button_container"><a href="javascript:void(0);" onclick="submit();" class="btn btn-info">提交订单</a></div>
	</body>
</html>