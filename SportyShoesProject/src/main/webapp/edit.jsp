<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1><i>Product  Edit Form</i></h1>
<form action="updatedata">
<input type="hidden" name="pid" value=<%=request.getParameter("pid") %>>
Updated Product Name<input type="text" name="pname"><br>
Update Product cost<input type="text" name="cost"><br>

<input type="submit" value="Update">
</form>
</body>
</html>
