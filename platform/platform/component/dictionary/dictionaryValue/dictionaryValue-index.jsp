<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*,com.unitever.platform.component.dictionary.model.Dictionary" pageEncoding="UTF-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp"%>
<script type="text/javascript">
Namespace.register('com.ue.platform.module.dictvalueIndex');
com.ue.platform.module.dictvalueIndex={
	pageId:"${pageId}",
	type_general:"<%=Dictionary.DICT_KIND_GENERAL%>",
	type_tree:"<%=Dictionary.DICT_KIND_TREE%>",
	selectedTab:function(title){
		var selectedLi = $('li[container="${pageId}tab"].tabs-selected');
		if (selectedLi && selectedLi.length == 1){
			var tabAttr = $.data(selectedLi[0], 'tabs.tab');
			if($(tabAttr).attr("title")!=title){
				$('#${pageId}tab').tabs('select',title);
			}
		}
	},
	refresh:function(type,id){
		var space=this;
		if(type==this.type_general){
			this.selectedTab("普通字典");
			this.loadGeneralDictionaryList(id)
		}
		if(type==this.type_tree){
			this.selectedTab("树型字典");
			this.loadTreeDictionaryList(id)
		}
	},
	loadGeneralDictionaryList:function(id){
		var space=this;
		$("#${pageId}rightContent").html('');
		$.getJSON("${path}/sys/dictionaryValue/dictListDataWithGeneral", function(jsondata){
			$("#${pageId}dictListWithGeneral").html('');
			space.buildDictList(jsondata,"${pageId}dictListWithGeneral");
			if(id){space.toInfo(id);}
		});
	},
	loadTreeDictionaryList:function(id){
		var space=this;
		$("#${pageId}rightContent").html('');
		$.getJSON("${path}/sys/dictionaryValue/dictListDataWithTree", function(jsondata){
			$("#${pageId}dictListWithTree").html('');
			space.buildDictList(jsondata,"${pageId}dictListWithTree");
			if(id){space.toDictionaryValue(id);}
		});
	},
	buildDictList:function(jsondata,positionListId){
		$.each(jsondata,function(){
				var potision_li=[];
				potision_li.push("<li>");
				potision_li.push("<a onclick='com.ue.platform.module.dictvalueIndex.toDictionaryValue(\""+this.type+"\",\""+this.id+"\")' href='javascript:void(0);' class='nav_icon' id='${pageId}listNode_"+this.id+"'>");
				potision_li.push(this.name);
				potision_li.push("</a>");
				potision_li.push("</li>");
				$("#"+positionListId).append(potision_li.join(''));
		});
	},
	toDictionaryValue:function(type,dictId){
		<%--设置职务列表节点选中--%>
		$(".nav_icon").removeClass("current");
		$("#${pageId}listNode_"+dictId).addClass("current");
		<%--加载职务信息--%>
		var url="${path}/sys/dictionaryValue/list?dictId="+dictId;
		if(type==this.type_tree){
			url="${path}/sys/dictionaryValue/listTree?dictId="+dictId;
		}
		$("body").mask('正在加载数据……');
		$("#${pageId}rightContent").load(url,function(){
			$("body").unmask();
		});
	},
	toGeneralDictionaryValue:function(dictId){
		this.toDictionaryValue(this.type_general,dictId);
	},
	toTreeDictionaryValue:function(dictId){
		this.toDictionaryValue(this.type_tree,dictId);
	}
};

$(document).ready(function() {
	$('#${pageId}Content').layout({west__size:220,west__togglerLength_open:0,resizable:false});
	$('#${pageId}tab').tabs({
		onSelect: function(){
			var id = $(this).attr("id");
			var type = $(this).attr("type");
			com.ue.platform.module.dictvalueIndex.refresh(type);
		}
	});
});
</script>
		<div id="${pageId}Content" style="position: absolute; top: 0;left: 0;right: 0;bottom: 0;">
			<div class="ui-layout-center inLayout-center default-page ovauto" style="overflow-x:hidden" id="${pageId}rightContent">
			 
				
			</div>
			<div class="ui-layout-west inLayout-west">
				<div class="leftPanel">
					<div class="tabs-container noneTopStyle" id="${pageId}tab">
						<div id="${pageId}generalDict" title="普通字典" type="<%=Dictionary.DICT_KIND_GENERAL%>">
							<ul class="navMain" id="${pageId}dictListWithGeneral">
					
							</ul>	
						</div>
						<div id="${pageId}treeDict" title="树型字典" type="<%=Dictionary.DICT_KIND_TREE%>">
							<ul class="navMain" id="${pageId}dictListWithTree">
					
							</ul>	
						</div>
					</div>	
				</div>
			</div> 
		</div>
