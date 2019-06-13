<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Integrity Eligibility System</title>
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.19/css/dataTables.jqueryui.min.css">
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script
	src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<script
	src="https://cdn.datatables.net/1.10.19/js/dataTables.jqueryui.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#ssnDetailsTable').DataTable({
			"pagingType" : "full_numbers"
		});
	});
</script>
</head>
<style>
table {
	border-collapse: collapse;
	width: 100%;
}

th, td {
	text-align: left;
	padding: 8px;
}

tr:nth-child(even) {
	background-color: #f2f2f2
}

th {
	background-color: #4CAF50;
	color: white;
}
</style>

<body>
	<a href="welcome.htm"><font size="6">Home</font></a>
	<h1 style="color: green; text-align: center">Welcome To SSN
		Details</h1>
	<br>
	<br>
	<table border="1" align="center" class="container" id="ssnDetailsTable" width="80%" height="80%">
		<thead>
			<tr>
				<th>Sr_No</th>
				<th>ssn_No</th>
				<th>First_Name</th>
				<th>Last_Name</th>
				<th>Gender</th>
				<th>DOB</th>
				<th>Phone No</th>
				<th>State</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${ssnModelsList}" var="ssnModel" varStatus="index">

				<tr>
					<td>${index.count}</td>
					<td><c:out value="${ssnModel.ssn_No}" /></td>
					<td><c:out value="${ssnModel.first_Name}" /></td>
					<td><c:out value="${ssnModel.last_Name}" /></td>
					<td>${ssnModel.gender}</td>
					<td>${ssnModel.dob}</td>
					<td>${ssnModel.phoneNo}</td>
					<td>${ssnModel.state}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>