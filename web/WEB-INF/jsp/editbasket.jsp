<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Pet shop - update quantity</title>
</head>
<body>
<h1>Pet shop - add/edit tool</h1>
<form:form method="post" modelAttribute="basket">
	<form:hidden path="id"/>
    Good's name      : ${basket.good.name}</br>
    Good's price     : ${basket.good.price}</br>
    Available Quant. : ${basket.good.quantity}</br> 
    Quantity : 
	<form:input type="text" path="quantity"/></br>
	<c:if test="${requestScope.error != null}">
		<div style="color:red;>"${requestScope.error}</br></div>
    </c:if>    <input type="submit" name="okButton" value="Ok">	
	<spring:url value="/shop.html" var="shopUrl"/>
    <input type="button" name="cancelButton" value="Cancel"
			onclick="location.href='${shopUrl}'; return false">
</form:form>
</body>
</html>