<!DOCTYPE html>
<%@page import="com.unitever.demo.model.TestObject"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp"%>
<!-- <a href='javascript:;' onclick='addUser();'>添加用户</a> -->
<!-- <div id='userList' style="bottom:50px!important;"> -->

<!-- </div> -->

<div class="box1 fl" boxTitle="1、JS使用方法">
	<font color="green"><b>展示效果：</b></font>
	<div style="margin-top: 10px;margin-left: 10px;margin-right: 10px;">
		<div id="fileDiv">
			     
        </div>
	</div>
	<br/>
	<font class="green fb">html代码部分：</font>
	<pre class="syntax brush-html">
		<div id="fileDiv">
			     
		</div>
	</pre>
	
	<font class="green fb">js代码部分：</font>
	<pre class="syntax brush-javascript">
		$.hz.swfupload.init("fileDiv",{configCode:"userPhoto",classFieldName:"com.unitever.platform.demo.model.User,id"});
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
		   <td>configCode</td>
		   <td>String</td>
		   <td>default</td>
		   <td>
		   		业务模块的附件配制编码
		   </td>
		</tr>
		<tr>
		   <td>classFieldName</td>
		   <td>String</td>
		   <td></td>
		   <td>
		   		格式："与附件关联的实体全路径名称，与附件关联的实体中的属性名"<br/>
		  		 示例：$.hz.swfupload.init("fileDiv",{classFieldName:"com.unitever.platform.demo.model.User,id"});
		   </td>
		</tr>
		<tr>
		   <td>idOwner</td>
		   <td>String</td>
		   <td></td>
		   <td>
		   		修改表单回显已上传的附件时用到idOwner，此属性对应与附件关联的实体中的属性值<br/>
		   		 示例：$.hz.swfupload.init("fileDiv",{classFieldName:"com.unitever.platform.demo.model.User,id" , idOwner:"userId"});
		   </td>
		</tr>
		<tr>
		   <td>finishCallback</td>
		   <td>String</td>
		   <td></td>
		   <td>
		   		单个附件上传完成时回调方法的名称
		   </td>
		</tr>
		<tr>
		   <td>deleteCallback</td>
		   <td>String</td>
		   <td></td>
		   <td>
		   		单个附件删除时时回调方法的名称
		   </td>
		</tr>
		<tr>
		   <td>indexNum</td>
		   <td>int</td>
		   <td>0</td>
		   <td>
		   		用于同一表单提交不同类型附件，后台需要手动处理绑定附件
		   </td>
		</tr>
		<tr>
		   <td>required</td>
		   <td>Boolean</td>
		   <td></td>
		   <td>
		   		验证上传组件必填
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
		   <td>validateAttachment</td>
		   <td>说明：表单提交前调用验证必填及附件上传是否完成，<br/>示例：$.hz.swfupload.validateAttachment("fileDiv");</td>
		</tr>
	</table>
</div>
<div class="box1" boxTitle="2、Tag使用方法">
	<font color="green"><b>展示效果：</b></font>
	<div style="margin-top: 10px;margin-left: 10px;margin-right: 10px;">
		<uc:attachmentUpload id="fileDiv2" model="<%=new TestObject()%>"></uc:attachmentUpload>
	</div>
	<br/>
	<font class="green fb">html代码部分：</font>
	<pre class="syntax brush-html">
		&lt;uc:attachmentUpload id="fileDiv2" model="&#36;{model}"/&gt;
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
		   <td>
		   		附件组件的唯一ID
		   </td>
		</tr>
		<tr>
		   <td>model</td>
		   <td>java.long.Object</td>
		   <td></td>
		   <td>
		   		与附件关联的实体对象
		   </td>
		</tr>
		<tr>
		   <td>configCode</td>
		   <td>String</td>
		   <td>default</td>
		   <td>
		   		业务模块的附件配制编码
		   </td>
		</tr>
		<tr>
		   <td>fieldName</td>
		   <td>String</td>
		   <td>id</td>
		   <td>
		   		与附件关联的实体中的属性名
		   </td>
		</tr>
		<tr>
		   <td>finishCallback</td>
		   <td>String</td>
		   <td></td>
		   <td>
		   		单个附件上传完成时回调方法的名称
		   </td>
		</tr>
		<tr>
		   <td>deleteCallback</td>
		   <td>String</td>
		   <td></td>
		   <td>
		   		单个附件删除时时回调方法的名称
		   </td>
		</tr>
		<tr>
		   <td>required</td>
		   <td>Boolean</td>
		   <td></td>
		   <td>
		   		验证上传组件必填
		   </td>
		</tr>
	</table>
</div>
<div class="box1" boxTitle="3、实体对象注解配置">
	<font class="green fb">java示例代码1：</font>
	<pre class="syntax brush-java">
		@AttachmentAnnotation
		public class User {
			private String id;
			private String name;
			private String email;
		}
	</pre>
	
	<font class="green fb">java示例代码2：</font>
	<pre class="syntax brush-java">
		@AttachmentAnnotation
		public class User {
			private String id;
			private String name;
			private String email;
			@AttachmentField
			private String id_photo;
		}
	</pre>
	
	<font class="green fb">注解说明：</font>
	<table class="cssTableBody">
		<tr>
			<th colspan="4" align="center">属性列表</th>
		</tr>
		<tr>
		   <th width="300">名称</th>
		   <th>描述</th>
		</tr>
		<tr>
		   <td>AttachmentAnnotation</td>
		   <td>
		   		标注实体对象关联附件：实体对象保存或更新时自动关联表单提交的附件
		   </td>
		</tr>
		<tr>
		   <td>AttachmentField</td>
		   <td>
		   		标注实体对象与附件关联的属性：实体对象保存或更新时与附件关联的属性
		   		<br/><b>注</b>：
		   		<br/>如果实体对象中任何属性都没有标注此注解，默认与附件关联的属性为“id”
		   		<br/>此注解可以标注在实体对象多个属性上，表示一个对象的多个属性都会关联附件
		   </td>
		</tr>
	</table>
</div>
<div class="box1" boxTitle="4、附件显示">
	<font class="green fb">附件显示方式1：</font>
	<pre class="syntax brush-java">
		&lt;uc:attachmentUrl var="attachments" model="&#36;{model}" /&gt;
		&lt;c:if test="&#36;{not empty attachments}">
			&lt;c:forEach items="&#36;{attachments}" var="attachment">
				<a href="&#36;{attachment.downloadUrl}" >&#36;{attachment.name}</a>
			&lt;/c:forEach>
		&lt;/c:if>
	</pre>

	<font class="green fb">演示效果：</font>
	<div style="margin-top: 10px;margin-left: 10px;margin-right: 10px;">
		<a href="/platform//sys/attachment/download.do?checkUser=false&period=year&downloadToken=20140807183809419598802981875370baa74dabf3dda852a826ed6bc9ec7747">Koala.jpg</a><br/>
		<a href="/platform//sys/attachment/download.do?checkUser=false&period=year&downloadToken=20140807155008744682454734118497baa74dabf3dda852a826ed6bc9ec7747">Chrysanthemum.jpg</a><br/>
		<a href="/platform//sys/attachment/download.do?checkUser=false&period=year&downloadToken=20140807183809004706888030206723baa74dabf3dda852a826ed6bc9ec7747">Jellyfish.jpg</a>
	</div>
	
	<table class="cssTableBody">
		<tr>
			<th colspan="4" align="center">属性说明</th>
		</tr>
		<tr>
		   <th width="300">名称</th>
		   <th>类型</th>
		   <th>默认值</th>
		   <th>描述</th>
		</tr>
		<tr>
		   	<td>model</td>
		  	<td>java.long.Object</td>
		  	<td></td>
		   	<td>
		   		与附件关联的实体对象<br/>
		   		说明：此属性目的是查询出与实体对象关联的附件列表，存储到request中
		   	</td>
		</tr>
		<tr>
		   <td>fieldName</td>
		   <td>String</td>
		   <td>id</td>
		   <td>
		   		与附件关联的实体中的属性名
		   </td>
		</tr>
		<tr>
		   	<td>var</td>
		  	<td>String</td>
		  	<td></td>
		   	<td>
		   		request中存储附件列表的key
		   		<br/>说明：
		   		<br/>1、通过key在request中拿到附件列表"attachments"
		   		<br/>2、遍历附件列表拿到附件对象"attachment"
		   		<br/>3、附件对象常用属性：id、name、downloadUrl、picUrl、audioUrl
		   	</td>
		</tr>
		<tr>
		   	<td>period</td>
		  	<td>String</td>
		  	<td>year</td>
		   	<td>附件生成下载地址的有效周期，包括year、month、day、hour</td>
		</tr>
		<tr>
		   	<td>checkUser</td>
		  	<td>Boolean</td>
		  	<td>false</td>
		   	<td>附件生成下载地址是否要验证上传用户，目的是只有上传用户可访问</td>
		</tr>
	</table>
	
	<font class="green fb">附件显示方式2：</font>
	<pre class="syntax brush-java">
		&lt;uc:attachmentView model="&#36;{model}">&lt;/uc:attachmentView>
	</pre>
	<font class="green fb">演示效果：</font>
	<div style="margin-top: 10px;margin-left: 10px;margin-right: 10px;">
		<fieldset>
			<legend>附件提取</legend>
			<ul>
				<li>
					<a href="/platform//sys/attachment/download.do?checkUser=false&amp;period=year&amp;downloadToken=20140807183809419598802981875370baa74dabf3dda852a826ed6bc9ec7747">Koala.jpg</a>
				</li>
				<li>
					<a href="/platform//sys/attachment/download.do?checkUser=false&amp;period=year&amp;downloadToken=20140807155008744682454734118497baa74dabf3dda852a826ed6bc9ec7747">Chrysanthemum.jpg</a>
				</li>
				<li>
					<a href="/platform//sys/attachment/download.do?checkUser=false&amp;period=year&amp;downloadToken=20140807183809004706888030206723baa74dabf3dda852a826ed6bc9ec7747">Jellyfish.jpg</a>
				</li>
			</ul>
			<a href="/platform//sys/attachment/download.do?checkUser=false&amp;period=year&amp;downloadToken=20140806134741018678937081449344baa74dabf3dda852a826ed6bc9ec7747&amp;isZip=true" class="button btnGreen">打包下载</a>
		</fieldset>
	</div>
	
	
	<table class="cssTableBody">
		<tr>
			<th colspan="4" align="center">属性说明</th>
		</tr>
		<tr>
		   <th width="300">名称</th>
		   <th>类型</th>
		   <th>默认值</th>
		   <th>描述</th>
		</tr>
		<tr>
		   	<td>model</td>
		  	<td>java.long.Object</td>
		  	<td></td>
		   	<td>
		   		与附件关联的实体对象<br/>
		   		说明：此属性目的是查询出与实体对象关联的附件列表，存储到request中
		   	</td>
		</tr>
		<tr>
		   <td>fieldName</td>
		   <td>String</td>
		   <td>id</td>
		   <td>
		   		与附件关联的实体中的属性名
		   </td>
		</tr>
		<tr>
		   	<td>period</td>
		  	<td>String</td>
		  	<td>year</td>
		   	<td>附件生成下载地址的有效周期，包括year、month、day、hour</td>
		</tr>
		<tr>
		   	<td>checkUser</td>
		  	<td>Boolean</td>
		  	<td>false</td>
		   	<td>附件生成下载地址是否要验证上传用户，目的是只有上传用户可访问</td>
		</tr>
		<tr>
		   	<td>showAll</td>
		  	<td>Boolean</td>
		  	<td>true</td>
		   	<td>是否显示附件列表</td>
		</tr>
		<tr>
		   	<td>showZipUrl</td>
		  	<td>Boolean</td>
		  	<td>true</td>
		   	<td>是否显示附件压缩包</td>
		</tr>
	</table>
</div>
<script type="text/javascript">
	function addUser(id){
		if(!id)id="";
		var url = "${path}/dm/user/input.do?id="+id;
		$.dialog.load(url,{
			title:"添加用户",
			width:700,
			height:600
	//			ok:function(){
	//				com.ue.platform.module.user.doAdd();
	//			}
		})
	}
	
	$(document).ready(function() {
		$(".box1").box1();
		$.hz.swfupload.init("fileDiv",{classFieldName:"com.unitever.platform.demo.model.User,id"});
		
// 		$.ajax({
// 		 	type: "POST",
// 		  	url: "${path}/dm/user/list.do",
// 		  	success: function(data){
// 		  		var userIds = data.split(":");
// 		  		$(userIds).each(function(){
// 		  			$("#userList").append("<a href='javascript:;' onclick='addUser(\""+this+"\");'>"+this+"</a><br/>")
// 		  		});
// 		  	}
// 		});
	})
</script>