package com.freenow.sample.api.tests.functionalTest;

import com.freenow.sample.api.functions.UserFunctions;
import com.freenow.sample.api.requests.data.UserDataProvider;
import com.freenow.sample.api.response.models.UserModel.UserDetails;
import com.freenow.sample.api.util.ResponseUtil;
import com.freenow.sample.api.util.TestBase;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class UserDetailTest extends TestBase {

    private static Long userID;
    private static Object[] userDetails;

    public static Long getUserID() {
        return userID;
    }

    public static void setUserDetails(Object[] userDetails) {
        UserDetailTest.userDetails = userDetails;
    }

    @Test(description = "ID-001", dataProvider = "valid-user-data-provider", dataProviderClass = UserDataProvider.class)
    public static void testUserSearchByValidName(Object userName) {
        SoftAssert softAssert = new SoftAssert();
        Response response = UserFunctions.searchUserByName(userName);
        if (ResponseUtil.getResponseStatus(response) == 200) {
            setUserDetails(null);
            // map the response to User Details object
            userDetails = ResponseUtil.getObject(response.asString(), UserDetails[].class);
            //get User ID
            userID = ((userDetails.length != 0) ? ((UserDetails) userDetails[0]).getId() : null);
        }

        softAssert.assertEquals(ResponseUtil.getResponseStatus(response), 200, "ERROR : Response status code should be 200.");
        softAssert.assertNotNull(userID, "ERROR : Unable to find a User named : " + userName + ". Please check again.");
        softAssert.assertAll();
    }

    @Test(description = "ID-002", dataProvider = "invalid-user-data-provider", dataProviderClass = UserDataProvider.class)
    public static void testUserSearchByInvalidName(Object userName) {
        SoftAssert softAssert = new SoftAssert();
        Response response = UserFunctions.searchUserByName(userName);
        if (ResponseUtil.getResponseStatus(response) == 200) {
            setUserDetails(null);
            // map the response to User Details object
            userDetails = ResponseUtil.getObject(response.asString(), UserDetails[].class);
            //get User ID
            userID = ((userDetails.length != 0) ? ((UserDetails) userDetails[0]).getId() : null);
        }

        softAssert.assertEquals(ResponseUtil.getResponseStatus(response), 200, "ERROR : Response status code should be 200.");
        softAssert.assertNull(userID, "ERROR : Returned Data for non existing User : " + userName);
        softAssert.assertAll();
    }
}
