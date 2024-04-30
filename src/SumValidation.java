import org.testng.Assert;
import org.testng.annotations.Test;

import files.payload;
import io.restassured.path.json.JsonPath;

public class SumValidation {
	
	@Test
	public void sumOfCourses()
	{
		
		JsonPath js = new JsonPath(payload.CoursePrice());
		
		//Verify if Sum of all Course prices matches with Purchase Amount
				int totalCoursePrice =0;
				int count = js.get("courses.size()");
				for(int i=0; i<count; i++)
				{
					int price =js.getInt("courses["+i+"].price");
					int copies =js.getInt("courses["+i+"].copies");
				    int coursePrice = copies*price;
				    System.out.println(coursePrice);
				    totalCoursePrice = totalCoursePrice + coursePrice;
				}
				System.out.println(totalCoursePrice);
				int purchaseAmount = js.getInt("dashboard.PurchaseAmount");
				Assert.assertEquals(totalCoursePrice,purchaseAmount);
	}

}
