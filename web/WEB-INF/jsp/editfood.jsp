<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pet shop - add/edit food</title>
</head>
<body>
<h1>Pet shop - add/edit food</h1>
<form:form method="post" modelAttribute="food">
	<form:hidden path="id"/><br>
    	Food name : 
	<form:input type="text" path="name" /><br>
    	Weight : 
	<form:input type="text" path="weight" /><br>
    	Price : 
	<form:input type="text" path="price" /><br>
		Quantity : 
	<form:input type="text" path="quantity"/><br>
 
    <input type="submit" name="okButton" value="Ok">	
	<spring:url value="/cont/foods" var="browseUrl"/>
    <input type="button" name="cancelButton" value="Cancel"
			onclick="location.href='${browseUrl}'; return false">
</form:form>
</body>
</html>