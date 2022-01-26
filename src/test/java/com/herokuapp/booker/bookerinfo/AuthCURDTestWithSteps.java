package com.herokuapp.booker.bookerinfo;

import com.herokuapp.booker.constants.Path;
import com.herokuapp.booker.model.AuthPojo;
import com.herokuapp.booker.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

/**
 * By Dimple Patel
 **/
//@RunWith(SerenityRunner.class)
public class AuthCURDTestWithSteps extends TestBase
{
    static String username="admin";
    static String password="password123";
    static String token;
    @Steps
    AuthenticationStep authenticationStep;
    @Title("This will generate a new token")
    @Test
    public void test001()
    {
        ValidatableResponse response=authenticationStep.generateToken(username,password);
        response.log().all().statusCode(200);
        token=response.extract().path("token");
        System.out.println(token);
        Path.TOKEN=token;
        System.out.println("Token Const: "+Path.TOKEN);
    }
}
