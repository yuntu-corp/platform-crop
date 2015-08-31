<%@ page language="java" import="java.util.*"  pageEncoding="UTF-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<form id="theForm_2_1" action="${path}/dm/page/ajaxDemo1/${type}">
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
<script>
	$(function(){
		$.hz.page.init("pageBarDemo",{
			type:"${type}",
			pageInfo:${pageObj.info},
			formId:"theForm_2_1",
			containerId:"ajaxPage1"
		});
	});	
</script>