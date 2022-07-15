package base_Urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class GoRestBaseUrl {
    protected RequestSpecification specification;


    @Before
    public void setUp(){

        specification = new RequestSpecBuilder().setBaseUri("https://gorest.co.in/public/v1").build();

    }


}