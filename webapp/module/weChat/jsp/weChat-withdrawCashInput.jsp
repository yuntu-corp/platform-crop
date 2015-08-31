<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
	<head>
		<script src="${path}/platform/common/js/jquery-1.11.1.js"></script>
		<meta name="viewport" content="width=320,user-scalable=no,target-densitydpi=medium-dpi" />
		<meta name="viewport" content="width=device-width,user-scalable=no,target-densitydpi=medium-dpi" />
		<link href="${path}/platform/theme/default/component/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet">
		<link href="${path}/platform/theme/distributionSystem/css/withdrawApplication.css" type="text/css" rel="stylesheet">
		<title>提现申请</title>
		<script type="text/javascript">
			function doSave() {
				$("#accountNameTip").hide();
				$("#accountNumTip").hide();
				$("#accountBankTip").hide();
				$("#moneyTip").hide();
				var flag = true;
				if($("#accountName").val().trim()=="") {
					flag = false;
					$("#accountNameTip").show();
				}
				if($("#accountNum").val().trim()=="") {
					flag = false;
					$("#accountNumTip").show();
				}
				if($("#accountBank").val().trim()=="") {
					flag = false;
					$("#accountBankTip").show();
				}
				if($("#money").val().trim()=="") {
					flag = false;
					$("#moneyTip").show();
				}
				if(flag) {
					var money = parseFloat($("#money").val().trim());
					var cash = parseFloat("${customer.remainMoney}");
					if(money<100) {
						alert("低于100元，无法提现");
						return;
					}
					if(money>cash) {
						alert("余额不足");
						return;
					}
					$("#${pageId}theForm").submit();
				}
			}
		</script>
	</head>
	<body>
		<!--header-->
    	<div class="clearing-header">
        	<a href="javascript:history.go(-1);"><span class="glyphicon glyphicon-menu-left" aria-hidden="true"></span></a>
            <span class="title">提现申请</span>
		</div>
        <!---->
		<form id="${pageId}theForm" method="post" action="${path}/weChat/withdrawCashSave">
			<input name="model.customer.id" value="${customer.id}" type="hidden">
			<input name="model.user.id" value="${customer.user.id}" type="hidden">
			<div class="shop">
			    <label for="" class="">户名：</label>
			    <input id="accountName" class="form-control" name="model.accountName" type="text" value="" />
			    <span id="accountNameTip" class="withdrawCashTip">户名不能为空！</span>
			</div>
			<div class="shop">
			    <label for="" class="">账号：</label>
			    <input id="accountNum" class="form-control" name="model.accountNum" type="text" value="" />
			    <span id="accountNumTip" class="withdrawCashTip">账号不能为空！</span>
			</div>
			<div class="shop">
			    <label for="" class="">开户银行：</label>
			    <input id="accountBank" class="form-control" name="model.accountBank" type="text" value="" />
			    <span id="accountBankTip" class="withdrawCashTip">开户银行不能为空！</span>
			</div>
			<div class="shop">
			    <label for="" class="">提现金额：</label>
			    <input id="money" class="form-control" name="model.money" type="text" value="" />
			    <span id="moneyTip" class="withdrawCashTip">提现金额不能为空！</span>
			</div>
			<!--提现按钮-->
			<a href="javascript:void(0);" onclick="doSave();" class="btn btn-info">提交</a>
			<p class="tel">温馨提示：您的可提现金额满100元即可提现，我们会在1-7个工作日内处理您的提现申请（节假日除外），如有异议请拨打电话进行查询&nbsp;400-6688-474</p>
		</form>
	</body>
</html>