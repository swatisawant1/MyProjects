package apiTesting;

import org.testng.Assert;

import files.Payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JsonPath js= new JsonPath(Payload.API());
		int count=js.getInt("courses.size()");
		System.out.println(js.getInt("courses.size()"));
		int purchaseprice=js.getInt("dashboard.purchaseAmount");
		System.out.println(js.getInt("dashboard.purchaseAmount"));
		System.out.println(js.getString("courses[0].title"));
		for(int i=0;i<count;i++) {
			System.out.println(js.getString("courses["+i+"].title"));
			System.out.println(js.getInt("courses["+i+"].price"));
		
			if(
			js.getString("courses["+i+"].title").equalsIgnoreCase("RPA")) {
				System.out.println(js.getInt("courses["+i+"].copies"));
				break;
			}
			
		}
		int sum=0;
		for(int j=0;j<count;j++) {
			int prices=js.getInt("courses["+j+"].price");
			int copie=js.getInt("courses["+j+"].copies");
			//int amount= prices*copie;	
			sum+= prices*copie;
			
			
			}
		 
		
		System.out.println(sum);
		Assert.assertEquals(sum,purchaseprice);

	}

}

