<ul id='${parameters.id?html}'></ul>
<script>
	$().ready(function(){
		var settings = {
			nodes:${parameters.nodes},
			isDropdown:${parameters.dropdown?html}, //下拉树
			checkValue:"${parameters.checkValue?html}", //回显值
			expandLevel:parseInt("${parameters.expandLevel?html}"), //回显值
			name:"${parameters.name?html}",
			required:${parameters.required?html},
			check: {
				enable: ${parameters.checkEnable?html},
				chkStyle: "${parameters.checkStyle?html}",
				radioType: "all"
			}
		}
		$.hz.ztree.init("${parameters.id?html}",settings);
	});
		if(""!='${parameters.width?html}'){
			//com.ue.cmpt.tree.setWidth('${parameters.id?html}','${parameters.width?html}');
		}
		if(""!='${parameters.cascade?html}'){
			//com.ue.cmpt.tree.setCascade('${parameters.id?html}');
		}
		if("true"=='${parameters.required?html}'){
			//com.ue.cmpt.tree.setRequired('${parameters.id?html}');
		}
		if(""!="${parameters.placeholder?html}"){
			//com.ue.cmpt.tree.setPlaceholder('${parameters.id?html}','${parameters.placeholder?html}');
		}
</script>
