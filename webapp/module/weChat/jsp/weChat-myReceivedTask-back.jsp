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
	<link rel="stylesheet" type="text/css" href="${path }/platform/theme/default/component/bootstrap/css/bootstrap.css" />
	<link rel="stylesheet" type="text/css" href="${path }/platform/theme/default/component/bootstrap/css/bootstrap-theme.css" />
	<link rel="stylesheet" type="text/css" href="${path }/platform/theme/default/component/bootstrap/css/animate.css"" />
	<script src="${path }/platform/common/js/jquery-1.7.1.min.js" type="text/javascript" ></script>
	<script src="${path }/platform/common/js/jquery.countdown.js"></script>
	<script type="text/javascript">
		function timeTest(index) {
			if(" 00 天 00 时 00 分 00 秒".trim() == $("#clock_" + index).text().trim()){
				return true;
			}
			return false;
		}
	</script>
	

    <title>已接受</title>
  </head>
  <body bgcolor="#f2f2f2">
    <!--个人中心*****已接受-->
    <c:forEach items="${taskList }" var="task" varStatus="v">
		<div style="width:98%; height:170px; margin:5px auto; margin-bottom:10px; background:#ddd;">
		    <p class="finish_status">${task.statusVal}</p>
		    <div class="finish_title">
	        <!-- <img src="images\women.png" width="30px" height="30px"/>&nbsp;&nbsp; -->
			任务标题：${task.title}
	        <!-- <input type="text" value="" style="width:260px; height:28px;" disabled="disabled"/>-->
	        </div>
			<p>
			   <div id="finish_clock1">
		           <!-- <span class="finish_clock">00</span>时<span class="finish_clock">00</span>分<span class="finish_clock">00</span>秒  -->
		           <span class="finish_clock"><span id="clock_${v.count }"></span></span>
	           </div>
	           
	           <div class="finish_public">发布人:${task.publisher.name } &nbsp;虚拟币:${task.finalBitcoin }个</div>
			</p>
	        <div id="comment_icon" >
	            <a id="a_${v.count }" href="${path }/weChat/publishEvaluation?publisherId=${employeeId}&receiverId=${task.publisher.id}&taskId=${task.id}" onclick="return timeTest(${v.count });" >
	            	<img src="${path }/platform/common/images/icon.png" width="23px" height="23px"/>
	            </a>
	        </div>
		</div>
		<script type="text/javascript">
			var second_${v.count } = new Date();
			second_${v.count }.setSeconds(${task.timeVal});
			$("#clock_" + ${v.count }).countdown(second_${v.count }, function(event) {
				$(this).html(event.strftime(" %D 天 %H 时 %M 分 %S 秒"));
			});
		</script>
    </c:forEach>

  </body>
</html>
