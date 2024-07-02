
import io.restassured.RestAssured;

public class IntroToAPI {
    public static void main(String[] args) {

        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJleHAiOjE3MTg4MDg5MDIsImlhdCI6MTcxNjIxNjkwMiwidXNlcm5hbWUiOiJhc3NlbGRhcmliYXlldmFAZ21haWwuY29tIn0.yY-NYQeuQeO2bVxXDzAbn2eS8S3vdU3IxHbwEZaFkF3Ul0PT-7Px70AJ4yCktdFolS060lAjciSIjQEtpGIVRg";
        RestAssured.given()
                .auth()
                .oauth2(token)
                .baseUri("https://backend.cashwise.us/api")
                .when()
                .get("/myaccount/sellers/all")
                .then()
                .statusCode(200);

        RestAssured.given()
                .auth()
                .oauth2(token)
                .baseUri("https://backend.cashwise.us/api")
                .when()
                .get("/myaccount/tags/all")
                .then()
                .statusCode(200);

        RestAssured.given()
                .auth()
                .oauth2(token)
                .baseUri("https://backend.cashwise.us/api")
                .and()
                .queryParam("page", "1")
                .queryParam("size", "4")
                .when()
                .get("/myaccount/reminder/requests")
                .then()
                .statusCode(200);

        RestAssured.given()
                .auth()
                .oauth2(token)
                .baseUri("https://backend.cashwise.us/api")
                .when()
                .get("/myaccount/reminder/notifications")
                .then()
                .statusCode(200);



    }

}
