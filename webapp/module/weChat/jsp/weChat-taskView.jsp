<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp"%>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<title>任务详情</title>
<link rel="stylesheet"
	href="${path}/module/weChat/agile-lite/assets/agile/css/agile.layout.css">
<link rel="stylesheet"
	href="${path}/module/weChat/agile-lite/assets/agile/css/flat/flat.component.css">
<link rel="stylesheet"
	href="${path}/module/weChat/agile-lite/assets/agile/css/flat/flat.color.css">
<link rel="stylesheet"
	href="${path}/module/weChat/agile-lite/assets/agile/css/flat/iconline.css">
<link rel="stylesheet"
	href="${path}/module/weChat/agile-lite/assets/agile/css/flat/iconform.css">
<link rel="stylesheet"
	href="${path}/module/weChat/agile-lite/assets/component/timepicker/timepicker.css">
<link rel="stylesheet"
	href="${path}/module/weChat/agile-lite/assets/app/css/app.css">
</head>
<body>
	<div id="section_container">
		<section id="form_section" data-role="section" class="active">
			<header>
				<div class="titlebar">
					<a data-toggle="back"
						href=""><i
						class="iconfont iconline-arrow-left"></i></a>
					<h1>任务详情</h1>
				</div>
			</header>
			<article data-role="article" id="normal_article"
				data-scroll="verticle" class="active"
				style="top: 45px; bottom: 0px;">
				<div class="scroller">
					<form id="form" autocomplete="off"
						oninput="range_output.value=parseInt(range.value)"
						class="form-common">
						<input type="hidden" name="taskId" value="${task.id}"/>
       				    <input type="hidden" name="publisherId" value="${publisherId }"/>
        				<input type="hidden" name="receiverId" value="${receiverId }"/>
        				
						<label class="label-left" >任务名称</label>
						<label class="label-right">${task.title}</label>
						<hr />
						
						<label class="label-left" >任务类型：</label>
						<label class="label-right">${task.taskType.typeName}</label>
						<hr />
						
						<label class="label-left" >发布人</label>
						<label class="label-right">${task.publisher.name}</label>
						<hr />
						
						<label class="label-left" >任务详情</label>
						<label class="label-right">${task.content}</label>
						<hr />
        				
        				
						<label class="label-left" >任务报价</label>
						<label class="label-right">￥${task.preBitcoin}</label>
						<hr />
						
						<label class="label-left" >发布日期</label>
						<label class="label-right"><fmt:formatDate value="${task.createTime}" type="date" pattern="yyyy-MM-dd"/></label>
						<hr />
						
						<label class="label-left" >完工日期：</label>
						<label class="label-right"><fmt:formatDate value="${task.finishTime}" type="date" pattern="yyyy-MM-dd"/></label>
						<hr />
						
						
						
						<label class="label-left" >任务状态：</label>
						<label class="label-right">${task.statusVal}</label>
						<hr />
						
						<c:if test="${task.status!=0}">
							<label class="label-left" >接受人：</label>
							<label class="label-right">${task.receiver.name}</label>
							<hr />
							
							<label class="label-left" >成交价格：</label>
							<label class="label-right">￥${task.finalBitcoin}</label>
							<hr />
						</c:if>
						


						
					</form>

				</div>
			</article>
		</section>
	</div>
	<script
		src="${path }/platform/theme/default/component/bootstrap/js/bootstrap.min.js"></script>
	<script
		src="${path }/platform/theme/default/component/bootstrap/js/bootstrap-datetimepicker.js"></script>
	<script
		src="${path }/platform/theme/default/component/bootstrap/js/locales/bootstrap-datetimepicker.fr.js"></script>
	<!--- third --->
	<script
		src="${path}/module/weChat/agile-lite/assets/third/jquery/jquery-2.1.3.min.js"></script>
	<script
		src="${path}/module/weChat/agile-lite/assets/third/jquery/jquery.mobile.custom.min.js"></script>
	<script
		src="${path}/module/weChat/agile-lite/assets/third/zepto/zepto.min.js"></script>
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
	<script
		src="${path}/module/weChat/agile-lite/assets/component/timepicker/agile.timepicker.js"></script>
	<script type="text/javascript"
		src="${path}/module/weChat/agile-lite/assets/component/extend.js"></script>
	<script type="text/javascript"
		src="${path}/module/weChat/agile-lite/assets/app/js/app.js"></script>
</body>
</html>