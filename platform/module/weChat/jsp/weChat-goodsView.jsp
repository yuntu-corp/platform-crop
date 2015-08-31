<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
	<head>
		<script src="${path}/platform/common/js/jquery-1.11.1.js"></script>
		<meta name="viewport" content="width=320,user-scalable=no,target-densitydpi=medium-dpi" />
		<meta name="viewport" content="width=device-width,user-scalable=no,target-densitydpi=medium-dpi" />
		<link href="${path}/platform/theme/default/component/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet">
		<link href="${path}/platform/theme/distributionSystem/css/productInformation.css" type="text/css" rel="stylesheet">
		<title>商品信息</title>
		<script type="text/javascript">
			function addShoppingCart() {
				$.ajax({
				 	type: "POST",
				 	url: "${path}/weChat/addShoppingCart?goodsId=${model.id}&customerId=${customerId}",
				  	processData:true,
				  	success: function(){
				  		$("#successTip").fadeIn(500, function(){
				  			setTimeout(function () { 
				  				$("#successTip").fadeOut(500);
				  		    }, 1000);
				  		});
				  	}
				});
			};
			function buyNow() {
				$.ajax({
				 	type: "POST",
				 	url: "${path}/weChat/addShoppingCart?goodsId=${model.id}&customerId=${customerId}",
				  	processData:true,
				  	success: function(){
				  		window.location.href="${path}/weChat/shoppingCart?customerId=${customerId}";
				  	}
				});
			};
			window.onload=function(){
				var titles=$(".notice-tit li");
				var divs=$(".notice-con div");
				//遍历titles下的所有lionmouseover
				for(var i=0;i<titles.length;i++){
					titles[i].id=i;
					titles[i].onclick=function(){
						//清除所有li上的class
						for(var j=0;j<titles.length;j++){
							titles[j].className='';
							divs[j].style.display='none';
						}
						//设置当前为高亮显示
						this.className='select';
						divs[this.id].style.display='block';
					}
				}
			}
		</script>
	</head>
	<body>
		<!--header-->
		<div id="1" class="appraisal-header">
        	<a href="javascript:history.go(-1);"><span class="glyphicon glyphicon-menu-left" aria-hidden="true"></span></a>
			<span class="title">商品详情</span>
		</div>
		<!--基本信息-->
		<img src="${path}/platform/images/${model.picUrls[0]}" class="product-banner">
        
        <div class="product-information">
			<div class="product-left">
				<div class="product-name">${model.name}</div>
				<div class="product-msg">
					<span class="product-money">￥${model.price}</span>
                    <span class="product-primary">原价：${model.originalPrice}</span>
					<span class="product-sales">销量：${model.totalCount}</span>
				</div>
			</div>
			<a href="javascript:void(0);" class="product-collect">
				<img src="${path}/platform/theme/distributionSystem/images/Heart1.png" class="collect-con">
				<span class="collect">收藏</span>
			</a>
            <div class="product-line"></div>
		</div>
		<!--商品信息、宝贝评价-->   
		<div id="notice" class="notice">
        	<div id="notice-tit" class="notice-tit">
            	<ul>
                	<li class="select">
                    	<a href="javascript:void(0);">商品详情</a>
                    </li>
                    <li>
                    	<a href="javascript:void(0);">用户评价</a>
                    </li>
                </ul>
            </div>
            <div id="notice-con" class="notice-con">
            	<div class="mod" style="display:block;">
                	<ul>
                		<c:forEach items="${model.picUrls}" var="picUrl" begin="1" >
	                		<li>
	                        	<img src="${path}/platform/images/${picUrl}" class="notice-img">
	                        </li>
						</c:forEach>
                    </ul>
                </div>
                <div class="mod" style="display:none;">
                	<ul class="mod-container">
                		<c:forEach items="${evaluationList}" var="evaluation">
	                    	<li>
	                        	<img src="${path}/platform/theme/distributionSystem/images/avatar.jpg" class="mod-img">
	                            <p class="mod-msg">
	                            	<span class="mod-namemsg">
	                                	<span class="mod-nickname">${evaluation.customer.name}</span>
	                                    <span class="mod-date">${evaluation.ft}</span>
	                                </span>
	                                <span class="appraisal">${evaluation.content}</span>
	                                <span class="mod-name">${evaluation.goods.name}</span>
	                            </p>
	                        </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
        <!---->
		<div class="buy-now">
            <a href="javascript:void(0);" onclick="addShoppingCart();" class="now-car">
            	<span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>
            	<span class="cart-icon">加入购物车</span>
            </a>
            <a href="javascript:void(0);" onclick="buyNow();" class="buy-button">立即购买</a>
		</div>
		<div id="successTip" style="background-color: #000;width: 40%;line-height: 60px; position: absolute;left: 30%;top: 40%;opacity: 0.5;text-align: center;border-radius: 5px;font-size: 22px;color: #F99000;display: none;">添加成功</div>
	</body>
</html>