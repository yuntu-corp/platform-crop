<!DOCTYPE html>
<%@page import="com.unitever.platform.util.UUID"%>
<%@page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
	<head>  
    <title>平台</title>
    <%@ include file="/platform/common/jsp/baseJs.jsp"%>
    <%@ include file="/demo/common/jsp/syntaxhighlighterJs.jsp"%>
<script src="${path}/demo/common/js/jquery-syntax/public/jquery.syntax.js" type="text/javascript"></script>
		<script src="${path}/demo/common/js/jquery-syntax/public/jquery.syntax.cache.js" type="text/javascript"></script>
    <link rel="stylesheet" href="${path}/demo/common/css/style.css"/>
	<script>
		Namespace.register('com.ue.platform.demo.index');
		com.ue.platform.demo.index={
			loadContent:function(obj){
				var that = this;
				$("#rightContent").empty();
				$(".current").removeClass("current");
				$(obj).addClass("current");
				if($(obj).attr("url")){
					$("#containers").mask('加载内容……');
					$("#rightContent").load($(obj).attr("url"),function(){
						$("#containers").unmask();
						$("#rightContent pre").each(function(){
							var html = that.replaceEscape($(this).html());
							$(this).html(html);
						});
						$.syntax({theme: 'paper', blockLayout: 'fixed'});
					});
				}
			},
			init:function(){
				var that = this;
				$("#containers .navMain a").bind("click",function(){
					that.loadContent(this);
				});
			},
			escape_re:/[<>]/g,
			escape_re_val:{"<" : "&lt;",">" : "&gt;"},
			replaceEscape : function(str) {
				var that = this;
				if (str.escaped)
					return str.escaped;
				str = str.replace(that.escape_re, function(c) {
					return that.escape_re_val[c];
				});
				return str;
			}
		}
	
		$(document).ready( function(){ 
			$('#containers').layout({
				 	west__size:				220
				,	spacing_open:			7
				,	spacing_closed: 		7
				,	resizable:				true  
			});
			
			$("#accordion").accordion();
			
			com.ue.platform.demo.index.init();
		});						
	</script>
	</head>
	<body>
	<div id="containers"  class="container">
		<div class="ui-layout-center centerStyle" id="rightContent" style="overflow:auto;">
		
		</div>
		<div class="ui-layout-west westStyle">
			<div id="accordion">
				<h3>基础组件手册</h3>
				<div>
					<ul class="navMain">
						<li>
							<a href="javascript:;" url="${path}/demo/component/baseform/baseform-demo.jsp">
								<span>基本表单</span>
							</a>
						</li>
						<li>
							<a href="javascript:;" url="${path}/demo/component/validate/validate-demo.jsp">
								<span>表单验证</span>
							</a>
						</li>
						<li>
							<a href="javascript:;" url="${path}/demo/component/ueditor/ueditor-demo.jsp">
								<span>富文本</span>
							</a>
						</li>
						<li>
							<a href="javascript:;" url="${path}/demo/component/ztree/ztree-demo.jsp">
								<span>树</span>
							</a>
						</li>
						<li>
							<a href="javascript:;" url="${path}/demo/component/swfupload/swfupload-demo.jsp">
								<span>附件</span>
							</a>
						</li>
						<li>
							<a href="javascript:;"  url="${path}/demo/component/layout/layout-demo.jsp" >
								<span>布局</span>
							</a>
						</li>
						<li>
							<a href="javascript:;"  url="${path}/demo/component/page/page-demo.jsp" >
								<span>分页</span>
							</a>
						</li>
						<li>
							<a href="javascript:;"  url="${path}/demo/component/dictionary/dictionary-demo.jsp" >
								<span>数据字典</span>
							</a>
						</li>
						<li>
							<a href="javascript:;">
								<span>抽屉</span>
							</a>
						</li>
					</ul>
				</div>
				<h3>其他</h3>
				<div>
					<p>骗子！什么都没有！</p>
				</div>
			</div>
		</div>
	</body>
</html>
