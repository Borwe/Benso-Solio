<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet" href="../css/bootstrap.min.css">
<script src="../js/jquery-3.3.1.min.js"></script>
<script src="../js/bootstrap.min.js"></script>

<title>ADMIN LOGIN</title>
</head>
<body>
<center>
<table>
	<form method="post" action="admin_login">
	<tr>
		<th colspan="2" align="center">LOGIN</th>
	</tr>
	
	<tr>
		<th colspan="2"><font  color="red"><%
			String admin_error=(String)
				request.getSession().getAttribute("admin_error");
			if(admin_error!=null){
				out.println(admin_error);
				request.getSession().setAttribute("admin_error", null);
			}
		%></font></th>
	</tr>
	<tr>
		<td>User Name:</td>
		<td><input type="text" name="user_name"></td> 
	</tr>
	<tr>
		<td>Password:</td>
		<td><input type="password" name="password"></td>
	</tr>
	<tr>
		<td colspan="2"><input type="submit" value="LOGIN"></td>
	</tr>
	</form>
</table>
</center>
</body>
</html>