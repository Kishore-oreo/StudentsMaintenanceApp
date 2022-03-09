<%--session handle --%>
<%
response.setHeader("Cache-Control", "no-cache,no-store, must-revalidate");

if (session.getAttribute("username") == null) {
	response.sendRedirect("index.jsp");
}
%>
<html>
<head>
<title>File Upload</title>
</head>
<body>
	<h2>Hello Admin!</h2>

	<form action="uploadServlet" method="post" enctype="multipart/form-data">

		<input type="file" name="file" multiple /> <input type="submit"
			value="Upload">


	</form>
</body>
</html>
