import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import netscape.javascript.JSObject;
import org.json.JSONObject;

import static org.hamcrest.Matchers.equalTo;

public class IntroToPostRequest {
    public static void main(String[] args) {

        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJleHAiOjE3MTg4MDg5MDIsImlhdCI6MTcxNjIxNjkwMiwidXNlcm5hbWUiOiJhc3NlbGRhcmliYXlldmFAZ21haWwuY29tIn0.yY-NYQeuQeO2bVxXDzAbn2eS8S3vdU3IxHbwEZaFkF3Ul0PT-7Px70AJ4yCktdFolS060lAjciSIjQEtpGIVRg";

        JSONObject requestBody = new JSONObject();
        requestBody.put("product_title", "Pizza");
        requestBody.put("product_price", 32);
        requestBody.put("service_type_id", 2);
        requestBody.put("category_id", 11);
        requestBody.put("product_description", "Italian");
        requestBody.put("date_of_payment", "2024-05-20");
        requestBody.put("remind_before_day", 1);
        requestBody.put("do_remind_every_month", "REPEAT_EVERY_MONTH");

        RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .auth()
                .oauth2(token)
                .baseUri("https://backend.cashwise.us/api")
                .body(requestBody.toString())
                .when()
                .post("/myaccount/products")
                .then()
                .statusCode(201)
                .body("product_title", equalTo("Pizza"))
                .body("product_price", equalTo(32));


    }
}
// "product_title": "Supply",
//  "product_price": 100,
//  "service_type_id": 1,
//  "category_id": 11,
//  "product_description": "Med supply",
//  "date_of_payment": "2024-05-20",
//  "remind_before_day": 1,
//  "do_remind_every_month": "REPEAT_EVERY_MONTH"