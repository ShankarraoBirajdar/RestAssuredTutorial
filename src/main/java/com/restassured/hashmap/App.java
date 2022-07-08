package com.restassured.hashmap;

import static io.restassured.RestAssured.given;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONObject;

import io.restassured.RestAssured;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
public class App {

	public static void main(String[] args) throws IOException {

		/* set base URL */
		RestAssured.baseURI = "https://dummy.restapiexample.com/";

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", "success");
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("name", "Shankarrao");
		data.put("salary", "12345");
		data.put("age", "28");
		data.put("id", 25);
		map.put("data", data);

		System.out.println(map);

     /*Convert/Serialization into Json using Jackson bind jar*/
		String json = new ObjectMapper().writeValueAsString(map);
        System.out.println( "JSON: %s"+ json);


		String response=given().header("Content-Type", "application/json").body(json).log().all().when().post("api/v1/create").then()
				.log().all().assertThat().statusCode(200).extract().response().asString();
		
		/*Convert/deSerialization into Map using Jackson bind jar*/
		@SuppressWarnings("unchecked")
		Map<String, String> map2 = new ObjectMapper().readValue(response, Map.class);
		System.out.println("Map Size is " + map2.size());
		System.out.println( map2);

	}

}
