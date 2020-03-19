package com.freenow.sample.api.functions;

import com.freenow.sample.api.common.HTTPRequestMethods;
import com.freenow.sample.api.common.LoggerUtil;
import com.freenow.sample.api.common.RestUtil;
import com.freenow.sample.api.common.URIs;
import com.freenow.sample.api.response.models.PostModel.PostDetails;
import com.freenow.sample.api.util.HeadersUtil;
import com.freenow.sample.api.util.RequestUtil;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * All the methods required in Post details Test
 */
public class PostFunctions {

    private static int[] postIDList;
    private static int[] userIDList;

    /**
     * @param userID send any type of userIDs formats instead Long type only
     * @return Post detail Response with searched user ID
     */
    public static Response searchPostByUserID(Object userID) {
        LoggerUtil.logINFO("REQUEST -> PostFunctions.search Posts by User ID: " + userID);
        Map<String, String> queryParameters = new HashMap<>();
        queryParameters.put("userId", userID.toString());
        Response response = RequestUtil.send(HeadersUtil.getJsonHeaders(), "", URIs.POST_PATH, HTTPRequestMethods.GET, queryParameters);

        return response;
    }

    /**
     * @param postDetails set of post details
     * @return the list of Post IDs
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
     * @param postDetails set of post details
     * @return the list of User IDs
     */
    public static int[] getUserIDsForEachPost(Object[] postDetails) {
        //initialize array
        userIDList = ((postDetails.length != 0) ? new int[postDetails.length] : null);
        //get User IDs List
        for (int i = 0; i < postDetails.length; i++) {
            userIDList[i] = ((PostDetails) postDetails[i]).getUserId();

        }
        return userIDList;
    }

}
