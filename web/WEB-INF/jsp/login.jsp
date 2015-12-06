<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" language="java" import="javax.servlet.jsp.PageContext" %>

<html>
<head>
<title>Pet Shop - Login</title>
</head>

<body>
	<h1>Pet Shop - Login</h1>

	<c:url var="loginUrl" value="/login" />
	<form action="${loginUrl}" method="post">
		<c:if test="${not empty param.err}">
                <div><c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/></div>
        </c:if>
        <c:if test="${not empty param.out}">
                <div>You've logged out successfully.</div>
        </c:if>
        <c:if test="${not empty param.time}">
                <div>You've been logged out due to inactivity.</div>
        </c:if>
		<p>
			<label for="username">User:</label>
		</p>
		<input type="text" id="username" name="username" />

		<p>
			<label for="password">Password:</label>
		</p>
		<input type="password" id="password" name="password">

		<p>
			<label for="rememberme">Remember Me</label>
		</p>
		<input type="checkbox" id="rememberme" name="remember-me" />

		<div>
			<input name="submit" type="submit" />
		</div>
	</form>

</body>
</html>