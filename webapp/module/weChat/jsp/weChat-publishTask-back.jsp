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
    <link href="${path }/platform/theme/default/component/bootstrap/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
	<script src="${path }/platform/common/js/jquery-1.8.3.min.js"></script>
	<script src="${path }/platform/theme/default/component/bootstrap/js/bootstrap.min.js"></script>
	<script src="${path }/platform/theme/default/component/bootstrap/js/bootstrap-datetimepicker.js"></script>
	<script src="${path }/platform/theme/default/component/bootstrap/js/locales/bootstrap-datetimepicker.fr.js"></script>
	
	<script src="${path}/module/weChat/amazeui/assets/js/jquery.min.js"></script>
	<script src="${path}/module/weChat/amazeui/assets/js/amazeui.js"></script>
	<script src="${path}/module/weChat/amazeui/assets/js/handlebars.min.js"></script>
	<script src="${path}/module/weChat/amazeui/assets/js/amazeui.widgets.helper.js"></script>
    <title>发布任务</title>
    
    
    

    <script type="text/javascript">
		var intervalLine = setInterval(function() {
			$("#text1").hide();
			$("#text2").hide();
		}, 3000);
		function taskSave(){
			$.ajax({
				url : "${path }/weChat/doPublishTask",//发布任务的地址
				data : $("#form").serialize(),
				type : "post",
				async : false,
				success : function(data) {
					if(data=="success"){
						$("#text1").show();
						intervalLinel;
					} else {
						$("#text2").show().html(data);
						intervalLinel;
					}
				}
			});
		}
    </script>
</head>

<body>

	<div style="width:96%; height:250px; background:#ddd; margin:5px auto; margin-bottom:10px; padding:1%; padding-top:5px;">
    	<form action=""  id="form">
	    	<table width="100%" >
	          <tr>
	            <td width="35%" align="right">标题：</td>
	            <td width="60%" align="left">
			    	<input name="employeeId" type="hidden" value="${employeeId }">
		            <input name="task.title" type="text" maxlength="20" style="width:90%;">
	            </td>
	          </tr>
	          <tr>
	            <td width="35%" align="right">描述：</td>
	            <td width="60%" align="left"><input name="task.content" type="text" maxlength="20" style="width:90%;"></td>
	          </tr>
	          <tr>
	            <td width="35%" align="right">任务类型：</td>
	            <td width="60%" align="left">
	            	<select style="width:90%;" name="task.type">
	                	<option selected="selected">请选择</option>
	                    <option value="0" >技术开发</option>
	                    <option value="1" >售后</option>
	                    <option value="2" >销售</option>
	                </select>
	            </td>
	          </tr>
	          <tr>
	            <td width="35%" align="right">完工日期：</td>
	            <td width="60%" align="left"><input id="form_datetime" name="task.finishTime" size="19" type="text" style="width:90%;" ></td>
	          </tr>
	          <tr>
	            <td width="35%" align="right">任务报价：</td>
	            <td width="60%" align="left"><input name="task.preBitcoin" type="text" maxlength="20" style="width:90%;"></td>
	          </tr>
	          <tr>
	            <td width="35%" align="right">人员：</td>
	            <td width="60%" align="left">
	            	 <select style="width:90%;" name="task.employeesString" multiple="multiple">
	            		<option selected="selected">请选择</option>
	            	 	<c:forEach items="${allEmployes }" var="emp">
	            	 		<option value="${emp.userId }" >${emp.name }</option>
	            	 	</c:forEach>
	                </select>
	            </td>
	          </tr>
	          <tr>
	          	<td colspan="2" width="95%" align="center">
	            	<input type="button" value="发布" onclick="taskSave()" />
	            </td>
	          </tr>
	        </table>
        </form>
    </div>
    <div id="text1" style="font-size: 20px; background-color: #000; opacity: 0.8; width: 170px; text-align: center; border: 1px solid #fff; border-radius: 4px; line-height: 36px; position: fixed; top: 50%; left: 25%; color: #FF2FBD; display: none;">发布成功！</div>
    <div id="text2" style="font-size: 20px; background-color: #000; opacity: 0.8; width: 170px; text-align: center; border: 1px solid #fff; border-radius: 4px; line-height: 36px; position: fixed; top: 50%; left: 25%; color: #FF2FBD; display: none;">您的余额不足！</div>
	<script type="text/javascript">
		$(document).ready(function(){ 
			$('#form_datetime').datetimepicker({
		        //language:  'fr',
		        weekStart: 1,
		        todayBtn:  1,
				autoclose: 1,
				todayHighlight: 1,
				startView: 2,
				forceParse: 0,
		        showMeridian: 1
		    });
		});
	</script>
</body>
</html>
