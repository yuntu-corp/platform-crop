<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp"%>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<title>待处理消息</title>
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
[data-role="slider"] img {
	display: block;
}

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

.listitem li .img {
	display: block;
}

button {
	float: right;
	height: 28px;
	line-height: 23px;
	margin: 4px 0 0 6px;
}

button.outline,input.outline {
	border-color: #ddd;
	color: #777777;
}
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
		<section id="slider_section" data-role="section" class="active"
			data-aside-left="#left_reveal_aside">
			<header>
				<div id="tabbarOuter" data-scroll="horizontal">
					<div class="scroller">
						<div class="tabbar" style="width: 100%;">
							 <a class="tab active" data-role="tab"
								href="#page1" data-toggle="page">任务请求</a>
								<a class="tab " data-role="tab" href="#page2"
								data-toggle="page">任务还价</a>
						</div>
					</div>
				</div>
			</header>

			<article data-role="article" id="main_article" class="active"
				style="top: 44px; bottom: 0; overflow: hidden;">
				<div id="sliderPage" data-role="slider" class="full">
				<div class="scroller">
				<div id="page1" class="slide" data-role="page"
						data-scroll="verticle">
						<c:if test="${unTreatedMsgListByOthers.size()<=0}">
							<div class="jump-nothing">
								<div class="nothing-icon"><img src="${path}/module/weChat/jump/images/aside/none.png" style="box-sizing:content-box;" class="nothing-img"></div>
								<p class="nothing-text">
									您还没有相关消息
									
								</p>
								<a href="${path }/weChat/taskList?employeeId=${employee.id}" class="nothing-button">随便逛逛</a>
							</div>
						</c:if>
						<c:if test="${unTreatedMsgListByOthers.size()>0}">
							<c:forEach items="${unTreatedMsgListByOthers}"
								var="taskFromOthers" varStatus="v">
								<ul class="listitem">
									<li>
										<div class="img appimg">
											<img alt="" src="${taskFromOthers.publisher.headimgurl }"
												style="position: absolute; top: 0; left: 0; width: 64px; border-radius: 50%;" />
										</div>
										<div class="text">
											<a
												href="${path }/weChat/taskView?id=${taskFromOthers.id}&employeeId=${employee.id}">
												任务名称：${taskFromOthers.title } <small>发布人：${taskFromOthers.publisher.name
													}</small> <small>虚拟币：￥${taskFromOthers.preBitcoin }</small> <small>工期：${taskFromOthers.durTime
													}天</small>
											</a>

										</div>
										<hr style="margin: 4px 0;">
										<button id="refuseTaskButton"
											onclick="refuseTask('${taskFromOthers.id}')" class="outline">拒绝</button>
										<button
											onclick="addMoney('${taskFromOthers.id}','${taskFromOthers.publisher.id}')"
											class="outline">加价</button>
										<button class="submit outline"
											onclick="acceptTask('${taskFromOthers.id}')">确定</button>
									</li>
								</ul>
							</c:forEach>
						</c:if>
					</div>
					<div id="page2" class="slide" data-role="page" data-scroll="pulldown">
						<div id="slide" data-role="slider" class="full">
							<c:if test="${dickerList.size()<=0 }">
								<div class="jump-nothing">
									<div class="nothing-icon"><img src="${path}/module/weChat/jump/images/aside/none.png" style="box-sizing:content-box;" class="nothing-img"></div>
									<p class="nothing-text">
										您还没有相关消息
										
									</p>
									<a href="${path }/weChat/taskList?employeeId=${employee.id}" class="nothing-button">随便逛逛</a>
								</div>
							</c:if>
							<c:if test="${dickerList.size()>0 }">
								<c:forEach items="${dickerList }" var="dicker" varStatus="d">
									<ul class="listitem">

										<li>
											<div class="img appimg">
												<img alt="" src="${dicker.publisher.headimgurl}"
													style="position: absolute; top: 0; left: 0; width: 64px; border-radius: 50%;" />
											</div> <a
											href="${path }/weChat/taskView?id=${dicker.task.id}&employeeId=${employee.id}">
												<div class="text" style="color: black">
													任务名称：${dicker.task.title } <small>还价人：${dicker.publisher.name
														}</small> <small>发布时虚拟币：￥${dicker.task.preBitcoin }</small> <small>还价时虚拟币：￥${dicker.bitcoin
														}</small> <small>工期：${dicker.task.durTime }天</small>
												</div>
										</a>
											<hr style="margin: 4px 0;">
											<button class="outline"
												onclick="refuseDicker('${dicker.id}')">拒绝</button>
											<button class="submit outline"
												onclick="acceptDicker('${dicker.id}')">确定</button>
										</li>
									</ul>
								</c:forEach>
							</c:if>
						</div>
					</div>
					
					</div>
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
	<script>
		$('#slider_section').on('sectionshow', function() {
			A.Component.scroll('#tabbarOuter');
		});
		$('#main_article').on('articleload', function() {
			A.Slider('#slide', {
				dots : 'right'
			});
			A.Slider('#sliderPage', {
				dots : 'hide'
			});
		});
	</script>

	<script type="text/javascript">
		function refuseTask(taskIdVal) {
			//显示加载中...
			 A.showMask(function(){
		    	//A.showToast('您已关闭请求');
		    });
			//$("#refuseTaskButton").attr("disabled","disabled");
			 $.ajax({
				url : "${path }/weChat/refuseTask?taskId=" + taskIdVal
						+ "&employeeId=${employee.id}",//拒绝任务的地址
				data : null,
				type : "post",
				async : false,
				success : function(data) {
					//关闭加载中...
					A.hideMask();
					A.alert('提示', '已成功拒绝任务！');
					//禁止再次点击
					$("#btn_submit").attr("disabled","disabled");
					setTimeout(function(){window.location.reload();}, 2000);
				}
			}); 
		}
		function acceptTask(taskIdVal) {
			//显示加载中...
			 A.showMask(function(){
		    	//A.showToast('您已关闭请求');
		    });
			//$("#acceptTaskButton").attr("disabled","disabled");
			$.ajax({
				url : "${path }/weChat/acceptTask?taskId=" + taskIdVal
						+ "&employeeId=${employee.id}",//接受任务的地址
				data : null,
				type : "post",
				async : false,
				success : function(data) {
					//关闭加载中...
					A.hideMask();
					if (data == "success") {
						A.alert("您已成功接收任务！");
					} else {
						A.alert(data);
					}
					t = setTimeout("window.location.reload()", 2000);
				}
			});
		}
		function refuseDicker(dickerIdVal) {
			//显示加载中...
			 A.showMask(function(){
		    	//A.showToast('您已关闭请求');
		    });
			//$("#refuseDickerButton").attr("disabled","disabled");
			$.ajax({
				url : "${path }/weChat/refuseDicker?dickerId=" + dickerIdVal,//拒绝还价的地址
				data : null,
				type : "post",
				async : false,
				success : function(data) {
					//关闭加载中...
					A.hideMask();
					A.alert("您已拒绝此项还价！");
					t = setTimeout("window.location.reload()", 2000);
				}
			});
		}
		function acceptDicker(dickerIdVal) {
			//显示加载中...
			 A.showMask(function(){
		    	//A.showToast('您已关闭请求');
		    });
			//$("#acceptDickerButton").attr("disabled","disabled");
			$.ajax({
				url : "${path }/weChat/acceptDicker?dickerId=" + dickerIdVal,//接受还价的地址
				data : null,
				type : "post",
				async : false,
				success : function(data) {
					//关闭加载中...
					A.hideMask();
					if (data == "success") {
						A.alert("您已成功接收还价！");
					} else {
						A.alert(data);
					}
					t = setTimeout("window.location.reload()", 2000);
				}
			});
		}
		function addMoney(taskId, receiverId) {
			var str = "${basePath }/weChat/goDicker?taskId=" + taskId
					+ "&publisherId=${employee.id}&receiverId=" + receiverId;
			window.location.href = str;
		}
	</script>

</body>
</html>