<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty msg}"><p>${msg}</p></c:if>

<html>
	<head>
		<script>
			function goBack() {
  				window.history.back();
  			}
		</script>
		<body>
			<input type="button" value="Back" onclick="goBack()">
		</body>
	</head>
</html>

<%
	int i = 0; 
%>
<c:forEach items="${books}" var="book" varStatus="status">
	<% 
		i++; 
	%>
</c:forEach>
<p>
	<%out.print(i);%> results found 
</p>

<ul>
	<c:forEach items="${books}" var="book" varStatus="status">
		<li>
			<a href="book/${book.id}">${book.title}</a> - <c:out value="${book.available}" />
		</li>
	</c:forEach>
</ul>
