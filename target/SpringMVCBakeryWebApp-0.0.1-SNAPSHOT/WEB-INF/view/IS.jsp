<%@ page language="java" session="true"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- Below is Taglib directive -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>




<!DOCTYPE html>

<html>

<head>

<title>Bakery Information System</title>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">



<%-- Link to the CSS file for this customer view page --%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/customerViewStyle.css">


<%-- Link to the CSS file for this userInfo modal view page --%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/userInfoModalStyle.css">


<%-- Link to the CSS file for this customer view page --%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/CheckoutModalStyle.css">


<!-- Google Fonts -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Pacifico&display=swap"
	rel="stylesheet">


<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Parisienne&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<script src="https://kit.fontawesome.com/815d5311a8.js"
	crossorigin="anonymous"></script>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>


<!-- Load Google Charts -->
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>

<!-- Prepare the chart -->
<script type="text/javascript">
    
        google.charts.load("current", {packages:["corechart"]});
        google.charts.setOnLoadCallback(drawChart);

        function drawChart() {
            var data = google.visualization.arrayToDataTable([
                ['Item Name', 'Count'],
                <c:forEach var="item" items="${ordersListForAnalytics}" varStatus="status">
                    ['${item.itemName}', ${item.itemCount}]${!status.last ? ',' : ''}
                </c:forEach>
            ]);
            
            var options = {
                title: 'Items Ordered on Selected Date',
                pieHole: 0.5,
                width: 800,
                height: 500
            };

            var chart = new google.visualization.PieChart(document.getElementById('donutchart'));
            chart.draw(data, options);
        }
        
    </script>





<style type="text/css">

/* ===== BASE STYLES ===== */
body {
	font-family: "Pacifico", cursive;
	font-style: normal;
	margin: 0;
	color: #885539;
}

#heading-title:hover {
	text-shadow: 0 0 5px #fff, 0 0 10px #fff, 0 0 15px #fff;
}

#person {
	color: #4a403a;
}

#person:hover {
	color: #DBDBDB;
}

#cartBasket {
	color: #4a403a;
}

#cartBasket:hover {
	color: #DBDBDB;
}

#box {
	color: #4a403a;
}

#box:hover {
	color: #DBDBDB;
}

.dropdown-menu div {
	margin-bottom: 5px;
}

/* ===== MENU CONTAINER ===== */
.menu-container {
	background-color: #A57A5A;
	padding: 30px;
	max-width: 1200px;
	margin: 0 auto;
	border-radius: 8px;
}

.menu-title {
	text-align: center;
	font-size: 46px;
	font-weight: bold;
	margin-bottom: 30px;
	text-shadow: 1px 1px 3px rgba(0, 0, 0, 0.2);
	transition: transform 0.4s ease, box-shadow 0.4s ease;
}

.menu-title:hover {
	transform: translateY(-10px) scale(1.05);
	text-shadow: 0 0 5px #fff, 0 0 10px #fff, 0 0 15px #fff;
}

/*=======================================================================*/

/* ===== CATEGORY BUTTONS ===== */
.category-buttons {
	display: flex;
	justify-content: center;
	gap: 30px;
	margin-bottom: 30px;
}

.category-btn {
	background: #F5F5DD;
	color: #885539;
	border: none;
	padding: 12px 30px;
	font-size: 18px;
	font-weight: bold;
	cursor: pointer;
	border-radius: 4px;
	transition: all 0.3s ease;
}

.category-btn:hover {
	background: #885539;
	color: #F5F5DD;
	transform: translateY(-3px);
}

/*=======================================================================*/

/* ===== MODAL STYLES ===== */
.modal-header {
	margin-bottom: 20px;
	background-color: #C9B194;
}

.modal-title {
	font-size: 24px;
	color: #4a403a;
	margin: 0;
}

.modal-body {
	margin-bottom: 20px;
}

.modal-body p {
	font-size: 19px;
}

.modal-footer {
	display: flex;
	justify-content: flex-end;
	gap: 10px;
}

/*=======================================================================*/
.menu-items {
	display: flex;
	flex-wrap: wrap;
	justify-content: space-between;
	gap: 20px;
	padding: 20px;
	max-width: 1200px;
	margin: 0 auto;
}

.menu-grid {
	display: flex;
	flex-wrap: wrap;
	justify-content: space-between;
	gap: 20px;
}

/* Card Styles */
.card {
	flex: 1 1 calc(33.33% - 20px);
	background-color: #C9B194;
	border: 2px solid #A08963;
	border-radius: 8px;
	box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
	text-align: center;
	display: flex;
	flex-direction: column;
	justify-content: space-between;
	height: 350px;
	padding: 10px;
}

.card-content img {
	width: 100%;
	height: 200px;
	object-fit: cover;
	border-radius: 5px;
	display: block;
}

.card-content h1 {
	font-family: 'Pacifico', cursive;
	font-style: normal;
	font-size: 30px;
	margin: 10px 0;
	color: #4a403a;
}

.card-action {
	font-family: 'Pacifico', cursive;
	font-style: normal;
	margin-top: auto;
	text-align: center;
}

.card-action button {
	background-color: #A08963;
	color: #DBDBDB;
	border: none;
	padding: 10px 20px;
	font-size: 22px;
	border-radius: 5px solid #344C3D;
	cursor: pointer;
}

.card-action button:hover {
	background-color: white;
	color: #A57A5A;
}

#itemDropdown {
	width: 250px;
	height: 45px;
	font-size: 16px;
	padding: 8px 12px;
}

/*=======================================================================*/

/* CSS code of the cart +,-,trash buttons*/
.cart-item-header, .cart-item-row {
	display: flex;
	justify-content: space-between;
	align-items: center;
	padding: 10px;
}

.item-name, .item-quantity, .item-price, .item-actions {
	flex: 1;
	text-align: center;
}

.item-actions {
	display: flex;
	justify-content: center;
	gap: 8px;
}

.btn-quantity, .btn-delete {
	width: 30px;
	height: 30px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	display: flex;
	align-items: center;
	justify-content: center;
	/* Add transition for smooth animation */
	transition: all 0.2s ease-in-out;
	/* Add a subtle shadow for depth */
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.btn-quantity {
	font-weight: bold;
	font-size: 16px;
}

.btn-delete {
	font-size: 14px;
}

/* Hover effects */
.btn-increase:hover {
	background-color: #218838 !important; /* Slightly darker green */
	transform: translateY(-2px) scale(1.05);
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.15);
}

.btn-decrease:hover {
	background-color: #e0a800 !important; /* Slightly darker yellow */
	transform: translateY(-2px) scale(1.05);
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.15);
}

.btn-delete:hover {
	background-color: #c82333 !important; /* Slightly darker red */
	transform: translateY(-2px) scale(1.05);
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.15);
}

/* Active/pressed effect */
.btn-increase:active, .btn-decrease:active, .btn-delete:active {
	transform: translateY(1px) scale(0.98);
	box-shadow: 0 1px 2px rgba(0, 0, 0, 0.1);
}

/* Checkout modal css code for the items list */
.items-list {
	max-height: 300px; /* Adjust height as needed */
	overflow-y: auto; /* Adds vertical scroll */
	padding-right: 10px;
	/* Prevents content from hiding behind scrollbar */
}

/*============================================================================*/
.order-table-wrapper {
	width: 100vw;
	margin: 0;
	padding: 20px 0;
	background-color: #C9B194;
	border-radius: 0;
}

.order-table {
	width: 100%;
	table-layout: fixed;
	font-size: 18px;
}

.order-table th, .order-table td {
	padding: 14px 16px;
	text-align: center;
}

.order-table .dropdown-menu div {
	margin-bottom: 6px;
}

@media ( max-width : 768px) {
	.order-table-wrapper {
		padding: 15px;
	}
	.order-table {
		font-size: 16px;
	}
}
</style>



</head>


<!--=========================-- Body of the WebPage --========================-->

<body
	style="font-family: '' Pacifico ', cursive; font-style: normal; background: linear-gradient(rgba(255, 255, 255, 0.5)), url('https://hicaps.com.ph/wp-content/uploads/2022/12/bakery-products.jpg') no-repeat center center fixed; background-size: cover; margin: 0; height: 100vh;">



	<!--  N A V I G A T I O N   B A R  -->
	<div class="topnav" id="myTopnav">


		<div class="navtop" id="mynavTop" style="background-color: #C9B194;">


              <form method="post" action="retrieveSalesData" id="analyticsForm">
              
              
                 <input type="date" id="orderDate" name="orderDate" required pattern="\d{4}-\d{2}-\d{2}" title="Please use YYYY-MM-DD format">
              
                 <input type="submit" value="Show Data">
              
              </form>
              

		</div>


	</div>





<script>
  document.getElementById('analyticsForm').addEventListener('submit', function (e) {
    const input = document.getElementById('orderDate');
    const value = input.value.trim(); // Avoid empty spaces

    // Basic blank-check
    if (!value || value === "--") {
      alert("Please select a valid date before submitting.");
      e.preventDefault(); // Stop form from submitting
      return;
    }

    // If format is dd-mm-yyyy, convert it
    const parts = value.split('-');
    if (parts.length === 3 && parts[2].length === 4) {
      const [dd, mm, yyyy] = parts;
      if (yyyy.length === 4 && mm.length === 2 && dd.length === 2) {
        input.value = `${yyyy}-${mm}-${dd}`;
      }
    }
  });
</script>








	<!--=========================--  M A I N   C O N T E N T  --====================-->

	<div id="heading">

		<h1 id="heading-title"
			style="font-family: 'Pacifico', cursive; font-style: normal; margin-top: 40px; color: #4a403a;">
			Suly Bakery</h1>

	</div>


	<!--  I T E M   C A R D S  -->

	<div id="main" style="margin-top: 40px;">

		<!-- Main Content - Menu Section -->
		<main class="order-table-wrapper">



			<div style="overflow-x: auto; margin: 20px auto; max-width: 90%;">
				
				<table style="width: 100%; border-collapse: collapse; font-family: Arial, sans-serif; background-color: #fdf6ee;">
					
					<thead style="background-color: #c5aa89; color: #442e1c;">
						
						<tr>
							
							<th style="padding: 12px; border: 1px solid #b89b7d;"> OrderID </th>
							
							<th style="padding: 12px; border: 1px solid #b89b7d;"> ItemName </th>
							
							<th style="padding: 12px; border: 1px solid #b89b7d;"> Quantity </th>
							
							<th style="padding: 12px; border: 1px solid #b89b7d;"> Unit Price </th>
							
							<th style="padding: 12px; border: 1px solid #b89b7d;"> Total Price </th>
							
							<th style="padding: 12px; border: 1px solid #b89b7d;"> Location </th>
							
							<th style="padding: 12px; border: 1px solid #b89b7d;"> Order Date </th>
						
						</tr>
					
					</thead>
					
					<tbody>
						
						<c:choose>

							<c:when test="${not empty sessionScope.ordersListForAnalytics}">

								<c:forEach var="order" items="${sessionScope.ordersListForAnalytics}">
									
									<tr style="border: 1px solid #ddd;">
										
										<td style="padding: 10px; border: 1px solid #ddd;"> ${order.orderId} </td>
										
										<td style="padding: 10px; border: 1px solid #ddd;"> ${order.itemName} </td>
										
										<td style="padding: 10px; border: 1px solid #ddd;"> ${order.selectedQuantity} </td>
										
										<td style="padding: 10px; border: 1px solid #ddd;">
										
										     <c:set var="unitPriceRaw" value="${order.itemPriceSum / order.selectedQuantity}" />
                                             
                                             <c:set var="unitPriceRounded" value="${fn:substringBefore(unitPriceRaw + 0.005, '.')}.${fn:substring(fn:substringAfter(unitPriceRaw + 0.005, '.'), 0, 2)}" />
                                             
                                             ${unitPriceRounded}
										     
										</td>
										
										<td style="padding: 10px; border: 1px solid #ddd;"> ${order.itemPriceSum} </td>
										
										<td style="padding: 10px; border: 1px solid #ddd;"> ${order.location} </td>
										
										<td style="padding: 10px; border: 1px solid #ddd;"> ${order.orderDate} </td>
									
									</tr>
								
								</c:forEach>

							</c:when>
							
							<c:otherwise>
								
								<tr>
									
									<td colspan="7" style="padding: 12px; text-align: center; font-style: italic; color: #6b4b3e; background-color: #e2cfc3;"> No data available for the selected date.</td>
							
								</tr>
								
							</c:otherwise>
						
						</c:choose>
				
					</tbody>
			
				</table>
			
			</div>



		</main>



	</div>
	<!-- Closing brace of the main -->







</body>

</html>