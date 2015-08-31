/**
 * 把page分页包装为jquery插件，调用插件中的方法。
 */
(function($){
	$.hz.page = {
		init:function(domId,settings){
			var dataUrl = $("#"+settings.formId).attr("action");
			if(!dataUrl || dataUrl.trim()=="")return false;
			var urlSplit = dataUrl.indexOf('?')==-1?"?":"&";
			var pageSettings = {type:"normal",showSwitchSize:false,dataUrl:dataUrl,urlSplit:urlSplit};
			$.extend(pageSettings, settings);
			setCacheSetting(domId,pageSettings);
			$("#"+domId).addClass("pageBar");
			if(renders[pageSettings.type]){
				renders[pageSettings.type].init(domId,pageSettings);
			}
			return true;
		},
		goUrl:function(url){
			if(!url)return;
			window.location = url;
		},
		gotoFirst:function(pageBarId){
			this.gotoPage(pageBarId,1);
		},
		gotoLast:function(pageBarId){
			var settings = getCacheSetting(pageBarId);
			this.gotoPage(pageBarId,settings.pageInfo.totalPages);
		},
		gotoPrevious:function(pageBarId){
			var settings = getCacheSetting(pageBarId);
			this.gotoPage(pageBarId,settings.pageInfo.currentPage-1);
		},
		gotoNext:function(pageBarId){
			var settings = getCacheSetting(pageBarId);
			this.gotoPage(pageBarId,settings.pageInfo.currentPage+1);
		},
		gotoPage:function(pageBarId,pageNo){
			var settings = getCacheSetting(pageBarId);
			var containerId = settings.containerId;
			var formId = settings.formId;
			var url = settings.dataUrl+settings.urlSplit+"page.pageSize="+settings.pageInfo.pageSize+"&page.pageNo="+pageNo;
			var params={};
			paramsStr=$("#"+formId).serializeArray();
			if(paramsStr!=""){
				$.each(paramsStr, function(i, field){
					params[field.name] = field.value;
				});
			}
			
			if(containerId!=null&&containerId!=""){
				$('#'+containerId).load(url, params);
			}else{
				this.goUrl(url);
			}
		},
		jumpPage:function(pageBarId){
			var settings = getCacheSetting(pageBarId);
			var jumpPageNo = $("#pn_"+pageBarId).val();
			if(!/^[0-9]*[1-9][0-9]*$/.test(jumpPageNo))return;
			if(jumpPageNo<1){this.gotoFirst(pageBarId);return;}
			if(jumpPageNo>settings.pageInfo.totalPages){this.gotoLast(pageBarId);;return;}
			this.gotoPage(pageBarId,jumpPageNo);
		},
		changePageSize:function(pageBarId,size){
			var settings = getCacheSetting(pageBarId);
			settings.pageInfo.pageSize = size;
			setCacheSetting(pageBarId,settings);
			this.gotoFirst(pageBarId);
		},
		getPageSize:function(pageBarId){
			var settings = getCacheSetting(pageBarId);
			if(!settings) return 0;
			return settings.pageInfo.pageSize;
		},
		getCurrentPage:function(pageBarId){
			var settings = getCacheSetting(pageBarId);
			if(!settings) return 0;
			return settings.pageInfo.currentPage;
		},
		refresh:function(pageBarId){
			this.gotoPage(pageBarId,1);
		}
	};
	
	function setCacheSetting(pageBarId,settings){
		$("#"+pageBarId).data("settings",settings);
	}
	
	function getCacheSetting(pageBarId){
		return $("#"+pageBarId).data("settings");
	}
	
	function buildNumberArray(sourceArray){
		var targetArray = [];
		var filterStr="";
		$.each(sourceArray,function(){
			var obj = parseInt(this);
			if(!isNaN(obj)&&filterStr.indexOf(""+obj)<0){
				filterStr+=obj;
				targetArray.push(obj);
			}
		});
		return targetArray;
	}
	
	var renders = {
		normal:{
			init:function(domId,settings){
				var pageBarId = domId;
				var pageInfo = settings.pageInfo;
				var pageSize = pageInfo.pageSize;
				var showSwitchSize = settings.changePageSize;
				
				var page_content = [];
				
				page_content.push('<table width="100%" cellspacing="0" cellpadding="0" border="0">');
				page_content.push('	<tbody><tr><td class="w20">&nbsp;</td>');
				page_content.push('	<td align="left" nowrap="nowrap">'+$.i18n.prop('pagebar.totalRecords',pageInfo.totalRecords)+'</td>');
				if(showSwitchSize){
					var changePageDataNumberArray;
					if(settings.changePageSizeNumber && typeof(settings.changePageSizeNumber)=='string'){
						var changePageSizeNumber = buildNumberArray(settings.changePageSizeNumber.split(","));
						changePageDataNumberArray = changePageSizeNumber.length>1?changePageSizeNumber:buildNumberArray([10,20,50]);
					}else{
						changePageDataNumberArray = buildNumberArray([10,20,50]);
					}
					changePageDataNumberArray = changePageDataNumberArray.sort(function(a, b){return (a > b) ? 1 : -1});
					
					page_content.push('	<td class="w70" nowrap="nowrap">'+$.i18n.prop('pagebar.pageShow')+'</td>');
					page_content.push('	<td class="w100" nowrap="nowrap"><ul class="pageNum">');
					$.each(changePageDataNumberArray,function(i,size){
						var sizeClass = (i==0)?"top":(i+1==changePageDataNumberArray.length)?"bottom":"center";
						page_content.push('	<li class="'+sizeClass+'"><p '+(pageSize==size?'class="select"':'')+'><a href="javascript:void(0);" onclick="$.hz.page.changePageSize(\''+pageBarId+'\','+size+');">'+size+'</a></p></li>');
					});
					page_content.push('	</ul></td>');
				}
				page_content.push('	<td class="w20">&nbsp;</td>');
				page_content.push('	<td class="w70 tc" nowrap="nowrap">');
				page_content.push(pageInfo.isFirstPage?'<span class="gray">'+$.i18n.prop('pagebar.home')+'</span>':'<a href="javascript:void(0);" onclick="$.hz.page.gotoFirst(\''+pageBarId+'\');return false;">'+$.i18n.prop('pagebar.home')+'</a></td>');
				page_content.push('	</td>');
				page_content.push('	<td class="w70 tc" nowrap="nowrap">');
				page_content.push(!pageInfo.isHavePrePage?'<span class="gray">'+$.i18n.prop('pagebar.previous')+'</span>':'<a href="javascript:void(0);" onclick="$.hz.page.gotoPrevious(\''+pageBarId+'\');return false;">'+$.i18n.prop('pagebar.previous')+'</a></td>');
				page_content.push('	</td>');		
				page_content.push('	<td class="w70 tc" nowrap="nowrap">');
				page_content.push(!pageInfo.isHaveNextPage?'<span class="gray">'+$.i18n.prop('pagebar.next')+'</span>':'<a href="javascript:void(0);" onclick="$.hz.page.gotoNext(\''+pageBarId+'\');return false;">'+$.i18n.prop('pagebar.next')+'</a></td>');
				page_content.push('	</td>');		
				page_content.push('	<td class="w70 tc" nowrap="nowrap">');
				page_content.push(pageInfo.isLastPage?'<span class="gray">'+$.i18n.prop('pagebar.last')+'</span>':'<a href="javascript:void(0);" onclick="$.hz.page.gotoLast(\''+pageBarId+'\');return false;">'+$.i18n.prop('pagebar.last')+'</a></td>');			
				page_content.push('	</td>');
				page_content.push('	<td class="w20">&nbsp;</td>');
				page_content.push('	<td class="w170" nowrap="nowrap"><span style="padding-left: 6px;">'+$.i18n.prop('pagebar.current')+'</span><input type="text" id="pn_'+pageBarId+'" size="2" value="'+pageInfo.currentPage+'" class="page-num" onkeydown="if(event.keyCode==13){$.hz.page.jumpPage(\''+pageBarId+'\');return false;}" value="'+pageInfo.currentPage+'">');
				page_content.push('	<span style="padding-right: 6px;">/'+$.i18n.prop('pagebar.totalPage',pageInfo.totalPages)+'</span></td>');
				page_content.push('	<td class="w20">&nbsp;</td>');
				page_content.push('	</tr></tbody></table>');		
				$("#"+pageBarId).append(page_content.join(""));
			}
		},
		simple:{
			init:function(domId,settings){
				var pageBarId = domId;
				var pageInfo = settings.pageInfo;
				var pageSize = pageInfo.pageSize;
				var showSwitchSize = settings.changePageSize;
				
				var page_content = [];
				
				page_content.push('<table width="100%" cellspacing="0" cellpadding="0" border="0">');
				page_content.push('	<tbody><tr>');
				if(showSwitchSize){
					var changePageDataNumberArray;
					if(settings.changePageSizeNumber && typeof(settings.changePageSizeNumber)=='string'){
						var changePageSizeNumber = buildNumberArray(settings.changePageSizeNumber.split(","));
						changePageDataNumberArray = changePageSizeNumber.length>1?changePageSizeNumber:buildNumberArray([10,20,50]);
					}else{
						changePageDataNumberArray = buildNumberArray([10,20,50]);
					}
					changePageDataNumberArray = changePageDataNumberArray.sort(function(a, b){return (a > b) ? 1 : -1});
					
					page_content.push('	<td class="w70" nowrap="nowrap">'+$.i18n.prop('pagebar.pageShow')+'</td>');
					page_content.push('	<td class="w100" nowrap="nowrap"><ul class="pageNum">');
					$.each(changePageDataNumberArray,function(i,size){
						var sizeClass = (i==0)?"top":(i+1==changePageDataNumberArray.length)?"bottom":"center";
						page_content.push('	<li class="'+sizeClass+'"><p '+(pageSize==size?'class="select"':'')+'><a href="javascript:void(0);" onclick="$.hz.page.changePageSize(\''+pageBarId+'\','+size+');">'+size+'</a></p></li>');
					});
					page_content.push('	</ul></td>');
				}
				page_content.push('	<td class="w20">&nbsp;</td>');
				page_content.push('	<td class="w70 tc" nowrap="nowrap">');
				page_content.push(!pageInfo.isHavePrePage?'<span class="gray">'+$.i18n.prop('pagebar.previous')+'</span>':'<a href="javascript:void(0);" onclick="$.hz.page.gotoPrevious(\''+pageBarId+'\');return false;">'+$.i18n.prop('pagebar.previous')+'</a></td>');
				page_content.push('	</td>');		
				page_content.push('	<td class="w170" nowrap="nowrap"><input type="text" id="pn_'+pageBarId+'" size="2" value="'+pageInfo.currentPage+'" class="page-num" onkeydown="if(event.keyCode==13){$.hz.page.jumpPage(\''+pageBarId+'\');return false;}" value="'+pageInfo.currentPage+'">');
				page_content.push('	<span style="padding-right: 6px;">/'+pageInfo.totalPages+'</span></td>');
				page_content.push('	<td class="w70 tc" nowrap="nowrap">');
				page_content.push(!pageInfo.isHaveNextPage?'<span class="gray">'+$.i18n.prop('pagebar.next')+'</span>':'<a href="javascript:void(0);" onclick="$.hz.page.gotoNext(\''+pageBarId+'\');return false;">'+$.i18n.prop('pagebar.next')+'</a></td>');
				page_content.push('	</td>');		
				page_content.push('	<td class="w20">&nbsp;</td>');
				page_content.push('	</tr></tbody></table>');		
				
				$("#"+pageBarId).append(page_content.join(""));
			}
		}
	};
})(jQuery);
$.i18n.properties({
    name:['pagebar'],
    path: com.ue.global.path+'/sys/i18n/ajaxGetI18nValues.do?bundleName=', 
    mode:'map',
    callback: function() {
    	
    }
});
