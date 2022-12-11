import helpers.Configuration;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

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

    }

    @Test(priority = 3)
    public void shouldCreateTodoList() {

    }


    @Test(priority = 4)
    public void shouldCreateCardOnTodo() {

    }


    @Test(priority = 5)
    public void shouldAddCommentToCar() {

    }


    @Test(priority = 6)
    public void shouldLikeComment() {

    }


    @Test(priority = 7)
    public void shouldMoveCardToDoneList() {

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

    }
}
