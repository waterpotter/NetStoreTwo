<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<title>哈士奇网站之无所不哈</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/main.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/util.js"></script>
</head>
<body >
	<br/>
	<h1 align="center" valign="center">你是不是二哈</h1>
	<br/>
	<div align="center" valign="center">
	<a href="${pageContext.request.contextPath }">首页</a>
	<a href="${pageContext.request.contextPath }">登录</a>
	<a href="${pageContext.request.contextPath }">免费注册</a>
	<a href="${pageContext.request.contextPath }">我的订单</a>
	<a href="${pageContext.request.contextPath }/showCart.jsp">购物车</a>
	
	</div>
	<br/>
