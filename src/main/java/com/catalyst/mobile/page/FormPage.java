package com.catalyst.mobile.page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class FormPage extends BasePage {

   AppiumDriver driver;
	
	public FormPage(AppiumDriver driver) {
		super(driver);
		this.driver=driver;
	}
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
	private WebElement nameInput;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/radioFemale")
	private WebElement femaleRadioBtn;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/radioMale")
	private WebElement maleRadioBtn;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/spinnerCountry")
	private WebElement countryDropdown;
	
	@AndroidFindBy(xpath="//android.widget.Button[@resource-id='com.androidsample.generalstore:id/btnLetsShop']")
	private WebElement letsShopBtn;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text=\"Pump Controller\"]")
	private WebElement pumpControllerText;
	
	public void setNameInput(String name) {
		nameInput.sendKeys(name);
	}
	
	public void selectGendar(String gender) {
		if(gender.contains("female")) {
			femaleRadioBtn.click();
		}else {
			femaleRadioBtn.click();
		}
	}
	
	public void selectCountryFromDropdown(String countryName) {
		countryDropdown.click();
		scrollToText(countryName);
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\""+countryName+"\"]")).click();
	}
	
	public void submitForm() throws InterruptedException {
	 	letsShopBtn.click();
	 	Thread.sleep(2000);
	 //return new ProductCatalougePage(driver);
	 }
	
	public void startActivity() {
		((JavascriptExecutor)driver).executeScript("mobile: startActivity", ImmutableMap.of("intent",
				"com.androidsample.generalstore/com.androidsample.generalstore.SplashActivity"
				));	
	}
    
}
