package com.freenow.sample.api.tests.functionalTest;

import com.freenow.sample.api.functions.PostFunctions;
import com.freenow.sample.api.requests.data.UserDataProvider;
import com.freenow.sample.api.response.models.PostModel.PostDetails;
import com.freenow.sample.api.response.models.UserModel.UserDetails;
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

    public static void setPostDetails(Object[] postDetails) {
        PostDetailTest.postDetails = postDetails;
    }

    public static int[] getPostIDList() {
        return postIDList;
    }

    @BeforeClass
    public static void initiate(ITestContext iTestContext) {
        iTestContext.setAttribute("feature", "User Post Detail - Functional");
    }

    @Test(description = "ID-003", dataProvider = "valid-user-ids-provider", dataProviderClass = UserDataProvider.class)
    public static void testPostSearchByValidUserID(Object userID) {
        SoftAssert softAssert = new SoftAssert();
        Response response = PostFunctions.searchPostByUserID(userID);
        if (ResponseUtil.getResponseStatus(response) == 200) {
            setPostDetails(null);
            // map the response to Post Details object
            postDetails = ResponseUtil.getObject(response.asString(), PostDetails[].class);
            //initialize array
            postIDList = ((postDetails.length != 0) ? new int[postDetails.length] : null);
            //get Post IDs List
            for (int i = 0; i < postDetails.length; i++) {
                postIDList[i] = ((PostDetails) postDetails[i]).getId();

            }
            softAssert.assertEquals(ResponseUtil.getResponseStatus(response), 200, "ERROR : Response status code should be 200.");
            softAssert.assertNotNull(postIDList, "ERROR : Unable to find a POST ID : " + userID + ". Please check again.");
            softAssert.assertAll();
        }
    }

    @Test(description = "ID-004", dataProvider = "invalid-user-ids-provider", dataProviderClass = UserDataProvider.class)
    public static void testPostSearchByInvalidUserID(Object userID) {
        SoftAssert softAssert = new SoftAssert();
        Response response = PostFunctions.searchPostByUserID(userID);
        if (ResponseUtil.getResponseStatus(response) == 200) {
            setPostDetails(null);
            // map the response to Post Details object
            postDetails = ResponseUtil.getObject(response.asString(), PostDetails[].class);
            //initialize array
            postIDList = ((postDetails.length != 0) ? new int[postDetails.length] : null);
            //get Post IDs List
            for (int i = 0; i < postDetails.length; i++) {
                postIDList[i] = ((PostDetails) postDetails[i]).getId();

            }
            softAssert.assertEquals(ResponseUtil.getResponseStatus(response), 200, "ERROR : Response status code should be 200.");
            softAssert.assertNull(postIDList, "ERROR : Returned a Post details for a non existing user ID : " + userID + ". Please check again.");
            softAssert.assertAll();
        }

    }
}