$(document).ready(function() {
	$("#alertSuccess").hide(); 
	$("#alertError").hide();
});
// SAVE ============================================
$(document).on("click", "#btnSave", function(event) {
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	// Form validation-------------------
	var status = validateReportForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	// If valid------------------------
	
	var type = ($("#hidReportIDSave").val() == "") ? "POST" : "PUT";
	
	$.ajax(
	{
	url : "reportsAPI",
	type : type,
	data : $("#formReport").serialize(),
	dataType : "text",
	complete : function(response, status)
	{
	onReportSaveComplete(response.responseText, status);
	}
	});
});

	function onReportSaveComplete(response, status)
	{
		if (status == "success")
		{
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success")
		{
		$("#alertSuccess").text("Successfully saved.");
		$("#alertSuccess").show();
		$("#divItemsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error")
		{
		$("#alertError").text(resultSet.data);
		$("#alertError").show();
		}
		} else if (status == "error")
		{
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
		} else
		{
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
		}
		$("#hidReportIDSave").val("");
		$("#formReport")[0].reset();
	}
	
	//$("#formReport").submit(); });
	
	
// UPDATE==========================================
$(document).on("click", ".btnUpdate",function(event) {
			$("#hidReportIDSave").val($(this).closest("tr").find('#hidReportIDUpdate').val()); 

			$("#reportType").val($(this).closest("tr").find('td:eq(0)').text());
			$("#description").val($(this).closest("tr").find('td:eq(1)').text());
			$("#patientID").val($(this).closest("tr").find('td:eq(2)').text());
			$("#doctorID").val($(this).closest("tr").find('td:eq(3)').text());
		});

//Remove
$(document).on("click", ".btnRemove", function(event) {
	$.ajax({
		url : "reportsAPI",
		type : "DELETE",
		data : "reportID=" + $(this).data("reportid"),
		dataType : "text",
		complete : function(response, status) {
			onItemDeleteComplete(response.responseText, status);
		}
	});
});

function onItemDeleteComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divItemsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}




// CLIENTMODEL=========================================================================
function validateReportForm() {
	// Type
	if ($("#reportType").val().trim() == "") {
		return "Insert Report Type.";
	}
	// Description-------------------------------
	if ($("#description").val().trim() == "") {
		return "Insert Description.";
	}
	// Assigned Doctor-------------------------------
	if ($("#patientID").val().trim() == "") {
		return "Insert Patient.";
	}
	// Patient Name-------------------------------
	if ($("#doctorID").val().trim() == "") {
		return "Insert Assigned Doctor.";
	}
	
	return true;
}