package com.catalyst.mobile.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExtentReportGenerator {
    private static ExtentSparkReporter spark;
    private static ExtentReports report;
    private static final Object lock = new Object();
    private static boolean isReportInitialized = false;

    public static ExtentReports generateExtentReport() {
        synchronized (lock) {
            if (report == null) {
                String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
                String reportPath = "src/test/java/com/resources/Reports/BusyProject_Report_" + timestamp + ".html";

                spark = new ExtentSparkReporter(new File(reportPath));
                spark.config().setDocumentTitle("BusyProject Report");
                spark.config().setReportName("Mobile Automation Test Report");
                spark.config().setTheme(Theme.DARK);
                
                report = new ExtentReports();
                report.attachReporter(spark);
                report.setSystemInfo("DeviceName", "Android Emulator");
                report.setSystemInfo("Platform", "Android");
                report.setSystemInfo("Tester", "Automation");
                isReportInitialized = true;
            }
            return report;
        }
    }

    public static void flushReport() {
        synchronized (lock) {
            if (report != null && isReportInitialized) {
                try {
                    report.flush();
                    report = null;
                    spark = null;
                    isReportInitialized = false;
                } catch (Exception e) {
                    System.err.println("Error flushing ExtentReport: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }

    public static void closeReport() {
        synchronized (lock) {
            try {
                flushReport();
            } finally {
                report = null;
                spark = null;
                isReportInitialized = false;
            }
        }
    }
}
