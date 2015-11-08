<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pet shop - foods list</title>
</head>
<body>
<h1>Foods list</h1>
	<form:form method="get" action="foods/byname" >
    	Filter by name : 
		<input type="text" name="filterFoodByName" /> 
    	<input type="submit" value="Filter" />	
		<spring:url value="/cont/foods" var="discardFilterUrl"/>
    	<input type="button" name="cancelButton" value="Discard"
				onclick="location.href='${discardFilterUrl}'; return false" />
	</form:form>
	<form:form method="get" action="foods/pricerange" >
    	Filter by price range : 
		<input type="text" name="start" size="4"/>&nbsp;&nbsp; 
		<input type="text" name="end" size="4"/> 
    	<input type="submit" value="Filter" />	
		<spring:url value="/cont/foods" var="discardFilterUrl"/>
    	<input type="button" name="cancelButton" value="Discard"
				onclick="location.href='${discardFilterUrl}'; return false" />
	</form:form>
	<table id="foodsTable" border="1">
    	<tr>
        	<th>Food name</th>
        	<th>Weight</th>
        	<th>Price</th>
        	<th>Avail.Qnt.</th>
        	<th>Action</th>
    	</tr>
    	<c:forEach var="food" items="${foodslist}">
   	 	<spring:url value="/cont/foods/update.html" var="updateUrl">
   			 <spring:param name="id" value="${food.id}"/>
   	 	</spring:url>
   	 	<spring:url value="/cont/foods/delete.html" var="deleteUrl">
   			 <spring:param name="id" value="${food.id}"/>
   	 	</spring:url>
   	 	<spring:url value="/cont/foods/tobasket.html" var="tobasketUrl">
   			 <spring:param name="id" value="${food.id}"/>
   	 	</spring:url>
        	<tr>
            	<td>${food.name}</td>
            	<td>${food.weight}</td>
            	<td>${food.price}</td>
            	<td>${food.quantity}</td>
            	<td>
   			 	<a href="${tobasketUrl}" >Add to basket</a>
   			 	<a href="${updateUrl}" >Update</a>
   			 	<a href="${deleteUrl}" onclick="return confirm('Are you sure?')" >Delete</a>
   			 </td>
        	</tr>
    	</c:forEach>
	</table>
	<spring:url value="/cont/foods/add.html" var="addUrl"/>
	<a href="${addUrl}" >Add</a>
	<spring:url value="/" var="indexUrl"/>
	<a href="${indexUrl}" >Main page</a>
	
    <c:if test="${requestScope.error != null}">
    	<script>
        	alert('${requestScope.error}');
    	</script>
    </c:if>
</body>
</html>