package get_Requests;

import base_Urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import test_Data.GoRestTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get10 extends GoRestBaseUrl {
    /*
        Given
            https://gorest.co.in/public/v1/users/2986
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And
            Response body should be like
          {
        "meta": null,
        "data": {
            "id": 55,
            "name": "Mangala Ahuja",
            "email": "mangala_ahuja@kris.com",
            "gender": "female",
            "status": "active"
          }
        }
     */

    @Test
    public void get01() {
        //1. Step: Set the Url
        specification.pathParams("first","users","second",2965);

        //2. Step: Set the expected data
        GoRestTestData dataKey = new GoRestTestData();
        Map<String , String> dataKeyMap = dataKey.dataKeyMap("Mr. Gita Menon", "gita_menon_mr@bayer.com","female","inactive");
        Map<String , Object> expectedData = dataKey.expectedDataMap(null,dataKeyMap);


        //3. Step: Send the request and Get the Response
        Response response = given().spec(specification).when().get("/{first}/{second}");

        Map<String, Object> actualDataMap = response.as(HashMap.class);


        //4. Step: Do Assertion
        assertEquals(expectedData.get("meta"),actualDataMap.get("meta"));
        assertEquals(dataKeyMap.get("name"),((Map)actualDataMap.get("data")).get("name"));
        assertEquals(dataKeyMap.get("email"),((Map)actualDataMap.get("data")).get("email"));
        assertEquals(dataKeyMap.get("gender"),((Map)actualDataMap.get("data")).get("gender"));
        assertEquals(dataKeyMap.get("status"),((Map)actualDataMap.get("data")).get("status"));

    }
}
