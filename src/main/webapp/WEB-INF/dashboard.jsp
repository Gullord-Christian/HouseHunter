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
			<c:choose>
				<c:when test="${user.realtor_buyer == 'Realtor' }">
					<a href="/houses/new"><button class="listingBtn">Add a new listing </button></a>
					<a href="/housemarket"><button class="houseMarketBtn"> House Market</button></a>
					<a href="/logout"><button class="logoutBtn">Log Out</button></a>
				</c:when>
				<c:otherwise>
					 <a href="/dashboard/1"><button class="listingBtn">Dashboard </button></a>
					 <a href="/housemarket"><button class="houseMarketBtn"> House Market</button></a>
					 <a href="/logout"><button class="logoutBtn">Log Out</button></a>	
				</c:otherwise>
			</c:choose>	
		</div>	
	</div>
	<h3 class="dashboard-subtitle">Check out our amazing  homes</h3>
	<div id="pageNavPosition" class="pager-nav"></div>
		<table id="pager" class="dashboard-table table table-striped table-hover" style="margin: 0 auto;">
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
					</tr>
			</thead>
			<tbody>
				<c:forEach var="house" items="${houses }"> 
					<tr style="text-align: center; vertical-align: middle"> 
						<td> <a href="/houses/${house.id }"><button class="houseLink">
						<c:out value="${house.address }" /></button> </a></td>
						<td> <c:out value="${house.state }"/> </td>
						<td> <c:out value="${house.city }"/> </td>
						<td> <c:out value="${house.zipcode }"/> </td>
						<td> <fmt:formatDate value="${house.listingDate}" pattern="MM/dd/YY"/> </td>
						<c:choose>
						 	<c:when test="${house.user.id == user.id }">
								<td style="font-weight: bold; text-shadow: 1px 0px 0px gray; 
								vertical-align: middle;">
								 <c:out value="${house.user.firstName}" /> 
								 <c:out value="${house.user.lastName }"/>
									<img class="user-image" src="images/user.png" />
	 							</td>
							</c:when>
							<c:otherwise>
								<td> <c:out value="${house.user.firstName}" /> 
									 <c:out value="${house.user.lastName }" />
								</td>
							</c:otherwise>
						</c:choose>
					  <td style="vertical-align: middle;"> 
						<fmt:setLocale value = "en_US"/>
	        			<fmt:formatNumber value = "${house.price}" type="currency" pattern="$###,###"/>	
        			 </td>	
						<td> <c:out value="${house.bedrooms }" /></td>
						<td> <c:out value="${house.bathrooms }" /></td>
						<td> <c:out value="${house.squarefootage }" /></td>
					</tr>			
				</c:forEach>
			</tbody>
		</table>
	<div class="custom-shape-divider-bottom-1663269303">
    <svg data-name="Layer 1" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1200 120" preserveAspectRatio="none">
        <path d="M0,0V46.29c47.79,22.2,103.59,32.17,158,28,70.36-5.37,136.33-33.31,206.8-37.5C438.64,32.43,512.34,53.67,583,72.05c69.27,18,138.3,24.88,209.4,13.08,36.15-6,69.85-17.84,104.45-29.34C989.49,25,1113-14.29,1200,52.47V0Z" opacity=".25" class="shape-fill"></path>
        <path d="M0,0V15.81C13,36.92,27.64,56.86,47.69,72.05,99.41,111.27,165,111,224.58,91.58c31.15-10.15,60.09-26.07,89.67-39.8,40.92-19,84.73-46,130.83-49.67,36.26-2.85,70.9,9.42,98.6,31.56,31.77,25.39,62.32,62,103.63,73,40.44,10.79,81.35-6.69,119.13-24.28s75.16-39,116.92-43.05c59.73-5.85,113.28,22.88,168.9,38.84,30.2,8.66,59,6.17,87.09-7.5,22.43-10.89,48-26.93,60.65-49.24V0Z" opacity=".5" class="shape-fill"></path>
        <path d="M0,0V5.63C149.93,59,314.09,71.32,475.83,42.57c43-7.64,84.23-20.12,127.61-26.46,59-8.63,112.48,12.24,165.56,35.4C827.93,77.22,886,95.24,951.2,90c86.53-7,172.46-45.71,248.8-84.81V0Z" class="shape-fill"></path>
    </svg>
</div>
		<script type="text/javascript" src="/js/dashboard.js"></script>
</body>
</html>