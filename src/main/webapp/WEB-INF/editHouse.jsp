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
<title>Edit House Form</title>
 <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
 <link rel="stylesheet" type="text/css" href="/css/edithouse.css">
</head>
<body>
	<div class="edithouse-navbar">
		<div>
			<h1 class="edithouse-title">House Hunter</h1>
			<div style="margin-left: 30px;">
			<img src="/images/crossbow.png" id="imageCrossbow">
			<img src="/images/house.png" id="imageHouse">
			</div>
		</div>
			<div style="text-align: center;">
			<h1 class="userGreeting">Welcome, 
			<c:out value="${user.firstName}"></c:out> <c:out value="${user.lastName }"/>! </h1>	
			<h5 style="font-style: italic ">Our mission is to help you find your dream home</h5>
			<c:choose>
				<c:when test="${user.realtor_buyer == 'Realtor' }">
					<a href="/houses/new"><button class="listingBtn">Add a new listing </button></a>
					<a href="/housemarket"><button class="houseMarketBtn"> House Market</button></a>
					<a href="/logout"><button class="logoutBtn">Log Out</button></a>
				</c:when>
				<c:otherwise>
					 <a href="/dashboard"><button class="listingBtn">Dashboard </button></a>
					 <a href="/housemarket"><button class="houseMarketBtn"> House Market</button></a>
					 <a href="/logout"><button class="logoutBtn">Log Out</button></a>	
				</c:otherwise>
			</c:choose>
		</div>	
	</div>
		<div class=" col-lg-6 offset-lg-3">
			<div class="row justify-content-center">
		    <form:form action="/houses/edit/${id}" method="post" modelAttribute="house" 
		    class="form-inline" enctype="multipart/form-data">
		    	<h1 style="text-align: center; margin-top: 10px">Edit Listing</h1>
		    <input type="hidden" name="_method" value="put" />
				<div class="row">
					<form:label path="address"> Address: </form:label>
					<form:input type="text" path="address" class="form-control-sm"  />
					<form:errors path="address" style="color:red" />
				</div>
				<div class="row">
					<form:label path="city"> City: </form:label>
					<form:input type="text" path="city" class="form-control-sm"  />
					<form:errors path="city" style="color:red" />
				</div>
				<div class="row">
					<form:label path="state"> State: </form:label>
					<form:input type="text" path="state" class="form-control-sm"  />
					<form:errors path="state" style="color:red" />
				</div>		
				<div class="row">
					<form:label path="zipcode"> Zip Code: </form:label>
					<form:input type="number" path="zipcode" class="form-control-sm"  />
					<form:errors path="zipcode" style="color:red" />
				</div>			
				<div class="row">
					<form:label path="price"> Price: </form:label>
					<form:input type="string" path="price" class="form-control-sm"   />
					<form:errors path="price" style="color:red"/>
				</div>	
				<div class="row">
					<form:label path="bedrooms"> Bedrooms: </form:label>
					<form:input type="number" path="bedrooms" class="form-control-sm"   />
					<form:errors path="bedrooms" style="color:red"/>
				</div>	
				<div class="row">
					<form:label path="bathrooms"> Bathrooms: </form:label>
					<form:input type="number" path="bathrooms" class="form-control-sm"   />
					<form:errors path="bathrooms" style="color:red"/>
				</div>	
				<div class="row">
					<form:label path="squarefootage"> Sq ft: </form:label>
					<form:input type="number" path="squarefootage" class="form-control-sm"   />
					<form:errors path="squarefootage" style="color:red"/>
				</div>								

				<form:hidden path="listingDate" value="${listingDate }" />
				<form:hidden path="user" value="${userId }"/>
				<br>
				<button type="submit" class="submitBtn"> Submit </button>
			</form:form>
			</div>
		</div>
</body>
</html>