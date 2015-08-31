<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ include file="/platform/common/jsp/taglibs.jsp"%>
<%@ include file="/platform/common/jsp/baseJs.jsp"%>

<html>

	<head>

		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>${title}-字典树</title>
		<script type="text/javascript">
		var valueId="${idHead}Value";
		var textId="${idHead}Text";
		var isFullPath;
		<c:if test="${not empty isFullPath}">
			isFullPath=${isFullPath};
		</c:if>
        
        function ok(){
        	var myObj=getOperateDoc();
			var checkedNodeId=$.hz.ztree.getCheckedNodeIds("dictParentTree");
// 			alert(com.ue.cmpt.tree.getCheckNodeNames("dictParentTree",isFullPath))
        	if(checkedNodeId){
	        	myObj.getElementById(valueId).value=checkedNodeId;
	        	myObj.getElementById(textId).value=$.hz.ztree.getCheckedNodeNames("dictParentTree");

	            window.close();
        	}else{
        		clearInput();
        	}
        }
        
        //--得到完整的字典名称
        function getfullName(node){
        	var nodeName=node.text;
        	if(node.parent){
        		if(node.parent.value){
        			nodeName=getfullName(node.parent)+"-"+nodeName;
        		}
        	}
        	return nodeName;
        }
        function clearInput(){
			var myObj=getOperateDoc();
			myObj.getElementById(valueId).value="";
			myObj.getElementById(textId).value="顶级";
			window.close();
        }
        //--得到操作弹出事件的窗体对象
        function getOperateDoc(){
			return art.dialog.opener.document;
        }
        
        $(document).ready(function() {
        	var treeJson=${treeJson};
				$.hz.ztree.init("dictParentTree",{
					nodes:treeJson,
					check: {
					enable: true,
					chkStyle: "radio",
					radioType: "all" 
					}
				});
				$.hz.ztree.expandNodeByLevel("dictParentTree",1);
        		
        		var myObj=getOperateDoc();
        		var parentValueId=myObj.getElementById(valueId).value;
        		if(parentValueId){
        			$.hz.ztree.expandNodeWithId("dictParentTree",parentValueId); 
        			$.hz.ztree.checkNodeWithId("dictParentTree",parentValueId);
        		}
        });
		</script>
	</head>

	<body>
		<ul id="dictParentTree">
		    
		</ul>
	</body>
</html>
