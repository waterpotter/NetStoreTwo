<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/header.jsp"%>
<br />
<tr align="center">
	<a href="${pageContext.request.contextPath}">所有分类:</a>
	<c:forEach items="${cs }" var="c">
		<a href="${pageContext.request.contextPath }/client/ClientServlet?op=showCategoryBooks&categoryId=${c.id}">${c.name }</a>
	</c:forEach>
</tr>
<table border="1" width="538" align="center">
	<tr>
		<c:forEach items="${page.records}" var="b">
			<td align="center"><img
				src="${pageContext.request.contextPath }/images/${b.path}/${b.filename}"
				alt="${b.filename}" /><br/> 书名:${b.name }<br /> 作者:${b.author }<br />
				单价:${b.price }<br /> 
				<a href="${pageContext.request.contextPath }/client/ClientServlet?op=showBookDetial&bookId=${b.id}">去看看</a>
				</td>
		</c:forEach>
	</tr>


	</tr>

</table>
<br />

<tr>
	<td colspan="8"><%@include file="/common/page.jsp"%>
	</td>
</tr>



</body>
</html>