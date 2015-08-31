<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp"%>
<script>
Namespace.register('com.ue.platform.module.dictvaluetree');
com.ue.platform.module.dictvaluetree={
	pageId:"${pageId}",
	refresh:function(id){
		$.getJSON("${path}/sys/dictionaryValue/listTreeData?dictId=${dictId}", function(jsondata){
			$.hz.ztree.init("${pageId}dictTree",{
				nodes:jsondata,
				check: {
					enable: true,
					chkStyle: "checkbox"
				}
			});
 			$.hz.ztree.expandNodeByLevel("${pageId}dictTree",1);
			if(id){
				$.hz.ztree.expandNodeWithId("${pageId}dictTree",id);
			}
		});
	},
	toAdd:function(){
		$.dialog.load("${path}/sys/dictionaryValue/inputTree?dictId=${dictId}",{
			title : '树字典值新增',
			width: 600,
			height: 300,
			fixed: true,
			id: '${pageId}',
			lock: true,
			ok: function(){
				com.ue.platform.module.dictvaluetree.input.doAdd();
				return false;
		    },
			cancel:function(){}
		});
	},
	toEdit:function(){
		var checkedIds=$.hz.ztree.getCheckedNodeIds("${pageId}dictTree");
		if (!checkedIds||checkedIds==""||checkedIds.split(",").length!=1) {
			alert("请选择一条记录！");
			return;
		};
		$.dialog.load("${path}/sys/dictionaryValue/inputTree?option=edit&id="+checkedIds,{
			title : '树字典值修改',
			width: 600,
			height: 300,
			fixed: true,
			id: '${pageId}',
			lock: true,
			ok: function(){
				com.ue.platform.module.dictvaluetree.input.doEdit();
				return false;
		    },
			cancel:function(){}
		});
	},
	doDel:function(){
		var checkedIds=$.hz.ztree.getCheckedNodeIds("${pageId}dictTree");
		if (!checkedIds||checkedIds==""||checkedIds.split(",").length<1) {
			alert("请选择要删除的项！");
			return;
		};
		confirm("确定要删除吗？可能会影响到其他业务功能！",function(){
			$("#deleteIds").val(checkedIds);
			this.goHref('${path}/sys/dictionaryValue/deleteTree');
		});
	},
	doDelBatch:function(){
		var space=this;
		var checkedIds=$.hz.ztree.getCheckedNodeIds("${pageId}dictTree");
		if (!checkedIds||checkedIds==""||checkedIds.split(",").length<1) {
			alert("请选择要删除的项！");
			return;
		};
		confirm("确定要删除吗？可能会影响到其他业务功能！",function(){
			var formData="";
			$.each($(checkedIds.split(",")),function(i){formData+='sels='+this+'&';});
			$.ajax({
			 	type: "POST",
			  	url: '${path}/sys/dictionaryValue/deleteTree',
			  	processData:true,
			  	data:formData,
			  	success: function(data){
			   		space.refresh();
			  	}
			 });
		});
	},
	toSort:function(){
		var space=this;
		var checkedIds=$.hz.ztree.getCheckedNodeIds("${pageId}dictTree");
		if (!checkedIds||checkedIds==""||checkedIds.split(",").length!=1) {
			alert("请选择一条记录！同层排序");
			return;
		};
		$.getJSON("${path}/sys/dictionaryValue/getDataJsonWithTree?dictId=${dictId}&id="+checkedIds, function(jsondata){
			com.ue.sort.dataSort(jsondata,function(ids){
				var formData="";
				$.each(ids,function(){formData+="sels="+this+"&"});
				 $.ajax({
				 	type: "POST",
				  	url: "${path}/sys/dictionaryValue/sortWithGeneral?dictId=${dictId}",
				  	processData:true,
				  	data:formData,
				  	success: function(data){
				   		space.refresh(checkedIds);
				   		$.successTips();
				  	}
				 });
			},"字典值排序");
		});
	}
};
	
$(document).ready(function() {
// 	com.ue.box.box1();
	com.ue.platform.module.dictvaluetree.refresh();
});
</script>
			<uc:form action="dictValue!list" id="${pageId}theForm" method="post">
				<div class="m5">
					<a class='button small' href='javascript:void(0);' onclick='com.ue.platform.module.dictvaluetree.toAdd();'><span class='fm f8 mr5'>+</span>添加</a>
					<a class='button small' href='javascript:void(0);' onclick='com.ue.platform.module.dictvaluetree.doDelBatch();'><span class="fm f8 mr5">-</span>删除</a>
					<a class='button small' href='javascript:void(0);' onclick='com.ue.platform.module.dictvaluetree.toEdit();'><span class="fm f8 mr5">p</span>修改</a>
					<a class='button small' href='javascript:void(0);' onclick='com.ue.platform.module.dictvaluetree.toSort();'><span class="fm f8 mr5">p</span>排序</a>
				</div>
	            <div class="box1 fl m5" boxTitle="树型字典值">
		            <ul id="${pageId}dictTree">
				
					</ul>
	            </div>
			        			                
			    <!-- End list -->
			</uc:form>
