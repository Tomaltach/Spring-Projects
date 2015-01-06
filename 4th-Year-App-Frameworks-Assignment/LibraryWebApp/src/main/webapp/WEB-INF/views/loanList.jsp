<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!-- to get member count -->
<%
	int i = 0; 
%>
<c:forEach items="${loans}" var="loan" varStatus="status">
	<% 
		i++; 
	%>
</c:forEach>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>Loans List</title>
	</head>
	<body>
		<c:if test="${not empty msg}"><p>${msg}</p></c:if>	
		<p>
			<%out.print(i);%> Loans Listed 
		</p>
		<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_ROOT')">
			<p>		
				<a href="loan/overdue">Overdue Loans</a>
			</p>
			<a href="<c:url value="/loan/add" />"><spring:message code="loan.add.link" text="Add Loans"/></a>
		</sec:authorize>
		<table>
			<tr>
	  			<td>
					<table>
						<c:forEach items="${loans}" var="loan">
							<tr>
								<sec:authorize access="isAuthenticated()">
									<td><a href="loan/${loan.id}">${loan.id}</a> - Member ID: ${loan.member_id}</td>
									<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_ROOT')">
							 			<td><a href="loan/${loan.id}/update">[Update]</a></td>
					 					<td><a href="loan/${loan.id}/delete">[Delete]</a></td>
									</sec:authorize>
								</sec:authorize>
							</tr>
						</c:forEach>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>
