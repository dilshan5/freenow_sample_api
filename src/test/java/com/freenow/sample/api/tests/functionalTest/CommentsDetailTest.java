package com.freenow.sample.api.tests.functionalTest;

import com.freenow.sample.api.functions.CommentsFunctions;
import com.freenow.sample.api.functions.PostFunctions;
import com.freenow.sample.api.requests.data.PostDataProvider;
import com.freenow.sample.api.requests.data.UserDataProvider;
import com.freenow.sample.api.response.models.CommentsModel.CommentDetails;
import com.freenow.sample.api.response.models.PostModel.PostDetails;
import com.freenow.sample.api.util.ResponseUtil;
import com.freenow.sample.api.util.TestBase;
import io.restassured.response.Response;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CommentsDetailTest extends TestBase {

    private static int[] commentsIDList;
    private static Object[] commentsDetails;
    private static SoftAssert softAssert;

    public CommentsDetailTest() {
        softAssert = new SoftAssert();
    }

    public static Object[] getCommentsDetails() {
        return commentsDetails;
    }

    @BeforeClass
    public static void initiate(ITestContext iTestContext) {
        iTestContext.setAttribute("feature", "Comment Details in the Post - Functional");
    }

    @Test(description = "ID-006", dataProvider = "valid-post-ids-provider", dataProviderClass = PostDataProvider.class)
    public static void testCommentsSearchByPostID(Object postID) {
        Response response = CommentsFunctions.searchCommentsByPostID(postID);
        if (ResponseUtil.getResponseStatus(response) == 200) {
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
        Response response = CommentsFunctions.searchCommentsByPostID(postID);
        if (ResponseUtil.getResponseStatus(response) == 200) {
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
}
