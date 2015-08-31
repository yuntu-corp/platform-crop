<!DOCTYPE html>
<%@ page language="java" import="java.util.*"  pageEncoding="UTF-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="description" content="北京合众天恒科技有限公司 数字化校园 中小学 软件平台" />
<title>数字化校园综合应用平台</title>
	<script src="${path }/platform/common/js/prototype-ext.js"></script>
<script type="text/javascript">
	//注册产品全局变量方法集
	Namespace.register('com.ue.global');
	com.ue.global = {
		path : '<%=path%>',
		basePath : '<%=basePath%>',
		jsessionid : '${pageContext.session.id}',//sessionId用于flash中提交文件无法使用当前session
		theme : 'default',
		productTitle:''
	}
</script>
	<script src="${path }/platform/common/js/jquery-1.11.1.js"></script>
	<script src="${path }/platform/common/js/jquery-ext.js"></script>
	<script src="${path }/platform/common/js/jquery.i18n.properties-1.0.9.js"></script>
	<script src="${path }/platform/component/page/jquery.page.js"></script>
    <link rel="stylesheet" href="${path}/demo/common/css/style.css"/>
    <link rel="stylesheet" href="${path}/platform/theme/default/component/page/css/pageBar.css"/>
</head>
<body>
<form id="theForm_1_1" action="${path}/dm/page/demo1">

			<table class="cssTableBody">	
				<thead>
					<tr>
						<th width="3%" align="center">
							序号
						</th>
						<th nowrap="nowrap">
							姓名
						</th>
						<th nowrap="nowrap">
							邮箱
						</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${not empty pageObj}">
						<c:forEach items="${pageObj.list}" var="item" varStatus="st">
						<tr>
							<td field="1" class="num">${st.index + 1}</td>
							<td field="3">${item.name }</td>
							<td field="4">${item.email }</td>
						</tr>
						</c:forEach>
					</c:if>	
				</tbody>
			</table>
	
	<!--分页栏开始-->
	<div id="pageBarDemo" ></div>
</form>
</body>
<script type="text/javascript">
$(document).ready(function() {
	$.hz.page.init("pageBarDemo",{
// 		type:"simple",
		changePageSize:true,
		changePageSizeNumber:"10,20,30",
		pageInfo:${pageObj.info},
		formId:"theForm_1_1"
	});
});
</script>
</html>
