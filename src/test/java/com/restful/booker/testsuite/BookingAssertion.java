package com.restful.booker.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;

public class BookingAssertion {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {

        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        response = given()
                .when()
                .get("/booking/1")
                .then().statusCode(200);
    }


    @Test
    public void testFirstName() {
        response.body(".", hasKey("firstname"));
    }


    @Test
    public void testLastName() {

        response.body(".", hasKey("lastname"));
    }


    @Test
    public void testTotalPrice() {

        response.body(".", hasKey("totalprice"));
    }


    @Test
    public void testDepositpaid() {

        response.body(".", hasKey("depositpaid"));
    }


    @Test
    public void testBookingDates() {
        response.body(".", hasKey("bookingdates"));

    }

    @Test
    public void testCheckin() {
        response.body("bookingdates", hasKey("checkin"));

    }

    @Test
    public void testCheckout() {

        response.body("bookingdates", hasKey("checkout"));

    }

}
