<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:if test="${not empty msg}"><p>${msg}</p></c:if>

<ul>
	<c:forEach items="${loans}" var="loan">
		<li>
			<sec:authorize access="isAuthenticated()">
				<a href="${loan.id}">${loan.id}</a> - Member ID: ${loan.member_id}
			</sec:authorize>			
		</li>
	</c:forEach>
</ul>
