<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>

<div id="treeDiv" class="container">
	<div id="tree" class="ztree" style="width: 250px;overflow: auto"></div>
</div>
<script>
	$(document).ready(function(){
		var setting = {
			view: {
				dblClickExpand: false,
				showLine: true,
				selectedMulti: false
			},
			data: {
				simpleData: {
					enable:true,
					idKey: "id",
					pIdKey: "pId",
					rootPId: ""
				}
			}
		}
		var zNodes = [
			{id:1, pId:0, name:"[core] 基本功能 演示", open:true},
			{id:101, pId:1, name:"最简单的树 --  标准 JSON 数据"},
			{id:102, pId:1, name:"最简单的树 --  简单 JSON 数据"},
			{id:2, pId:0, name:"[excheck] 复/单选框功能 演示", open:false},
			{id:201, pId:2, name:"Checkbox 勾选操作"},
			{id:206, pId:2, name:"Checkbox nocheck 演示"},
			{id:207, pId:2, name:"Checkbox chkDisabled 演示"},
			{id:208, pId:2, name:"Checkbox halfCheck 演示"},
			{id:202, pId:2, name:"Checkbox 勾选统计"}
		]
		$.fn.zTree.init($("#tree"), setting, zNodes);
	});
</script>