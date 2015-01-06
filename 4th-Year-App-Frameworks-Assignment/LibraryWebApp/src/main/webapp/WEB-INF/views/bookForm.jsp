<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty msg}"><p>${msg}</p></c:if>

<form:form commandName="book">
	<form:hidden path="id" />
	<table align="center">
		<tr>
			<td>Title:</td>
			<td>
				<form:input path="title" /><br/>
				<form:errors path="title" cssClass="errorMsg" />
			</td>
		</tr>
		<tr>
			<td>Author:</td>
			<td>
				<form:input path="author" /><br/>
				<form:errors path="author" cssClass="errorMsg" />
			</td>
		</tr>
		<tr>
			<td>Publisher:</td>
			<td>
				<form:input path="publisher" /><br/>
				<form:errors path="publisher" cssClass="errorMsg" />
			</td>
		</tr>
		<tr>
			<td>Publication Date:</td>
			<td>
				<form:input path="publication_date" /><br/>
				<form:errors path="publication_date" cssClass="errorMsg" />
			</td>
		</tr>
		<tr>
			<td>ISBN:</td>
			<td>
				<form:input path="isbn" /><br/>
				<form:errors path="isbn" cssClass="errorMsg" />
			</td>
		</tr>
		<tr>
			<td>Available:</td>
			<td>
				<form:input path="available" /><br/>
				<form:errors path="available" cssClass="errorMsg" />
			</td>
		</tr>
		<tr>
			<td></td>
			<td><input type="submit" value="Submit" /></td>
		</tr>
	</table>
</form:form>