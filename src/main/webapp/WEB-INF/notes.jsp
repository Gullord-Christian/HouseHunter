
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
<title>New Note</title>
 <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
 <link rel="stylesheet" type="text/css" href="/css/notes.css">
</head>
<body>
	<div class="notes-navbar">
		<div>
			<h1 class="notes-title">House Hunter</h1>
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
		<div class="noteContainer">
			<div class="noteHouseDetails">
				<h4> Address: <span class="noteSpan"><c:out value="${house.address }" /> </span></h4>
				<h4> City: <span class="noteSpan"><c:out value="${house.state }"/> </span></h4>
				<h4> State: <span class="noteSpan"><c:out value="${house.city }"/></span> </h4>
				<h4> Zip Code: <span class="noteSpan"><c:out value="${house.zipcode }"/></span> </h4>
				<h4> Listing Date: <span class="noteSpan"><fmt:formatDate value="${house.listingDate}" 
					pattern="MMM dd YYYY"/></span> </h4>
				<h4> Listed by: <span class="noteSpan"><c:out value="${house.user.firstName}" /> </span></h4>
				<h4> Price: <span class="noteSpan">$<c:out value="${house.price }" /></span></h4>
				<h4> Bedrooms: <span class="noteSpan"><c:out value="${house.bedrooms }" /></span></h4>
				<h4> Bathrooms: <span class="noteSpan"><c:out value="${house.bathrooms }" /></span></h4>
				<h4> Sq. Ft: <span class="noteSpan"><c:out value="${house.squarefootage }" /></span></h4>
			<div class="noteForm">
				<form:form action="/houses/${house.id }/notes" method="post" modelAttribute="newNote">
					<div>
						<form:errors path="note" class="text-danger"/>
						<form:textarea rows="4" cols="50" class="input" path="note" 
						placeholder="Add a note for this lister..." style="border-radius: 10px;"/>
						<button class="noteBtn" type="submit">Add note</button>
					</div>
				</form:form>
			</div>
			</div>
			<div class="noteBox">
				<h2>Notes</h2>
				<c:choose>
				<c:when test="${notes.isEmpty()}">
					<p>There are currently no notes, be the first! </p>
				</c:when>
						<c:otherwise>
							<c:forEach var="note" items="${notes }">
							<h4><c:out value="${note.note }"></c:out></h4>
							<p>Added by: <c:out value="${note.creator.firstName }"> </c:out> 
							<c:out value="${note.creator.lastName }" /> <br>
							<fmt:formatDate value="${note.createdAt }" pattern="h:mm a || MMM dd, YY" /></p>
							<hr style="width: 60%; margin-left: auto; margin-right: auto;">
							</c:forEach>
						</c:otherwise>
				</c:choose>
			</div>
		</div>
</body>
</html>