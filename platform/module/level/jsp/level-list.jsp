<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
	<head>  
		<script type="text/javascript">
			Namespace.register("com.yunt.level.list");
			com.yunt.level.list = {
				doDelete : function(id) {
					confirm("确定要删除吗？",function(){
						$.ajax({
						 	type: "POST",
						 	url: "${path}/level/doDelete?id="+id,
						  	processData:true,
						  	success: function(data){
						  		com.yunt.level.index.refresh();
						  	}
						 });
					});
				}
			}
		</script>
	</head>
	<body>
		<form id="${pageId}theForm" action="${path}/level/list">
			<table class="table" cellspacing="0" cellpadding="0">
				 <thead>
			        <tr>
			            <th width="5%">序号</th>
			            <th width="20%">名称</th>
			            <th width="20%">类型</th>
			            <th width="20%">层级</th>
			            <th width="10%">比例</th>
			            <th>操作</th>
			        </tr>
			    </thead>
				<tbody>
					<c:forEach items="${pageObj.results}" var="level" varStatus="i">
						<c:if test="${i.index%2 eq 0}">
							<tr class="a1">
								<td>${(i.index+1)+((pageObj.pageNo-1) * pageObj.pageSize)}</td>
								<td>${level.name}</td>
								<td>${level.kindValue}</td>
								<td>${level.level}</td>
								<td>${level.proportion}</td>
								<td>
									<input type="button" value="修改" class="btn" onclick="com.yunt.level.index.input('${path}/level/input?id=${level.id}&inputKind=update', '修改');" />
								</td>
							</tr>
						</c:if>
						<c:if test="${i.index%2 ne 0}">
							<tr class="a2">
								<td>${(i.index+1)+((pageObj.pageNo-1) * pageObj.pageSize)}</td>
								<td>${level.name}</td>
								<td>${level.kindValue}</td>
								<td>${level.level}</td>
								<td>${level.proportion}</td>
								<td>
									<input type="button" value="修改" class="btn" onclick="com.yunt.level.index.input('${path}/level/input?id=${level.id}&inputKind=update', '修改');" />
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
