<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
	<head>  
		<script type="text/javascript">
			Namespace.register("com.yunt.order.list");
			com.yunt.order.list = {
				input : function(href) {
					$.dialog.load(href,{
						id: '${pageId}orderInput',
						title:"添加",
						width:800,
						height:500,
						lock:true,
						ok:function(){
							com.yunt.order.input.doInput('${pageId}orderInput');
							return false;
						},
						cancel: function(){
		
						}
					});
				},
				deliver : function(href) {
					$.dialog.load(href,{
						id: '${pageId}orderDeliver',
						title:"确认发货",
						width:850,
						height:500,
						lock:true,
						ok:function(){
							com.yunt.order.deliver.doInput('${pageId}orderDeliver');
							return false;
						},
						cancel: function(){
		
						}
					});
				},
				view : function(href) {
					$.dialog.load(href,{
						id: '${pageId}orderView',
						title:"查看",
						width:900,
						height:500,
						lock:true,
						cancel: function(){
		
						}
					});
				}
			};
		</script>
	</head>
	<body>
		<form id="${pageId}theForm" action="${path}/order/list?model.code=${model.code}">
			<table class="table" cellspacing="0" cellpadding="0">
				 <thead>
			        <tr>
			            <th width="5%">序号</th>
			            <th width="15%">订单号</th>
			            <th width="15%">类型</th>
			            <th width="15%">微信昵称</th>
			            <th width="15%">收件人</th>
			            <th width="15%">日期</th>
			            <th width="20%">状态</th>
			            <th>操作</th>
			        </tr>
			    </thead>
				<tbody>
					<c:forEach items="${pageObj.results}" var="order" varStatus="i">
						<c:if test="${i.index%2 eq 0}">
							<tr class="a1">
								<td>${(i.index+1)+((pageObj.pageNo-1) * pageObj.pageSize)}</td>
								<td>${order.code}</td>
								<td>${order.kindValue}</td>
								<td>${order.customer.name}</td>
								<td>${order.receiver}</td>
								<td>${order.date}</td>
								<td>${order.stateValue}</td>
								<td>
									<c:if test="${order.state eq '1'}">
										<input type="button" value="确认发货" class="btn" onclick="com.yunt.order.list.deliver('${path}/order/deliver?id=${order.id}');">
									</c:if>
									<c:if test="${order.state ne '1'}">
										<input type="button" value="查看" class="btn" onclick="com.yunt.order.list.view('${path}/order/view?id=${order.id}');">
									</c:if>
								</td>
							</tr>
						</c:if>
						<c:if test="${i.index%2 ne 0}">
							<tr class="a2">
								<td>${(i.index+1)+((pageObj.pageNo-1) * pageObj.pageSize)}</td>
								<td>${order.code}</td>
								<td>${order.kindValue}</td>
								<td>${order.customer.name}</td>
								<td>${order.receiver}</td>
								<td>${order.date}</td>
								<td>${order.stateValue}</td>
								<td>
									<c:if test="${order.state eq '1'}">
										<input type="button" value="确认发货" class="btn" onclick="com.yunt.order.list.deliver('${path}/order/deliver?id=${order.id}');">
									</c:if>
									<c:if test="${order.state ne '1'}">
										<input type="button" value="查看" class="btn" onclick="com.yunt.order.list.view('${path}/order/view?id=${order.id}');">
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
