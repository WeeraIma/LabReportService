package com.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;

public class report extends HttpServlet { // A common method to connect to the DB
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
//Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcare", "root", "");
			System.out.print("Database Successfully connected");
		} catch (Exception e) {
			System.out.print("Database not connected");
			e.printStackTrace();
		}
		return con;
	}

	public String insertItem(String reportType, String description, String patientID,
			String doctorID) {
		System.out.println("INSERT COming ");
		String output = "";
		try {
			Connection con = connect();
			if (con == null) { 
				return "Error while connecting to the database for inserting.";

			}
// create a prepared statement
			String query = " insert into labreport (`reportID`,`reportType`,`description`,`patientID`,`doctorID`)"
					+ " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, reportType);
			preparedStmt.setString(3, description);
			preparedStmt.setInt(4, Integer.parseInt(patientID));
			preparedStmt.setInt(5, Integer.parseInt(doctorID));

// execute the statement
			preparedStmt.execute();
			con.close();
			
			String newReport = readItems();
			output = "{\"status\":\"success\", \"data\": \"" +newReport + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the item.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readItems() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Report Type</th><th>Description</th><th>Assigned Doctor</th><th>Patient Name</th><th>Update</th><th>Remove</th></tr>";
			String query = "select * from labreport";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
// iterate through the rows in the result set
			while (rs.next()) {
				String reportID = Integer.toString(rs.getInt("reportID"));
				String reportType = rs.getString("reportType");
				String description = rs.getString("description");
				String patientName = rs.getString("patientID");
				String doctorName = rs.getString("doctorID");
// Add into the html table
				output += "<tr><td><input id='hidReportIDUpdate'name='hidReportIDUpdate'type='hidden' value='" + reportID+ "'>" + reportType + "</td>";
				output += "<td>" + description + "</td>";
				output += "<td>" + patientName + "</td>";
				output += "<td>" + doctorName + "</td>";
// buttons
				output += "<td><input name='btnUpdate'type='button' value='Update'class='btnUpdate btn btn-secondary'></td>"+ "<td><input name='btnRemove'type='button' value='Remove'class='btnRemove btn btn-danger'data-itemid='"+ reportID + "'>" + "</td></tr>";
				//output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td><td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-reportid='" + reportID + "'>" + "</td></tr>";
				//output += "<td><input name='btnUpdate' type='button' value='Update\"class=\" btnUpdate btn btn-secondary\"></td><td><form method=\"post\" action=\"reports.jsp\"><input name=\"btnRemove\" type=\"submit\"value=\"Remove\" class=\"btn btn-danger\"><input name=\"hidReportIDDelete\" type=\"hidden\"value=\"" + reportID + "\">" + "</form></td></tr>";
			}
			con.close();
// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteItem(String reportID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from labreport where reportID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(reportID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			String newReport = readItems();
			output = "{\"status\":\"success\", \"data\": \"" +newReport + "\"}";
			
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the item.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateItem(String reportID, String reportType, String description, String patientID,
			String doctorID) {
		System.out.println("Update COming ");
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE labreport SET reportType=?, description=?, patientID=?, doctorID=? WHERE reportID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, reportType);
			preparedStmt.setString(2, description);
 			preparedStmt.setInt(3, Integer.parseInt(patientID));
 			preparedStmt.setInt(4, Integer.parseInt(doctorID));
 			preparedStmt.setInt(5, Integer.parseInt(reportID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			String newReport = readItems();
			output = "{\"status\":\"success\", \"data\": \"" +newReport + "\"}";
			
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while updating the item.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

}

