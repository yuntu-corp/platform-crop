<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
	<head>
		<meta name="viewport" content="width=320,user-scalable=no,target-densitydpi=medium-dpi" />
		<meta name="viewport" content="width=device-width,user-scalable=no,target-densitydpi=medium-dpi" />
		<link href="${path}/platform/theme/default/component/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet">
		<link href="${path}/platform/theme/distributionSystem/css/spread.css" type="text/css" rel="stylesheet">
		<script src="${path}/platform/common/js/jweixin-1.js"></script>
		<title>扫面二维码，赢取代金券！</title>
		<script type="text/javascript">
			/* window.onload=function(){
				var obj=eval('(${jsApiData})');
				wx.config({
				     debug: false,
				     appId: obj.appId,
				     timestamp: obj.timeStamp,
				     nonceStr: obj.nonceStr,
				     signature: obj.signature,
				     jsApiList: [
				       'checkJsApi',
				       'onMenuShareTimeline',
				       'onMenuShareAppMessage',
				       'onMenuShareQQ',
				       'onMenuShareWeibo',
				       'hideMenuItems',
				       'showMenuItems',
				       'hideAllNonBaseMenuItem',
				       'showAllNonBaseMenuItem',
				       'translateVoice',
				       'startRecord',
				       'stopRecord',
				       'onRecordEnd',
				       'playVoice',
				       'pauseVoice',
				       'stopVoice',
				       'uploadVoice',
				       'downloadVoice',
				       'chooseImage',
				       'previewImage',
				       'uploadImage',
				       'downloadImage',
				       'getNetworkType',
				       'openLocation',
				       'getLocation',
				       'hideOptionMenu',
				       'showOptionMenu',
				       'closeWindow',
				       'scanQRCode',
				       'chooseWXPay',
				       'openProductSpecificView',
				       'addCard',
				       'chooseCard',
				       'openCard'
					]
			 	});
			};
			wx.ready(function(){
				 wx.hideOptionMenu({
				      success: function (res) {
				    	  wx.showMenuItems({
						      menuList: [
						        'menuItem:share:appMessage', // 阅读模式
						        'menuItem:share:timeline', // 分享到朋友圈
						        'menuItem:share:qq',
						        'menuItem:favorite'// 复制链接
						      ],
						      success: function (res) {
						    	  
						      },
						      fail: function (res) {
						    	  alert(1);
						      }
						    });
				      },
				      fail: function (res) {
				    	  alert(2);
				      }
				    });
			}); */
		</script>
	</head>
	<body>
		<img src="${path}/platform/theme/distributionSystem/images/spread_bg.jpg" class="spread-bg">
        <div class="spread-container">
        	<div class="spread-header">
            	<img src="${customer.headimgurl}" class="spread-photo">
                <div style="font-size:18px;font-weight: 600;">${customer.name}</div>
            </div>
            <div class="spread-main">
            	<div>长按二维码关注公众号即可获得购物代金券</div>
                <img src="${qrcodeUrl}" class="spread-code">
            </div>
        </div>
	</body>
</html>