package com.freenow.sample.api.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.freenow.sample.api.common.Constant;
import com.freenow.sample.api.common.LoggerUtil;
import io.restassured.response.Response;

import java.io.IOException;

/**
 * Handle all the responses in this class
 * Converting response to an object or getting values in the response
 */
public class ResponseUtil {


    /**
     * @param response
     * @param className to map the response.
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

    /**
     * @param response
     * @return status code
     */
    public static int getResponseStatusCode(Response response) {
        return response.getStatusCode();
    }

    /**
     * Split the response StatusLine to get response message
     * eg: HTTP/1.1 404 Not Found
     * @param response
     * @return status message
     */
    public static String getResponseStatus(Response response) {
        return response.getStatusLine().split(Constant.RESPONSE_MESSAGE_PATTERN)[1].toString();
    }

    /**
     * @param response
     * @return response body
     */
    public static String getResponseBody(Response response) {
        return response.asString();
    }

}




