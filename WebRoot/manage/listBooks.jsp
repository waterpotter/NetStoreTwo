<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/manage/header.jsp"%>
<br/>
<table border="1" width="738" align="center">
	<tr>
		<th>序号</th>
		<th>图片</th>
		<th>书名</th>
		<th>作者</th>
		<th>单价</th>
		<th>描述</th>
		<th>所属分类</th>
		<th>操作</th>
	</tr>
	<c:forEach items="${page.records}" var="b" varStatus="vs">
		<tr class="${vs.index%2==0?'odd':'even' }">
			<td>${vs.count }</td>
			<td align="center">
				<img alt="${b.filename}" src="${pageContext.request.contextPath }/images/${b.path}/${b.filename}">
			</td>
			<td align="center">${b.name }</td>
			<td>${b.author }</td>
			<td>${b.price }</td>
			<td>${b.description }</td>
			<td align="center">${b.category.description }</td>
			<td>
				<a href="#">修改</a>
				<a href="#">删除</a>
			</td>
		</tr>
	
	</c:forEach>
	
	
	
</tr>
</table>
<br/>

<td colspan="8" >
		<%@include file="/common/page.jsp" %>
	</td>



</body>
</html>