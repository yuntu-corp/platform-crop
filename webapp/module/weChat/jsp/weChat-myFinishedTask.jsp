<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp"%>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<title>已完成任务</title>
<link rel="stylesheet" href="${path}/module/weChat/agile-lite/assets/agile/css/agile.layout.css">
<link rel="stylesheet" href="${path}/module/weChat/agile-lite/assets/agile/css/flat/flat.component.css">
<link rel="stylesheet" href="${path}/module/weChat/agile-lite/assets/agile/css/flat/flat.color.css">
<link rel="stylesheet" href="${path}/module/weChat/agile-lite/assets/agile/css/flat/iconline.css">
<link rel="stylesheet" href="${path}/module/weChat/agile-lite/assets/agile/css/flat/iconform.css">
<link rel="stylesheet" href="${path}/module/weChat/agile-lite/assets/component/timepicker/timepicker.css">
<link rel="stylesheet" href="${path}/module/weChat/agile-lite/assets/app/css/app.css">

<link href="${path }/platform/common/css/tastnote.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${path }/platform/theme/default/component/bootstrap/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="${path }/platform/theme/default/component/bootstrap/css/bootstrap-theme.css" />
<link rel="stylesheet" type="text/css" href="${path }/platform/theme/default/component/bootstrap/css/animate.css"  />
<style>
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
<script src="${path }/platform/common/js/jquery.countdown.js"></script>
</head>

<body>
	<div id="section_container">
		<section id="form_section" data-role="section" class="active">
			<article data-role="article" id="article_unreceive" data-scroll="verticle" class="active" style="top: 0px; bottom: 0px;">
				<div class="scroller">
					<c:if test="${taskList.size()<=0}">
						<div class="jump-nothing">
							<div class="nothing-icon"><img src="${path}/module/weChat/jump/images/aside/none.png" style="box-sizing:content-box;" class="nothing-img"></div>
							<p class="nothing-text">您还没有相关消息</p>
							<a href="${path }/weChat/taskList?employeeId=${employee.id}" class="nothing-button">随便逛逛</a>
						</div>
					</c:if>
					<c:if test="${taskList.size()>0}">
						<ul class="listitem">
							<c:forEach items="${taskList }" var="task">
								<li><a
									href="${path }/weChat/taskView?id=${task.id}&employeeId=${employeeId}">
										<div class="img appimg" style="padding-left: 5px;">
											<img alt="" src="${task.publisher.headimgurl}"
												style="position: absolute; top: 0; left: 0; width: 64px; border-radius: 50%;" />
										</div>
										<div class="text" style="padding-left: 10px; color: #000">
											${task.title } <small> 发布时间：<fmt:formatDate
													value="${task.createTime }" pattern="yyyy-MM-dd" /> <br />
												发布人：${task.publisher.name }
											</small>
										</div>
								</a></li>
							</c:forEach>
						</ul>
					</c:if>
				</div>
			</article>
		</section>
	</div>
	<script src="${path }/platform/theme/default/component/bootstrap/js/bootstrap.min.js"></script>
	<script src="${path }/platform/theme/default/component/bootstrap/js/bootstrap-datetimepicker.js"></script>
	<script src="${path }/platform/theme/default/component/bootstrap/js/locales/bootstrap-datetimepicker.fr.js"></script>
	<!--- third --->
	<script src="${path}/module/weChat/agile-lite/assets/third/jquery/jquery-2.1.3.min.js"></script>
	<script src="${path}/module/weChat/agile-lite/assets/third/jquery/jquery.mobile.custom.min.js"></script>
	<script src="${path}/module/weChat/agile-lite/assets/third/zepto/zepto.min.js"></script>
	<script src="${path}/module/weChat/agile-lite/assets/third/iscroll/iscroll-probe.js"></script>
	<script src="${path}/module/weChat/agile-lite/assets/third/arttemplate/template-native.js"></script>
	<!-- agile -->
	<script type="text/javascript" src="${path}/module/weChat/agile-lite/assets/agile/js/agile.js"></script>
	<!--- bridge --->
	<script type="text/javascript" src="${path}/module/weChat/agile-lite/assets/bridge/exmobi.js"></script>
	<script type="text/javascript" src="${path}/module/weChat/agile-lite/assets/bridge/agile.exmobi.js"></script>
	<!-- app -->
	<script src="${path}/module/weChat/agile-lite/assets/component/timepicker/agile.timepicker.js"></script>
	<script type="text/javascript" src="${path}/module/weChat/agile-lite/assets/component/extend.js"></script>
	<script type="text/javascript" src="${path}/module/weChat/agile-lite/assets/app/js/app.js"></script>

	<script type="text/javascript">
	</script>

</body>
</html>