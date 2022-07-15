package post_Requests;

import base_Urls.HerokuAppBaseUrl;
import base_Urls.JsonPlaceHolderBaseUrls;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_Data.HerokuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post02 extends HerokuAppBaseUrl {

    /*
       Given
            1) https://restful-booker.herokuapp.com/booking
            2) {
                 "firstname": "John",
                 "lastname": "Doe",
                 "totalprice": 11111,
                 "depositpaid": true,
                 "bookingdates": {
                     "checkin": "2021-09-09",
                     "checkout": "2021-09-21"
                  }
               }
        When
            I send POST Request to the Url
        Then
            Status code is 200
            And response body should be like {
                                                "bookingid": 5315,
                                                "booking": {
                                                    "firstname": "John",
                                                    "lastname": "Doe",
                                                    "totalprice": 11111,
                                                    "depositpaid": true,
                                                    "bookingdates": {
                                                        "checkin": "2021-09-09",
                                                        "checkout": "2021-09-21"
                                                    }
                                                }
                                             }
     */


    @Test
    public void post01() {

        specification.pathParam("first", "booking");

        HerokuAppTestData herokuApp = new HerokuAppTestData();

        Map<String,String> bookingDatesMap = herokuApp.bookingDatesSetup("2021-09-09", "2021-09-21");
        Map<String,Object> expectedDataMap = herokuApp.expectedDataSetup("John", "Doe", 11111, true,bookingDatesMap);

        Response response = given().spec(specification).contentType(ContentType.JSON).body(expectedDataMap).when().post("/{first}");
        response.prettyPrint();

        Map<String,Object> actualDataMap = response.as(HashMap.class);

        assertEquals(expectedDataMap.get("firstname"),((Map)actualDataMap.get("booking")).get("firstname"));
        assertEquals(expectedDataMap.get("lastname"),((Map)actualDataMap.get("booking")).get("lastname"));
        assertEquals(expectedDataMap.get("totalprice"),((Map)actualDataMap.get("booking")).get("totalprice"));
        assertEquals(expectedDataMap.get("depositpaid"),((Map)actualDataMap.get("booking")).get("depositpaid"));

        assertEquals(bookingDatesMap.get("checkin"),((Map)((Map)actualDataMap.get("booking")).get("bookingdates")).get("checkin"));
        assertEquals(bookingDatesMap.get("checkout"),((Map)((Map)actualDataMap.get("booking")).get("bookingdates")).get("checkout"));


    }
}
