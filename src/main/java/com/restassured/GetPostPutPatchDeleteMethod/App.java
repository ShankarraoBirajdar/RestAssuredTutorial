package com.restassured.GetPostPutPatchDeleteMethod;

import static io.restassured.RestAssured.given;
import java.io.FileNotFoundException;
import io.restassured.RestAssured;

public class App {

	public static void main(String[] args) throws FileNotFoundException {
		
		/* given()  -- set/add all input which required to api */
		/* when()  -- set/add http method name and resources  */
		/* then()  -- assert the status code and get response  */
		/* Add .log().all() at given() and then() function then you will see log in console */ 

		/* set base URL*/
		RestAssured.baseURI = "https://reqres.in/";

		getMethod_ById();/* get one user by id using Get Method */
		getMethod_All(); /* get all user using Get Method */
		postMethod_Create(); /* Create User using Post Method */
		putMethod_UpdateById(); /* update user by id using Put Method */
		patchMethod_UpdateById(); /* update user by id using Patch Method */
		deleteMethod_UpdateById(); /* delete user by id using Delete Method */
		
	

//    	String responseString = given().header("Content-Type","application/json")
//    	.when().get("/api/users/2")
//    	.then().extract().response().toString();
	}

	public static void getMethod_ById() {
		// Get By Id
		given().log().all().header("Content-Type", "application/json").when().get("/api/users/2").then().log().all()
				.assertThat().statusCode(200);
		/* given()  -- set/add all input which required to api */
		/* when()  -- set/add http method name and resources  */
		/* then()  -- assert the status code and get response  */
		/* Add .log().all() at given() and then() function then you will see log in console */ 
	}

	public static void getMethod_All() {
		// Get All
		given().log().all().header("Content-Type", "application/json").queryParam("page", "2").when().get("/api/users")
				.then().log().all().assertThat().statusCode(200);
	}

	public static void postMethod_Create() {
		// Create User using Post
		String body = "{\"name\": \"Shankarrao\",\"job\": \"QA\"}";
		given().header("Content-Type", "application/json").body(body).when().post("/api/users").then().log().all()
				.assertThat().statusCode(201);
	}

	public static void putMethod_UpdateById() {
		// Update User By Id using put
		String body1 = "{\"name\": \"Shankarrao\",\"job\": \"Developer\"}";
		given().header("Content-Type", "application/json").body(body1).when().put("/api/users/2").then().log().all()
				.assertThat().statusCode(200);
	}

	public static void patchMethod_UpdateById() {
		// Update User By Id using patch
		String body2 = "{\"name\": \"Shankarrao\",\"job\": \"Admin\"}";
		given().header("Content-Type", "application/json").body(body2).when().patch("/api/users/2").then().log().all()
				.assertThat().statusCode(200);
	}

	public static void deleteMethod_UpdateById() {
		// Delete User By Id using delete
		given().header("Content-Type", "application/json").when().delete("/api/users/2").then().log().all().assertThat()
				.statusCode(204);

	}

}
