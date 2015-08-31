Namespace.register('com.ue.print');
// TODO 整理接口
com.ue.print = {
	lodop : null,
	param : {
		pageName : "A4",
		orientation : 1,//1:纵向，固定,2:横向，固定3:纵向宽度固定,0:有操作者自行控制,
		pageWidth : 0,
		pageHeight : 0,
		top : "5mm",
		left : "0mm",
		width : "100%",
		height : "98%"
	},
	defaultParam : {
		pageName : "A4",
		orientation : 1,//1:纵向，固定,2:横向，固定3:纵向宽度固定,0:有操作者自行控制,
		pageWidth : 0,
		pageHeight : 0,
		top : "5mm",
		left : "0mm",
		width : "100%",
		height : "98%"
	},
	setParam : function(param){
		this.param = $.extend({}, this.param, param);
	},
	hasInit : false,
	getLodop : function(oOBJECT,oEMBED){
		/**************************
		  本函数根据浏览器类型决定采用哪个对象作为控件实例：
		  IE系列、IE内核系列的浏览器采用oOBJECT，
		  其它浏览器(Firefox系列、Chrome系列、Opera系列、Safari系列等)采用oEMBED。
		**************************/
	    var LODOP = oEMBED;
		try{
		     if (navigator.appVersion.indexOf("MSIE") >= 0 || navigator.userAgent.indexOf('Trident')>=0){
		    	 LODOP = oOBJECT;
		     }
		     
		     if ((LODOP == null) || (typeof(LODOP.VERSION) == "undefined") || LODOP.VERSION < "6.1.1.8"){
				this.showTips();
				return null;
		     }
		     
		     return LODOP; 
		}catch(err){
		     this.showTips();
		     return null; 
		}
	},
	showTips : function(){
		var file = "install_lodop32.exe";
		if (navigator.userAgent.indexOf('Win64') >= 0 || navigator.userAgent.indexOf('x64') >= 0){
			file = "install_lodop64.exe";
		}
		var strHtml1 = $.i18n.prop('print.install.content');
		$.dialog({
		    fixed: true,
		    resize: false,
		    drag: true,
		    title: $.i18n.prop('print.install.tip'),
		    content: strHtml1,
		    icon: "warning",
		    okVal: $.i18n.prop('print.install.download'),
		    id: 'installPrint'
		},function(){
			window.location.href = com.ue.global.path + "/platform/component/print/" + file;
			return false;
		});
	},
	initPrint : function(){
		if($.browser.isMobilePlatforms){
			alert($.i18n.prop('print.device.notsupport'));//设备不支持
			return;
		}
		if(this.hasInit){
			return;
		}
		var plus = document.getElementById('LODOP');
		if(plus == null || (typeof(plus) == "undefined")){
			var plusHtml = "<object id='LODOP' classid='clsid:2105C259-1E0C-4534-8141-A753534CB4CA' width=0 height=0>";
			plusHtml += "<embed id='LODOP_EM' type='application/x-print-lodop' width=0 height=0 pluginspage='install_lodop.exe'></embed>";
			plusHtml += "</object>";
	 		var newNode = document.createElement("div");
			newNode.innerHTML = plusHtml;
			document.body.appendChild(newNode);
		}
 		this.lodop = this.getLodop(document.getElementById('LODOP'),document.getElementById('LODOP_EM'));
 		if(this.lodop == null){
			return;
		}
 		this.lodop.PRINT_INIT("");
 		if($.cookie('L10N_TRACK') == "en-US"){
 			this.lodop.SET_SHOW_MODE("LANGUAGE",1);
 		}else{
	 		this.lodop.SET_SHOW_MODE("LANGUAGE",0);//0:简体中文;1:英语;2:繁体;3:big5;
 		}
 		this.hasInit = true;
	},
	previewHtml : function(htmlStr,cssFiles){
		this.initPrint();
		if(this.lodop == null){
			return;
		}
		if(!!htmlStr){
			var printHtml = "";
			if(!!cssFiles){
				for(var i = 0;i < cssFiles.length;i++){
					printHtml += "<link href='" + cssFiles[i] + "' type='text/css' rel='stylesheet'>";
				}
			}
			printHtml += "<body>" + htmlStr + "</body>";
			this.lodop.ADD_PRINT_HTM(this.param.top,this.param.left,this.param.width,this.param.height,printHtml);
		}
		this.lodop.SET_PRINT_PAGESIZE(this.param.orientation,this.param.pageWidth,this.param.pageHeight,this.param.pageName);
		this.lodop.PREVIEW();
		com.ue.print.setParam(com.ue.print.defaultParam);
	},
	previewTable : function(htmlStr,cssFiles){
		this.initPrint();
		if(this.lodop == null){
			return;
		}
		if(!!htmlStr){
			var printHtml = "";
			if(!!cssFiles){
				for(var i = 0;i < cssFiles.length;i++){
					printHtml += "<link href='" + cssFiles[i] + "' type='text/css' rel='stylesheet'>";
				}
			}
			printHtml += "<body>" + htmlStr + "</body>";
			this.lodop.ADD_PRINT_TABLE(this.param.top,this.param.left,this.param.width,this.param.height,printHtml);
		}
		this.lodop.SET_PRINT_PAGESIZE(this.param.orientation,this.param.pageWidth,this.param.pageHeight,this.param.pageName);
		this.lodop.PREVIEW();
		com.ue.print.setParam(com.ue.print.defaultParam);
	},
	previewUrl : function(url){
		this.initPrint();
		if(this.lodop == null){
			return;
		}
		this.lodop.SET_PRINT_PAGESIZE(this.param.orientation,this.param.pageWidth,this.param.pageHeight,this.param.pageName);
		this.lodop.ADD_PRINT_URL(this.param.top,this.param.left,this.param.width,this.param.height,url);
		this.lodop.PREVIEW();
		com.ue.print.setParam(com.ue.print.defaultParam);
	},
	addPageHtml : function(htmlStr,cssFiles){
		this.initPrint();
		if(this.lodop == null){
			return;
		}
		if(!!htmlStr){
			var printHtml = "";
			if(!!cssFiles){
				for(var i = 0;i < cssFiles.length;i++){
					printHtml += "<link href='" + cssFiles[i] + "' type='text/css' rel='stylesheet'>";
				}
			}
			this.lodop.NEWPAGEA();
			printHtml += "<body>" + htmlStr + "</body>";
			this.lodop.ADD_PRINT_HTM(this.param.top,this.param.left,this.param.width,this.param.height,printHtml);
		}
	},
	addPageTable : function(htmlStr,cssFiles){
		this.initPrint();
		if(this.lodop == null){
			return;
		}
		if(!!htmlStr){
			var printHtml = "";
			if(!!cssFiles){
				for(var i = 0;i < cssFiles.length;i++){
					printHtml += "<link href='" + cssFiles[i] + "' type='text/css' rel='stylesheet'>";
				}
			}
			this.lodop.NEWPAGEA();
			printHtml += "<body>" + htmlStr + "</body>";
			this.lodop.ADD_PRINT_TABLE(this.param.top,this.param.left,this.param.width,this.param.height,printHtml);
		}
	},
	addPageUrl : function(url){
		this.initPrint();
		if(this.lodop == null){
			return;
		}
		if(!!url){
			this.lodop.NEWPAGEA();
			this.lodop.ADD_PRINT_URL(this.param.top,this.param.left,this.param.width,this.param.height,url);
		}
	},
	preview : function(){
		this.initPrint();
		if(this.lodop == null){
			return;
		}
		this.lodop.SET_PRINT_PAGESIZE(this.param.orientation,this.param.pageWidth,this.param.pageHeight,this.param.pageName);
		this.lodop.PREVIEW();
		com.ue.print.setParam(com.ue.print.defaultParam);
	}
};