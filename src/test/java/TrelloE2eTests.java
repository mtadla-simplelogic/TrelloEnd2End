import io.restassured.response.Response;
import models.Emoji;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class TrelloE2eTests extends TestBase {
    private static String boardId;
    private static String todoId;
    private static String doneId;
    private static String cardId;
    private static String actionId;

    @Test(priority = 1)
    public void shouldCreateBoardWithoutDefaultLists() {
        Response response =
                given()
                        .spec(reqSpec)
                        .queryParam("name", "This is my board")
                        .queryParam("defaultLists", false).
                when()
                        .post(baseUrl + boards).
                then()
                        .statusCode(200)
                        .extract().response();

        boardId = response.jsonPath().get("id");
    }

    @Test(priority = 2)
    public void shouldCreateDoneList() {
        Response response =
                given()
                        .spec(reqSpec)
                        .queryParam("name", "DONE")
                        .queryParam("idBoard", boardId).
                when()
                        .post(baseUrl + lists).
                then()
                        .statusCode(200)
                        .extract().response();

        doneId = response.jsonPath().get("id");
    }

    @Test(priority = 3)
    public void shouldCreateTodoList() {
        Response response =
                given()
                        .spec(reqSpec)
                        .queryParam("name", "TODO")
                        .queryParam("idBoard", boardId).
                when()
                        .post(baseUrl + lists).
                then()
                        .statusCode(200)
                        .extract().response();

        todoId = response.jsonPath().get("id");
    }


    @Test(priority = 4)
    public void shouldCreateCardOnTodo() {
        Response response =
                given()
                        .spec(reqSpec)
                        .queryParam("idList", todoId)
                        .queryParam("name", "RestIntro").
                when()
                        .post(baseUrl + cards).
                then()
                        .statusCode(200)
                        .extract().response();

        cardId = response.jsonPath().get("id");
    }


    @Test(priority = 5)
    public void shouldAddCommentToCard() {
        Response response =
                given()
                        .spec(reqSpec)
                        .queryParam("text", "done!!")
                        .pathParam("cardId", cardId).
                when()
                        .post(baseUrl + cards + "/{cardId}/actions/comments").
                then()
                        .statusCode(200)
                        .extract().response();

        actionId = response.jsonPath().get("id");
    }


    @Test(priority = 6)
    public void shouldLikeComment() {
        String emojiV0 = "{\n" +
                "    \"unified\": \"1F44D\",\n" +
                "    \"shortName\": \"+1\"\n" +
                "}";

        HashMap<String, Object> emojiV1 = new HashMap<>();
        emojiV1.put("unified", "1F44D");
        emojiV1.put("shortName", "+1");

        Emoji emojiV2 = new Emoji("1F44D", "+1");

        given()
                .spec(reqSpec)
                .pathParam("actionId", actionId)
//                .body(emojiV0)
//                .body(emojiV1)
                .body(emojiV2).
        when()
                .post(baseUrl + actions + "/{actionId}/reactions").
        then()
                .statusCode(200);
    }


    @Test(priority = 7)
    public void shouldMoveCardToDoneList() {
        given()
                .spec(reqSpec)
                .pathParam("cardId", cardId)
                .queryParam("idList", doneId).
        when()
                .put(baseUrl + cards + "/{cardId}").
        then()
                .statusCode(200);
    }

    @Test(priority = 8)
    public void shouldDeletedBoard() {
        given()
                .spec(reqSpec)
                .pathParam("id", boardId).
        when()
                .delete(baseUrl + boards + "/{id}").
        then()
                .statusCode(200);
    }

    @Test(priority = 9)
    public void shouldGetDeletedBoard() {
        given()
                .spec(reqSpec)
                .pathParam("id", boardId).
        when()
                .get(baseUrl + boards + "/{id}").
        then()
                .statusCode(404);
    }
}
