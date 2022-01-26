package com.herokuapp.booker.model;

import com.herokuapp.booker.constants.Path;
import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;

/**
 * By Dimple Patel
 **/
public class AuthPojo
{
    private String username;
    private String password;
    //static String token;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
    public static AuthPojo getAuthPojo(String username, String password)
    {
        AuthPojo authPojo=new AuthPojo();
        authPojo.setUsername(username);
        authPojo.setPassword(password);
        return authPojo;
    }
}
