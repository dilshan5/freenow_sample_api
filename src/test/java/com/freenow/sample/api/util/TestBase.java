package com.freenow.sample.api.util;

import com.freenow.sample.api.common.Constant;
import com.freenow.sample.api.common.LoggerUtil;
import org.testng.annotations.*;

import java.lang.reflect.Method;

/**
 * Base class for all the Test classes
 */
public class TestBase {

    @BeforeClass(groups = {Constant.BVT, Constant.REGRESSION})
    public void initClass() {
        RequestUtil.setRequestBaseUrls();
        System.out.println("====== Start Running Test Class " + this.getClass().toString() + " ======");
    }

    @BeforeMethod
    public void initMethod(Method method) {
        LoggerUtil.logINFO("Running Test Method - " + method.getName());
        Test test = method.getAnnotation(Test.class);
        LoggerUtil.logINFO("Running Test Description - " + test.description());
    }

    @AfterClass
    public void cleanUp() {
        System.out.println("====== End Running Test Class " + this.getClass().toString() + " ======");
    }

}
