package com.automation.framework.tests;

import com.automation.framework.config.FrameworkConfig;
import com.automation.framework.core.DriverFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public abstract class BaseTest {

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        DriverFactory.initializeDriver();
        DriverFactory.getDriver().get(FrameworkConfig.getInstance().getBaseUrl());
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
