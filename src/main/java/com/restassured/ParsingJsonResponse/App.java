package com.restassured.ParsingJsonResponse;

import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class App {

	private static String  response = null;
	public static void main(String[] args) {
		/* set base URL*/
		RestAssured.baseURI = "https://reqres.in/";
		
		/* convert json response to as String */
		System.out.println("================asString()====================");
		 response = given().header("Content-Type", "application/json").when().get("/api/users/2").then()
		.assertThat().statusCode(200).extract().response().asString();
		System.out.println(response);
		System.out.println("====================================");

		
		/* convert json response to as String with pretty format */
		System.out.println("================asPrettyString()====================");
		response=given().header("Content-Type", "application/json").when().get("/api/users/2").then()
				.assertThat().statusCode(200).extract().response().asPrettyString();
		System.out.println(response);
		System.out.println("====================================");
		
		
		/* convert json response to as String with pretty format and print  */
		System.out.println("=================prettyPrint()===================");
		given().header("Content-Type", "application/json").when().get("/api/users/2").then()
		.assertThat().statusCode(200).extract().response().prettyPrint();
		System.out.println("====================================");

		/* print json with convert into string */
		given().header("Content-Type", "application/json").when().get("/api/users/2").then()
		.assertThat().statusCode(200).extract().response().print();
		
		/* Using JsonPath Class*/
		response = given().header("Content-Type", "application/json").when().get("/api/users/2").then()
				.assertThat().statusCode(200).extract().response().asString();
		
		JsonPath jsonPath = new JsonPath(response);
		System.out.println("id="+jsonPath.get("data.id"));
		System.out.println("email="+jsonPath.get("data.email"));
		System.out.println("firstname="+jsonPath.get("data.first_name"));
		
		
	}

}
