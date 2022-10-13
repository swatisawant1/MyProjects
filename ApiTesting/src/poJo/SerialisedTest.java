package poJo;
import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
public class SerialisedTest {
	public static void main(String[] args) {
		 RestAssured.baseURI="https://rahulshettyacademy.com";
		 Serialisations p=new Serialisations();
		 p.setAccuracy(50);
		 p.setAddress("29, side layout, cohen 09");
		 p.setName("Frontline house");
		 p.setLanguage("French-IN");
		 p.setPhone_number("(+91) 983 893 3937");
		 List<String>mylist=new ArrayList<String>();
		 mylist.add("shoe park");
		 mylist.add("shop");
		 p.setTypes(mylist);
		 p.setWebsite("http://google.com");
		 location l= new location();
		 l.setLat(-38.383494);
		 l.setLng(33.427362);
		 p.setLocation(l);
		 
		 
		 RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key","qaclick123").setContentType(ContentType.JSON).build();
		ResponseSpecification respo = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		 RequestSpecification res =given().spec(req).body(p);
		 
		Response Response = res.when().post("/maps/api/place/add/json")
		.then().spec(respo).extract().response();
		String Responsess=Response.asString();
		System.out.println(Responsess);
}
}
