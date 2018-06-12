<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Guest Login</title>
</head>
<body>

	<%
		if(request.getSession().getAttribute("user")!=null){
			request.getRequestDispatcher("page.jsp").forward(request, response);
		}
	%>

	<table>
		<form method="post" action="customer_login">
		<tr><th colspan="2">LOGIN</th></tr>
		<tr>
			<td>User Name:</td>
			<td><input type="text" id="user_name" name="user_name"></td>
		</tr>
		<tr>
			<td>Password:</td>
			<td><input type="password" id="password" name="password"></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="LOGIN">
			</td>
		</tr>
		</form>
		
		<form action="customer_register" method="post">
		<tr>
			<th colspan="2">REGISTRATION</th>
		</tr>
		<tr>
			<td>First Name:</td>
			<td><input type="text" id="first_name" name="first_name">
		</tr>
		<tr>
			<td>Last Name:</td>
			<td><input type="text" id="last_name" name="last_name">
		</tr>
		<tr>
			<td>User Name:</td>
			<td><input type="text" id="user_name" name="user_name">
		</tr>
		<tr>
			<td>Phone No:</td>
			<td><input type="text" id="phone_no" name="phone_no">
		</tr>
		<tr>
			<td>Password:</td>
			<td><input type="password" id="password" name="password">
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="REGISTER"></td>
		</tr>
		
		</form>
	</table>
</body>
</html>