<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<div class="box1 fl" boxTitle="1、JS使用方法">
	<font color="green"><b>展示效果：</b></font>
	<div style="margin-top: 10px;margin-left: 10px;margin-right: 10px;">
		<uc:form id="theForm">
			<div id="editor" style="width:600px;height:300px;"></div>
		</uc:form>
		
		<a href="javascript:;" onclick="getContent();">获取内容</a>
		<a href="javascript:;" onclick="setContent();">设置内容</a>
		<a href="javascript:;" onclick="disable();">禁用</a>
		<a href="javascript:;" onclick="enable();">启用</a>
		<a href="javascript:;" onclick="hide();">隐藏</a>
		<a href="javascript:;" onclick="show();">显示</a>
		<a href="javascript:;" onclick="sync();">同步</a>
		<!-- <a href="javascript:;" onclick="setReset();">重置</a> -->
	</div>
	
	<font class="green fb">html代码部分：</font>
	<pre class="syntax brush-html">
		<div id="editor" style="width:600px;height:300px;"></div>
	</pre>
	
	<font class="green fb">js代码部分：</font>
	<pre class="syntax brush-javascript">
		$.hz.ueditor.init("editor",{
			content:"<p>内容：<span style='color:red'>您好!!</span></p>",
			name:"modelName",
			simpleEnabled:false
		});
	</pre>
	
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
		   <td>name</td>
		   <td>String</td>
		   <td></td>
		   <td>表单提交Name</td>
		</tr>
		<tr>
		   <td>content</td>
		   <td>String</td>
		   <td></td>
		   <td>编辑器默认显示内容</td>
		</tr>
		<tr>
		   <td>simpleEnabled</td>
		   <td>Boolean</td>
		   <td>false</td>
		   <td>使用简单模式</td>
		</tr>
		<tr>
		   <td>autoHeightEnabled</td>
		   <td>Boolean</td>
		   <td>false</td>
		   <td>编辑框部分可以随着编辑内容的增加而自动长高</td>
		</tr>
		<tr>
		   <td>wordCount</td>
		   <td>Boolean</td>
		   <td>false</td>
		   <td>是否显示已输入字符个数</td>
		</tr>
		<tr>
		   <td>toolbar</td>
		   <td>Array</td>
		   <td>编辑器默认功能按钮</td>
		   <td>定制编辑器工具栏功能按钮，此项设置后“simpleEnabled”参数失效。<br/>具体配置请参考ueditor.config.js</td>
		</tr>
		<tr>
		   <td>zIndex</td>
		   <td>Integer</td>
		   <td>2000</td>
		   <td>编辑器层次堆叠顺序</td>
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
		   <td>getContent</td>
		   <td>说明：获取编辑器内容<br/>示例：$.hz.ueditor.getContent("editor");</td>
		</tr>
		<tr>
		   <td>setContent</td>
		   <td>说明：设置编辑器内容<br/>示例：$.hz.ueditor.setContent("editor","新内容");</td>
		</tr>
		<tr>
		   <td>enable</td>
		   <td>说明：启用编辑器内容<br/>示例：$.hz.ueditor.enable("editor");</td>
		</tr>
		<tr>
		   <td>disable</td>
		   <td>说明：禁用编辑器内容<br/>示例：$.hz.ueditor.disable("editor");</td>
		</tr>
		<tr>
		   <td>show</td>
		   <td>说明：显示编辑器内容<br/>示例：$.hz.ueditor.show("editor");</td>
		</tr>
		<tr>
		   <td>hide</td>
		   <td>说明：隐藏编辑器内容<br/>示例：$.hz.ueditor.hide("editor");</td>
		</tr>
		<tr>
		   <td>sync</td>
		   <td>说明：编辑器内容与表单同步，此方法执行后表单可以正常提交编辑器内容<br/>示例：$.hz.ueditor.sync("editor");</td>
		</tr>
	</table>
</div>

<div class="box1 fl" boxTitle="2、Tag使用方法">
	<font color="green"><b>展示效果：</b></font>
	<div style="margin-top: 10px;margin-left: 10px;margin-right: 10px;">
		<uc:UEditor id="editor2" name="modelName2"  value="<p>内容：您好!!</p>" style="width:600px;height:300px;"></uc:UEditor>
	</div>
	
	<font class="green fb">html代码部分：</font>
	<pre class="syntax brush-html">
		&lt;uc:UEditor id="editor2" name="modelName2"  value="<p>内容：您好!!</p>" style="width:600px;height:300px;"/&gt;	
	</pre>
	
	<font class="green fb">标签属性：</font>
	<table class="cssTableBody">
		<tr>
			<th colspan="4" align="center">属性列表</th>
		</tr>
		<tr>
		   <th width="300">名称</th>
		   <th width="200">类型</th>
		   <th width="200">默认值</th>
		   <th>描述</th>
		</tr>
		<tr>
		   <td>id</td>
		   <td>String</td>
		   <td></td>
		   <td>编辑器唯一ID</td>
		</tr>
		<tr>
		   <td>name</td>
		   <td>String</td>
		   <td></td>
		   <td>表单提交Name</td>
		</tr>
		<tr>
		   <td>value</td>
		   <td>String</td>
		   <td></td>
		   <td>编辑器默认显示内容</td>
		</tr>
		<tr>
		   <td>simpleEnabled</td>
		   <td>Boolean</td>
		   <td>false</td>
		   <td>使用简单模式</td>
		</tr>
		<tr>
		   <td>autoHeightEnabled</td>
		   <td>Boolean</td>
		   <td>false</td>
		   <td>编辑框部分可以随着编辑内容的增加而自动长高</td>
		</tr>
		<tr>
		   <td>wordCount</td>
		   <td>Boolean</td>
		   <td>false</td>
		   <td>是否显示已输入字符个数</td>
		</tr>
		<tr>
		   <td>zIndex</td>
		   <td>String</td>
		   <td>2000</td>
		   <td>编辑器层次堆叠顺序</td>
		</tr>
	</table>
</div>

<script>
	function getContent(){
		var content = $.hz.ueditor.getContent("editor");
		alert(content);
	}
	
	function setContent(){
		$.hz.ueditor.setContent("editor","新内容：<font color='red'>hello!!</font>");
	}
	
	function enable(){
		$.hz.ueditor.enable("editor");
	}
	
	function disable(){
		$.hz.ueditor.disable("editor");
	}
	
	function show(){
		$.hz.ueditor.show("editor");
	}
	
	function hide(){
		$.hz.ueditor.hide("editor");
	}
	
	function setReset(){
		$.hz.ueditor.reset("editor");
	}
	
	function sync(){
		$.hz.ueditor.sync("editor");
		var formData = $("#theForm").serialize()
		 $.ajax({
			 	type: "POST",
			  	url: "${path}/dm/component/saveUEditor.do",
			  	processData:true,
			  	data:formData,
			  	success: function(data){
			  	}
		 });
		alert(formData);
	}
	
	$(document).ready(function(){
		$.hz.ueditor.init("editor",{
			content:"<p>内容：<span style='color:red'>您好!!</span></p>",
			name:"modelName",
			simpleEnabled:false
		});
		$(".box1").box1();
// 		UE.getEditor('editor');
// 		UE.delEditor('editor');
	});
</script>