package com.catalyst.mobile.basetest;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.time.Duration;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.catalyst.mobile.driver.DriverFactory;
import com.catalyst.mobile.config.configReader;

public class BaseTest {

    // @BeforeSuite
    // public void startAppiumServer() {
    //     DriverFactory.startServer();
    // }

    @BeforeMethod
    public void setup() throws MalformedURLException, URISyntaxException {

        // 1. Try from terminal
        String platform = System.getProperty("platform");

        // 2. Fallback to config.properties
        if (platform == null || platform.isEmpty()) {
            platform = configReader.getPlatform(); // create this method
        }

        // 3. Default fallback
        if (platform == null || platform.isEmpty()) {
            platform = "android";
        }

        System.out.println("Final Platform → " + platform);

        DriverFactory.initializeDriver(platform);

        int timeout = Integer.parseInt(configReader.getTime());

        DriverFactory.getDriver()
                .manage()
                .timeouts()
                .implicitlyWait(Duration.ofSeconds(timeout));
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    // @AfterSuite
    // public void stopServer() {
    //     DriverFactory.stopServer();
    // }
}