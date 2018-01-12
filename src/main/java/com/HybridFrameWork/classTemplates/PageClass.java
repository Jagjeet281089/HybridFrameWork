package com.HybridFrameWork.classTemplates;

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

public class PageClass {
	
	public static final Logger log = LoggerHelper.getLogger(PageClass.class);
	
	WebDriver driver;
	AlertHelper alertHelper;
	AssertionHelper assertionHelper;
	BrowserHelper browserHelper;
	DropDownHelper dropDownHelper;
	GenericHelper genericHelper;
	JavaScriptHelper javaScriptHelper;
	PropertiesHelper propertiesHelper;
	WaitHelper waitHelper;
	
	public PageClass(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this); // This is a construct that will initialize all the webelements defined above under page factory.	
		
		alertHelper = new AlertHelper(driver);
		assertionHelper = new AssertionHelper();
		browserHelper = new BrowserHelper(driver);
		dropDownHelper = new DropDownHelper(driver);
		genericHelper = new GenericHelper();
		javaScriptHelper = new JavaScriptHelper(driver);
		propertiesHelper = new PropertiesHelper(driver);
		
		try {
			propertiesHelper.loadSignUpPageProperties();
		} catch (Exception e) {
			log.info("Unable to locate SignUpPage Property file.");
			e.printStackTrace();
		}
	}
}
