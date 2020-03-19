package com.freenow.sample.api.tests.functionalTest;

import com.freenow.sample.api.common.*;
import com.freenow.sample.api.functions.UserFunctions;
import com.freenow.sample.api.requests.data.UserDataProvider;
import com.freenow.sample.api.response.models.UserModel.UserDetails;
import com.freenow.sample.api.util.HeadersUtil;
import com.freenow.sample.api.util.ResponseUtil;
import com.freenow.sample.api.util.TestBase;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 * Test cases which belongs to User endpoint
 */
public class UserDetailTest extends TestBase {

    private static Long userID;
    private static Object[] userDetails; // store User details data
    private static String responseUserName;
    private static String responseUserEmailAddress;

    public static void setUserID(Long userID) {
        UserDetailTest.userID = userID;
    }

    public static void setResponseUserName(String responseUserName) {
        UserDetailTest.responseUserName = responseUserName;
    }

    public static void setResponseUserEmailAddress(String responseUserEmailAddress) {
        UserDetailTest.responseUserEmailAddress = responseUserEmailAddress;
    }

    public static Long getUserID() {
        return userID;
    }


    public static void setUserDetails(Object[] userDetails) {
        UserDetailTest.userDetails = userDetails;
    }

    @BeforeMethod
    public void UserDetailTest() {
        setUserDetails(null);
        setUserID(null);
        setResponseUserName(null);
        setResponseUserEmailAddress(null);
    }

    @Test(description = "ID-001 - Verify User can GET User details by valid Name.", dataProvider = "valid-user-data-provider",
            dataProviderClass = UserDataProvider.class, groups = {Constant.BVT})
    public static void testGetUserWithValidName(Object userName) {
        SoftAssert softAssert = new SoftAssert();
        Response response = UserFunctions.searchUserByName(userName);
        if (ResponseUtil.getResponseStatusCode(response) == StatusCodes.SUCCESS_200_CODE) {
            try {
                // map the response to User Details object
                userDetails = ResponseUtil.getObject(response.asString(), UserDetails[].class);
            } catch (Exception e) {
                LoggerUtil.logERROR(e.getMessage(), e);
                // Json Schema validation
                Assert.fail("ERROR : Returned invalid JSON Schema for the User details response.");
            }

            //get User ID
            userID = ((userDetails.length != 0) ? ((UserDetails) userDetails[0]).getId() : null);
            //get User Name
            responseUserName = ((userDetails.length != 0) ? ((UserDetails) userDetails[0]).getUsername() : null);
            //get User Email address
            responseUserEmailAddress = ((userDetails.length != 0) ? ((UserDetails) userDetails[0]).getEmail() : null);
        }

        softAssert.assertEquals(ResponseUtil.getResponseStatusCode(response), StatusCodes.SUCCESS_200_CODE,
                "ERROR : Response status code should be 200.");
        softAssert.assertEquals(ResponseUtil.getResponseStatus(response), "OK",
                "ERROR : Expected status: OK. But got status: " + ResponseUtil.getResponseStatus(response));
        // Validate whether expected user details was returned
        softAssert.assertEquals(responseUserName, userName, "ERROR : User Name mismatched found in the returned JSON response.");
        // Check the UserID is not empty
        softAssert.assertNotNull(userID, "ERROR : Unable to find  UserID in JSON response.");
        // Check for user email address format
        softAssert.assertTrue(ResponseUtil.isValidEmailAddress(responseUserEmailAddress),
                "ERROR : Invalid Email address format found.");
        softAssert.assertAll();
        LoggerUtil.logINFO("Verified user '" + userName + "' exists with the userID " + userID);
    }

    @Test(description = "ID-002 - Verify User GET correct error response for invalid Name search.", dataProvider = "invalid-user-data-provider",
            dataProviderClass = UserDataProvider.class, groups = {Constant.REGRESSION})
    public static void testGetUserWithInvalidName(Object userName) {
        SoftAssert softAssert = new SoftAssert();
        Response response = UserFunctions.searchUserByName(userName);

        softAssert.assertEquals(ResponseUtil.getResponseStatusCode(response), StatusCodes.BAD_REQUESTS_400_CODE,
                "ERROR : Response status code should be 400.");
        softAssert.assertEquals(ResponseUtil.getResponseStatus(response), "Bad Request",
                "ERROR : Expected status message: Bad Request. But got status message: " + ResponseUtil.getResponseStatus(response));
        // Verify the response Error message
        softAssert.assertEquals(ResponseUtil.getResponseBody(response), "Expected Error message",
                "ERROR : Invalid response Error message.");
        softAssert.assertAll();
    }

    @Test(description = "ID-009 - Verify User GET correct error response for invalid Endpoint invoke.", groups = {Constant.REGRESSION})
    public static void testGetUserWithInvalidEndpoint() {
        SoftAssert softAssert = new SoftAssert();
        Response response = RestUtil.send(HeadersUtil.getJsonHeaders(), "", "user", HTTPRequestMethods.GET, null);

        softAssert.assertEquals(ResponseUtil.getResponseStatusCode(response), StatusCodes.NOT_FOUND_404_CODE,
                "ERROR : Response status code should be 404.");
        softAssert.assertEquals(ResponseUtil.getResponseStatus(response), "Not Found",
                "ERROR : Expected status message: Not Found. But got status message: " + ResponseUtil.getResponseStatus(response));
        // Verify the response Error message
        softAssert.assertEquals(ResponseUtil.getResponseBody(response), "Expected Error message",
                "ERROR : Invalid response Error message.");
        softAssert.assertAll();
    }
}
