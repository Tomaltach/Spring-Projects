<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty msg}"><p>${msg}</p></c:if>

<form:form commandName="member">
	<form:hidden path="id" />
	<table align="center">
		<tr>
			<td>Name:</td>
			<td>
				<form:input path="name" /><br/>
				<form:errors path="name" cssClass="errorMsg" />
			</td>
		</tr>
		<tr>
			<td>Address 1:</td>
			<td>
				<form:input path="address1" /><br/>
				<form:errors path="address1" cssClass="errorMsg" />
			</td>
		</tr>
		<tr>
			<td>Address 2:</td>
			<td>
				<form:input path="address2" /><br/>
				<form:errors path="address2" cssClass="errorMsg" />
			</td>
		</tr>
		<tr>
			<td>Town:</td>
			<td>
				<form:input path="town" /><br/>
				<form:errors path="town" cssClass="errorMsg" />
			</td>
		</tr>
		<tr>
			<td>Contact No:</td>
			<td>
				<form:input path="contact_number" /><br/>
				<form:errors path="contact_number" cssClass="errorMsg" />
			</td>
		</tr>
		<tr>
			<td>Book Allowance:</td>
			<td>
				<form:input path="book_allowance" /><br/>
				<form:errors path="book_allowance" cssClass="errorMsg" />
			</td>
		</tr>
		<tr>
			<td>Balance:</td>
			<td>
				<form:input path="balance" /><br/>
				<form:errors path="balance" cssClass="errorMsg" />
			</td>
		</tr>
		<tr>
			<td>Active:</td>
			<td>
				<form:input path="active" /><br/>
				<form:errors path="active" cssClass="errorMsg" />
			</td>
		</tr>
		<tr>
			<td></td>
			<td><input type="submit" value="Submit" /></td>
		</tr>
	</table>
</form:form>