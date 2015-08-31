
<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
	<head>
		<meta name="viewport" content="width=320,user-scalable=no,target-densitydpi=medium-dpi" />
		<meta name="viewport" content="width=device-width,user-scalable=no,target-densitydpi=medium-dpi" />
		<link href="${path}/platform/theme/distributionSystem/css/application_successful.css" type="text/css" rel="stylesheet">
		<title>提交成功</title>
		<script type="text/javascript">
		</script>
	</head>
	<body>
		<div class="checkmark">
			<img src="${path}/platform/theme/distributionSystem/images/checkmark.png" class="checkmark_bg" >
		</div>
		<div class="withdrawals_content1">提现申请已提交</div>
		<div class="arrival_time">预计到账时间：7个工作日之内</div>
		<div class="withdrawal_information">
			<div class="basic_information1">
		    	<div>储蓄卡</div>
		    	<div>提现金额</div>
		    </div>
		    <div class="basic_information2">
		    	<div>${accountBank} <!-- 尾号7503 --></div>
		        <div>￥${withdrawCash.money}</div>
		    </div>
		</div>
		<div class="submit_completed">
			<a href="${path}/weChat/wcPersonalCenter?customerId=${withdrawCash.customer.id}">完成</a>
		</div>
	</body>
</html>