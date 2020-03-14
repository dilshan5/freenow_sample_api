package com.freenow.sample.api.requests.data;

import org.testng.annotations.DataProvider;

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
                {65556},
                {"@#$%%^"},
                {""},
                {"DennisdfghjDFGHJKDFGHJ"}
        };
    }

    @DataProvider(name = "valid-user-ids-provider")
    public static Object[][] getValidUsersIDsprovider() {
        return new Object[][]{
                {1},
                {2}
        };
    }

    @DataProvider(name = "invalid-user-ids-provider")
    public static Object[][] getInvalidUsersIDsprovider() {
        return new Object[][]{
                {234455},
                {"hfhgffg"},
                {"@#$"},
                {""}
        };
    }
}
