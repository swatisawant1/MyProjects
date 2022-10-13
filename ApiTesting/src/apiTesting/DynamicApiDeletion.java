package apiTesting;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.Payload;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

public class DynamicApiDeletion {
@Test(dataProvider="BooksData")
public void deleteBook(String isbn, String aisle) {
	RestAssured.baseURI="http://216.10.245.166";
	given().header("content-type","application/json").body(Payload.AddBook(isbn,aisle))
	.when().delete("Library/Addbook.php")
	.then().extract().response().asString();
	
}
@DataProvider(name="BooksData")
public Object[][] getData() {
	return new Object[][] {{"abcde","132"},{"dfdds","3654"},{"efsd","rt54"}};
}
}
