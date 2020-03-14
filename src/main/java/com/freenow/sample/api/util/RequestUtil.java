package com.freenow.sample.api.util;

import com.freenow.sample.api.common.HTTPRequestMethods;
import com.freenow.sample.api.common.RestUtil;
import com.freenow.sample.api.common.URIs;
import io.restassured.response.Response;
import java.util.Map;


public class RequestUtil {

    RequestUtil() {

    }

    public static void setRequestBaseUrls() {
        RestUtil.BASE_URI = URIs.BASE_URI;
        RestUtil.BASE_PATH = URIs.BASE_PATH;

    }
    public static Response send(Map<String, String> headers, String bodyString, String uri, HTTPRequestMethods requestMethod, Map<String, String> queryParameters) {
        return RestUtil.send(headers, bodyString, uri, requestMethod, queryParameters);

    }

}
