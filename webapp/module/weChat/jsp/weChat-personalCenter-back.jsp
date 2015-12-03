<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<!DOCTYPE html>
<html>
	<head>
    	<meta charset="utf-8">
		<meta name="viewport" content="width=320,user-scalable=no,target-densitydpi=medium-dpi" />
		<meta name="viewport" content="width=device-width,user-scalable=no,target-densitydpi=medium-dpi" />
		<title>个人中心</title>
		<script type="text/javascript">
		</script>
	</head>
	<body>
		<div style="width:100%; margin: 0 auto; padding: 0 auto; padding-bottom:10px;">
        	<div style=" width: 100%; height:50px; background-color:#ddd; border-bottom-style:solid; border-bottom-color:#666; border-bottom-width:2px;">
            	<img src="${employee.headimgurl }" style="width: 50px; height:50px; float:left;" />
                <span style="width:40%; height:50px; float:left; line-height:50px; font-size:18px;"> &nbsp;${employee.name }</span>
                <span style="width:40%; height:50px; float:right; line-height:50px; font-size:12px;">职位：${employee.wxCpUser.position}</span>
            </div>
            
            <div style="width:100%; height:50px; line-height:50px; text-align:center; background-color:#ddd border-bottom:solid 1px #666;">
				<a href="${path }/weChat/myPublishedTask?employeeId=${employee.id}" style="width:24.5%; height:50px; border-right:solid 1px #666; float:left; color:#333; text-decoration:none; ">已发布</a>
                <a href="${path }/weChat/msgList?employeeId=${employee.id}" style="width:24.5%; height:50px; border-right:solid 1px #666; float:left; color:#333; text-decoration:none; ">待处理</a>
                <a href="${path }/weChat/evaluationList?employeeId=${employee.id}" style="width:24.5%; height:50px; border-right:solid 1px #666; float:left; color:#333; text-decoration:none; ">评价</a>
                <a href="${path }/weChat/myReceivedTask?employeeId=${employee.id}" style="width:24.5%; height:50px; float:left; color:#333; text-decoration:none; ">已接受</a>
            </div>
            
            <div style="width: 100%; height:120px; background-color:#ddd; margin-top:10px;">
                <table width="100%" >
                    <tr>
                        <td width="35%" align="right">当月基础币：</td>
                        <td width="60%" align="left">￥<span style="color:#F9481C">${employee.baseBitcoin }</span></td>
                    </tr>
                    <tr>
                        <td width="35%" align="right">本月收入币：</td>
                        <td width="60%" align="left">￥<span style="color:#F9481C">${employee.bitcoinIncome }</span></td>
                    </tr>
                    <tr>
                        <td width="35%" align="right">本月消耗币：</td>
                        <td width="60%" align="left">￥<span style="color:#F9481C">${employee.bitcoinConsume }</span></td>
                    </tr>
                    <tr>
                        <td width="35%" align="right">当前余额：</td>
                        <td width="60%" align="left">￥<span style="color:#F9481C">${employee.bitcoinSurplus }</span></td>
                    </tr>
                </table>
            </div>
            <div style="width:100%; height:50px; line-height:50px; text-align:center; background-color:#ddd; margin-top:10px;">
         	   <a href="${path }/weChat/publishTask?employeeId=${employee.id}" style="width:100%; height:50px; background:#ddd; float:left; text-align:center; color:#333; text-decoration:none; ">发布任务</a>
            </div>
        </div>
	</body>
</html>