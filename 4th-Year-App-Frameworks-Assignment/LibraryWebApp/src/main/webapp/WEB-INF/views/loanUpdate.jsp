<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html 
     PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
     "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">


<form:form commandName="loan">
	<form:hidden path="id" />
	
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>Loan Details</title>
	</head>
	<body>
		<h1>Loan Details</h1>
		<c:if test="${not empty msg}"><p>${msg}</p></c:if> 
		<table>
			<tr>
	  		<td>
				<table>
					<tr>
						<td>Loan ID:</td>
						<td>${loan.id}</td>
						<td><input type="hidden" name="id" value="${loan.id}"></td>
					</tr>
					<tr>
						<td>Member ID:</td>
						<td>${loan.member_id}</td>
						<td><input type="hidden" name="member_id" value="${loan.member_id}"></td>
					</tr>
					<tr>
						<td>Book ID:</td>
						<td>${loan.book_id}</td>
						<td><input type="hidden" name="book_id" value="${loan.book_id}"></td>
					</tr>
					<tr>
						<td>Loan Date:</td>
						<td><fmt:formatDate value="${loan.loan_date}" pattern="dd-MM-yyyy" /></td>
						<td><input type="hidden" name="loan_date" value="<fmt:formatDate value="${loan.loan_date}" pattern="dd/MM/yyyy" />"></td>
					</tr>
					<tr>
						<td>Return Date:</td>
						<td>
							<form:input path="return_date" />
							<form:errors path="return_date" cssClass="errorMsg" />
						</td>
					</tr>
					<tr>
						<td>Fine:</td>
						<td>&euro;<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${loan.fine}"/></td>						
						<td><input type="hidden" name="fine" value="${loan.fine}"></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="Submit" /></td>
					</tr>
				</table>
			</td></tr>
		</table>
	</body>
</html>

</form:form>