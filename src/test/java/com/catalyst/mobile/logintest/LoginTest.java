package com.catalyst.mobile.logintest;

import java.util.logging.Logger;

import org.testng.annotations.Test;

import com.catalyst.mobile.basetest.BaseTest;
import com.catalyst.mobile.driver.DriverFactory;
import com.catalyst.mobile.pages.FormPage;

public class LoginTest extends BaseTest {

	private static final Logger logger = Logger.getLogger(LoginTest.class.getName());
    @Test
    public void formTest() {
		logger.info("Starting form test");
        FormPage form = new FormPage(DriverFactory.getDriver());
		logger.info("Filling form details");		
        form.setNameInput("Swat");
        form.selectGender("f");
		logger.info("Selecting country from dropdown");
        form.selectCountryFromDropdown("Australia");
        form.submitForm();
		logger.info("Form submitted successfully");	

       // org.testng.Assert.assertTrue(form.isPumpControllerVisible(), "Pump Controller should be visible after submit");
    } 
}
