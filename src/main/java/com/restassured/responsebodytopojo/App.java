package com.restassured.responsebodytopojo;

import static io.restassured.RestAssured.given;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

public class App {

	public static void main(String[] args) throws JsonMappingException, JsonProcessingException {

		/* set base URL */
		RestAssured.baseURI = "https://fakerestapi.azurewebsites.net";

		/* Deserialization using as() */
		Employee employee = given().expect().defaultParser(Parser.JSON).when()
				.get("/api/v1/Activities/1").as(Employee.class);

		System.out.println(employee.getId());
		System.out.println(employee.getTitle());
		System.out.println(employee.getDueDate());
		System.out.println(employee.isCompleted());
		
		/* Deserialization using ObjectMapper */
		String response = given().expect().defaultParser(Parser.JSON).when()
		.get("/api/v1/Activities/1").then().extract().response().asString();
		ObjectMapper objectMapper =new ObjectMapper();
		employee = objectMapper.readValue(response, Employee.class);
		
		System.out.println(employee.getId());
		System.out.println(employee.getTitle());
		System.out.println(employee.getDueDate());
		System.out.println(employee.isCompleted());
		
		
		/*Deserialization using as() and print Array of Object*/
		Employee[] emp = given().expect().defaultParser(Parser.JSON).when()
				.get("/api/v1/Activities").as(Employee[].class);
		
		for (int i = 0; i < emp.length; i++) {
			System.out.println(emp[i]);
		}
		
		/*Deserialization using as() and print List of Object*/
		response = given().expect().defaultParser(Parser.JSON).when()
				.get("/api/v1/Activities")
				.then().extract().response().asString();
		
		JsonPath jsonPath =new JsonPath(response);
		List<Employee> allEmployees = jsonPath.getList("",Employee.class);
		
		for (int i = 0; i < allEmployees.size(); i++) {
			System.out.println(allEmployees.get(i));
		}
		
		
		
		
	}

}



class Employee {
	int id;
	String title;
	Date dueDate;
	boolean completed;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", title=" + title + ", dueDate=" + dueDate + ", completed=" + completed + "]";
	}

}
