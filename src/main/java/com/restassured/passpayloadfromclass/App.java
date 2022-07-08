package com.restassured.passpayloadfromclass;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;

public class App {

	public static void main(String[] args) {
		
		/* set base URL*/
		RestAssured.baseURI = "https://reqres.in/";
		// Create User using Post
		String body = Payload.nameAndJobPayload();
		given().header("Content-Type", "application/json").body(body).when().post("/api/users").then().log().all()
				.assertThat().statusCode(201);

	}

}

class Payload{
	
	public static  String nameAndJobPayload() {
		
		return "{\"name\": \"Shankarrao\",\"job\": \"QA\"}";
		
	}
}
