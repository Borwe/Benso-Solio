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
<title>Guest Section</title>
</head>
<body>

<%
if(request.getSession(false).getAttribute("user")==null){
	request.getRequestDispatcher("index.jsp").forward(request, response);
}
%>

<table>
	<tr>
		<th>Produce Available</th>
		<th>Buying History</th>
		<th>
		<form method="post" action="customer_logout">
		<input type="submit" value="LOG OUT">
		</form>
		</th>
	</tr>
</table>

<br><br>

<table>
	
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

<table>
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

</body>
</html>