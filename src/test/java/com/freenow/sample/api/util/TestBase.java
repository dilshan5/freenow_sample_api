package com.freenow.sample.api.util;

import com.freenow.sample.api.common.LoggerUtil;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class TestBase {

    @BeforeClass
    public void initClass() {
        RequestUtil.setRequestBaseUrls();
        System.out.println("====== Start Running Test Class " + this.getClass().toString() + " =========");
    }

    @BeforeMethod
    public void initMethod(Method method) {
        LoggerUtil.logINFO("Running Test Method - " + method.getName());
        Test test = method.getAnnotation(Test.class);
        LoggerUtil.logINFO("Running Test Description - " + test.description());
    }

    @AfterClass
    public void cleanUp() {
        System.out.println("====== End Running Test Class " + this.getClass().toString() + " =========");
    }

}
