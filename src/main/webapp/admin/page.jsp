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
<title>ADMINISTRATOR PAGE</title>
</head>
<body>

<table>
    <tr>
        <th>ADD PRODUCE</th>
        <th>ADD EMPLOYEE</th>
        <th>Product to Employee</th>
    </tr>
</table>

<br><br>

<table>
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

<br><br>

<table>
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

<br><br>

<table>
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

</body>
</html>