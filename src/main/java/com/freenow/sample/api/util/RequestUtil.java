package com.freenow.sample.api.util;

import com.freenow.sample.api.common.HTTPRequestMethods;
import com.freenow.sample.api.common.RestUtil;
import com.freenow.sample.api.common.URIs;
import io.restassured.response.Response;
import java.util.Map;

/**
 * Handle all the request related methods
 */
public class RequestUtil {

    RequestUtil() {

    }

    /**
     * Set BASE_URI and BASE_PATH of the
     */
    public static void setRequestBaseUrls() {
        RestUtil.setBaseUri(URIs.BASE_URI);
        RestUtil.setBasePath(URIs.BASE_PATH);

    }

    /**
     *
     * @param headers
     * @param bodyString
     * @param uri
     * @param requestMethod
     * @param queryParameters
     * @return the response from any Rest API request
     */
    public static Response send(Map<String, String> headers, String bodyString, String uri, HTTPRequestMethods requestMethod, Map<String, String> queryParameters) {
        return RestUtil.send(headers, bodyString, uri, requestMethod, queryParameters);

    }

}
