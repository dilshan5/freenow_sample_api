package com.freenow.sample.api.functions;

import com.freenow.sample.api.common.HTTPRequestMethods;
import com.freenow.sample.api.common.LoggerUtil;
import com.freenow.sample.api.common.RestUtil;
import com.freenow.sample.api.common.URIs;
import com.freenow.sample.api.util.HeadersUtil;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class PostFunctions {
    /**
     *
     * @param send any type of userIDs instead Long type only
     * @return User's Posts by User ID
     */

    public static Response searchPostByUserID(Object userID){
        LoggerUtil.logINFO("REQUEST -> PostFunctions.search Posts by User ID: " + userID);
        Map<String, String> queryParameters = new HashMap<>();
        queryParameters.put("userId", userID.toString());
        Response response = RestUtil.send(HeadersUtil.getJsonHeaders(),"", URIs.POST_PATH, HTTPRequestMethods.GET,queryParameters);

        return response;
    }
}
