<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
	<head>  
		<script type="text/javascript">
			Namespace.register("com.yunt.employee.updateEmployee");
			com.yunt.employee.updateEmployee = {
				doUpdate : function(dialogId) {
					if(!$("#${pageId}theForm").valid()){
						return false;
					}
					var formData = $("#${pageId}theForm").serialize();
					$.ajax({
					 	type: "POST",
					  	url: "${path}/employee/updateEmployee",
					  	data: formData,
					  	success: function(data){
					  		if("success"==data){
					  			$.successTips();
						   		art.dialog.list[dialogId].close();
						   		com.yunt.employee.index.refresh();
					  		} else {
					  			alert(data);
					  		}
					  	}
					});
				}
			};
			$(document).ready(function() {
				$.hz.validate.init("${pageId}theForm");
			});
		</script>
	</head>
	<body>
		<form id="${pageId}theForm" method="post" class="BB">
			<input type="hidden" name="model.id" value="${model.id}" />
			<input type="hidden" name="model.userId" value="${model.userId}" />
			<input type="hidden" name="model.user.id" value="${model.user.id}" />
			<div style="padding: 20px;">
				<div class="pl100 lh50">
			     	<label class="labelTitle"><em style="color: red;">*</em>姓名：</label>
			     	<input id="${pageId}name" name="model.name" type='text' maxlength="64" class="required" value="${model.name }"/>
				</div>
				<div class="pl100 lh50">
			     	<label class="labelTitle"><em style="color: red;">*</em>性别：</label>
			     	<uc:select id="${pageId}level" list="{1:'男',2:'女'}" name="model.wxCpUser.gender" cssClass="search" value="${model.wxCpUser.gender }" />
				</div>
				<div class="pl100 lh50">
			     	<label class="labelTitle"><em style="color: red;">*</em>部门：</label>
			     	<div class="view_detail2" style="width: 315px;">
				     	<c:forEach items="${departList }" var="depart" varStatus="i">
				     	
				     		<c:if test="${fn:containsIgnoreCase(departvalue,depart.id)}">
								<span >
									<input type="checkbox" class="required" style="width: auto;height: auto;" value="${depart.id  }" name="departStr" checked="checked" />
									<label class="black">${depart.name}</label>
								</span>
				     		</c:if>
				     		<c:if test="${!fn:containsIgnoreCase(departvalue,depart.id)}">
								<span >
									<input type="checkbox" class="required" style="width: auto;height: auto;" value="${depart.id  }" name="departStr" />
									<label class="black">${depart.name}</label>
								</span>
				     		</c:if>
							<c:if test="${i.count%4==0 }"><br/></c:if>
		    			</c:forEach>
			     	</div>
				</div>
				<div class="pl100 lh50">
			     	<label class="labelTitle">员工ID：</label>
			     	<div class="view_detail2">${model.userId}</div>
				</div>
				<div class="pl100 lh50">
			     	<label class="labelTitle"><em style="color: red;">*</em>职位：</label>
			     	<input id="${pageId}position" name="model.wxCpUser.position" type='text' maxlength="64" class="required" value="${model.wxCpUser.position }" />
				</div>
				<div class="pl100 lh50">
			     	<label class="labelTitle"><em style="color: red;">*</em>微信账号：</label>
			     	<input id="${pageId}name" name="model.wxCpUser.weiXinId" type='text' class="required" value="${model.wxCpUser.weiXinId }"/>
				</div>
				<div class="pl100 lh50">
			     	<label class="labelTitle"><em style="color: red;">*</em>手机号：</label>
			     	<input id="${pageId}name" name="model.wxCpUser.mobile" type='text' class="required" value="${model.wxCpUser.mobile }"/>
				</div>
				<div class="pl100 lh50">
			     	<label class="labelTitle"><em style="color: red;">*</em>邮箱：</label>
			     	<input id="${pageId}name" name="model.wxCpUser.email" type='text' maxlength="64" class="required" value="${model.wxCpUser.email }"/>
				</div>
			</div>
		</form>
	</body>
</html>