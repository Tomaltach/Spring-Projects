<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authorize access="isAuthenticated()">
	<a href="<c:url value="/welcome"/>">Home</a> |
	<a href="<c:url value="/search"/>">Search</a> | 
	<a href="<c:url value="/book"/>">Books</a> |
	<a href="<c:url value="/member"/>">Members</a>
	<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_ROOT')">
		| <a href="<c:url value="/loan"/>">Loans</a>
		| <strike><a href="<c:url value="/admin"/>">Admin</a></strike>
	</sec:authorize>
</sec:authorize>