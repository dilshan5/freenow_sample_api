package com.freenow.sample.api.common;

/**
 * contain constant HTTP header values
 */
public enum ContentTypeOptions {

    APPLICATION_JSON("application/json");

    private String value;

    private ContentTypeOptions(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
