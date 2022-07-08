package com.restassured.commonSpecificationAndAddLogs;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Date;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class App {

	public static void main(String[] args) throws FileNotFoundException {
     /*add common Specification using RequestSpecification Class, how to add log in a file*/
		commonSpecificationAndAddLog();
												 
	}
	
	public static void commonSpecificationAndAddLog() throws FileNotFoundException {
		/* create PrintStream Class Object and pass filename */
		PrintStream printStream = new PrintStream(new FileOutputStream(getFileName()));

		RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri("https://reqres.in/")
				.addFilter(RequestLoggingFilter.logRequestTo(printStream))
				.addFilter(ResponseLoggingFilter.logResponseTo(printStream)).setContentType(ContentType.JSON).build();

		String body = "{\"name\": \"Shankarrao\",\"job\": \"QA\"}";

		given().spec(requestSpecification).body(body).when().post("/api/users").then().assertThat().statusCode(201);
	}
	
	/* create a file name with txt extension contain logging_current Date and time*/
	public static String getFileName() {

		String temp = String.valueOf(new Date());
		String fileName = "logging_" + temp.replaceAll("\\W", "_") + ".txt";
		/* \\W it is a RegexExprAltContext , it will remove All Character except Digits and Alphabets */
		return fileName;

	}

}
