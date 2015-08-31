/**
 * 把ztree包装为jquery插件，调用插件中的方法。
 */
(function($){
	$.hz.ztree = {
		init:function(treeId,param){
			var $obj = $("#"+treeId);
			if($obj.size() == 0){
				return;
			}
			$obj.addClass("ztree");
			var defaultSetting = {
				data: {
					simpleData: {
						enable: false
					},
					key: {
						children: "nodes"
					}
				}
			};
			var setting =  $.extend(defaultSetting, param);
			if(setting.check && !setting.check.chkboxType){
				setting.check.chkboxType={ "Y": "", "N": "" };
			}
			if(setting.isDropdown){
				var required = setting.required?" required":"";
				var name = setting.name?setting.name:"";
				var treeWrap = $obj.wrap("<div id='"+ treeId + "Background' style='z-index:50000;display:none;position:absolute;height:200px;width:163px;background-color:white;border:1px solid #CCCCCC;'></div>").parent();
				treeWrap.before("<input id='" + treeId + "_value' type='hidden' name='"+name+"'/>");
				treeWrap.before("<input id='" + treeId + "_name' class='inputStyle"+required+"'  type='text' onclick=\"$.hz.ztree.showDropdownTree('"+treeId+"')\" readonly value=''/>");
				$obj.wrap("<div style='background-color:white;z-index:50000;position:absolute;height:170px; width:163px; overflow-y:auto;overflow-x:auto;'></div>");
				var buttonHtml=[];
				buttonHtml.push("<div style='border:1px solid #CCCCCC;background-color:#EEEEEE;z-index:50000;width:163px;height:30px;position:absolute;bottom:-1px;left:-1px'>");
				buttonHtml.push("<a class='button btnBlue' style='height: 11px; width: 28px; position: absolute; left: 15px;' onclick=\"$.hz.ztree.hideDropdownTree('"+treeId+"')\">"+"确定"+"</a>");
				buttonHtml.push("<a class='button' style='height: 11px; width: 28px; position: absolute; left: 83px;' onclick='javascript:$(\"#" + treeId+"Background\").hide()'>"+"取消"+"</a>");
				buttonHtml.push("</div>");
				treeWrap.append(buttonHtml.join(''));
			}
			$.fn.zTree.init($obj, setting, setting.nodes);
			if(setting.checkValue){
				var valueArray = setting.checkValue.split(",");
				for(var i=0;i<valueArray.length;i++){
					this.checkNodeWithId(treeId,valueArray[i]);
				}
				if(setting.isDropdown){
					this.hideDropdownTree(treeId);
				}
			}
			
			if(setting.expandLevel && !isNaN(setting.expandLevel)){
				this.expandNodeByLevel(treeId,setting.expandLevel);
			}
//				$("#"+domId).data("ztree",ztree);
		},
		getZTreeObj:function(treeId){
			return getZTreeObj(treeId);
		},
		expandAll:function(treeId,expandFlag){
			var treeObj =getZTreeObj(treeId);
			treeObj.expandAll(expandFlag);
		},
		expandNode:function(treeId,treeNode,expandFlag,sonSign,focus,callbackFlag){
			var treeObj =getZTreeObj(treeId);
			treeObj.expandNode(treeNode,expandFlag,sonSign,focus,callbackFlag);
		},
		expandNodeWithId:function(treeId,nodeId){
			var treeObj =getZTreeObj(treeId);
			var node = treeObj.getNodeByParam("id", nodeId, null);
			treeObj.expandNode(node,true,true,false);
		},
		checkAllNodes:function(treeId,checked){
			var treeObj =getZTreeObj(treeId);
			treeObj.checkAllNodes(checked);
		},
		checkNode:function(treeId,treeNode,checked,checkTypeFlag,callbackFlag){
			var treeObj =getZTreeObj(treeId);
			treeObj.checkNode(treeNode,checked,checkTypeFlag,callbackFlag);
		},
		checkNodeWithId:function(treeId,nodeId){
			var treeObj =getZTreeObj(treeId);
			var node = treeObj.getNodeByParam("id", nodeId, null);
			treeObj.checkNode(node,true,true,false);
		},
		selectNode:function(treeId,treeNode,addFlag){
			var treeObj =getZTreeObj(treeId);
			treeObj.selectNode(treeNode,addFlag);
		},
		getCheckedNodes:function(treeId){
			var treeObj =getZTreeObj(treeId);
			return treeObj.getCheckedNodes(true);
		},
		getSelectedNodes:function(treeId){
			var treeObj =getZTreeObj(treeId);
			return treeObj.getSelectedNodes();
		},
		getCheckedNodeIds:function(treeId){
			var treeObj =getZTreeObj(treeId); 
			var nodes = treeObj.getCheckedNodes(true);
			return buildNodeIds(nodes);
		},
		getCheckedNodeNames:function(treeId){
			var treeObj =getZTreeObj(treeId); 
			var nodes = treeObj.getCheckedNodes(true);
			return buildNodeNames(nodes);
		},
		getSelectedNodeIds:function(treeId){
			var treeObj =getZTreeObj(treeId);
			var nodes = treeObj.getSelectedNodes();
			return buildNodeIds(nodes);
		},
		expandNodeByLevel:function(treeId,level){
			var treeObj =getZTreeObj(treeId);
			var nodes = treeObj.getNodesByFilter(function(node){
				return node.level < level;
			});
			$(nodes).each(function() {
				treeObj.expandNode(this, true, false);
			});
		},
		//显示下拉选择树
		showDropdownTree:function(treeId) {
			var nameInput = $("#" + treeId + "_name");
//			nameInput.validateTheField();
			//根据隐藏域中已选择的值回显树
			this.checkAllNodes(treeId,false);
			var values = $("#" + treeId + "_value").val();
			var valueArray = values.split(",");
			for(var i=0;i<valueArray.length;i++){
				if(valueArray[i]=="")continue;
				this.checkNodeWithId(treeId,valueArray[i]);
			}
			//显示下拉选择树
			var cityOffset = $("#" + treeId + "_name").position();
			$("#" + treeId+"Background").css( {
				left : cityOffset.left + "px",
				top : cityOffset.top + nameInput.outerHeight() + "px"
			}).slideDown("fast");
		},
		//隐藏下拉选择树
		hideDropdownTree:function(treeId) {
			var nameInput = $("#" + treeId + "_name");
			var valueInput = $("#" + treeId + "_value");
			nameInput.val("");
			valueInput.val("");
			nameInput.prev("label").html("");
			nameInput.val(this.getCheckedNodeNames(treeId));
			valueInput.val(this.getCheckedNodeIds(treeId));
//			//validationEngine(cityObj);
			$("#" + treeId + "Background").fadeOut("fast");
//			nameInput.validateTheField();
		}
	};
	
	function getZTreeObj(treeId){
		return $.fn.zTree.getZTreeObj(treeId);
	}
	
	function buildNodeIds(nodes){
		if(!nodes || nodes.length==0){
			return;
		}
		var ids = "";
		$(nodes).each(function() {
			ids +=this.id+",";
		});
		return ids.substring(0,ids.length-1);
	}
	
	function buildNodeNames(nodes){
		if(!nodes || nodes.length==0){
			return;
		}
		var names = "";
		$(nodes).each(function() {
			names +=this.name+",";
		});
		return names.substring(0,names.length-1);
	}
})(jQuery);


