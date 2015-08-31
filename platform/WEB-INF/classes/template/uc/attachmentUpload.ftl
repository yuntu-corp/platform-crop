<div id="${parameters.id}"></div>

<script>
	$.hz.swfupload.init("${parameters.id}",{
		configCode:"${parameters.configCode}",
		classFieldName:"${parameters.className},${parameters.fieldName}",
		idOwner:"${parameters.ownerId}",
		finishCallback:"${parameters.finishCallback}",
		deleteCallback:"${parameters.deleteCallback}",
		required:${parameters.required}
	});
</script>