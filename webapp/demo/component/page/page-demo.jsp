<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp"%>
<link rel="stylesheet" href="${path}/demo/common/css/style.css"/>
<div class="box1 fl" boxTitle="分页">
	<div class="box1 fl" boxTitle="1.普通分页">
		<font color="green"><b>展示效果：</b></font>
		<div style="margin-top: 10px; margin-left: 10px; margin-right: 10px;">
			<div class="bdmain">
				<iframe id="iframeDemo1" width="1000" height="500" src="${path}/dm/page/demo1" frameborder="no" border="0" marginwidth="0" marginheight="0″ scrolling="no" allowtransparency="yes"></iframe>
			</div>
		</div>

			<!-- <a href="javascript:;" onclick="setReset();">重置</a> -->
		<font class="green fb">html代码部分：</font>
		<pre class="syntax brush-html">
			<form id="theForm_1_1" action="${path}/dm/page/demo1">
				……	……
				<div id="pageBarDemo" ></div>
			</form>
		</pre>

		<font class="green fb">js代码部分：</font>
		<pre class="syntax brush-javascript">
			$.hz.page.init("pageBarDemo",{
				changePageSize:true,
				changePageSizeNumber:"10,20,30",
				pageInfo:&#36;{pageObj.info},
				formId:"theForm_1_1"
			});
		</pre>
		
		<font class="green fb">标签使用方法：</font>
		<pre class="syntax brush-html">
			<form id="theForm_1_1" action="${path}/dm/page/demo1">
				……	……
				&lt;uc:pageBar pageInfo="&#36;{pageObj.info}" formId="theForm_1_1" changePageSize="true" changePageSizeNumber="10,20,30">&lt;/uc:pageBar>
			</form>
		</pre>
	</div>

	<div class="box1 fl" boxTitle="2.Ajax分页">
		<font color="green"><b>展示效果：</b></font>
		<div style="margin-top: 10px; margin-left: 10px; margin-right: 10px;">
			分页条展现类型
			<select onchange="loadPage(this.value);">
				<option value="normal">normal</option>
				<option value="simple">simple</option>
			</select>
			<div id="ajaxPage1">
	
			</div>
			<!-- <a href="javascript:;" onclick="setReset();">重置</a> -->
		</div>
		<font class="green fb">父页面（加载）html代码：</font>
		<pre class="syntax brush-html">
			<div id="ajaxPage1">
			
			</div>
		</pre>

		<font class="green fb">父页面（加载）js代码：</font>
		<pre class="syntax brush-javascript">
			$('#ajaxPage1').load("/dm/page/ajaxDemo1",function(){});
		</pre>

		<font class="green fb">子页面（列表）html代码：</font>
		<pre class="syntax brush-html">
			<div id="ajaxPage1">
				<form id="theForm_2_1" action="/dm/page/ajaxDemo1">
					……	……
					<div id="pageBarDemo" ></div>
				</form>
			</div>
		</pre>
		
		<font class="green fb">子页面（列表）js代码：</font>
		<pre class="syntax brush-javascript">
			$.hz.page.init("pageBarDemo",{
				pageInfo:${pageObj.info},
				formId:"theForm_2_1",
				containerId:"ajaxPage1"
			});
		</pre>
	</div>

	<font class="green fb">常用API：</font>
	<table class="cssTableBody">
		<tr>
			<th colspan="4" align="center">参数列表</th>
		</tr>
		<tr>
		   <th width="300">名称</th>
		   <th width="200">类型</th>
		   <th width="200">默认值</th>
		   <th>描述</th>
		</tr>
		<tr>
		   <td>pageInfo</td>
		   <td>JSON</td>
		   <td></td>
		   <td>分页条JSON数据</td>
		</tr>
		<tr>
		   <td>formId</td>
		   <td>String</td>
		   <td></td>
		   <td>
		 	 	表单ID
		   </td>
		</tr>
		<tr>
		   <td>containerId</td>
		   <td>String</td>
		   <td></td>
		   <td>
		  		Ajax分页时必须指定的数据列表的上层容器ID
		   </td>
		</tr>
		<tr>
		   <td>type</td>
		   <td>String</td>
		   <td>normal</td>
		   <td>
		  		分页条展现类型<br/>
		  		normal：普通类型<br/>
		  		simple：简单类型
		   </td>
		</tr>
		<tr>
		   <td>changePageSize</td>
		   <td>Boolean</td>
		   <td>false</td>
		   <td>
		   		是否启用切换单页数据条数功能
		   </td>
		</tr>
		<tr>
		   <td>changePageSizeNumber</td>
		   <td>String</td>
		   <td>10,20,50</td>
		   <td>
		   		单页数据条数可切换的数值，以逗号分隔。
		   </td>
		</tr>
	</table>
	<br/>
	<table class="cssTableBody">
		<tr>
			<th colspan="2" align="center">方法列表</th>
		</tr>
		<tr>
		   <th width="300">名称</th>
		   <th>描述</th>
		</tr>
		<tr>
		   <td>gotoFirst</td>
		   <td>首页<br/>示例：$.hz.page.gotoFirst("pageBarId")</td>
		</tr>
		<tr>
		   <td>gotoLast</td>
		   <td>尾页<br/>示例：$.hz.page.gotoLast("pageBarId")</td>
		</tr>
		<tr>
		   <td>gotoPrevious</td>
		   <td>上一页<br/>示例：$.hz.page.gotoPrevious("pageBarId")</td>
		</tr>
		<tr>
		   <td>gotoNext</td>
		   <td>下一页<br/>示例：$.hz.page.gotoNext("pageBarId")</td>
		</tr>
		<tr>
		   <td>gotoPage</td>
		   <td>跳转到指定页<br/>示例：$.hz.page.gotoPage("pageBarId",2)</td>
		</tr>
		<tr>
		   <td>changePageSize</td>
		   <td>改变单页数据条数<br/>示例：$.hz.page.changePageSize("pageBarId",20)</td>
		</tr>
		<tr>
		   <td>getPageSize</td>
		   <td>获取单页数据条数<br/>示例：$.hz.page.getPageSize("pageBarId")</td>
		</tr>
		<tr>
		   <td>getCurrentPage</td>
		   <td>获取当前页码<br/>示例：$.hz.page.getCurrentPage("pageBarId")</td>
		</tr>
		<tr>
		   <td>refresh</td>
		   <td>刷新<br/>示例：$.hz.page.refresh("pageBarId")</td>
		</tr>
	</table>
</div>

<script>
	function loadPage(type){
		$('#ajaxPage1').load("${path}/dm/page/ajaxDemo1/"+type,function(){});
	}
	$(document).ready(function() {
		$('#ajaxPage1').load("${path}/dm/page/ajaxDemo1/normal",function(){});
		$(".box1").box1();
	});
</script>