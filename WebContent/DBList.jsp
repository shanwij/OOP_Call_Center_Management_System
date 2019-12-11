<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
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
</style>
</head>
<body>
    <a href="AdminDB.jsp">Go back to menu selection</a>
	<h1>
		<c:choose>
			<c:when test="${param['dblist'] == 'agent'}">
				Manage Agents
			</c:when>
			<c:when test="${param['dblist'] == 'contact'}">
				Manage Service Numbers
			</c:when>
			<c:when test="${param['dblist'] == 'customer'}">
				View Engaged Customers
			</c:when>
			<c:when test="${param['dblist'] == 'issue'}">
				View Past Issues
			</c:when>
			<c:when test="${param['dblist'] == 'complaint'}">
				Review Complaints
			</c:when>
		</c:choose>
	</h1>
	<table>
		<c:choose>
			<c:when test="${param['dblist'] == 'agent'}">
				<th>Agent Code</th>
				<th>Agent Name</th>
				<th class="link"><a href="DBAddServlet?dblist=agent">Add</a></th>
			</c:when>
			<c:when test="${param['dblist'] == 'contact'}">
				<th>Contact Code</th>
				<th>Contact Type</th>
				<th>Contact Description</th>
				<th class="link"><a href="DBAddServlet?dblist=contact">Add</a></th>
			</c:when>
			<c:when test="${param['dblist'] == 'customer'}">
				<th>Customer Code</th>
				<th>Customer Status</th>
			</c:when>
			<c:when test="${param['dblist'] == 'issue'}">
				<th>Issue Code</th>
				<th>Agent Code</th>
				<th>Customer Code</th>
				<th>Issue Date</th>
				<th>Issue Description</th>
				<th>Issue Status</th>
			</c:when>
			<c:when test="${param['dblist'] == 'complaint'}">
				<th>Complaint Code</th>
				<th>Issue Code</th>
				<th colspan="2">Complaint Description</th>
			</c:when>
		</c:choose>
		<c:forEach items="${data}" var="item">
			<tr>
				<c:choose>
					<c:when test="${param['dblist'] == 'agent'}">
						<td><c:out value="${item.code}" /></td>
						<td><c:out value="${item.name}" /></td>
						<td class="link">
							<a href="DBUpdateServlet?dblist=agent&agentcode=${item.code}">Edit</a>
							<a href="DBDeleteServlet?dblist=agent&agentcode=${item.code}">Remove</a>
						</td>
					</c:when>
					<c:when test="${param['dblist'] == 'contact'}">
						<td><c:out value="${item.code}" /></td>
						<td><c:out value="${item.type}" /></td>
						<td><c:out value="${item.desc}" /></td>
						<td class="link">
							<a href="DBUpdateServlet?dblist=contact&contactcode=${item.code}">Edit</a>
							<a href="DBDeleteServlet?dblist=contact&contactcode=${item.code}">Remove</a>
						</td>
					</c:when>
					<c:when test="${param['dblist'] == 'customer'}">
						<td><c:out value="${item.code}" /></td>
						<c:choose>
							<c:when test="${item.statusCode == 'UNREGISTERED'}">
								<td>Unregistered Customer</td>
							</c:when>
							<c:when test="${item.statusCode == 'REGISTERED'}">
								<td>Registered Customer</td>
							</c:when>
						</c:choose>
					</c:when>
					<c:when test="${param['dblist'] == 'issue'}">
						<td><c:out value="${item.code}" /></td>
						<td><c:out value="${item.customerCode}" /></td>
						<td><c:out value="${item.agentCode}" /></td>
						<td><c:out value="${item.date}" /></td>
						<td><c:out value="${item.desc}" /></td>
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
					</c:when>
					<c:when test="${param['dblist'] == 'complaint'}">
						<td><c:out value="${item.code}" /></td>
						<td><c:out value="${item.issueCode}" /></td>
						<td><c:out value="${item.desc}" /></td>
						<td class="link"><a href="DBStatusChangeServlet?dblist=complaint&complaintcode=${item.code}">Close Complaint</a></td>
					</c:when>
				</c:choose>
			</tr>
		</c:forEach>
	</table>
</body>
</html>