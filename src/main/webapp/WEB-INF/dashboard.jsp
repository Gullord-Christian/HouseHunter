<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>House Dashboard</title>
 <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
 <link rel="stylesheet" type="text/css" href="/css/dashboard.css">
</head>
<body>
	<div class="dashboard-navbar">
		<div>
			<h1 class="dashboard-title">House Hunter</h1>
			<div style="margin-left: 30px;">
			<img src="/images/crossbow.png" id="imageCrossbow">
			<img src="/images/house.png" id="imageHouse">
			</div>
		</div>
			<div style="text-align: center;">
			<h1 class="userGreeting">Welcome, 
			<c:out value="${user.firstName}"></c:out> <c:out value="${user.lastName }"/>! </h1>	
			<h5 style="font-style: italic ">Our mission is to help you find your dream home</h5>
			<a href="/houses/new"><button class="listingBtn">Add a new listing </button></a>
			<a href="/housemarket"><button class="houseMarketBtn"> House Market</button></a>
			<a href="/logout"><button class="logoutBtn">Log Out</button></a>	
		</div>	
	</div>
	<h3 class="dashboard-subtitle">Check out our amazing  homes</h3>
	<table class="table table-striped" style="margin: 0 auto;">
		<thead>
				<tr>
					<th scope="col"> Address </th>
					<th scope="col"> City </th>
					<th scope="col"> State </th>
					<th scope="col"> Zip Code </th>
					<th scope="col"> Listed On </th>
					<th scope="col"> Listed By </th>
					<th scope="col"> Price </th>
					<th scope="col"> Bedrooms </th>
					<th scope="col"> Bathrooms </th>
					<th scope="col"> Square Feet </th>
				</tr>
		</thead>
		<tbody>
			<c:forEach var="house" items="${houses }"> 
				<tr> 
					<td> <a href="/houses/${house.id }"><button class="houseLink"><c:out value="${house.address }" /></button> </a></td>
					<td> <c:out value="${house.state }"/> </td>
					<td> <c:out value="${house.city }"/> </td>
					<td> <c:out value="${house.zipcode }"/> </td>
					<td> <fmt:formatDate value="${house.listingDate}" pattern="MMM dd YYYY"/> </td>
					<td> <c:out value="${house.user.firstName}" /> </td>
					<td> $<c:out value="${house.price }" /></td>
					<td> <c:out value="${house.bedrooms }" /></td>
					<td> <c:out value="${house.bathrooms }" /></td>
					<td> <c:out value="${house.squarefootage }" /></td>
				</tr>			
			</c:forEach>
		</tbody>
	</table>
</body>
</html>