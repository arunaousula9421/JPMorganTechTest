package com.jpmorgan.stepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;

public class JSONPlaceHolderStepDefinitions {

    private Response response;
    private RequestSpecification requestSpecification;
    private String baseURI = "https://jsonplaceholder.typicode.com";

    @Before
    public void setUp() {
        RestAssured.baseURI = baseURI;
    }


    @Given("I set required headers for the request")
    public void setHeaderForTheRequest() {
        requestSpecification = given().contentType(ContentType.TEXT);
    }

    @Given("I set parameters for the request")
    public void setParamsForRequest() {
        requestSpecification = given().queryParam("postId", "1");
    }

    @Given("I send a get request for users")
    public void sendGetRequestForUsers() {
        response = given().when().get("/users");
    }

    @And("pass required data")
    public void sendRequiredJsonData() {
        requestSpecification = requestSpecification
                .formParam("title", "foo")
                .formParam("userId", "1")
                .formParam("body", "Sample Body");
    }

    @When("I send new (.*) request")
    public void sendNewPostRequest(String post) {
        response = requestSpecification.when().post(post);
        response.prettyPrint();
    }

    @When("I send a (.*) request")
    public void sendGetRequest(String get) {
        response = requestSpecification.when().get(get);
    }

    @Then("the response code is (.*)")
    public void verifyResponseCode(int responseCode) {
        int statusCode = response.statusCode();
        Assert.assertEquals(statusCode, responseCode);
    }

    @And("response has size as (.*)")
    public void VerifyResponseSize(int size) {
        response.then().body(".", hasSize(size));
    }

    @And("response contains required data sent")
    public void verifyResponseBody() {
        int id = response.then().extract().path("id");
        Assert.assertEquals(id, 101);
    }

    @And("list the users")
    public void listUsers() {
        List<String> users = response.jsonPath().getList("name");
        for(String userName:users) {
            System.out.println(userName);
        }
    }
}
