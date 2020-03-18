package com.freenow.sample.api.functions;

import com.freenow.sample.api.common.HTTPRequestMethods;
import com.freenow.sample.api.common.LoggerUtil;
import com.freenow.sample.api.common.RestUtil;
import com.freenow.sample.api.common.URIs;
import com.freenow.sample.api.response.models.PostModel.PostDetails;
import com.freenow.sample.api.util.HeadersUtil;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class PostFunctions {

    private static int[] postIDList;
    private static int[] userIDList;

    /**
     * @param send any type of userIDs instead Long type only
     * @return User's Posts by User ID
     */

    public static Response searchPostByUserID(Object userID) {
        LoggerUtil.logINFO("REQUEST -> PostFunctions.search Posts by User ID: " + userID);
        Map<String, String> queryParameters = new HashMap<>();
        queryParameters.put("userId", userID.toString());
        Response response = RestUtil.send(HeadersUtil.getJsonHeaders(), "", URIs.POST_PATH, HTTPRequestMethods.GET, queryParameters);

        return response;
    }

    /**
     * @param postDetails
     * @return the list of PostIDs
     */
    public static int[] getPostIDsList(Object[] postDetails) {
        //initialize array
        postIDList = ((postDetails.length != 0) ? new int[postDetails.length] : null);
        //get Post IDs List
        for (int i = 0; i < postDetails.length; i++) {
            postIDList[i] = ((PostDetails) postDetails[i]).getId();

        }
        return postIDList;
    }/**/

    /**
     * Get the User ID for each Post
     *
     * @param postDetails
     * @return
     */
    public static int[] getUserIDsForEachPost(Object[] postDetails) {
        //initialize array
        userIDList = ((postDetails.length != 0) ? new int[postDetails.length] : null);
        //get Post IDs List
        for (int i = 0; i < postDetails.length; i++) {
            userIDList[i] = ((PostDetails) postDetails[i]).getUserId();

        }
        return userIDList;
    }


}
