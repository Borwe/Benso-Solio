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
    </tr>
</table>

<table>
    <tr>
        <th>SELECT CATEGORY</th>
        <td><select>
        	<%
        		List<Categories> categories=Admin.getListOfCategories();
        		for(Categories cat:categories){
        			%>
        				<option><%=cat.getCategory_name() %></option>
        			<%
        		}
        	%>
            </select>
        </td>
    </tr>
</table>

</body>
</html>