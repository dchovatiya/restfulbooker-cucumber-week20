package com.herokuapp.booker.cucumber.steps;

import com.herokuapp.booker.bookerinfo.AuthenticationStep;
import com.herokuapp.booker.constants.Path;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;

/**
 * By Dimple Patel
 **/
public class AuthStepsTests
{
    static ValidatableResponse response;
    static String token;
    @Steps
    AuthenticationStep authenticationStep;
    @When("^User send admin \"([^\"]*)\" and password \"([^\"]*)\" as a payload body$")
    public void userSendAdminAndPasswordAsAPayloadBody(String username, String password)
    {
        response=authenticationStep.generateToken(username,password);
        response.log().all().statusCode(200);
    }
    @Then("^User should be able to generate and use that auth token for access to the Update and Delete booking$")
    public void userShouldBeAbleToGenerateAndUseThatAuthTokenForAccessToTheUpdateAndDeleteBooking()
    {
        token=response.extract().path("token");
        System.out.println(token);
        Path.TOKEN=token;
        System.out.println("Token Const: "+Path.TOKEN);
    }
}
