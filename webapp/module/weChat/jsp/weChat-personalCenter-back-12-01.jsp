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
<link rel="shortcut icon"
	href="http://statics.oneplus.cn/img/oneplus.ico" type="image/x-icon">
<link rel="stylesheet" type="text/css"
	href="${path}/module/weChat/others/css/common_pc.css?v=20150718">
<script src="http://statics.oneplus.cn/v2/core.js?v=20150718"
	id="oneplusjs" updatelist="" update=""></script>
<script>
	document.documentElement.className += op.isMobile ? ' mobile ' : ' pc ';
</script>
<!--[if IE 8]>
        <script type="text/javascript" src="http://statics.oneplus.cn/v2/lib/PIE.js?v=20150718"></script>
    <![endif]-->
<link rel="stylesheet" type="text/css"
	href="${path}/module/weChat/others/css/m-index.css?v=20150718">
</head>
<body>
	<script>
		window.op = window.op || {};
		op.startTime = new Date();
		op.URL = {
			staticUrl : {
				staticHead : "http://statics.oneplus.cn"
			},
			cartUrl : {
				query : 'http://store.oneplus.cn/cart/query',
				add : 'http://store.oneplus.cn/cart/add',
				del : 'http://store.oneplus.cn/cart/del',
				update : 'http://store.oneplus.cn/cart/update',
				getCartNum : 'http://store.oneplus.cn/cart/ajaxCartNum',
				cartPage : 'http://store.oneplus.cn/cart',
				orderPage : 'http://store.oneplus.cn/order/tocheckout',
				storePage : 'http://store.oneplus.cn',
				wxPay : 'http://store.oneplus.cn/pay/wxpayment',
				notice : 'http://store.oneplus.cn/arrival/notice/add' //到货提醒
			},
			account : {
				login : "/onepluslogin",
				logout : "http://account.oneplus.cn/logout",
				get : "http://account.oneplus.cn/ajaxGetUserInfo",
				reg : "/register",
				find : "http://account.oneplus.cn/login/forget",
				sendCode : "http://account.oneplus.cn/sendCode",
				phplogin : "http://account.oneplus.cn/login/phplogin",
				loginPage : "http://account.oneplus.cn/login",
				regPage : "http://account.oneplus.cn/regist"
			},
			xxoo : "register|login|passwordFormatVerify|forgetSendCode|resetPassWord|checkVerifyCode|updatePassWord",
			my : {
				index : "http://my.oneplus.cn"
				//静态资源服务器
				,
				noPhoto : "http://statics.oneplus.cn/v2/img/user/avatar-default-s.png",
				bindIMEI : "http://my.oneplus.cn/bindImei"
			},
			check : {
				opid : "http://account.oneplus.cn/verify",
				sendSMS : "http://account.oneplus.cn/sendSmsCode"
				//验证码
				,
				getVerifyImg : "http://account.oneplus.cn/getVerifyImage"
			},
			agreement : {
				JDB : "http://www.oneplus.cn/jdb/agreement",
				privacy : "http://account.oneplus.cn/agreement_privacy",
				user : "http://account.oneplus.cn/agreement"
			},
			userAddress : {
				addressList : 'http://www.oneplus.cn/user/addr/region',
				query : 'http://www.oneplus.cn/user/addr/query',
				add : 'http://www.oneplus.cn/user/addr/add',
				edit : 'http://www.oneplus.cn/user/addr/modify',
				del : 'http://www.oneplus.cn/user/addr/del',
				mlist : 'http://store.oneplus.cn/pcas/mlist',
				setDefault : 'http://www.oneplus.cn/user/addr/default',
				addressDetail : 'http://www.oneplus.cn/user/addr',
				addressCenter : 'http://store.oneplus.cn/order/addressCenter'
			},
			service : {
				verifyIMEI : 'http://service.oneplus.cn/verifyIMEI',
				findKnowledge : 'http://service.oneplus.cn/findKnowledge',
				addFeedback : 'http://service.oneplus.cn/addFeedback',
				getPoint : 'http://service.oneplus.cn/getPoint',
				serarchKnowledge : 'http://service.oneplus.cn/serarchKnowledge',
				myInsurance : 'http://service.oneplus.cn/queryInsurance'
			}
			// 上传头像flash路径(域名和打开的页面一致)
			,
			uploadSwf : {
				flash : 'http://account.oneplus.cn/account'
			}

		};
		seajs.use('http://statics.oneplus.cn/v2/js/g/top-head.js', function(
				topHead) {
			topHead.init({});
		});
	</script>
	<div class="user-hd">
		<div class="my-avatar">
			<a href="#填写个人修改头像地址"> <img src="${employee.headimgurl }" alt="">
				<span class="update-tip"><i class="i-update-avatar"></i></span>
			</a>
		</div>
		<div class="my-nickname">
			<span> ${employee.name }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${employee.wxCpUser.position}
			</span>
		</div>
		<ul class="my-stuffs">
			<li><a href="javascript:void(0);"> <span class="my-point">${employee.baseBitcoin }</span>
					<em>当月基础币</em>
			</a></li>
			<li><a href="javascript:void(0);"> <span class="my-point">${employee.bitcoinIncome }</span>
					<em>本月收入币</em>
			</a></li>
			<li>
				<!-- 已绑定时显示 --> <a href="javascript:void(0);"> <span>${employee.bitcoinConsume }</span>
					<em>本月消耗币</em>
			</a>
			</li>
			<li><a href="javascript:void(0);"> <span>${employee.bitcoinSurplus }</span>
					<em>当前余额</em>
			</a></li>
		</ul>
	</div>

	<div class="user-bd">
		<div class="user-aside">
			<dl class="user-menu">
				<dt>我的任务</dt>
				<dd>
					<a href="${path }/weChat/myPublishedTask?employeeId=${employee.id}"><i
						class="i-my-order"></i>已发布任务</a>
				</dd>
				<dd>
					<a href="${path }/weChat/myReceivedTask?employeeId=${employee.id}"><i
						class="i-my-address"></i>已接受任务</a>
				</dd>
				<dd>
					<a href="${path }/weChat/myFinishedTask?employeeId=${employee.id}"><i
						class="i-my-prize"></i>已完成任务</a>
				</dd>
				<dd>
					<a href="${path }/weChat/msgList?employeeId=${employee.id}"><i
						class="i-my-cunpon"></i>待处理任务</a>
				</dd>
				<dd>
					<a href="${path }/weChat/publishTask?employeeId=${employee.id}"><i
						class="i-my-prize"></i>发布任务</a>
				</dd>
			</dl>
			<dl class="user-menu">
				<dt>评价管理</dt>
				<dd>
					<a href="${path }/weChat/evaluationList?employeeId=${employee.id}"><i
						class="i-my-account"></i>我的评价</a>
				</dd>
			</dl>
		</div>
		<footer class="op-footer clearfix" id="op-footer">
			<div class="op-footer-kf">
				<p class="op-telhot">
					<i class="i-ft-tel"></i><span>客服热线：</span>0472-5198570
				</p>
			</div>

			<div id="op-language">
				<p>
					Region：<i></i>
				</p>
				<ul id="op-lang-ul">
					<li class="ch selected"><a href="" target="" onclick=""><span>中国</span></a></li>
					<li class="us"><a href="" target="" onclick=""><span>Global</span></a></li>
				</ul>
			</div>

			<div class="op-copyright">
				<p>
					Copyright&copy;<a href="http://www.lanrenmb.com/" target="_blank">云图科技</a>(2013-2015)
					版权所有
				</p>
			</div>
		</footer>
		<script>
			var _hmt = _hmt || [];
			(function() {
				var hm = document.createElement("script");
				hm.src = "//hm.baidu.com/hm.js?e3c7f7f17054135e8f86f4c943459d8b";
				var s = document.getElementsByTagName("script")[0];
				s.parentNode.insertBefore(hm, s);
			})();
		</script>
		<script type="text/javascript">
			(function() {
				var a = document.createElement("script");
				a.type = "text/javascript";
				a.async = true;
				a.src = "http://analytics.oneplus.cn/opdc.js";
				var b = document.getElementsByTagName("script")[0x0];
				b.parentNode.insertBefore(a, b)
			})();
		</script>
	</div>
	</div>
	</div>
	<!--[if lte IE 9]>
<script src="http://statics.oneplus.cn/v2/lib/placeholder.min.js"></script>
<![endif]-->
</body>

</html>
