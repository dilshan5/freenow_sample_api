package com.freenow.sample.api.util;


import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class TestBase {

    @BeforeClass
    public void init() {
        System.out.println("====== Start Running " + this.getClass().toString() + " =========");
    }

    @AfterClass
    public void tear() {
        System.out.println("====== End Running " + this.getClass().toString() + " =========");
    }

}
