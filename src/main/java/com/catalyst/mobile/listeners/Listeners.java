package com.catalyst.mobile.listeners;

import java.util.Base64;
import java.util.logging.Logger;
import java.io.File;
import java.nio.file.Files;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.catalyst.mobile.driver.DriverFactory;
import com.catalyst.mobile.utils.ExtentReportGenerator;
import com.catalyst.mobile.utils.ScreenshotCapture;
import io.appium.java_client.AppiumDriver;




public class Listeners implements ITestListener {
	private static final Logger logger = Logger.getLogger(Listeners.class.getName());
    AppiumDriver driver;
	ExtentTest test;
	ExtentReports report=ExtentReportGenerator.generateExtentReport();
	
    @Override
	public void onTestStart(ITestResult result) {
		test=report.createTest(result.getMethod().getMethodName());
		test.log(Status.INFO, "Test is started");
		logger.info("Test started: " + result.getMethod().getMethodName());	
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Test Is PASS");
		logger	.info("Test passed: " + result.getMethod().getMethodName());
	}

	@Override
public void onTestFailure(ITestResult result) {
    test.fail(result.getThrowable());
    test.log(Status.FAIL, "Test Is FAIL");

    try {
        driver = DriverFactory.getDriver();
    } catch (Exception e) {
        logger.warning("Unable to fetch driver: " + e.getMessage());
    }

    if (driver != null) {
        try {
            // ✅ 1. Take screenshot ONLY ONCE (file)
            String path = ScreenshotCapture.getScreenshotPath(
                    driver,
                    result.getMethod().getMethodName()
            );

            // ✅ 2. Attach file (relative path for report)
            String relativePath = path.replace(System.getProperty("user.dir") + "\\", "")
                                      .replace("\\", "/");

            test.addScreenCaptureFromPath(relativePath, "Saved Screenshot");

            // ✅ 3. Convert SAME file → Base64 (NO second capture)
            byte[] fileContent = Files.readAllBytes(new File(path).toPath());
            String base64 = Base64.getEncoder().encodeToString(fileContent);

            test.addScreenCaptureFromBase64String(base64, "Inline Screenshot");

        } catch (Exception e) {
            logger.warning("Screenshot capture failed: " + e.getMessage());
        }
    } else {
        logger.warning("Driver is null → screenshot skipped");
    }
}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.log(Status.SKIP, "Test Is SKIP");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// Optional: implement if needed
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// Optional: implement if needed
	}

	@Override
	public void onStart(ITestContext context) {
		// Optional: implement if needed
	}

	@Override
	public void onFinish(ITestContext context) {
		try {
			ExtentReportGenerator.flushReport();
		} catch (Exception e) {
			logger.warning("Error flushing ExtentReport: " + e.getMessage());
		}
	}   
}
