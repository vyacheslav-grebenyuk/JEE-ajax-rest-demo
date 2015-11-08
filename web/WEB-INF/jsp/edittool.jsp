<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pet shop - add/edit tool</title>
</head>
<body>
<h1>Pet shop - add/edit tool</h1>
<form:form method="post" modelAttribute="tools">
	<form:hidden path="id"/><br>
    	Tools name : 
	<form:input type="text" path="name" /><br>
    	Price : 
	<form:input type="text" path="price" /><br>
		Quantity : 
	<form:input type="text" path="quantity"/><br>
 
    <input type="submit" name="okButton" value="Ok">	
	<spring:url value="/cont/tools" var="browseUrl"/>
    <input type="button" name="cancelButton" value="Cancel"
			onclick="location.href='${browseUrl}'; return false">
</form:form>
</body>
</html>