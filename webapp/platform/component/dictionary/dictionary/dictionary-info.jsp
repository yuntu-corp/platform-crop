<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>

<script>
Namespace.register('com.ue.platform.module.dict.input');
com.ue.platform.module.dict.info={
	pageId:"${pageId}",
	toEdit:function(){
		com.ue.platform.module.dict.toEdit('${model.id}');
	},
	doDel:function(){
		com.ue.platform.module.dict.doDel('${model.kind}','${model.id}');
	},
};

$(document).ready(function() {
})
</script>
		<div class="formLayout01">
			<table class="detail_layout w mt10">
				<tr>
					<td class="w3">字典类型</td>
					<td class="detail">${DictKindMap[model.kind]}</td>
				</tr>
				<tr>
					<td>字典条目名称</td>
					<td class="detail">${model.name}</td>
				</tr>	
				<tr>
					<td>字典条目编码</td>
					<td class="detail">${model.code}</td>
				</tr>
				<tr>
					<td>是否允许编辑字典值</td>
					<td class="detail">${canEditFlagMap[model.editableFlag]}</td>
				</tr>
			</table>
		</div>
		<div class="formButton">
			<a href="javascript:void(0);" class="button btnRed" onclick="com.ue.platform.module.dict.info.doDel();">删除</a>
			<a href="javascript:void(0);" class="button" onclick="com.ue.platform.module.dict.info.toEdit();">编辑</a>
		</div>
