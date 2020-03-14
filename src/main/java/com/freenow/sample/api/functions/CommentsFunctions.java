package com.freenow.sample.api.functions;

import com.freenow.sample.api.common.HTTPRequestMethods;
import com.freenow.sample.api.common.LoggerUtil;
import com.freenow.sample.api.common.RestUtil;
import com.freenow.sample.api.common.URIs;
import com.freenow.sample.api.util.HeadersUtil;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class CommentsFunctions {

    /**
     *
     * @param postID - send any type of postIDs instead Integer only
     * @return user comments by Post ID
     */
    public static Response searchCommentsByPostID(Object postID){
        LoggerUtil.logINFO("REQUEST -> PostFunctions.search user comments by Post ID: " + postID);
        Map<String, String> queryParameters = new HashMap<>();
        queryParameters.put("postId", postID.toString());
        Response response = RestUtil.send(HeadersUtil.getJsonHeaders(),"", URIs.COMMENTS_PATH, HTTPRequestMethods.GET,queryParameters);

        return response;
    }
}
