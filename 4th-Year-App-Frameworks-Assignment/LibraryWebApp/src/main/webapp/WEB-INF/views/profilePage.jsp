<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html 
     PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
     "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>My Details</title>
	</head>
	<body>
		<h1>My Details</h1>
		<c:if test="${not empty msg}"><p>${msg}</p></c:if> 
		<table>
			<tr>
	  		<td>
				<table>
					<tr>
						<td>User ID:</td>
						<td>${user.id}</td>
					</tr>
						<td>User Name:</td>
						<td>${user.username}</td>
					</tr>
					<tr>
						<!-- BLANK ROW -->
					</tr>
				</table>
			</td></tr>
		</table>
	</body>
</html>