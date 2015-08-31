<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp"%>
<html>
<head>
   <%@ include file="/platform/common/jsp/common.jsp"%>
   <script type="text/javascript">
		Namespace.register('com.ue.courseplatform.module.basicData.index');
		com.ue.courseplatform.module.basicData.index = {
			toLoadSchoolContent : function(obj){
				$("body").mask($.i18n.prop('message_loading'));
				$("#${pageId}dataContainer").load("${path}/bd/school/input?id=${user.school.id}&flag=1",function(){
					$("body").unmask();
				});
				$(".co_menu").find("a").removeClass("co_on");
				$(obj).addClass("co_on");
			},
			toLoadContent : function(obj){
				$("body").mask($.i18n.prop('message_loading'));
				$("#${pageId}dataContainer").load("${path}/bd/schoolTerm/list",function(){
					$("body").unmask();
				});
				$(".co_menu").find("a").removeClass("co_on");
				$(obj).addClass("co_on");
			},
			toLoadCourseContent : function(obj){
				$("body").mask($.i18n.prop('message_loading'));
				$("#${pageId}dataContainer").load("${path}/bd/course/list",function(){
					$("body").unmask();
				});
				$(".co_menu").find("a").removeClass("co_on");
				$(obj).addClass("co_on");
			},
			toLoadClassroomContent : function(obj){
				$("body").mask($.i18n.prop('message_loading'));
				$("#${pageId}dataContainer").load("${path}/bd/classroom/list",function(){
					$("body").unmask();
				});
				$(".co_menu").find("a").removeClass("co_on");
				$(obj).addClass("co_on");
			},
			toLoadEclassContent : function(obj,type){
				$("body").mask($.i18n.prop('message_loading'));
				$("#${pageId}dataContainer").load("${path}/bd/eclass/list?type="+type,function(){
					$("body").unmask();
				});
				$(".co_menu").find("a").removeClass("co_on");
				$(obj).addClass("co_on");
			},
			toLoadTeacher : function(obj){
				$("body").mask($.i18n.prop('message_loading'));
				$("#${pageId}dataContainer").load("${path}/bd/user/list?type=2",function(){
					$("body").unmask();
				});
				$(".co_menu").find("a").removeClass("co_on");
				$(obj).addClass("co_on");
			},
			toLoadStudent : function(obj){
				$("body").mask($.i18n.prop('message_loading'));
				$("#${pageId}dataContainer").load("${path}/bd/user/list?type=0",function(){
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
				<a href="javascript:void(0)" onclick="com.ue.courseplatform.module.basicData.index.toLoadSchoolContent(this);" class="co_on"><em></em>学校信息</a>
				<a href="javascript:void(0)" onclick="com.ue.courseplatform.module.basicData.index.toLoadEclassContent(this,0);"><em></em>行政班信息</a>
				<a href="javascript:void(0)" onclick="com.ue.courseplatform.module.basicData.index.toLoadEclassContent(this,1);"><em></em>教学班信息</a>
				<a href="javascript:void(0)" onclick="com.ue.courseplatform.module.basicData.index.toLoadTeacher(this);"><em></em>教师信息</a>
				<a href="javascript:void(0)" onclick="com.ue.courseplatform.module.basicData.index.toLoadStudent(this);"><em></em>学生信息</a>
				<a href="javascript:void(0)" onclick="com.ue.courseplatform.module.basicData.index.toLoadCourseContent(this);"><em></em>课程信息</a>
				<a href="javascript:void(0)" onclick="com.ue.courseplatform.module.basicData.index.toLoadContent(this);"><em></em>学期信息</a>
				<a href="javascript:void(0)" onclick="com.ue.courseplatform.module.basicData.index.toLoadClassroomContent(this);"><em></em>教室信息</a>
		    </div>
		    <div id="${pageId}dataContainer" class="co_main"></div>
		</div>
		<%@ include file="/platform/common/jsp/footer.jsp"%>
	</div>
</body>
</html>