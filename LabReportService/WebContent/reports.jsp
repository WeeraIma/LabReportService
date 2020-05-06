<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.controller.report" %>   

    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Lab Reports Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/reports.js"></script>
</head>
<body>
<div class="container">
		<div class="row">
			<div class="col-7">
			<h2>Lab Report Management</h2>
			
			<form id="formReport" name="formReport" method="post" action="reports.jsp">
					Report Type:
					<input id="reportType" name="reportType" type="text" class="form-control form-control-sm"> 
					<br> Description: 
					<input id="description" name="description" type="text" class="form-control form-control-sm">
                    <br> Assigned Doctor: 
                    <input id="patientID" name="patientID" type="text" class="form-control form-control-sm"> 
					<br> Patient Name: 
                    <input id="doctorID" name="doctorID" type="text" class="form-control form-control-sm"> 
                    <br>
					<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary"> 
					<input type="hidden" id="hidReportIDSave" name="hidReportIDSave" value="">
				</form>
				
					<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				
				<div id="divItemsGrid">
				
				<% 
				     report itemObj = new report();
				     out.print(itemObj.readItems());
				%>
				
	</div>
				</div>
				</div>
				</div>
			
			
</body>
</html>