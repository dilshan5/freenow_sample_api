package com.freenow.sample.api.response.models.PostModel;

import java.util.HashMap;
import java.util.Map;

/**
 * This class map with GET Post detail response
 * Generate Plain Java Objects from JSON
 * Used http://www.jsonschema2pojo.org/
 */
public class PostDetails {
    private int userId;
    private int id;
    private String title;
    private String body;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
