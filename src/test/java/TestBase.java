import helpers.Configuration;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;

public class TestBase {
    public final static String baseUrl = Configuration.getBaseUrl();
    public final static String boards = "/boards";
    public final static String lists = "/lists";
    public final static String cards = "/cards";
    public final static String actions = "/actions";
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
