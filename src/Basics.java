import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.ReUsableMethods;

public class Basics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// validate if Add Place API is workimg as expected 
		
				//given - all input details 
				//when - Submit the API -resource,http method
				//Then - validate the response
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		//Add Place
		
		String response = given().log().all().queryParam("key","qaclick123").header("Content-Type", "application/json")
		.body("payload.AddPlace()")
		.when().post("/maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
		
		//JsonPath js = new JsonPath(response); //for parsing String
		JsonPath js =ReUsableMethods.rawToJson(response);
		String placeId = js.getString("place _id");
		
		//Update Place
		
		String newAddress = "70 Summer walk, USA";
		
		given().log().all().queryParam("key","qaclick123").header("Content-Type", "application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeId+"\",\r\n"
				+ "\"address\":\""+newAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}\r\n"
				+ "")

		.when().put("maps/api/place/update/json")

		.then().assertThat().log().all().statusCode(200)
		.body("msg", equalTo("Address successfully updated"));
		
		//Get Place
		
		String getPlaceResponse = given().log().all().queryParam("key","qaclick123").queryParam("place_id",placeId)
		
		.when().get("maps/api/place/get/json")
		
		.then().assertThat().log().all().statusCode(200)
		
		.extract().response().asString();
		
		//JsonPath js1 = new JsonPath(getPlaceResponse); //for parsing String
		
		JsonPath js1 =ReUsableMethods.rawToJson(getPlaceResponse);
		
		String actualAddress = js1.getString("address");

		Assert.assertEquals(actualAddress, newAddress);
	}

}
