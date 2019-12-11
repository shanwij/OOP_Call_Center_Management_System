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
			<h1>Edit Agent</h1>
			<form action="DBUpdateServlet" method="get">
				<input type="hidden" name="redirect" value="DBListServlet?dblist=agent">
				<label>Agent Code: ${oldagent.code}</label>
				<input type="hidden" name="agentcode" value="${oldagent.code}"><br>
				<label>Agent Name:</label>
				<input type="text" name="agentname" value="${oldagent.name}"><br>
				<button name="dbupdate" value="agent">Edit</button>
			</form>
		</c:when>
		<c:when test="${databaselist == 'contact'}">
			<h1>Edit Contact</h1>
			<form action="DBUpdateServlet" method="get">
				<input type="hidden" name="redirect" value="DBListServlet?dblist=contact">
				<label>Contact Code: ${oldcontact.code}</label>
				<input type="hidden" name="contactcode" value="${oldcontact.code}"><br>
				<label>Contact Type: ${oldcontact.type}</label>
				<input type="hidden" name="contacttype" value="${oldcontact.type}"><br>
				<label>Contact Description:</label>
				<input type="text" name="contactdesc" value="${oldcontact.desc}"><br>
				<button name="dbupdate" value="contact">Edit</button>
			</form>
		</c:when>
		<c:when test="${databaselist == 'issue'}">
			<h1>Edit Issue</h1>
			<form action="DBUpdateServlet" method="get">
				<input type="hidden" name="redirect" value="AgentCallServlet?agentcode=${oldissue.agentCode}&customercode=${oldissue.customerCode}&call=start">
				<input type="hidden" name="agentcode" value="${oldissue.agentCode}">
				<input type="hidden" name="customercode" value="${oldissue.customerCode}">
				<label>Issue Code: ${oldissue.code}</label>
				<input type="hidden" name="issuecode" value="${oldissue.code}"><br>
				<label>Issue Description:</label>
				<input type="text" name="issuedesc" value="${oldissue.desc}"><br>
				<button name="dbupdate" value="issue">Edit</button>
			</form>
		</c:when>
	</c:choose>
</body>
</html>