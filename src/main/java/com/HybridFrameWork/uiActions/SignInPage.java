package com.HybridFrameWork.uiActions;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.HybridFrameWork.helper.alertHelper.AlertHelper;
import com.HybridFrameWork.helper.assertionHelper.AssertionHelper;
import com.HybridFrameWork.helper.browserHelper.BrowserHelper;
import com.HybridFrameWork.helper.dropDownHelper.DropDownHelper;
import com.HybridFrameWork.helper.genericHelper.GenericHelper;
import com.HybridFrameWork.helper.javaScriptHelper.JavaScriptHelper;
import com.HybridFrameWork.helper.loggerHelper.LoggerHelper;
import com.HybridFrameWork.helper.propertiesHelper.PropertiesHelper;
import com.HybridFrameWork.helper.waitHelper.WaitHelper;
import com.HybridFrameWork.testBase.Config;
import com.HybridFrameWork.testBase.TestBase;

public class SignInPage {
	public static final Logger log = LoggerHelper.getLogger(SignInPage.class);

	WebDriver driver;

	AlertHelper alert;
	AssertionHelper assertion;
	BrowserHelper browser;
	DropDownHelper dropDown;
	GenericHelper genericHelp;
	JavaScriptHelper javaScript;
	PropertiesHelper properties;
	WaitHelper wait;
	Config config;
	TestBase testBase;


	/*@FindBy (id ="monthHeading")
	WebElement currentMonthName;

	@FindBy(xpath="//button[@type = 'button' and @class = 'next active']/span[1]")
	WebElement nextMonthsName;*/	


	public SignInPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

		alert = new AlertHelper(driver);
		assertion = new AssertionHelper();
		browser = new BrowserHelper(driver);
		dropDown = new DropDownHelper(driver);
		genericHelp = new GenericHelper();
		javaScript = new JavaScriptHelper(driver);
		properties = new PropertiesHelper(driver);
		wait = new WaitHelper(driver);
		testBase = new TestBase();
		config = new Config(testBase.OR);

		properties.loadHomePageProperties();
		wait.waitForElement(driver, 20 , properties.getWebElement("Signup_UserName"));
	}
}
