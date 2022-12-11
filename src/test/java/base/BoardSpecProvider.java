package base;

import helpers.Configuration;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BoardSpecProvider {
    public static RequestSpecification getNewBoardWithoutListReqSpec() {
        RequestSpecBuilder reqBuilder = new RequestSpecBuilder();

        reqBuilder.addQueryParam("key", Configuration.getKey());
        reqBuilder.addQueryParam("token", Configuration.getToken());
        reqBuilder.addQueryParam("name", "This is my board");
        reqBuilder.addQueryParam("defaultLists", false);
        reqBuilder.setContentType(ContentType.JSON);

        return reqBuilder.build();
    }

    public static ResponseSpecification getNewBoardWithoutListRespSpec() {
        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        responseSpecBuilder.expectStatusCode(200);

        return responseSpecBuilder.build();
    }
}
