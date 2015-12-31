<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp"%>
<html lang="en" class="no-js">
<!--<![endif]-->

<head>
<!--[if lte IE 7]>
	<meta http-equiv="refresh" content="0;url=http://www.oneplus.cn/upgrade/browser">
	<script>location.href="http://www.oneplus.cn/upgrade/browser";</script>
	<![endif]-->
<meta charset="utf-8">

<title>个人中心</title>
<meta name="keyword" content="个人中心" />
<meta name="description" content="个人中心" />

<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="HandheldFriendly" content="true">
<meta name="MobileOptimized" content="320">
<meta name="screen-orientation" content="portrait">
<meta name="x5-orientation" content="portrait">
<meta name="full-screen" content="no">
<meta name="x5-fullscreen" content="true">
<meta name="x5-page-mode" content="app">
<meta name="msapplication-tap-highlight" content="no">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<!--[if IE 8]><meta http-equiv="X-UA-Compatible" content="IE=8"><![endif]-->
<link href="${path}/module/weChat/jump/css/jump-demo.css" rel="stylesheet" type="text/css">
<link href="${path}/module/weChat/jump/css/modify.css" rel="stylesheet" type="text/css">
<style>
a:link{ text-decoration:none;}/*未被访问过的链接不显示下划线*/
a:active{ text-decoration:none;}/*正在点击的链接文本闪烁*/
a:hover{ text-decoration:none;}/*鼠标移入的链接文字有下划线*/
a:visited{ text-decoration:none;}/*已被访问过链接*/
}
</style>
</head>
<script type="text/javascript" src="${path}/platform/ui/semantic/xdate.js"></script>
<script type="text/javascript">
function a(){
	/* if("ABC".equalIgnoreCase('abc')){
		alert("ok");
	}else{
		alert("erroe");
	} */
	var date=new XDate();
	alert(date.toString());
}
</script>
<body>
	<div class="jump-container">
		<div class="jump-main" style="background-color:#3779d0;">
		<!-- ${path}/weChat/goUpdateCurrentBitcoin?employeeId=${employee.id} -->
		<a href="JavaScript:void(0)" onclick="a()">
			<img src="${employee.headimgurl }">
			<div class="text" style="color:#fff;">
				${employee.name } 
					<small style="display:inline; padding-left:4px; font-size:16px;  color:#dbeeff;">${employee.wxCpUser.position}</small>
				</div>
			</a>
		</div>
		<!-- first -->
		<div class="jump-column-frist">
			<ul class="listitem">
				<li>${employee.baseBitcoin } <small>月薪</small>
				</li>
				<li
					style="border-left: 1px solid #fff; border-right: 1px solid #fff;">
					${employee.bitcoinIncome } <small>本月收入</small>
				</li>
				<li style="border-right: 1px solid #fff;">${employee.bitcoinConsume }  <small>本月消耗</small>
				</li>
				<li>${employee.bitcoinSurplus } <small>当前余额</small>
				</li>
			</ul>
		</div>

		<!---->
		<div class="jump-content">
			<ul class="listitem">
				<li>
					<a href="${path}/weChat/goUpdateCurrentBitcoin?employeeId=${employee.id}">
						<img src="${path}/module/weChat/jump/images/aside/iconline-arrow-right.png" class="iconline-arrow-right">
						<div class="iconline-icon-right" style="color:#444; background-color:#fff; border-radius:0;">￥${employee.currentBitcoin }</div> 
						<!-- <div class="iconline-icon-right">5</div> -->
						<div class="text">修改今日价格</div>
					</a>
				</li>
				<li>
					<a href="${path }/weChat/myPublishedTask?employeeId=${employee.id}">
						<img src="${path}/module/weChat/jump/images/aside/iconline-arrow-right.png" class="iconline-arrow-right">
						<!-- <div class="iconline-icon-right">5</div> -->
						<div class="text">已发布任务</div>
					</a>
				</li>
				<li>
					<a href="${path }/weChat/myReceivedTask?employeeId=${employee.id}">
						<img src="${path}/module/weChat/jump/images/aside/iconline-arrow-right.png" class="iconline-arrow-right">
						<!-- <div class="iconline-icon-right">5</div> -->
						<div class="text">已接受任务</div>
					</a>
				</li>
				<li>
					<a href="${path }/weChat/myFinishedTask?employeeId=${employee.id}">
						<img src="${path}/module/weChat/jump/images/aside/iconline-arrow-right.png" class="iconline-arrow-right">
						<!-- <div class="iconline-icon-right">5</div> -->
						<div class="text">已完成任务</div>
					</a>
				</li>
				<li>
					<a href="${path }/weChat/msgList?employeeId=${employee.id}">
						<img src="${path}/module/weChat/jump/images/aside/iconline-arrow-right.png" class="iconline-arrow-right">
						<!-- <div class="iconline-icon-right" style="background-color:#ff0000;">5</div> -->
						<div class="text">待处理任务</div>
					</a>
				</li>
			</ul>
			<ul class="listitem">
				<li>
					<a href="${path }/weChat/evaluationList?employeeId=${employee.id}">
						<img src="${path}/module/weChat/jump/images/aside/iconline-arrow-right.png" class="iconline-arrow-right">
						<!-- <div class="iconline-icon-right" style="background-color:#ff0000;">5</div> -->
						<div class="text">我的评价</div>
					</a>
				</li>
			</ul>
			<ul class="listitem">
				<li>
					<img src="${path}/module/weChat/jump/images/aside/iconline-arrow-right.png" class="iconline-arrow-right">
				 	<div class="text">
						帮助
					</div>
				</li>
			</ul>
			<ul class="telephone">
				<li>
					<div class="text">客服热线：0472-5198570</div>
				</li>
			</ul>
		</div>
	</div>
</body>

</html>

