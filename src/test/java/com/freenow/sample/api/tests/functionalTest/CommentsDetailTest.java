package com.freenow.sample.api.tests.functionalTest;

import com.freenow.sample.api.common.LoggerUtil;
import com.freenow.sample.api.common.StatusCodes;
import com.freenow.sample.api.functions.CommentsFunctions;
import com.freenow.sample.api.requests.data.PostDataProvider;
import com.freenow.sample.api.response.models.CommentsModel.CommentDetails;
import com.freenow.sample.api.util.ResponseUtil;
import com.freenow.sample.api.util.TestBase;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CommentsDetailTest extends TestBase {

    private static int[] commentsIDList;
    private static Object[] commentsDetails;

    public static void setCommentsIDList(int[] commentsIDList) {
        CommentsDetailTest.commentsIDList = commentsIDList;
    }

    public static void setCommentsDetails(Object[] commentsDetails) {
        CommentsDetailTest.commentsDetails = commentsDetails;
    }

    @BeforeMethod
    public void init() {
        setCommentsDetails(null);
        setCommentsIDList(null);
    }

    @Test(description = "ID-006", dataProvider = "valid-post-ids-provider", dataProviderClass = PostDataProvider.class)
    public static void testGetCommentsWithValidPostID(Object postID) {
        SoftAssert softAssert = new SoftAssert();
        Response response = CommentsFunctions.searchCommentsByPostID(postID);
        if (ResponseUtil.getResponseStatusCode(response) == StatusCodes.SUCCESS_200_CODE) {
            try {
                // map the response to Comment Details object
                commentsDetails = ResponseUtil.getObject(response.asString(), CommentDetails[].class);
            } catch (Exception ex) {
                // Json Schema validation
                Assert.fail("ERROR : Returned invalid JSON Schema for the Comment details response.");
            }
            //Get list of comments IDs
            commentsIDList = CommentsFunctions.getCommentIDsList(commentsDetails);
        }

        softAssert.assertEquals(ResponseUtil.getResponseStatusCode(response), StatusCodes.SUCCESS_200_CODE, "ERROR : Response status code should be 200.");
        softAssert.assertEquals(ResponseUtil.getResponseStatus(response), "OK", "ERROR : Expected status message: OK. But got status message: " + ResponseUtil.getResponseStatus(response));
        softAssert.assertNotNull(commentsIDList, "ERROR : Unable to find a Comment in the Post ID: " + postID + ". Please check again.");
        softAssert.assertAll();
        LoggerUtil.logINFO("Verified Comments found for postID: " + postID.toString());
    }

    @Test(description = "ID-007", dataProvider = "invalid-post-ids-provider", dataProviderClass = PostDataProvider.class)
    public static void testGetCommentsWithInvalidPostID(Object postID) {
        SoftAssert softAssert = new SoftAssert();
        Response response = CommentsFunctions.searchCommentsByPostID(postID);

        softAssert.assertEquals(ResponseUtil.getResponseStatusCode(response), StatusCodes.BAD_REQUESTS_400_CODE, "ERROR : Response status code should be 400.");
        softAssert.assertEquals(ResponseUtil.getResponseStatus(response), "Bad Request", "ERROR : Expected status message: Bad Request. But got status message: " + ResponseUtil.getResponseStatus(response));
        // Verify the response Error message
        softAssert.assertEquals(ResponseUtil.getResponseBody(response), "Expected Error message", "ERROR : Invalid response Error message.");
        softAssert.assertAll();

    }

    @Parameters({"postID"})
    @Test(description = "ID-008")
    public static void testVerifyEmailAddress(@Optional("21") int postID) {
        SoftAssert softAssert = new SoftAssert();
        boolean isValid = false;
        testGetCommentsWithValidPostID(postID);
        for (CommentDetails comment : (CommentDetails[]) commentsDetails) {
            isValid = CommentsFunctions.isValidEmailAddress(comment.getEmail());
            softAssert.assertTrue(isValid, "Found Invalid email address: " + comment.getEmail() + " in comment ID: " + comment.getId() + " which belongs to Post ID: " + comment.getPostId());
            if (isValid)
                LoggerUtil.logINFO("Verified Email address: " + comment.getEmail() + " as a valid format.");
        }
        softAssert.assertAll();
    }

}
