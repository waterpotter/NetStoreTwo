<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/header.jsp"%>
	<form action="${pageContext.request.contextPath}/client/ClientServlet?op=registCustomer" method="post">
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
			<td>昵称:</td>
			<td>
				<input name="nickname"/>
			</td>
		</tr>
		<tr>
			<td>电话:</td>
			<td>
				<input name="phonenum"/>
			</td>
		</tr>
		<tr>
			<td>地址</td>
			<td>
				<input name="address"/>
			</td>
		</tr>
		<tr>
			<td>邮件</td>
			<td>
				<input name="email"/>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="保存"/>
			</td>
		</tr>
		
		</table>
	
	</form>

</body>
</html>