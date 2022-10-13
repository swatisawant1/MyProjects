Feature: Validating Place API's 
@AddPlace
Scenario Outline: Verify if place is being successfully added using AddplaceAPI 

	Given Add place Payload with "<name>" "<language>" "<address>" 
	When user calls API "AddPlaceAPI" with "Post" http request 
	Then the API call is success with status code 200 
	And "status" in response body is "OK" 
	And "scope" in response body is "APP" 
	And verify place_Id created maps to "<name>" using "getPlaceAPI" 
	
	Examples: 
		|name			|language      |address     |
		|xyz	 		|parsian 	   |104-kilo    |
		#|abc	 		|german 	   |1odgdsfsd   |
	
@DeletePlace	
Scenario: Verify if Delete place functionality is working 

	Given DeletePlace Payload 
	When user calls API "deletePlaceAPI" with "Post" http request 
	Then the API call is success with status code 200 
	And "status" in response body is "OK"