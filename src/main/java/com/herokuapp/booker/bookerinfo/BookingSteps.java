package com.herokuapp.booker.bookerinfo;

import com.herokuapp.booker.constants.EndPoints;
import com.herokuapp.booker.constants.Path;
import com.herokuapp.booker.model.BookingPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;
import java.util.Map;

/**
 * By Dimple Patel
 **/
public class BookingSteps
{
    @Step("Creating booking with firstname:{1}, lastname:{2}, totalprice:{3},depositpaid:{4},bookingdates:{5},additionalneeds:{6}")
    public ValidatableResponse createBooking(String firstname, String lastname, int totalprice, boolean depositpaid, HashMap<String,String>bookingdates,
                                              String additionalneeds)
    {

        BookingPojo bookingPojo=BookingPojo.getBookingPojo(firstname,lastname,totalprice,depositpaid,bookingdates,additionalneeds);
        return  SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(bookingPojo)
                .when()
                .post(Path.BOOKING)
                .then().log().all();
    }
//    @Step("Getting the booking information with first name: {0}")
//    public Map<String, Object> getBookingInfoByName(String firstname, String lastname, int totalprice, int bookingId)
//    {
//        String p1="findAll{it.firstname=='";
//        String p2="'}.get(0)";
//
//        return SerenityRest.given().log().all()
//                .queryParam("lastname", lastname)
//                .queryParam("totalprice", totalprice)
//                .queryParam("bookingid",bookingId)
//                .when()
//                .basePath(Path.BOOKING)
//                .get()
//                .then()
//                .statusCode(200)
//                .extract()
//                .path(p1+firstname+p2);
//
//    }
    @Step("Updating booking information with firstname:{0}, lastname:{1}, totalprice:{2},depositpaid:{3},checkin:{4},checkout:{5},additionalneeds:{6}")
    public ValidatableResponse updateBooking (String username, String password,int bookingId, String firstname, String lastname, int totalprice, boolean depositpaid, HashMap<String, String> bookingdates,
                                              String additionalneeds)
    {
        Path.TOKEN=AuthenticationStep.getToken(username, password);
        System.out.println(Path.TOKEN);
        BookingPojo bookingPojo = BookingPojo.getBookingPojo(firstname, lastname, totalprice, depositpaid,bookingdates,additionalneeds);
        System.out.println(Path.TOKEN);
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Accept","application/json")
                .header("Cookie", "token="+Path.TOKEN)
                .pathParam("bookingid",bookingId)
                .body(bookingPojo)
                .when()
                .put(Path.BOOKING+EndPoints.UPDATE_BOOKING_BY_ID)
                .then();
    }
    @Step("Deleting booking information with id")
    public ValidatableResponse deleteBooking (int bookingId){
        System.out.println(Path.TOKEN);
        return SerenityRest
                .given()
                .header("Content-Type", "application/json")
                .header("Accept","application/json")
                .header("Cookie", "token="+Path.TOKEN)
                .pathParam("bookingid", bookingId)
                .when()
                .delete(Path.BOOKING+EndPoints.DELETE_BOOKING_BY_ID)
                .then();
    }
    @Step("Getting booking information with bookingId")
    public ValidatableResponse getBookingById (int bookingId){
        return SerenityRest
                .given()
                .pathParam("bookingid", bookingId)
                .when()
                .get(Path.BOOKING+EndPoints.GET_BOOKING_BY_ID)
                .then();
    }
    @Step("Getting all booking information")
    public ValidatableResponse getAllBookingRecords (){
        return SerenityRest
                .given()
                .when()
                .get(Path.BOOKING)
                .then();
    }

}
