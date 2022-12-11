package actions;

import base.BoardSpecProvider;
import helpers.Configuration;

import static io.restassured.RestAssured.given;

public class BoardActions {
    public static String createBoardWithoutList() {
        return
                given()
                        .spec(BoardSpecProvider.getNewBoardWithoutListReqSpec()).
                when()
                        .post(Configuration.getBaseUrl() + "/boards").
                then()
                        .spec(BoardSpecProvider.getNewBoardWithoutListRespSpec())
                        .extract().response().jsonPath().get("id");
    }
}
