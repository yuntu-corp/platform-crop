<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%-- 标签库声明 --%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="uc" uri="/WEB-INF/tld/UC.tld" %>
<%-- 全局Java变量声明 --%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%-- 页面变量声明 --%>
<c:set var="path" 	value="<%=path%>"/>
<c:set var="basePath" 	value="<%=basePath%>"/>