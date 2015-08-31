<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>用户列表显示</title>
		<#include "/demo/common/meta.html">
		<#assign user = "Joe Hider">
	</head>
	<body>
	     <strong>查询：</strong> 
	     <@uc.textfield name="model.name" maxlength="50" cssClass="inputStyle w200 required"/>
		<#if users?size != 0> 
		<div class="userList"> 
		${path}-${user}-${RequestParameters['id']!}
			<table border="0" cellpadding="3" cellspacing="1">
				<tr align="center">
					<th width="300">ID</th>
					<th width="300">姓名</th>
					<th width="300">年龄</th>
					<th width="300">邮箱</th>
					<th width="300">部门</th>
				</tr>
				<#if users??>
		    		<#list users as entity>
					<tr align="center">
						<td>${entity.id}</td>
						<td>${entity.name}</td>
						<td>${entity.getAgeView(entity.age!0)!}</td>
						<td>${entity.email!}</td>
						<td>
							<#if entity.dept??>  
								${entity.dept.name!}
							</#if>  
						</td>
					</tr>
					</#list>
		        </#if>
			</table>
		</div> 
		<#else> 
		<div class="userList"></div> 
		</#if> 
	</body>
</html>