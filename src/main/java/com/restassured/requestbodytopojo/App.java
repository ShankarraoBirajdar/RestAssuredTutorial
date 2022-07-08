package com.restassured.requestbodytopojo;

import static io.restassured.RestAssured.given;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;

public class App {

	public static void main(String[] args) throws JsonProcessingException {

		/* set base URL */
		RestAssured.baseURI = "https://dummy.restapiexample.com/";

		Employee employee = new Employee();
		employee.setStatus("success");

		Data data = new Data();
		data.setName("Shankarrao");
		data.setSalary("40000");
		data.setAge("29");
		data.setId(26);

		employee.setData(data);

		// ObjectMapper class to serialize Pojo object to JSON
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(employee);
		System.out.println("Json Object is :-");
		System.out.println(json);

		// Direct pass POJO object into Body
		String response = given().header("Content-Type", "application/json").body(employee).log().all().when()
				.post("api/v1/create").then().assertThat().statusCode(200).extract().response().asString();

		System.out.println(response);

	}

}

class Employee {

	String status;
	Data data;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

}

class Data {
	String name;
	String salary;
	String age;
	int id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
