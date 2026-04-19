package com.catalyst.mobile.utils;

import java.io.IOException;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.OutputType;
import org.apache.commons.io.FileUtils;

import io.appium.java_client.AppiumDriver;

public class ScreenshotCapture {

    // ✅ Your desired folder (dynamic path)
    private static final String SCREENSHOT_DIR =
            System.getProperty("user.dir") + "/src/test/java/com/resources/Screenshot/";

    public static String getScreenshotPath(AppiumDriver driver, String testCaseName) throws IOException {

        // ✅ Create folder if not exists
        File dir = new File(SCREENSHOT_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // Generate file name
        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS"));

        String threadId = String.valueOf(Thread.currentThread().getId());

        String fileName = testCaseName + "_" + timestamp + "_thread" + threadId + ".png";

        File source = driver.getScreenshotAs(OutputType.FILE);

        String destinationPath = SCREENSHOT_DIR + fileName;

        FileUtils.copyFile(source, new File(destinationPath));

        return destinationPath;   // ✅ absolute path
    }
}