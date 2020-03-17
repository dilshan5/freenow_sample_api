package com.freenow.sample.api.tests.functionalTest;

import com.freenow.sample.api.common.LoggerUtil;
import com.freenow.sample.api.common.StatusCodes;
import com.freenow.sample.api.functions.UserFunctions;
import com.freenow.sample.api.requests.data.UserDataProvider;
import com.freenow.sample.api.response.models.UserModel.UserDetails;
import com.freenow.sample.api.util.ResponseUtil;
import com.freenow.sample.api.util.TestBase;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class UserDetailTest extends TestBase {

    private static Long userID;
    private static Object[] userDetails;
    private static String responseUserName;

    public static void setUserID(Long userID) {
        UserDetailTest.userID = userID;
    }

    public static Long getUserID() {
        return userID;
    }

    public static void setUserName(String userName) {
        UserDetailTest.responseUserName = userName;
    }

    public static void setUserDetails(Object[] userDetails) {
        UserDetailTest.userDetails = userDetails;
    }

    @BeforeMethod
    public void init() {
        setUserDetails(null);
        setUserID(null);
        setUserName(null);
    }

    @Test(description = "ID-001", dataProvider = "valid-user-data-provider", dataProviderClass = UserDataProvider.class)
    public static void testGetUserWithValidName(Object userName) {
        SoftAssert softAssert = new SoftAssert();
        Response response = UserFunctions.searchUserByName(userName);
        if (ResponseUtil.getResponseStatusCode(response) == StatusCodes.SUCCESS_200_CODE) {
            try {
                // map the response to User Details object
                userDetails = ResponseUtil.getObject(response.asString(), UserDetails[].class);
            } catch (Exception ex) {
                // Json Schema validation
                Assert.fail("ERROR : Returned invalid JSON Schema for the User details response.");
            }

            //get User ID
            userID = ((userDetails.length != 0) ? ((UserDetails) userDetails[0]).getId() : null);
            //get User Name
            responseUserName = ((userDetails.length != 0) ? ((UserDetails) userDetails[0]).getUsername() : null);
        }

        softAssert.assertEquals(ResponseUtil.getResponseStatusCode(response), StatusCodes.SUCCESS_200_CODE, "ERROR : Response status code should be 200.");
        softAssert.assertEquals(ResponseUtil.getResponseStatus(response), "OK", "ERROR : Expected status: OK. But got status: " + ResponseUtil.getResponseStatus(response));
        // Validate whether expected user details was returned
        softAssert.assertEquals(responseUserName, userName, "ERROR : User Name mismatched found in the returned JSON response.");
        // Check the UserID is not empty
        softAssert.assertNotNull(userID, "ERROR : Unable to find  UserID in JSON response.");
        softAssert.assertAll();
        LoggerUtil.logINFO("Verified user '" + userName + "' exists with the userID " + userID);
    }

    @Test(description = "ID-002", dataProvider = "invalid-user-data-provider", dataProviderClass = UserDataProvider.class)
    public static void testGetUserWithInvalidName(Object userName) {
        SoftAssert softAssert = new SoftAssert();
        Response response = UserFunctions.searchUserByName(userName);

        softAssert.assertEquals(ResponseUtil.getResponseStatusCode(response), StatusCodes.BAD_REQUESTS_400_CODE, "ERROR : Response status code should be 400.");
        softAssert.assertEquals(ResponseUtil.getResponseStatus(response), "Bad Request", "ERROR : Expected status message: Bad Request. But got status message: " + ResponseUtil.getResponseStatus(response));
        // Verify the response Error message
        softAssert.assertEquals(ResponseUtil.getResponseBody(response), "Expected Error message", "ERROR : Invalid response Error message.");
        softAssert.assertAll();
    }
}
