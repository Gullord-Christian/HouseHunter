 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!-- for the date format  -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Error Page</title>
<link rel="stylesheet" type="text/css" href="/css/error.css">
</head>
<body>
	<div class="error-image-container">
		<img class="error-image" src="images/dizzy-robot.png" >
	</div>
	<h1 class="error-title">Uh oh, looks like there was an error!</h1>
	<div class="error-message-container">
			<div class="err-div"> First Name must be at least 3 characters </div>
			<div class="err-div"> Last Name must be at least 3 characters </div>
			<div class="err-div"> Email must be valid </div>
			<div class="err-div"> Password must be at least 8 characters </div>
			<div class="err-div"> Passwords must match </div>
			<div class="err-div"> Please select Realtor or Buyer </div>
	</div>
	<div class="error-btn-container">
	<a href="/"><button class="error-reg-btn">Register here</button></a>
	</div>
</body>
</html>