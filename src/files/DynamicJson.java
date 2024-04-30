package files;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJson {
	
	@Test(dataProvider="BooksData")
	public void addBook(String aisle,String isbn)
	{
		String response = RestAssured.baseURI="http://216.10.245.166";
		given().log().all().header("Content-Type","application/json")
		.body(payload.AddBook(aisle, isbn))
		.when().post("Library/Addbook.php")
		.then().assertThat().statusCode(200)
		.extract().response().asString();
		
		JsonPath js = ReUsableMethods.rawToJson(response);
		String id = js.get("ID");
		System.out.println(id);
		
		//Delete Book (is added in real time)
	}

	@DataProvider(name="BooksData")
	public Object[][] getData()
	{
	 	return new Object[][] {{"fghij","5678"},{"cfgbh","9765"},{"lokiu","8765"}};
	}

}
