package com.freenow.sample.api.functions;

import com.freenow.sample.api.common.*;
import com.freenow.sample.api.response.models.CommentsModel.CommentDetails;
import com.freenow.sample.api.util.HeadersUtil;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommentsFunctions {
    private static int[] commentsIDList;
    private static int[] postIDList;
    private static Pattern pattern;
    private static Matcher matcher;

    /**
     * @param postID - send any type of postIDs instead Integer only
     * @return user comments by Post ID
     */
    public static Response searchCommentsByPostID(Object postID) {
        LoggerUtil.logINFO("REQUEST -> PostFunctions.search user comments by Post ID: " + postID);
        Map<String, String> queryParameters = new HashMap<>();
        queryParameters.put("postId", postID.toString());
        Response response = RestUtil.send(HeadersUtil.getJsonHeaders(), "", URIs.COMMENTS_PATH, HTTPRequestMethods.GET, queryParameters);

        return response;
    }

    /**
     * @param commentsDetails
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
     * Acceptable email prefix formats (User Name):
     *      Only letters (a-z), numbers (0-9), underscores (_) and periods (.) are allowed.
     *      The first character of username must be an ASCII letter (a-z) or number (0-9).
     *      Can not end with periods (.) or dash (-)
     * Acceptable email domain formats:
     *      Allowed characters: letters, numbers, dashes.
     * Acceptable email Top-level domains (TLD) formats:
     *      The last portion of the domain must be at 2-6 characters, for example: .com, .org, .cc
     * Acceptable email sub domain formats:
     *      Must be 2-6 letters (a-z) OR unlimited characters (a-zA-Z0-9)
     *       Can have multiple sub domains
     *      last sub domain must be 2-6 letters (a-z), for example: .com
     *
     * @param emailAddress
     * @return
     */
    public static boolean isValidEmailAddress(String emailAddress) {
        pattern = Pattern.compile(Constant.EMAIL_PATTERN);
        matcher = pattern.matcher(emailAddress);
        return matcher.matches();
    }

    /**
     * Get the Post ID for each Comment
     *
     * @param commentDetails
     * @return
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
