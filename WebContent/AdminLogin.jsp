<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
button, input, label {
	margin: 2px 0;
}
</style>
</head>
<body>
	<c:choose>
		<c:when test="${not empty loginAdmin and loginAdmin == pageContext.session.id}">
			<script>window.location.replace('AdminDB.jsp');</script>
		</c:when>
		<c:otherwise>
			<h1>Administrator Login</h1>
			<form action="LoginServlet" method="post">
				<label>User Name: </label>
				<input type="text" name="username"><br>
				<label>Password: </label>
				<input type="password" name="password"><br>
				<button type="submit" name="login" value="admin">Login</button>
			</form>
		</c:otherwise>
	</c:choose>
</body>
</html>