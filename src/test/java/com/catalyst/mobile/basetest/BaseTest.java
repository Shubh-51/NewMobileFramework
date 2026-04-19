package com.catalyst.mobile.basetest;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.time.Duration;

import org.testng.annotations.AfterMethod;
//import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
//

import com.catalyst.mobile.driver.DriverFactory;
import com.catalyst.mobile.config.configReader;

public class BaseTest {

    private static final org.apache.logging.log4j.Logger logger = org.apache.logging.log4j.LogManager.getLogger(BaseTest.class);

    //  @BeforeSuite
    // public void beforeSuite() {
    //      logger.info("Starting Appium server before suite");
    //      DriverFactory.startServer();
    // }

    @BeforeMethod
    public void setup() throws MalformedURLException, URISyntaxException {
        String platform = System.getProperty("platform", configReader.getPlatform());
        if (platform == null || platform.isEmpty()) {
            platform = "android";
        }

        logger.info("Final Platform → {}", platform);

        DriverFactory.initializeDriver(platform);

        int timeout = configReader.getTime();
        DriverFactory.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    //  @AfterSuite
    //  public void afterSuite() {
    //      logger.info("Stopping Appium server after suite");
    //      DriverFactory.stopServer();
    // }
}