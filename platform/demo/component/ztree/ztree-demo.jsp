<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<div class="box1 fl" boxTitle="1、JS使用方法">
	<div class="box1 fl" boxTitle="菜单树">
		<font color="green"><b>展示效果：</b></font>
		<div style="margin-top: 10px;margin-left: 10px;margin-right: 10px;">
			<ul id="menuTree" >
			
			</ul>
		</div>
		
		<font class="green fb">html代码部分：</font>
		<pre class="syntax brush-html">
			<ul id="menuTree" ></ul>
		</pre>
		
		<font class="green fb">js代码部分：</font>
		<pre class="syntax brush-javascript">
			$.hz.ztree.init("menuTree",{
				nodes:nodes
			});
		</pre>
	</div>
	
	<div class="box1 fl" boxTitle="复选树">
		<font color="green"><b>展示效果：</b></font>
		<div style="margin-top: 10px;margin-left: 10px;margin-right: 10px;">
			<ul id="checkboxTree" >
			
			</ul>
			[ <a id="expandAllTrue" href="#"  onclick="return false;">展开全部</a> ]
			[ <a id="expandAllFalse" href="#"  onclick="return false;">折叠全部</a> ]
			[ <a id="checkAllTrue" href="#"  onclick="return false;">选中全部</a> ]
			[ <a id="checkAllFalse" href="#" onclick="return false;">取消选中全部</a> ]
			[ <a id="nocheckTrue"  href="#"   onclick="return false;">隐藏选择框</a> ]
			[ <a id="nocheckFalse" href="#"  onclick="return false;">显示选择框</a> ]
			<!-- <a href="javascript:;" onclick="setReset();">重置</a> -->
		</div>
		
		<font class="green fb">html代码部分：</font>
		<pre class="syntax brush-html">
			<ul id="checkboxTree" ></ul>
		</pre>
		
		<font class="green fb">js代码部分：</font>
		<pre class="syntax brush-javascript">
			$.hz.ztree.init("checkboxTree",{
				nodes:nodes,
				check: {
					enable: true,
					chkStyle: "checkbox",
				}
			});
		</pre>
	</div>
	
	<div class="box1 fl" boxTitle="单选树">
		<font color="green"><b>展示效果：</b></font>
		<div style="margin-top: 10px;margin-left: 10px;margin-right: 10px;">
			<ul id="radioTree" >
			
			</ul>
		</div>
		
		<font class="green fb">html代码部分：</font>
		<pre class="syntax brush-html">
			<ul id="radioTree" ></ul>
		</pre>
		
		<font class="green fb">js代码部分：</font>
		<pre class="syntax brush-javascript">
			$.hz.ztree.init("radioTree",{
				nodes:zNodes,
				check: {
					enable: true,
					chkStyle: "radio",
					radioType: "all" //radioType = "all" 时，在整棵树范围内当做一个分组。
					//radioType = "level" 时，在每一级节点范围内当做一个分组。
				}
			});
		</pre>
	</div>
	
	<div class="box1 fl" boxTitle="下拉树">
		<font color="green"><b>展示效果：</b></font>
		<div style="margin-top: 10px;margin-left: 10px;margin-right: 10px;">
			<ul id="dropdownTree" >
			
			</ul>
			<uc:tree nodes="[{id:1, pId:0, name:'北京',isParent:true},{id:2, pId:0, name:'天津'},{id:4, pId:0,name:'河北省',open:true,url:'http://www.taobao.com',nodes:[{id:41, pId:4, name:'石家庄'},{id:42, pId:4, name:'张家口',nodes:[{id:421,pId:42,name:'涿鹿',nodes:[{id:4211,pId:421,name:'西关村'}]}]}]}]" 
			 id="dropdownTree1" dropdown="true" checkEnable="true" checkValue="4,41" name="model.region" required="true"></uc:tree>
		</div>
		
		<font class="green fb">html代码部分：</font>
		<pre class="syntax brush-html">
			<ul id="dropdownTree" ></ul>
		</pre>
		
		<font class="green fb">js代码部分：</font>
		<pre class="syntax brush-javascript">
			$.hz.ztree.init("dropdownTree",{
				nodes:zNodes,
				isDropdown:true,	//下拉树
				checkValue:"4,41",		//回显值
				check: {
					enable: true,
					chkStyle: "checkbox"
				}
			});
		</pre>
	</div>
	<font class="green fb">常用API：</font>
	<table class="cssTableBody">
		<tr>
			<th colspan="5" align="center">参数列表</th>
		</tr>
		<tr>
		   <th width="100">分组</th>
		   <th width="300">属性</th>
		   <th width="200">类型</th>
		   <th width="200">默认值</th>
		   <th>描述</th>
		</tr>
		<tr>
		   <td rowspan="6"></td>
		   <td>nodes</td>
		   <td>JSON</td>
		   <td></td>
		   <td>构建树的Json数据源</td>
		</tr>
		<tr>
		   <td>checkValue</td>
		   <td>String</td>
		   <td></td>
		   <td>树的回显值，以逗号分隔，例如："1,12,22"</td>
		</tr>
		<tr>
		   <td>expandLevel</td>
		   <td>String</td>
		   <td></td>
		   <td>树默认展开层级，例如："2"</td>
		</tr>
		<tr>
		   <td>isDropdown</td>
		   <td>Boolean</td>
		   <td>false</td>
		   <td>下拉树开关</td>
		</tr>
		<tr>
		   <td>name</td>
		   <td>String</td>
		   <td></td>
		   <td>下拉选择树的输入框名称（配合isDropdown使用）</td>
		</tr>
		<tr>
		   <td>required</td>
		   <td>Boolean</td>
		   <td>false</td>
		   <td>下拉选择树是否必填（配合isDropdown使用）</td>
		</tr>
		<tr>
		   <td rowspan="6">check</td>
		   <td>enable</td>
		   <td>Boolean</td>
		   <td>false</td>
		   <td>设置 zTree 的节点上是否显示 checkbox / radio</td>
		</tr>
		<tr>
		   <td>chkStyle</td>
		   <td>String</td>
		   <td>checkbox</td>
		   <td>勾选框类型(checkbox 或 radio）</td>
		</tr>
		<tr>
		   <td>radioType</td>
		   <td>String</td>
		   <td>level</td>
		   <td>
		   		radio 的分组范围<br/>
		   		radioType = "level" 时，在每一级节点范围内当做一个分组。 <br/>
		   		radioType = "all" 时，在整棵树范围内当做一个分组。
		   </td>
		</tr>
		<tr>
		   <td>chkboxType</td>
		   <td>String</td>
		   <td>{ "Y": "ps", "N": "ps" }</td>
		   <td>
				Y 属性定义 checkbox 被勾选后的情况；<br/>N 属性定义 checkbox 取消勾选后的情况；<br/>
				"p" 表示操作会影响父级节点；<br/>"s" 表示操作会影响子级节点。
		   </td>
		</tr>
		<tr>
		   <td>nocheckInherit</td>
		   <td>Boolean</td>
		   <td>false</td>
		   <td>当父节点设置 nocheck = true 时，设置子节点是否自动继承 nocheck = true 。</td>
		</tr>
		<tr>
		   <td>chkDisabledInherit</td>
		   <td>Boolean</td>
		   <td>false</td>
		   <td>当父节点设置 chkDisabled = true 时，设置子节点是否自动继承 chkDisabled = true 。</td>
		</tr>
		<tr>
		   <td rowspan="6">callback</td>
		   <td>onClick</td>
		   <td>Function</td>
		   <td></td>
		   <td>
		   		用于捕获节点被点击的事件回调函数,回调参数如下<br/>
				event:js event 对象<br/>
				treeId:对应 zTree 的 treeId，便于用户操控<br/>
				treeNode:被点击的节点 JSON 数据对象
		   </td>
		</tr>
		<tr>
		   <td>onDblClick</td>
		   <td>Function</td>
		   <td></td>
		   <td>
		   		用于捕获 zTree 上鼠标双击之后的事件回调函数,回调参数如下<br/>
				event:js event 对象<br/>
				treeId:对应 zTree 的 treeId，便于用户操控<br/>
				treeNode:被点击的节点 JSON 数据对象
		   </td>
		</tr>
		<tr>
		   <td>onCheck</td>
		   <td>Function</td>
		   <td></td>
		   <td>
		   		用于捕获 checkbox / radio 被勾选 或 取消勾选的事件回调函数,回调参数如下<br/>
				event:js event 对象<br/>
				treeId:对应 zTree 的 treeId，便于用户操控<br/>
				treeNode:被点击的节点 JSON 数据对象
		   </td>
		</tr>
		<tr>
		   <td>beforeClick</td>
		   <td>Function</td>
		   <td></td>
		   <td>
		   		用于捕获单击节点之前的事件回调函数，并且根据返回值确定是否触发 onClick 事件回调函数,回调参数如下<br/>
				treeId:对应 zTree 的 treeId，便于用户操控<br/>
				treeNode:被点击的节点 JSON 数据对象<br/>
				<br/>
				返回值是 true / false
		   </td>
		</tr>
		<tr>
		   <td>beforeDblClick</td>
		   <td>Function</td>
		   <td></td>
		   <td>
		   		用于捕获 zTree 上鼠标双击之前的事件回调函数，并且根据返回值确定是否触发 onDblClick 事件回调函数,回调参数如下<br/>
				treeId:对应 zTree 的 treeId，便于用户操控<br/>
				treeNode:被点击的节点 JSON 数据对象<br/>
				<br/>
				返回值是 true / false
		   </td>
		</tr>
		<tr>
		   <td>beforeCheck</td>
		   <td>Function</td>
		   <td></td>
		   <td>
		   		用于捕获 勾选 或 取消勾选 之前的事件回调函数，并且根据返回值确定是否允许 勾选 或 取消勾选 ,回调参数如下<br/>
				treeId:对应 zTree 的 treeId，便于用户操控<br/>
				treeNode:被点击的节点 JSON 数据对象<br/>
				<br/>
				返回值是 true / false
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
		   <td>getZTreeObj</td>
		   <td>根据 treeId 获取 zTree 对象<br/>示例：$.hz.ztree.getZTreeObj("treeDemo");</td>
		</tr>
		<tr>
		   <td>expandAll</td>
		   <td>
		   		展开 / 折叠 全部节点，参数说明：<br/>
		   		expandFlag： 展开 / 折叠状态（true/false）<br/>
		   		示例：$("#treeDemo").ztree("expandAll",true);
		   </td>
		</tr>
		<tr>
		   <td>expandNode</td>
		   <td>
		 		  展开 / 折叠 指定的节点，参数说明：<br/>
		   		treeNode:需要 展开 / 折叠 的节点数据<br/>
		   		expandFlag:需要 展开 / 折叠 的状态（true/false）<br/>
		   		sonSign:全部子孙节点是否进行与 expandFlag 相同的操作（true/false）<br/>
		   		focus：展开 / 折叠 操作后，通过设置焦点保证此焦点进入可视区域内（true/false）<br/>
		   		示例：$.hz.ztree.expandNode("treeDemo",treeNode,true,false,true);<br/>
		   </td>
		</tr>
		<tr>
		   <td>expandNodeWithId</td>
		   <td>
		   		根据Id 展开 / 折叠 指定的节点，参数说明：<br/>
		   		id:结点ID<br/>
		   		示例：$.hz.ztree.expandNodeWithId("treeDemo","1143");
		   </td>
		</tr>
		<tr>
		   <td>checkAllNodes</td>
		   <td>
		   		勾选 或 取消勾选 全部节点，参数说明：<br/>
		   		checked:勾选 或 取消勾选状态（true/false）<br/>
		   		示例：$.hz.ztree.checkAllNodes("treeDemo",true);
		   </td>
		</tr>
		<tr>
		   <td>checkNode</td>
		   <td>
		   		勾选 或 取消勾选 单个节点，参数说明：<br/>
		   		treeNode:需要 展开 / 折叠 的节点数据<br/>
		   		checked:勾选 或 取消勾选状态（true/false）<br/>
		   		checkTypeFlag:表示按照 setting.check.chkboxType 属性进行父子节点的勾选联动操作（true/false）<br/>
		   		callbackFlag：执行此方法时是否触发 beforeCheck & onCheck 事件回调函数（true/false）<br/>
		   		示例：$.hz.ztree.checkNode("treeDemo",treeNode,true,false,false);
		   </td>
		</tr>
		<tr>
		   <td>checkNodeWithId</td>
		   <td>
		   		根据Id勾选 或 取消勾选 单个节点，参数说明：<br/>
		   		id:结点ID<br/>
		   		示例：$.hz.ztree.checkNodeWithId("treeDemo","1143");
		   </td>
		</tr>
		<tr>
		   <td>selectNode</td>
		   <td>
		  		 选中指定节点，参数说明：<br/>
		  		treeNode：节点数据<br/>
		  		addFlag： 是否追加选中，会出现多点同时被选中的情况（true/false）<br/>
		  		 示例：$.hz.ztree.selectNode("treeDemo",true);
		   </td>
		</tr>
		<tr>
		   <td>getCheckedNodes</td>
		   <td>
		   		获取勾选 或 未勾选的节点集合，参数说明：<br/>
		   		checked:勾选 或 未勾选状态（true/false）<br/>
		   		示例：$.hz.ztree.getCheckedNodes("treeDemo",true);
		   </td>
		</tr>
		<tr>
		   <td>getSelectedNodes</td>
		   <td>获取 zTree 当前被选中的节点数据集合<br/>示例：$.hz.ztree.getSelectedNodes("treeDemo");</td>
		</tr>
		<tr>
		   <td>getCheckedNodeIds</td>
		   <td>获取被勾选的结点Id集合<br/>示例：$.hz.ztree.getCheckedNodeIds("treeDemo");</td>
		</tr>
		<tr>
		   <td>getCheckedNodeIds</td>
		   <td>获取被勾选的结点Name集合<br/>示例：$.hz.ztree.getCheckedNodeNames("treeDemo");</td>
		</tr>
		<tr>
		   <td>getSelectedNodeIds</td>
		   <td>获取被选中的结点Id集合<br/>示例：$.hz.ztree.getSelectedNodeIds("treeDemo");</td>
		</tr>
		<tr>
		   <td>expandNodeByLevel</td>
		   <td>根据指定层级展开树<br/>示例：$.hz.ztree.expandNodeByLevel("treeDemo",2);</td>
		</tr>
	</table>
</div>

<script>
	

	var zNodes =[{id:1, pId:0, name:'北京',isParent:true},{id:2, pId:0, name:'天津'},{id:4, pId:0,name:'河北省',open:true,url:'http://www.taobao.com',nodes:[{id:41, pId:4, name:'石家庄'},{id:42, pId:4, name:'张家口',nodes:[{id:421,pId:42,name:'涿鹿',nodes:[{id:4211,pId:421,name:'西关村'}]}]}]}];
	
	function nocheckNode(e) {
		var zTree = $.hz.ztree.getZTreeObj("checkboxTree");
		
		var nocheck = e.data.nocheck,
		nodes = zTree.getSelectedNodes();
		if (nodes.length == 0) {
			alert("请先选择一个节点");
			return;
		}

		for (var i=0, l=nodes.length; i<l; i++) {
			nodes[i].nocheck = nocheck;
			zTree.updateNode(nodes[i]);
		}
	}
	
	function expandAll(e) {
		var expandFlag = e.data.expandFlag;
		 $.hz.ztree.expandAll("checkboxTree",expandFlag)
	}
	
	function checkAll(e) {
		var checked = e.data.checked;
		$.hz.ztree.checkAllNodes("checkboxTree",checked);
	}
	
	
	function initMenuTree(){
		$.getJSON("${path}/dm/getMenuTreeJson", function(nodes){
			$.hz.ztree.init("menuTree",{
				nodes:nodes
			});
		});
	}
	
	function initCheckTree(){
		$.hz.ztree.init("checkboxTree",{
			nodes:zNodes,
			check: {
				enable: true,
				nocheckInherit: true,
				chkStyle: "checkbox",
				chkboxType: { "Y": "ps", "N": "ps" }
			}
		});
		
		$("#nocheckTrue").bind("click", {nocheck: true}, nocheckNode);
		$("#nocheckFalse").bind("click", {nocheck: false}, nocheckNode);
		$("#expandAllTrue").bind("click", {expandFlag: true}, expandAll);
		$("#expandAllFalse").bind("click", {expandFlag: false}, expandAll);
		$("#checkAllTrue").bind("click", {checked: true}, checkAll);
		$("#checkAllFalse").bind("click", {checked: false}, checkAll);
	}
	
	function initRadioTree(){
		$.hz.ztree.init("radioTree",{
			nodes:zNodes,
			check: {
				enable: true,
				chkStyle: "radio",
				radioType: "all" 
				//radioType = "level" 时，在每一级节点范围内当做一个分组。
				//radioType = "all" 时，在整棵树范围内当做一个分组。
			}
		});
	}
	
	function initDropdownTree(){
		$.hz.ztree.init("dropdownTree",{
			nodes:zNodes,
			isDropdown:true,
			checkValue:"4,41",
			check: {
				enable: true,
				chkStyle: "checkbox"
// 				radioType: "all" 
				//radioType = "level" 时，在每一级节点范围内当做一个分组。
				//radioType = "all" 时，在整棵树范围内当做一个分组。
			}
		});
	}

	
	function init(){
		initMenuTree();
		initCheckTree();
		initRadioTree();
// 		initDropdownTree();
	}
	
	$(document).ready(function(){
		$(".box1").box1();
		init();
	});
</script>