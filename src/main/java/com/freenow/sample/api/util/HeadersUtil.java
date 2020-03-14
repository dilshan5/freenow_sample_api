package com.freenow.sample.api.util;


import java.util.HashMap;

/**
 * all headers are generated here.
 */
public class HeadersUtil {

    public static HashMap<String, String> getJsonHeaders() {
        HashMap<String, String> headers = new HashMap();
        headers.put("Content-Type", "application/xml");
        return headers;
    }

}
