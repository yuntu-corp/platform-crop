<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=320,user-scalable=no,target-densitydpi=medium-dpi" />
    <meta name="viewport" content="width=device-width,user-scalable=no,target-densitydpi=medium-dpi" />
    <title>待处理</title>

    <script type="text/javascript" src="${path }/platform/common/js/jquery-1.11.1.js"></script>
        <script type="text/javascript">
	    var intervalLine = setInterval(function() {
			$("#text1").hide();
			$("#text2").hide();
			$("#text3").hide();
			$("#text4").hide();
			$("#text5").hide();
			$("#text6").hide();
		}, 3000);
    	function refuseTask(taskIdVal) {
    		$("#refuseTaskButton").attr("disabled","disabled");
    		$.ajax({
				url : "${path }/weChat/refuseTask?taskId=" + $("#taskId_" + taskIdVal).val() + "&employeeId=${employeeId}",//拒绝任务的地址
				data : null,
				type : "post",
				async : false,
				success : function(data) {
					$("#text1").show();
					intervalLinel;
					 refresh();
				}
			});
		}
    	function acceptTask(taskIdVal) {
    		$("#acceptTaskButton").attr("disabled","disabled");
    		$.ajax({
				url : "${path }/weChat/acceptTask?taskId=" + $("#taskId_" + taskIdVal).val() + "&employeeId=${employeeId}",//接受任务的地址
				data : null,
				type : "post",
				async : false,
				success : function(data) {
					if(data=="success"){
						$("#text2").show();
						intervalLinel;
					} else {
						$("#text6").show();
						intervalLinel;
					}
					window.location.reload();
				}
			});
		}
    	function refuseDicker(dickerIdVal) {
    		$("#refuseDickerButton").attr("disabled","disabled");
    		$.ajax({
				url : "${path }/weChat/refuseDicker?dickerId=" + $("#dickerId_" + dickerIdVal).val(),//拒绝还价的地址
				data : null,
				type : "post",
				async : false,
				success : function(data) {
					$("#text3").show();
					intervalLinel;
					window.location.reload();
				}
			});
		}
    	function acceptDicker(dickerIdVal) {
    		$("#acceptDickerButton").attr("disabled","disabled");
    		$.ajax({
				url : "${path }/weChat/acceptDicker?dickerId=" + $("#dickerId_" + dickerIdVal).val(),//接受还价的地址
				data : null,
				type : "post",
				async : false,
				success : function(data) {
					if(data=="success"){
						$("#text4").show();
						intervalLinel;
					} else {
						$("#text5").show();
						intervalLinel;
					}
				}
			});
		}
    </script>
</head>

<body>
	<div style="width:98%; height:50px; border-bottom: #333 solid 1px;margin-bottom:5px;">
        <a href="#" style="width: 49.5%; height: 50px; background-color: #ddd; float: left; line-height: 50px; text-align: center; color:#333; text-decoration:none; ">来自他人</a>
        <span style=" width:0.5%; height:50px; background-color: #ddd; float: left; border-right: #333 solid 1px;"></span>
        <a href="#" style="width: 49.6%; height: 50px; background-color: #ddd; float: left; line-height: 50px; text-align: center; color:#333; text-decoration:none; ">我发布的</a>
    </div>
<!-- 可接受任务的人 -->
	<div style="width:98%; ">
		<c:forEach items="${unTreatedMsgListByOthers}" var="taskFromOthers" varStatus="v">
			<div style="width:100%;  background:#ddd; line-height:80px; margin-bottom:10px;">
				<div style="width:100%; height:80px; background:#ddd; line-height:80px;">
		    		<img src="heardimg.png" style=" margin:5px; width:70px; height:70px; float:left;"/><span style="width:70%; height:80px; line-height:80px; float:left;  font-size:18px;">${taskFromOthers.title }</span>
				</div>
				<div style="width:94%; height:25px; line-height:25px; font-size:13px; text-align:right; padding: 0 3%; border-bottom:#333 1px solid;">
		        	共1个任务&nbsp;&nbsp;虚拟币：${taskFromOthers.preBitcoin }个&nbsp;&nbsp;工期：${taskFromOthers.durTime }天
		        </div>
		        <div style="width:100%; height:30px; background-color:#ddd; ">
		            <a id="refuseTaskButton" href="javaScript:void(0);" onclick="refuseTask(${v.count})" style="width:50px; border:solid 1px #333; text-align:center; background-color:#ddd; height:25px; float:right; color:#333; text-decoration:none; line-height: 25px;">
		            	拒绝
		            </a>
		            <a href="${path }/weChat/goDicker?taskId=${taskFromOthers.id}&publisherId=${employeeId}&receiverId=${taskFromOthers.publisher.id}" style="width:50px; border:solid 1px #333; text-align:center; background-color:#ddd; height:25px; float:right; color:#333; text-decoration:none; line-height: 25px;">
		            	加价
		            </a>
		        	<a id="acceptTaskButton" href="javaScript:void(0);" onclick="acceptTask(${v.count})" style="width:50px; border:solid 1px #333; text-align:center; background-color:#ddd; height:25px; float:right; color:#333; text-decoration:none; line-height: 25px;">
		        		确认
		        	</a>
		        	<input id="taskId_${v.count}"  value="${taskFromOthers.id }" type="hidden" />
		        </div>
		    </div>
		</c:forEach>
    </div>
<!-- 发布任务的人 -->
	<div style="width:98%; ">
		<c:forEach items="${dickerList }" var="dicker" varStatus="d">
			<div style="width:100%; height:125px; background:#ddd; line-height:80px; margin-bottom:10px; ">
				<div style="width:100%; height:80px; background:#ddd; line-height:80px; ">
		    		<img src="heardimg.png" style=" margin:5px; width:70px; height:70px; float:left;"/><span style="width:70%; height:80px; line-height:80px; float:left;  font-size:18px;">${dicker.task.title }</span>
				</div>
				<div style="width:94%; height:25px; line-height:25px; font-size:13px; text-align:right; padding: 0 3%; border-bottom:#333 1px solid;">
		        	共1个任务&nbsp;&nbsp;虚拟币：${dicker.task.preBitcoin }个&nbsp;&nbsp;工期：${dicker.task.durTime }天
		        </div>
		        <!-- 有人加价的情况下显示 -->
		        <div style="width:100%; height:30px; background-color:#ddd; border-bottom:#333 dashed 1px;">
		            <a  id="refuseDickerButton" href="javaScript:void(0);" onclick="refuseDicker(${d.count})" style="width:50px; border:solid 1px #333; text-align:center; background-color:#ddd; height:25px; float:right; color:#333; text-decoration:none; line-height: 25px;">
		            	拒绝
		            </a>
		        	<a  id="acceptDickerButton" href="javaScript:void(0);" onclick="acceptDicker(${d.count})" style="width:50px; border:solid 1px #333; text-align:center; background-color:#ddd; height:25px; float:right; color:#333; text-decoration:none; line-height: 25px;">
		        		确认
		        	</a>
		        	<input id="dickerId_${d.count}"  value="${dicker.id }" type="hidden" />
		            <span style="width:50%; height:30px; line-height:30px; float:right; ">${dicker.publisher.name }加价：￥<span style="color:#F9481C">${dicker.bitcoin }</span></span>
		        </div>
		    </div>
		</c:forEach>
		<!-- <div style="width:98%; height:12 5px; background:#399; line-height:80px; margin-bottom:10px; ">
			<div style="width:100%; height:80px; background:#969; line-height:80px; ">
	    		<img src="heardimg.png" style=" margin:5px; width:70px; height:70px; float:left;"/><span style="width:70%; height:80px; line-height:80px; float:left;  font-size:18px;">三江尊园拼图游戏</span>
			</div>
			<div style="width:94%; height:25px; line-height:25px; font-size:13px; text-align:right; padding: 0 3%; border-bottom:#333 1px solid;">
	        	共1个任务&nbsp;&nbsp;虚拟币：10000个&nbsp;&nbsp;工期：3天
	        </div>
	        有人加价的情况下显示
	        <div style="width:100%; height:30px; background-color:#FC6; border-bottom:#333 dashed 1px;">
	            <a href="#" style="width:50px; border:solid 1px #333; text-align:center; background-color:#369; height:25px; float:right; color:#333; text-decoration:none; line-height: 25px;">拒绝</a>
	        	<a href="#" style="width:50px; border:solid 1px #333; text-align:center; background-color:#369; height:25px; float:right; color:#333; text-decoration:none; line-height: 25px;">确认</a>
	            <span style="width:50%; height:30px; line-height:30px; float:right; ">李四加价：￥<span style="color:#F9481C">10000</span></span>
	        </div>
	        <div style="width:100%; height:30px; background-color:#FC6; ">
	            <a href="#" style="width:50px; border:solid 1px #333; text-align:center; background-color:#369; height:25px; float:right; color:#333; text-decoration:none; line-height: 25px;">拒绝</a>
	        	<a href="#" style="width:50px; border:solid 1px #333; text-align:center; background-color:#369; height:25px; float:right; color:#333; text-decoration:none; line-height: 25px;">确认</a>
	            <span style="width:50%; height:30px; line-height:30px; float:right; ">张三加价：￥<span style="color:#F9481C">20</span></span>
	        </div>	
	    </div> -->
	</div>
	<div id="text1" style="font-size: 20px; background-color: #000; opacity: 0.8; width: 170px; text-align: center; border: 1px solid #fff; border-radius: 4px; line-height: 36px; position: fixed; top: 50%; left: 25%; color: #FF2FBD; display: none;">拒绝任务成功！</div>
    <div id="text2" style="font-size: 20px; background-color: #000; opacity: 0.8; width: 170px; text-align: center; border: 1px solid #fff; border-radius: 4px; line-height: 36px; position: fixed; top: 50%; left: 25%; color: #FF2FBD; display: none;">接受任务成功！</div>
    <div id="text3" style="font-size: 20px; background-color: #000; opacity: 0.8; width: 170px; text-align: center; border: 1px solid #fff; border-radius: 4px; line-height: 36px; position: fixed; top: 50%; left: 25%; color: #FF2FBD; display: none;">拒绝还价成功！</div>
    <div id="text4" style="font-size: 20px; background-color: #000; opacity: 0.8; width: 170px; text-align: center; border: 1px solid #fff; border-radius: 4px; line-height: 36px; position: fixed; top: 50%; left: 25%; color: #FF2FBD; display: none;">接受还价成功！</div>
    <div id="text5" style="font-size: 20px; background-color: #000; opacity: 0.8; width: 170px; text-align: center; border: 1px solid #fff; border-radius: 4px; line-height: 36px; position: fixed; top: 50%; left: 25%; color: #FF2FBD; display: none;">您的余额不足！</div>
    <div id="text6" style="font-size: 20px; background-color: #000; opacity: 0.8; width: 170px; text-align: center; border: 1px solid #fff; border-radius: 4px; line-height: 36px; position: fixed; top: 50%; left: 25%; color: #FF2FBD; display: none;">发布人余额不足！</div>
</body>
</html>
