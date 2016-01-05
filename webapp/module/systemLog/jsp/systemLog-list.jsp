<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
	<head>  
		<script type="text/javascript">
			Namespace.register("com.yunt.systemLog.list");
			com.yunt.systemLog.list = {
			}
		</script>
	</head>
	<body>
		<form id="${pageId}theForm" action="${path}/systemLog/list">
			<table class="table" cellspacing="0" cellpadding="0">
				 <thead>
			        <tr>
			            <th width="5%">序号</th>
			            <th width="20%">操作人</th>
			            <th width="20%">操作人类别</th>
			            <th width="20%">操作动作</th>
			            <th width="20%">操作时间</th>
			            <th>操作</th>
			        </tr>
			    </thead>
				<tbody>
					<c:forEach items="${pageObj.results}" var="systemLog" varStatus="i">
						<c:if test="${i.index%2 eq 0}">
							<tr class="a1">
								<td>${(i.index+1)+((pageObj.pageNo-1) * pageObj.pageSize)}</td>
								<td>${systemLog.operatorName}</td>
								<td>${systemLog.isAdminValue}</td>
								<td>${systemLog.operateValue}</td>
								<td><fmt:formatDate value="${systemLog.createTime}" type="date" pattern="yyyy-MM-dd hh:mm:ss"/></td>
								<td>
									<input type="button" value="查看" class="btn" onclick="com.yunt.systemLog.index.view('${path}/systemLog/view?id=${systemLog.id}', '查看');" />
								</td>
							</tr>
						</c:if>
						<c:if test="${i.index%2 ne 0}">
							<tr class="a2">
								<td>${(i.index+1)+((pageObj.pageNo-1) * pageObj.pageSize)}</td>
								<td>${systemLog.operatorName}</td>
								<td>${systemLog.isAdminValue}</td>
								<td>${systemLog.operateValue}</td>
								<td><fmt:formatDate value="${systemLog.createTime}" type="date" pattern="yyyy-MM-dd hh:mm:ss"/></td>
								<td>
									<input type="button" value="查看" class="btn" onclick="com.yunt.systemLog.index.view('${path}/systemLog/view?id=${systemLog.id}', '查看');" />
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
