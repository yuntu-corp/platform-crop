<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
	<head>
    <title>平台</title>
    <%@ include file="/platform/common/jsp/baseJs.jsp"%>
<script>
Namespace.register('com.ue.platform.module.dictIndex');
com.ue.platform.module.dictIndex={
	pageId:"${pageId}",
	loadContent:function(url){
		$(".container").mask('正在加载数据……');
		$("#${pageId}wrap").load(url,function(){
			$("body").unmask();
		});
	},
	loadDictWithDict:function(){
		this.loadContent("${path}/sys/dictionary/list");
	},
	loadDictWithDictValue:function(){
		this.loadContent("${path}/sys/dictionaryValue/index");
	}
};

$(document).ready(function() {
	com.ue.platform.module.dictIndex.loadDictWithDict();
	$('.container').layout({
		north__size:50
	});
})
</script>
<style type="text/css">
.container {
	position: absolute;
	top: 0px;
	left: 0px;
	bottom: 0px;
	right: 0px;
	overflow: hidden;
}
body {
	position: absolute;
	top: 0px;
	left: 0px;
	bottom: 0px;
	right: 0px;
	overflow: hidden;
}
</style>
	</head>
	<body>
		<div class="container">
			<div class="clearfix box_header ui-layout-north northStyle">
			    <h2 class="box_title tc">数据字典</h2>
			    <div class="box_nav">
					<ul>
						<li>
							<a href="javascript:void(0);" original-title="字典条目" onclick="com.ue.platform.module.dictIndex.loadDictWithDict();">字典条目</a>
						</li>
						<li>
							<a href="javascript:void(0);" original-title="字典值" onclick="com.ue.platform.module.dictIndex.loadDictWithDictValue();">字典值</a>
						</li>
					</ul>
			    </div>
			    <div class="clear">
			    </div>
			</div>
			<div id="${pageId}wrap" class="ui-layout-center centerStyle">
				
			</div>
		</div>
	</body>
</html>
