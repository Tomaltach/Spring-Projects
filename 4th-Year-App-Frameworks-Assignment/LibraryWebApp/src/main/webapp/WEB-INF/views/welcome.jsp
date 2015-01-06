<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<h3>
	Welcome <u><i>${pageContext.request.userPrincipal.name}</i></u>
</h3>

<p>
	<a href="<c:url value='/book'/>">View Books</a>
</p>
<p>
	<a href="<c:url value='/member'/>">View Members</a>
</p>
<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_ROOT')">
	<p>
		<a href="<c:url value='/loan'/>">View Loans</a>
	</p>
</sec:authorize>
<p>
	Click <a href="<c:url value="j_spring_security_logout" />">here</a> to logout.
</p>