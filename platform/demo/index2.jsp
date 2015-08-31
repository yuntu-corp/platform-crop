<!DOCTYPE html>
<%@page import="com.unitever.platform.util.UUID"%>
<%@page import="com.unitever.demo.model.TBUser"%>
<%@page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
	<head>  
    <title>平台</title>
    <%@ include file="/platform/common/jsp/baseJs.jsp"%>
	</head>
	<script type="text/javascript">
	
		function load(url,ipt){
			$(ipt).after("<div></div>");
			$(ipt).next().load(url);
		}
		
		function popupTest0(){
			$.dialog.load("${path}/dm/test.jsp",{
				title:"组件测试",
				width:"70%",
				height:"60%",
				ok:function(){
					
				}
			})
		}
		
		function popup(url){
			$.dialog.load(url,{
				title:"组件测试",
				width:700,
				height:600,
				ok:function(){
					
				}
			})
		}
		
		function copyToClip(){
			$("#copyBtn").zeroclipboard("copyFrom",true);
		}
		
		function maskDemo(){
			$("body").mask("正在加载...");
			setTimeout(function(){
				$("body").unmask();
			},5000);
		}
		
		function progressDemo(){
			$("#progressDiv").progressBar(50);
		}
		
		function printDemo(){
			com.ue.print.previewHtml($("body").html());
		}
		
		function gotoPage(url){
			location.href = url;
		}
		
		function goUserList(){
			var url = "${path}/dm/user/list.do";
			$.dialog.load(url,{
				title:"用户列表",
				width:700,
				height:600
// 				ok:function(){
// 					com.ue.platform.module.user.doAdd();
// 				}
			})
		}
		
		function addUser(){
			var url = "${path}/dm/user/input.do";
			$.dialog.load(url,{
				title:"添加用户",
				width:700,
				height:600
// 				ok:function(){
// 					com.ue.platform.module.user.doAdd();
// 				}
			})
		}
		
		$(document).ready(function(){
			copyToClip();
			$("#entryModeSwitch").iButton();
// 			$("body").jeegoocontext("menu4Desktop",{
				
// 			})
			
			$("#tooltipDiv").tooltip($("#tooltipDiv").attr("title"),{hover:1});
			$("#poshytipDiv").poshytip({className: 'tip-violet'});
			$("#ratyDiv").raty();
			
			$("#formId").validationEngine();
			
			$("#uploadDiv").swfupload();
		})
		
	</script>
	<body>
		<%
			long t1 = System.currentTimeMillis();
		%>
	  	index.jsp
	  	<a id="tooltipDiv" title="提示title。。。。。。。。。。。">提示信息。。。。。。。。</a>
	  	<a id="poshytipDiv" title="poshytip提示title。。。。。。。。。。。">poshytip提示信息。。。。。。。。</a>
	  	<hr>
	  	<input type="button" value="test1" onclick="load('${path}/dm/test.do',this)"><hr>
	  	<input type="button" value="saveUser" onclick="load('${path}/dm/user/save.do',this)"><hr>
	  	<input type="button" value="listUser" onclick="load('${path}/dm/user/list.do',this)"><hr>
	  	<%
	  		TBUser u0 = new TBUser();
	  		  		u0.setId(UUID.getUUID());
	  		  		u0.setName("张三");
	  		  		
	  		  		TBUser u1 = new TBUser();
	  		  		u1.setId(UUID.getUUID());
	  		  		u1.setName("李四");
	  		  		
	  		  		TBUser u2 = new TBUser();
	  		  		u2.setId(UUID.getUUID());
	  		  		u2.setName("王五");
	  		  		
	  		  		List<TBUser> users = new ArrayList<TBUser>();
	  		  		users.add(u0);
	  		  		users.add(u1);
	  		  		users.add(u2);
	  		  		request.setAttribute("users", users);
	  	%>
	  		<uc:form action="aaa" method="post" id="formId">
<%-- 	  	<c:forEach begin="0" end="10000" step="1"> --%>
	  	<uc:textfield required="true" name="aaa" id="aaa" value="123132 " placeholder="a132456"></uc:textfield>
<%-- 	  	</c:forEach> --%>
			<uc:integer name="aaa" minValue="10" maxValue="20" asfd="safdsfda"></uc:integer>
			<uc:float name="bbb" decimalScale="2"></uc:float>
			<label style="color: graytext; line-height: 1.3; margin-top: 8px; margin-right: 0px; margin-bottom: 0px; margin-left: 10px; position: absolute; cursor: text;" for="20140717161744159442558708703424"></label>
			<uc:password name="cc" placeholder="请输入密码123"></uc:password>
			<uc:textarea name="dd" value="123" placeholder="文本框" rows="3" cols="20" aa="Bb" maxlength="5"></uc:textarea>
			<uc:hidden name="ee" value="123465"></uc:hidden>
			<uc:radio name="ff" label="radio1" labelEnableI18n="true" aaa="aaa"></uc:radio>
			<uc:checkbox name="gg" label="checkbox" required="true"></uc:checkbox>
			<hr>
			<uc:radiolist list="${users }" listKey="id" listValue="name" name="hh" tableLayout="false"></uc:radiolist>
			<hr>
			<uc:radiolist list="${users }" listKey="id" listValue="name" name="hh2" tableLayout="true" cols="1"></uc:radiolist>
			<div id="uploadDiv"></div>
			<hr>
			<uc:checkboxlist list="${users }" listKey="id" listValue="name" name="ii" tableLayout="false" cols="1"></uc:checkboxlist>
			<hr>
			<uc:select id="s11" cssStyle="width:200px" list="${users }" name="jj" listKey="id" listValue="name" headerKey="" headerValue="请选择"></uc:select><hr>
			<uc:select cssStyle="width:200px" editEnable="true" list="${users }" name="jj" listKey="id" listValue="name" headerKey="" headerValue="请选择"></uc:select><hr>
			<uc:select cssStyle="width:200px" list="${users }" name="jj" listKey="id" listValue="name" headerKey="" headerValue="多选框" multiple="true"></uc:select><hr>
			<input id="entryModeSwitch" type="checkbox" class="{labelOn: '按班级录分', labelOff: '按考场录分', easing: 'easeOutBounce', duration: 500,change:function(){}}" /><hr>
			<input placeholder="日期选择" type="text" id="datePicker" name="kk" onfocus="WdatePicker()">
			<div id="progressDiv"></div>
			<div id="ratyDiv"></div>
	  		</uc:form>
	  		<!-- 右键菜单 -->
			<ul id="menu4Desktop" class="jeegoocontext cm_default select-disable" unselectable="on" onselectstart="return false;">
				<li class="icon" onclick="alert(1)">
					<span class="icon disk"></span>菜单1
				</li>
				<li class="icon" onclick="alert(2)">
					<span class="icon disk"></span>菜单2
				</li>
				<li class="separator"></li>
				<li class="icon" onclick="alert(3)">
					<span class="icon file"></span>菜单3
				</li>
			</ul>
	  	<hr>
	  	<input type="text" value="" id="copyFrom">
	  	<input type="button" value="复制" id="copyBtn">
	  	<input type="button" value="validate" onclick="alert($('#formId').validationEngine('validate'))">
	  	<input type="button" value="弹出层测试0" onclick="popupTest0()">
	  	<input type="button" value="打印测试" onclick="printDemo()">
	  	<input type="button" value="tabs页" onclick="popup('${path}/dm/tabs.jsp')">
	  	<input type="button" value="layout" onclick="popup('${path}/dm/layout.jsp')">
	  	<input type="button" value="box" onclick="popup('${path}/dm/box.jsp')">
	  	<input type="button" value="lavalamp.jsp" onclick="popup('${path}/dm/lavalamp.jsp')">
	  	<input type="button" value="mask测试" onclick="maskDemo()">
	  	<input type="button" value="进度条" onclick="progressDemo()">
	  	i18n:<uc:i18n code="upload_js_fileSizeNotAllow"><uc:i18nParam value="132"/></uc:i18n><uc:i18n code="upload_js_required"></uc:i18n><hr>
	  	<input type="button" value="treeTable.jsp" onclick="popup('${path}/dm/treeTable.jsp')">
	  	<input type="button" value="ueditor.jsp" onclick="popup('${path}/dm/ueditor.jsp')">
	  	<input type="button" value="ztree.jsp" onclick="popup('${path}/dm/ztree.jsp')">
	  	<input type="button" value="tipbox.jsp" onclick="popup('${path}/dm/tipbox.jsp')">
	  	<input type="button" value="accordion.jsp" onclick="popup('${path}/dm/accordion.jsp')">
	  	<input type="button" value="sortable.jsp" onclick="popup('${path}/dm/sortable.jsp')">
	  	<input type="button" value="autocomplate.jsp" onclick="popup('${path}/dm/autocomplate.jsp')">
	  	<input type="button" value="jscrollpane.jsp" onclick="popup('${path}/dm/jscrollpane.jsp')">
	  	<input type="button" value="jqGrid.jsp" onclick="popup('${path}/dm/jqGrid.jsp')">
	  	<input type="button" value="附件配置管理" onclick="gotoPage('${path}/sys/attachmentConfig/toConfigIndex.do')">
	  	<input type="button" value="用户列表" onclick="goUserList()">
	  	<input type="button" value="添加用户" onclick="addUser()">
	  	
	  	<%=System.currentTimeMillis()-t1%>
	</body>
</html>
