package com.freenow.sample.api.tests.functionalTest;

import com.freenow.sample.api.functions.PostFunctions;
import com.freenow.sample.api.requests.data.UserDataProvider;
import com.freenow.sample.api.response.models.PostModel.PostDetails;
import com.freenow.sample.api.util.ResponseUtil;
import com.freenow.sample.api.util.TestBase;
import io.restassured.response.Response;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 * This class used to test all the functions related to User Posts
 */
public class PostDetailTest extends TestBase {

    private static int[] postIDList;
    private static Object[] postDetails;
    private static SoftAssert softAssert;

    @BeforeClass
    public static void initiate(ITestContext iTestContext) {
        iTestContext.setAttribute("feature", "User Post Detail - Functional");
        softAssert = new SoftAssert();
    }

    @Test(description = "ID-003", dataProvider = "valid-user-ids-provider", dataProviderClass = UserDataProvider.class)
    public static void testPostSearchByUserID(Long userID) {
        Response response = PostFunctions.searchPostByUserID(userID);
        if (ResponseUtil.getResponseStatus(response) == 200) {
            // map the response to Post Details object
            postDetails = ResponseUtil.getObject(response.asString(), PostDetails[].class);
            //get Post IDs List
            postIDList = new int[postDetails.length];
            for (int i = 0; i < postDetails.length; i++) {
                postIDList[i] = ((PostDetails) postDetails[i]).getId();

            }
            softAssert.assertEquals(ResponseUtil.getResponseStatus(response), 200, "ERROR : Response status code should be 200.");
            softAssert.assertNotNull(postIDList, "ERROR : Unable to find a POST ID : " + userID + ". Please check again.");
            softAssert.assertAll();
        }
    }

    @Test(description = "ID-004", dataProvider = "invalid-user-ids-provider", dataProviderClass = UserDataProvider.class)
    public static void testPostSearchByInvalidUserID(Long userID) {
        Response response = PostFunctions.searchPostByUserID(userID);
        if (ResponseUtil.getResponseStatus(response) == 200) {
            // map the response to Post Details object
            postDetails = ResponseUtil.getObject(response.asString(), PostDetails[].class);
            //get Post IDs List
            postIDList = new int[postDetails.length];
            for (int i = 0; i < postDetails.length; i++) {
                postIDList[i] = ((PostDetails) postDetails[i]).getId();

            }
            softAssert.assertEquals(ResponseUtil.getResponseStatus(response), 200, "ERROR : Response status code should be 200.");
            softAssert.assertNotNull(postIDList, "ERROR : Unable to find a Post by user ID : " + userID + ". Please check again.");
            softAssert.assertAll();
        }

    }
}