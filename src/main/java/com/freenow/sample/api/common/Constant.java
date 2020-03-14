package com.freenow.sample.api.common;

public class Constant {

    public static final String TEST_ENV = System.getProperty("tests.env", "QE");
    public static final String TEST_RELEASE = System.getProperty("tests.release", "Regression");
    public static final String TEST_PROJECT = System.getProperty("tests.project", "FreeNow");

    Constant() {

    }


}
