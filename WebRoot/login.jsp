<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/header.jsp"%>
<form action="${pageContext.request.contextPath}/client/ClientServlet?op=loginCustomer" method="post">
		<table border="1" width="438" align="center">
			<tr>
			<td>用户名:</td>
			<td>
				<input name="username"/>
			</td>
		</tr>
		<tr>
			<td>密码:</td>
			<td>
				<input type="password" name="password"/>
			</td>
		</tr>
	
		<tr>
			<td colspan="2">
				<input type="submit" value="登陆"/>
			</td>
		</tr>
		
		</table>
	
	</form>


</body>
</html>