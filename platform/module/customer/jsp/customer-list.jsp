<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
	<head>  
		<script type="text/javascript">
			Namespace.register("com.yunt.customer.list");
			com.yunt.customer.list = {
				cancelAgent : function(id) {
					confirm("确定要取消该客户的代理吗？",function(){
						$.ajax({
						 	type: "POST",
						 	url: "${path}/customer/cancelAgent?id="+id,
						  	processData:true,
						  	success: function(data){
						  		com.yunt.customer.index.refresh();
						  	}
				 		});
					});
				}
			};
		</script>
	</head>
	<body>
		<form id="${pageId}theForm" action="${path}/customer/list?model.name=${model.name}">
			<table class="table" cellspacing="0" cellpadding="0">
				 <thead>
			        <tr>
			            <th width="5%">序号</th>
			            <th width="15%">姓名</th>
			            <th width="15%">微信号</th>
			            <th width="15%">类型</th>
			            <th width="15%">佣金</th>
			            <th width="20%">可提现</th>
			            <th>操作</th>
			        </tr>
			    </thead>
				<tbody>
					<c:forEach items="${pageObj.results}" var="customer" varStatus="i">
						<c:if test="${i.index%2 eq 0}">
							<tr class="a1">
								<td>${(i.index+1)+((pageObj.pageNo-1) * pageObj.pageSize)}</td>
								<td>${customer.name}</td>
								<td>${customer.weChatNum}</td>
								<td>${customer.kindValue}</td>
								<td>${customer.commission}</td>
								<td>${customer.cash}</td>
								<td>
									<c:if test="${customer.kind eq '1'}">
										<input type="button" value="取消代理" class="btn" onclick="com.yunt.customer.list.cancelAgent('${customer.id}');" />
									</c:if>
								</td>
							</tr>
						</c:if>
						<c:if test="${i.index%2 ne 0}">
							<tr class="a2">
								<td>${(i.index+1)+((pageObj.pageNo-1) * pageObj.pageSize)}</td>
								<td>${customer.name}</td>
								<td>${customer.weChatNum}</td>
								<td>${customer.kindValue}</td>
								<td>${customer.commission}</td>
								<td>${customer.cash}</td>
								<td>
									<c:if test="${customer.kind eq '1'}">
										<input type="button" value="取消代理" class="btn" onclick="com.yunt.customer.list.cancelAgent('${customer.id}');" />
									</c:if>
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