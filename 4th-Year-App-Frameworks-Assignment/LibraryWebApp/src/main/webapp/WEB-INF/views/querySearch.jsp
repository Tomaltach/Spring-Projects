<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty msg}"><p>${msg}</p></c:if>

<form:form commandName="query">
	<form:hidden path="search" />
	<table align="center">
		<tr>
			<td>Query Books:</td>
			<td>
				<form:input path="search" value="" /><br/>
				<form:errors path="search" cssClass="errorMsg" />
			</td>
		</tr>
		<tr>
			<td></td>
			<td><input type="submit" value="Submit" /></td>
		</tr>
	</table>
</form:form>