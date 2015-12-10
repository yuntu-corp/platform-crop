<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp"%>
<html>
	<head>
		<meta charset="utf-8"/>
		<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
		<title>我的评价</title>
		<link rel="stylesheet" href="${path}/module/weChat/agile-lite/assets/agile/css/agile.layout.css">
		<link rel="stylesheet" href="${path}/module/weChat/agile-lite/assets/agile/css/flat/flat.component.css">
		<link rel="stylesheet" href="${path}/module/weChat/agile-lite/assets/agile/css/flat/flat.color.css">
		<link rel="stylesheet" href="${path}/module/weChat/agile-lite/assets/agile/css/flat/iconline.css">
		<link rel="stylesheet" href="${path}/module/weChat/agile-lite/assets/app/css/app.css">
		<style>
			/* starbox */
			.clearfix:after{content:".";display:block;height:0;clear:both;visibility:hidden}
			.clearfix{display:inline-table}
			*html .clearfix{height:1%}
			.clearfix{display:block}
			*+html .clearfix{min-height:1%}
			.fl{float:left;}
			
			.rev_pro li .revtit{text-align:right;display:block;float:left;}
			.revinp{float:left;display:inline;}
			.level .level_solid,.level .level_hollow{float:left;background-image:url("${path}/module/weChat/others/img/icon2.png");background-repeat:no-repeat;display:inline-block;width:15px;height:15px;}
			.level .level_solid{background-position:0px 0px;}
			.level .level_hollow{background-position:-21px 0px;}
			.revgrade{margin-left:20px;}
			/* starbox */
			a:link{ text-decoration:none;}/*未被访问过的链接不显示下划线*/
			a:active{ text-decoration:none;}/*正在点击的链接文本闪烁*/
			a:hover{ text-decoration:none;}/*鼠标移入的链接文字有下划线*/
			a:visited{ text-decoration:none;}/*已被访问过链接*/
			.jump-nothing{ text-align:center;}
.nothing-icon{ background-color:#e1e1e1; border-radius:50%; width:100px; height:100px; margin:60px auto 10px;}
.nothing-img{ width:50px; height:50px; padding:25px;}
.nothing-text{ margin-bottom:40px;}
.nothing-button{ border:1px solid #3190e8; border-radius:2px; padding:6px 30px; color:#3190e8; background-color:#fff;}
			
		</style>
	</head>
	<body>
		<div id="section_container">
			<section id="slider_section" data-role="section" class="active" data-aside-left="#left_reveal_aside">
				<header>
					<div id="tabbarOuter" data-scroll="horizontal">
						<div class="scroller">
							<div class="tabbar" style="width:100%;">
								<a class="tab active" data-role="tab" href="#page1" data-toggle="page">来自他人</a>
								<a class="tab" data-role="tab" href="#page2" data-toggle="page">评价他人</a>
							</div>
						</div>
					</div>
				</header>
				
				<article data-role="article" id="main_article" class="active" style="top:44px;bottom:0;overflow: hidden;">
					<div id="sliderPage" data-role="slider" class="full">
						<div class="scroller">
							<div id="page1" class="slide" data-role="page" data-scroll="pulldown">
								<div class="scroller">
									<div id="slide" data-role="slider" class="full">
										<c:if test="${receiveList.size()<=0 }">
											<div class="jump-nothing">
												<div class="nothing-icon"><img src="${path}/module/weChat/jump/images/aside/none.png" style="box-sizing:content-box;" class="nothing-img"></div>
												<p class="nothing-text"> 您还没有相关消息 </p>
												<a href="${path }/weChat/taskList?employeeId=${employee.id}" class="nothing-button">随便逛逛</a>
											</div>
										</c:if>
										<c:if test="${receiveList.size()>0 }">
											<ul class="listitem">
												<c:forEach var="evaluation" items="${receiveList }">
													 <li>
														<div class="img appimg">
															<img alt="" src="${evaluation.publisher.headimgurl }" style="position:absolute;top:0;left:0;width:64px; border-radius:50%;"/>
														</div>
														<div class="text">
															<small style="color:#000;">评价人：${evaluation.publisher.name}</small>
															<small style="color:#000;">项目：${evaluation.task.title}</small>
															<small style="color:#000;">评价内容：${evaluation.content}</small>
															<div id="star">
																<ul class="rev_pro clearfix">
																	<li style="border-bottom:none; padding:0; font-size:12px;">
																		<span class="revtit" style="margin-left:px;">综合评级：</span>
																		<div class="revinp">
																			<span id="test" class="level">
																				<c:if
																					test="${evaluation.level==0}">
																					<c:forEach var="x" begin="1" end="5" step="1">
																						<i class="level_hollow" cjmark=""></i>
																					</c:forEach>
																				</c:if> <c:if test="${evaluation.level!=0}">
																					<c:forEach var="x" begin="1"
																						end="${evaluation.level}" step="1">
																						<i class="level_solid" cjmark=""></i>
																					</c:forEach>
																					<c:forEach var="x" begin="${evaluation.level+1}"
																						end="5" step="1">
																						<i class="level_hollow" cjmark=""></i>
																					</c:forEach>
																				</c:if>
																			</span>
																		</div>
																	</li>
																</ul>
															</div><!--star end-->
		
															<small style="color:#000; margin-top:0;">评价时间：<fmt:formatDate
																	value="${evaluation.createTime }"
																	pattern="yyyy-MM-dd HH:mm" /></small>
														</div>
													</li>
												</c:forEach>
											</ul>
										</c:if>
									</div>
								</div>
							</div>
							<div id="page2" class="slide" data-role="page" data-scroll="verticle">
								<div class="scroller">
									<c:if test="${publishList.size()<=0 }">
										<div class="jump-nothing">
											<div class="nothing-icon"><img src="${path}/module/weChat/jump/images/aside/none.png" style="box-sizing:content-box;" class="nothing-img"></div>
											<p class="nothing-text"> 您还没有相关消息 </p>
											<a href="${path }/weChat/taskList?employeeId=${employee.id}" class="nothing-button">随便逛逛</a>
										</div>
									</c:if>
									<c:if test="${publishList.size()>0 }">
										<ul class="listitem">
											<c:forEach var="evaluation" items="${publishList }">
												<li>
													<div class="img appimg">
														<img alt="" src="${evaluation.receiver.headimgurl }" style="position:absolute;top:0;left:0;width:64px; border-radius:50%;"/>
													</div>
													<div class="text">
														<small style="color:#000;">项目发布人：${evaluation.task.publisher.name}</small>
														<small style="color:#000;">项目：${evaluation.task.title}</small>
														<small style="color:#000;">评价内容：${evaluation.content}</small>
														<div id="star">
															<ul class="rev_pro clearfix">
																<li style="border-bottom:none; padding:0; font-size:12px;">
																	<span class="revtit" style="margin-left:px;">综合评级：</span>
																	<div class="revinp">
																		<span id="test" class="level">
																			<c:if
																				test="${evaluation.level==0}">
																				<c:forEach var="x" begin="1" end="5" step="1">
																					<i class="level_hollow" cjmark=""></i>
																				</c:forEach>
																			</c:if> <c:if test="${evaluation.level!=0}">
																				<c:forEach var="x" begin="1"
																					end="${evaluation.level}" step="1">
																					<i class="level_solid" cjmark=""></i>
																				</c:forEach>
																				<c:forEach var="x" begin="${evaluation.level+1}"
																					end="5" step="1">
																					<i class="level_hollow" cjmark=""></i>
																				</c:forEach>
																			</c:if>
																		</span>
																	</div>
																</li>
															</ul>
														</div>
														<!--star end-->
														<small style="color:#000; margin-top:0;">评价时间：
															<fmt:formatDate value="${evaluation.createTime }" pattern="yyyy-MM-dd HH:mm" />
														</small>
													</div>
												</li>
											</c:forEach>
										</ul>
									</c:if>
								</div>
							</div>
						</div>
					</div>
				</article>
			</section>
		</div>
		
		<!--- third --->
		<script src="${path}/module/weChat/agile-lite/assets/third/jquery/jquery-2.1.3.min.js"></script>
		<script src="${path}/module/weChat/agile-lite/assets/third/jquery/jquery.mobile.custom.min.js"></script>
		<script src="${path}/module/weChat/agile-lite/assets/third/iscroll/iscroll-probe.js"></script>
		<script src="${path}/module/weChat/agile-lite/assets/third/arttemplate/template-native.js"></script>
		<!-- agile -->
		<script type="text/javascript" src="${path}/module/weChat/agile-lite/assets/agile/js/agile.js"></script>		
		<!--- bridge --->
		<script type="text/javascript" src="${path}/module/weChat/agile-lite/assets/bridge/exmobi.js"></script>
		<script type="text/javascript" src="${path}/module/weChat/agile-lite/assets/bridge/agile.exmobi.js"></script>
		<!-- app -->
		<script type="text/javascript" src="${path}/module/weChat/agile-lite/assets/app/js/app.js"></script>
		<script>
		
		$('#slider_section').on('sectionshow', function(){
			A.Component.scroll('#tabbarOuter');
		});
		$('#main_article').on('articleload', function(){
			A.Slider('#slide', {
				dots : 'right'
			});			
			A.Slider('#sliderPage', {
				dots : 'hide'
			});
		});
		</script>
	</body>
</html>