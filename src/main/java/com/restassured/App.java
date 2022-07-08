package com.restassured;

import java.io.FileNotFoundException;
import java.util.Base64;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class App {

	private static final Log log = LogFactory.getLog(App.class);

	

	public static void main(String[] args) throws FileNotFoundException {
	
	}

	
	/* encode String or User name and Password and store .properties or json or xlsx file or database */
	public static String encode(String str1, String str2) {
		return new String(Base64.getEncoder().encode((str1 + ":" + str2).getBytes()));
	}

	/* decode String or User name and Password and send to user  */
	public static String decode(String encode) {
		String autoraization = encode("Shankar", "shankar");

		System.out.println(autoraization);
		log.info(autoraization);
		System.out.println(decode(autoraization));
		log.info(decode(autoraization));
		
		return new String(Base64.getDecoder().decode((encode).getBytes()));
	}

}
