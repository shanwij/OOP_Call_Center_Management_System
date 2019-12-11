<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
button {
	margin: 2px 0;
}
</style>
</head>
<body>
	<c:choose>
		<c:when test="${not empty loginAgent}">
			<form action="LogoutServlet" method="get">
				<h1>Agent Name: <c:out value="${loginAgent.name}" /></h1>
				<p>Agent Code: <c:out value="${loginAgent.code}" /></p>
				<button type="submit" name="logout" value="agent">Logout</button>
			</form>
			<div class="not-available">
				<h1>Call Not Available, Please Wait...</h1>
			</div>
			<form style="display: none;" class="available" action="AgentCallServlet" method="get">
				<h1>Call Available</h1>
				<label>Customer Code: </label>
				<label class="customer-code"></label><br>
				<input type="hidden" name="agentcode" value="${loginAgent.code}">
				<input type="hidden" name="customercode">
				<button type="submit" name="call" value="start">Start call</button>
			</form>
			<script>
				var handleQueue = function() {
					var request = new XMLHttpRequest();
					request.open("GET", "AgentQueueServlet?agentcode=${loginAgent.code}", true);
					request.onreadystatechange = function() {
						if (this.readyState == 4 && this.status == 200) {
							updateInterface(request.responseText === "null" ? null : request.responseText);
							console.log(request.responseText);
							handleQueue();
						}
					};
					request.send();
				};
				var updateInterface = function(customerCode) {
					var available = document.querySelector(".available");
					var notAvailable = document.querySelector(".not-available");
					var customerCodeLabel = document.querySelector(".customer-code");
					var customerCodeInput = document.querySelector("input[name=customercode]");
					if (customerCode) {
						available.style.display = null;
						customerCodeLabel.innerHTML = customerCode;
						customerCodeInput.value = customerCode;
						notAvailable.style.display = "none";
					} else {
						available.style.display = "none";
						customerCodeLabel.innerHTML = null;
						customerCodeInput.value = null;
						notAvailable.style.display = null;
					}
				}
				handleQueue();
			</script>
		</c:when>
		<c:otherwise>
			<script>window.location.replace('AgentLogin.jsp');</script>
		</c:otherwise>
	</c:choose>
</body>
</html>