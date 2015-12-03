<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=320,user-scalable=no,target-densitydpi=medium-dpi" />
    <meta name="viewport" content="width=device-width,user-scalable=no,target-densitydpi=medium-dpi" />
    <title>任务公告</title>
    <script type="text/javascript">
    </script>
</head>

<body>
	<c:forEach items="${taskList }" var="task">
		<div style="width:98%; height:80px; background:#ddd; line-height:80px; margin-bottom:10px; padding-left: 10px;">
			<a href="${path }/weChat/taskView?id=${task.id}&employeeId=${employee.id}" style="width:98%; height:80px; line-height:80px;">
		    	<span style="width:60%; height:80px; line-height:80px; float:left;  font-size:18px; color: #333;">${task.title }</span>
		        <span style="width:25%; height:80px; float:right; line-height:80px; font-size:15px; color:#EAB648">${task.statusVal }</span>
		    </a>
	    </div>
    </c:forEach>
</body>
</html>
