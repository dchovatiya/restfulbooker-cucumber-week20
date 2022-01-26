package com.herokuapp.booker.cucumber.steps;

import com.herokuapp.booker.bookerinfo.BookingSteps;
import com.herokuapp.booker.utils.TestUtils;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.internal.ValidatableResponseImpl;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;

import java.util.HashMap;

/**
 * By Dimple Patel
 **/
public class BookingStepsTests
{
    static ValidatableResponse response;
    static String firstname="jim"+ TestUtils.getRandomText();
    static String lastname="Brown"+TestUtils.getRandomText();
    static int totalprice=111;
    static boolean depositpaid=true;
    static String additionalneeds="Breakfast";
    static int bookingId;
    static String username = "admin";
    static String password = "password123";
    static String token;

    @Steps
    BookingSteps bookingSteps;

    @When("^User send GET request to see all booking$")
    public void userSendGETRequestToSeeAllBooking()
    {
        response=bookingSteps.getAllBookingRecords().log().all().statusCode(200);
    }

    @Then("^User must get back a valid status code$")
    public void userMustGetBackAValidStatusCode()
    {
        response.statusCode(200);
    }
    @When("^I create a new booking by providing firstName, lastName, totalPrice, depositPaid, checkIn,  checkOut and additionalNeeds$")
    public void iCreateANewBookingByProvidingFirstNameLastNameTotalPriceDepositPaidCheckInCheckOutAndAdditionalNeeds()
    {
        HashMap<String,String> bookingdates=new HashMap<>();
        bookingdates.put("checkin","2016-02-23");
        bookingdates.put("checkout","2016-10-23");
        response=bookingSteps.createBooking(firstname,lastname,totalprice,depositpaid,bookingdates,additionalneeds).log().all().statusCode(200);

        bookingId=response.extract().path("bookingid");
        System.out.println("Generated booking id is:"+bookingId);
    }
    @Then("^I verify that new booking is created with the given credentials$")
    public void iVerifyThatNewBookingIsCreatedWithTheGivenCredentials()
    {
        bookingSteps.getBookingById(bookingId).log().all().statusCode(200);
    }
    @When("^I update booking by providing firstName, lastName, totalPrice, depositPaid, checkIn,  checkOut, additionalNeeds$")
    public void iUpdateBookingByProvidingFirstNameLastNameTotalPriceDepositPaidCheckInCheckOutAdditionalNeeds()
    {
        HashMap<String, String> bookingdates=new HashMap<>();
        bookingdates.put("checkin","2015-02-23");
        bookingdates.put("checkout","2016-10-23");

        firstname=firstname+"_updated";
        response=bookingSteps.updateBooking(username,password,bookingId,firstname,lastname,totalprice,depositpaid,bookingdates,additionalneeds).log().all().statusCode(200);
    }
    @Then("^I verify that booking is updated with firstName, lastName, additionalNeeds in database$")
    public void iVerifyThatBookingIsUpdatedWithFirstNameLastNameAdditionalNeedsInDatabase()
    {
       response= bookingSteps.getBookingById(bookingId).log().all().statusCode(200);
    }
    @When("^I delete the booking data$")
    public void iDeleteTheBookingData()
    {
         bookingSteps.deleteBooking(bookingId).log().all().statusCode(201);
    }
    @Then("^I verify that same booking data should get deleted$")
    public void iVerifyThatSameBookingDataShouldGetDeleted()
    {
        bookingSteps.getBookingById(bookingId).log().all().statusCode(404);
    }


}
