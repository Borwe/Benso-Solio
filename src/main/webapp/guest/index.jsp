<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet" href="../css/bootstrap.min.css">
<script src="../js/jquery-3.3.1.min.js"></script>
<script src="../js/bootstrap.min.js"></script>

<title>Guest Login</title>
</head>
<body>

<script>
	$(document).ready(function() {
		$("#registration_table").hide();
		$("#login_table").show();
		
		$("#registration_btn").click(function() {
			$("#registration_table").show();
			$("#login_table").hide();
		});
		
		$("#login_btn").click(function() {
			$("#registration_table").hide();
			$("#login_table").show();
		});
	});
</script>
<center>

	<%
		if(request.getSession().getAttribute("user")!=null){
			request.getRequestDispatcher("page.jsp").forward(request, response);
		}
	%>
	
	<h3><font color="red">
		<%
			String user_error=(String)
				request.getSession().getAttribute("user_error");
			
			if(user_error!=null){
				out.println(user_error);
				request.getSession().setAttribute("user_error", null);
			}
		%>
	</font></h3>

	<table id="login_table">
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
			<td>
				<input type="submit" value="LOGIN">
			</td>
			</form>
			<td>
				<input id="registration_btn" type="button" value="START REGISTRATION">
			</td>
		</tr>
		
	</table>
	
		<form action="customer_register" method="post">
	<table id="registration_table">
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
			<td ><input type="submit" value="REGISTER"></td>
			</form>
			<td><input id="login_btn" type="button" value="BACK TO LOGIN"></td>
		</tr>
		
		
	</table>
</center>
</body>
</html>