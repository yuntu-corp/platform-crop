<table class="prompt-mark"><tr>
	<#list parameters.dictValueVOs as attVo>
		<#if (parameters.cols??) && (parameters.cols?number > 0) && (attVo_index%parameters.cols?number == 0)>
			</tr><tr>
		</#if>
		<td align='left'>
			<input type='checkbox'<#rt/> 
				id='${parameters.id?html}-${attVo_index}'<#rt/>
				name='${parameters.name?html}'<#rt/>
				value='${attVo.code}'<#rt/>
				
			class='<#if parameters.cssClass??>${parameters.cssClass?html}</#if><#if parameters.required?default(false)> required</#if>'<#rt/>
			
			<#if parameters.cssStyle??>
				style='${parameters.cssStyle?html}'<#rt/>
			</#if>
			<#if parameters.other??>
				${parameters.other} <#rt/>
			</#if>
			<#if parameters.checkValue!?contains(attVo.code)>
				checked='checked' <#rt/>
			</#if>
			
			/>
			<label for='${parameters.id?html}-${attVo_index}'>${attVo.value}</label>
		</td>
	</#list>
</tr></table>
