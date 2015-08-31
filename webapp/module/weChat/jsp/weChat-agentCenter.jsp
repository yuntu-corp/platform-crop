<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
	<head>
		<meta name="viewport" content="width=320,user-scalable=no,target-densitydpi=medium-dpi" />
		<meta name="viewport" content="width=device-width,user-scalable=no,target-densitydpi=medium-dpi" />
		<link href="${path}/platform/theme/default/component/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet">
		<link href="${path}/platform/theme/distributionSystem/css/b.css" rel="stylesheet" type="text/css">
		<title>代理中心</title>
		<script type="text/javascript">
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
		<div class="msg_container">
			<div class="msg_bg">
		        <img src="${agentCenterVo.customer.headimgurl}" alt="" class="img-circle">
		        <div class="msg">
		            <h4>${agentCenterVo.customer.name}</h4>
		            <p>
		            	用户级别：
		            	<c:if test="${agentCenterVo.customer.kind eq '0'}">
		            		普通用户
		            	</c:if>
		            	<c:if test="${agentCenterVo.customer.kind eq '1'}">
		            		会员
		            	</c:if>
	            	</p>
		           <%--  <c:if test="${agentCenterVo.customer.kind eq '0'}">
		            	<span class="moneycoin">点此链接购买成为代理</span>
		            </c:if> --%>
		        </div>
			</div>
		</div>
		<!--佣金模块-->
		<div class="Commission_module">
			<div class="Listings">
		    	<p class="Listungs_Nub">${agentCenterVo.myAllAgentNum}</p>
		        <div>我的代理</div>
		    </div>
		    <div class="Commission">
		    	<p class="Commission_Amount">￥${agentCenterVo.customer.commission}</p>
		        <div>我的佣金</div>
		    </div>
		    <div class="Withdrawal_commission">
		    	<p class="Withdrawal_Amount">￥${agentCenterVo.customer.cash}</p>
		        <div>已提现佣金</div>
		    </div>
		</div>
		<c:if test="${agentCenterVo.customer.kind eq '0'}">
			<!--提示信息-->
			<div class="note">
				<p>亲！您还未获得成为代理的资格，点击立即购买即可成为代理，坐享高额分红。</p>
			</div>
		</c:if>
		<!--代理管理-->
		<div class="shop" id="shop">
			<div class="myListings">
		    	<p class="myListings_titl">
		            <span class="glyphicon glyphicon-user" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;
		            <span>我的代理</span>
		            <!-- <span class="glyphicon glyphicon-menu-down" aria-hidden="true"></span> -->
		        </p>
		        <ul style="display: block;">
		        	<c:forEach items="${agentCenterVo.levelList}" var="level">
			        	<li class="myPromotion">
			                <div class="Acting_level">
			                    <span class="glyphicon glyphicon-heart-empty" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;
			                    <span> ${level.name}</span>
			                    <span class="money">(${level.proportion}%佣金比例)</span>
			                </div>
			                <div class="Agent_Listings">${fn:length(level.subCustomerList)}人</div>
			   			</li>
		            </c:forEach>
		       </ul>
		    </div>
		    <div class="myPromotion">
				<p class="myPromotion_titl">
		            <span class="glyphicon glyphicon-send" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;
		            <span>我的推广</span>
		        </p>
		        <ul style="display: block;">
		            <li class="myPromotion">
		                <div class="Acting_level">
		                    <span class="glyphicon glyphicon-heart-empty" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;
		                    <span>我的招募</span>
		                    <div class="X">由您直接招募的总人数</div>
		                </div>
		                <div class="Agent_Listings">${agentCenterVo.myFollowerNum}人</div>
		   			</li>
		            
		            <li class="myPromotion">
		                <div class="Acting_level">
		                    <span class="glyphicon glyphicon-heart-empty" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;
		                    <span>我的代理</span>
		                    <div class="X">已付款，且订单已签收</div>
		                </div>
		                <div class="Agent_Listings">${agentCenterVo.myAgentNum}人</div>
		   			</li>
		            
					<li class="myPromotion">
		                <div class="Acting_level">
		                    <span class="glyphicon glyphicon-heart-empty" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;
		                    <span>过客</span>
		                    <div class="X">未下单或者未付款的用户</div>
		                </div>
		                <div class="Agent_Listings">${agentCenterVo.unAgentNum}人</div>
		            </li> 
		       </ul>  
		    </div>
		    <div class="myCommission">
				<p class="myCommission_titl">
		            <span class="glyphicon glyphicon-piggy-bank" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;
		            <span>我的佣金</span>
		        </p>
		        <ul style="display: block;">
		            <li class="myPromotion">
		                <div class="Acting_level">
		                    <span class="glyphicon glyphicon-usd" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;
		                    <span>未支付</span>
		                </div>
		                <div class="Agent_Listings">${agentCenterVo.unPaymentNum}</div>
		            </li>
		            <li class="myPromotion">
		                <div class="Acting_level">
		                    <span class="glyphicon glyphicon-usd" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;
		                    <span>未发货</span>
		                </div>
		                <div class="Agent_Listings">${agentCenterVo.unSendNum}</div>
		            </li> 
		            <li class="myPromotion">
		                <div class="Acting_level">
		                    <span class="glyphicon glyphicon-usd" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;
		                    <span>未收货</span>
		                </div>
		                <div class="Agent_Listings">${agentCenterVo.unReceiveNum}</div>
		            </li> 
		            <li class="myPromotion">
		                <div class="Acting_level">
		                    <span class="glyphicon glyphicon-usd" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;
		                    <span>完成</span>
		                </div>
		                <div class="Agent_Listings">${agentCenterVo.finshedNum}</div>
		            </li> 
		            <li class="myPromotion">
		                <div class="Acting_level">
		                    <span class="glyphicon glyphicon-usd" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;
		                    <span>可提现</span>
		                </div>
		                <div class="Agent_Listings">${agentCenterVo.remainCash}</div>
		            </li> 
		            <li class="myPromotion">
		                <div class="Acting_level">
		                    <span class="glyphicon glyphicon-usd" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;
		                    <span>已提现</span>
		                </div>
		                <div class="Agent_Listings">${agentCenterVo.customer.cash}</div>
		            </li>  
				</ul>
		    </div>
		</div>
	</body>
</html>