package stepDefinations;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.Serialisations;
import pojo.location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinations extends Utils {
	RequestSpecification res;
	ResponseSpecification respo;
	Response response;
	static String place_id;
	JsonPath js;
	TestDataBuild data= new TestDataBuild();
	@Given("Add place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		 
			  res =given().spec(requestSpecification()).body(data.addPlacePayLoad(name, language, address));
	}

	@When("user calls API {string} with {string} http request")
	public void user_calls_api_with_http_request(String resource, String method) {
	    // Write code here that turns the phrase above into concrete actions
		APIResources resApi = APIResources.valueOf(resource);
		System.out.println(resApi);
		respo = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if(method.equalsIgnoreCase("POST")) 
		response = res.when().post(resApi.getResource())
				.then().spec(respo).extract().response();
		else if(method.equalsIgnoreCase("GET"))
			response = res.when().get(resApi.getResource())
			.then().spec(respo).extract().response();
	}

	@Then("^the API call is success with status code (\\d+)$")
	public void the_API_call_is_success_with_status_code(int arg1) {
	    // Write code here that turns the phrase above into concrete actions
	    assertEquals(response.getStatusCode(),200);
	}

	@Then("^\"([^\"]*)\" in response body is \"([^\"]*)\"$")
	public void in_response_body_is(String keyValue, String Expectedvalue)  {
	    // Write code here that turns the phrase above into concrete actions

	   assertEquals(getJsonPath(response,keyValue),Expectedvalue);
	}
	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
		 place_id=getJsonPath(response,"place_id");
		 res =given().spec(requestSpecification()).queryParam("place_id",place_id);
		 user_calls_api_with_http_request(resource,"GET");
		 String actualName=getJsonPath(response,"name");
		 assertEquals(actualName,expectedName);
	}
	
	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {
		 res = given().spec(requestSpecification()).body(data.deleteplacePayload(place_id));
	}
}
