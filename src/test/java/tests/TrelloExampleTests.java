package tests;

import base.BoardSpecProvider;
import base.TestBase;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TrelloExampleTests extends TestBase {

    @Test()
    public void shouldCreateBoardWithoutDefaultLists() {
        given()
                .spec(BoardSpecProvider.getNewBoardWithoutListReqSpec()).
        when()
                .post(baseUrl + boards).
        then()
                .spec(BoardSpecProvider.getNewBoardWithoutListRespSpec());

    }
}
