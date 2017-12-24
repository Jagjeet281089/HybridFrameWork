package com.HybridFrameWork.helper.alertHelper;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

import com.HybridFrameWork.helper.loggerHelper.LoggerHelper;



public class AlertHelper {
	
	private WebDriver driver;
	public static final Logger log = LoggerHelper.getLogger(AlertHelper.class);
	
	public AlertHelper(WebDriver driver){
		this.driver = driver;
		log.debug("AlertHelper : " + this.driver.hashCode());
	} 
	
	//Switches to generated alert.
	public Alert getAlert(){
		log.debug("Switching to alert");
		return driver.switchTo().alert();
	}
	
	//accepts the generated alert.
	public void AcceptAlert() {
		log.info("");
		getAlert().accept();
	}
	
	//Dismisses the generated alert.
	public void DismissAlert() {
		log.info("");
		getAlert().dismiss();
	}

	//Returns the text of generated alert.
	public String getAlertText() {
		String text = getAlert().getText();
		log.info("Alert Text is :"+text);
		return text;
	}

	//Checks If alert is present and switches to it, also returns True. If not returns False.
	public boolean isAlertPresent() {
		try {
			getAlert();
			log.info("Alert is pesent.");
			return true;
		} catch (NoAlertPresentException e) {
			// Do nothing.
			log.info("Alert is not pesent.");
			return false;
		}
	}

	//Accepts the alert, If alert is present.
	public void AcceptAlertIfPresent() {
		if (!isAlertPresent())
		return;
		AcceptAlert();
		log.info("Alert accepted.");
	}

	//Dismisses the alert, If alert is present.
	public void DismissAlertIfPresent() {
		if (!isAlertPresent())
		return;
		DismissAlert();
		log.info("Alert Dismissed.");
	}
	
	
	public void AcceptPrompt(String text) {
		if (!isAlertPresent())
		return;
		Alert alert = getAlert();
		alert.sendKeys(text);
		alert.accept();
		log.info(text);
	}
	
}
