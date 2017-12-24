package com.HybridFrameWork.helper.assertionHelper;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

import com.HybridFrameWork.helper.loggerHelper.LoggerHelper;


public class AssertionHelper {
	
	public static final Logger log = LoggerHelper.getLogger(AssertionHelper.class);

	//Verifies if element is present on the page.
	public static synchronized boolean verifyElementPresent(WebElement element) {
		boolean isDispalyed = false;
		try {
			isDispalyed= element.isDisplayed();
			log.info(element.getText()+" is dispalyed");
		}
		catch(Exception ex) {
			log.error("Element not found " + ex);
		}
		return isDispalyed;
	}

	//Verifies if element is not present on the page.
	public static synchronized boolean verifyElementNotPresent( WebElement element) {
		boolean isDispalyed = false;
		try {
			element.isDisplayed();
			log.info(element.getText()+" is dispalyed");
		}
		catch(Exception ex) {
			log.error("Element not found " + ex);
			isDispalyed = true;
		}

		return isDispalyed;
	}

	//Verifies correct element text is present.
	public static synchronized boolean verifyElementTextEquals(WebElement element,String expectedText) {
		boolean flag = false;
		try {
			String actualText=element.getText();

			if(verifyElementPresent(element)==true && actualText.equals(expectedText)){
				log.info("Text matches. ActualText is :"+actualText+" expected text is: "+expectedText);
				return flag=true;
			}
			else {
				log.error("Text does not match. actualText is :"+actualText+" expected text is: "+expectedText);
				return flag;
			}

		}
		catch(Exception ex) {
			log.info("Error occured while text matching" + ex);
			log.error("actualText is :"+element.getText()+" expected text is: "+expectedText);
			return flag;
		}
	}


}
