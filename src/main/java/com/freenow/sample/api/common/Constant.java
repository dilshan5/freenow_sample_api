package com.freenow.sample.api.common;

/**
 * Define all the constant variables in the project
 */
public class Constant {/**/

    public static final String TEST_ENV = System.getProperty("tests.env", "QE");
    public static final String TEST_RELEASE = System.getProperty("tests.release", "Regression");
    public static final String TEST_PROJECT = System.getProperty("tests.project", "FreeNow");
    public static final String RESPONSE_MESSAGE_PATTERN = "\\D*\\s\\d{3}\\s";
    public static final String EMAIL_PATTERN = "^[^\\_\\W][\\w]+(\\.[\\w]+)*@([\\w-]+(\\.[a-zA-Z0-9]+)*\\.[a-z]{2,6})$";

    Constant() {

    }


}
