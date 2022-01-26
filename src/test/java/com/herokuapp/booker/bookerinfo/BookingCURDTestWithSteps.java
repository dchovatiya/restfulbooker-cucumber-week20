package com.herokuapp.booker.bookerinfo;

import com.herokuapp.booker.testbase.TestBase;
import com.herokuapp.booker.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.hasValue;

/**
 * By Dimple Patel
 **/
@RunWith(SerenityRunner.class)
public class BookingCURDTestWithSteps extends TestBase
{

    static String firstname="jim"+TestUtils.getRandomText();
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
    @Title("This will create a new booking")
    @Test
    public void test001()
    {
        HashMap<String,String>bookingdates=new HashMap<>();
        bookingdates.put("checkin","2013-02-23");
        bookingdates.put("checkout","2014-10-23");
        ValidatableResponse response=bookingSteps.createBooking(firstname,lastname,totalprice,depositpaid,bookingdates,additionalneeds);
        response.log().all().statusCode(200);
        bookingId=response.extract().path("bookingid");
        System.out.println("Generated booking id is:"+bookingId);
        bookingSteps.getBookingById(bookingId);
    }
//    @Title("Verify if the booking was added to the application")
//    @Test
//    public void test002()
//    {
//        Map<String, Object> value = bookingSteps.getBookingInfoByName(firstname,lastname,totalprice,bookingId);
//        Assert.assertThat(value, hasValue(firstname));
//        Assert.assertThat(value, hasValue(lastname));
//        Assert.assertThat(value, hasValue(totalprice));
//        //bookingId = (int) value.get("bookingid");
//        System.out.println(bookingId);
//    }
    @Title("Update the booking information and verify the updated information")
    @Test
    public void test003()
    {
        HashMap<String, String> bookingdates=new HashMap<>();
        bookingdates.put("checkin","2015-02-23");
        bookingdates.put("checkout","2016-10-23");
        firstname=firstname+"_updated";
        bookingSteps.updateBooking(username,password,bookingId,firstname, lastname, totalprice, depositpaid, bookingdates,additionalneeds).log().all().statusCode(200);
        bookingSteps.getBookingById(bookingId).statusCode(200);
    }
    @Title("Delete the booking and verify if the booking is deleted!")
    @Test
    public void test004() {
        bookingSteps.deleteBooking(bookingId).statusCode(201);
        bookingSteps.getBookingById(bookingId) .statusCode(404);
    }

}
