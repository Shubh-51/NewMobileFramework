package com.catalyst.mobile.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class configReader {
    private static  Properties properties = new Properties();
    private static final String CONFIG_PATH = "D:\\NewMobileFramework\\BusyProject\\src\\test\\java\\com\\resources\\config.properties";
    
    static {
        try {
            properties = new Properties();
            FileInputStream fis = new FileInputStream(CONFIG_PATH);
            properties.load(fis);
            fis.close();
        } catch (IOException e) {
            throw new RuntimeException("Could not read config.properties file: " + e.getMessage());
        }
    }


    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Property " + key + " not found in config.properties");
        }
        return value;
    }

    public static String getIpAddress() {
        return getProperty("ipAddress"); 
    }

    public static String getDeviceName() { 
        return getProperty("deviceName"); 
    }

    public static String getPlatformAndroid() { 
        return getProperty("platformAndroid");
     
    }

    public static String getPlatformIOS() {
        return getProperty("platformIOS");
    }

    public static String getPlatform() {
        return getProperty("platform"); 
    }

    public static String getChromeDriverExecutable() {
        return getProperty("chromeDriverExecutable"); 
    }

    public static String getAppAndroid() {
        return getProperty("appAndroid"); 
    }

    public static String getAppIOS() {
        return getProperty("appIOS"); 
    }

    public static int getTime() {
        return Integer.parseInt(getProperty("time"));
    }
}
