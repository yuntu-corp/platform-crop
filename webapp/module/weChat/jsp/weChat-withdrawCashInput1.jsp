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
			function submit() {
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
					$("#${pageId}theForm").submit();
/* 					var formData = $("#${pageId}theForm").serialize();
					$.ajax({
					 	type: "POST",
					  	url: "${path}/weChat/withdrawCashSave",
					  	data: formData,
					  	success: function(){
					  		window.location.href="http://www.baidu.com";
					  	}
					});
 */				}
			}
		</script>
	</head>
	<body>
		<form id="${pageId}theForm" method="post" action="${path}/weChat/withdrawCashSave">
			<input type="hidden" name="model.customer.id" value="${customer.id}" />
			<input type="hidden" name="model.user.id" value="${customer.user.id}" />
			<div class="shop">
			    <label for="" class="">户名：</label>
			    <input id="accountName" type="text" class="form-control" name="model.accountName" value="" />
			    <span id="accountNameTip" class="withdrawCashTip">户名不能为空！</span>
			</div>
			<div class="shop">
			    <label for="" class="">账号：</label>
			    <input id="accountNum" type="text" class="form-control" name="model.accountNum" value="" />
			    <span id="accountNumTip" class="withdrawCashTip">账号不能为空！</span>
			</div>
			<div class="shop">
			    <label for="" class="">开户银行：</label>
			    <input id="accountBank" type="text" class="form-control" name="model.accountBank" value="" />
			    <span id="accountBankTip" class="withdrawCashTip">开户银行不能为空！</span>
			</div>
			<div class="shop">
			    <label for="" class="">提现金额：</label>
			    <input id="money" type="text" class="form-control" name="model.money" value="" />
			    <span id="moneyTip" class="withdrawCashTip">提现金额不能为空！</span>
			</div>
			<!--提现按钮-->
			<a href="javascript:void(0);" onclick="submit();" class="btn btn-info">提交提现申请</a>
			<p class="tel">温馨提示：您的可提现金额满99元即可提现，我们会在1-7个工作日内处理您的提现申请（节假日除外），如有异议请拨打电话进行查询&nbsp;400-6688-474</p>
		</form>
	</body>
</html>