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
		<c:when test="${databaselist == 'agent'}">
			<h1>Add Agent</h1>
			<form action="DBAddServlet" method="get">
				<input type="hidden" name="redirect" value="DBListServlet?dblist=agent">
				<label>Agent Code:</label>
				<input type="text" name="agentcode"><br>
				<label>Agent Name:</label>
				<input type="text" name="agentname"><br>
				<button name="dbadd" value="agent">Add</button>
			</form>
		</c:when>
		<c:when test="${databaselist == 'contact'}">
			<h1>Add Contact</h1>
			<form action="DBAddServlet" method="get">
				<input type="hidden" name="redirect" value="DBListServlet?dblist=contact">
				<label>Contact Code:</label>
				<input type="text" name="contactcode"><br>
				<label>Contact Type:</label>
				<input type="text" name="contacttype"><br>
				<label>Contact Description:</label>
				<input type="text" name="contactdesc"><br>
				<button name="dbadd" value="contact">Add</button>
			</form>
		</c:when>
		<c:when test="${databaselist == 'issue'}">
			<h1>Add Issue</h1>
			<form action="DBAddServlet" method="get">
				<input type="hidden" name="redirect" value="AgentCallServlet?agentcode=${agentref}&customercode=${customerref}&call=start">
				<input type="hidden" name="agentcode" value="${agentref}">
				<input type="hidden" name="customercode" value="${customerref}">
				<label>Issue Code:</label>
				<input type="text" name="issuecode"><br>
				<label>Issue Description:</label>
				<input type="text" name="issuedesc"><br>
				<button name="dbadd" value="issue">Add</button>
			</form>
		</c:when>
	</c:choose>
</body>
</html>