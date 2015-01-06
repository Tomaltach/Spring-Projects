<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html 
     PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
     "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

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
					</tr>
					<tr>
						<td>Member ID:</td>
						<td>${loan.member_id}</td>
						<td>
						</td>
					</tr>
					<tr>
						<td>Book ID:</td>
						<td>${loan.book_id}</td>
					</tr>
					<tr>
						<td>Loan Date:</td>
						<td><fmt:formatDate value="${loan.loan_date}" pattern="dd-MM-yyyy" /></td>
					</tr>
					<tr>
						<td>Return Date:</td>
						<td><fmt:formatDate value="${loan.return_date}" pattern="dd-MM-yyyy" /></td>
					</tr>
					<tr>
						<td>Fine:</td>
						<td>&euro;<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${loan.fine}"/></td>
					</tr>
				</table>
			</td></tr>
		</table>
	</body>
</html>