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
	box-sizing: border-box;
	width: 50px;
	height: 50px;
	font-size: 1.2em;
}
button[type="submit"] {
	width: 237px;
}
button[name="clear"] {
	width: 75px;
}
input {
	box-sizing: border-box;
	width: 158px;
	height: 50px;
	font-size: 1.2em;
}
input[name="customercode"] {
	width: 237px;
}
button, input, label {
	margin: 2px 0;
}
</style>
</head>
<body>
	<c:choose>
		<c:when test="${not empty loginCustomer}">
			<script>window.location.replace('CustomerCall.jsp');</script>
		</c:when>
		<c:otherwise>
			<h1>Customer's Phone</h1>
			<form name="dialer" action="CustomerCallServlet" method="get">
				<div class="dialer">
					<div>
						<label>Customer Code:</label>
					</div>
					<div>
						<input type="text" name="customercode">
					</div>
					<div>
						<label>Customer Interface:</label>
					</div>
					<div>
						<input type="text" name="contactcode">
						<button type="button" name="clear" onclick="clearChar();">Clear</button>
					</div>
					<div>
						<button type="button" onclick="addChar('1');">1</button>
						<button type="button" onclick="addChar('2');">2</button>
						<button type="button" onclick="addChar('3');">3</button>
					</div>
					<div>
						<button type="button" onclick="addChar('4');">4</button>
						<button type="button" onclick="addChar('5');">5</button>
						<button type="button" onclick="addChar('6');">6</button>
					</div>
					<div>
						<button type="button" onclick="addChar('7');">7</button>
						<button type="button" onclick="addChar('8');">8</button>
						<button type="button" onclick="addChar('9');">9</button>
					</div>
					<div>
						<button type="button" onclick="addChar('*');">*</button>
						<button type="button" onclick="addChar('0');">0</button>
						<button type="button" onclick="addChar('#');">#</button>
					</div>
					<div>
						<button type="submit" name="call" value="start">Call</button>
					</div>
					<script>
						function clearChar() {
							var value = document.forms['dialer']['contactcode'].value;
							if (value.length > 0)
								document.forms['dialer']['contactcode'].value = value.substring(0, value.length - 1);
						}
						
						function addChar(value) {
							document.forms['dialer']['contactcode'].value += value;
						}
					</script>
				</div>
			</form>
		</c:otherwise>
	</c:choose>
</body>
</html>