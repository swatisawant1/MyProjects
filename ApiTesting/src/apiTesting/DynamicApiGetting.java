package apiTesting;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import files.Payload;
import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamicApiGetting {
	@Test
	public void deleteBook(String isbn, String aisle) {
		String response = RestAssured.baseURI="http://216.10.245.166";
		given().header("content-type","application/json")
		.when().get("/Library/GetBook.php?AuthorName=somename")
		.then().extract().response().asString();
		
		JsonPath js=  ReusableMethods.rawToJson(response);
		String id= js.get("ID");
		System.out.println(id);
	}
}
