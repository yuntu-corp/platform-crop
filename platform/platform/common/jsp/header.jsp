<%@ page language="java" pageEncoding="utf-8"%>

<!-- header -->
<div class="co_header">
	<div class="co_corner">
		欢迎您,<a href="javascript:void(0);">${user.name}</a> | <a href="${path}/spring_logout">退出</a> | <a href="javascript:void(0);" onclick="resetPwd('${user.id}')">修改密码</a>
	</div>
	<a href="javascript:void(0);" class="co_logo" title="${productTitle}"></a>
</div>
<!-- header end -->

<!-- menu -->
<div class="co_nav">
	<a href="javascript:void(0);" class="co_help">使用帮助</a>
	<div class="col_menu_item" id="${pageId}modules">
		<!-- 这里判断用户有哪些操作  -->
		<!-- 每个人在href的action里头必须要传一个moduleCode过来，要和id中pageId后的内容一致。用来添加选中样式  -->
		<a id="${pageId}system" href="${path}/bd/courseDirection/toSystemMessageIndex">系统信息</a>
		<a href="${path}/bd/school/index" id="${pageId}school">学校信息</a>
		<a href="${path}/bd/user/index" id="${pageId}user">用户信息</a>
		<a href="${path}/ec/electivePlan/toElectivePlanIndex" id="${pageId}electivePlan">选课计划</a>
		<a href="${path}/ct/coursetable/index" id="${pageId}courseTable">查看课表</a>
<!-- 		<a>排版选课</a> -->
<!-- 		<a>课程统计</a> -->
		<a href="${path}/bd/schoolTerm/toBasicDataIndex" id="${pageId}basicData">基础数据</a>
	</div>
</div>
<!-- menu end -->

<script>
	//这里设置选中  class="co_on"
	$("#${pageId}modules a").removeClass("co_on");
	$("#${pageId}${moduleCode}").addClass("co_on");
	
	function resetPwd(id){
		$.dialog.load("${path}/bd/user/toResetPwd?id="+id, {
			title : "修改密码",
			width: 550,
			height: 330,
			fixed: true,
			id: '${pageId}toResetPwd',
			lock: true,
			ok: function(){
				com.ue.courseplatform.module.user.resetPwd.doResetPwd("${pageId}toResetPwd");
				return false;
		    },
			cancel : function(){
				
			}
		});
	}
</script>