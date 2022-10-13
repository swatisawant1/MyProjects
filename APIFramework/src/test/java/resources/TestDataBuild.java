package resources;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import pojo.Serialisations;
import pojo.location;

public class TestDataBuild {
	public Serialisations addPlacePayLoad(String name, String language, String address) {
	
	 Serialisations p =new Serialisations();
	 p.setAccuracy(50);
	 p.setAddress(address);
	 p.setName(name);
	 p.setLanguage(language);
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
	 return p;
}
public String deleteplacePayload(String placeId) {
	return "{\r\n\"place_id\":\""+placeId+"\"\r\n}";
}
}
