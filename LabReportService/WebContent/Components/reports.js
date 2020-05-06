$(document).ready(function() {
	if ($("#alertSuccess").text().trim() == "") {
		$("#alertSuccess").hide();
	}
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
	$("#formReport").submit();
});
// UPDATE==========================================
$(document).on("click",".btnUpdate",function(event) {
			$("#hidReportIDSave").val($(this).closest("tr").find('#hidReportIDUpdate').val());
			$("#reportType").val($(this).closest("tr").find('td:eq(0)').text());
			$("#description").val($(this).closest("tr").find('td:eq(1)').text());
			$("#patientID").val($(this).closest("tr").find('td:eq(2)').text());
			$("#doctorID").val($(this).closest("tr").find('td:eq(3)').text());
		});
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