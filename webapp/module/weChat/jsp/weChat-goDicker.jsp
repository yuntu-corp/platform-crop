<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp"%>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<title>还价</title>
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
	<style>
	a:link{ text-decoration:none;}/*未被访问过的链接不显示下划线*/
a:active{ text-decoration:none;}/*正在点击的链接文本闪烁*/
a:hover{ text-decoration:none;}/*鼠标移入的链接文字有下划线*/
a:visited{ text-decoration:none;}/*已被访问过链接*/
}
</style>
</head>
<body>
	<div id="section_container">
		<section id="form_section" data-role="section" class="active">
			<header>
				<div class="titlebar">
					<a data-toggle="back"
						href="${path }/weChat/msgList?employeeId=${publisherId}"><i
						class="iconfont iconline-arrow-left"></i></a>
					<h1>还价</h1>
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
						
						<label class="label-left" >发布人</label>
						<label class="label-right">${task.publisher.name}</label>
						<hr />
						
						<label class="label-left" >任务详情</label>
						<label class="label-right">${task.content}</label>
						<hr />
        				
        				
						<label class="label-left" >当前虚拟币数</label>
						<label class="label-right">￥${task.preBitcoin}</label>
						<hr />


						<label class="label-left" for="age">还价虚拟币数</label>
						<div class="label-right">
							<input name="dicker.bitcoin" id="bitcoin" type="number"
								placeholder="请输入还价虚拟币数" min="1"  required />
						</div>
						<hr />
					</form>
					<div class="card noborder" style="margin-top: 60px">
						<button id="btn_submit" class="block" onclick="submitDicker()">确认还价</button>
					</div>

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

	<script type="text/javascript">
		function check() {
			//验证普通form标签
			if (!$("#bitcoin")[0].checkValidity()) {
				A.alert("请输入大于0虚拟币数！");
				return false;
			} else if(Number($("#bitcoin")[0].value)>Number('${task.preBitcoin}')*10){
				A.alert("请输入小于任务发布金额10倍以内的虚拟币数！");
				return false;
			}
			return true;
		}

		function submitDicker() {
			if (check()) {
				//显示加载中...
				A.showMask(function() {
				});
				$
						.ajax({
							url : "${path }/weChat/doDicker",//提交还价的地址
							data : $("#form").serialize(),
							type : "post",
							async : false,
							success : function(data) {

								//关闭加载中...
								A.hideMask();
								if (data == "success") {
									A.alert('提示', '还价成功');
									//禁止再次点击
									$("#btn_submit").attr("disabled","disabled");
									setTimeout(function(){window.location.href = "${path }/weChat/msgList?employeeId=${publisherId}";}, 1500);
								} else {
									A.alert('提示', data);
								}

							},
							error : function(xhr, type, exception) {
								A.hideMask();
								A.alert(xhr.responseText, "Failed");
							}
						});
			}
		}
	</script>

</body>
</html>