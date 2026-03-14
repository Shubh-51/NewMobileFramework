package com.catalyst.mobile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class configReader {
     private static Properties properties;
    private static final String CONFIG_PATH = "src/test/resources/properties/config.properties";

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
	  
	  public static String getChromeDriverExecutable() {
	        return getProperty("chromeDriverExecutable");
	    }
	  
	  public static String getAppAndroid() {
	        return getProperty("appAndroid");
	    }
	  
	  public static String getTime() {
	        return getProperty("time");
	    }
}
