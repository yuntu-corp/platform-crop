<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<script>
	$(document).ready(function(){
		$("#tableId").treeTable();
	})
</script>
<div id="treeTable" class="container">
	<table id="tableId" width="90%" style="margin-left: 30px">
		<thead>
			<tr>
				<th width="30%">题号</th>
				<th width="30%">题目分值</th>
				<th>得分率</th>
			</tr>
		</thead>
		<tbody>
			<tr id="ex0-node-1">
				<td>一</td>
				<td>4</td>
				<td>56.8%</td>
			</tr>
			<tr id="ex0-node-1-1" class="child-of-ex0-node-1">
				<td>1</td>
				<td>5</td>
				<td>53.8%</td>
			</tr>
			<tr id="ex0-node-1-1-1" class="child-of-ex0-node-1-1">
				<td>(1)</td>
				<td>9</td>
				<td>53.8%</td>
			</tr>
			<tr id="ex0-node-1-1-2" class="child-of-ex0-node-1-1">
				<td>(2)</td>
				<td>9</td>
				<td>53.8%</td>
			</tr>
			<tr id="ex0-node-1-1-3" class="child-of-ex0-node-1-1">
				<td>(3)</td>
				<td>9</td>
				<td>53.8%</td>
			</tr>
			<tr id="ex0-node-1-1-4" class="child-of-ex0-node-1-1">
				<td>(4)</td>
				<td>9</td>
				<td>53.8%</td>
			</tr>
			<tr id="ex0-node-1-2" class="child-of-ex0-node-1">
				<td>2</td>
				<td>8</td>
				<td>26.8%</td>
			</tr>
			<tr id="ex0-node-1-3" class="child-of-ex0-node-1">
				<td>3</td>
				<td>3</td>
				<td>26.8%</td>
			</tr>
			<tr id="ex0-node-1-4" class="child-of-ex0-node-1">
				<td>4</td>
				<td>8</td>
				<td>26.8%</td>
			</tr>
			<tr id="ex0-node-2">
				<td>二</td>
				<td>4</td>
				<td>56.8%</td>
			</tr>
		</tbody>
	</table>
</div>