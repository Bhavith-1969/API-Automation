import files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JsonPath js = new JsonPath(payload.CoursePrice());
		
		//Print No of courses returned by API
		int count = js.get("courses.size()");
		System.out.println(count);
		
		/*//Print Purchase Amount
		int totalAmount = js.getInt("dashboard.PurchaseAmount");
		System.out.println(totalAmount);
		
		//Print Title of the first course
		String firstCourse = js.get("courses[0].title");
		System.out.println(firstCourse);
		
		//Print All course titles and their respective Prices
		for(int i=0; i<count; i++)
		{
		    String title = js.get("courses["+i+"].title");
		    System.out.println(title);
		    int price =js.getInt("courses["+i+"].price");
		    System.out.println(price);
		}
		
		//Print no of copies sold by RPA Course
		for(int i=0; i<count; i++)
		{
		    String title = js.get("courses["+i+"].title");
		    if (title.equals("RPA"))
		    {
		    	int copies =js.getInt("courses["+i+"].copies");
		    	System.out.println(copies);
		    	break;
		    }
		    }*/
		
		//Verify if Sum of all Course prices matches with Purchase Amount
		int totalCoursePrice =0;
		//int count = js.get("courses.size()");
		for(int i=0; i<count; i++)
		{
			int price =js.getInt("courses["+i+"].price");
			int copies =js.getInt("courses["+i+"].copies");
		    int coursePrice = copies*price;
		    System.out.println(coursePrice);
		    totalCoursePrice = totalCoursePrice + coursePrice;
		}
		System.out.println(totalCoursePrice);
		
	}

}
