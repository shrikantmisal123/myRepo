<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<h1 style="color: cyan; text-align: center">
	<marquee>Welcome To SSN Registration Application</marquee>
</h1>


<style>
table, th, td {
	border: 2px solid cyan;
	border-collapse: inherit;
}

.error {
	color: red
}
</style>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script
	src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  
  <script>
  
  
  $( function() {
	 $( "#datepicker" ).datepicker({ dateFormat: 'dd-M-yy',maxDate:0 });		
  } );
  </script>
<script>
	function removeErrMsg(frm) {
		document.getElementById("fileError").innerHTML = "";

	}
	function checkFormData(frm) {
		document.getElementById("clientSideValidationStatus").value="yes";
		if( document.getElementById("file").files.length == 0 ){
			document.getElementById("fileError").innerHTML = "<font color='red'>*Please Select a Photo</font>";
			return false;
		}
		 else
		{
				return true;
		}
	}



	$(document).ready(function() {
		$("#register").validate({
			rules : {
				first_Name : 'required',
				last_Name : 'required',
				dob : 'required',
				phoneNo : 'required',
			},
			messages : {
				first_Name : '*first_Name is Required',
				last_Name : '*last_Name is Required',
				dob : '*DOB is Required',
				phoneNo : '*Phone_No is Required',
			},
		});
	});
</script>

<body>
	<form:form id="register" method="post" modelAttribute="ssnModel"
		 enctype="multipart/form-data">
		<a href="welcome.htm"><font size="10">Home</font></a>
	
		<table border="2" width="500" height="500" style="margin: 0px auto;">
			<tr>
				<td colspan="2" align="center"><h1 style="color: green">Enrollment
						Form</h1></td>
			</tr>
			<tr>
				<td>First_Name</td>
				<td><form:input path="first_Name" id="first_Name" /> <form:errors path="first_Name"/></td> 
			</tr>
			<tr>
				<td>Last_Name</td>
				<td><form:input path="last_Name" id="last_Name" /> <form:errors path="last_Name"/> </td>
			</tr>
			<tr>
				<td>Gender</td>
				<td><input type="radio" name="gender" value="male">Male
				<input type="radio" 	name="gender" value="female" checked="checked">Female
				<form:errors path="gender"/> 
					</td>			
			</tr>
			<tr>
				<td>DOB</td>
				<td><form:input path="dob" id="datepicker" />
						 	<form:errors path="dob"/> 
				</td>
			</tr>
			<tr>
				<td>States Name</td>
				<td><form:select path="state">
						<form:options items="${states}" />
					</form:select> <form:errors path="state"/></td>
					
			</tr>
			<tr>
				<td>Phone_No</td>
				<td><form:input path="phoneNo" /> <form:errors path="phoneNo"/> </td>
			</tr>
			
			<tr>
				<td>Photo</td>
				<td><form:input path="photo" type="file" id="file"
						onclick="removeErrMsg(this)" /> <form:errors path="photo"/> <span id="fileError"></span>
						
					<input type="hidden" value="no" name="clientSideValidationStatus" id="clientSideValidationStatus">
				</td>
			</tr>
			<tr>
				<td colspan="2" width="300%">
						<input type="submit" value="Enroll" onclick="return checkFormData(this)">&nbsp;&nbsp;&nbsp;
						<input type="reset" value="Reset">
					</td>
			</tr>
		</table>
	</form:form>
</body>