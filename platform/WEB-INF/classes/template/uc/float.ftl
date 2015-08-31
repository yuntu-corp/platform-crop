<input type="text"<#rt/>
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
<#if parameters.readonly?default(false)>
 readonly="readonly"<#rt/>
</#if>
<#if parameters.placeholder??>
 placeholder="${i18n('${parameters.placeholder}')}"<#rt/>
</#if>
 class="validate[<#rt/>
<#if parameters.required?default(false)>
required,custom[float<#rt/>
<#else>
optional,custom[float<#rt/>
</#if>
<#if parameters.decimalScale?? >
${parameters.decimalScale?html}<#rt/>
</#if>
]<#rt/>
<#if parameters.minValue?? >
,min[${parameters.minValue?html}]<#rt/>
</#if>
<#if parameters.maxValue?? >
,max[${parameters.maxValue?html}]<#rt/>
</#if>
]<#rt/> 
<#if parameters.cssClass?? >
 ${parameters.cssClass?default('')?html}<#rt/>
</#if>
"<#rt/>
<#if parameters.cssStyle??>
 style="${parameters.cssStyle?html}"<#rt/>
</#if>
<#include "event.ftl"/>
/>