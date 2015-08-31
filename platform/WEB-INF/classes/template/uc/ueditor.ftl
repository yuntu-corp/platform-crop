<script id="${parameters.id}" style="${parameters.style}"></script>
<script>
	$.hz.ueditor.init("${parameters.id}",{
		content:"${parameters.value}",
		name:"${parameters.name}",
		zIndex:"${parameters.zIndex}",
		wordCount:${parameters.wordCount},
		autoHeightEnabled:${parameters.autoHeightEnabled},
		simpleEnabled:${parameters.simpleEnabled}
	});
</script>
