<#assign itemCount = 0/>
<#list parameters.dataItems as dataItem>
<#assign itemCount = itemCount + 1/>
<input type="radio"<#rt/>
<#if parameters.name??>
 name="${parameters.name?html}"<#rt/>
</#if>
 id="${parameters.id?default('')?html}-${itemCount}"<#rt/>
<#if dataItem.sel >
 checked="checked"<#rt/>
</#if>
 value="${dataItem.key?html}"<#rt/>
<#if parameters.disabled?default(false)>
 disabled="disabled"<#rt/>
</#if>
<#if parameters.tabindex??>
 tabindex="${parameters.tabindex?html}"<#rt/>
</#if>
<#if parameters.title??>
 title="${parameters.title?html}"<#rt/>
</#if>
<#include "css.ftl"/>
<#include "event.ftl"/>
<#if (parameters.dynamicAttributes?? && parameters.dynamicAttributes?size > 0)><#t/>
    <#assign aKeys = parameters.dynamicAttributes.keySet()><#t/>
    <#list aKeys as aKey><#t/>
 ${aKey}="${parameters.dynamicAttributes[aKey]?html}"<#rt/>
    </#list><#t/> 
</#if><#t/>
/><#rt/>
<label for="${parameters.id?default('')?html}-${itemCount}"><#rt/>
    ${dataItem.value}<#t/>
</label>
</#list>
