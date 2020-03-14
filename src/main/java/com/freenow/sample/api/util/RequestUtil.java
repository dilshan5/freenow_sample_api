package com.freenow.sample.api.util;

import com.freenow.sample.api.common.HTTPRequestMethods;
import com.freenow.sample.api.common.RestUtil;
import io.restassured.response.Response;
import java.util.Map;


public class RequestUtil {

    RequestUtil() {

    }

    public static Response send(Map<String, String> headers, String bodyString, String uri, HTTPRequestMethods requestMethod, Map<String, String> queryParameters) {
        return RestUtil.send(headers, bodyString, uri, requestMethod, queryParameters);

    }

}
