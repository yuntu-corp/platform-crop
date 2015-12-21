<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<title>今日价格</title>
<link rel="stylesheet"
	href="${path}/module/weChat/agile-lite/assets/agile/css/agile.layout.css">
<link rel="stylesheet"
	href="${path}/module/weChat/agile-lite/assets/agile/css/flat/flat.component.css">
<link rel="stylesheet"
	href="${path}/module/weChat/agile-lite/assets/agile/css/flat/flat.color.css">
<link rel="stylesheet"
	href="${path}/module/weChat/agile-lite/assets/agile/css/flat/iconline.css">
<link rel="stylesheet"
	href="${path}/module/weChat/agile-lite/assets/app/css/app.css">

<style>
* {
	margin: 0;
	padding: 0;
	list-style-type: none;
}

article {
	background-color: #;
}

/* 价格 */
small {
	font-size: 12px;
}

.star_ul li {
	border: none;
}

.titlebar h1 {
	top: 26%;
	width: 100%;
	left: 0;
}
/*价格*/

/* starbox */
.clearfix:after {
	content: ".";
	display: block;
	height: 0;
	clear: both;
	visibility: hidden
}

.clearfix {
	display: inline-table
}

* html .clearfix {
	height: 1%
}

.clearfix {
	display: block
}

*+html .clearfix {
	min-height: 1%
}

.fl {
	float: left;
}

.rev_pro li .revtit {
	text-align: right;
	display: block;
	float: left;

	width: 75px;
}

.revinp {
	float: left;
	display: inline;
}

.level .level_solid,.level .level_hollow {
	float: left;
	background-image: url("${path}/module/weChat/others/img/icon2.png");
	background-repeat: no-repeat;
	display: inline-block;
	width: 15px;
	height: 15px;
}

.level .level_solid {
	background-position: 0px 0px;
}

.level .level_hollow {
	background-position: -21px 0px;
}

.revgrade {
	margin-left: 20px;
}
/* starbox */
</style>
</head>

<body>
	<div id="section_container">
		<div><a href="${path }/weChat/publishTask?employeeId=${employee.id}"  style=""><img style="position:fixed; bottom:20px; right:15px; z-index:1000; float:right; width:60px; height:60px;" src="${path }/module/weChat/jump/images/icon-celease.png"></a></div>
		<section id="list_more_section" data-role="section" class="active">
			<article data-role="article" id="main_article" data-scroll="verticle"
				class="active" style="bottom: 0px;">
				<div class="scroller">
					<ul class="listitem">
						<li style="background-color: #f4fcff">
							<div class="img appimg">
								<img alt="" src="${employee.headimgurl }"
									style="position: absolute; top: 12px; left: 0; width: 64px; border-radius: 50%;" />
							</div>
							<div class="text">
								${employee.name }<small style="float: right;">日期：${employee.nowDate
									}</small>
								<small >
									<span style="text-align: right; width: 75px;">今 日 价 格 ：</span><span > ${employee.currentBitcoin}</span> <br>
									<span style="text-align: right; width: 75px;">本 月 收 入 ：</span><span > ￥${employee.bitcoinIncome}</span> <br>
									<span style="text-align: right; width: 75px;">已接受任务：</span><span >  ${employee.taskCount}</span> <br>
									<span style="text-align: right; width: 75px;">已发布任务：</span><span >  ${employee.publishTaskCount}</span> <br>
								</small>
								<div id="star">
									<ul class="rev_pro clearfix">
										<li style="border-bottom: none; padding: 0; font-size: 12px; color: #aaa;">
											<span class="revtit" >综 合 评 价：</span>
											<div class="revinp">
												<span id="test" class="level"> <c:if
														test="${employee.evaluationLevel==0}">
														<c:forEach var="x" begin="1" end="5" step="1">
															<i class="level_hollow" cjmark=""></i>
														</c:forEach>
													</c:if> <c:if test="${employee.evaluationLevel!=0}">
														<c:forEach var="x" begin="1"
															end="${employee.evaluationLevel}" step="1">
															<i class="level_solid" cjmark=""></i>
														</c:forEach>
														 <c:forEach var="x" begin="${employee.evaluationLevel+1}"
															end="5" step="1">
															<i class="level_hollow" cjmark=""></i>
														</c:forEach> 
													</c:if>

												</span>
											</div>
										</li>
									</ul>
								</div>
							</div>
						</li>
						<c:forEach var="emp" items="${employeeList }">
							<c:if test="${emp.id ne employee.id}">
								<li>
									<div class="img appimg" >
										<img alt="" src="${emp.headimgurl }"
											style="position: absolute; top: 12px; left: 0; width: 64px; border-radius: 50%;" />
									</div>
									<div class="text">
										${emp.name }<small style="float: right;"></small>
										<small>
											<span style="text-align: right; width: 75px;">今 日 价 格 ：</span><span >￥${emp.currentBitcoin} </span> <br/>
											<span style="text-align: right; width: 75px;">本 月 收 入 ：</span><span >￥${emp.bitcoinIncome} </span> <br/>
											<span style="text-align: right; width: 75px;">已接受任务：</span><span > ${emp.taskCount}</span> <br/>
											<span style="text-align: right; width: 75px;">已发布任务：</span><span > ${emp.publishTaskCount}</span> <br/>
										</small>
										<div id="star">
											<ul class="rev_pro clearfix">
												<li style="border-bottom: none; padding: 0; font-size: 12px; color: #aaa;">
													<span class="revtit" >综 合 评 价：</span>
													<div class="revinp">
														<span id="test" class="level"> <c:if
																test="${emp.evaluationLevel==0}">
																<c:forEach var="x" begin="1" end="5" step="1">
																	<i class="level_hollow" cjmark=""></i>
																</c:forEach>
															</c:if> <c:if test="${emp.evaluationLevel!=0}">
																<c:forEach var="x" begin="1"
																	end="${emp.evaluationLevel}" step="1">
																	<i class="level_solid" cjmark=""></i>
																</c:forEach>
																 <c:forEach var="x" begin="${emp.evaluationLevel+1}"
																	end="5" step="1">
																	<i class="level_hollow" cjmark=""></i>
																</c:forEach> 
															</c:if>
														</span>
													</div>
												</li>
											</ul>
										</div>
									</div>
								</li>
							</c:if>
						</c:forEach>
					</ul>
				</div>
			</article>
		</section>
	</div>

	<!--- third --->
	<script
		src="${path}/module/weChat/agile-lite/assets/third/jquery/jquery-2.1.3.min.js"></script>
	<script
		src="${path}/module/weChat/agile-lite/assets/third/jquery/jquery.mobile.custom.min.js"></script>
	<script
		src="${path}/module/weChat/agile-lite/assets/third/iscroll/iscroll-probe.js"></script>
	<script
		src="${path}/module/weChat/agile-lite/assets/third/arttemplate/template-native.js"></script>
	<!-- agile -->
	<script type="text/javascript"
		src="${path}/module/weChat/agile-lite/assets/agile/js/agile.js"></script>
	<!--- bridge --->
	<script type="text/javascript"
		src="${path}/module/weChat/agile-lite/assets/bridge/exmobi.js"></script>
	<script type="text/javascript"
		src="${path}/module/weChat/agile-lite/assets/bridge/agile.exmobi.js"></script>
	<!-- app -->
	<script type="text/javascript"
		src="${path}/module/weChat/agile-lite/assets/app/js/app.js"></script>

	<script type="text/javascript">
		
	</script>


</body>
</html>