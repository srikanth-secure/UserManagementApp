<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Forgot Password Page</title>

</head>
<body>

	<div align="center">
		<h3>Recover Password Here</h3>

		<font color="red">${failMsg}</font> <font color="green">${succMsg}</font>
		<form action="forgotPwd">
			<table>
				<tr>
					<td>Email :</td>
					<td><input type="text" name="email" /></td>
				</tr>

				<tr>
					<td><input type="reset" value="Reset" /></td>
					<td><input type="submit" value="Submit" id="unlock Account" /></td>
				</tr>
			</table>

		</form>
	</div>

</body>
</html>