<%@page import="java.util.Collections"%>
<%@page import="java.util.Collection"%>
<%@page import="models.Sale"%>
<%@page import="models.Customer"%>
<%@page import="java.util.HashMap"%>
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

<title>Guest Section</title>
</head>
<body>

<script>
	$(document).ready(function() {
		$("#produce_table").show();
		$("#sales_table").hide();
		
		$("#produce_btn").click(function() {
			$("#produce_table").show();
			$("#sales_table").hide();
		});
		
		$("#buy_history_btn").click(function() {
			$("#produce_table").hide();
			$("#sales_table").show();
		})
	})
</script>
<center>
<%
if(request.getSession(false).getAttribute("user")==null){
	request.getRequestDispatcher("index.jsp").forward(request, response);
}
%>

<table width="80%">
	<tr>
		<th><a href="#" id="produce_btn">Produce Available</a></th>
		<th><a href="#" id="buy_history_btn">Buying History</a></th>
		<th>
		<a href="customer_logout">LOG OUT</a>
		</th>
	</tr>
</table>

<br><br>

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

<table id="produce_table" width="80%">
	
	<form method="post" action="confirm_list">
	<input type="submit" value="BUY SELECTED">
	<tr>
		<th>Produce Name</th>
		<th>Produce Category</th>
		<th>Quantity Available(Units)</th>
		<th>Price Per Unit</th>
		<th>Amount To Purchase</th>
	</tr>
	
	<%
		List<Produce> produceList=Produce.getProduceList();
		for(Produce prod:produceList){
			%>
				<tr>
				<td><%=prod.getProduce_name() %></td>
				<td><%=prod.getCategoryName() %></td>
				<td><%=prod.getQuantity() %></td>
				<td><%=prod.getPrice() %></td>
				<td><input type="number" value=0 min="0" 
					max="<%=prod.getQuantity() %>"
					name="<%=prod.getProduce_name()%>"></td>
				</tr>
			<%
		}
	%>
	
	</form>
</table>

<table id="sales_table" width="80%">
	<tr>
		<th>Produce Name</th>
		<th>Quantity Purchased</th>
		<th>Price(Ksh.)</th>
		<th>MPesa Code</th>
		<th>Date</th>
	</tr>
	
	<%
		HashMap<String,String> user=
			(HashMap<String,String>)
			request.getSession().getAttribute("user");
		Customer customer=Customer.getFromDB(user.get("user_name"),
				user.get("password"));
		List<Sale> sales=customer.getPreviousProduceList();
		Collections.reverse(sales);
		for(Sale sale:sales){
			%>
				<tr>
					<td><%=sale.getProduceName() %></td>
					<td><%=sale.getQuantity() %></td>
					<td><%=sale.getAmount() %></td>
					<td><%=sale.getMpesa_Code() %></td>
					<td><%=sale.getDate() %></td>
				</tr>
			<%
		}
	%>
</table>

</center>
</body>
</html>