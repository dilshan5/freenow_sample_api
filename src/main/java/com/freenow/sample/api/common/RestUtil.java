package com.freenow.sample.api.common;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.EncoderConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

/**
 * All REST API related common methods
 */
public class RestUtil {

    private static String BASE_URI;
    private static String BASE_PATH;

    public static void setBaseUri(String baseUri) {
        BASE_URI = baseUri;
    }

    public static void setBasePath(String basePath) {
        BASE_PATH = basePath;
    }

    public RestUtil() {
    }

    /**
     *
     * @param headers to be sent
     * @param bodyString to be sent
     * @param uri of the request
     * @param requestMethod HTTP method
     * @param queryParameters query params if available
     * @return Response for the request
     */
    public static Response send(Map<String, String> headers, String bodyString, String uri, HTTPRequestMethods requestMethod, Map<String, String> queryParameters) {
        RestAssured.baseURI = BASE_URI;
        RestAssured.basePath = BASE_PATH;

        System.out.println("\n\nHEADERS\n" + headers + "\n*********\n\n");
        System.out.println("\n\nREQUEST_URL\n" + RestAssured.baseURI + RestAssured.basePath + uri + "\n*********\n\n");
        RequestSpecification requestSpecification = getRequestSpec(headers, bodyString);
        logRequestBody(bodyString);

        requestSpecification = RestAssured.given().spec(requestSpecification);
        String theUri = setQueryParameters(uri, queryParameters);
        Response response = execute(requestMethod, requestSpecification, theUri);
        logUri(theUri, requestMethod, response);
        logResponse(response);
        return response;
    }

    /**
     * Build the header and request body
     * @param headers of the request.
     * @param body of the request.
     * @return Request Specification
     */
    private static RequestSpecification getRequestSpec(Map<String, String> headers, String body) {
        RequestSpecBuilder reqSpecBuilder = new RequestSpecBuilder();
        if (headers != null) {
            reqSpecBuilder.addHeaders(headers);
        }

        if (body != null && body.length() > 0) {
            reqSpecBuilder.setBody(body);
        }

        return reqSpecBuilder.build();
    }

    /**
     *
     * @param url endpoint of the request.
     * @param queryParameters query parameters of the request.
     * @return search query params
     */
    public static String setQueryParameters(String url, Map<String, String> queryParameters) {
        if (queryParameters == null || queryParameters.isEmpty())
            return url;
        String newUrl = url.concat("?");
        for (Map.Entry<String, String> entry : queryParameters.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            newUrl = newUrl.concat(key).concat("=").concat(value).concat("&");
        }
        return newUrl.substring(0, newUrl.length() - 1);
    }

    public static Response execute(HTTPRequestMethods reqMethod, RequestSpecification requestSpec, String uri) {
        RequestSpecification requestSpecification = RestAssured.given(requestSpec).config(RestAssured.config().encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)));
        Response response = null;

        if (reqMethod.equals(HTTPRequestMethods.GET)) {
            response = requestSpecification.expect().when().get(uri);
        }

        if (reqMethod.equals(HTTPRequestMethods.POST)) {
            response = requestSpecification.expect().when().post(uri);
        }

        if (reqMethod.equals(HTTPRequestMethods.PUT)) {
            response = requestSpecification.expect().when().put(uri);
        }

        if (reqMethod.equals(HTTPRequestMethods.DELETE)) {
            response = requestSpecification.expect().when().delete(uri);
        }

        if (reqMethod.equals(HTTPRequestMethods.PATCH)) {
            response = requestSpecification.expect().when().patch(uri);
        }

        return response;
    }

    /**
     * Print request body
     * @param bodyString
     */
    private static void logRequestBody(String bodyString) {
        if (bodyString != null && !bodyString.isEmpty())
            LoggerUtil.logINFO("\n\nREQUEST_BODY\n" + bodyString + "\n********\n\n");
    }


    /**
     * print the requested http method and URI
     * @param theURI
     * @param requestMethod
     * @param response
     */
    private static void logUri(String theURI, HTTPRequestMethods requestMethod, Response response) {
        if (response != null) {
            LoggerUtil.logINFO("\n\nrequestMethod: " + requestMethod.toString());
            LoggerUtil.logINFO("\n\nrequestURI: " + theURI);
            LoggerUtil.logINFO("\n\nresponse_Status: " + response.getStatusCode());
        }
    }

    /**
     * print the response
     * @param response
     */
    private static void logResponse(Response response) {
        if (response != null)
            LoggerUtil.logINFO("\n\nRESPONSE\n" + response.asString() + "\n*********\n\n");
    }
}
