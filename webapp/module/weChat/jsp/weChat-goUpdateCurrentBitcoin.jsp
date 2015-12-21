<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp"%>
<!doctype html>
<html>
<head>
	<meta charset="utf-8"/>
	<title>修改今日价格</title>
	<meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<link href="${path }/module/weChat/jump/css/jump-demo.css" rel="stylesheet" type="text/css">
    <link href="${path }/module/weChat/jump/css/flat.color.css" rel="stylesheet" type="text/css">
    <link href="${path }/module/weChat/jump/css/flat.component.css" rel="stylesheet" type="text/css">
    <link href="${path }/module/weChat/jump/css/iconline.css" rel="stylesheet" type="text/css">
    <style type="text/css">
    	input::-webkit-outer-spin-button,
		input::-webkit-inner-spin-button{
		    -webkit-appearance: none !important;
		    margin: 0; 
		}/*chrome*/
		input[type="number"]{-moz-appearance:textfield;}/*FireFox*/
    </style>
</head>

<body>
	<div class="jump-container">
    	<div class="jump-content">
           <div style=" border-bottom:1px solid #ddd;border-top:1px solid #ddd;height:40px; margin-top:20px; padding:0 10px;background-color:#fff; ">
           	<input type="number" style="width:100%; height:100%;" placeholder="${employee.currentBitcoin }" id="currentBitcoin" min="0" max="100000" required>
           </div>
            <div style="margin-top:80px; padding:0 10px;"><button class="block" onclick="updateCurrentBitcoin()">提交</button></div>
            <!-- <div style="margin-top:10px; padding:0 10px;"><button class="block cancel" >取消</button></div> -->
        </div>
    </div>
    
    <script src="${path }/module/weChat/jump/js/zepto.min.js"></script>
    <script type="text/javascript">
		function updateCurrentBitcoin() {
			if($("#currentBitcoin").val().trim().length <= 0){
				alert("今日价格不能为空！");
				return false;
			} else if (!$("#currentBitcoin")[0].checkValidity()) {
				alert("请输入1万以内的整数虚拟币数！");
				return false;
			} else if($("#currentBitcoin")[0].value.indexOf('0')==0){
				alert("请输入首字符不为0的虚拟币数！");
				return false;
			}
			$.ajax({
				url : "${path }/weChat/updateCurrentBitcoin?id=" + '${employee.id}' + "&currentBitcoin=" + $("#currentBitcoin").val(),//接受还价的地址
				data : null,
				type : "post",
				async : false,
				success : function(data) {
					alert("您已成功修改今日价格！");
					//if (data == "success") {
					//} else {
					//	alert(data);
					//}
					setTimeout(function(){window.location.href = "${path }/weChat/personalCenter?employeeId="+'${employee.id}';}, 500);
				}
			});
		}
    </script>
</body>
</html>
