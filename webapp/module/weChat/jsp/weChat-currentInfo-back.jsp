<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=320,user-scalable=no,target-densitydpi=medium-dpi" />
		<meta name="viewport" content="width=device-width,user-scalable=no,target-densitydpi=medium-dpi" />
		<title>今日价格</title>
		<link href="${path }/platform/theme/default/component/bootstrap/css/bootstrap.min.css" rel="stylesheet">
		<link href="${path }/platform/theme/default/component/bootstrap/css/star-rating.css" media="all" rel="stylesheet" type="text/css"/>
		<script src="${path }/platform/common/js/jquery.min.js"></script>
		<script src="${path }/platform/theme/default/component/bootstrap/js/star-rating.js" type="text/javascript"></script>
		<script type="text/javascript">
		</script>
	</head>
	<body>
		<div style="width:98%; margin: 0 auto; padding: 0 auto; padding-bottom:10px; padding-top: 10px;">
			<div style=" width: 100%; height:50px; background-color:#ddd; border-bottom-style:solid; border-bottom-color:#666; border-bottom-width:2px;">
				<img src="${employee.headimgurl }" style="width: 50px; height:50px; float:left;" />
				<span style="width:40%; height:50px; float:left; line-height:50px; font-size:18px;"> &nbsp;姓名：${employee.name }</span>
				<span style="width:40%; height:50px; float:right; line-height:50px; font-size:12px;">日期：${employee.nowDate }</span>
			</div>
			<div style="width: 100%; height:100px; background-color:#ddd;">
				<table width="100%">
					<tr>
						<td width="35%" align="right">基础虚拟币：</td>
						<td width="65%" align="left">￥<span style="color:#F9481C">${employee.baseBitcoin }</span></td>
					</tr>
					<tr>
						<td width="35%" align="right">当前虚拟币：</td>
						<td width="65%" align="left">￥<span style="color:#F9481C">${employee.nowBitcoin}</span></td>
					</tr>
					<tr>
						<td width="35%" align="right">总体评价：</td>
						<td width="65%" align="left">
							<input id="input-21e" value="${employee.evaluationLevel}" type="number" class="rating" min=0 max=5 step=1 data-size="xs" disabled="disabled">
						</td>
					</tr>
				</table>
			
			</div>
		</div>
		<c:forEach var="emp" items="${employeeList }">
			<c:if test="${emp.id ne employee.id}">
				<div style="width:98%; margin: 0 auto; padding: 0 auto; padding-bottom:10px;">
					<div style=" width: 100%; height:50px; background-color:#ddd; border-bottom-style:solid; border-bottom-color:#666; border-bottom-width:2px;">
						<img src="${emp.headimgurl }" style="width: 50px; height:50px; float:left;" />
						<span style="width:40%; height:50px; float:left; line-height:50px; font-size:18px;"> &nbsp;姓名：${emp.name }</span>
						<span style="width:40%; height:50px; float:right; line-height:50px; font-size:12px;">日期：${emp.nowDate }</span>
					</div>
					<div style="width: 100%; height:100px; background-color:#ddd;">
						<table width="100%">
							<tr>
								<td width="35%" align="right">基础虚拟币：</td>
								<td width="65%" align="left">￥<span style="color:#F9481C">${emp.baseBitcoin }</span></td>
							</tr>
							<tr>
								<td width="35%" align="right">当前虚拟币：</td>
								<td width="65%" align="left">￥<span style="color:#F9481C">${emp.nowBitcoin}</span></td>
							</tr>
							<tr>
								<td width="35%" align="right">总体评价：</td>
								<td width="65%" align="left">
									<input id="input-21e" value="${emp.evaluationLevel}" type="number" class="rating" min=0 max=5 step=1 data-size="xs" disabled="disabled">
								</td>
							</tr>
						</table>
					</div>
				</div>
			</c:if>
		</c:forEach>
	</body>
</html>