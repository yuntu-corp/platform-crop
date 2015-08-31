(function($) {
	
	var CLASS_SWFUPLOAD_CONTAINERS="swfupload_containers";
	var SWFUPLOAD_PARAM = "swfupload_param";
	
	$.hz.swfupload = {
		init:function(objId,param){
			var $obj = $("#"+objId);
			if($obj.size() == 0){
				return;
			}
			if(!param){
				param = {};
			}
			$obj.addClass(CLASS_SWFUPLOAD_CONTAINERS);
			var configCode = param.configCode || "default";
			var uploadParam = {};
			uploadParam.finishCallback = param.finishCallback;
			uploadParam.deleteCallback = param.deleteCallback;
			uploadParam.divId = $.uuid();
			uploadParam.required = param.required;
			uploadParam.classFieldName = param.classFieldName;
			uploadParam.indexNum = param.indexNum || 0;
			$.ajax({
				url:com.ue.global.path + "/sys/attachmentConfig/ajaxGetConfig.do",
				type:"post",
				dataType:"json",
				async:false,
				data:{
					configCode:configCode
				},
				success:function(result){
					uploadParam.tip = result.allowTypeStr + result.limitFileExtentionStr + result.maxCountStr + result.maxFileSize;
					uploadParam.filterArr = result.filterArr;
					uploadParam.maxFileSize = result.maxFileSize;
					uploadParam.maxFileCount = result.maxFileCount;
					uploadParam.limitFileExtention = result.limitFileExtention;
					uploadParam.configId = result.configId;
				}
			});
			
			//根据idOwner获取已保存的附件
			if(param.idOwner && param.idOwner != ""){
				$.ajax({
					url:com.ue.global.path + "/sys/attachment/ajaxGetAttachment.do",
					type:"post",
					dataType:"json",
					async:false,
					data:{
						idOwner:param.idOwner
					},
					success:function(result){
						uploadParam.existAttachments = result;
					}
				});
			}
			loadUploadFlash.apply($obj ,[uploadParam]);
		},
		removeUpFile:function(objId,ufId){
			var $obj = $("#"+objId);
			removeUploadFile.apply($obj,[ufId]);
		},
		validateAttachment:function(objId){//验证接口
			var $obj = $("#"+objId);
			var result = true;
			$obj.each(function(){
				var flag = checkRequired.apply($(this));
				if(!flag){
					result = false;
				}
				var param = $(this).data(SWFUPLOAD_PARAM);
				var key = param.divId;
				var vals = param.files;
				var attIds = "";
				$("#" + key + "HiddenValIpt").val("");
				for(var j = 0;j < vals.values().length; j++){
					var uf = vals.values()[j];
					if(uf.status != UPLOADFILE_STATUS_UPLOADED){
						if(uf.proc == 100){
							alert($.i18n.prop('upload_js_processing'));
						}else{
							alert($.i18n.prop('upload_js_uploading'));
						}
						canCommit = false;
						break;
					}else{
						attIds += uf.attachmentId + ",";
					}
				}
				$("#" + key + "HiddenValIpt").val(attIds);
				
			});
			return result;
		},
		deleteExists:function(objId,attachmentId){//删除已经提交过的附件
			var $obj = $("#"+objId);
			var param = $obj.data(SWFUPLOAD_PARAM);
			var divId = param.divId;
			//删除已有附件
			var oldIds = $("#" + divId + "DelIds").val();
			$("#" + divId + "DelIds").val(oldIds + "," + attachmentId);
			$("#" + attachmentId).remove();
			
			//设置oldFileArea区域
			var exist = $("#" + divId + "ExistUl").find("li").size();
			if(exist == 0){
				$("#" + divId + "OldFileArea").hide();
			}
			resetSwfStats.apply($obj);
			
			//设置上传按钮状态
			resetUploadButtonStatus.apply($obj);
			
			//回调删除
			if(param.deleteCallback){
				var obj = {};
				obj.attachmentId = attachmentId;
				obj.divId = divId;
				obj.isSavedFile = true;
				eval(param.deleteCallback + "(obj)");
			}
			//
			checkRequired.apply($obj);
		},
		/**
		 * 添加行中提交表单时调用
		 * @param {Object} name
		 * @param {Object} delNames
		 * @param {Object} elementId
		 */
		setAttachmentsNames : function(name,delNames,elementId){
			var $ele = $("#" + elementId);
			if(!elementId){
				$ele = $("body");
			}
			var vals = $("."+CLASS_SWFUPLOAD_CONTAINERS,$ele);
			$.each(vals,function(index){
				$(this).find("input[id*='HiddenValIpt']").prop("name",name.replace("{0}",index));
				$(this).find("input[id*='DelIds']").prop("name",delNames.replace("{0}",index));
			});
		}
	};
	//等待上传
	var UPLOADFILE_STATUS_WAIT = 0;
	//正在上传
	var UPLOADFILE_STATUS_UPLOADING = 1;
	//已经上传
	var UPLOADFILE_STATUS_UPLOADED = 2;
	
	function loadUploadFlash(param){
		var html = $.nano(getUploadUITemp(),{divId:param.divId,configId:param.configId,classFieldName:param.classFieldName,indexNum:param.indexNum,uploadTip:param.tip});
		$(this).append(html);
		var id = $(this).attr("id");
		if(param.existAttachments && param.existAttachments.length>0){
			$("#" + param.divId + "OldFileArea").show();
			var htmlLi = "";
			$(param.existAttachments).each(function(){
				var uf = this;
				htmlLi += "<li id='"+ uf.id + "' style='float:left;padding-right:10px'>";
				htmlLi += "	<img src='" + com.ue.global.path + "/sys/attachment/showPic.do?downloadToken=" + uf.id + "6210e537ab7425ada8f8cd2430ccb36f' width='100px' height='100px' alt='' />&nbsp";
				//htmlLi += "	<span>" + uf.name + "</span>&nbsp";
				htmlLi += "	<span></span>&nbsp";
				htmlLi += "	<span onclick='$.hz.swfupload.deleteExists(\"" + id + "\",\"" + uf.id + "\");' class='green hand'>" + $.i18n.prop('upload_delete') + "</span>";
				htmlLi += "</li>";
			});
			$("#" + param.divId + "ExistUl").append(htmlLi);
		}
		var that = this;
		var settings = {
				flash_url : com.ue.global.path + "/platform/component/swfupload/swf/swfupload.swf",
				upload_url: com.ue.global.path + "/sys/attachment/saveUploadFile.do;jsessionid=" + com.ue.global.jsessionid,
				post_params: {},
				file_post_name : "filedata",
				file_size_limit : param.maxFileSize + "Mb",
				file_types : param.filterArr == "" ? "*.*" : param.filterArr.split(":")[0],
				file_types_description : param.filterArr == "" ? "All Files" : param.filterArr.split(":")[1],
				file_upload_limit : param.maxFileCount,
				file_queue_limit : 0,
//				custom_settings : {
//					progressTarget : "fsUploadProgress",
//					cancelButtonId : "btnCancel"
//				},
				debug: false,
								
				// Button settings
				button_window_mode : "opaque",//防止flash在最上层
				button_image_url: com.ue.global.path + "/platform/component/swfupload/images/XPButtonUploadText_61x22.png",
				button_width: "74",
				button_height: "28",
				//button_placeholder_id: "spanButtonPlaceHolder",
				button_text: $.i18n.prop('upload_add'),
				button_text_style: ".theFont { font-size: 20; }",
				button_text_left_padding: 10,
				button_text_top_padding: 5,
				
				// The event handler functions are defined in handlers.js
				file_dialog_start_handler : function(){},
				file_queued_handler : function(file){
					var checkResult = checkFile.apply(that,[file]);
					if(!checkResult){
						this.stopUpload();
						this.cancelUpload(file.id,false);
						this.fileQueueError(file,SWFUpload.QUEUE_ERROR.INVALID_FILETYPE,'');
						this.startUpload();
					}else{
						addNewFile.apply(that,[file]);
					}
				},
				swfupload_loaded_handler : function(){resetSwfStats.apply(that);resetUploadButtonStatus.apply(that);},
				file_queue_error_handler : function(file,errorCode,message){return queueError.apply(that,[file,errorCode,message]);},
				file_dialog_complete_handler : function(numFilesSelected,numFilesQueued){this.startUpload();},
				upload_start_handler : function(file){return true;},
				upload_progress_handler : function(file,bytesLoaded,bytesTotal){setFileUploadProgress.apply(that,[bytesLoaded,file.id]);},
				upload_error_handler : function(file, errorCode, message){uploadError.apply(that,[file,errorCode,message]);},
				upload_success_handler : function(file,serverData){setAttachmentId.apply(that,[file.id,serverData]);},
				upload_complete_handler : function(){},
				queue_complete_handler : function(){}	// Queue plugin event
		};
		settings.button_placeholder_id = param.divId + "Flash";
		var swfObj = new SWFUpload(settings);
		param.swfObj = swfObj;
		param.files = new Hashtable();
		$(this).data(SWFUPLOAD_PARAM,param);
	}
	
	function setAttachmentId(ufId,attachmentId){
		var param = $(this).data(SWFUPLOAD_PARAM);
		var divId = param.divId;
		//文件可能已经删除了
		if(param.files.get(ufId) == null){
			return;
		}
		var uf = param.files.get(ufId);
		uf.attachmentId = attachmentId;
		uf.status = UPLOADFILE_STATUS_UPLOADED;
		$("#" + divId + "Pb"+ ufId).html("");
		$("#" + divId + "Img"+ ufId)[0].src = com.ue.global.path + "/sys/attachment/showTempPic.do?attachmentId=" + attachmentId;
		$("#" + divId + "SumPb").progressBar(getMathRound((getUploadedSize.apply(this,[0])/getSumSize.apply(this)*100),2),{animated:false});
		if(param.finishCallback){
			var obj = {};
			obj.picUrl = com.ue.global.path + "/sys/attachment/showTempPic.do?attachmentId=" + attachmentId;
			obj.videoUrl = com.ue.global.path + "/sys/attachment/showTempVideo.do?attachmentId=" + attachmentId;
			obj.attachmentId = attachmentId;
			obj.divId = divId;
			obj.fileId = ufId;
			eval(param.finishCallback + "(obj)");
		}
	}
	
	function addNewFile(file){
		var param = $(this).data(SWFUPLOAD_PARAM);
		var divId = param.divId;
		var id = $(this).attr("id");
		if(!id){
			id = $.uuid();
			$(this).attr("id",id);
		}
		var uf = {id:file.id,name:file.name,status:UPLOADFILE_STATUS_WAIT,size:file.size};
		//显示总进度
		$("#" + divId + "Sum").show();
		$("#" + divId).parent(".uploadStyle").find(".newFileArea").show();
		//添加到缓存
		param.files.put(uf.id,uf);
		//添加单个文件
		var html = "";
		html += "<li id='" + divId + "Li" + uf.id + "' style='float:left;padding-right:10px'>";
		html += "	<img id='" + divId + "Img" + uf.id + "' width='100px' height='100px' src='" + com.ue.global.path + "/platform/component/swfupload/images/0.gif' alt='' />&nbsp";
		//html += "	<span>" + uf.name + "</span>&nbsp";
		html += "	<span id='" + divId + "Pb" + uf.id + "'></span>&nbsp";
		html += "	<span onclick='$.hz.swfupload.removeUpFile(\"" + id + "\",\"" + uf.id + "\");' class='green hand'>" + $.i18n.prop('upload_delete') + "</span>";
		html += "</li>";
		$("#" + divId + "Ul").html($("#" + divId + "Ul").html() + html);
		
		//刷新总大小
		$("#" + divId + "SumSize").html(getSumSizeShow.apply(this));
		
		//设置上传按钮状态
		resetUploadButtonStatus.apply(this);
		
		//取消必填提示
		checkRequired.apply(this);
	}
	
	function removeUploadFile(ufId){
		var param = $(this).data(SWFUPLOAD_PARAM);
		var divId = param.divId;
		var uf = param.files.get(ufId);
		var swfObj = param.swfObj;
		//删除等待上传文件
		if(uf.status == UPLOADFILE_STATUS_WAIT){
			swfObj.cancelUpload(ufId,false);
		}
		//删除正在上传	
		if(uf.status == UPLOADFILE_STATUS_UPLOADING){
			
		}
		//删除已经上传的
		if(uf.status == UPLOADFILE_STATUS_UPLOADED){
			$.post(com.ue.global.path + "/sys/attachment/ajaxDeleteUploadFile.do", {
					attachmentId : uf.attachmentId
				}, 
				function(data) {
	//				alert(data + "已删除");
				},
				"text"
			);
		}
		//删除页面显示
		$("#" + divId + "Li" + uf.id).remove();
		//删除缓存
		param.files.remove(ufId);
		
		//重新计算总进度
		$("#" + divId + "SumSize").html(getSumSizeShow.apply(this));
		$("#" + divId + "UploadedSize").html(getUploadedSizeShow.apply(this,[0]));
		$("#" + divId + "SumPb").progressBar(getMathRound((getUploadedSize.apply(this,[0])/getSumSize.apply(this)*100),2));
		
		if(param.files.size() == 0){
			$("#" + divId + "SumSize").html("");
			$("#" + divId + "UploadedSize").html("");
			$("#" + divId + "Sum").hide();
			$("#" + divId + "NewFileArea").hide();
		}
		
		//设置上传按钮状态
		resetUploadButtonStatus.apply(this);
		
		//设置swfupload中的状态
		resetSwfStats.apply(this);
		
		//回调删除
		if(param.deleteCallback){
			var obj = {};
			obj.fileId = ufId;
			obj.divId = divId;
			obj.isSavedFile = false;
			obj.status = uf.status;
			eval(param.deleteCallback + "(obj)");
		}
		//
		checkRequired.apply(this);
	}
	
	function getSumSize(){
		var param = $(this).data(SWFUPLOAD_PARAM);
		var arr = param.files.values();
		var sumSize = 0;
		for(var i = 0;i < arr.length;i++){
			sumSize += arr[i].size;
		}
		return sumSize;
	}
	
	function getSumSizeShow(){
		var sumSize = getSumSize.apply(this);
		if(sumSize < 1024){
			return sumSize + "B";
		}else if(sumSize < 1024*1024){
			return getMathRound(sumSize/(1024),2)  + "K";
		}else if(sumSize < 1024*1024*1024){
			return getMathRound(sumSize/(1024*1024),2) + "M";
		}else{
			return getMathRound(sumSize/(1024*1024*1024),2) + "G";
		}
	}
	
	function checkRequired(){
		var param = $(this).data(SWFUPLOAD_PARAM);
		var canCommit = true;
		var requiredFlag = param.required;
		if(requiredFlag){
			var divId = param.divId;
			var swfupload = $("#" + divId + "Btn").find(".swfupload");
			if(getExistFileCount.apply(this) == 0){//没有附件
				canCommit = false;
				$.hz.validate.showPrompt(swfupload,$.i18n.prop('upload_js_required'));
			}else{//已有附件
				$.hz.validate.closePrompt(swfupload);
			}
		}
		return canCommit;
	}
	
	function checkFile(file){
		var param = $(this).data(SWFUPLOAD_PARAM);
		var limitFileExt = param.limitFileExtention;
		if(limitFileExt == "" || file.type == ""){
			return true;
		}
		if(limitFileExt.substring(limitFileExt.length - 1,limitFileExt.length) != ";"){
			limitFileExt = ";" + limitFileExt + ";";
		}
		var type = file.type.substring(1,file.type.length);
		if(limitFileExt.indexOf(";" + type + ";") != -1){
			return false;
		}
		return true;
	}
	
	function setFileUploadProgress(bytesLoaded,ufId){
		var param = $(this).data(SWFUPLOAD_PARAM);
		var divId = param.divId;
		//修改缓存状态
		var uf = param.files.get(ufId);
		uf.status = UPLOADFILE_STATUS_UPLOADING;
	
		var proc = getMathRound((bytesLoaded / uf.size * 100),2);
		//更新单个文件进度
		$("#" + divId + "Pb"+ ufId).html(proc + "%");
		uf.proc = proc;
		
		//更新单个文件状态图片
		if($("#" + divId + "Img"+ ufId)[0].src.toString().indexOf("1.gif") == -1){
			$("#" + divId + "Img"+ ufId)[0].src = com.ue.global.path + "/platform/component/swfupload/images/1.gif";
		}
	
		//设置已上传大小
		$("#" + divId + "UploadedSize").html(getUploadedSizeShow.apply(this,[bytesLoaded]));
		
		//设置进度条
		var totalProc = getMathRound((getUploadedSize.apply(this,[bytesLoaded])/getSumSize.apply(this) * 100),2);
		$("#" + divId + "SumPb").progressBar(totalProc,{animated:false});
	}
	
	function getUploadedSizeShow(bytesLoaded){
		var loadedSize = getUploadedSize.apply(this,[bytesLoaded]);
		if(loadedSize < 1024){
			return loadedSize + "B";
		}else if(loadedSize < 1024*1024){
			return getMathRound(loadedSize/(1024),2)  + "K";
		}else if(loadedSize < 1024*1024*1024){
			return getMathRound(loadedSize/(1024*1024),2) + "M";
		}else if(loadedSize < 1024*1024*1024*1024){
			return getMathRound(loadedSize/(1024*1024*1024),2) + "G";
		}
	}
	
	function getUploadedSize(bytesLoaded){
		var param = $(this).data(SWFUPLOAD_PARAM);
		var arr = param.files.values();
		var loadedSize = 0;
		for(var i = 0;i < arr.length;i++){
			if(arr[i].status == UPLOADFILE_STATUS_UPLOADED){
				loadedSize += arr[i].size;
			}
		}
		loadedSize += bytesLoaded;
		return loadedSize;
	}
	
	function getMathRound(num,pos){
		return num.toFixed(pos);
	}
	
	function getExistFileCount(){
		var param = $(this).data(SWFUPLOAD_PARAM);
		var divId = param.divId;
		return param.files.size() + $("#" + divId + "ExistUl").find("li").size();
	}
	
	function uploadError(file,errorCode,message){
		//alert(message);
	}
	
	function queueError(file,errorCode,message){
		var param = $(this).data(SWFUPLOAD_PARAM);
		if (errorCode === SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED) {
			alert($.i18n.prop('upload_js_fileCountAllow',param.maxFileCount));
			return;
		}
		if (errorCode === SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT){
			alert($.i18n.prop('upload_js_fileSizeNotAllow',param.maxFileSize));
			return;
		}
		if (errorCode === SWFUpload.QUEUE_ERROR.INVALID_FILETYPE){
			alert($.i18n.prop('upload_js_notAllowFileType',param.limitFileExtention));
			return;
		}
	}
	
	function resetUploadButtonStatus(){
		var param = $(this).data(SWFUPLOAD_PARAM);
		var maxCount = param.maxFileCount;
		if(maxCount == -1){
			return;
		}
		var curCount = getExistFileCount.apply(this);
		
		if(curCount >= maxCount){
			//禁止
			param.swfObj.setButtonDisabled(true);
		}else{
			//可以
			param.swfObj.setButtonDisabled(false);
		}
	}
	
	function resetSwfStats(){
		var swfObj = $(this).data(SWFUPLOAD_PARAM).swfObj;
		var stat = swfObj.getStats();
		stat.successful_uploads = getExistFileCount.apply(this);
		swfObj.setStats(stat);
	}
	
	function getUploadUITemp(){
		var html = "";
		html += "<input type='hidden' name='attachmentList[{indexNum}]' value='{classFieldName}'/>";
		html += "<input type='hidden' name='{classFieldName}' value='' id='{divId}HiddenValIpt'/>";
		html += "<input type='hidden' name='{classFieldName}DelIds' value='' id='{divId}DelIds'/>";
		html += "<input type='hidden' name='{classFieldName}ConfigId' value='{configId}' id='{divId}ConfigId'/>";
		html += "<div class='uploadStyle'>";
		html += "	<div class='uploadBtn' id='{divId}Btn'>";
		html += "		<div id='{divId}Flash'></div>";
		html += "		<span style='font-size:12px;' id='{divId}tip'>{uploadTip}</span>";
		html += "	</div>";
		html += "	<div class='newFileArea' style='display: none;' id='{divId}NewFileArea'><ul id='{divId}Ul'></ul></div>";
		html += "	<div id='{divId}' class='progressArea'>";
		html += "		<div id='{divId}Sum' style='display: none;'>";
		html += "			<span>" + $.i18n.prop('upload_uploadSumProgress') + "</span>";
		html += "			<span id='{divId}SumPb'></span>";
		html += "			<span>" + $.i18n.prop('upload_hasUpload') + "<em id='{divId}UploadedSize'></em>&nbsp" + $.i18n.prop('upload_sumSize') + "<em id='{divId}SumSize'></em></span>";
		html += "		</div>";
		html += "	</div>";
		html += "	<div class='oldFileArea' id='{divId}OldFileArea' style='display: none;'>";
		html += "		<span class='ml10 pt5 pl10 clearfix'>" + $.i18n.prop('upload_hasAttachment') + "</span>";
		html += "		<ul id='{divId}ExistUl'></ul>";
		html += "	</div>";
		html += "</div>";
		return html;
	}
})(jQuery);