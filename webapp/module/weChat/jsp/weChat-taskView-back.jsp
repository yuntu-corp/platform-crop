<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=320,user-scalable=no,target-densitydpi=medium-dpi" />
    <meta name="viewport" content="width=device-width,user-scalable=no,target-densitydpi=medium-dpi" />
    <title>任务详情</title>
    <script type="text/javascript">
    </script>
</head>

<body>
	<div style="width:96%; height:170px; background:#ddd; margin-bottom:10px; padding:1%; padding-top:5px;">
    	<table width="100%" >
          <tr>
            <td width="35%" align="right">发布人：</td>
            <td width="60%" align="left">${task.publisher.name }</td>
          </tr>
          <tr>
            <td width="35%" align="right">任务类型：</td>
            <td width="60%" align="left">${task.typeVal}</td>
          </tr>
          <tr>
            <td width="35%" align="right">发布日期：</td>
            <td width="60%" align="left"><fmt:formatDate value="${task.createTime}" type="date" pattern="yyyy-MM-dd"/> </td>
          </tr>
          <tr>
            <td width="35%" align="right">完工日期：</td>
            <td width="60%" align="left"><fmt:formatDate value="${task.finishTime}" type="date" pattern="yyyy-MM-dd"/></td>
          </tr>
          <tr>
            <td width="35%" align="right">任务报价：</td>
            <td width="60%" align="left">￥<span style="color:#F9481C">${task.preBitcoin}</span></td>
          </tr>
          <tr>
            <td width="35%" align="right">成交价格：</td>
            <td width="60%" align="left">￥<span style="color:#F9481C">${task.finalBitcoin}</span></td>
          </tr>
		   <tr>
            <td width="35%" align="right">接受人：</td>
            <td width="60%" align="left">${task.receiver.name}</td>
          </tr>
        </table>
    </div>
</body>
</html>
