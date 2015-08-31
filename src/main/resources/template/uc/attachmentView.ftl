<#if parameters.attachmentVOs?size != 0>
<div class="download_add">
<fieldset>
<legend>附件提取</legend>
<ul>
	<#list parameters.attachmentVOs as attVo>
	<li>
		<a href='${attVo.downloadUrl}'>${attVo.name}</a>
	</li>
	</#list>
</ul>
<#if parameters.zipUrl?trim != "">
<a class='button btnGreen' href='${parameters.zipUrl}'>打包下载</a>
</#if>
</fieldset>
</div>
</#if>
