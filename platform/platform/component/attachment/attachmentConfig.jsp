<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
	<head>
    <title>平台</title>
    <%@ include file="/platform/common/jsp/baseJs.jsp"%>
	</head>
	<script type="text/javascript">
		Namespace.register('com.ue.platform.module.attachment.attachmentConfig.list');
		com.ue.platform.module.attachment.attachmentConfig.list = {
			toEdit:function(id){
				$.dialog.load("${path}/sys/attachmentConfig/input.do?id=" + id,{
					title : '修改配置',
					width : 800,
					height : 400,
					id : "${pageId}Dia",
					ok : function(){
						com.ue.platform.module.attachment.attachmentConfig.input.doAdd("${pageId}Dia");
						return false;
				    }
				});
			},
			toAdd:function(){
				$.dialog.load("${path}/sys/attachmentConfig/input.do",{
					title : '添加配置',
					width : 800,
					height : 400,
					id : "${pageId}Dia",
					ok : function(){
						com.ue.platform.module.attachment.attachmentConfig.input.doAdd("${pageId}Dia");
						return false;
				    }
				});
			},
			toDele:function(id){
				confirm("是否确定删除？",function(){
					$.ajax({
					 	type: "POST",
					  	url: '${path}/sys/attachmentConfig/ajaxDele.do?id='+id,
					  	success: function(data){
					  		$.successTips();
					  	}
					 });
				});
			},
			toSetting:function(){
				$.dialog.load("${path}/sys/attachmentSetting/input.do",{
					title : '添加配置',
					width : 800,
					height : 400
				});
			}
		}
	</script>
	<body>
		<input type="button" value="添加" onclick="com.ue.platform.module.attachment.attachmentConfig.list.toAdd()">
		<input type="button" value="全局配置" onclick="com.ue.platform.module.attachment.attachmentConfig.list.toSetting()">
		<ul>
			<c:forEach items="${list }" var="config">
			<li>
				${config.code }
				<input type="button" value="修改" onclick="com.ue.platform.module.attachment.attachmentConfig.list.toEdit('${config.id}')">
				<input type="button" value="删除" onclick="com.ue.platform.module.attachment.attachmentConfig.list.toDele('${config.id}')">
			</li>
			</c:forEach>
		</ul>
	</body>
</html>
