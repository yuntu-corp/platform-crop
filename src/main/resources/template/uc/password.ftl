<input type="password"<#rt/>
<#include "common.ftl"/>
<#if parameters.size??>
 size="${parameters.size?html}"<#rt/>
</#if>
<#if parameters.maxlength??>
 maxlength="${parameters.maxlength?html}"<#rt/>
</#if>
<#if parameters.value??>
 value="${parameters.value}"<#rt/>
</#if>
<#if parameters.placeholder??>
 placeholder="${i18n('${parameters.placeholder}')}"<#rt/>
</#if>
<#include "css.ftl"/>
<#include "event.ftl"/>
/>