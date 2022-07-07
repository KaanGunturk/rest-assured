package get_Requests;

import base_Urls.HerokuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Get06 extends HerokuAppBaseUrl {
    /*
    Given
            https://restful-booker.herokuapp.com/booking/555
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is “application/json”
        And
            Response body should be like;
          {
            "firstname": "Sally",
            "lastname": "Brown",
            "totalprice": 111,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2013-02-23",
                "checkout": "2014-10-23"
            },
            "additionalneeds": "Breakfast"
        }
     */

    @Test
    public void get01(){

        //1. Step: Set the Url
        specification.pathParams("first","booking","second",101);

        //2. Set the expected data

        //3. Send request
        Response response = given().spec(specification).when().get("/{first}/{second}");
        //response.prettyPrint();

        //4. Step Do Assertion
        //1. Yol
        response.
                then().assertThat().statusCode(200).
                contentType(ContentType.JSON).
                body("firstname",equalTo("GGS"),
                        "lastname",equalTo("FINCH"),
                        "totalprice",equalTo(111),
                        "depositpaid",equalTo(true),
                        "bookingdates.checkin",equalTo("2018-01-01"),
                        "bookingdates.checkout",equalTo(  "2019-01-01"));

        //2. Yol: JsonPath Class kullanilir

        JsonPath jsonPath = response.jsonPath();
        assertEquals("GGS", jsonPath.getString("firstname"));
        assertEquals("FINCH", jsonPath.getString("lastname"));
        assertEquals(111, jsonPath.getInt("totalprice"));
        assertEquals(true, jsonPath.getBoolean("depositpaid"));
        assertEquals("2018-01-01", jsonPath.getString("bookingdates.checkin"));
        assertEquals("2019-01-01", jsonPath.getString("bookingdates.checkout"));

        //3: Tol: Soft Assertion(TestNG)

        SoftAssert softAssert = new SoftAssert();

        //2) Obje aracılığı ile assert yapılır.

        softAssert.assertEquals(jsonPath.getString("firstname"), "GGS","firstname uyuşmadı");
        softAssert.assertEquals(jsonPath.getString("lastname"),"FINCH","lastname uyuşmadı");
        softAssert.assertEquals(jsonPath.getInt("totalprice"),111,"totalprice uyuşmadı");
        softAssert.assertEquals(jsonPath.getBoolean("depositpaid"),true,"depositpaid uyuşmadı");
        softAssert.assertEquals(jsonPath.getString("bookingdates.checkin"),"2018-01-01","checkin uyuşmadı");
        softAssert.assertEquals(jsonPath.getString("bookingdates.checkout"),"2019-01-01","checkout uyuşmadı");

        //3) assertAll() methodu kullanılır. Aksi taktirde kod her zaman pass olur.
        softAssert.assertAll();
    }
}
