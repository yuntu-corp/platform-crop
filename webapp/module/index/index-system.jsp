<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp"%>
<html>
<head>
   <%@ include file="/platform/common/jsp/common.jsp"%>
   <script type="text/javascript">
		Namespace.register('com.ue.courseplatform.module.system.index');
		com.ue.courseplatform.module.system.index = {
			toLoadCourseDirectionContent : function(obj){
				$("body").mask($.i18n.prop('message_loading'));
				$("#${pageId}dataContainer").load("${path}/bd/courseDirection/list",function(){
					$("body").unmask();
				});
				$(".co_menu").find("a").removeClass("co_on");
				$(obj).addClass("co_on");
			},
			toLoadCourseContent : function(obj){
				$("body").mask($.i18n.prop('message_loading'));
				$("#${pageId}dataContainer").load("${path}/bd/course/list?flag=1",function(){
					$("body").unmask();
				});
				$(".co_menu").find("a").removeClass("co_on");
				$(obj).addClass("co_on");
			}
		}
		$(function(){
			$("#${pageId}menu a:eq(0)").click();
		});
	</script>
</head>
<body>
	<div class="co_wrap">
		<%@ include file="/platform/common/jsp/header.jsp"%>
		<div class="co_maincontent">
			<div class="co_menu" id="${pageId}menu">
				<a href="javascript:void(0)" class="co_on" onclick="com.ue.courseplatform.module.system.index.toLoadCourseContent(this)"><em></em>课程</a>
				<a href="javascript:void(0)" name1='courseDirection' onclick="com.ue.courseplatform.module.system.index.toLoadCourseDirectionContent(this)"><em></em>课程方向</a>
<!-- 				<a href="javascript:void(0)"><em></em>所属区域</a> -->
<!-- 				<a href="javascript:void(0)"><em></em>配置</a> -->
		    </div>
		    <div id="${pageId}dataContainer" class="co_main"></div>
		</div>
		<%@ include file="/platform/common/jsp/footer.jsp"%>
	</div>
</body>
</html>