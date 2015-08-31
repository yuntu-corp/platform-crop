<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<meta charset="utf-8" />
<!-- 原生扩展 start -->
<script src="${path }/platform/common/js/prototype-ext.js"></script>
<script type="text/javascript">
	//注册产品全局变量方法集
	Namespace.register('com.ue.global');
	com.ue.global = {
		path : '<%=path%>',
		basePath : '<%=basePath%>',
		jsessionid : '${pageContext.session.id}',//sessionId用于flash中提交文件无法使用当前session
		theme : 'default',
		productTitle:''
	}
</script>
<script src="${path }/platform/common/js/jshashtable-3.0.js"></script>
<script src="${path }/platform/common/js/swfobject.js"></script>
<!-- 原生扩展 end -->
<!-- jQuery start -->
<script src="${path }/platform/common/js/jquery-1.11.1.js"></script>
<!-- jQuery end -->
<!-- jQuery扩展 start -->
<script src="${path }/platform/common/js/jquery-ext.js"></script>
<script src="${path }/platform/common/js/jquery.i18n.properties-1.0.9.js"></script>
<script src="${path }/platform/common/js/jquery.cookie.js"></script>
<script type="text/javascript">
	// 初始化js国际化文件 
	$.i18n.properties({
	    name:['upload','validationEngine','print'],
	    path: com.ue.global.path+'/sys/i18n/ajaxGetI18nValues.do?bundleName=', 
	    mode:'map',
	    callback: function() {
	    	
	    }
	});
</script>
<script src="${path }/platform/common/js/jquery-ui.js"></script>
<script src="${path}/platform/component/validate/jquery.validate.js" type="text/javascript"></script>
<script src="${path}/platform/component/validate/jquery.validate.extend.js" type="text/javascript"></script>
<script src="${path}/platform/component/validate/jquery.validate.messages_zh.js" type="text/javascript"></script>
<script src="${path }/platform/component/popupLayer/jquery.artDialog.4.1.7.js"></script>
<script src="${path }/platform/component/popupLayer/jquery.artDialog.iframeTools.4.1.7.js"></script>
<script src="${path }/platform/component/tabs/jquery.tabs.js"></script>
<script src="${path }/platform/component/layout/jquery.layout.js"></script>
<script src="${path }/platform/component/box/jquery.box.js"></script>
<script src="${path }/platform/component/lavaLamp/jquery.lavaLamp-1.4.js"></script>
<script src="${path }/platform/component/zeroclipboard/ZeroClipboard.js"></script>
<script src="${path }/platform/component/zeroclipboard/jquery.zeroclipboard.js"></script>
<script src="${path }/platform/component/editableSelect/jquery.editableSelect.js"></script>
<script src="${path }/platform/component/ibutton/jquery.metadata.js"></script>
<script src="${path }/platform/component/ibutton/jquery.easing.1.3.js"></script>
<script src="${path }/platform/component/ibutton/jquery.ibutton.js"></script>
<script src="${path }/platform/component/mask/jquery.mask.js"></script>
<script src="${path }/platform/component/jeegoocontext/jquery.jeegoocontext.js"></script>
<script src="${path }/platform/component/placeholder/jquery.placeholder.js"></script>
<script src="${path }/platform/component/progressbar/jquery.progressbar.js"></script>
<script src="${path }/platform/component/treeTable/jquery.treeTable.js"></script>
<script src="${path }/platform/component/my97-date/WdatePicker.js"></script>
<script src="${path }/platform/component/ueditor/ueditor.config.js"></script>
<script src="${path }/platform/component/ueditor/ueditor.all.js"></script>
<script src="${path }/platform/component/ueditor/lang/zh-cn/zh-cn.js"></script>
<script src="${path }/platform/component/ueditor/jquery.ueditor.js"></script>
<script src="${path }/platform/component/ztree/jquery.ztree.all-3.5.js"></script>
<script src="${path }/platform/component/ztree/jquery.ztree.js"></script>
<script src="${path }/platform/component/tipbox/jquery.tipbox.js"></script>
<script src="${path }/platform/component/tooltip/jquery.tooltip.js"></script>
<script src="${path }/platform/component/poshytip/jquery.poshytip.js"></script>
<script src="${path }/platform/component/raty/jquery.raty.js"></script>
<script src="${path }/platform/component/accordion/jquery-ui-accordion.js"></script>
<script src="${path }/platform/component/sortable/jquery-ui-sortable.js"></script>
<script src="${path }/platform/component/autocomplete/jquery.autocomplete.js"></script>
<script src="${path }/platform/component/print/com.ue.print.js"></script>
<script src="${path }/platform/component/swfupload/js/swfupload.js"></script>
<script src="${path }/platform/component/swfupload/js/swfupload.queue.js"></script>
<script src="${path }/platform/component/swfupload/js/jquery.swfupload.js"></script>
<script src="${path }/platform/component/jScrollPane/jquery.jscrollpane.js"></script>
<script src="${path }/platform/component/jScrollPane/jquery.mousewheel.js"></script>
<script src="${path }/platform/component/multiselect/jquery.multiselect.js"></script>
<script src="${path }/platform/component/jqGrid/grid.locale.js"></script>
<script src="${path }/platform/component/jqGrid/jquery.jqGrid.js"></script>
<script src="${path }/platform/component/maxlength/jquery.maxlength.js"></script>
<script src="${path }/platform/component/qtip/jquery.qtip.js"></script>
<script src="${path }/platform/component/page/jquery.page.js"></script>

<script src="${path }/platform/common/js/common.js"></script>
<script src="${path }/platform/common/js/co_common.js"></script>
<!-- jQuery扩展 end -->
<!-- 样式 start -->
<link rel="stylesheet" href="${path}/platform/theme/default/style.css"/>
<!-- 样式 end -->

<!-- 测试使用 -->
<%-- <link rel="stylesheet" href="${path}/dm/style.css"/><!-- 测试用的样式 --> --%>
<%-- <script src="${path }/dm/data.js"></script> --%>