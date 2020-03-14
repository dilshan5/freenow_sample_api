package com.freenow.sample.api.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.freenow.sample.api.common.LoggerUtil;
import io.restassured.response.Response;

import java.io.IOException;

/**
 * Handle all the responses in this class
 * Converting response to an object or getting values in the response
 */
public class ResponseUtil {


    /**
     *
     * @param response
     * @param class Name to map the response.
     * @return
     */
    public static Object[] getObject(String response, Class c) {
        try {
            return (Object[]) new ObjectMapper().readValue(response, c);
        } catch (IOException e) {
            LoggerUtil.logERROR(e.getMessage(), e);
        }
        return null;
    }

    public static int getResponseStatus(Response response){
        return  response.getStatusCode();
    }

}



