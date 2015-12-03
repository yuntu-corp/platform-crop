<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=320,user-scalable=no,target-densitydpi=medium-dpi" />
    <meta name="viewport" content="width=device-width,user-scalable=no,target-densitydpi=medium-dpi" />
    <title>评价详情</title>
	<link href="${path }/platform/theme/default/component/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="${path }/platform/theme/default/component/bootstrap/css/star-rating.css" media="all" rel="stylesheet" type="text/css"/>
	<script src="${path }/platform/common/js/jquery.min.js"></script>
	<script src="${path }/platform/theme/default/component/bootstrap/js/star-rating.js" type="text/javascript"></script>
</head>

<body>
	<div style="width:96%; height:140px; background:#ddd; margin-bottom:10px; padding:1%; padding-top:5px;">
        <table width="100%">
            <tr>
                <td width="35%" align="right">评价人：</td>
                <td width="60%" align="left">${evaluation.publisher.name } </td>
            </tr>
            <tr>
                <td width="35%" align="right">任务名称：</td>
                <td width="60%" align="left">${evaluation.task.title }  </td>
            </tr>
            <tr>
                <td width="35%" align="right">内容：</td>
                <td width="60%" align="left">${evaluation.content } </td>
            </tr>
            <tr >
                <td colspan="2" style=" border-bottom:#333 solid 1px;"></td>
            </tr>
            <tr >
                <td align="right" colspan="2">
                    <span style="float:right"> <input id="input-21e" value="${evaluation.level } " type="number" class="rating" min=0 max=5 step=1 data-size="xs" disabled="disabled" ></span>
            		<span style="float:right">综合评价 &nbsp;</span>
                </td>
            </tr>
        </table>
    </div>
</body>
</html>
