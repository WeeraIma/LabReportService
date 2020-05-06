package com.Service;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.controller.report;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
//For JSON 

@Path("/Reports")
public class reportService {
	report itemObj = new report();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems() {
		return itemObj.readItems();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(@FormParam("reportType") String reportType,
			@FormParam("description") String description, @FormParam("patientID") String patientID,
			@FormParam("doctorID") String doctorID) {
		String output = itemObj.insertItem(reportType, description, patientID, doctorID);
		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(String itemData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());

		// Read the value from the element <itemID>
		String reportID = doc.select("reportID").text();
		String output = itemObj.deleteItem(reportID);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(String itemData) {
		// Convert the input string to a JSON object
		JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
		// Read the values from the JSON object
		String reportID = itemObject.get("reportID").getAsString();
		String reportType = itemObject.get("reportType").getAsString();
		String description = itemObject.get("description").getAsString();
		String patientID = itemObject.get("patientID").getAsString();
		String doctorID = itemObject.get("doctorID").getAsString();
		String output = itemObj.updateItem(reportID, reportType, description, patientID, doctorID);
		return output;
	}

}

