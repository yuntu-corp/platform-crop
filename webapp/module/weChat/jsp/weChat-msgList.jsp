<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp"%>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<title>待处理消息</title>
 <link href="${path }/module/weChat/jump/css/jump-demo.css" rel="stylesheet" type="text/css">
 <link rel="stylesheet" href="${path}/module/weChat/agile-lite/assets/agile/css/agile.layout.css">
 <link rel="stylesheet" href="${path}/module/weChat/agile-lite/assets/app/css/app.css">
<style>
	.jump-content ul{ margin:0;}
	.listitem a{ background-color:#fff; border-bottom:1px solid #ddd;}
	.jump-menu .menu li{ width:50%;}
	.listitem .text{ padding-left:76px;}
	.listitem li{ position:relative;}
	.listitem .appimg{ position:absolute; }
	small{ font-size:12px;}
	.normal{ border:1px solid #ddd; color:#777; background-color:#fff;}
	.normal:active{ background-color:#ddd; border-color:#ddd;}
	button,img,ul,li,{-webkit-user-select: none;/*禁用手机浏览器的用户选择功能*/ -moz-user-select: none;}
	.listitem btton{ float:right; display:block;}
	button{ float:right;}
	.jump-content .listitem li{ border:none;}
	button.outline{ border-color:#3190e8;}
	.outline:active{ background-color:#3190e8;}

	#lean_overlay {position: fixed;z-index:100;top: 0px;left: 0px;height:100%;width:100%;background: #000;display: none;}
	.center .flatbtn-blu{border-color: rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.25);background-color: #3190e8;color: #edf4f9;border-radius: 2px; width:100%; margin-top:7px; line-height:38px; font-size:16px;}
	.center .flatbtn-blu:active{ background-color:#217cd1;}
	.loginmodal{display:none;padding: 24px 20px;background: #f3f6fa;border-radius: 4px;box-shadow: 0 1px 5px rgba(0, 0, 0, 0.5); width:80%; margin:auto;}
	.txtfield{ width: 98%; color: #7988a3; border:none; border-radius:2px; height:100px; padding:6px 2px;}

	#section_container{position: static;top:0; right:0; bottom :0; left:0; height: 100%; width: 100%; overflow: hidden; z-index: 3;}
</style>
</head>
<body>
	<div class="jump-container" >
		<div class="jump-content">
			<div class="jump-menu">
				<ul class="menu">
					<li class="menu-current">任务请求</li>
					<li class="menu-other">任务还价</li>
				</ul>
				
				<div class="content menu-current">
					<c:if test="${unTreatedMsgListByOthers.size()<=0}">
						<div class="jump-nothing">
							<div class="nothing-icon"><img src="${path}/module/weChat/jump/images/aside/none.png" style="box-sizing:content-box;" class="nothing-img"></div>
							<p class="nothing-text">您还没有相关消息</p>
							<a href="${path }/weChat/taskList?employeeId=${employee.id}" class="nothing-button">随便逛逛</a>
						</div>
					</c:if>
					<c:if test="${unTreatedMsgListByOthers.size()>0}">
						<ul class="listitem">
							<c:forEach items="${unTreatedMsgListByOthers}" var="taskFromOthers" varStatus="v">
								<li style="border-bottom:1px solid #ddd;padding: 0px;">
									<a href="${path }/weChat/taskView?id=${taskFromOthers.id}&employeeId=${employee.id}" style="border-bottom:none;padding: 18px 16px;">
										<div>
											<img src="${taskFromOthers.publisher.headimgurl }" class="appimg">
											<div class="text">
												任务名称：${taskFromOthers.title }
												<small>发布人：${taskFromOthers.publisher.name}</small>
												<small>虚拟币：￥${taskFromOthers.preBitcoin }</small>
												<small>工期：${taskFromOthers.durTime}</small>
											</div>
										</div>
									</a>
									<hr style="width:94%; margin:auto;">
									<div class="button" style="overflow:hidden; margin-top:10px; padding-bottom:18px;">
										<button class="normal" type="button" href="#loginmodal_${v.count }" id="modaltrigger_${v.count }">拒绝</button>
										<button class="normal" type="button" onclick="addMoney('${taskFromOthers.id}','${taskFromOthers.publisher.id}')">加价</button>
										<button class="outline" type="button" onclick="acceptTask('${taskFromOthers.id}')">确定</button>
									</div>
								</li>
								<div id="loginmodal_${v.count }" class="loginmodal">
									<textarea placeholder="请填写您拒绝此项目的理由，有助于方便发布者参考" type="text" name="username" id="reason_${v.count }" class="txtfield" tabindex="1"></textarea>
									<div class="center" style=" display: block; text-align: center; ">
										<input type="button" id="loginbtn_${v.count }" class="flatbtn-blu hidemodal" value="确定" tabindex="3" onclick="refuseTask('${taskFromOthers.id}','${v.count }')">
									</div>
								</div>
							</c:forEach>
						</ul>
					</c:if>
				</div>

				<div class="content menu-current" style="display:none;">
					<c:if test="${dickerList.size()<=0 }">
						<div class="jump-nothing">
							<div class="nothing-icon"><img src="${path}/module/weChat/jump/images/aside/none.png" style="box-sizing:content-box;" class="nothing-img"></div>
							<p class="nothing-text">您还没有相关消息</p>
							<a href="${path }/weChat/taskList?employeeId=${employee.id}" class="nothing-button">随便逛逛</a>
						</div>
					</c:if>
					<c:if test="${dickerList.size()>0 }">
						<ul class="listitem">
							<c:forEach items="${dickerList }" var="dicker" varStatus="d">
								<li style="border-bottom:1px solid #ddd;padding: 0px;">
									<a href="${path }/weChat/taskView?id=${dicker.task.id}&employeeId=${employee.id}" style="border-bottom:none;padding: 18px 16px;">
										<div>
											<img src="${dicker.publisher.headimgurl}" class="appimg">
											<div class="text">
												任务名称：${dicker.task.title }
												<small>还价人：${dicker.publisher.name}</small>
												<small>发布时虚拟币：￥${dicker.task.preBitcoin }</small>
												<small>还价时虚拟币：￥${dicker.bitcoin}</small>
												<small>工期：${dicker.task.durTime }</small>
											</div>
										</div>
									</a>
									<hr style="width:94%; margin:auto;">
									<div class="button" style="overflow:hidden; margin-top:10px; padding-bottom:18px;">
										<button class="normal" type="button" onclick="refuseDicker('${dicker.id}')">拒绝</button>
										<button class="outline" type="button" onclick="acceptDicker('${dicker.id}')">确定</button>
									</div>
								</li>
							</c:forEach>
						</ul>
					</c:if>
				</div>
				
				
			</div>
		</div>
	</div>
	<script src="${path }/module/weChat/jump/js/jquery-2.1.3.min.js"></script>
	<script src="${path }/module/weChat/jump/js/zepto.min.js"></script>
	<script type="text/javascript" src="${path}/module/weChat/agile-lite/assets/agile/js/agile.js"></script>
	<script src="${path}/module/weChat/agile-lite/assets/third/jquery/jquery.mobile.custom.min.js"></script>

	<script>
		$(".menu li").click(function(){
			$(".menu li").addClass("menu-other");
			$(this).removeClass("menu-other").addClass("menu-current");
			var index = $(".menu li").index(this);
			$(".content").hide();
			$(".content").eq(index).fadeIn(200);
		})
	</script>
	
	<script type="text/javascript">
		$(function(){
			$('#loginform').submit(function(e){
				return false;
			});
			<c:forEach items="${unTreatedMsgListByOthers}" var="taskFromOthers" varStatus="v">
				$('#modaltrigger_${v.count}').leanModal({ top: 110, overlay: 0.45, closeButton: ".hidemodal" });
			</c:forEach>
		});
	</script>

	<script>
		(function($) {
			$.fn.extend({
				leanModal: function(options) {
					var defaults = {
						top: 100,
						overlay: 0.5,
						closeButton: null
					};
					var overlay = $("<div id='lean_overlay'></div>");
					$("body").append(overlay);
					options = $.extend(defaults, options);
					return this.each(function() {
						var o = options;
						$(this).click(function(e) {
							var modal_id = $(this).attr("href");
							$("#lean_overlay").click(function() {
								close_modal(modal_id)
							});
							$(o.closeButton).click(function() {
								close_modal(modal_id)
							});
							var modal_height = $(modal_id).outerHeight();
							var modal_width = $(modal_id).outerWidth();
							$("#lean_overlay").css({
								"display": "block",
								opacity: 0
							});
							$("#lean_overlay").fadeTo(200, o.overlay);
							$(modal_id).css({
								"display": "block",
								"position": "fixed",
								"opacity": 0,
								"z-index": 11000,
								"left": 50 + "%",
								"margin-left": -(modal_width / 2) + "px",
								"top": o.top + "px"
							});
							$(modal_id).fadeTo(200, 1);
							e.preventDefault()
						})
					});
					function close_modal(modal_id) {
						$("#lean_overlay").fadeOut(200);
						$(modal_id).css({
							"display": "none"
						})
					}
				}
			})
		})(jQuery);
	</script>

	<script type="text/javascript">
		function refuseTask(taskIdVal,count) {
			if($("#reason_" + count).val().trim().length<=0||$("#reason_" + count).val().trim().length>100){
				A.alert("请输入1~100字的理由！");
				return;
			}
			//显示加载中...
			 A.showMask(function(){
		    	//A.showToast('您已关闭请求');
		    });
			//$("#refuseTaskButton").attr("disabled","disabled");
			 A.ajax({
				url : "${path }/weChat/refuseTask?taskId=" + taskIdVal
						+ "&employeeId=${employee.id}&refuseReason= " + $("#reason_" + count).val(),//拒绝任务的地址
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