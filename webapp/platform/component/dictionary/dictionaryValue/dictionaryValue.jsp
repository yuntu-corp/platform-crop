<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp"%>
<script>
Namespace.register('com.ue.platform.module.dictvalue');
com.ue.platform.module.dictvalue={
	pageId:"${pageId}",
	search:function(){
		this.refreshTable();
	},
	refresh:function(){
// 		this.search();
		com.ue.platform.module.dictvalueIndex.toGeneralDictionaryValue("${dictId}");
	},
	toAdd:function(){
		var dictId=$("#${pageId}dictId").val();
		$.dialog.load("${path}/sys/dictionaryValue/input?dictId=${dictId}",{
					title : '字典值新增',
					width: 600,
					height: 300,
					fixed: true,
					id: '${pageId}',
					lock: true,
					ok: function(){
						com.ue.platform.module.dictvalue.input.doAdd();
						return false;
				    },
				    cancel:function(){}
		});
	},
	toEdit:function(id){
		$.dialog.load("${path}/sys/dictionaryValue/input?option=edit&id="+id,{
					title : '字典值修改',
					width: 600,
					height: 300,
					fixed: true,
					id: '${pageId}',
					lock: true,
					ok: function(){
						com.ue.platform.module.dictvalue.input.doEdit();
						return false;
				    },
				    cancel:function(){}
		});
	},
	doDel:function(id){
		var space=this;
		confirm("确定要删除吗？可能会影响到其他业务功能！",function(){
			$.ajax({
			 	type: "POST",
			  	url: '${path}/sys/dictionaryValue/delete?sels='+id,
			  	processData:true,
			  	success: function(data){
// 			   		space.refreshTable();
			  		space.refresh();
			  	}
		 	});
		});
	},
	doDelBatch:function(){
		var space=this;
		var selectIds=$("#${pageId}list").jqGrid("getGridParam","selarrrow");
		if(selectIds.length < 1){
			alert("请选择要删除的项！");
			return;
		}
		
		confirm("确定要删除吗？可能会影响到其他业务功能！",function(){
			var formData="";
			$.each($(selectIds),function(i){formData+='sels='+this+'&';});
			$.ajax({
			 	type: "POST",
			  	url: '${path}/sys/dictionaryValue/delete',
			  	processData:true,
			  	data:formData,
			  	success: function(data){
			   		space.refreshTable();
			  	}
			 });
		});
		
	},
	forbidden:function(id){
		var space=this;
		confirm("确定要禁用吗？",function(){
			$.ajax({
			 	type: "POST",
			  	url: '${path}/sys/dictionaryValue/forbidden?id='+id,
			  	processData:true,
			  	success: function(data){
			   		space.refreshTable();
			  	}
		 	});
		});
	},
	enabled:function(id){
		var space=this;
		confirm("确定要开启吗？",function(){
			$.ajax({
			 	type: "POST",
			  	url: '${path}/sys/dictionaryValue/enabled?id='+id,
			  	processData:true,
			  	success: function(data){
			   		space.refreshTable();
			  	}
		 	});
		});
	},
	bindTable:function(){
		$("#${pageId}list").bindTable('${path}/sys/dictionaryValue/listData?dictId=${dictId}');
		var opHtml=[];
		opHtml.push("<div class='fl ml10'>");
		opHtml.push("<a class='toolbarBtn ml10' href='javascript:void(0);' onclick='com.ue.platform.module.dictvalue.toAdd()'><span class='fm f8 mr5'>+</span>添加</a>");
		opHtml.push("<span class='spaceline ml5 mr5'></span>");
		opHtml.push("<a class='toolbarBtn' href='javascript:void(0);' onclick='com.ue.platform.module.dictvalue.doDelBatch()'><span class='fm f8 mr5'>p</span>批量删除</a>");
		opHtml.push("<span class='spaceline ml5 mr5'></span>");
		opHtml.push("<a class='toolbarBtn' href='javascript:void(0);' onclick='com.ue.platform.module.dictvalue.toSort()'><span class='fm f8 mr5'>p</span>排序</a>");
		opHtml.push("</div>");
		$("#t_${pageId}list").append(opHtml.join(''));
	},
	refreshTable:function(){
		com.ue.datagrid.refreshTable("${pageId}list");
	},
	tableCellRedrawWithOP:function(cellvalue,options,rowObject){
		var opHtml=[];
		opHtml.push("<div class='tc'>");
		opHtml.push("<a href='javascript:void(0);' class='button medium'  onclick='com.ue.platform.module.dictvalue.toEdit(\""+rowObject.id+"\");'><span class='fm f8 mr5'>p</span>修改</a>");
		opHtml.push("<a href='javascript:void(0);' class='button medium'  onclick='com.ue.platform.module.dictvalue.doDel(\""+rowObject.id+"\");'><span class='fm f8 mr5'>-</span>删除</a>");
		if(rowObject.enableFlag=='1'){
			opHtml.push("<a href='javascript:void(0);' class='button medium'  onclick='com.ue.platform.module.dictvalue.forbidden(\""+rowObject.id+"\");'><span class='fm f8'>x</span>禁用</a>");
		}
		if(rowObject.enableFlag=='0'){
			opHtml.push("<a href='javascript:void(0);' class='button medium'  onclick='com.ue.platform.module.dictvalue.enabled(\""+rowObject.id+"\");'><span class='fm f8'>y</span>启用</a>");
		}
		opHtml.push("</div>");
		return opHtml.join("");
	},
	toSort:function(){
		var space=this;
		$.getJSON("${path}/sys/dictionaryValue/getDataJsonWithGeneral?dictId=${dictId}", function(jsondata){
			com.ue.sort.dataSort(jsondata,function(ids){
				var formData="";
				$.each(ids,function(){formData+="sels="+this+"&"});
				 $.ajax({
				 	type: "POST",
				  	url: "${path}/sys/dictionaryValue/sortWithGeneral?dictId=${dictId}",
				  	processData:true,
				  	data:formData,
				  	success: function(data){
				   		space.refreshTable();
				   		$.successTips();
				  	}
				 });
			},"字典值排序");
		});
	}
};

$(document).ready(function() {
	$("#${pageId}list").closest("div.ui-layout-pane").removeClass("inLayout-center");
});


</script>
			<a class='toolbarBtn ml10' href='javascript:void(0);' onclick='com.ue.platform.module.dictvalue.toAdd()'><span class='fm f8 mr5'>+</span>添加</a>
			<uc:form id="${pageId}theForm" method="post">
				<!-- Begin search -->
	                <table id="${pageId}list" multiselect="true" pager="pager" toolbar="true" class="jqgrid">
						<thead>
							<tr>
								<th name="dictionary.name">
									所属字典条目
								</th>
								<th name="value">
									字典值显示
								</th>
								<th name="valueI18n">
									字典值英文
								</th>
								<th name="code">
									字典值编码
								</th>
								<th name="enableFlagView" index="enableFlag">
									使用状态
								</th>
								<th name="op" thWidth="450" formatter="com.ue.platform.module.dictvalue.tableCellRedrawWithOP">
									操作
								</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${dictionaryValues}" var="model">
								<tr>
									<td>${model.dictionary.name}</td>
									<td>${model.value}</td>
									<td>${model.valueI18n}</td>
									<td>${model.code}</td>
									<td>${model.enableFlagView}</td>
									<td>
										<a href='javascript:void(0);' class='button medium'  onclick='com.ue.platform.module.dictvalue.toEdit("${model.id}");'>修改</a>
										<a href='javascript:void(0);' class='button medium'  onclick='com.ue.platform.module.dictvalue.doDel("${model.id}");'>删除</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
			</uc:form>
	</body>
</html>
