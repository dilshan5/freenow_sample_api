package com.freenow.sample.api.tests.functionalTest;

import com.freenow.sample.api.common.LoggerUtil;
import com.freenow.sample.api.common.StatusCodes;
import com.freenow.sample.api.functions.PostFunctions;
import com.freenow.sample.api.requests.data.UserDataProvider;
import com.freenow.sample.api.response.models.PostModel.PostDetails;
import com.freenow.sample.api.util.ResponseUtil;
import com.freenow.sample.api.util.TestBase;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 * This class used to test all the functions related to User Posts
 */
public class PostDetailTest extends TestBase {

    private static int[] postIDList;
    private static Object[] postDetails;

    public static void setPostIDList(int[] postIDList) {
        PostDetailTest.postIDList = postIDList;
    }

    public static void setPostDetails(Object[] postDetails) {
        PostDetailTest.postDetails = postDetails;
    }

    public static int[] getPostIDList() {
        return postIDList;
    }

    @BeforeMethod
    public void init() {
        setPostDetails(null);
        setPostIDList(null);
    }


    @Test(description = "ID-003", dataProvider = "valid-user-ids-provider", dataProviderClass = UserDataProvider.class)
    public static void testGetPostWithValidUserID(Object userID) {
        SoftAssert softAssert = new SoftAssert();
        Response response = PostFunctions.searchPostByUserID(userID);
        if (ResponseUtil.getResponseStatusCode(response) == StatusCodes.SUCCESS_200_CODE) {
            // map the response to Post Details object
            postDetails = ResponseUtil.getObject(response.asString(), PostDetails[].class);
            //get List of Post IDs
            postIDList = PostFunctions.getPostIDsList(postDetails);
        }

        softAssert.assertEquals(ResponseUtil.getResponseStatusCode(response), 200, "ERROR : Response status code should be 200.");
        softAssert.assertEquals(ResponseUtil.getResponseStatus(response), "OK", "ERROR : Expected status message: OK. But got status message: " + ResponseUtil.getResponseStatus(response));
        softAssert.assertNotNull(postIDList, "ERROR : Unable to find a POST ID : " + userID + ". Please check again.");
        softAssert.assertAll();
        LoggerUtil.logINFO("Verified Post with ID: " + userID.toString());

    }

    @Test(description = "ID-004", dataProvider = "invalid-user-ids-provider", dataProviderClass = UserDataProvider.class)
    public static void testGetPostWithInvalidUserID(Object userID) {
        SoftAssert softAssert = new SoftAssert();
        Response response = PostFunctions.searchPostByUserID(userID);

        softAssert.assertEquals(ResponseUtil.getResponseStatusCode(response), 400, "ERROR : Response status code should be 400.");
        softAssert.assertEquals(ResponseUtil.getResponseStatus(response), "Bad Request", "ERROR : Expected status message: Bad Request. But got status message: " + ResponseUtil.getResponseStatus(response));
        // Verify the response Error message
        softAssert.assertEquals(ResponseUtil.getResponseBody(response), "Expected Error message", "ERROR : Invalid response Error message.");
        softAssert.assertAll();

    }
}