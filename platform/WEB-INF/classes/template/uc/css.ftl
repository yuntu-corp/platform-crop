<#if parameters.cssClass?? || parameters.validate?default(false) >
 class="<#include "validate.ftl" /> ${parameters.cssClass?default('')?html}"<#rt/>
</#if>
<#if parameters.cssStyle??>
 style="${parameters.cssStyle?html}"<#rt/>
</#if>