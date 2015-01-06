<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
 
<div id="header">
	<img src="<c:url value="/resources/images/logo.png"/>"/> THIS IS THE TEST SECTION!
	
	<div style="float:right;">
		<sec:authorize access="isAuthenticated()">
			<a href="<c:url value="/j_spring_security_logout"/>">Logout</a>
		</sec:authorize>
	</div>
		
</div>