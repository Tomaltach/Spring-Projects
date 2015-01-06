<!DOCTYPE HTML>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<html>
<head>
    <meta charset="utf-8">
    <c:set var="titleKey">
        <tiles:getAsString name="title" />
    </c:set> 
    <title>
        Library Manager | <spring:message code="${titleKey}" text="Giving our members the best possible service!"/>
    </title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/styles.css"/>" >
</head>
<body>
    <div id="mainWrapper">
		<tiles:insertAttribute name="header"/>
        <div id="menu">
        	<tiles:insertAttribute name="menu"/>
        </div>       
    	<div id="mainContent">          
        	<h1><spring:message code="${titleKey}" text="${titleKey}" /></h1> 	        
 	        <tiles:insertAttribute name="body" />     	 
    	</div>       
		<tiles:insertAttribute name="footer" />       
    </div>
</body>
</html>