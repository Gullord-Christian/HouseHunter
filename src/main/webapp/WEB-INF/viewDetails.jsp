
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
<title>Details</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/css/viewdetails.css">
</head>
<body>
	<div class="viewdetails-navbar">
		<div>
			<h1 class="viewdetails-title">House Hunter</h1>
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
		<h2 style="text-align: center;">House details</h2>
		<div class="detailContainer">
				<div class=detailContainer-header>
					<div class="houseDetailTitles">
						<h3>Address: </h3>
						<h3>Price: </h3>
						<h3>City: </h3>
						<h3>State: </h3>
						<h3>Zip Code: </h3>
						<h3>Bedrooms: </h3>
						<h3>Bathrooms: </h3>
						<h3>Square feet: </h3>
						<h3>Listing Date:</h3>
						<h3>Listed by: </h3>
					</div>
					<div class="houseDetailValues">
						<h3> <c:out value="${house.address}" /> </h3>
						<h3>$<c:out value="${house.price}" /> </h3>
						<h3> <c:out value="${house.state }"/></h3>
						<h3> <c:out value="${house.city }"/></h3>
						<h3> <c:out value="${house.zipcode }" /> </h3>
						<h3> <c:out value="${house.bedrooms }"/></h3>
						<h3> <c:out value="${house.bathrooms }"/></h3>
						<h3> <c:out value="${house.squarefootage }"/></h3>
						<h3> 
						<fmt:formatDate value="${house.createdAt}" pattern="MMM dd, YYYY"  />
						</h3>
						<h3>
							<c:out value="${house.user.firstName }"/>
							<c:out value="${house.user.lastName }"/>
						</h3>
					</div>
				</div>
			<div class="container-footer" >
				<div class="detailContainer-footer-right">
					<a href="/houses/${house.id}/notes">
						<button class="noteBtn" >Add a note about this house</button>
					</a>
				</div>
			</div>
			<c:if test = "${userId == house.user.id }" >
			<div class="detailContainer-footer-left">
				<p style="font-style:italic; font-size: 12px;">
						** Because you listed this house <c:out value="${user.firstName }"/>, <br>
						you may edit or delete the listing.
					</p>
				<div style="display: flex;">
					<a href="/houses/edit/${id }"><button class="detail-editBtn">Edit</button></a>
					 <form action="/houses/delete/${house.id }" method="post" >
					 	<input type="hidden" name="_method" value="delete">
					 	<button type="submit" class="detail-deleteBtn">Delete</button>
					 </form>
				</div>
			</div>
			</c:if>
		</div>
</body>
</html>