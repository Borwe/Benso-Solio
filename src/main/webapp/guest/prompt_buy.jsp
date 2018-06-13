<%@page import="java.util.Set"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Enumeration"%>
<%@page import="models.Produce"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet" href="../css/bootstrap.min.css">
<script src="../js/jquery-3.3.1.min.js"></script>
<script src="../js/bootstrap.min.js"></script>

<title>CONFIRM</title>
</head>
<body>
<center>
<table>
	<tr>
		<th>Produce Chosen:</th>
		<th>Quantity (units)</th>
		<th>Price(Ksh)</th>	
	</tr>
<%
	
	HashMap<String,String> values=(HashMap<String,String>)request.getSession().getAttribute("values");
	
	Set<String> keys=values.keySet();
	
	double total_price_ofItems=0;
	
	for(String k:keys){
		Produce p=Produce.getProduceWithName(k);
		double price=p.getPrice()*Double.parseDouble(values.get(k));
		total_price_ofItems=total_price_ofItems+price;
		%>
			<tr>
				<td><%=p.getProduce_name() %></td>
				<td><%=values.get(k) %></td>
				<td><%=String.valueOf(price) %></td>
			</tr>
		<%
	}
%>
	<tr>
		<th colspan="2">Total Price:</th>
		<td><%=total_price_ofItems %></td>
	</tr>
	<form method="post" action="customer_paid">
	<tr>
		<th colspan="2">Pay with MPESA to no. 0711881816<br>
			And enter the Mpesa code here. 
		</th>
		<td>
			<input type="text" name="mpesa_code">
		</td>
	</tr>
	<tr>
		<td colspan="2"><input type="submit" value="Paid With MPESA"></td>
		</form>
		<form method="post" action="cancel_list">
		<td><input type="submit" value="CANCEL"></td>
		</form>
	</tr>
</table>
</center>
</body>
</html>