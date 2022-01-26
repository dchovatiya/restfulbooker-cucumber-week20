package com.herokuapp.booker.bookerinfo;

import com.herokuapp.booker.model.AuthPojo;
import com.herokuapp.booker.testbase.TestBase;
import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import org.junit.Test;

/**
 * By Dimple Patel
 **/
public class GetAuthToken extends TestBase {
    @Title("Creating a new token to use for access to PUT and DELETE booking requests")
    @Test
    public void creatingNewToken() {
        AuthPojo authPojo = new AuthPojo();
        authPojo.setUsername("admin");
        authPojo.setPassword("password123");
        String token = SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(authPojo)
                .when()
                .post("/auth")
                .then()
                .extract()
                .path("token");
        System.out.println(token);

    }
}
