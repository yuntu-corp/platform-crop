<div id="<#if parameters.id??>${parameters.id}</#if>">
	
</div>
<script type="text/javascript">
$(function(){
	$.hz.page.init('<#if parameters.id??>${parameters.id}</#if>',{
		pageInfo:${parameters.pageInfo},
		formId:"${parameters.formId}",
		containerId:"${parameters.containerId!}",
		type:"${parameters.type}",
		changePageSize:${parameters.changePageSize},
		changePageSizeNumber:"${parameters.changePageSizeNumber!}"
	});
});
</script>