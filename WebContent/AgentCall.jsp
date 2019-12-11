<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
table {
	width: 100%;
}
th, td {
	text-align: left;
}
.link {
	text-align: right;
}
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
button {
	margin: 2px 0;
}
</style>
</head>
<body>
	<c:choose>
		<c:when test="${not empty loginAgent}">
			<h1>Agent Name: <c:out value="${loginAgent.name}" /></h1>
			<p>Agent Code: <c:out value="${loginAgent.code}" /></p>
			<form action="AgentCallServlet" method="get">
				<h1>Customer Details</h1>
				<p>Customer Code:  <c:out value="${customercode}" /></p>
				<input type="hidden" name="customercode" value="${customercode}">
				<p>Customer Registration: 
					<c:choose>
						<c:when test="${customerstatus == 'UNREGISTERED'}">
							<span>Unregistered Customer</span>
						</c:when>
						<c:when test="${customerstatus == 'REGISTERED'}">
							<span>Registered Customer</span>
						</c:when>
					</c:choose>
				</p>
				<button type="submit" name="call" value="end">End call</button>
			</form>
			<h1>Issue Tracker</h1>
			<a href="DBAddServlet?dblist=issue&agentcode=${loginAgent.code}&customercode=${customercode}">Open New Issue</a>
			<table>
				<tr>
					<th>Issue</th>
					<th>Last Modified</th>
					<th colspan="2">Status</th>
				</tr>
				<c:forEach items="${data}" var="item">
					<tr class="selectable">
						<td><c:out value="${item.code} - ${item.desc}" /></td>
						<td><c:out value="${item.date}" /></td>
						<c:choose>
							<c:when test="${item.statusCode == 'ACTIVE'}">
								<td>Issue Active</td>
							</c:when>
							<c:when test="${item.statusCode == 'COMPLAINT'}">
								<td>Filed as a Complaint</td>
							</c:when>
							<c:when test="${item.statusCode == 'CLOSED'}">
								<td>Issue Closed</td>
							</c:when>
						</c:choose>
						<c:choose>
							<c:when test="${item.statusCode == 'ACTIVE'}">
								<td class="link">
									<a href="DBUpdateServlet?dblist=issue&issuecode=${item.code}">Edit Issue</a>
									<a href="DBStatusChangeServlet?dblist=issue&issuecode=${item.code}&status=CLOSED">Close Issue</a>
									<a href="DBStatusChangeServlet?dblist=issue&issuecode=${item.code}&status=COMPLAINT">File a Complaint</a>
									<a href="DBDeleteServlet?dblist=issue&issuecode=${item.code}">Delete Issue</a>
								</td>
							</c:when>
							<c:when test="${item.statusCode == 'COMPLAINT'}">
								<td class="link">
									<a href="DBDeleteServlet?dblist=issue&issuecode=${item.code}">Delete Issue</a>
								</td>
							</c:when>
							<c:when test="${item.statusCode == 'CLOSED'}">
								<td class="link">
									<a href="DBDeleteServlet?dblist=issue&issuecode=${item.code}">Delete Issue</a>
								</td>
							</c:when>
						</c:choose>
					</tr>
				</c:forEach>
			</table>
			<script>
				var handleQueue = function() {
					var request = new XMLHttpRequest();
					request.open("GET", "AgentQueueServlet?agentcode=${loginAgent.code}", true);
					request.onreadystatechange = function() {
						if (this.readyState == 4 && this.status == 200) {
							if (request.responseText !== "${customercode}")
								window.location.replace('AgentCallServlet?call=end&customercode=${customercode}');
							else
								handleQueue();
						}
					};
					request.send();
				};
				handleQueue();
			</script>
		</c:when>
		<c:otherwise>
			<script>window.location.replace('AgentLogin.jsp');</script>
		</c:otherwise>
	</c:choose>
</body>
</html>