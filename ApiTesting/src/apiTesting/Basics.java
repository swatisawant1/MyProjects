package apiTesting;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.Payload;
import files.ReusableMethods;

public class Basics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI="https://rahulshettyacademy.com";
		 String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(Payload.AddPlace()).when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope",equalTo("APP"))
		.header("Server","Apache/2.4.41 (Ubuntu)").extract().response().asString();
		
		//System.out.println(response);
		JsonPath js= ReusableMethods.rawToJson(response);
		String placeId=js.getString("place_id");
		System.out.println(placeId);
		
		String newAddress="70 winter walk, USA";
		given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeId+"\",\r\n"
				+ "\"address\":\""+newAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}")
		.when().put("maps/api/place/update/json").then().assertThat().log().all().statusCode(200).body("msg",equalTo("Address successfully updated"));
		
		String getPlaceResponse=given().log().all().queryParam("key","qaclick123")
		.queryParam("place_id",placeId)
		.when().get("maps/api/place/get/json").then().assertThat().log().all().statusCode(200).extract().response().asString();
		//body("address",equalTo("70 winter walk, USA"));
		JsonPath js1=ReusableMethods.rawToJson(getPlaceResponse);
		String Address=js1.getString("address");
		System.out.println(Address);
		Assert.assertEquals(Address,newAddress);
	
	}

}
