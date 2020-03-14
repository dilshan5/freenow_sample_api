package com.freenow.sample.api.common;

/**
 * contain constant HTTP request methods
 */
public enum HTTPRequestMethods {
    GET("GET"),
    PUT("PUT"),
    POST("POST"),
    DELETE("DELETE"),
    PATCH("PATCH");

    private String value;

    private HTTPRequestMethods(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

}
