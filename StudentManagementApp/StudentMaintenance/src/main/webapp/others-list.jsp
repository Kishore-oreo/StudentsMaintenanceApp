<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
	<%--session handle --%>
  <%
  response.setHeader("Cache-Control","no-cache,no-store, must-revalidate"); 
  
  if(session.getAttribute("username")==null)
     {
    	 response.sendRedirect("index.jsp");
     }
  %>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Other Users Manager</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: blue">
			<div>
				<a href="https://www.xadmin.net" class="navbar-brand"> Other
					User Manager</a>
			</div>

		</nav>
	</header>
	<br>

	<div class="row">


		<div class="container">
			<h3 class="text-center">List of Other Users</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/others-form.jsp"
					class="btn btn-success">Add New Users</a>
			</div>
			<div class="container text-right">

			<a href="<%=request.getContextPath()%>/upload.jsp" 	
				class="btn btn-success">Upload</a>
		</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>User name</th>
						<th>Password</th>
						<!-- <th>Actions	</th> -->
					</tr>
				</thead>
				<tbody>

					<c:forEach var="otheruser" items="${listUser}">

						<tr>
							<td><c:out value="${otheruser.id}" /></td>
							<td><c:out value="${otheruser.username}" /></td>
							<td><c:out value="${otheruser.password}" /></td>

							<!--<td><a href="edit?id=<c:out value='${otheruser.id}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="delete?id=<c:out value='${otheruser.id}' />">Delete</a></td>-->
						</tr>
					</c:forEach>

				</tbody>

			</table>

		</div>
		<div class="container text-left">

			<a href="<%=request.getContextPath()%>/user" class="btn btn-success">Students
				List</a>
		</div>
		<div class="container text-right">

			<a href="<%=request.getContextPath()%>/LogoutServlet" align="left"
				class="btn btn-success">Log out</a>
		</div>
	</div>
</body>
</html>