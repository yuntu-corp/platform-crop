<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
	<head>  
		<script type="text/javascript">
			Namespace.register("com.yunt.propaganda.list");
			com.yunt.propaganda.list = {
				doDelete : function(id) {
					confirm("确定要删除吗？",function(){
						$.ajax({
						 	type: "POST",
						 	url: "${path}/propaganda/doDelete?id="+id,
						  	processData:true,
						  	success: function(data){
						  		com.yunt.propaganda.index.refresh();
						  	}
						 });
					});
				}
			}
		</script>
	</head>
	<body>
		<form id="${pageId}theForm" action="${path}/propaganda/list?model.content=${model.content}">
			<table class="table" cellspacing="0" cellpadding="0">
				 <thead>
			        <tr>
			            <th width="5%">序号</th>
			            <th width="60%">内容</th>
			            <th>操作</th>
			        </tr>
			    </thead>
				<tbody>
					<c:forEach items="${pageObj.results}" var="propaganda" varStatus="i">
						<c:if test="${i.index%2 eq 0}">
							<tr class="a1">
								<td>${(i.index+1)+((pageObj.pageNo-1) * pageObj.pageSize)}</td>
								<td>${propaganda.content}</td>
								<td>
									<input type="button" value="修改" class="btn" onclick="com.yunt.propaganda.index.input('${path}/propaganda/input?id=${propaganda.id}&inputKind=update', '修改');" />
									<input type="button" value="删除" class="btn" onclick="com.yunt.propaganda.list.doDelete('${propaganda.id}');" />
								</td>
							</tr>
						</c:if>
						<c:if test="${i.index%2 ne 0}">
							<tr class="a2">
								<td>${(i.index+1)+((pageObj.pageNo-1) * pageObj.pageSize)}</td>
								<td>${propaganda.content}</td>
								<td>
									<input type="button" value="修改" class="btn" onclick="com.yunt.propaganda.index.input('${path}/propaganda/input?id=${propaganda.id}&inputKind=update', '修改');" />
									<input type="button" value="删除" class="btn" onclick="com.yunt.propaganda.list.doDelete('${propaganda.id}');" />
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
