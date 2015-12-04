<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp"%>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<title>发布任务</title>
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
						href="${path }/weChat/personalCenter?employeeId=${employeeId}"><i
						class="iconfont iconline-arrow-left"></i></a>
					<h1>发布任务</h1>
				</div>
			</header>
			<article data-role="article" id="normal_article"
				data-scroll="verticle" class="active"
				style="top: 45px; bottom: 0px;">
				<div class="scroller">
					<form id="form" autocomplete="off"
						oninput="range_output.value=parseInt(range.value)"
						class="form-common">
						<input name="employeeId" type="hidden" value="${employeeId }">
						<label class="label-left" for="user">标题</label> <label
							class="label-right"> <input name="task.title" id="title"
							type="text" placeholder="请输入标题"
							pattern="^[\u4e00-\u9fa5_a-zA-Z0-9_]{1,20}$" title="中文英文数字"
							required />
						</label>
						<hr />

						<label class="label-left" for="email">简介</label> <label
							class="label-right"> <input name="task.content"
							id="content" type="textarea" placeholder="请输入项目简介"
							autocomplete="on" required />
						</label>
						<hr />

						<label class="label-left" for="age">任务报价</label>
						<div class="label-right">
							<input name="task.preBitcoin" id="preBitcoin" type="number"
								placeholder="请输入报价" min="0" required />
						</div>
						<hr />

						<label class="label-left">完工日期</label>
						<div data-role="date" class="label-right">
							<label>请选择日期</label> <input id="finishDate" type="hidden"
								name="task.finishDate" value="" required />
						</div>
						<hr />

						<label class="label-left" for="date">完工时间</label>
						<div data-role="time" class="label-right">
							<label>请选择时间</label> <input type="hidden" id="finishTime"
								name="task.finishTime" required />
						</div>
						<hr />

						<label class="label-left">技术类型</label> <label class="label-right">
							<c:forEach items="${taskTypes }" var="taskType">
								<a href="#" data-role="radio"> <input
									name="task.taskType.id" id="type" type="radio"
									value=${taskType.id } style="left: 0; right: auto;" checked />
									<label for="male" class="black">${taskType.typeName }</label>
								</a>
							</c:forEach>
						</label>
						<hr />

						<label class="label-left">人员</label> <label class="label-right">
							<c:forEach items="${allEmployes }" var="emp">
								<c:if test="${userId!=emp.userId}">
									<a href="#" data-role="checkbox"> <input type="checkbox"
										id="employeesString" value="${emp.userId }"
										name="task.employeesString" /> <label for="baskball"
										class="black">${emp.name }</label>
									</a>
								</c:if>
							</c:forEach>
						</label>
						<hr />




					</form>
					<div class="card noborder" style="margin-top: 20px">
						<button id="btn_submit" class="block" onclick="taskSave()">发布任务</button>
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
		//当输入的日期值大于或等于当前日期返回true，日期类型为（2015-11-20）
		function compareNowDate(date) {
			var d2 = new Date(date.replace(/-/g, "/"));
			var d1 = new Date(new Date().toLocaleDateString().replace("年", "/")
					.replace("月", "/").replace("日", ""));
			//结束日期 等于 当前日期
			if (Date.parse(d1) - Date.parse(d2) == 0) {
				return true;
			}
			//结束日期 大于 当前日期
			if (Date.parse(d1) - Date.parse(d2) < 0) {
				return true;
			}
			//结束日期 小于 当前日期
			if (Date.parse(d1) - Date.parse(d2) > 0) {
				return false;
			}
			return true;
		}
		//当输入的时间值大于当前时间返回true，日期类型为（11:00）
		function compareNowTime(date,time) {
			var d2 = new Date(date.replace(/-/g, "/")+" "+time);
			var nowStr=new Date().toLocaleDateString().replace("年", "/").replace("月", "/").replace("日", "")+" "+new Date().getHours()+":"+new Date().getMinutes();
			var d1 = new Date(nowStr);
			//结束日期 等于 当前日期
			if (Date.parse(d1) - Date.parse(d2) == 0) {
				return false;
			}
			//结束日期 大于 当前日期
			if (Date.parse(d1) - Date.parse(d2) < 0) {
				return true;
			}
			//结束日期 小于 当前日期
			if (Date.parse(d1) - Date.parse(d2) > 0) {
				return false;
			}
			return true;
		}
		//自动加载
		(function() {
			//输入自动加载的内容
		})();
		function check() {
			//验证普通form标签
			if (!$("#title")[0].checkValidity()) {
				A.alert("请输入正确的标题！");
				return false;
			} else if (!$("#content")[0].checkValidity()) {
				A.alert("请输入2000字以内的简介！");
				return false;
			} else if (!$("#preBitcoin")[0].checkValidity()) {
				A.alert("请输入1万以内的整数虚拟币数！");
				return false;
			} else if($("#preBitcoin")[0].value.indexOf('0')==0){
				A.alert("请输入首字符不为0的虚拟币数！");
				return false;
			}else if ($("#finishDate")[0].value == "") {
				A.alert("请输入选择日期！");
				return false;
			} else if ($("#finishTime")[0].value == "") {
				A.alert("请输入选择时间！");
				return false;
			} else if (!compareNowDate($("#finishDate")[0].value)) {
				A.alert("请选择大于等于今日的日期！");
				return false;
			} else if (!compareNowTime($("#finishDate")[0].value,
					$("#finishTime")[0].value)) {
				A.alert("请选择大于现在的时间！");
				return false;
			}

			//验证单选
			var isCheck_type = false;
			$("input[id='type']").each(function() {
				if ($(this).is(':checked')) {
					isCheck_type = true;
				}
			});
			if (!isCheck_type) {
				A.alert("至少选择一种技术类型！");
				return false;
			}
			//验证多选
			var isCheck_employeesString = false;
			$("input[id='employeesString']").each(function() {
				if ($(this).is(':checked')) {
					isCheck_employeesString = true;
				}
			});
			if (!isCheck_employeesString) {
				A.alert("至少选择一个人！");
				return false;
			}
			return true;
		}

		function taskSave() {
			if (check()) {

				//显示加载中...
				 A.showMask(function(){
			    	//A.showToast('您已关闭请求');
			    });
				$
						.ajax({
							url : "${path }/weChat/doPublishTask",//发布任务的地址
							data : $("#form").serialize(),
							type : "post",
							async : false,
							success : function(data) {
								//关闭加载中...
								A.hideMask();
								if (data == "success") {
									A.alert('提示', '发布成功');
									//禁止再次点击
									$("#btn_submit").attr("disabled",
											"disabled");
									setTimeout(function(){window.location.href = "${path }/weChat/personalCenter?employeeId=${employeeId}";}, 1500);
								} else {
									A.alert('提示', data);
								}

							},
							error : function(xhr, type, exception) {
								A.hideMask();
								A.alert(exception, "Failed");
							}
						});
			}
		}
	</script>

</body>
</html>