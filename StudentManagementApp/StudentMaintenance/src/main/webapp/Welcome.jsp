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
<html>
<head>
<meta charset="ISO-8859-1">
<title>Here u are</title>
</head>
<body>

  <%
  response.setHeader("Cache-Control","no-cache,no-store, must-revalidate");
     if(session.getAttribute("username")==null)
     {
    	 response.sendRedirect("index.jsp");
     }
  %>
Welcome ${username}

<div class="container text-left">
			<a href="<%=request.getContextPath()%>/user" align="right"
				class="btn btn-success" >Students List</a>


		</div>


</body>
</html>