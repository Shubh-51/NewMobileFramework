package com.catalyst.mobile.config;

import java.io.IOException;

public class configReader {
    private static final org.apache.logging.log4j.Logger logger = org.apache.logging.log4j.LogManager.getLogger(configReader.class);
    private static final java.util.Properties properties = new java.util.Properties();

    static {
        try (java.io.InputStream input = configReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                logger.error("config.properties not found in classpath");
                throw new RuntimeException("config.properties not found in classpath");
            }
            properties.load(input);
            logger.info("Loaded config.properties successfully");
        } catch (IOException e) {
            logger.error("Could not read config.properties file", e);
            throw new RuntimeException("Could not read config.properties file: " + e.getMessage(), e);
        }
    }

    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            logger.error("Property {} not found in config.properties", key);
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
