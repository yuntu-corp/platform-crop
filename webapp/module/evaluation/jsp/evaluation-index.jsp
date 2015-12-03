<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
	<head>  
		<script type="text/javascript">
			Namespace.register("com.yunt.evaluation.index");
			com.yunt.evaluation.index = {
				loadContent : function(href) {
					$("body").mask("数据正在加载中，请稍后···");
					$("#content").load(href,function(){
						$("body").unmask();
					});
				},
				input : function(href, title) {
					$.dialog.load(href,{
						id: '${pageId}evaluationInput',
						title:title,
						width:700,
						height:500,
						ok:function(){
							com.yunt.evaluation.input.doInput('${pageId}evaluationInput');
							return false;
						},
						cancel: function(){
		
						}
					});
				},
				view : function(href, title) {
					$.dialog.load(href,{
						id: '${pageId}evaluationInput',
						title:title,
						width:600,
						height:500,
						ok:function(){
						},
						cancel: function(){
		
						}
					});
				},
				refresh : function () {
					com.yunt.evaluation.index.loadContent("${path}/evaluation/list?model.level="+$("#${pageId}level").val());
				}
			};
			$(document).ready(function(){
				com.yunt.evaluation.index.refresh();
				var height=$(window).height()-160;
				$(".details").css("height",height);
			});
		</script>
	</head>
	<body>
		<div class="cont">
			<div class="title">评价管理</div>
			<div class="details">
				<div class="details_operation clearfix">
<%-- 				    <div class="bui_select">
				    	<img src="${path}/platform/theme/distributionSystem/images/coin.png">
						<input type="button" value="添 加" class="add hand" onclick="com.yunt.evaluation.index.input('${path}/evaluation/input?inputKind=save', '添加');">
					</div> --%>
					<div class="fr">
	                	<form method="" action="" class="box">
							<label for="pass" style="line-height:30px;padding-left:10px;">评价星级:</label>
	                    	<uc:select id="${pageId}level" list="{0:'0星',1:'1星',2:'2星',3:'3星',4:'4星',5:'5星'}" name="state" cssClass="search" headerKey="" headerValue="请选择" />
	               			<input type="button" value="搜索" class="searchCoin" onclick="com.yunt.evaluation.index.refresh();">
						</form>
					</div>
				</div>
				<div id="content" />
		    </div>
		</div>
	</body>
</html>
