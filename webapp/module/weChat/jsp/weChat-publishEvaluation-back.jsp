<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<!DOCTYPE html>
<html>
  <head>
    <meta name="generator"
    content="HTML Tidy for HTML5 (experimental) for Windows https://github.com/w3c/tidy-html5/tree/c63cc39" />
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=320,user-scalable=no,target-densitydpi=medium-dpi" />
    <meta name="viewport" content="width=device-width,user-scalable=no,target-densitydpi=medium-dpi" />
	<link href="${path }/platform/common/css/tastnote.css" rel="stylesheet" type="text/css"/>
	<link href="${path }/platform/theme/default/component/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="${path }/platform/theme/default/component/bootstrap/css/star-rating.css" media="all" rel="stylesheet" type="text/css"/>
	<script src="${path }/platform/common/js/jquery.min.js"></script>
	<script src="${path }/platform/theme/default/component/bootstrap/js/star-rating.js" type="text/javascript"></script>
	
	<script type="text/javascript" src="${path }/platform/common/js/jquery-1.11.1.js"></script>
	<script type="text/javascript">
		var intervalLine = setInterval(function() {
			$("#text1").hide();
		}, 3000);
		function formSubmitTest() {
			$.ajax({
				url : "${path }/weChat/dopublishEvaluation",//提交任务评价的地址
				data : $("#form").serialize(),
				type : "post",
				async : false,
				success : function(data) {
					$("#text1").show();
					intervalLinel;
				}
			});
		}
	</script>
    <title>任务评价</title>
  </head>
  <body bgcolor="#f2f2f2">
    <!--已完成*******评价-->
    <form action="" id="form">
	    <div class="tastnote_container">
	    	<div style="width: 100%">
	    		<span style="font-weight:bold;font-size:20px;width: 19%;text-align: right; float: left; height: 25px;">内容&nbsp;</span>
	        	<textarea name="evaluation.content" rows="7" cols="41" style="width: 80%; float: left;"></textarea>
	        </div>
			<div id="tastnote_note">
				<span style="font-weight:bold;font-size:20px;float: left;">综合评价&nbsp;&nbsp;</span>
				<span style="float: left;">
					<input name="evaluation.level" id="input-21e" type="number" class="rating" min=0 max=5 step=1 data-size="xs">
				</span>
			</div>
			<div style="width: 100%;">
		        <p class="tastnote_button" style="float: right;">
		            <a href="javaScript:void(0);" onclick="formSubmitTest();">发表</a>&nbsp;&nbsp;
		            <a href="javascript:history.go(-1);">取消</a>&nbsp;&nbsp;
			    </p>
			</div>
		</div>
		<input type="hidden" name="taskId" value="${taskId }"/> 
		<input type="hidden" name="publisherId" value="${publisherId }"/> 
		<input type="hidden" name="receiverId" value="${receiverId }"/> 
	</form>
	<div id="text1" style="font-size: 20px; background-color: #000; opacity: 0.8; width: 170px; text-align: center; border: 1px solid #fff; border-radius: 4px; line-height: 36px; position: fixed; top: 50%; left: 25%; color: #FF2FBD; display: none;">评价成功！</div>
  </body>
</html>
