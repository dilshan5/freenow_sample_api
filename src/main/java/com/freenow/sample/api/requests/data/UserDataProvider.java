package com.freenow.sample.api.requests.data;

import org.testng.annotations.DataProvider;

/**
 *  All the positive and negative test data for User details
 */
public class UserDataProvider {

    @DataProvider(name = "valid-user-data-provider")
    public static Object[][] getValidUsersDataprovider() {
        return new Object[][]{
                {"Samantha"},
                {"Leanne Graham"},
                {"Nicholas Runolfsdottir V"},
                {"Mrs. Dennis Schulist"}
        };
    }

    @DataProvider(name = "invalid-user-data-provider")
    public static Object[][] getInvalidUsersDataprovider() {
        return new Object[][]{
                {"samantha"},
                {6},
                {"@#$%%^"},
                {""},
                {"DennisdfghjDFGHJKDFGHJ"}
        };
    }

    @DataProvider(name = "valid-user-ids-provider")
    public static Object[][] getValidUsersIDsprovider() {
        return new Object[][]{
                {1},
                {"02"}
        };
    }

    @DataProvider(name = "invalid-user-ids-provider")
    public static Object[][] getInvalidUsersIDsprovider() {
        return new Object[][]{
                {-5},
                {65536},
                {"hfhgffg"},
                {"@#$"},
                {""}
        };
    }
}
