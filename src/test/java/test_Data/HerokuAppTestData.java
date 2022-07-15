package test_Data;

import java.util.HashMap;
import java.util.Map;

public class HerokuAppTestData {


    public Map<String, String> bookingDatesSetup(String checkin, String checkout) {

        Map<String, String> bokingDatesMap = new HashMap<>();
        bokingDatesMap.put("checkin", checkin);
        bokingDatesMap.put("checkout", checkout);

        return bokingDatesMap;
    }

    public Map<String, Object> expectedDataSetup(String firstname, String lastname, int totalprice, Boolean depositpaid, Map<String, String> bookingdates) {

        Map<String, Object> expectedDataMap = new HashMap<>();
        expectedDataMap.put("firstname",firstname);
        expectedDataMap.put("lastname",lastname);
        expectedDataMap.put("totalprice",totalprice);
        expectedDataMap.put("depositpaid",depositpaid);
        expectedDataMap.put("bookingdates",bookingdates);

        return expectedDataMap;
    }
}
