<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:if test="${not empty msg}"><p>${msg}</p></c:if>

<sec:authorize access="hasRole('ROLE_ROOT')">
	<a href="<c:url value="/admin/add" />"><spring:message code="admin.add.link" text="Add Admin"/></a>
</sec:authorize>

<ul>
	<c:forEach items="${admins}" var="admins">
		<li>
			<sec:authorize access="isAuthenticated()">
				<a href="admin/${admin.id}">${admin.name}</a>
				<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_ROOT')">
					 <a href="admin/${admin.id}/delete">[Delete]</a>
				</sec:authorize>
			</sec:authorize>			
		</li>
	</c:forEach>
</ul>
