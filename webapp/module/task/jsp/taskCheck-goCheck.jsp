<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<html>
	<head>  
		<style type="text/css">
			a:link{ text-decoration:none;}/*未被访问过的链接不显示下划线*/
			a:active{ text-decoration:none;}/*正在点击的链接文本闪烁*/
			a:hover{ text-decoration:none;}/*鼠标移入的链接文字有下划线*/
			a:visited{ text-decoration:none;}/*已被访问过链接*/
			.txtfield{ width: 98%; color: #7988a3; border:none; border-radius:2px; height:100px; padding:6px 2px;}
		</style>
		<script type="text/javascript">
			Namespace.register("com.yunt.taskCheck.check");
			com.yunt.taskCheck.check = {
				doCheck : function(dialogId) {
					if(!$("#${pageId}theForm").valid()){
						return false;
					}
					if( $("input[type='radio']:checked").val() == "6" && $("#refuseReason").val().trim().length <= 0){
						alert("请输入未通过审核的理由！");
						return false;
					}
					var formData = $("#${pageId}theForm").serialize();
					$.ajax({
					 	type: "POST",
					  	url: "${path}/task/doCheck",
					  	data: formData,
					  	success: function(){
					  		$.successTips();
					   		art.dialog.list[dialogId].close();
					   		com.yunt.taskCheck.index.refresh();
					  	}
					});
				}
			};
			$(document).ready(function() {
				$.hz.validate.init("${pageId}theForm");
			});
			function closeReason(){
				$("#refuseReason").attr("disabled",true);
			}
			function openReason(){
				$("#refuseReason").attr("disabled",false);
			}
		</script>
	</head>
	<body>
		<form id="${pageId}theForm" method="post" class="BB">
		<input type="hidden" name="model.id" value="${model.id }"/>
		<input type="hidden" name="model.isSuccess" value="${model.isSuccess }"/>
		<input type="hidden" name="model.publisher.id" value="${model.publisher.id }"/>
		<input type="hidden" name="model.title" value="${model.title }"/>
		<input type="hidden" name="model.finalBitcoin" value="${model.finalBitcoin }"/>
		<div style="padding: 20px;">
			<div class="pl100 lh50">
		     	<label class="labelTitle"><em style="color: red;">*</em>标题：</label>
		     	<div class="view_detail2">${model.title}</div>
			</div>
			<div class="pl100 lh50">
		     	<label class="labelTitle"><em style="color: red;">*</em>任务类型：</label>
		     	<div class="view_detail2">${model.taskType.typeName}</div>
			</div>
			<div class="pl100 lh50">
		     	<label class="labelTitle"><em style="color: red;">*</em>任务描述:</label>
		     	<div class="view_detail2">${model.content}</div>
			</div>
			<div class="pl100 lh50">
		     	<label class="labelTitle"><em style="color: red;">*</em>发布人:</label>
		     	<div class="view_detail2">${model.publisher.name}</div>
			</div>
			<c:if test="${model.status ne '0' }">
				<div class="pl100 lh50">
			     	<label class="labelTitle"><em style="color: red;">*</em>接受人:</label>
			     	<div class="view_detail2">${model.receiver.name}</div>
				</div>
			</c:if>
			<div class="pl100 lh50">
		     	<label class="labelTitle"><em style="color: red;">*</em>任务金币:</label>
		     	<c:if test="${model.status eq '0' }">
			     	<div class="view_detail2">${model.bitcoinConsume}</div>
		     	</c:if>
		     	<c:if test="${model.status ne '0' }">
			     	<div class="view_detail2">${model.finalBitcoin}</div>
		     	</c:if>
			</div>
			<div class="pl100 lh50">
		     	<label class="labelTitle"><em style="color: red;">*</em>发布日期:</label>
		     	<div class="view_detail2">
		     		<fmt:formatDate value="${model.createTime}" type="date" pattern="yyyy-MM-dd"/>
		     	</div>
			</div>
			<div class="pl100 lh50">
		     	<label class="labelTitle"><em style="color: red;">*</em>结束日期:</label>
		     	<div class="view_detail2">
			     	<fmt:formatDate value="${model.finishDate}" type="date" pattern="yyyy-MM-dd"/>
		     	</div>
			</div>
			<div class="pl100 lh50">
		     	<label class="labelTitle"><em style="color: red;">*</em>任务状态:</label>
		     	<div class="view_detail2">${model.statusVal}</div>
			</div>
			<c:if test="${model.isSuccessVal ne ''}">
				<div class="pl100 lh50">
			     	<label class="labelTitle"><em style="color: red;">*</em>谈单结果:</label>
			     	<div class="view_detail2">${model.isSuccessVal}</div>
				</div>
			</c:if>
			<div class="pl100 lh50">
		     	<label class="labelTitle"><em style="color: red;">*</em>审核结果:</label>
		     	<div class="view_detail2">
			     	<label style="min-height: 20px; line-height: 20px; width: 100px;" onclick="closeReason()">
						<input type="radio" name="model.status"  value="5"  style="width: 13px; height:13px;margin:4px 4px 0px 0px;  line-height: normal;cursor: pointer;" >通过
					</label>
			     	<label style="min-height: 20px;line-height: 20px; width: 100px;" onclick="openReason()">
						<input type="radio" name="model.status"  value="6" style="width: 13px;height:13px;margin: 4px 4px 0px 0px; line-height: normal;cursor: pointer;" checked >未通过
					</label>
		     	</div>
			</div>
			<div class="pl100 lh50">
		     	<label class="labelTitle"><em style="color: red;">*</em>未通过原因：</label>
		     	<div class="view_detail2"><textarea id="refuseReason"  class="txtfield" name="refuseReason" style="width: 98%; margin-left: -10px;" placeholder="请输入未通过原因" maxlength="100" /></div>
			</div>
		</div>
		</form>
	</body>
</html>