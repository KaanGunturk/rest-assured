package patch_Request;

import base_Urls.JsonPlaceHolderBaseUrls;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_Data.JsonPlaceHolderTestData;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class Patch01 extends JsonPlaceHolderBaseUrls {
    /*
        Given
	        1) https://jsonplaceholder.typicode.com/todos/198
	        2) {
                 "title": "Wash the dishes"
               }
        When
	 		I send PATCH Request to the Url
	    Then
	   	   Status code is 200
	   	   And response body is like   {
									    "userId": 10,
									    "title": "Wash the dishes",
									    "completed": true,
									    "id": 198
									   }
     */

    @Test
    public void patch01() {

        //1. Step: Set the Url
        specification.pathParams("first","todos","second",198);

        //2. Step: Set the Request Body
        JsonPlaceHolderTestData requestBody = new JsonPlaceHolderTestData();
        Map<String,Object> requestBodyMap = requestBody.expectedDataWithMissingKeys(10,"Wash the dishes",true);

        Response response = given().spec(specification).contentType(ContentType.JSON).body(requestBodyMap).when().put("/{first}/{second}");
        response.prettyPrint();

        //4. Step: Do Assertion
        Map<String,Object> mapToAssertAllDetails = requestBody.expectedDataWithAllKeys(10,"Wash the dishes",true);

        response.then().statusCode(200).body("title",equalTo(mapToAssertAllDetails.get("title")),
                "userId",equalTo(mapToAssertAllDetails.get("userId")),
                "completed",equalTo(mapToAssertAllDetails.get("completed")));


    }
}
