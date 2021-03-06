package com.freenow.sample.api.functions;

import com.freenow.sample.api.common.*;
import com.freenow.sample.api.response.models.CommentsModel.CommentDetails;
import com.freenow.sample.api.util.HeadersUtil;
import com.freenow.sample.api.util.RequestUtil;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * All the methods required in Comment details Test
 */
public class CommentsFunctions {
    private static int[] commentsIDList;
    private static int[] postIDList;

    /**
     * @param postID - send any type of postIDs instead Integer only
     * @return user comments by Post ID
     */
    public static Response searchCommentsByPostID(Object postID) {
        LoggerUtil.logINFO("REQUEST -> PostFunctions.search user comments by Post ID: " + postID);
        Map<String, String> queryParameters = new HashMap<>();
        queryParameters.put("postId", postID.toString());
        Response response = RequestUtil.send(HeadersUtil.getJsonHeaders(), "", URIs.COMMENTS_PATH, HTTPRequestMethods.GET, queryParameters);

        return response;
    }

    /**
     * Get comment IDs for each Comment
     *
     * @param commentsDetails of the response
     * @return the list of CommentIDs
     */
    public static int[] getCommentIDsList(Object[] commentsDetails) {
        //initialize array
        commentsIDList = ((commentsDetails.length != 0) ? new int[commentsDetails.length] : null);
        //get Comment IDs List
        for (int i = 0; i < commentsDetails.length; i++) {
            commentsIDList[i] = ((CommentDetails) commentsDetails[i]).getId();

        }
        return commentsIDList;
    }


    /**
     * Get the Post ID for each Comment
     *
     * @param commentDetails of the response
     * @return the list of PostIDs
     */
    public static int[] getPostIDsForEachComment(Object[] commentDetails) {
        //initialize array
        postIDList = ((commentDetails.length != 0) ? new int[commentDetails.length] : null);
        //get Post IDs List
        for (int i = 0; i < commentDetails.length; i++) {
            postIDList[i] = ((CommentDetails) commentDetails[i]).getPostId();

        }
        return postIDList;
    }

}
