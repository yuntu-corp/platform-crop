<textarea<#rt/>
<#include "common.ftl"/>
 cols="${parameters.cols?default("")?html}"<#rt/>
 rows="${parameters.rows?default("")?html}"<#rt/>
<#if parameters.maxlength??>
 maxlength="${parameters.maxlength?html}"<#rt/>
</#if>
<#if parameters.readonly?default(false)>
 readonly="readonly"<#rt/>
</#if>
<#if parameters.placeholder??>
 placeholder="${i18n('${parameters.placeholder}')}"<#rt/>
</#if>
<#include "css.ftl"/>
<#include "event.ftl"/>
><#rt/>
<#if parameters.value??>
${parameters.value}<#t/>
</#if>
</textarea><#rt/>
<script type="text/javascript">
$("#${parameters.id?default('')?html}").maxlength({
	maxLength: ${parameters.maxlength?default(0)?html}
});
</script>