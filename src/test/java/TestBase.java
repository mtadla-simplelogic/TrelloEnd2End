import helpers.Configuration;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

import java.util.List;

import static io.restassured.RestAssured.given;

public class TestBase {
    public final static String baseUrl = Configuration.getBaseUrl();
    public final static String boards = "/boards";
    public final static String lists = "/lists";
    public final static String cards = "/cards";
    public final static String actions = "/actions";
    public RequestSpecification reqSpec;

    @BeforeMethod
    public void testSetup() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
        reqBuilder.addQueryParam("key", Configuration.getKey());
        reqBuilder.addQueryParam("token", Configuration.getToken());
        reqBuilder.setContentType(ContentType.JSON);

        reqSpec = reqBuilder.build();
    }

    @AfterSuite
    public void testsCleanup() {
        Response response =
                given()
                        .spec(reqSpec)
                        .when()
                        .pathParam("id", "YOUR_ORG_ID")
                        .get(baseUrl + "/organizations/{id}/boards")
                        .then()
                        .statusCode(200)
                        .extract().response();

        JsonPath jsonPath = response.jsonPath();
        List<String> allBoardIds = jsonPath.getList("id");

        for (String id : allBoardIds) {
            given()
                    .spec(reqSpec)
                    .pathParam("id", id)
                    .when()
                    .delete(baseUrl + boards + "/{id}")
                    .then()
                    .statusCode(200);
        }
    }

}
