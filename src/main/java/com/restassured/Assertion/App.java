package com.restassured.Assertion;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import junit.framework.Assert;

public class App {

	private static String response = null;
	private static JsonPath jsonPath = null;

	public static void main(String[] args) {
		/* set base URL */
		RestAssured.baseURI = "https://reqres.in/";
		restAssuredFunction_assertThat();
		junitFunction_assertEquals();
		HamcrestFunction_equalTo();/* matching Request Body with Response Body */

	}

	public static void restAssuredFunction_assertThat() {
		/* use assertThat()*/
		given().header("Content-Type", "application/json")
		.when().get("/api/users/2")
		.then().assertThat().statusCode(200);
	}

	public static void junitFunction_assertEquals() {

		/* Junit Assert Function for Post Method */
		String body3 = "{\"name\": \"Shankarrao\",\"job\": \"QA\"}";
		response = given().log().all().header("Content-Type", "application/json").body(body3).when().post("/api/users")
				.then().log().all().assertThat().statusCode(201).extract().response().asString();

		/* "name":"Shankarrao" */
		jsonPath = new JsonPath(response);
		String name = jsonPath.get("data.name");
		System.out.println("name=" + name);
		Assert.assertEquals("Shankarrao", name);

		/* Junit Assert Function for Get Method */
		response = given().header("Content-Type", "application/json").when().get("/api/users/2").then().assertThat()
				.statusCode(200).extract().response().asString();

		jsonPath = new JsonPath(response);
		String email = jsonPath.get("data.email");

		System.out.println("email=" + email);
		Assert.assertEquals("janet.weaver@reqres.in", email);

	}

	public static void HamcrestFunction_equalTo() {
		/* Assertions on json Response - validate name is equal or not to shankarrao */
		/* matching Request Body with Response Body */
		/* server validation .header("server", "cloudflare"); */
		/* Verify Response is user created or not ==>Using equalTo of */
		/* org.hamcrest.Matcher */
		String body3 = "{\"name\": \"Shankarrao\",\"job\": \"QA\"}";
		given().log().all().header("Content-Type", "application/json").body(body3).when().post("/api/users").then()
				.log().all().assertThat().statusCode(201).body("name", equalTo("Shankarrao"))
				.header("server", "cloudflare");

	}

}
