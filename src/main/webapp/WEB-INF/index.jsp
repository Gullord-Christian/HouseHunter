
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
<title>Login and Register</title>
<link rel="stylesheet" type="text/css" href="/css/index.css">
<script src="https://kit.fontawesome.com/ce7aa84242.js" crossorigin="anonymous"></script>
</head>
	<body>
		<div class="container">
			<div class="forms-container">
				<h1 class="app-title">House Hunter</h1>
				<div class="login-register">
					<form:form action="/login" method="post" modelAttribute="newLogin" class="login-form">
					<h1 class="title-welcome">Welcome Back!</h1>
						<h2 class="title">Sign in</h2>
						<div class="input-field">
							<i class="fas fa-envelope"></i>
							<form:input path="email" type="email" placeholder="Email" />
						</div>
						<div class="input-field">
							<i class="fas fa-lock"></i>
							<form:input
								type="password"
								path="password"
								placeholder="Password" />
						</div>
						<input type="submit" class="btn solid" value="Login" />
						<br><br>
						<div class="error-container">
							<form:errors path="email" style="color:red; font-weight: bold;" />
							<br>
							<form:errors path="password" style="color:red; font-weight: bold;" />
						</div>
					</form:form>
				</div>

				<div class="register-register">
					<form:form action="/register" method="post" class="register-form" modelAttribute="newUser" >
						<h2 class="title">Register</h2>
						<div class="input-field">
							<i class="fas fa-user"></i>
							<form:input
								type="text"
								path="firstName"
								placeholder="First Name" />
						</div>
						<div class="input-field">
							<i class="fas fa-user"></i>
							<form:input
								type="text"
								path="lastName"
								placeholder="Last Name" />
						</div>
						<div class="input-field">
							<i class="fas fa-envelope"></i>
							<form:input type="email" path="email" placeholder="Email" />
						</div>
						<div class="input-field">
							<i class="fas fa-lock"></i>
							<form:input
								type="password"
								path="password"
								placeholder="Password" />
						</div>
						<div class="input-field">
							<i class="fas fa-lock"></i>
							<form:input
								type="password"
								path="confirm"
								placeholder="Password Confirmation" />
						</div>
						<br>
						<div class="input-field-realtor">
								<i class="fa-solid fa-house">
									Buying or Selling?
								</i>
								 <form:select path="realtor_buyer" class="realtor-buyer-select">
								 <option value="" selected disabled hidden>Select One</option>
								 <option value="Realtor">Realtor</option>
								 <option value="Buyer">Buyer</option>
								 </form:select>
						</div>
						<br>
						<input
							type="submit"
							class="btn solid"
							value="Register" />
						
					</form:form>
				</div>
			</div>

			<div class="panels-container">
				<div class="panel left-panel">
					<div class="content">
						<h3>New here?</h3>
						<p>
							We are glad you've stopped by, please register an
							account and start hunting!
						</p>
						<button
							type="submit"
							class="btn transparent"
							id="register-btn">
							Register now
						</button>
					</div>
					<img src="images/login.svg" alt="loginImage" class="image" />
				</div>

				<div class="panel right-panel">
					<div class="content">
						<h3>Already have an account?</h3>
						<p>Please sign in to continue</p>
						<button
							type="submit"
							class="btn transparent"
							id="login-btn">
							Login
						</button>
					</div>
					<img src="images/register.svg" alt="registerImage" class="image" />
				</div>
			</div>
		</div>
		<script type="text/javascript" src="/js/app.js"></script>
	</body>
</html>