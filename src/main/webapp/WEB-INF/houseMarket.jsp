
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
<title>House Market</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/css/housemarket.css">
</head>
<body>
	<div class="housemarket-navbar">
		<div>
			<h1 class="housemarket-title">House Hunter</h1>
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
			<table class="table table-striped caption-top" style="margin: 0 auto;">
			<caption style="text-align:center; font-size: 30px;"> All Listings </caption>
			<thead> 
				<tr style="text-align: center;">
					<th scope="col"> Address </th>
					<th scope="col"> City </th>
					<th scope="col"> State </th>
					<th scope="col"> Zip Code </th>
					<th scope="col"> Listed On </th>
					<th scope="col"> Listed By </th>
					<th scope="col"> Price </th>
					<th scope="col"> Bed</th>
					<th scope="col"> Bath</th>
					<th scope="col"> Sq. ft</th>
					<th scope="col"> Actions </th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="house" items="${houses}">
				<tr>
					<td> <a href="/houses/${house.id }"><button class="houseLink">
					<c:out value="${house.address }" /></button> </a></td>
					<td> <c:out value="${house.state }"/> </td>
					<td> <c:out value="${house.city }"/> </td>
					<td> <c:out value="${house.zipcode }"/> </td>
					<td> <fmt:formatDate value="${house.listingDate}" pattern="MMM dd YYYY"/> </td>
					<td> <c:out value="${house.user.firstName}" /> </td>
					<td> $<c:out value="${house.price }" /></td>
					<td> <c:out value="${house.bedrooms }" /></td>
					<td> <c:out value="${house.bathrooms }" /></td>
					<td> <c:out value="${house.squarefootage }" /></td>
				
					<c:if test="${house.user.id == user.id }">
					<td style="display: flex; justify-content: center;"> 
						<a href="/houses/edit/${house.id }">
						<button class="housemarket-editBtn">Edit</button></a>
						 <form action="/houses/delete/${house.id }" method="post" >
						 	<input type="hidden" name="_method" value="delete">
						 	<button type="submit"class="housemarket-deleteBtn">Delete</button>
						 </form>
					</td>
					</c:if>
					<c:if test="${house.user.id!=user.id}">
						<td style="display: flex; justify-content: center;"> <a href="/housemarket/${house.id }">
						<button class="housemarket-saveBtn">Save House</button></a></td>
					</c:if>
			 </tr>
				</c:forEach>
			</tbody>
		</table>
		<p class="house-save-message">
			Notice: When you save a house, we temporarily remove it from the market. We want you to get the house you deserve. Happy Hunting! 
		</p>
		<table class="table table-dark-sm caption-top" style="margin: 0 auto;">
			<caption style="text-align:center; font-size: 30px;"> My saved houses </caption>
				<thead> 
					<tr style="text-align: center;">
						<th scope="col"> Address </th>
						<th scope="col"> City </th>
						<th scope="col"> State </th>
						<th scope="col"> Zipcode </th>
						<th scope="col"> Listed On </th>
						<th scope="col"> Listed By </th>
						<th scope="col"> Price </th>
						<th scope="col"> Bed </th>
						<th scope="col"> Bath </th>
						<th scope="col"> Sq ft </th>
						<th scope="col"> Actions </th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="house" items="${myHouses}">
					<tr>
					<td> <a href="/houses/${house.id }"><button class="houseLink">
					<c:out value="${house.address }" /></button> </a></td>
						<td> <c:out value="${house.state }"/> </td>
						<td> <c:out value="${house.city }"/> </td>
						<td> <c:out value="${house.zipcode }"/> </td>
						<td> <fmt:formatDate value="${house.listingDate}" pattern="MMM dd YYYY"/> </td>
						<td> <c:out value="${house.user.firstName}" /> </td>
						<td> $<c:out value="${house.price }" /></td>
						<td> <c:out value="${house.bedrooms }" /></td>
						<td> <c:out value="${house.bathrooms }" /></td>
						<td> <c:out value="${house.squarefootage }" /></td>
						<td style="display: flex; justify-content: center;"> <a href="/housemarket/return/${house.id }">
						<button class="housemarket-unsaveBtn">Unsave</button></a></td>
					</tr> 
					</c:forEach>
				</tbody>
		</table>
</body>
</html>