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

    private static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();
    private static AppiumDriverLocalService service;

    // START APPIUM SERVER
    public static void startServer() {

       service = new AppiumServiceBuilder()
        .withAppiumJS(new File("C:\\Users\\shubh\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
        .withIPAddress("127.0.0.1")
        .usingPort(4723)
        .build();

        service.start();

        System.out.println("Appium Server Started → " + service.getUrl());
    }

    // STOP APPIUM SERVER
    public static void stopServer() {

        if (service != null) {
            service.stop();
            System.out.println("Appium Server Stopped");
        }
    }

    // INITIALIZE DRIVER
    public static void initializeDriver(String platform) throws MalformedURLException, URISyntaxException {

        if (platform.equalsIgnoreCase("android")) {

            initializeAndroidDriver();

        } 
        else if (platform.equalsIgnoreCase("ios")) {

            initializeIOSDriver();
        }
    }

    // ANDROID DRIVER
    private static void initializeAndroidDriver() throws MalformedURLException, URISyntaxException {
         String ipAddress = System.getProperty("ipAddress") != null
		        ? System.getProperty("ipAddress")
		        : configReader.getIpAddress();

        UiAutomator2Options options = new UiAutomator2Options();

        options.setDeviceName(configReader.getDeviceName());
        options.setPlatformName("Android");
        options.setApp(configReader.getAppAndroid());

        driver.set(new AndroidDriver(new URI(ipAddress).toURL(), options));

        System.out.println("Android Driver Started");
    }

    // IOS DRIVER
    private static void initializeIOSDriver() {

        XCUITestOptions options = new XCUITestOptions();

        options.setDeviceName("iPhone 13 Pro");
        options.setPlatformName("iOS");
        options.setPlatformVersion("15.5");
        options.setApp(configReader.getAppIOS());

        driver.set(new IOSDriver(service.getUrl(), options));

        System.out.println("iOS Driver Started");
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