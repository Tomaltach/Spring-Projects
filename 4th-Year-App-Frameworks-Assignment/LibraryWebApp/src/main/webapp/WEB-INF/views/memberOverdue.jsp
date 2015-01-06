<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:if test="${not empty msg}"><p>${msg}</p></c:if>

<ul>
	<c:forEach items="${members}" var="member">
		<li>
			<sec:authorize access="isAuthenticated()">
				<a href="${member.id}">${member.id}</a> - Name: ${member.name}
			</sec:authorize>			
		</li>
	</c:forEach>
</ul>
