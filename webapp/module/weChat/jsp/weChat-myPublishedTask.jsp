<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp"%>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<title>已发布任务</title>
 <link href="${path}/module/weChat/jump/css/jump-demo.css" rel="stylesheet" type="text/css">
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
	.loginmodal{display:none;padding:8px 20px 24px 20px;background: #f3f6fa;border-radius: 4px;box-shadow: 0 1px 5px rgba(0, 0, 0, 0.5); width:80%; margin:auto;}
	#section_container{position: static;top:0; right:0; bottom :0; left:0; height: 100%; width: 100%; overflow: hidden; z-index: 3;}
	 input[type="radio"] { pointer-events: auto; }
	 input[type="button"],textarea{-webkit-appearance:none;}/*隐藏文本框阴影*/
	</style>
</head>

<body>
	<div class="jump-container">
		<div class="jump-content">
			<div class="jump-menu">
				<c:if test="${taskList.size()<=0}">
					<div class="jump-nothing">
						<div class="nothing-icon"><img src="${path}/module/weChat/jump/images/aside/none.png" style="box-sizing:content-box;" class="nothing-img"></div>
						<p class="nothing-text">您还没有相关消息</p>
						<a href="${path }/weChat/taskList?employeeId=${employee.id}" class="nothing-button">随便逛逛</a>
					</div>
				</c:if>
				<c:if test="${taskList.size()>0}">
					<div class="content menu-current">
						<ul class="listitem">
							<c:forEach items="${taskList }" var="task" varStatus="v">
								<li style="border-bottom: 1px solid #ddd; padding: 0;">
									<a href="${path }/weChat/taskView?id=${task.id}&employeeId=${employeeId}" style=" border-bottom: none; padding: 18px 16px;">
										<div>
											<img src="${task.publisher.headimgurl}" class="appimg">
											<div class="text">
												${task.title }
												<small>发布时间：<fmt:formatDate value="${task.createTime }" pattern="yyyy-MM-dd" /></small>
												<small>发布人：${task.publisher.name }</small>
												<small>任务状态：${task.statusVal }</small>
											</div>
										</div>
									</a>
									<hr style="width:94%; margin:auto;">
									<c:if test="${task.taskType.typeKey=='xs' and task.status=='1'}">
										<div class="button" style="overflow:hidden; margin-top:10px; padding-bottom:18px;margin-right: 10px">
										<button class="outline" type="button" href="#loginmodal_${v.count }"  id="modaltrigger_${v.count }">提交审核</button>
									</div>
									</c:if>
								</li>
								<div id="loginmodal_${v.count }" class="loginmodal">
									<div style="position: relative;">
										<input type="button" value="X" id="close" style="background-color: #ddd; color: #aaa; position: absolute; top: 0px; right: -12px; padding: 0 9px; margin: 0;border-radius:1px;">
										<p style=" width: 30%; margin: auto; line-height: 36px;">提交审核</p>
									</div>
									<div style="background-color: #fff; overflow: hidden; padding: 20px 10px;">
										<label class="label-left" style="float: left; line-height: 36px; padding-left: 16%;">谈单结果：</label>
										<label class="label-right" style="float: left;">
											<a href="#" data-role="radio">
												<input type="radio" name="check_${v.count }" value="1" style="left:0;right:auto; border-radius:50%;pointer-events: auto; "/>
												<label for="male" class="black" style="line-height: 36px;">成功</label>
											</a>
											<a href="#" data-role="radio">
												<input type="radio" name="check_${v.count }" value="0" style="left:0;right:auto; border-radius:50%;pointer-events: auto; " checked/>
												<label for="female" class="black" style="line-height: 36px;">失败</label>
											</a>
										</label>
									</div>
									<div class="center" style="text-align: center;">
										<input type="button" id="loginbtn_${v.count }" class="flatbtn-blu hidemodal" value="确定" tabindex="3" onclick="submitTaskCheck('${task.id}','${v.count }')">
									</div>
								</div>
							</c:forEach>
						</ul>
					</div>
				</c:if>
			</div>
		</div>
	</div>

	<script src="${path}/module/weChat/jump/js/jquery-2.1.3.min.js"></script>
	<script src="${path}/module/weChat/jump/js/zepto.min.js"></script>
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
	  <c:forEach items="${taskList }" var="task" varStatus="i">
	  $('#modaltrigger_${i.count}').leanModal({ top: 110, overlay: 0.45, closeButton: ".hidemodal" });
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
						$("#close").click(function() {
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
		function submitTaskCheck(taskIdVal,count) {
			var checkVal = $('input:radio[name="check_' + count + '"]:checked').val();
	        if(checkVal == null) {
	            A.alert("提示","请选择谈单结果！");
	            return false;
	        } 
			//显示加载中...
			 A.showMask(function(){
		    	//A.showToast('您已关闭请求');
		    });
			//$("#refuseTaskButton").attr("disabled","disabled");
			 $.ajax({
				url : "${path }/weChat/commitVerify?taskId=" + taskIdVal
						+ "&employeeId=${employeeId}&isSuccess=" + checkVal,//拒绝任务的地址
				data : null,
				type : "post",
				async : false,
				success : function(data) {
					A.alert(data);
					//关闭加载中...
					A.hideMask();
					if(data=="success"){
						A.alert('提示', '已成功提交审核！');
						//禁止再次点击
						$("#btn_submit").attr("disabled","disabled");
						setTimeout(function(){window.location.reload();}, 500);
					}else{
						A.alert('提示', '提交审核失败！');
					}
					
				}
			}); 
		}
	</script>
</body>
</html>