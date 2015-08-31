
<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
	<head>
		<meta name="viewport" content="width=320,user-scalable=no,target-densitydpi=medium-dpi" />
		<meta name="viewport" content="width=device-width,user-scalable=no,target-densitydpi=medium-dpi" />
		<link href="${path}/platform/theme/distributionSystem/css/successfulPayment.css" type="text/css" rel="stylesheet">
		<title>支付成功</title>
		<script type="text/javascript">
		</script>
	</head>
	<body>
		<img src="${path}/platform/theme/distributionSystem/images/payment_img.jpg" class="paysuccess_tipsbg">
		<div class="paysuccess_container">
			<div class="disbursements">实付款：<span class="symbol">￥</span><span class="disbursements_money">${order.totalPrice}</span></div>
			<div class="addressee">收件人：${order.receiver}&nbsp;&nbsp;${order.receiverPhoneNum}</div>
		    <div class="shipping_address">收货地址：${order.receiveAddress}</div>
		    <input class="return" type="submit" name="submit" value="返回商城" />
		</div>
		<div class="safety_tips">
			<div>安全提示：</div>
		    <div>付款成功后，我们不会以任何理由为由联系您。<span>请勿泄露银行卡账号、手机验证码，否则会造成欠款损失。谨防电话诈骗！</span></div>
		</div>
	</body>
</html>