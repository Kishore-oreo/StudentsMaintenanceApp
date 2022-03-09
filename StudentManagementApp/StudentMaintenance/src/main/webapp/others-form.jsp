<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%--session handle --%>
  <%
  response.setHeader("Cache-Control","no-cache,no-store, must-revalidate"); 
  
  if(session.getAttribute("username")==null)
     {
    	 response.sendRedirect("index.jsp");
     }
  %>
<!DOCTYPE html>
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
				<a href="https://www.xadmin.net" class="navbar-brand"> Other users Manager</a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/OtheruserServlet"
					class="nav-link">Users</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${otheruser != null}">
					<form action="/" method="post">
				</c:if>
				<c:if test="${otheruser == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${otheruser != null}">
            			Edit User
            		</c:if>
						<c:if test="${otheruser == null}">
            			Add New User
            		</c:if>
					</h2>
				</caption>

				<c:if test="${otheruser != null}">
					<input type="hidden" name="id" value="<c:out value='${otheruser.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>User name</label> <input type="text"
						value="<c:out value='${otheruser.username}' />" class="form-control"
						name="username" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Password</label> <input type="text"
						value="<c:out value='${otheruser.password}' />" class="form-control"
						name="password">
				</fieldset>


				<button type="submit" class="btn btn-success">Save</button>
				</form>
				
				
			</div>
		</div>
	</div>
</body>
</html>