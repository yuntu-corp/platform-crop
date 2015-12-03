<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp"%>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<title>评价</title>
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
article {
	background-color: #f2f2f2;
}

p {
	margin: 0;
}

textarea {
	margin: 0;
}

textarea {
	border: none;
	font-size: 12px;
	height: 80px;
	color: #9c9c9c;
}

button.submit,input[type="submit"],a.button.submit {
	border-radius: none;
	background-color: #00afff;
	color: #fff;
}

.card {
	background-color: #f2f2f2;
	margin-top: 64px;
}

button.submit:active,input[type="submit"]:active,a.button.submit:active
	{
	color: #fff;
	border-color: #029ee6;
	background-color: #029ee6;
}

* {
	margin: 0;
	padding: 0;
	list-style-type: none;
}

body {
	color: #666;
	font: 12px/1.5 Arial;
}
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
	margin-right: 10px;
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
		<section id="refresh_section" data-role="section" class="active">
			<article data-role="article" id="main_article" data-scroll="pull"
				class="active" style="bottom: 0px;">
				<div class="scroller">
					<div
						style="background-color: #fff; border: 1px solid #e9e9e9; padding: 10px 6px;">
						<div id="star">
							<ul class="rev_pro clearfix">
								<li
									style="border-bottom: none; padding: 0; font-size: 12px; color: #aaa;">
									<span class="revtit" style="margin-left: 1px;">综合评级：</span>
									<div class="revinp">
										<span id="test" class="level"> <i class="level_solid"
											cjmark=""></i> <i class="level_solid" cjmark=""></i> <i
											class="level_solid" cjmark=""></i> <i class="level_solid"
											cjmark=""></i> <i class="level_hollow" cjmark=""></i>
										</span> <span class="revgrade"></span>
									</div>
								</li>
							</ul>
						</div>
						<!--star end-->

					</div>

					<!--输入文本-->
					<div class=""
						style="background-color: #fff; border-bottom: 1px solid #e9e9e9; border-top: 1px solid #e9e9e9; padding: 10px; margin: 6px 0;">
						<textarea id="comment">写点评价吧，对其他小伙伴帮助很大哦</textarea>
					</div>

					<!--提交按钮-->
					<div class="card noborder">
						<button id="btn_submit" class="block submit"
							onclick="publishEvaluation()">
							提交
							<tton>
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
	<!--星星-->
	<script type="text/javascript">
		var degree = [ '', '很差', '差', '中', '良', '优', '未评分' ];
		var level = "0";
		$(function() {
			var num = -1;
			var list = $("#test").find('i');
			for ( var i = 0; i <= num; i++) {
				list.eq(i).attr('class', 'level_solid');
			}
			for ( var i = num + 1, len = list.length - 1; i <= len; i++) {
				list.eq(i).attr('class', 'level_hollow');
			}

			//点星星
			$(document).on('tap', 'i[cjmark]', function() {
				var num = $(this).index();
				level = Number(num) + 1;
				var pmark = $(this).parents('.revinp');
				var mark = pmark.prevAll('input');

				if (mark.prop('checked'))
					return false;

				var list = $(this).parent().find('i');
				for ( var i = 0; i <= num; i++) {
					list.eq(i).attr('class', 'level_solid');
				}
				for ( var i = num + 1, len = list.length - 1; i <= len; i++) {
					list.eq(i).attr('class', 'level_hollow');
				}
				$(this).parent().next().html(degree[num + 1]);

			})
			//点击星星
			$(document).on('click', 'i[cjmark]', function() {
				var num = $(this).index();
				var pmark = $(this).parents('.revinp');
				var mark = pmark.prevAll('input');

				if (mark.prop('checked')) {
					mark.val('');
					mark.prop('checked', false);
					mark.prop('disabled', true);
				} else {
					mark.val(num);
					mark.prop('checked', true);
					mark.prop('disabled', false);
				}
			})
			//选框
			$('#Addnewskill_119 input[type="checkbox"]').change(function() {
				if ($(this).not(':checked')) {//!($(this).prop('checked'))
					$(this).prop('checked', false);
					$(this).prop('disabled', true)
					var smark = $(this).nextAll('.revinp');
					smark.find('span.revgrade').html('未评分');
					smark.find('i').attr('class', 'level_hollow');
					smark.val(6);
				}
			})
		})

		function publishEvaluation() {
			if ($("#comment").val() == "") {
				A.alert("请输入评价");
			} else if ($("#comment").val().length > 100) {
				A.alert("请输100子以内的评价！");
			} else {
				$.ajax({
					type : "POST",
					url : "${path }/weChat/dopublishEvaluation",
					data : {
						"level" : level,
						"content" : $("#comment").val(),
						"taskId" : '${taskId}',
						"publisherId" : '${publisherId}',
						"receiverId" : '${receiverId}'
					},
					async : false,
					success : function(data) {
						if(data=="success"){
							A.alert("发布评价成功！");
							setTimeout(function(){history.back(-1);}, 2000);
						}
						
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						A.alert(XMLHttpRequest.status);
						A.alert(XMLHttpRequest.readyState);
						A.alert(textStatus);
					}
				});
			}
		}
	</script>
</body>
</html>