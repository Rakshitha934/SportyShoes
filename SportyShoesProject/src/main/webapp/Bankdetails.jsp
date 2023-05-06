<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<fieldset>
<center>
<h1><i> Make a Payment</i></h1> 

<form action="bankcontroller">
The Cost of the Product is : <%=request.getParameter("pcost")%>
<input type="hidden" name="pcost" value=<%=request.getParameter("pcost") %>>
<br>
<br>
CheckOut :<input type="submit" value="Pay">
</form>


</center>
</fieldset>
</body>
</html>


