<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<style>
	#layout-1,#layout-2,#layout-3,#layout-4,#layout-5 {
	    background: none repeat scroll 0 0 #F7F7F7;
	    min-height: 300px;
	    min-width: 500px;
	    margin:0px;
	}
</style>
<div class="box1 fl" boxTitle="左右结构">
	<font color="green"><b>展示效果：</b></font>
	<div id="layout-1">
		<div class="ui-layout-center centerStyle"><div style="font-size:30px;color: #CCCCCC;text-align: center;">右</div></div>
		<div class="ui-layout-west westStyle"><div style="font-size:30px;color: #CCCCCC;text-align: center;">左</div></div>	
	</div>
	
	<font class="green fb">html代码部分：</font>
	<pre class="syntax brush-html">
		<div id="layout-1">
			<div class="ui-layout-center centerStyle"></div>
			<div class="ui-layout-west westStyle"></div>	
		</div>
	</pre>
	
	<font class="green fb">js代码部分：</font>
	<pre class="syntax brush-javascript">
		$(document).ready( function(){ 
			$('#layout-1').layout({
				west__size:180
			});
		});
	</pre>
</div>
<div class="clear"></div>

<div class="box1 fl" boxTitle="左中右结构">
	<font class="green fb">展示效果：</font>
	<div id="layout-2">
		<div class="ui-layout-center centerStyle"><div style="font-size:30px;color: #CCCCCC;text-align: center;">中</div></div>
		<div class="ui-layout-east eastStyle"><div style="font-size:30px;color: #CCCCCC;text-align: center;">右</div></div>		
		<div class="ui-layout-west westStyle"><div style="font-size:30px;color: #CCCCCC;text-align: center;">左</div></div>	
	</div>
	
	<font class="green fb">html代码部分：</font>
	<pre class="syntax brush-html">
		<div id="layout-2">
			<div class="ui-layout-center centerStyle"></div>
			<div class="ui-layout-east eastStyle"></div>		
			<div class="ui-layout-west westStyle"></div>	
		</div>
	</pre>
	
	<font class="green fb">js代码部分：</font>
	<pre class="syntax brush-javascript">
		$(document).ready( function(){ 
			$('#layout-2').layout({
				west__size:180,	
				east__size:180
			});
		});
	</pre>
</div>

<div class="box1 fl" boxTitle="上下结构">
	<font class="green fb">展示效果：</font>
	<div id="layout-3">
		<div class="ui-layout-center centerStyle"><div style="font-size:30px;color: #CCCCCC;text-align: center;">下</div></div>
		<div class="ui-layout-north northStyle"><div style="font-size:30px;color: #CCCCCC;text-align: center;">上</div></div>
	</div>
	
	<font class="green fb">html代码部分：</font>
	<pre class="syntax brush-html">
		<div id="layout-3">
			<div class="ui-layout-center centerStyle"></div>
			<div class="ui-layout-north northStyle"></div>
		</div>
	</pre>
	
	<font class="green fb">js代码部分：</font>
	<pre class="syntax brush-javascript">
		$(document).ready( function(){ 
			$('#layout-3').layout({
				north__size:50
			});
		});
	</pre>
</div>
<div class="clear"></div>
<div class="box1 fl" boxTitle="上中下结构">
	<font class="green fb">展示效果：</font>
	<div id="layout-4">
		<div class="ui-layout-center centerStyle"><div style="font-size:30px;color: #CCCCCC;text-align: center;">中</div></div>
		<div class="ui-layout-north northStyle"><div style="font-size:30px;color: #CCCCCC;text-align: center;">上</div></div>
		<div class="ui-layout-south southStyle"><div style="font-size:30px;color: #CCCCCC;text-align: center;">下</div></div>
	</div>
	
	<font class="green fb">html代码部分：</font>
	<pre class="syntax brush-html">
		<div id="layout-4">
			<div class="ui-layout-center centerStyle"></div>
			<div class="ui-layout-north northStyle"></div>
			<div class="ui-layout-south southStyle"></div>
		</div>
	</pre>
	
	<font class="green fb">js代码部分：</font>
	<pre class="syntax brush-javascript">
		$(document).ready( function(){ 
			$('#layout-4').layout({
				north__size:50,	
				south__size:50
			});
		});
	</pre>
</div>
<div class="clear"></div>
<div class="box1 fl" boxTitle="上左中右下结构">
	<font class="green fb">展示效果：</font>
	<div id="layout-5">
		<div class="ui-layout-center centerStyle"><div style="font-size:30px;color: #CCCCCC;text-align: center;">中</div></div>
		<div class="ui-layout-north northStyle"><div style="font-size:30px;color: #CCCCCC;text-align: center;">上</div></div>
		<div class="ui-layout-south southStyle"><div style="font-size:30px;color: #CCCCCC;text-align: center;">下</div></div>
		<div class="ui-layout-east eastStyle"><div style="font-size:30px;color: #CCCCCC;text-align: center;">右</div></div>		
		<div class="ui-layout-west westStyle"><div style="font-size:30px;color: #CCCCCC;text-align: center;">左</div></div>	
	</div>
	
	<font class="green fb">html代码部分：</font>
	<pre class="syntax brush-html" id="haha123">
		<div id="layout-5">
			<div class="ui-layout-center centerStyle"></div>
			<div class="ui-layout-north northStyle"></div>
			<div class="ui-layout-south southStyle"></div>
			<div class="ui-layout-east eastStyle"></div>		
			<div class="ui-layout-west westStyle"></div>	
		</div>
	</pre>
	
	<font class="green fb">js代码部分：</font>
	<pre class="syntax brush-javascript">
		$(document).ready( function(){ 
			$('#layout-5').layout({
				north__size:50,	
				south__size:50,	
				west__size:180,	
				east__size:180
			});
		});
	</pre>
</div>
<script>
	$(document).ready(function(){
		$(".box1").box1();
			
		$('#layout-1').layout({
			west__size:180
		});
		$('#layout-2').layout({
			west__size:180,
			east__size:180
		});
		$('#layout-3').layout({
			north__size:50
		});
		$('#layout-4').layout({
		 	north__size:50,
		 	south__size:50
		});
		$('#layout-5').layout({
			north__size:50,
			south__size:50,
			west__size:180,
			east__size:180
		});
	})
</script>
