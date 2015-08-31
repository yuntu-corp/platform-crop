<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
	<head>
		<meta name="viewport" content="width=320,user-scalable=no,target-densitydpi=medium-dpi" />
		<meta name="viewport" content="width=device-width,user-scalable=no,target-densitydpi=medium-dpi" />
		<link href="${path}/platform/theme/default/component/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet">
		<link href="${path}/platform/theme/distributionSystem/css/personalCenter.css" type="text/css" rel="stylesheet">
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
		<div class="header_container">
			<img src="${personalCenterVo.customer.headimgurl}" alt="" class="img-circle">
		    <h4>${personalCenterVo.customer.name}</h4>
		    <%-- <c:if test="${personalCenterVo.customer.kind eq '0'}">
		    	<p>点此链接购买成为代理</p>
		    </c:if> --%>
		</div>
		
		<!--佣金模块-->
		<div class="Commission_container">
			<div class="listings">
		    	<p class="listungs_nub">${fn:length(personalCenterVo.myAgentList)}</p>
		        <div>我的代理</div>
		    </div>
		    <div class="commission">
		    	<p class="commission_amount">￥${personalCenterVo.customer.commission}</p>
		        <div>累计佣金</div>
		    </div>
		    <div class="withdrawal_commission">
		    	<p class="withdrawal_amount">￥${personalCenterVo.remainCash}</p>
		        <div>未提现佣金</div>
		    </div>
		</div>
		
		<!--管理模块-->
		<div class="shop" id="shop">
		    <div class="MyListings">
		        <p class="MyListings_Titl">
					<span class="glyphicon glyphicon-home" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;
					<span>我的代理</span>
		            <span class="agent_listings">${fn:length(personalCenterVo.myAgentList)}</span>
		        </p>
		        <ul style="display: block;">
		        	<c:forEach items="${personalCenterVo.myAgentList}" var="customer">
			            <li class="Mypromotion">
			            	<img src="${customer.headimgurl}" class="image_border_circle">
							<span>${customer.name}</span>
			            </li>
		            </c:forEach>
		        </ul>    
		    </div>
		    
		    <div class="MyListings">
		        <p class="MyListings_Titl">
		            <span class="glyphicon glyphicon-globe" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;
		            <span>我的跟随者</span>
		            <span class="agent_listings">${fn:length(personalCenterVo.myFollowerList)}</span>
		        </p>
		        <ul style="display: block;">
		        	<c:forEach items="${personalCenterVo.myFollowerList}" var="customer">
			            <li class="Mypromotion">
			            	<img src="${customer.headimgurl}" class="image_border_circle">
							<span>${customer.name}</span>
			            </li>
		            </c:forEach>
		        </ul>    
		    </div>
		    
		    <div class="myCommission">
			    <div class="MyListings_Titl" onclick="toWithDrawCash();">
					<span class="glyphicon glyphicon-credit-card" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;
					<span>提现申请</span>
			        <span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>
		        </div>
		    </div>
		</div>
	</body>
</html>