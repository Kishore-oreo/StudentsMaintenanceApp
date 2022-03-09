 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Page</title>
</head>
<body>
<div align="center">
<h1>Admin Login</h1>
<form action="login" method="post">
<table>
<tr><td>User Name:</td><td><input type="text" name="username"></td></tr>
<tr><td>Password :</td><td><input type="password" name="password"></td></tr>
<tr><td></td><td><input type="submit" value="Login"></td></tr>
</table>
</form>
</div>


<div class="container text-left">

				<a href="<%=request.getContextPath()%>/others.jsp"  class="btn btn-success">User Login</a>
			</div>
</body>

</html>