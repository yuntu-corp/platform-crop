<input type="checkbox"<#rt/>
<#include "common.ftl"/>
 value="${parameters.fieldValue?default('')?html}"<#rt/>
<#if parameters.value?? && parameters.fieldValue?? && parameters.value == parameters.fieldValue>
 checked="checked"<#rt/>
</#if>
<#if parameters.readonly?default(false)>
 readonly="readonly"<#rt/>
</#if>
<#include "css.ftl"/>
<#include "event.ftl"/>
/>
<#if parameters.label??>
<label for="${parameters.id}" class="checkboxLabel"><#if parameters.labelEnableI18n>${i18n('${parameters.label}')}<#else>${parameters.label}</#if></label><#rt/>
</#if>