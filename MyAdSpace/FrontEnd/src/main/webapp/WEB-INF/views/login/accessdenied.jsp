<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html 
     PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
     "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<head>
		<title>Welcome</title>
	</head>
	<body>
		<h2>Access Denied</h2>
		<p>You attempted to access a page which you are not authorised to access.</p>
		<p>Click <a href="<c:url value='/login'/>">here</a> if you wish to log in with alternative credentials.</p>
	</body>
</html>