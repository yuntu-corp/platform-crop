<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<script>
	$(document).ready(function(){
		$("#boxBodyDiv").find(".box1").box1();
		$("#modeSwitch").iButton();
	})
</script>
<div id="boxBodyDiv" class="container">
	<div class="box1 fl" boxTitle="基本信息">
		<table class="info_layout">
			<tr>
				<td class="tit w120">
					姓名
				</td>
				<td>
					张三
				</td>
				<td class="tit w120">
					学号
				</td>
				<td>
					2009010203
				</td>
			</tr>
			<tr>
				<td class="tit">
					学籍号
				</td>
				<td>
					2010101010
				</td>
				<td class="tit">
					性别
				</td>
				<td>
					男
				</td>
			</tr>
		</table>
	</div>
	<div class="box1 fl" boxTitle="学籍信息">
		<table class="info_layout">
			<tr>
				<td class="tit w120">
					姓名
				</td>
				<td>
					张三
				</td>
				<td class="tit w120">
					学号
				</td>
				<td>
					2009010203
				</td>
			</tr>
			<tr>
				<td class="tit">
					学籍号
				</td>
				<td>
					2010101010
				</td>
				<td class="tit">
					性别
				</td>
				<td>
					男
				</td>
			</tr>
		</table>
	</div>
	<div class="box1 fl mt5" boxTitle="学籍信息">
		<div class="box1-header">
			<h2>历次考试总分趋势</h2>	
		</div>
		<div class="box1-content tc" style="height: 200px;">
			<input id="modeSwitch" type="checkbox" class="{labelOn: '按班级录分', labelOff: '按考场录分', easing: 'easeOutBounce', duration: 500,change:function(){}}" />
		</div>
	</div>
</div>