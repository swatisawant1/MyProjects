package files;

import io.restassured.path.json.JsonPath;

public class ReusableMethods {

	public static JsonPath rawToJson(String response) {
		JsonPath convertingJson=new JsonPath(response);
		return convertingJson;
	}
	
	
}

