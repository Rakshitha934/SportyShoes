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
<th>Edit Action</th>
<th>Delete Action</th>
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

<td><a href="edit.jsp?pid=<%=product.getPid()%>">Edit</td>
<td><a href="delete?pid=<%=product.getPid()%>">Delete</a></td>
</tr>
<%} %>
</table>
<body>

</body>
</html>
