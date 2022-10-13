package jiraApi;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.io.File;

import org.testng.Assert;
public class JiraTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI="http://localhost:8080";
		String commentMsg="Hello there general kenobi";
		SessionFilter session= new SessionFilter();
		//login
		String response=given().header("content-type","application/json").body("{\r\n"
				+ "    \"username\": \"padnap946\",\r\n"
				+ "    \"password\": \"Alpha@1235\"\r\n"
				+ "}").filter(session)
		.when().log().all().post("/rest/auth/1/session").then().log().all().extract().response().asString();
		//addcomment
		String addComment = given().pathParam("id", "10033").log().all().header("content-type","application/json").body("{\r\n"
				+ "    \"body\": \""+commentMsg+"\",\r\n"
				+ "    \"visibility\": {\r\n"
				+ "        \"type\": \"role\",\r\n"
				+ "        \"value\": \"Administrators\"\r\n"
				+ "    }\r\n"
				+ "}").filter(session)
		.when().post("/rest/api/2/issue/{id}/comment")
		.then().log().all().statusCode(201).extract().response().asString();
		JsonPath js= new JsonPath(addComment);
		String commentId=js.getString("id");
		
		//addattachment
		given().header("X-Atlassian-Token","no-check").filter(session).pathParam("id", "10033").header("content-type","multipart/form-data").multiPart("file",new File("C:\\Users\\Swati\\Documents\\ApiTesting\\src\\files\\data.txt"))
		.when().post("rest/api/2/issue/{id}/attachments").then().log().all().assertThat().statusCode(200);
		
		//getstring
		String getresponse = given().filter(session).pathParam("id", "10033")
				.queryParam("fields","comment").log().all().when().get("rest/api/2/issue/{id}").then().log().all().extract().response().asString();
		System.out.println(getresponse);
		JsonPath js1= new JsonPath(getresponse);
		int countComment = js1.get("fields.comment.comments.size()");
		for(int i=0;i<countComment;i++) {
			String compareId = js1.get("fields.comment.comments["+i+"].id").toString();
			if(compareId.equals(commentId))
			{
				String message=js1.get("fields.comment.comments["+i+"].body").toString();
				System.out.println(message);
				Assert.assertEquals(commentMsg, message);
				}
			
		}
		
	}

}
