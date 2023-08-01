package com.restful.booker.crudtest;

import com.restful.booker.model.BookingPojo;
import com.restful.booker.model.Bookingdates;
import com.restful.booker.model.TokenPojo;
import com.restful.booker.utils.TestUtils;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class BookingCRUDTest {
    static String firstName = "PrimUser" + TestUtils.getRandomValue();
    static String updateFirstName = "Update" + TestUtils.getRandomValue();
    static String lastName = "Testing" + TestUtils.getRandomValue();
    static String additionalNeeds = "Breakfast";
    static int userId;

    static String token;


    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
    }


    @Test
    public void test001() {
        TokenPojo tokenPojo = new TokenPojo();
        tokenPojo.setUsername("admin");
        tokenPojo.setPassword("password123");
        Response response = given()
                .header("Content-Type", "application/json")
                .when()
                .body(tokenPojo)
                .post("/auth");
        response.then().log().all().statusCode(200);
        String responseBody = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseBody);
        token = jsonPath.getString("token");

    }

    @Test()
    public void test002() {
        System.out.println("====================" + token);

        BookingPojo bookingPojo = new BookingPojo();
        bookingPojo.setFirstname(firstName);
        bookingPojo.setLastname(lastName);
        bookingPojo.setTotalprice(111);
        bookingPojo.setDepositpaid(true);
        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("");
        bookingdates.setCheckout("");
        bookingPojo.setBookingdates(bookingdates);
        bookingPojo.setAdditionalneeds(additionalNeeds);
        Response response = given().log().all()
                .headers("Content-Type", "application/json", "Cookie", "token=" + token)
                .when()
                .body(bookingPojo)
                .post("/booking");
        response.then().log().all().statusCode(200);

        String responseBody = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseBody);
        userId = jsonPath.getInt("bookingid");
    }

    @Test
    public void test003() {

        BookingPojo bookingPojo = new BookingPojo();
        bookingPojo.setFirstname(updateFirstName);
        bookingPojo.setLastname(lastName);
        bookingPojo.setTotalprice(111);
        bookingPojo.setDepositpaid(true);
        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("");
        bookingdates.setCheckout("");
        bookingPojo.setBookingdates(bookingdates);
        bookingPojo.setAdditionalneeds(additionalNeeds);
        Response response = given().log().all()
                .headers("Content-Type", "application/json", "Cookie", "token=" + token)
                .when()
                .body(bookingPojo)
                .put("/booking/"+userId);
        response.then().log().all().statusCode(200);



    }

    @Test
    public void test004() {

        given().log().all()
                .headers("Content-Type", "application/json", "Cookie", "token=" + token)
                .pathParam("id", userId)

                .when()
                .get("/booking/{id}")
                .then()
                .statusCode(200);

    }

    @Test
    public void test005() {

        given()
                .headers("Content-Type", "application/json", "Cookie", "token=" + token)
                .pathParam("id", userId)
                .when()
                .delete("/booking/{id}")
                .then()
                .statusCode(201);

    }
}
