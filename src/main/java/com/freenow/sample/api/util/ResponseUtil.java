package com.freenow.sample.api.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.freenow.sample.api.common.Constant;
import com.freenow.sample.api.common.LoggerUtil;
import io.restassured.response.Response;

import java.io.IOException;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Handle all the responses in this class
 * Converting response to an object or getting values in the response
 */
public class ResponseUtil {

    private static Pattern pattern;
    private static Matcher matcher;

    /**
     * @param response
     * @param className to map the response.
     * @return
     */
    public static Object[] getObject(String response, Class c) {
        try {
            return (Object[]) new ObjectMapper().readValue(response, c);
        } catch (IOException e) {
            LoggerUtil.logERROR(e.getMessage(), e);
        }
        return null;
    }

    /**
     * @param response
     * @return status code
     */
    public static int getResponseStatusCode(Response response) {
        return response.getStatusCode();
    }

    /**
     * Split the response StatusLine to get response message
     * eg: HTTP/1.1 404 Not Found
     *
     * @param response
     * @return status message
     */
    public static String getResponseStatus(Response response) {
        return response.getStatusLine().split(Constant.RESPONSE_MESSAGE_PATTERN)[1].toString();
    }

    /**
     * @param response
     * @return response body
     */
    public static String getResponseBody(Response response) {
        return response.asString();
    }

    /**
     * HashSet doesn't allow duplicates elements
     *
     * @param list
     * @return true if  duplicate ID found in the list
     */
    public static boolean isIDsDuplicate(int[] list) {
        boolean isDuplicate = false;
        HashSet<Integer> set = new HashSet<Integer>();
        HashSet<Integer> duplicateIDs = new HashSet<Integer>();
        if (list != null) {
            for (Integer duplicate : list) {
                //If add() returns false, that ID is a duplicate
                if (set.add(duplicate) == false) {
                    isDuplicate = true;
                    duplicateIDs.add(duplicate);
                }
            }
        }
        if (isDuplicate)
            LoggerUtil.logINFO("Found following duplicate IDs: " + duplicateIDs);

        return isDuplicate;
    }

    /**
     * Check whether List of IDs contain any different ID other than searched ID
     * Should contain only element which is the searched ID
     *
     * @param list
     * @param expectedID
     * @return
     */
    public static boolean isIdenticalIds(int[] list, Object expectedID) {
        boolean isIdentical = true;
        HashSet<Integer> set = new HashSet<Integer>();
        if (list != null) {
            for (Integer duplicate : list) {
                //Only added IDs which are not duplicate
                set.add(duplicate);
            }
            // should have only one ID exists which was search ID in the query
            // expected ID should match with the returned list of IDs
            if ((set.size() > 1) || !(set.contains(expectedID))) {
                isIdentical = false;
                LoggerUtil.logINFO("ERROR : Received details for following irrelevant IDs: " + set + " in the JSON response.");
            }

        }
        return isIdentical;
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

}




