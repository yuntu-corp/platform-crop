/**
 * 把ueditor包装为jquery插件，调用插件中的方法。
 */
(function($){
	$.hz.ueditor = {
		init:function(domId,param){
			var defaultToolbar = ['undo', 'redo', '|','bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain',
			                      '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc', '|','rowspacingtop', 'rowspacingbottom', 'lineheight', '|','customstyle', 'paragraph', 
			                      'fontfamily', 'fontsize', '|','directionalityltr', 'directionalityrtl', 'indent', '|','justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|', 'touppercase', 'tolowercase', '|',
			                      'link', 'unlink', 'anchor', '|', 'imagenone', 'imageleft', 'imageright', 'imagecenter', '|','simpleupload', 'insertimage', 'insertframe','pagebreak', 'template', 'background', '|','horizontal', 
			                      'date', 'time', 'spechars', 'wordimage', '|','inserttable', 'deletetable', 'insertparagraphbeforetable', 'insertrow', 'deleterow', 'insertcol', 'deletecol', 'mergecells', 'mergeright', 'mergedown',
			                      'splittocells', 'splittorows', 'splittocols','|', 'searchreplace', 'help'];
			var simpleToolbar = ['undo', 'redo','bold', 'italic', 'underline', 'strikeThrough'];
			var defaults = {
				toolbar:[],//可以自定义toolbar，或指定mode
				name:"",//提交的name
				content:"",//内容
				zIndex:UEDITOR_CONFIG.zIndex,
				wordCount:false,//是否显示已输入字符个数
				autoHeightEnabled:false,
				simpleEnabled:false
			};
			var options =  $.extend(defaults, param);
			
			var toolbar = options.toolbar.length > 0 ? options.toolbar : (options.simpleEnabled ? simpleToolbar : defaultToolbar);
			
			var $obj = $("#"+domId);
			
			var minFrameHeight = 500;
			var style = $obj.attr("style");
			if(style && style.indexOf("height") != -1){
				var heightTemp = style.substring(style.indexOf("height:") + 7);
				minFrameHeight = heightTemp.substring(0, heightTemp.indexOf("px")).trim();
			}
			//手动合并name
			options.name = (options.name == "" ? $obj.attr("name") : options.name);
			
			//缓存domId
//			new UE.ui.Editor(opt);
			var editor = new UE.ui.Editor({
				toolbars:[toolbar],
				elementPathEnabled:false,
				textarea:options.name,
				autoHeightEnabled:options.autoHeightEnabled,
				wordCount:options.wordCount,
				zIndex:options.zIndex,
				minFrameHeight:minFrameHeight,
//				initialFrameWidth:options.initialFrameWidth,
//				initialFrameHeight:options.initialFrameHeight,
				initialContent:options.content
			});
			 editor.render(domId);
			//ueditor构建时把原来的dom删除了
			$("#"+domId).data("ueditor",editor);
			$("#"+domId).data("ueditor_option",options);
		},
		sync:function(domId,name){
			var $obj =  $("#"+domId);
			var editor = $obj.data("ueditor");
			editor.sync();
			if(name){
				var options = $obj.data("ueditor_option");
				$("textarea[name=" + options.name + "]").attr("name",name);
				options.name = name;//更新
			}
		},
		reset:function(domId){
			$("#"+domId).data("ueditor").reset();
		},
		getContent:function(domId){
			return $("#"+domId).data("ueditor").getContent();
		},
		setContent:function(domId,content){
			$("#"+domId).data("ueditor").setContent(content);
		},
		enable:function(domId){
			$("#"+domId).data("ueditor").enable();
		},
		disable:function (domId){
			$("#"+domId).data("ueditor").disable();
		},
		show:function (domId){
			$("#"+domId).data("ueditor").show();
		},
		hide:function (domId){
			$("#"+domId).data("ueditor").hide();
		},
		view:function(domId,content,cssClass,cssStyle){
			var ifrTemp = "<iframe src=\"javascript:void(0)\" onload=\"$.hz.ueditor.setUEditorViewIFrameHeight('{domId}','{id}fr')\" id=\"{id}fr\" width=\"95%\" height=\"1px\" frameborder=0 class='{cssClass}' style='{cssStyle}'></iframe>";
			var id = com.ue.common.uuid();
			var html = $.nano(ifrTemp,{domId:domId,id:id,cssClass:cssClass,cssStyle:cssStyle});
			this.html(html);
			var iframe = $("#" + id + "fr")[0];
			//添加内容
			iframe.contentDocument.write(content);
			iframe.contentDocument.close();
	        var iframeDocument = iframe.contentDocument || iframe.contentWindow.document;
	        var headElement = iframeDocument.getElementsByTagName("head")[0];
	        //添加样式
	        var styleEl = iframeDocument.createElement("style");
	        styleEl.type = "text/css";
	        var cssHtml = ".selectTdClass{background-color:#3399FF !important}";
	        cssHtml += "table{clear:both;margin-bottom:10px;border-collapse:collapse;word-break:break-all;}";
	        cssHtml += ".pagebreak{display:block;clear:both !important;cursor:default !important;width: 100% !important;margin:0;}";
	        cssHtml += ".view{padding:0;word-wrap:break-word;word-break:break-all;cursor:text;height:100%;}";
	        cssHtml += "li{clear:both}p{margin:5px 0;}";
	        cssHtml += "body{word-wrap:break-word;}";
	        if(styleEl.styleSheet){
		        styleEl.styleSheet.cssText = cssHtml;
	        }else{
				styleEl.innerHTML = cssHtml;
	        }
	        headElement.appendChild(styleEl);
	        
	        //设置高度
	        var h = Math.max(iframe.contentWindow.window.document.documentElement.scrollHeight, iframe.contentWindow.window.document.body.scrollHeight);
	        iframe.height = h + 20;
	        setTimeout(function(){
	        	h = Math.max(iframe.contentWindow.window.document.documentElement.scrollHeight, iframe.contentWindow.window.document.body.scrollHeight);
		        iframe.height = h + 20;
	        },2);
		},
		setUEditorViewIFrameHeight:function(domId,iframeId){
	    	var iframe = $("#" + iframeId)[0];
	    	setTimeout(function(){
	        	var h = Math.max(iframe.contentWindow.window.document.documentElement.scrollHeight, iframe.contentWindow.window.document.body.scrollHeight);
		        iframe.height = h;
	        },2);
		}
	};
	
})(jQuery);

