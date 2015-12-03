<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=320,user-scalable=no,target-densitydpi=medium-dpi" />
    <meta name="viewport" content="width=device-width,user-scalable=no,target-densitydpi=medium-dpi" />
    <link href="${path }/platform/theme/default/component/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="${path }/platform/theme/default/component/bootstrap/css/star-rating.css" media="all" rel="stylesheet" type="text/css"/>
	<script src="${path }/platform/common/js/jquery.min.js"></script>
	<script src="${path }/platform/theme/default/component/bootstrap/js/star-rating.js" type="text/javascript"></script>
    <title>评价列表</title>
    <script type="text/javascript" src="${path }/platform/common/js/jquery-1.11.1.js"></script>
    <script type="text/javascript">
    	function receiveType(typeVal){
    		if("1" == typeVal){
    			$("#receive_1").show();
    			$("#receive_2").hide();
    		} else {
    			$("#receive_1").hide();
    			$("#receive_2").show();
    			
    		}
    	}
    </script>
</head>

<body>
	<div style="width:96%; background:#ddd; margin:5px auto; margin-bottom:10px; padding:1%; padding-top:5px;">
    	<div style="width:100%; height:50px; border-bottom: #333 solid 1px;margin-bottom:5px;">
            <a href="javaScript:receiveType(1);"  style="width: 49.5%; height: 50px; background-color: #ddd; float: left; line-height: 50px; text-align: center; color:#333; text-decoration:none; ">来自他人</a>
            <span style=" width:0.5%; height:50px; background-color: #ddd; float: left; border-right: #333 solid 1px;"></span>
            <a href="javaScript:receiveType(2);"  style="width: 49.6%; height: 50px; background-color: #ddd; float: left; line-height: 50px; text-align: center; color:#333; text-decoration:none; ">评价他人</a>
        </div>
        <div style="width:100%;  background-color: #ddd;  margin-bottom:5px; " id="receive_1" >
        	<span>来自他人(${receiveList.size()})</span>
        	<c:forEach items="${receiveList }" var="receiveEvaluation">
        		<div style="width:96%; line-height:25px;  margin:0 2%; padding:1%; padding-top:5px; color:#333; text-decoration: none;">
			        <table width="100%">
			            <tr>
			                <td width="35%" align="right">评价人：</td>
			                <td width="60%" align="left">${receiveEvaluation.publisher.name } </td>
			            </tr>
			            <tr>
			                <td width="35%" align="right">任务名称：</td>
			                <td width="60%" align="left">${receiveEvaluation.task.title }  </td>
			            </tr>
			            <tr>
			                <td width="35%" align="right">内容：</td>
			                <td width="60%" align="left">${receiveEvaluation.content } </td>
			            </tr>
			             <tr >
			            	<td width="35%" align="right">综合评价：</td>
			                <td width="60%" align="left">
								<input id="input-21e" value="${publishEvaluation.level}" type="number" class="rating" min=0 max=5 step=1 data-size="xs" disabled="disabled">
							</td>
			            </tr>
			            <tr >
			                <td colspan="2" style=" border-bottom:#333 solid 1px;"></td>
			            </tr>
			        </table>
        		</div>
        	</c:forEach>
        </div>
        <div style="width:100%;  background-color: #ddd;margin-bottom:5px; display: none; " id="receive_2">
        	<span>评价他人(${publishList.size()})</span>
        	<c:forEach items="${publishList }" var="publishEvaluation">
		        <div style="width:96%;  line-height:25px;  margin:0 2%; padding:1%; padding-top:5px; color:#333; text-decoration: none;">
			        <table width="100%">
			            <tr>
			                <td width="35%" align="right">被评价人：</td>
			                <td width="60%" align="left">${publishEvaluation.receiver.name } </td>
			            </tr>
			            <tr>
			                <td width="35%" align="right">任务名称：</td>
			                <td width="60%" align="left">${publishEvaluation.task.title }  </td>
			            </tr>
			            <tr>
			                <td width="35%" align="right">内容：</td>
			                <td width="60%" align="left">${publishEvaluation.content } </td>
			            </tr>
			            <tr >
			            	<td width="35%" align="right">综合评价：</td>
			                <td width="60%" align="left">
								<input id="input-21e" value="${publishEvaluation.level}" type="number" class="rating" min=0 max=5 step=1 data-size="xs" disabled="disabled">
							</td>
			            </tr>
			            <tr >
			                <td colspan="2" style=" border-bottom:#333 solid 1px;"></td>
			            </tr>
			        </table>
        		</div>
        	</c:forEach>
        </div>
    </div>
    
    
<%--        <c:forEach items="${receiveList }" var="receiveEvaluation">
		        <a href="${path }/weChat/evaluationView?evaluationId=${receiveEvaluation.id}" style="width:96%; height:50px; line-height:25px;  margin:0 2%; padding:1%; padding-top:5px; color:#333; text-decoration: none;" >
			        <table width="100%" style="color: #000;">
			            <tr>
			                <td width="35%" align="right">评价人：</td>
			                <td width="60%" align="left">${receiveEvaluation.publisher.name } </td>
			            </tr>
			            <tr>
			                <td width="35%" align="right">任务名称：</td>
			                <td width="60%" align="left">${receiveEvaluation.task.title }  </td>
			            </tr>
			        </table>
			    </a>
        	</c:forEach>
        	<c:forEach items="${publishList }" var="publishEvaluation">
		        <a href="${path }/weChat/evaluationView?evaluationId=${publishEvaluation.id}" style="width:96%; height:50px; line-height:25px;  margin:0 2%; padding:1%; padding-top:5px; color:#333; text-decoration: none;" >
			        <table width="100%" style="color: #000;">
			            <tr>
			                <td width="35%" align="right">任务名称：</td>
			                <td width="60%" align="left">${publishEvaluation.task.title }  </td>
			            </tr>
			        </table>
			    </a>
        	</c:forEach>
        	
        	
        	
--%>
</body>
</html>

