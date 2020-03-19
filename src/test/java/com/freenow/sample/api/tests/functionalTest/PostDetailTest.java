package com.freenow.sample.api.tests.functionalTest;

import com.freenow.sample.api.common.LoggerUtil;
import com.freenow.sample.api.common.StatusCodes;
import com.freenow.sample.api.functions.PostFunctions;
import com.freenow.sample.api.requests.data.UserDataProvider;
import com.freenow.sample.api.response.models.PostModel.PostDetails;
import com.freenow.sample.api.util.ResponseUtil;
import com.freenow.sample.api.util.TestBase;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 * Test cases which belongs to Post endpoint
 */
public class PostDetailTest extends TestBase {

    private static int[] postIDList;
    private static int[] userIDList;
    private static Object[] postDetails;

    public static void setUserIDList(int[] userIDList) {
        PostDetailTest.userIDList = userIDList;
    }

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
    public void PostDetailTest() {
        setPostDetails(null);
        setPostIDList(null);
        setUserIDList(null);
    }


    @Test(description = "ID-003", dataProvider = "valid-user-ids-provider", dataProviderClass = UserDataProvider.class)
    public static void testGetPostWithValidUserID(Object userID) {
        SoftAssert softAssert = new SoftAssert();
        Response response = PostFunctions.searchPostByUserID(userID);
        if (ResponseUtil.getResponseStatusCode(response) == StatusCodes.SUCCESS_200_CODE) {
            try {
                // map the response to Post Details object
                postDetails = ResponseUtil.getObject(response.asString(), PostDetails[].class);
            } catch (Exception e) {
                LoggerUtil.logERROR(e.getMessage(), e);
                // Json Schema validation
                Assert.fail("ERROR : Returned invalid JSON Schema for the Post details response.");
            }
            //get List of Post IDs
            postIDList = PostFunctions.getPostIDsList(postDetails);
            //get List of User ID for each post
            userIDList = PostFunctions.getUserIDsForEachPost(postDetails);

        }

        softAssert.assertEquals(ResponseUtil.getResponseStatusCode(response), StatusCodes.SUCCESS_200_CODE, "ERROR : Response status code should be 200.");
        softAssert.assertEquals(ResponseUtil.getResponseStatus(response), "OK", "ERROR : Expected status message: OK. But got status message: " + ResponseUtil.getResponseStatus(response));
        softAssert.assertNotNull(postIDList, "ERROR : POST ID is missing in the response for User ID : " + userID);
        //Verify response only contain unique Post IDs for the given user. So the Post IDs should be unique always
        softAssert.assertFalse(ResponseUtil.isIDsDuplicate(postIDList), "ERROR : Found duplicate Post IDs in the response.");
        //Verify the response contain Post details which belongs to the requested User only. So User IDs should be same.
        softAssert.assertTrue(ResponseUtil.isIdenticalIds(userIDList, userID), "ERROR : Received details for irrelevant User IDs:");
        softAssert.assertAll();
        LoggerUtil.logINFO("Verified Post with ID: " + userID.toString());

    }

    @Test(description = "ID-004", dataProvider = "invalid-user-ids-provider", dataProviderClass = UserDataProvider.class)
    public static void testGetPostWithInvalidUserID(Object userID) {
        SoftAssert softAssert = new SoftAssert();
        Response response = PostFunctions.searchPostByUserID(userID);

        softAssert.assertEquals(ResponseUtil.getResponseStatusCode(response), StatusCodes.BAD_REQUESTS_400_CODE, "ERROR : Response status code should be 400.");
        softAssert.assertEquals(ResponseUtil.getResponseStatus(response), "Bad Request", "ERROR : Expected status message: Bad Request. But got status message: " + ResponseUtil.getResponseStatus(response));
        // Verify the response Error message
        softAssert.assertEquals(ResponseUtil.getResponseBody(response), "Expected Error message", "ERROR : Invalid response Error message.");
        softAssert.assertAll();

    }
}