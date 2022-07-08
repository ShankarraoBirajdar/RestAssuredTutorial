package com.restassured.Iteration;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class App {

	public static void main(String[] args) {


		
		/* set base URL*/
		RestAssured.baseURI = "https://reqres.in/";
		// Get All
				String response=given().header("Content-Type", "application/json").when().get("/api/users")
						.then().assertThat().statusCode(200).extract().response().asPrettyString();
				
				
				JsonPath jsonPath = new JsonPath(response);
				int size=jsonPath.getInt("data.size()");
				System.out.println("Size = "+size);
				/* print data array*/
				for (int i = 0; i < size; i++) {
					
					System.out.print("id = "+jsonPath.get("data.id["+i+"]")+", ");
					System.out.print("email = "+jsonPath.get("data.email["+i+"]")+", ");
					System.out.print("first_name = "+jsonPath.get("data.first_name["+i+"]")+", ");
					System.out.print("last_name = "+jsonPath.get("data.last_name["+i+"]")+", ");
					System.out.print("avatar = "+jsonPath.get("data.avatar["+i+"]")+", ");
					
					System.out.println();
					
				}

	}

}
