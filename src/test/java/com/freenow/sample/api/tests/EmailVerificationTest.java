package com.freenow.sample.api.tests;

import com.freenow.sample.api.response.models.UserModel.UserDetails;
import com.freenow.sample.api.util.ResponseUtil;
import com.freenow.sample.api.util.TestBase;
import com.freenow.sample.api.functions.UserFunctions;
import io.restassured.response.Response;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class EmailVerificationTest extends TestBase {

    private static Long userID;
    private static Object[] userDetails;
    private static SoftAssert softAssert;


    @BeforeClass
    public static void initiate(ITestContext iTestContext) {
        iTestContext.setAttribute("feature", "Email Verification - Comments");
        softAssert = new SoftAssert();
    }

    @Test(description = "ID-001")
    public static void testUserSearchByName() {
        Response response = UserFunctions.searchUserByName("Antonette");
        if (ResponseUtil.getResponseStatus(response) == 200) {
            // map the response to User Details object
            userDetails = ResponseUtil.getObject(response.asString(), UserDetails[].class);
            //get User ID
            userID = ((userDetails.length != 0) ? ((UserDetails) userDetails[0]).getId() : null);
        }

        softAssert.assertEquals(ResponseUtil.getResponseStatus(response), 200, "ERROR : Response status code should be 200.");
        softAssert.assertNotNull(userID, "ERROR : User ID was not found in the response.");
        softAssert.assertAll();
    }

}
