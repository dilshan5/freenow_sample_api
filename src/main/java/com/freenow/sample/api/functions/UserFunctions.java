package com.freenow.sample.api.functions;

import com.freenow.sample.api.common.*;
import com.freenow.sample.api.response.models.UserModel.UserDetails;
import com.freenow.sample.api.util.HeadersUtil;
import com.freenow.sample.api.util.RequestUtil;
import com.freenow.sample.api.util.ResponseUtil;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * methods related to get User details
 */
public class UserFunctions {

    /**
     * @param userName send any format of userName instead String only
     * @return User detail Response
     */
    public static Response searchUserByName(Object userName) {
        LoggerUtil.logINFO("REQUEST -> UserFunctions.search users by Name: " + userName);
        Map<String, String> queryParameters = new HashMap<>();
        queryParameters.put("username", userName.toString());
        Response response = RequestUtil.send(HeadersUtil.getJsonHeaders(), "", URIs.USER_PATH, HTTPRequestMethods.GET, queryParameters);

        return response;
    }

    /**
     * @return all user details object
     * @throws Exception
     */
    public static Object[] getAllUserDetails() throws Exception {
        LoggerUtil.logINFO("REQUEST -> UserFunctions.get all the users: ");
        Response response = RequestUtil.send(HeadersUtil.getJsonHeaders(), "", URIs.USER_PATH, HTTPRequestMethods.GET, null);

        return ResponseUtil.getObject(response.asString(), UserDetails[].class);
    }

}
