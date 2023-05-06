<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<fieldset>
<center>
<body>
<h1><i>The Product Details are Below</i></h1>
<form action="Bankdetails.jsp">
 Product ID:  <%=request.getParameter("pid") %><br><br>
 Product Name :   <%=request.getParameter("pname") %><br><br>
 Product Cost :   <%=request.getParameter("pcost") %><br><br>
 <input type="hidden" name="pcost" value=<%=request.getParameter("pcost") %>>
 
 <input type="submit" value ="Buy">
 </form>
 </center>
 </fieldset>
</body>
</html>