<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty msg}"><p>${msg}</p></c:if>

<form:form commandName="loan">
	<form:hidden path="id" />
	<table align="center">
		<tr>
			<td>Member ID:</td>
			<td>
				<form:input path="member_id" /><br/>
				<form:errors path="member_id" cssClass="errorMsg" />
			</td>
		</tr>
		<tr>
			<td>Book ID:</td>
			<td>
				<form:input path="book_id" /><br/>
				<form:errors path="book_id" cssClass="errorMsg" />
			</td>
		</tr>
		<tr>
			<td>
				<td><input type="hidden" name="loan_date" value="01/01/1970"></td>
				<td><input type="hidden" name="return_date" value="01/01/1970"></td>
				<td><input type="hidden" name="fine" value="0.0"></td>
			</td>
		</tr>
		<tr>
			<td></td>
			<td><input type="submit" value="Submit" /></td>
		</tr>
	</table>
</form:form>