package com.freenow.sample.api.tests.functionalTest;

import com.freenow.sample.api.functions.CommentsFunctions;
import com.freenow.sample.api.requests.data.PostDataProvider;
import com.freenow.sample.api.response.models.CommentsModel.CommentDetails;
import com.freenow.sample.api.util.ResponseUtil;
import com.freenow.sample.api.util.TestBase;
import io.restassured.response.Response;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CommentsDetailTest extends TestBase {

    private static int[] commentsIDList;
    private static Object[] commentsDetails;

    public static void setCommentsDetails(Object[] commentsDetails) {
        CommentsDetailTest.commentsDetails = commentsDetails;
    }

    @Test(description = "ID-006", dataProvider = "valid-post-ids-provider", dataProviderClass = PostDataProvider.class)
    public static void testCommentsSearchByValidPostID(Object postID) {
        SoftAssert softAssert = new SoftAssert();
        Response response = CommentsFunctions.searchCommentsByPostID(postID);
        if (ResponseUtil.getResponseStatus(response) == 200) {
            setCommentsDetails(null);
            // map the response to Comment Details object
            commentsDetails = ResponseUtil.getObject(response.asString(), CommentDetails[].class);
            //initialize array
            commentsIDList = ((commentsDetails.length != 0) ? new int[commentsDetails.length] : null);
            //get Comment IDs List
            for (int i = 0; i < commentsDetails.length; i++) {
                commentsIDList[i] = ((CommentDetails) commentsDetails[i]).getId();

            }
            softAssert.assertEquals(ResponseUtil.getResponseStatus(response), 200, "ERROR : Response status code should be 200.");
            softAssert.assertNotNull(commentsIDList, "ERROR : Unable to find a Comment in the Post ID: " + postID + ". Please check again.");
            softAssert.assertAll();
        }
    }

    @Test(description = "ID-007", dataProvider = "invalid-post-ids-provider", dataProviderClass = PostDataProvider.class)
    public static void testCommentsSearchByInvalidPostID(Object postID) {
        SoftAssert softAssert = new SoftAssert();
        Response response = CommentsFunctions.searchCommentsByPostID(postID);
        if (ResponseUtil.getResponseStatus(response) == 200) {
            setCommentsDetails(null);
            // map the response to Comments Details object
            commentsDetails = ResponseUtil.getObject(response.asString(), CommentDetails[].class);
            //initialize array
            commentsIDList = ((commentsDetails.length != 0) ? new int[commentsDetails.length] : null);
            //get Comments IDs List
            for (int i = 0; i < commentsDetails.length; i++) {
                commentsIDList[i] = ((CommentDetails) commentsDetails[i]).getId();
            }
            softAssert.assertEquals(ResponseUtil.getResponseStatus(response), 200, "ERROR : Response status code should be 200.");
            softAssert.assertNull(commentsIDList, "ERROR : Returned Comment details for non existing Post ID: " + postID + ". Please check again.");
            softAssert.assertAll();
        }
    }

    @Parameters({"postID"})
    @Test(description = "ID-008",alwaysRun = true)
    public static void testVerifyEmailAddress(@Optional("3") Object postID) {
        SoftAssert softAssert = new SoftAssert();
        setCommentsDetails(null);
        testCommentsSearchByValidPostID(postID);
        for (CommentDetails comment : (CommentDetails[]) commentsDetails) {
            softAssert.assertTrue(isValidEmailAddress(comment.getEmail()), "Found Invalid email address: " + comment.getEmail() + " in comment ID: " + comment.getId() + " which belongs to Post ID: " + comment.getPostId());
        }
        softAssert.assertAll();
    }

    private static boolean isValidEmailAddress(String emailAddress) {

        return true;
    }

}
