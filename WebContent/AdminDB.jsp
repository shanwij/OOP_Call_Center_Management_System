<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
a {
   -webkit-appearance: button;
   -moz-appearance: button;
   appearance: button;
   padding: 3px 8px;
   margin: 2px 0;
   color: black;
   font-family: sans-serif;
   font-size: 0.8em;
   text-decoration: none;
}
button, label, a {
	margin: 2px 0;
}
</style>
</head>
<body>
	<c:choose>
		<c:when test="${not empty loginAdmin and loginAdmin == pageContext.session.id}">
			<div>
				<a href="LogoutServlet?logout=admin">Logout</a>
			</div>
			<form action="DBListServlet" method="get">
				<button type="submit" name="dblist" value="agent">Manage Agents</button>
				<button type="submit" name="dblist" value="contact">Manage Service Numbers</button>
				<button type="submit" name="dblist" value="customer">View Engaged Customers</button>
				<button type="submit" name="dblist" value="issue">View Past Issues</button>
				<button type="submit" name="dblist" value="complaint">Review Complaints</button>
			</form>
		</c:when>
		<c:otherwise>
			<script>window.location.replace('AdminLogin.jsp');</script>
		</c:otherwise>
	</c:choose>
</body>
</html>