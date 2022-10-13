package oAuthImp;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.opentelemetry.exporter.logging.SystemOutLogExporter;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import poJo.GetterSetters;
import poJo.api;
import poJo.webAutomation;
public class OAuthImp {
	
	public static void main(String[] args) {
		String[] courseTitles= {"Selenium Webdriver Java","Cypress","Protractor"};
		// TODO Auto-generated method stub
//		System.setProperty("webdriver.chrome.driver","C:\\Users\\Swati\\Downloads\\chromedriver_win32\\chromedriver.exe");
//		WebDriver driver= new ChromeDriver();
//		driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&state=abcde&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&redirect_uri=https://rahulshettyacademy.com/getCourse.php&response_type=code");
//		driver.findElement(By.xpath("//div[@class='rFrNMe N3Hzgf jjwyfe QBQrY zKHdkd sdJrJc Tyc9J']")).sendKeys("sawantswati005@gmail.com");
		
		//driver.findElement(By.xpath("//div[@data-email='sawantswati005@gmail.com']")).click();
//		String codeTaking = given()
//				.queryParams("scope","https://www.googleapis.com/auth/userinfo.email")
//				.queryParams("state","abcde")
//				.queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
//				.queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
//				.queryParams("response_type","code")
//		.when().get("https://accounts.google.com/o/oauth2/v2/auth")
//		.then().extract().asString();
//		JsonPath js1= new JsonPath(codeTaking);
//		String Token=js1.getString("code");
//		String url= driver.getCurrentUrl();
		String url="https://rahulshettyacademy.com/getCourse.php?state=abcde&code=4%2F0ARtbsJoEMpEkf2up8Yil2-1EvBwz2HK-zBCbMwPZKGun3ea_pFJ3r4Jdj_Y0wJkRfBoLsw&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none";
		String slitting = url.split("&code=")[1];
		String finalCode = slitting.split("&scope")[0];
		System.out.println(finalCode);
	
		//https://rahulshettyacademy.com/getCourse.php?state=abcde&code=4%2F0ARtbsJq4rTt4OA758mwMOsTr8z1gU6PeQNu_qu2-qtzafjpRoQUw2KCkqtcNc09zQ149BA&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=consent
		String accessTokenContaining = given().urlEncodingEnabled(false)
				.queryParams("code",finalCode)
				.queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
				.queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
				.queryParams("grant_type","authorization_code")
		.when().log().all()
		.post("https://www.googleapis.com/oauth2/v4/token")
		.asString();
		
		JsonPath js= new JsonPath(accessTokenContaining);
		String accessToken = js.getString("access_token");
		//defaultparser is used as content -type is not aplication/json
		 GetterSetters gc = given().queryParam("access_token",accessToken).expect().defaultParser(Parser.JSON)
		.when()
		.get("https://rahulshettyacademy.com/getCourse.php").as(GetterSetters.class);
		 //System.out.println(gc);
		 
		 System.out.println(gc.getLinkedIn());
		 System.out.println(gc.getInstructor());
		 List<api> var = gc.getCourses().getApi();
		 for(int i=0;i<var.size();i++) {
			 if(var.get(i).getCourseTitle().equalsIgnoreCase("SoapUi Webservices testing")) {
				 System.out.println(var.get(i).getPrice());
			 }
		 }
			 ArrayList<String>a=new ArrayList<String>();
			 
			List<webAutomation> cour = gc.getCourses().getWebAutomation();
			for(int j=0;j<cour.size();j++) {
				a.add(cour.get(j).getCourseTitle());
			}
				List<String> expectedList = Arrays.asList(courseTitles);
				Assert.assertTrue(a.equals(expectedList));
			}
		 
	}

	
