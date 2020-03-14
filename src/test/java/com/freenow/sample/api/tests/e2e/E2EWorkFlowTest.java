package com.freenow.sample.api.tests.e2e;

import com.freenow.sample.api.util.TestBase;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class E2EWorkFlowTest extends TestBase {

    private static SoftAssert softAssert;

    @BeforeClass
    public static void initiate(ITestContext iTestContext) {
        iTestContext.setAttribute("feature", "Email Verification - Comments");
        softAssert = new SoftAssert();
    }

    @Test(description = "ID-005")
    public static void testEmailVerificationInComments() {

    }

}


