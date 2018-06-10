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
<title>CONFIRM</title>
</head>
<body>

<%
	List<Produce> produceList=Produce.getProduceList();
	Enumeration<String> params=request.getParameterNames();
	
	HashMap<String,String> values=new HashMap<>();
	
	while(params.hasMoreElements()){
		values.put(params.nextElement(), request.getParameter(params.nextElement()));
	}
%>
	
</body>
</html>