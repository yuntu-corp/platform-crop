<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp"%>
<html>
<head>
	<meta charset="utf-8"/>
	<title>评价</title>
	<meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<link href="${path }/module/weChat/jump/css/jump-demo.css" rel="stylesheet" type="text/css">
    <link href="${path }/module/weChat/jump/css/flat.color.css" rel="stylesheet" type="text/css">
    <link href="${path }/module/weChat/jump/css/flat.component.css" rel="stylesheet" type="text/css">
    <link href="${path }/module/weChat/jump/css/iconline.css" rel="stylesheet" type="text/css">
	<link href="${path }/module/weChat/jump/css/modify.css" rel="stylesheet" type="text/css">
	<style>
		/* starbox */
		#star{ background-color:#fff; border-bottom:1px solid #ddd; border-top:1px solid #ddd; padding:12px 10px 8px; margin-bottom:10px;}
		.clearfix{display:inline-table}
		
		.jump-content ul{ margin:0;}	
		.rev_pro li .revtit{text-align:right;display:block;float:left;margin-right:10px;}
		.revinp{float:left;display:inline;}
		.level .level_solid,.level .level_hollow{float:left;background-image:url("${path}/module/weChat/others/img/icon2.png");background-repeat:no-repeat;display:inline-block;width:15px;height:15px;}
		.level .level_solid{background-position:0px 0px;}
		.level .level_hollow{background-position:-21px 0px;}
		.revgrade{margin-left:20px;}
		/* starbox */
	</style>
</head>

<body>
	<div class="jump-container">
    	<div class="jump-content">
        	<div id="star">
            	<ul class="rev_pro clearfix">
                	<li>
                    	<span class="revtit">综合评价：</span>
                      	<div class="revinp">
                         	<span id="test" class="level">
                            	<i class="level_solid" cjmark=""></i>
                               	<i class="level_solid" cjmark=""></i>
                               	<i class="level_solid" cjmark=""></i>
                              	<i class="level_solid" cjmark=""></i>
                               	<i class="level_hollow" cjmark=""></i>
                          	</span>
                          	<span class="revgrade"></span>
                       	</div>
                  	</li>
           		</ul>
          	</div>

            <div style="padding:12px 10px; background-color:#fff;">
				<textarea id="comment" placeholder="写点评价吧，对其他小伙伴帮助很大哦" rows="5" style="width:100%; border:none;" ></textarea>
            </div>
            
            <div style="margin-top:80px; padding:0 10px;"><button class="block" onclick="publishEvaluation()">提交</button></div>

        </div>
    </div>
    <script src="${path }/module/weChat/jump/js/jquery-2.1.3.min.js"></script>
    <script src="${path }/module/weChat/jump/js/zepto.min.js"></script>
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
				alert("请输入评价");
			} else if ($("#comment").val().length > 100) {
				alert("请输100字以内的评价！");
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