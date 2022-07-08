package com.restassured.Parameters;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;

public class App {

	public static void main(String[] args) {
		
		/* set base URL*/
		RestAssured.baseURI = "https://reqres.in/";
		
		/* it will print page 1 data*/
		String response=given().header("Content-Type", "application/json").log().all().when().get("/api/users")
				.then().assertThat().statusCode(200).extract().response().asPrettyString();

		System.out.println(response);
		
		/* print page 2 data  how to pass Query Param*/
		  response=given().header("Content-Type", "application/json").queryParam("page", "2").log().all().when().get("/api/users")
				.then().assertThat().statusCode(200).extract().response().asPrettyString();
		  
		  System.out.println(response);

		  
		  /* print by id  how to pass Path Param*/
		  response=given().header("Content-Type", "application/json").pathParam("id", "2").log().all().when().get("/api/users/{id}")
			.then().assertThat().statusCode(200).extract().response().asPrettyString();
		  
		  System.out.println(response);
		  
		  /* page 2 id 8*/
		  response=given().header("Content-Type", "application/json").queryParam("page", "2").pathParam("id", "8").log().all().when().get("/api/users/{id}")
			.then().assertThat().statusCode(200).extract().response().asPrettyString();
		  
		  System.out.println(response);
	}

}
