<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=320,user-scalable=no,target-densitydpi=medium-dpi" />
    <meta name="viewport" content="width=device-width,user-scalable=no,target-densitydpi=medium-dpi" />
    <title>讨价</title>
    <script type="text/javascript" src="${path }/platform/common/js/jquery-1.11.1.js"></script>
    <script type="text/javascript">
	    var intervalLine = setInterval(function() {
			$("#text1").hide();
			$("#text2").hide();
		}, 3000);
    	function accMul(arg1,arg2) {
			var m=0,s1=arg1.toString(),s2=arg2.toString();
			try{m+=s1.split(".")[1].length}catch(e){}
			try{m+=s2.split(".")[1].length}catch(e){}
			return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m);
	    }
    	function selectBitcoin() {
    		if("" != $("#select_bitcoin").val()){
    			$("#bitcoinSee").html(accMul($("#select_bitcoin").val() , '${task.preBitcoin}'));
    			$("#bitcoin").val($("#bitcoinSee").text().trim());
    		} else {
				$("#text1").show();
				intervalLinel;
    		}
		}
    	function submitBitcoin(){
    		if("" == $("#select_bitcoin").val()){
				$("#text1").show();
				intervalLinel;
				return false;
    		}
    		$("#conferm").attr("disabled","disabled");
    		$.ajax({
				url : "${path }/weChat/doDicker",//提交还价的地址
				data : $("#form").serialize(),
				type : "post",
				async : true,
				success : function(data) {
					$("#text2").show();
					intervalLinel;
				}
			});
    	}
    </script>
</head>

<body>
	<div style="width:96%; height:150px; background:#ddd; margin-bottom:10px; padding:1%; padding-top:5px;">
    	<form action=""  id="form">
    	<table width="100%" >
          <tr>
            <td width="35%" align="right">任务主题：</td>
            <td width="60%" align="left">${task.title}</td>
          </tr>
          <tr>
            <td width="35%" align="right">当前价格：</td>
            <td width="60%" align="left">￥<span style="color:#F9481C">${task.preBitcoin}</span></td>
          </tr>
          <tr>
            <td width="35%" align="right">价格加倍：</td>
			<td width="60%" align="left">
            	<select style="width:90%;" id="select_bitcoin" onchange="selectBitcoin()">
                	<option value="" selected="selected">请选择加价倍数</option>
                    <option value="1.1" >1.1</option>
                    <option value="1.2">1.2</option>
                    <option value="1.3">1.3</option>
                </select>
            </td>
          </tr>
          <tr>
            <td width="35%" align="right">最终价格：</td>
            <td width="60%" align="left">
            	￥<span style="color:#F9481C" id="bitcoinSee">${task.preBitcoin}</span>
            </td>
          </tr>
          <tr>
            <td colspan="2" >
        		<input id="conferm" value="确认" type="button" style="width:70px; height:30px; float:right;" onclick="submitBitcoin()">确认</input>
            </td>
          </tr>
        </table>
        <input type="hidden" name="taskId" value="${task.id}"/>
        <input type="hidden" name="publisherId" value="${publisherId }"/>
        <input type="hidden" name="receiverId" value="${receiverId }"/>
        <input type="hidden" name="dicker.bitcoin" value="" id="bitcoin"/>
        </form>
    </div>
    <div id="text1" style="font-size: 20px; background-color: #000; opacity: 0.8; width: 170px; text-align: center; border: 1px solid #fff; border-radius: 4px; line-height: 36px; position: fixed; top: 50%; left: 25%; color: #FF2FBD; display: none;">请选择加价倍数！</div>
    <div id="text2" style="font-size: 20px; background-color: #000; opacity: 0.8; width: 170px; text-align: center; border: 1px solid #fff; border-radius: 4px; line-height: 36px; position: fixed; top: 50%; left: 25%; color: #FF2FBD; display: none;">加价成功！</div>
</body>
</html>
