<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
	<head>
		<meta name="viewport" content="width=320,user-scalable=no,target-densitydpi=medium-dpi" />
		<meta name="viewport" content="width=device-width,user-scalable=no,target-densitydpi=medium-dpi" />
		<link href="${path}/platform/theme/default/component/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet">
		<link href="${path}/platform/theme/distributionSystem/css/personal.css" type="text/css" rel="stylesheet">
		<title>个人中心</title>
		<script type="text/javascript">
			function toWithDrawCash() {
				window.location.href='${path}/weChat/wcWithdrawCashInput?customerId=${customer.id}&userId=${user.id}';
			}
			window.onload=function(){
				var shop=document.getElementById('shop'),
					ps=shop.getElementsByTagName('p'),
					uls=shop.getElementsByTagName('ul');
					for(var i in ps){
						ps[i].id=i;
						ps[i].onclick=function(){
							var u=uls[this.id];
							if(u.style.display=='block'){
								u.style.display='none';
							}else{
								u.style.display='block';
							}	
						}
					}
			}
		</script>
	</head>
	<body>
		<!--基本信息-->
		<div class="msg-container">
	        <img src="${personalCenterVo.customer.headimgurl}" alt="" class="img-circle">
	        <div class="msg">
	            <h4>${personalCenterVo.customer.name}</h4>
	            <c:if test="${personalCenterVo.customer.kind eq '0'}">
            		<p class="user-level">普通用户</p>
            	</c:if>
            	<c:if test="${personalCenterVo.customer.kind eq '1'}">
            		<p class="user-level">会员用户</p>
            	</c:if>
	            
	        </div>
		</div>
        <!--提现-->
        <div class="withdrawal-management">
        	<div class="account-balance">账户余额：<span class="balance">￥${personalCenterVo.customer.remainMoney}</span></div>
            <a href="${path}/weChat/wcWithdrawCashInput?customerId=${personalCenterVo.customer.id}" class="withdrawals"><span class="glyphicon glyphicon-credit-card" aria-hidden="true"></span>提现</a>
        </div>
        <!--我的收藏-->
        <div class="favorites-container">
        	<a href="javascript:void(0);">
				<p class="favorites">
					<span class="glyphicon glyphicon-piggy-bank" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;
					<span>我的收藏</span>
				</p>
	            <span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>
	        </a>
        </div>
		<!--佣金模块-->
		<div class="commission">
			<div class="commission-module">
		    	<p class="color-blue">${personalCenterVo.myAgentNum}</p>
		        <div>我的代理</div>
		    </div>
		    <div class="commission-module">
		    	<p class="color-red">￥${personalCenterVo.customer.commission}</p>
		        <div>我的佣金</div>
		    </div>
		    <div class="commission-module">
		    	<p class="color-yellow">￥${personalCenterVo.customer.cash}</p>
		        <div>已提现佣金</div>
		    </div>
		</div>
		
		<!--代理管理-->
        <div class="shop">
            <a href="${path}/weChat/agentList?customerId=${personalCenterVo.customer.id}" class="shop-content">
                <p class="shop-content-tit">
                    <span class="glyphicon glyphicon-user" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;
                    <span>我的代理</span>
                </p>
              	 <span class="glyphicon glyphicon-menu-right" aria-hidden="true">
            </a>
            <div class="line"></div>
            <a href="${path}/weChat/promotionList?customerId=${personalCenterVo.customer.id}" class="shop-content">
                <p class="shop-content-tit">
                    <span class="glyphicon glyphicon-send" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;
                    <span>我的推广</span>
                </p>
                 <span class="glyphicon glyphicon-menu-right" aria-hidden="true">
            </a>
            <div class="line"></div>
            <a href="${path}/weChat/orderList?customerId=${personalCenterVo.customer.id}" class="shop-content">
                <p class="shop-content-tit">
                    <span class="glyphicon glyphicon-piggy-bank" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;
                    <span>我的订单</span>
                </p>
                 <span class="glyphicon glyphicon-menu-right" aria-hidden="true">
            </a>  
		</div>
        <!--导航-->
        <div class="main-nav">
        	<a href="${path}/weChat/wcGoodsList?customerId=${personalCenterVo.customer.id}" class="nav-icon">
            	<img src="${path}/platform/theme/distributionSystem/images/home1.png" />
            	<span>首页</span>
            </a>
            <div class="navLine"></div>
            <a href="${path}/weChat/shoppingCart?customerId=${personalCenterVo.customer.id}" class="nav-icon">
            	<img src="${path}/platform/theme/distributionSystem/images/cart.png" />
            	<span>购物车</span>
            </a>
            <div class="navLine"></div>
            <a href="javascript:void(0);" class="nav-icon">
            	<img src="${path}/platform/theme/distributionSystem/images/women01.png" />
            	<span>个人中心</span>
            </a>
        </div>
	</body>
</html>