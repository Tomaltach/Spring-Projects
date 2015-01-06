<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html 
     PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
     "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<form:form commandName="book">
	<form:hidden path="id" />
	
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>Book Details</title>
	</head>
	<body>
		<h1>Book Details</h1>
		<c:if test="${not empty msg}"><p>${msg}</p></c:if> 
		<table>
			<tr>
	  		<td>
				<table>
					<tr>
						<td>Book ID:</td>
						<td>${book.id}</td>
						<td><input type="hidden" name="id" value="${book.id}"></td>
					</tr>
					<tr>
						<td>Title:</td>
						<td>${book.title}</td>
						<td><input type="hidden" name="title" value="${book.title}"></td>
					</tr>
					<tr>
						<td>Author:</td>
						<td>${book.author}</td>
						<td><input type="hidden" name="author" value="${book.author}"></td>
					</tr>
					<tr>
						<td>Publisher:</td>
						<td>${book.publisher}</td>
						<td><input type="hidden" name="publisher" value="${book.publisher}"></td>
					</tr>
					<tr>
						<td>Publication Date:</td>
						<td><fmt:formatDate value="${book.publication_date}" pattern="dd/MM/yyyy" /></td>
						<td><input type="hidden" name="publication_date" value="<fmt:formatDate value="${book.publication_date}" pattern="dd/MM/yyyy" />"></td>
					</tr>
					<tr>
						<td>ISBN:</td>
						<td>${book.isbn}</td>
						<td><input type="hidden" name="isbn" value="${book.isbn}"></td>
					</tr>
					<tr>
						<td>Available:</td>
						<td>${book.available}</td>
						<td><input type="hidden" name="available" value="${!book.available}"></td>
					</tr>					
					<sec:authorize access="hasRole('ROLE_USER')">
						<tr>
							<td></td>
							<td>
								<c:choose>
    								<c:when test="${book.available == true}">
 										<input type="submit" value="Take Out" />
    								</c:when>
								</c:choose>
							</td>
						</tr>
					</sec:authorize>
				</table>
			</td></tr>
		</table>
	</body>
</html>


</form:form>