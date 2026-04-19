package com.catalyst.mobile.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class FormPage extends BasePage {

    public FormPage(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
    private WebElement nameInput;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/radioFemale")
    private WebElement femaleRadioBtn;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/radioMale")
    private WebElement maleRadioBtn;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/spinnerCountry")
    private WebElement countryDropdown;

    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id='com.androidsample.generalstore:id/btnLetsShop']")
    private WebElement letsShopBtn;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Pump Controller\"]")
    private WebElement pumpControllerText;

    public void setNameInput(String name) {
        sendKeys(nameInput, name);
    }

    public void selectGender(String gender) {
        if ("female".equalsIgnoreCase(gender)) {
            clickElement(femaleRadioBtn);
        } else if ("male".equalsIgnoreCase(gender)) {
            clickElement(maleRadioBtn);
        } else {
            throw new IllegalArgumentException("Invalid gender: " + gender);
        }
    }

    public void selectCountryFromDropdown(String countryName) {
        clickElement(countryDropdown);
        scrollToText(countryName);
        WebElement countryOption = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"" + countryName + "\"]"));
        clickElement(countryOption);
    }

    public void submitForm() {
        clickElement(letsShopBtn);
    }

	public void resetApp() {
		((JavascriptExecutor)driver).executeScript("mobile: startActivity", ImmutableMap.of("intent",
				"com.androidsample.generalstore/com.androidsample.generalstore.SplashActivity"
				));	
	}
    
}
