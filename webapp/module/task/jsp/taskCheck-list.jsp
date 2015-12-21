<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
	<head>  
		<script type="text/javascript">
			Namespace.register("com.yunt.taskCheck.list");
			com.yunt.taskCheck.list = {
				goCheck : function(href, title) {
					$.dialog.load(href,{
						id: '${pageId}taskInput',
						title:title,
						width:600,
						height:500,
						ok:function(){
							com.yunt.taskCheck.check .doCheck('${pageId}taskInput');
							return false;
						},
						cancel: function(){
		
						}
					});
				}
			}
		</script>
	</head>
	<body>
		<form id="${pageId}theForm" action="${path}/task/list">
			<table class="table" cellspacing="0" cellpadding="0">
				 <thead>
			        <tr>
			            <th width="5%">序号</th>
			            <th width="15%">任务标题</th>
			            <th width="20%">发布人</th>
			            <th width="10%">任务金币</th>
			            <th width="20%">发布日期</th>
			            <th width="15%">任务类型</th>
			            <th>操作</th>
			        </tr>
			    </thead>
				<tbody>
					<c:forEach items="${pageObj.results}" var="task" varStatus="i">
						<c:if test="${i.index%2 eq 0}">
							<tr class="a1">
								<td>${(i.index+1)+((pageObj.pageNo-1) * pageObj.pageSize)}</td>
								<td>${task.title}</td>
								<td>${task.publisher.name}</td>
								<c:if test="${task.status eq '0' }">
									<td>${task.preBitcoin}</td>
								</c:if>
								<c:if test="${task.status ne '0' }">
									<td>${task.finalBitcoin}</td>
								</c:if>
								<td>
									<fmt:formatDate value="${task.createTime}" type="date" pattern="yyyy-MM-dd"/>
								</td>
								<td>${task.taskType.typeName}</td>
								<td>
									<input type="button" value="查看" class="btn" onclick="com.yunt.taskCheck.index.view('${path}/task/view?id=${task.id}', '查看');" />
									<input type="button" value="审核" class="btn" onclick="com.yunt.taskCheck.list.goCheck('${path}/task/goCheck?id=${task.id}','审核');" />
								</td>
							</tr>
						</c:if>
						<c:if test="${i.index%2 ne 0}">
							<tr class="a2">
								<td>${(i.index+1)+((pageObj.pageNo-1) * pageObj.pageSize)}</td>
								<td>${task.title}</td>
								<td>${task.publisher.name}</td>
								<c:if test="${task.status eq '0' }">
									<td>${task.preBitcoin}</td>
								</c:if>
								<c:if test="${task.status ne '0' }">
									<td>${task.finalBitcoin}</td>
								</c:if>
								<td>
									<fmt:formatDate value="${task.createTime}" type="date" pattern="yyyy-MM-dd"/>
								</td>
								<td>${task.taskType.typeName}</td>
								<td>
									<input type="button" value="查看" class="btn" onclick="com.yunt.taskCheck.index.view('${path}/task/view?id=${task.id}', '查看');" />
									<input type="button" value="审核" class="btn" onclick="com.yunt.taskCheck.list.goCheck('${path}/task/goCheck?id=${task.id}','审核');" />
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
