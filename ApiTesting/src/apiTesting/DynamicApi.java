package apiTesting;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.Payload;
import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DynamicApi {
	
	@Test(dataProvider="BooksData")
	public void addbook(String isbn,String aisle) throws IOException {
		RestAssured.baseURI="http://216.10.245.166";
		String response=given().header("Content-Type","application/json").body(new String(Files.readAllBytes(Paths.get("C:\\Users\\Swati\\Documents\\staticJson.json"))))
		.when().post("Library/Addbook.php")
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
		
		JsonPath js=  ReusableMethods.rawToJson(response);
		String id= js.get("ID");
		System.out.println(id);
	}

@DataProvider(name="BooksData")
public Object[][] getData() {
	return new Object[][] {{"abcde","132"},{"dfdds","3654"},{"efsd","rt54"}};
}
}

