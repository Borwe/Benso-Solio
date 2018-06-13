<%@page import="models.Customer"%>
<%@page import="java.util.Collections"%>
<%@page import="models.Sale"%>
<%@page import="models.Employee"%>
<%@page import="models.Produce"%>
<%@page import="models.Admin"%>
<%@page import="models.Categories"%>
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

<title>ADMINISTRATOR PAGE</title>
</head>
<body><center>
<script>
	$(document).ready(function() {
		$("#add_produce").hide();
		$("#add_employee").hide();
		$("#show_sales").hide();
		$("#show_products").show();
		
		$("#add_produce_btn").click(function() {
			$("#add_produce").show();
			$("#add_employee").hide();
			$("#show_sales").hide();
			$("#show_products").hide();
		});
		
		$("#add_employee_btn").click(function() {
			$("#add_produce").hide();
			$("#add_employee").show();
			$("#show_sales").hide();
			$("#show_products").hide();
		});
		
		$("#show_products_btn").click(function() {
			$("#add_produce").hide();
			$("#add_employee").hide();
			$("#show_sales").hide();
			$("#show_products").show();
		});
		
		$("#show_sales_btn").click(function() {
			$("#add_produce").hide();
			$("#add_employee").hide();
			$("#show_sales").show();
			$("#show_products").hide();
		});
	});
</script>
<table width="100%">
    <tr>
        <th><a href="#" id="add_produce_btn">ADD PRODUCE</a></th>
        <th><a href="#" id="add_employee_btn">ADD EMPLOYEE</a></th>
        <th><a href="#" id="show_products_btn">PRODUCT TO EMPLOYEES</a></th>
        <th><a href="#" id="show_sales_btn">SALES</a></th>
        <th><a href="admin_logout">LOG OUT</a></th>
    </tr>
</table>

<br>
<h3><font color="red">
<% 
String admin_error=(String)request.getSession().getAttribute("admin_error");
if(admin_error!=null){
	out.println(admin_error);
	request.getSession().setAttribute("admin_error", null);
}
%>
</font>
</h3>


<table id="add_produce" width="80%">
	<form method="post" action="add_produce">
    <tr>
        <th>SELECT CATEGORY</th>
        <td><select name="category_id">
        	<%
        		List<Categories> categories=Admin.getListOfCategories();
        		for(Categories cat:categories){
        			%>
        				<option value="<%=cat.getCategory_id()%>"><%=cat.getCategory_name() %></option>
        			<%
        		}
        	%>
            </select>
        </td>
    </tr>
    <tr>
    	<th>Name of Produce:</th>
    	<td><input type="text" id="produce_name" name="produce_name"></td>
    </tr>
    <tr>
    	<th>Quantity</th>
    	<td><input type="number" min=1 name="quantity"></td>
    </tr>
    <tr>
    </tr>
    	<th>Price Per Unit</th>
    	<td><input type="number" min=1 name="price"></td>
    <tr>
    	<td colspan="2"><input type="submit" value="ADD PRODUCE"></td>
    </tr>
    </form>
</table>

<table id="add_employee" width="80%">
	<form method="post" action="add_employee">
	<tr>
		<th>First Name:</th>
		<td><input type="text" name="first_name"></td>
	</tr>
	<tr>
		<th>Last Name:</th>
		<td><input type="text" name="last_name"></td>
	</tr>
	<tr>
		<th>National ID</th>
		<td><input type="text" name="national_id"></td>
	</tr>
	<tr>
		<th>Produce In Charge:</th>
		<td><select name="produce_id">
			<% List<Produce> produceList=Produce.getProduceList();
				if(produceList.size()<=0){
					%><option>Nothing</option><%
				}else{
					for(Produce produce:produceList){
						%>
							<option value="<%= produce.getProduce_id()%>"><%=produce.getProduce_name() %></option>
						<%
					}
				}
			%>
		</select></td>
	</tr>
	<tr>
		<td colspan="2"><%
			if(produceList.size()<=0){
				out.println("Sorry, No products, so no need for employee");
			}else{
				%>
				<input type="submit" value="ADD EMPLOYEE">
				<%
			}
		%></td>
	</tr>
	</form>
</table>

<table id="show_products" width="80%">
	<tr>
		<th>Produce ID</th>
		<th>Produce Name</th>
		<th>Quantity</th>
		<th>Price Per Unit(Ksh.)</th>
		<th>Workers Responsible</th>
	</tr>
	<%
		for(Produce prod:produceList){
			String employeeNames="";
			for(Employee employee:prod.getEmployeesResponsible()){
				employeeNames+=
						employee.getFirst_name()+" "+employee.getLast_name()
						+", ";
			}
			employeeNames=employeeNames.trim();
			%>
				<tr>
				<td><%=prod.getProduce_id() %></td>
				<td><%=prod.getProduce_name() %></td>
				<td><%=prod.getQuantity() %></td>
				<td><%=prod.getPrice() %></td>
				<td><%=employeeNames %></td>
				</tr>
			<%
		}
	%>
</table>

<table id="show_sales" width="80%">
	<tr>
		<th>ID</th>
		<th>Produce Name</th>
		<th>Customer Name</th>
		<th>Quantity(Units)</th>
		<th>Price(Ksh.)</th>
		<th>MPESA code</th>
		<th>Date</th>
	</tr>
	
	<%
		List<Sale> salesList=Sale.getSalesList();
		Collections.reverse(salesList);
		
		for(Sale sale:salesList){
			%>
				<tr>
				<td><%=sale.getInventory_id() %></td>
				<td><% for(Produce produce:Produce.getProduceList()){
					if(produce.getProduce_id()==sale.getProduce_id()){
						out.println(produce.getProduce_name());
					}
				}
				%></td>
				<td><%=Customer.getCustomerNameByID(sale.getCustomer_id()) %></td>
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