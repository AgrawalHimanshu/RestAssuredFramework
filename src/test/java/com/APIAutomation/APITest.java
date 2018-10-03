package com.APIAutomation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APITest {
	
	
	RequestSpecification httpRequest;
	Response response;
	public static Map<String, String> map= new HashMap<String, String>();
	
	@BeforeClass
	public void createRequest() {
		RestAssured.baseURI = "";
		httpRequest = RestAssured.given();
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void test_01() {
		httpRequest.headers("Content-Type", "application/json");
		
		ArrayList<String> arrLi = new ArrayList<String>();
		arrLi.add("1111");
		JSONObject requestParams = new JSONObject();
	
		requestParams.put("batchIds", arrLi);

		 
		requestParams.put("country", "US");
		requestParams.put("language", "en");
		requestParams.put("brand", "brand");
		
		httpRequest.body(requestParams.toJSONString());
		
		response = httpRequest.post("endpoints");
		
		
		System.out.println(response.body().asString());
		
		System.out.println(response.getStatusCode());
		
		System.out.println(response.getSessionId());
	}
	
	

}
