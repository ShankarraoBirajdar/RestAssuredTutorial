package com.restassured.staticjsonfile;

import static io.restassured.RestAssured.given;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import io.restassured.RestAssured;

public class App {

	public static void main(String[] args) throws IOException {
		
		/* set base URL*/
		RestAssured.baseURI = "https://dummy.restapiexample.com/";
		
		// Create User using Post
		String body = new String(Files.readAllBytes(Paths.get("src/main/java/com/restassured/staticjsonfile/employeedata.json")));
		
		given().header("Content-Type", "application/json").body(body).log().all()
		.when().post("api/v1/create")
		.then().log().all()
				.assertThat().statusCode(200);
	}
	
	

}
