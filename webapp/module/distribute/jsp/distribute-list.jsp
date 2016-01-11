<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
	<head>  
		<script type="text/javascript">
			Namespace.register("com.yunt.distribute.list");
			com.yunt.distribute.list = {
				
			}
		</script>
	</head>
	<body>
		<form id="${pageId}theForm" action="${path}/distribute/list">
			<table class="table" cellspacing="0" cellpadding="0">
				 <thead>
			        <tr>
			            <th width="5%">序号</th>
			            <th width="5%">分发虚拟币数量</th>
			            <th width="20%">接收人</th>
			            <th width="20%">日期</th>
			            <th width="30%">原因</th>
			            <th>操作</th>
			        </tr>
			    </thead>
				<tbody>
					<c:forEach items="${pageObj.results}" var="distribute" varStatus="i">
						<c:if test="${i.index%2 eq 0}">
							<tr class="a1">
								<td>${(i.index+1)+((pageObj.pageNo-1) * pageObj.pageSize)}</td>
								<td>${distribute.bitcoin}</td>
								<td>${distribute.employee.name}</td>
								<td><fmt:formatDate value="${distribute.date}" pattern="yyyy-MM-dd HH:mm"/> </td>
								<td>${distribute.reason}</td>
								<td>
									<input type="button" value="查看" class="btn" onclick="com.yunt.distribute.index.view('${path}/distribute/view?id=${distribute.id}', '查看');" />
								</td>
							</tr>
						</c:if>
						<c:if test="${i.index%2 ne 0}">
							<tr class="a2">
								<td>${(i.index+1)+((pageObj.pageNo-1) * pageObj.pageSize)}</td>
								<td>${distribute.bitcoin}</td>
								<td>${distribute.employee.name}</td>
								<td><fmt:formatDate value="${distribute.date}" pattern="yyyy-MM-dd HH:mm"/> </td>
								<td>${distribute.reason}</td>
								<td>
									<input type="button" value="查看" class="btn" onclick="com.yunt.distribute.index.view('${path}/distribute/view?id=${distribute.id}', '查看');" />
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
