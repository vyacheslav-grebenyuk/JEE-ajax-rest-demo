<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pet shop - pets list</title>
</head>
<body>
<h1>Pets list</h1>
	<form:form method="get" action="pets/byname" >
    	Filter by name : 
		<input type="text" name="filterPetByName" /> 
    	<input type="submit" value="Filter" />	
		<spring:url value="/cont/pets" var="discardFilterUrl"/>
    	<input type="button" name="cancelButton" value="Discard"
				onclick="location.href='${discardFilterUrl}'; return false" />
	</form:form>
	<form:form method="get" action="pets/pricerange" >
    	Filter by price range : 
		<input type="text" name="start" size="4"/>&nbsp;&nbsp; 
		<input type="text" name="end" size="4"/> 
    	<input type="submit" value="Filter" />	
		<spring:url value="/cont/pets" var="discardFilterUrl"/>
    	<input type="button" name="cancelButton" value="Discard"
				onclick="location.href='${discardFilterUrl}'; return false" />
	</form:form>
	<table id="petsTable" border="1">
    	<tr>
        	<th>Pet's name</th>
        	<th>Age</th>
        	<th>Price</th>
        	<th>Avail.Qnt.</th>
        	<th>Action</th>
    	</tr>
    	<c:forEach var="pet" items="${petslist}">
   	 	<spring:url value="/cont/pets/update.html" var="updateUrl">
   			 <spring:param name="id" value="${pet.id}"/>
   	 	</spring:url>
   	 	<spring:url value="/cont/pets/delete.html" var="deleteUrl">
   			 <spring:param name="id" value="${pet.id}"/>
   	 	</spring:url>
   	 	<spring:url value="/cont/pets/tobasket.html" var="tobasketUrl">
   			 <spring:param name="id" value="${pet.id}"/>
   	 	</spring:url>
        	<tr>
            	<td>${pet.name}</td>
            	<td>${pet.age}</td>
            	<td>${pet.price}</td>
            	<td>${pet.quantity}</td>
            	<td>
   			 	<a href="${tobasketUrl}" >Add to basket</a>
   			 	<a href="${updateUrl}" >Update</a>
   			 	<a href="${deleteUrl}" onclick="return confirm('Are you sure?')" >Delete</a>
   			 </td>
        	</tr>
    	</c:forEach>
	</table>
	<spring:url value="/cont/pets/add.html" var="addUrl"/>
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