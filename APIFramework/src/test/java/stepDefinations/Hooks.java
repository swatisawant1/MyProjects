package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
@Before("@DeletePlace")
public void beforeScenario() throws IOException {
	
	StepDefinations fresh=new StepDefinations();
	if(StepDefinations.place_id==null) {
	fresh.add_place_payload_with("swatz", "spanish", "india");
	fresh.user_calls_api_with_http_request("AddPlaceAPI", "POST");
	fresh.verify_place_id_created_maps_to_using("swatz", "getPlaceAPI");
	}
}
}
