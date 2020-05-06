<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.controller.report" %>   

<%
	//Initialize---------------------------------
	session.setAttribute("stausMsg", "");
	System.out.println("Hi Trying to process...");

	//Save---------------------------------
	if (request.getParameter("reportType") != null) {
		System.out.println("234CLICK BUTTONTrying to process...");
		report itemObj = new report();
		String stsMsg = "";

		//Insert--------------------------
		if (request.getParameter("hidReportIDSave") == "") {
			stsMsg = itemObj.insertItem(request.getParameter("reportType"),
					request.getParameter("description"),request.getParameter("patientID"),request.getParameter("doctorID"));
		} else//Update----------------------
		{
			stsMsg = itemObj.updateItem(request.getParameter("hidReportIDSave"),request.getParameter("reportType"),
					request.getParameter("description"), request.getParameter("patientID"),
					request.getParameter("doctorID"));
		}
		session.setAttribute("statusMsg", stsMsg);
	}

	//Delete-----------------------------
	if (request.getParameter("hidReportIDDelete") != null) {
		report itemObj = new report();
		String stsMsg = itemObj.deleteItem(request.getParameter("hidReportIDDelete"));
		session.setAttribute("statusMsg", stsMsg);
	}
%>







 
    
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
				
				<div id="alertSuccess" class="alert alert-success">
				      <% 
				      out.print(session.getAttribute("statusMsg")); 
				      %>
				</div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<% 
				     report itemObj = new report();
				     out.print(itemObj.readItems());
				%>
				
			</div>
</div>
</div>
			
			
			
</body>
</html>