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
		<c:when test="${not empty loginCustomer}">
			<h1>Customer's Phone</h1>
			<form action="CustomerCallServlet" method="get">
				<label>Connecting with Customer Care via Call</label><br>
				<label>Customer Code: <c:out value="${loginCustomer.code}" /></label><br>
				<button type="submit" name="call" value="end">End Call</button>
			</form>
			<script>
				var handleQueue = function() {
					var request = new XMLHttpRequest();
					request.open("GET", "CustomerQueueServlet?customercode=${loginCustomer.code}", true);
					request.onreadystatechange = function() {
						if (this.readyState == 4 && this.status == 200) {
							if (request.responseText !== "false")
								handleQueue();
							else
								window.location.replace('CustomerCallServlet?call=end');
						}
					};
					request.send();
				};
				handleQueue();
			</script>
		</c:when>
		<c:otherwise>
			<script>window.location.replace('CustomerIdle.jsp');</script>
		</c:otherwise>
	</c:choose>
</body>
</html>