<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
	<head>  
		<script type="text/javascript">
			Namespace.register("com.yunt.employee.list");
			com.yunt.employee.list = {
				doDelete : function(id) {
					confirm("确定要删除吗？",function(){
						$.ajax({
						 	type: "POST",
						 	url: "${path}/employee/doDelete?id="+id,
						  	processData:true,
						  	success: function(data){
						  		com.yunt.employee.index.refresh();
						  	}
						 });
					});
				}
			}
		</script>
	</head>
	<body>
		<form id="${pageId}theForm" action="${path}/employee/list">
			<table class="table" cellspacing="0" cellpadding="0">
				 <thead>
			        <tr>
			            <th width="5%">序号</th>
			            <th width="20%">姓名</th>
			            <th width="20%">员工编号</th>
			            <th width="20%">每月基础币</th>
			            <th>操作</th>
			        </tr>
			    </thead>
				<tbody>
					<c:forEach items="${pageObj.results}" var="employee" varStatus="i">
						<c:if test="${i.index%2 eq 0}">
							<tr class="a1">
								<td>${(i.index+1)+((pageObj.pageNo-1) * pageObj.pageSize)}</td>
								<td>${employee.name}</td>
								<td>${employee.userId}</td>
								<td>${employee.baseBitcoin}</td>
								<td>
									<input type="button" value="修改" class="btn" onclick="com.yunt.employee.index.update('${path}/employee/doUpdate?id=${employee.id}', '修改');" />
									<input type="button" value="查看" class="btn" onclick="com.yunt.employee.index.view('${path}/employee/view?id=${employee.id}', '查看');" />
								</td>
							</tr>
						</c:if>
						<c:if test="${i.index%2 ne 0}">
							<tr class="a2">
								<td>${(i.index+1)+((pageObj.pageNo-1) * pageObj.pageSize)}</td>
								<td>${employee.name}</td>
								<td>${employee.userId}</td>
								<td>${employee.baseBitcoin}</td>
								<td>
									<input type="button" value="修改" class="btn" onclick="com.yunt.employee.index.update('${path}/employee/doUpdate?id=${employee.id}', '修改');" />
									<input type="button" value="查看" class="btn" onclick="com.yunt.employee.index.view('${path}/employee/view?id=${employee.id}', '查看');" />
								</td>
							</tr>
						</c:if>
					</c:forEach>
		    	</tbody>
		    </table>
		    <uc:pageBar pageInfo="${pageObj.info}" containerId="content" formId="${pageId}theForm" changePageSize="true" changePageSizeNumber="10,20,30" />
	    </form>
	</body>
</html>
