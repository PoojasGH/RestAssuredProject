package day1;

import org.testng.annotations.Test;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class HttpRequest {
	 int id;
	
	@Test(priority=1)
	
	void getUsers() 
	{
		//content type, set cookies, add authentication, add parameters, set headers info rtc...-pre-requests
		//given()
		
		
		//Get, Post, Put, Delete
		when()
			.get("https://reqres.in/api/users?page=2")
		
		//Validate status code, extract response,extract headers cookies & response body..
		.then()
			.statusCode(200)
			.body("page",equalTo(2))
			.log().all();
					
	}
	
	
	@Test(priority=2)
	void createUsers()
	{
		
		HashMap data = new HashMap();
		data.put("name", "Pooja");
		data.put("job", "Multitalented");
		
		  id = given()
			.contentType("application/json")
			.body(data)
			
		
		.when()
		.post("https://reqres.in/api/users")
		.jsonPath().getInt("id");
		
		//.then()
			//.statusCode(201)
			//.log().all();
	}
	
	
	@Test(priority=3,dependsOnMethods={"createUsers"})
	void updateUser() {
		

		HashMap data = new HashMap();
		data.put("name", "Naruto");
		data.put("job", "Nija");
		
		   given()
			.contentType("application/json")
			.body(data)
			
		
		.when()
			.put("https://reqres.in/api/users/" +id)
		//.jsonPath().getInt("id")
		
		.then()
			.statusCode(200)
			.log().all();
		
	}
	
	@Test(priority=4)
	void deleteUser()
	{
		given()
		
		.when()
			.delete("https://reqres.in/api/users/" +id)
		
		.then()
			.statusCode(204)
			.log().all();
		
	}
	

}
