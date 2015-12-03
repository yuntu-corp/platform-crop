<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
	<head>  
		<script type="text/javascript">
			Namespace.register("com.yunt.evaluation.list");
			com.yunt.evaluation.list = {
				doDelete : function(id) {
					confirm("确定要删除吗？",function(){
						$.ajax({
						 	type: "POST",
						 	url: "${path}/evaluation/doDelete?id="+id,
						  	processData:true,
						  	success: function(data){
						  		com.yunt.evaluation.index.refresh();
						  	}
						 });
					});
				}
			}
		</script>
	</head>
	<body>
		<form id="${pageId}theForm" action="${path}/evaluation/list">
			<table class="table" cellspacing="0" cellpadding="0">
				 <thead>
			        <tr>
			            <th width="5%">序号</th>
			            <th width="20%">任务名称</th>
			            <th width="20%">评价人</th>
			            <th width="20%">评价星级</th>
			            <th>操作</th>
			        </tr>
			    </thead>
				<tbody>
					<c:forEach items="${pageObj.results}" var="evaluation" varStatus="i">
						<c:if test="${i.index%2 eq 0}">
							<tr class="a1">
								<td>${(i.index+1)+((pageObj.pageNo-1) * pageObj.pageSize)}</td>
								<td>${evaluation.task.title}</td>
								<td>${evaluation.publisher.name}</td>
								<td>${evaluation.level}</td>
								<td>
									<input type="button" value="查看" class="btn" onclick="com.yunt.evaluation.index.view('${path}/evaluation/view?id=${evaluation.id}', '查看');" />
								</td>
							</tr>
						</c:if>
						<c:if test="${i.index%2 ne 0}">
							<tr class="a2">
								<td>${(i.index+1)+((pageObj.pageNo-1) * pageObj.pageSize)}</td>
								<td>${evaluation.task.title}</td>
								<td>${evaluation.publisher.name}</td>
								<td>${evaluation.level}</td>
								<td>
									<input type="button" value="查看" class="btn" onclick="com.yunt.evaluation.index.view('${path}/evaluation/view?id=${evaluation.id}', '查看');" />
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
