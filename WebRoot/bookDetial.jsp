<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/header.jsp"%>
<br />
	
		
	
	<img alt="${book.filename }" 
	src="${pageContext.request.contextPath }/images/${book.path}/${book.filename}"/>
	<br/>
	<br/>
	书名:${book.name }<br/>
	作者:${book.author }<br/>
	价格:${book.price }<br/>
	描述:${book.description }<br/>
	<br/>
	<a href="${pageContext.request.contextPath }/client/ClientServlet?op=buy&bookId=${book.id}">放入购物车</a><br/>
	<a href="javascript:window.history.back()">返回</a><br/>
	
</body>
</html>