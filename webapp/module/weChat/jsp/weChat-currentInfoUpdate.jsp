<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp"%>
<!doctype html>
<html>
<head>
	<meta charset="utf-8"/>
	<title>修改今日价格</title>
	<meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<link href="${path }/module/weChat/jump/css/jump-demo.css" rel="stylesheet" type="text/css">
    <link href="${path }/module/weChat/jump/css/flat.color.css" rel="stylesheet" type="text/css">
    <link href="${path }/module/weChat/jump/css/flat.component.css" rel="stylesheet" type="text/css">
    <link href="${path }/module/weChat/jump/css/iconline.css" rel="stylesheet" type="text/css">
</head>

<body>
	<div class="jump-container">
    	<div class="jump-content">
           <div style=" border-bottom:1px solid #ddd;border-top:1px solid #ddd;height:40px; margin-top:20px; padding:0 10px;background-color:#fff; "><input type="number" style="width:100%; height:100%;" placeholder="600"></div>
            <div style="margin-top:80px; padding:0 10px;"><a href=""><button class="block">提交</button></a></div>
            <div style="margin-top:10px; padding:0 10px;"><a href=""><button class="block cancel">取消</button></a></div>
        </div>
    </div>
    
    <script src="${path }/module/weChat/jump/js/zepto.min.js"></script>
</body>
</html>
