<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pet shop - tools list</title>
</head>
<body>
<h1>Tools list</h1>
<p>Hello, <sec:authentication property="principal.username" />!</p>
	<form:form method="get" action="tools/byname" >
    	Filter by name : 
		<input type="text" name="filterToolsByName" /> 
    	<input type="submit" value="Filter" />	
		<spring:url value="/cont/tools" var="discardFilterUrl"/>
    	<input type="button" name="cancelButton" value="Discard"
				onclick="location.href='${discardFilterUrl}'; return false" />
	</form:form>
	<form:form method="get" action="tools/pricerange" >
    	Filter by price range : 
		<input type="text" name="start" size="4"/>&nbsp;&nbsp; 
		<input type="text" name="end" size="4"/> 
    	<input type="submit" value="Filter" />	
		<spring:url value="/cont/tools" var="discardFilterUrl"/>
    	<input type="button" name="cancelButton" value="Discard"
				onclick="location.href='${discardFilterUrl}'; return false" />
	</form:form>
	<table id="toolsTable" border="1">
    	<tr>
        	<th>Tools name</th>
        	<th>Price</th>
        	<th>Avail.Qnt.</th>
        	<th>Action</th>
    	</tr>
    	<c:forEach var="tool" items="${toolslist}">
   	 	<spring:url value="/cont/tools/update.html" var="updateUrl">
   			 <spring:param name="id" value="${tool.id}"/>
   	 	</spring:url>
   	 	<spring:url value="/cont/tools/delete.html" var="deleteUrl">
   			 <spring:param name="id" value="${tool.id}"/>
   	 	</spring:url>
   	 	<spring:url value="/cont/tools/tobasket.html" var="tobasketUrl">
   			 <spring:param name="id" value="${tool.id}"/>
   	 	</spring:url>
        	<tr>
            	<td>${tool.name}</td>
            	<td>${tool.price}</td>
            	<td>${tool.quantity}</td>
            	<td>
   			 	<a href="${tobasketUrl}" >Add to basket</a>
   			 	<sec:authorize access="hasRole('USER')">
   			 	<a href="${updateUrl}" >Update</a>
   			 	<sec:authorize access="hasRole('ADMIN')">
   			 	<a href="${deleteUrl}" onclick="return confirm('Are you sure?')" >Delete</a>
   			 	</sec:authorize>
   			 	</sec:authorize>
   			 </td>
        	</tr>
    	</c:forEach>
	</table>
	<sec:authorize access="hasRole('ADMIN')">
	<spring:url value="/cont/tools/add.html" var="addUrl"/>
	<a href="${addUrl}" >Add</a>
	</sec:authorize>
	<br />
	<spring:url value="/" var="indexUrl"/>
	<a href="${indexUrl}" >Main page</a>
	<spring:url value="../j_spring_security_logout" var="logoutUrl" />
	<a href="${logoutUrl}">Log Out</a>
	
    <c:if test="${requestScope.error != null}">
    	<script>
        	alert('${requestScope.error}');
    	</script>
    </c:if>
</body>
</html>