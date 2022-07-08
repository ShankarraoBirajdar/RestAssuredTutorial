package com.restassured.BasicAuthentication;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class App {

	public static void main(String[] args) {
		
//		given().auth().preemptive().basic("your username", "your password").get("your end point URL");
		
		/* set base URL */
		RestAssured.baseURI = "https://postman-echo.com";
		RequestSpecification httpRequest = RestAssured.given().auth().basic("postman", "password");
		Response res = httpRequest.get("/basic-auth");
		ResponseBody body = res.body();
		// Converting the response body to string
		String rbdy = body.asString();
		System.out.println("Data from the GET API- " + rbdy);
		
		  //Using the preemptive directive of basic auth to send credentials to the server
         httpRequest = RestAssured.given().auth().preemptive().basic("postman", "password");
         res = httpRequest.get("https://postman-echo.com/basic-auth");
         body = res.body();
        //Converting the response body to string
         rbdy = body.asString();
        System.out.println("Data from the GET API- "+rbdy);
        
		/* Digest Authentication */
        /*given().auth().digest("your username", "your password").get("your end point URL")*/
        
		/* Form Authentication */
//        given() .auth().form("your username", "your password").post("your end point URL")
        
		/* OAuth 1.0 */
//        given().auth().oauth(consumerKey, consumerSecret, accessToken, tokenSecret).get("your end point URL")
        
        /* OAuth 2.0 */
//		 given().auth().oauth2("Access token").get("your end point URL") 

	}

}
