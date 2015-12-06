<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Pet shop - main page</title>
</head>
<body>
<h2>Pet Shop - basket</h2>
<p>Hello, <sec:authentication property="principal.username" />!</p>
<table id="petsTable" border="1">
    	<tr>
        	<th>Good's name</th>
        	<th>Price</th>
        	<th>Avail. Qnt.</th>
        	<th>Qnt. to buy</th>
        	<th>Action</th>
    	</tr>
    	<c:choose>
    		<c:when test="${ goodslist.isEmpty() == true}">
    			<tr><td colspan="0">Basket is empty</td></tr>
    		</c:when>
    		<c:otherwise>
    			<c:forEach var="basketItem" items="${goodslist}">
   	 				<spring:url value="/cont/basket/update.html" var="updateUrl">
   			 			<spring:param name="id" value="${basketItem.id}"/>
   	 				</spring:url>
   	 				<spring:url value="/cont/basket/delete.html" var="deleteUrl">
   			 			<spring:param name="id" value="${basketItem.id}"/>
   	 				</spring:url>
        			<tr>
            			<td>${basketItem.good.name}</td>
            			<td>${basketItem.good.price}</td>
            			<td>${basketItem.good.quantity}</td>
            			<td>${basketItem.quantity}</td>
            			<td>
			   			 	<a href="${updateUrl}" >Update</a>
   						 	<a href="${deleteUrl}" onclick="return confirm('Are you sure?')" >Delete</a>
   			 			</td>
        			</tr>
    			</c:forEach>
    		</c:otherwise>
    	</c:choose>
	</table>
<p><a href='cont/pets'>Pets</a> 
<a href='cont/foods'>Foods</a> 
<a href='cont/tools'>Tools</a></p>
<spring:url value="./j_spring_security_logout" var="logoutUrl" />
<a href="${logoutUrl}">Log Out</a>
</body>
</html>