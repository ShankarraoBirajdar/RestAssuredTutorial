package com.restassured.dataprovider;

import static io.restassured.RestAssured.given;

import org.testng.annotations.*;
import io.restassured.RestAssured;

public class App {

	@DataProvider(name = "countryDataProvider")
	public static String[][] getCountryData() {
		String countryData[][] = { { "Shankarrao", "QA" }, { "priyanka","dev"}};
		return (countryData);
	}

	@Test(dataProvider = "countryDataProvider")
	public static void TestCaseOne(String name,String job) {
		/* set base URL */
		RestAssured.baseURI = "https://reqres.in/";
		// Create User using Post
		String body = getPayload(name,job);
		given().header("Content-Type", "application/json").body(body).log().all().when().post("/api/users").then()
				.log().all().assertThat().statusCode(201);
	}

	public static String getPayload(String name,String job) {
		return "{\"name\": \""+name+"\",\"job\": \""+job+"\"}";
		
	}

}
