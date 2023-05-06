<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@page import="com.example.demo.*" %>
<%@page import="java.util.*" %> 
<%@page import="java.nio.file.*" %>
<%@page import="java.io.*" %>   
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<%@include file="index.jsp" %> 
<h1>List of Products</h1>
<table border=1>
<tr>
<th>Product id</th>
<th>Image of the product</th>
<th>Product name</th>
<th>Product Cost</th>
<th> Add to Cart</th>
</tr>
<%
List<Product> list=(List<Product>)request.getAttribute("list");
for(Product product:list){
%>
<tr>
<td><%=product.getPid() %></td>
<%String path=product.getFilePath();
byte[] images=Files.readAllBytes(new File(path).toPath());
byte[] encodeBase64=Base64.getEncoder().encode(images);

String base64Encoded=new String(encodeBase64,"UTF-8");
%>

<td><img alt="image" src="data:image/png;base64,<%=base64Encoded%>"></td>
<td><%=product.getPname() %></td>
<td><%=product.getCost() %></td>
<td><a href="AddtoCart.jsp?pid=<%=product.getPid()%>&pname=<%=product.getPname()%>&pcost=<%=product.getCost()%>">Add to Cart</a>
</tr>
<%} %>
</table>
<body>

</body>
</html>
