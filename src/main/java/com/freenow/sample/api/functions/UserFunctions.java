package com.freenow.sample.api.functions;

import com.freenow.sample.api.common.*;
import com.freenow.sample.api.response.models.UserModel.UserDetails;
import com.freenow.sample.api.util.ResponseUtil;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * methods related to get User details
 */
public class UserFunctions {

    /**
     *
     * @param userName
     * @return user details object
     */

    public static Response searchUserByName(String userName){
        LoggerUtil.logINFO("REQUEST -> UserFunctions.search users by Name: " + userName);
        Map<String, String> headers = new HashMap<>();
        headers.put(FunctionBase.CONTENT_TYPE, ContentTypeOptions.APPLICATION_JSON.value());
        Map<String, String> queryParameters = new HashMap<>();
        queryParameters.put("username", userName);
        Response response = RestUtil.send(headers,"", URIs.USER_PATH, HTTPRequestMethods.GET,queryParameters);

        return response;

    }

    /**
     *
     * @param userName
     * @return user details object
     */
    public static Object[] getUsers(){
        LoggerUtil.logINFO("REQUEST -> UserFunctions.get all the users: ");
        Map<String, String> headers = new HashMap<>();
        headers.put(FunctionBase.CONTENT_TYPE, ContentTypeOptions.APPLICATION_JSON.value());
        Response response = RestUtil.send(headers,"", URIs.USER_PATH, HTTPRequestMethods.GET,null);

        return ResponseUtil.getObject(response.asString(), UserDetails[].class);

    }

}
