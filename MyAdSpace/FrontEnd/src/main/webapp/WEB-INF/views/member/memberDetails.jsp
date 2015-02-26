<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html 
     PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
     "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">


<form:form commandName="member">
	<form:hidden path="id" />
	
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>Member Details</title>
	</head>
	<body>
		<h1>Member Details</h1>
		<c:if test="${not empty msg}"><p>${msg}</p></c:if> 
		<table>
			<tr>
	  		<td>
				<table>
					<tr>
						<td>Member ID:</td>
						<td>${member.id}</td>
						<td><input type="hidden" name="id" value="${member.id}"></td>
					</tr>
					<tr>
						<td>Name:</td>
						<td>${member.name}</td>
						<td><input type="hidden" name="name" value="${member.name}"></td>	
					</tr>
					<tr>
						<td>Address 1:</td>
						<td>${member.address1}</td>
						<td><input type="hidden" name="address1" value="${member.address1}"></td>
					</tr>
					<tr>
						<td>Address 2:</td>
						<td>${member.address2}</td>
						<td><input type="hidden" name="address2" value="${member.address2}"></td>
					</tr>
					<tr>
						<td>Town:</td>
						<td>${member.town}</td>
						<td><input type="hidden" name="town" value="${member.town}"></td>
					</tr>
					<tr>
						<td>Contact No:</td>
						<td>${member.contact_number}</td>
						<td><input type="hidden" name="contact_number" value="${member.contact_number}"></td>
					</tr>
					<tr>
						<td>Book Allowance:</td>
						<td>${member.book_allowance}</td>
						<td><input type="hidden" name="book_allowance" value="${member.book_allowance}"></td>
					</tr>
					<tr>
						<td>Active:</td>
						<td>${member.active}</td>
						<td><input type="hidden" name="active" value="${member.active}"></td>
					</tr>
					<tr>
						<td>Balance:</td>
						<td>&euro;<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${member.balance}"/></td>
					</tr>
					<tr>
						<td></td>
						<td>
							<c:choose>
    							<c:when test="${member.balance > 0.00}">
									<form:input path="balance" value="0.00" onFocus="this.value=''" /><br/>
									<form:errors path="balance" cssClass="errorMsg" />
									<input type="submit" value="Pay Fine" />
    							</c:when>
    							<c:otherwise>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
				</table>
			</td></tr>
		</table>
	</body>
</html>

</form:form>