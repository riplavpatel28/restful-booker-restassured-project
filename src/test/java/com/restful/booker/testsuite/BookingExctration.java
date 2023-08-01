package com.restful.booker.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class BookingExctration {

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
        String FirstName = response.extract().path("firstname");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value is : " + FirstName);
        System.out.println("------------------End of Test---------------------------");

    }

    @Test
    public void testLastName() {
        String lastName = response.extract().path("lastname");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value is : " + lastName);
        System.out.println("------------------End of Test---------------------------");

    }

    @Test
    public void testTotalPrice() {
        int price = response.extract().path("totalprice");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value is : " + price);
        System.out.println("------------------End of Test---------------------------");

    }

    @Test
    public void testDepositPaid() {
        boolean depositpaid = response.extract().path("depositpaid");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value is : " + depositpaid);
        System.out.println("------------------End of Test---------------------------");

    }

    @Test
    public void testBookingDates() {
        HashMap<String , Object> dates = response.extract().path("bookingdates");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value is : " + dates);
        System.out.println("------------------End of Test---------------------------");

    }

    @Test
    public void testAdditionalneeds() {
        String needs = response.extract().path("additionalneeds");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value is : " + needs);
        System.out.println("------------------End of Test---------------------------");

    }
}
