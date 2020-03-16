package com.freenow.sample.api.tests.e2e;

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
    @Test(description = "ID-005")
    public static void testEmailVerificationInComments(@Optional("Samantha") String userName) {
        //Get userID
        UserDetailTest.testUserSearchByValidName(userName);
        //Get all the Post IDs which has been posted by above user
        PostDetailTest.testPostSearchByValidUserID(UserDetailTest.getUserID());
        //Verify the email address format in each comment, for each post
        for(int postId : PostDetailTest.getPostIDList()){
            CommentsDetailTest.testVerifyEmailAddress(postId);
        }

    }

}


