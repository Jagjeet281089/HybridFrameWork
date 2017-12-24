package com.HybridFrameWork.helper.genericHelper;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.HybridFrameWork.helper.loggerHelper.LoggerHelper;

public class GenericHelper {



	public static final Logger log = LoggerHelper.getLogger(GenericHelper.class);

	
	//Returns true if the web element is displayed.
	public boolean isDisplayed(WebElement element) {
		try {
			element.isDisplayed();
			log.info("Element "+element+"is displayed.");
			return true;
		} catch (Exception e) {
			log.info(e);
			Reporter.log(e.fillInStackTrace().toString());
			return false;
		}
	}

	//Returns true if the web element is not displayed.
	protected boolean isNotDisplayed(WebElement element) {
		try {
			element.isDisplayed();
			log.info("element is displayed.."+element);
			return false;
		} catch (Exception e) {
			log.error(e);
			Reporter.log(e.fillInStackTrace().toString());
			return true;
		}
	}
	
	
	
	public String readValueFromElement(WebElement element) {
		
		//Returns Null if webelement is null.
		if (null == element){
			log.info("Weblement "+element+"is null");
			return null;
		}

		//initializes a variable as false and if element is displayed it will be become true.
		boolean displayed = false;
		try {
			displayed = isDisplayed(element);
		} catch (Exception e) {
			log.error(e);
			Reporter.log(e.fillInStackTrace().toString());
			return null;
		}

		//If element is not displayed, it will 
		if (!displayed){
			return null;
		}
		String text = element.getText();
		log.info("weblement valus is.."+text);
		return text;
	}

	
	
	public String readValueFromInput(WebElement element) {
		if (null == element){
			return null;
		}
		if (!isDisplayed(element)){
			return null;
		}
		String value = element.getAttribute("value");
		log.info("weblement valus is.."+value);
		return value;
	}

	
	protected String getDisplayText(WebElement element) {
		if (null == element){
			return null;
		}
		if (!isDisplayed(element)){
			return null;
		}
		return element.getText();
	}


	public static synchronized String getElementText( WebElement element) {
		if (null == element) {
			log.info("weblement is null");
			return null;
		}
		String elementText = null;
		try {
			elementText = element.getText();
		} catch (Exception ex) {
			log.info("Element not found " + ex);
			Reporter.log(ex.fillInStackTrace().toString());
		}
		return elementText;
	}

}
