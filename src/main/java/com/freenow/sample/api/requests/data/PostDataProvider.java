package com.freenow.sample.api.requests.data;

import org.testng.annotations.DataProvider;

public class PostDataProvider {

    @DataProvider(name = "valid-post-data-provider")
    public static Object[][] getValidPostDataprovider() {
        return new Object[][]{
                {"1"},
                {"33"}
        };
    }

    @DataProvider(name = "invalid-post-data-provider")
    public static Object[][] getInvalidPostDataprovider() {
        return new Object[][]{
                {"@$%^"},
                {"65556"},
                {""},
                {"DennisdfghjDFGHJKDFGHJ"}
        };
    }
}
