
<select name='${parameters.name?html}'<#rt/>
<#if parameters.id??>
	id='${parameters.id?html}'<#rt/>
</#if>

class='<#if parameters.cssClass??>${parameters.cssClass?html}</#if><#if parameters.required?default(false)> required</#if>'<#rt/>

<#if parameters.cssStyle??>
	style='${parameters.cssStyle?html}'<#rt/>
</#if>
<#if parameters.other??>
	${parameters.other}
</#if>
>
	<#if parameters.headerValue!?trim != "">
		<option value='${parameters.headerKey}'>${bundle('${parameters.headerValue}')}</option>
	</#if>
	<#if parameters.dictValueVOs?size != 0>
		<#list parameters.dictValueVOs as attVo>
			<option value='${attVo.code}' <#if parameters.checkValue == attVo.code>selected</#if>>${attVo.value}</option>
		</#list>
	</#if>
</select>

