<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
 
<div id="header">
	<img src="<c:url value="/resources/images/library-logo.png"/>"/>
	 
	<div style="float:right;">
		<a href="?lang=en"><img src="<c:url value="/resources/images/flagGb.png"/>" alt="GB"/></a>
		<a href="?lang=fr"><img src="<c:url value="/resources/images/flagFr.png"/>" alt="FR"/></a>
		<a href="?lang=de"><img src="<c:url value="/resources/images/flagDe.png"/>" alt="DE"/></a>
		<sec:authorize access="hasRole('ROLE_USER')">
			<a href="<c:url value="/user/${pageContext.request.userPrincipal.name}"/>">My Profile</a> |
		</sec:authorize>
		<sec:authorize access="isAuthenticated()">			
			<!-- <a href="<c:url value="/user"/>">My Profile</a> | -->
			<a href="<c:url value="/j_spring_security_logout"/>">Logout</a>
		</sec:authorize>
	</div>
		
</div>