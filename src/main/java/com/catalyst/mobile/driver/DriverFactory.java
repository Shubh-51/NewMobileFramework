package com.catalyst.mobile.driver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

import com.catalyst.mobile.config.configReader;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class DriverFactory {

    private static final org.apache.logging.log4j.Logger logger = org.apache.logging.log4j.LogManager.getLogger(DriverFactory.class);
    private static final ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();
    private static AppiumDriverLocalService service;

    public static void startServer() {
        String appiumJs = System.getProperty("appium.js.path", "C:\\Users\\shubh\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js");
        logger.info("Starting Appium server using {}", appiumJs);

        service = new AppiumServiceBuilder()
            .withAppiumJS(new File(appiumJs))
            .withIPAddress("127.0.0.1")
            .usingPort(4723)
            .build();

        service.start();
        logger.info("Appium Server Started → {}", service.getUrl());
    }

    public static void stopServer() {
        if (service != null && service.isRunning()) {
            service.stop();
            logger.info("Appium Server Stopped");
        }
    }

    public static void initializeDriver(String platform) throws MalformedURLException, URISyntaxException {
        logger.info("initializeDriver called with platform: {}", platform);
        if (platform == null || platform.isEmpty()) {
            throw new IllegalArgumentException("Platform must not be null or empty");
        }

        if (platform.equalsIgnoreCase("android")) {
            initializeAndroidDriver();
        } else if (platform.equalsIgnoreCase("ios")) {
            initializeIOSDriver();
        } else {
            throw new IllegalArgumentException("Unsupported platform: " + platform);
        }
    }

    // ANDROID DRIVER
    private static void initializeAndroidDriver() throws MalformedURLException, URISyntaxException {
        String ipAddress = System.getProperty("ipAddress", configReader.getIpAddress());

        UiAutomator2Options options = new UiAutomator2Options()
            .setDeviceName(configReader.getDeviceName())
            .setPlatformName("Android")
            .setApp(configReader.getAppAndroid());

        driver.set(new AndroidDriver(new URI(ipAddress).toURL(), options));
        logger.info("Android Driver Started on {}", ipAddress);
    }

    private static void initializeIOSDriver() throws MalformedURLException, URISyntaxException {
        String ipAddress = System.getProperty("ipAddress", configReader.getIpAddress());

        XCUITestOptions options = new XCUITestOptions()
            .setDeviceName("iPhone 13 Pro")
            .setPlatformName("iOS")
            .setPlatformVersion("15.5")
            .setApp(configReader.getAppIOS());

        driver.set(new IOSDriver(new URI(ipAddress).toURL(), options));
        logger.info("iOS Driver Started on {}", ipAddress);
    }

    // GET DRIVER
    public static AppiumDriver getDriver() {

        return driver.get();
    }

    // QUIT DRIVER
    public static void quitDriver() {

        if (driver.get() != null) {

            driver.get().quit();
            driver.remove();

            System.out.println("Driver Closed");
        }
    }
}