<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
	<head>  
		<script type="text/javascript">
			Namespace.register("com.yunt.withdrawCash.list");
			com.yunt.withdrawCash.list = {
				input : function(href) {
					$.dialog.load(href,{
						id: '${pageId}withdrawCashInput',
						title:"添加",
						width:800,
						height:250,
						ok:function(){
							com.yunt.withdrawCash.input.doInput('${pageId}withdrawCashInput');
							return false;
						},
						cancel: function(){
		
						}
					});
				},
				view : function(href) {
					$.dialog.load(href,{
						id: '${pageId}withdrawCashView',
						title:"查看",
						width:800,
						height:500,
						cancel: function(){
		
						}
					});
				},
				comfirm : function(href) {
					$.dialog.load(href,{
						id: '${pageId}withdrawCashComfirm',
						title:"确认打款",
						width:800,
						height:500,
						ok:function(){
							com.yunt.withdrawCash.comfirm.doInput('${pageId}withdrawCashComfirm');
							return false;
						},
						cancel: function(){
		
						}
					});
				}
			};
		</script>
	</head>
	<body>
		<form id="${pageId}theForm" action="${path}/withdrawCash/list?model.code=${model.code}">
			<table class="table" cellspacing="0" cellpadding="0">
				<thead>
			        <tr>
			            <th width="5%">序号</th>
			            <th width="10%">提现单号</th>
			            <th width="10%">开户人</th>
			            <th width="10%">开户银行</th>
			            <th width="10%">金额</th>
			            <th width="10%">客户</th>
			            <th width="10%">日期</th>
			            <th width="10%">状态</th>
			            <th>操作</th>
			        </tr>
			    </thead>
				<tbody>
					<c:forEach items="${pageObj.results}" var="withdrawCash" varStatus="i">
						<c:if test="${i.index%2 eq 0}">
							<tr class="a1">
								<td>${i.index+1}</td>
								<td>${withdrawCash.code}</td>
								<td>${withdrawCash.accountName}</td>
								<td>${withdrawCash.accountBank}</td>
								<td>${withdrawCash.money}</td>
								<td>${withdrawCash.customer.name}</td>
								<td>${withdrawCash.date}</td>
								<td>${withdrawCash.stateValue}</td>
								<td>
									<c:if test="${withdrawCash.state eq '1'}">
										<input type="button" value="查看" class="btn" onclick="com.yunt.withdrawCash.list.view('${path}/withdrawCash/view?id=${withdrawCash.id}');">
									</c:if>
									<c:if test="${withdrawCash.state eq '0'}">
										<input type="button" value="确认打款" class="btn" onclick="com.yunt.withdrawCash.list.comfirm('${path}/withdrawCash/comfirm?id=${withdrawCash.id}');">
									</c:if>
								</td>
							</tr>
						</c:if>
						<c:if test="${i.index%2 ne 0}">
							<tr class="a2">
								<td>${i.index+1}</td>
								<td>${withdrawCash.code}</td>
								<td>${withdrawCash.accountName}</td>
								<td>${withdrawCash.accountBank}</td>
								<td>${withdrawCash.money}</td>
								<td>${withdrawCash.customer.name}</td>
								<td>${withdrawCash.date}</td>
								<td>${withdrawCash.stateValue}</td>
								<td>
									<c:if test="${withdrawCash.state eq '1'}">
										<input type="button" value="查看" class="btn" onclick="com.yunt.withdrawCash.list.view('${path}/withdrawCash/view?id=${withdrawCash.id}');">
									</c:if>
									<c:if test="${withdrawCash.state eq '0'}">
										<input type="button" value="确认打款" class="btn" onclick="com.yunt.withdrawCash.list.comfirm('${path}/withdrawCash/comfirm?id=${withdrawCash.id}');">
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
