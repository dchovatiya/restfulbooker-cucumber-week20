package com.herokuapp.booker.bookerinfo;

import com.herokuapp.booker.constants.EndPoints;
import com.herokuapp.booker.constants.Path;
import com.herokuapp.booker.model.AuthPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.vavr.API;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

/**
 * By Dimple Patel
 **/
    public class AuthenticationStep
    {
        public static String token;

        @Step("Generating authentication token for  username:{0}, password:{1} ")
    public static ValidatableResponse generateToken(String username, String password)
    {
        AuthPojo authPojo=AuthPojo.getAuthPojo(username,password);
        return  SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(authPojo)
                .when()
                .post(Path.AUTH)
                .then();
        }
        public static String getToken(String username, String password) {
            AuthPojo authPojo = AuthPojo.getAuthPojo(username, password);

            token= SerenityRest.given().log().all()
                    .contentType(ContentType.JSON)
                    .body(authPojo)
                    .when()
                    .basePath(Path.AUTH)
                    .post()
                    .then()
                    .extract()
                    .path("token");
            System.out.println(token);
            return token;
        }
    }


