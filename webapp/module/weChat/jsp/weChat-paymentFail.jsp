
<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
	<head>
		<meta name="viewport" content="width=320,user-scalable=no,target-densitydpi=medium-dpi" />
		<meta name="viewport" content="width=device-width,user-scalable=no,target-densitydpi=medium-dpi" />
		<link href="${path}/platform/theme/distributionSystem/css/return.css" type="text/css" rel="stylesheet">
		<title>支付失败</title>
		<script type="text/javascript">
			function submit() {
				window.location.href="${path}/weChat/wcGoodsList?customerId=${customerId}";
			}
		</script>
	</head>
	<body>
		<img src="${path}/platform/theme/distributionSystem/images/failure_pay.jpg" class="paysuccess_tipsbg">
		<div class="paysuccess_container">
			<div class="disbursements">付款失败！</div>
			<input class="return" type="button" onclick="submit();" name="submit" value="返回商城" />
		</div>
	</body>
</html>