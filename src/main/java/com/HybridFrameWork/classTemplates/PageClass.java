package com.HybridFrameWork.classTemplates;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.test.automation.uiAutomation.uiActions.ProductDetailsPage;

public class PageClass {

	
	@FindBy(xpath="//button[@class= 'btn btn-default btn-facebook']")
	WebElement facebookButton;
	
	@FindBy (id="content")
	WebElement facebookPopupMainText;
	
	public static final Logger log = Logger.getLogger(ProductDetailsPage.class.getName());
	WebDriver driver;
	
	public PageClass(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this); // This is a construct that will initialize all the webelements defined above under page factory.
	}
	
	public void log(String data) {
		log.info(data);
		Reporter.log(data);
	}
	
}
