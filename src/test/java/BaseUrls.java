import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class BaseUrls {

    protected RequestSpecification specification;

    @Before
    public void setUp(){

        specification = new RequestSpecBuilder().setBaseUri("https://jsonplaceholder.typicode.com").build();
    }
}
