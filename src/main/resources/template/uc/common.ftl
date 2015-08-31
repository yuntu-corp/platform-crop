<#if parameters.id??>
 id="${parameters.id?html}"<#rt/>
</#if>
<#if parameters.name??>
 name="${parameters.name?html}"<#rt/>
</#if>
<#if parameters.tabindex??>
 tabindex="${parameters.tabindex?html}"<#rt/>
</#if>
<#if parameters.accesskey??>
 accesskey="${parameters.accesskey?html}"<#rt/>
</#if>
<#if parameters.disabled?default(false)>
 disabled="disabled"<#rt/>
</#if>
<#if parameters.title??>
 title="${parameters.title?html}"<#rt/>
</#if>
<#if (parameters.dynamicAttributes?? && parameters.dynamicAttributes?size > 0)><#t/>
    <#assign aKeys = parameters.dynamicAttributes?keys><#t/>
    <#list aKeys as aKey><#t/>
 ${aKey}="${parameters.dynamicAttributes[aKey]!}"<#rt/>
    </#list><#t/>
</#if><#t/>
