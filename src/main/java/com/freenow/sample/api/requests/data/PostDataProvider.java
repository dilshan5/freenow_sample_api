package com.freenow.sample.api.requests.data;

import org.testng.annotations.DataProvider;

public class PostDataProvider {

    @DataProvider(name = "valid-post-ids-provider")
    public static Object[][] getValidPostDataprovider() {
        return new Object[][]{
                {1},
                {21}
        };
    }

    @DataProvider(name = "invalid-post-ids-provider")
    public static Object[][] getInvalidPostDataprovider() {
        return new Object[][]{
                {"@$%^"},
                {"65556"},
                {""},
                {"DennisdfghjDFGHJKDFGHJ"}
        };
    }
}