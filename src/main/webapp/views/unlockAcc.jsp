<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Unlock Account Page</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	
</script>
</head>
<body>

	<div align="center">
		<h3>Unlock Account Here</h3>

		<form:form action="unlockAccount?email=${userAcc.email }"
			method="POST" modelAttribute="userAcc">

			<font color="red">${failMsg}</font>
			<font color="green">${succMsg}</font>
			<table>
				<tr>
					<td>Email :</td>
					<td>${userAcc.email}</td>
				</tr>
				<tr>
					<td>Temp Password :</td>
					<td><form:password path="tempPwd" /></td>
				</tr>
				<tr>
					<td>Choose New Password :</td>
					<td><form:password path="newPwd" />
				</tr>
				<tr>
					<td>Confirm Password :</td>
					<td><form:password path="confirmNewPwd" />
				</tr>

				<tr>
					<td><input type="reset" value="Reset" /></td>
					</tr>
					<tr>
					<td><input type="submit" value="Submit" id="unlock Account" /></td>
				</tr>
			</table>

		</form:form>
	</div>

</body>
</html>