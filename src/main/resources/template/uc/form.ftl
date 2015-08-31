<form<#rt/>
<#if parameters.id??>
 id="${parameters.id?html}"<#rt/>
</#if>
<#if parameters.name??>
 name="${parameters.name?html}"<#rt/>
</#if>
<#if parameters.onsubmit??>
 onsubmit="${parameters.onsubmit?html}"<#rt/>
</#if>
<#if parameters.action??>
 action="${parameters.action?html}"<#rt/>
</#if>
<#if parameters.target??>
 target="${parameters.target?html}"<#rt/>
</#if>
<#if parameters.method??>
 method="${parameters.method?html}"<#rt/>
<#else>
 method="post"<#rt/>
</#if>
<#if parameters.enctype??>
 enctype="${parameters.enctype?html}"<#rt/>
</#if>
<#if parameters.cssStyle??>
 style="${parameters.cssStyle?html}"<#rt/>
</#if>
<#if parameters.cssClass??>
 class="${parameters.cssClass?html}"<#rt/>
</#if>
>