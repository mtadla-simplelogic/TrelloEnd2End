import helpers.Configuration;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;

public class TestBase {
    public String baseUrl = Configuration.getBaseUrl();
    public String boards = "/boards";
    public String lists = "/lists";
    public String cards = "/cards";
    public String actions = "/actions";
    public RequestSpecification reqSpec;
    @BeforeMethod
    public void testSetup(){
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
        reqBuilder.addQueryParam("key", Configuration.getKey());
        reqBuilder.addQueryParam("token", Configuration.getToken());
        reqBuilder.setContentType(ContentType.JSON);

        reqSpec = reqBuilder.build();
    }

}
