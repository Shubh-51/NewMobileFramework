package com.catalyst.mobile.pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class BasePage {

    protected AppiumDriver driver;
    protected org.apache.logging.log4j.Logger logger = org.apache.logging.log4j.LogManager.getLogger(BasePage.class);
    protected org.openqa.selenium.support.ui.WebDriverWait wait;

    public BasePage(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofMillis(200)), this);
    }

    public void scrollToText(String text) {
        logger.debug("scrollToText: {}", text);
        driver.findElement(AppiumBy.androidUIAutomator(
            "new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + text + "\"));"));
    }

    public void longPress(org.openqa.selenium.WebElement ele) {
        logger.debug("longPress");
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(ele));
        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
            "elementId", ((RemoteWebElement) ele).getId(),
            "duration", 2000
        ));
    }

    public void clickElement(org.openqa.selenium.WebElement element) {
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void sendKeys(org.openqa.selenium.WebElement element, String text) {
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(text);
    }
}
