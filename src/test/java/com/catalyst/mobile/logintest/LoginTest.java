package com.catalyst.mobile.logintest;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.catalyst.mobile.basetest.BaseTest;
import com.catalyst.mobile.driver.DriverFactory;
import com.catalyst.mobile.page.FormPage;

import io.appium.java_client.AppiumBy;

public class LoginTest extends BaseTest {

    @Test
	public void formTest() throws InterruptedException {
        FormPage form = new FormPage(DriverFactory.getDriver());
		form.setNameInput("Swat");
		form.selectGendar("female");
		form.selectCountryFromDropdown("Australia");
		//DriverFactory.getDriver().findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		Thread.sleep(3000);
		form.submitForm();
		
		//How to handle Toast Msges in Phone
		//Every Toast msges are developed by using tag //android.widget.Toast
		//String name=DriverFactory.getDriver().findElement(AppiumBy.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
		//Assert.assertEquals(name, "Please enter your name");

	}

    
}
