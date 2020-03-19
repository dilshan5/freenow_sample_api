package com.freenow.sample.api.tests.e2e;

import com.freenow.sample.api.common.LoggerUtil;
import com.freenow.sample.api.tests.functionalTest.CommentsDetailTest;
import com.freenow.sample.api.tests.functionalTest.PostDetailTest;
import com.freenow.sample.api.tests.functionalTest.UserDetailTest;
import com.freenow.sample.api.util.TestBase;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * This test class define all the E2E test cases
 */
public class E2EWorkFlowTest extends TestBase {

    @Parameters({"userName"})
    @Test(description = "ID-005 - E2E work flow to verify email address format in the comments for the posts made by given user.")
    public static void testEmailVerificationInComments(@Optional("Samantha") String userName) {
        //Get userID
        UserDetailTest.testGetUserWithValidName(userName);
        //Get all the Post IDs which has been posted by above user
        PostDetailTest.testGetPostWithValidUserID(UserDetailTest.getUserID());
        //Note: An email address can duplicate, because a person can type several comments for the same Post
        //Verify the email address format in each comment, for each post
        for (int postId : PostDetailTest.getPostIDList()) {
            CommentsDetailTest.testVerifyEmailAddress(postId);
        }
        LoggerUtil.logINFO("Verified Email address format in all Comments for User: " + userName);
    }

}


