<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<a href="${path}/sys/dictionary/index" onclick="" target="blank">字典管理</a><br><br>
<div class="box1 fl" boxTitle="字典下拉">
	<font color="green"><b>展示效果：</b></font>
	<div style="margin-top: 10px;margin-left: 10px;margin-right: 10px;">
		<uc:dictSelect name="guojia" dictCode="guojia" checkValue="china"></uc:dictSelect>
	</div>
	
	<font class="green fb">html代码：</font>
	<pre class="syntax brush-html">
		&lt;uc:dictSelect name="guojia" dictCode="guojia" checkValue="china">&lt;/uc:dictSelect>
	</pre>
</div>

<div class="box1 fl" boxTitle="字典复选">
	<font color="green"><b>展示效果：</b></font>
	<div style="margin-top: 10px;margin-left: 10px;margin-right: 10px;">
		<uc:dictCheck dictCode="guojia" name="guojia" checkValue="yanguizi,ribenguizi" cols="2"></uc:dictCheck>
	</div>
	
	<font class="green fb">html代码：</font>
	<pre class="syntax brush-html">
		&lt;uc:dictCheck dictCode="guojia" name="guojia" checkValue="yanguizi,ribenguizi" cols="2">&lt;/uc:dictCheck>
	</pre>
</div>

<div class="box1 fl" boxTitle="字典树">
	<font color="green"><b>展示效果：</b></font>
	<div style="margin-top: 10px;margin-left: 10px;margin-right: 10px;">
		下拉复选树：<uc:dictTree id="testregion1" dictCode="testregion" dropdown="true" checkEnable="true" expandLevel="1"></uc:dictTree>
		<br><br>
		下拉单选树：<uc:dictTree id="testregion2" dictCode="testregion" dropdown="true" checkEnable="true" checkStyle="radio" expandLevel="1"></uc:dictTree>
		<br><br>
		菜单树<uc:dictTree id="testregion3" dictCode="testregion" expandLevel="1"></uc:dictTree>
	</div>
	
	<font class="green fb">html代码：</font>
	<pre class="syntax brush-html">
		&lt;uc:dictTree id="testregion1" dictCode="testregion" dropdown="true" checkEnable="true" expandLevel="1">&lt;/uc:dictTree>
		&lt;uc:dictTree id="testregion2" dictCode="testregion" dropdown="true" checkEnable="true" checkStyle="radio" expandLevel="1">&lt;/uc:dictTree>
		&lt;uc:dictTree id="testregion3" dictCode="testregion" expandLevel="1">&lt;/uc:dictTree>
	</pre>
</div>

<div class="box1 fl" boxTitle="字典值显示">
	<font color="green"><b>展示效果：</b></font>
	<div style="margin-top: 10px;margin-left: 10px;margin-right: 10px;">
		字典显示（单值）：<uc:dictView dictCode="guojia" valueCode="yanguizi"></uc:dictView>
		<br><br>
		字典显示（多值）：<uc:dictView dictCode="guojia" valueCode="yanguizi,ribenguizi"></uc:dictView>
		<br><br>
		字典显示（单值全路径）：<uc:dictView dictCode="testregion" valueCode="nangangqu" isFullPath="true"></uc:dictView>
		<br><br>
		字典显示（多值全路径）：<uc:dictView dictCode="testregion" valueCode="nangangqu,daoliqu" isFullPath="true"></uc:dictView>
	</div>
	
	<font class="green fb">html代码：</font>
	<pre class="syntax brush-html">
		&lt;uc:dictView dictCode="guojia" valueCode="yanguizi">&lt;/uc:dictView>
		&lt;uc:dictView dictCode="guojia" valueCode="yanguizi,ribenguizi">&lt;/uc:dictView>
		&lt;uc:dictView dictCode="testregion" valueCode="nangangqu" isFullPath="true">&lt;/uc:dictView>
		&lt;uc:dictView dictCode="testregion" valueCode="nangangqu,daoliqu" isFullPath="true">&lt;/uc:dictView>
	</pre>
</div>


<script>
	$(document).ready(function(){
		$(".box1").box1();
	});
</script>