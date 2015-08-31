<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>

<div id="tab" class="tab container">
	<div id="t1" title="tab1" url="${path }/dm/test.jsp"></div>
	<div id="t2" title="tab2" url="${path }/index.jsp"></div>
	<div id="t3" title="tab3" url="${path }/dm/test.jsp"></div>
</div>
<script>
	$(document).ready(function(){
		$("#tab").tabs({
			onSelect:function(){
				$(this).load($(this).attr("url"));
			}
		})
	})
</script>