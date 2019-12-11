<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
button, label {
	margin: 2px 0;
}
</style>
</head>
<body>
	<c:choose>
		<c:when test="${databaselist == 'agent'}">
			<h1>Remove Agent</h1>
			<form action="DBDeleteServlet" method="get">
				<input type="hidden" name="redirect" value="DBListServlet?dblist=agent">
				<label>Click "Confirm" button to remove ${oldcode}. click browser back button to cancel.</label><br>
				<input type="hidden" name="agentcode" value="${oldcode}">
				<button name="dbdelete" value="agent">Confirm</button>
			</form>
		</c:when>
		<c:when test="${databaselist == 'contact'}">
			<h1>Remove Contact</h1>
			<form action="DBDeleteServlet" method="get">
				<input type="hidden" name="redirect" value="DBListServlet?dblist=contact">
				<label>Click "Confirm" button to remove ${oldcode}. click browser back button to cancel.</label><br>
				<input type="hidden" name="contactcode" value="${oldcode}">
				<button name="dbdelete" value="contact">Confirm</button>
			</form>
		</c:when>
		<c:when test="${databaselist == 'issue'}">
			<h1>Remove Issue</h1>
			<form action="DBDeleteServlet" method="get">
				<input type="hidden" name="redirect" value="AgentCallServlet?agentcode=${oldissue.agentCode}&customercode=${oldissue.customerCode}&call=start">
				<label>Click "Confirm" button to remove ${oldissue.code}. click browser back button to cancel.</label><br>
				<input type="hidden" name="issuecode" value="${oldissue.code}">
				<button name="dbdelete" value="issue">Confirm</button>
			</form>
		</c:when>
	</c:choose>
</body>
</body>
</html>