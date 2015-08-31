<#setting number_format="#.#####">
<select<#rt/>
<#include "common.ftl"/>
<#if parameters.multiple?default(false)>
 multiple="multiple"<#rt/>
</#if>
<#include "css.ftl"/>
<#include "event.ftl"/>
>
<#if parameters.headerKey?? && parameters.headerValue??>
<option value="${parameters.headerKey?html}">${i18n('${parameters.headerValue}')}</option>
</#if>
<#list parameters.dataItems as dataItem>
<option value="${dataItem.key?html}" <#if dataItem.sel>selected="selected"</#if>>${dataItem.value?html}</option><#lt/>
</#list>
</select>
<#if parameters.editEnable?default(false)>
<script>
	$("#${parameters.id?html}").editableSelect();
</script>
</#if>
<#if parameters.multiple?default(false)>
<script type="text/javascript">
	$(function(){$('#${parameters.id?default('')?html}').multiselect({selectedList: 4});});
</script>
</#if>