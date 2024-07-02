package steps;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;

import static org.hamcrest.Matchers.equalTo;

public class ProductSteps {
    String id;
    RequestSpecification request;
    Response response;
    String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJleHAiOjE3MjE5MjE4NjksImlhdCI6MTcxOTMyOTg2OSwidXNlcm5hbWUiOiJhc3NlbGRhcmliYXlldmFAZ21haWwuY29tIn0.fBKphRzQDHLSVFG2dkyn7hUUKH09IPzL0LDnZKfNZrAEvdem6Q3R_Ih6oPWI8jJbhFoC8UyQEOVx44ghRCtGtQ";
    JSONObject requestBody = new JSONObject();


    @Given("base url {string}")
    public void base_url(String baseUrl) {
        request = RestAssured.given().baseUri(baseUrl).contentType(ContentType.JSON);
    }

    @Given("I have access")
    public void i_have_access() {
        request = request.auth().oauth2(token);
    }

    @Given("I have the endpoint {string}")
    public void i_have_the_endpoint(String endpoint) {
        request = request.basePath(endpoint);
    }

    @Given("I have {string} with {string} in request body")
    public void i_have_with_in_request_body(String key, String value) {
        requestBody.put(key, value);
    }

    @When("I send POST request")
    public void i_send_post_request() {
        response = request.body(requestBody.toString()).post();
    }

    @Then("verify status code is {int}")
    public void verify_status_code_is(Integer statusCode) {
        Assert.assertEquals(statusCode, response.statusCode());
    }

    @Then("verify I have {string} with {string} in response body")
    public void verify_i_have_with_in_response_body(String key, String value) {
        response.then()
                .body(key, equalTo(value));

    }
    @Then("I delete the product")
    public void i_delete_the_product() {
        id = response.jsonPath().getString("product_id");

        response = RestAssured.given()
                .baseUri("https://backend.cashwise.us/api/myaccount")
                .contentType(ContentType.JSON)
                .auth()
                .oauth2(token)
                .when()
                .delete("/products/" + id);

        Assert.assertEquals(response.statusCode(), 200);
    }

    @Then("verify the product is not present in products list")
    public void verify_the_product_is_not_present_in_products_list() {
        response = RestAssured.given()
                .baseUri("https://backend.cashwise.us/api/products")
                .auth()
                .oauth2(token)
                .when()
                .get();
    }

    @When("I send PUT request")
    public void iSendPUTRequest() {
        response = request.body(requestBody.toString()).put(id);
    }

    @And("I have {string} with {string} in reguest body")
    public void iHaveWithInReguestBody(String arg0, String arg1) {
    }

    @And("I retrieve id for {string}")
    public void iRetrieveIdFor(String id) {
        this.id = response.jsonPath().getString(id);
    }


    @Given("I have {string} with product")
    public void i_have_with_product(String products) {

        Response response = RestAssured.given()
                .baseUri("https://backend.cashwise.us/api/myaccount")
                .contentType(ContentType.JSON)
                .auth()
                .oauth2(token)
                .get("/products/1718");
        System.out.println(response.prettyPrint());

        JSONObject product = new JSONObject();
        product.put("product_title", response.jsonPath().getString("product_title"));
        product.put("product_id", Integer.parseInt(response.jsonPath().getString("product_id")));
        product.put("count_of_product", 0);
        product.put("product_price", Double.parseDouble(response.jsonPath().getString("product_price")));
        product.put("service_type_id", Integer.parseInt(response.jsonPath().getString("service_type.service_type_id")));
        product.put("category_id", Integer.parseInt(response.jsonPath().getString("category.category_id")));
        product.put("product_description", response.jsonPath().getString("product_description"));

        JSONArray arrayOfProducts = new JSONArray();
        arrayOfProducts.put(product);

        requestBody.put(products, arrayOfProducts);

    }

    @Given("I have {string} with {string} as query param")
    public void i_have_with_as_query_param(String key, String value) {
        request = request.queryParam(key, value);
    }
    @When("I send GET request")
    public void i_send_get_request() {
        response = request.get();
    }



}
