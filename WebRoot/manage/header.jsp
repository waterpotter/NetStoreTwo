<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<title>后台管理</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/main.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/util.js"></script>
</head>
<body >
	<br/>
	<h1 align="center" valign="center">后台管理</h1>
	<br/>
	<div align="center" valign="center">
	<a href="${pageContext.request.contextPath }/manage/addCategory.jsp">添加分类</a>
	<a href="${pageContext.request.contextPath }/manage/ManageServlet?op=showAllCategory">查询分类</a>
	<a href="${pageContext.request.contextPath }/manage/ManageServlet?op=addBookUI">添加图书</a>
	<a href="${pageContext.request.contextPath }/manage/ManageServlet?op=showPageBooks">查询图书</a>
	<a href="">待处理订单</a>
	<a href="">已处理订单</a>
	</div>
	<br/>
