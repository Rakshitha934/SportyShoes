<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1><i>ADD Product</i></h1>
<form action="insert" method="post" enctype="multipart/form-data">
pname<input type="text" name="pname"><br>
pcost<input type="text" name="pcost"><br>
productimage<input type="file" name="file"><br>
<input type="submit" value="add">
</form>
</body>
</html>
