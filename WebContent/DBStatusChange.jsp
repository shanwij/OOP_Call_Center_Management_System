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
		<c:when test="${databaselist == 'issue'}">
			<c:choose>
				<c:when test="${status == 'COMPLAINT'}">
					<h1>File a Complaint</h1>
				</c:when>
				<c:when test="${status == 'CLOSED'}">
					<h1>Close Issue</h1>
				</c:when>
			</c:choose>
			<form action="DBStatusChangeServlet" method="get">
				<input type="hidden" name="redirect" value="AgentCallServlet?agentcode=${oldissue.agentCode}&customercode=${oldissue.customerCode}&call=start">
				<c:choose>
					<c:when test="${status == 'COMPLAINT'}">
						<label>Issue Code: ${oldissue.code}</label><br>
						<input type="hidden" name="issuecode" value="${oldissue.code}">
						<label>Complaint Code:</label>
						<input type="text" name="complaintcode"><br>
						<label>Complaint Description:</label>
						<input type="text" name="complaintdesc"><br>
						<label>Click "Confirm" button to mark ${oldissue.code} as closed. click browser back button to cancel.</label><br>
						<button type="submit" name="dbstatuschange" value="issuecomplaint">Confirm</button>
					</c:when>
					<c:when test="${status == 'CLOSED'}">
						<label>Click "Confirm" button to mark ${oldissue.code} as closed. click browser back button to cancel.</label><br>
						<input type="hidden" name="issuecode" value="${oldissue.code}">
						<button type="submit" name="dbstatuschange" value="issueclose">Confirm</button>
					</c:when>
				</c:choose>
			</form>
		</c:when>
		<c:when test="${databaselist == 'complaint'}">
			<h1>Close Complaint</h1>
			<form action="DBStatusChangeServlet" method="get">
				<input type="hidden" name="redirect" value="DBListServlet?dblist=complaint">
				<label>Click "Confirm" button to mark ${oldcode} as closed. click browser back button to cancel.</label><br>
				<input type="hidden" name="complaintcode" value="${oldcode}">
				<button type="submit" name="dbstatuschange" value="complaintclose">Confirm</button>
			</form>
		</c:when>
	</c:choose>
</body>
</html>