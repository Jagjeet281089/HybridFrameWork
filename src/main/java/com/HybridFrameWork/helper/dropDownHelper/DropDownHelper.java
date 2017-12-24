package com.HybridFrameWork.helper.dropDownHelper;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.HybridFrameWork.helper.loggerHelper.LoggerHelper;


public class DropDownHelper {
	

	private WebDriver driver;
	public static final Logger log = LoggerHelper.getLogger(DropDownHelper.class);

	public DropDownHelper(WebDriver driver) {
		this.driver = driver;
		log.debug("DropDownHelper : " + this.driver.hashCode());
	}

	
	public void SelectUsingVisibleValue(WebElement element,String visibleValue) {
		Select select = new Select(element);
		select.selectByVisibleText(visibleValue);
		log.info("Locator : " + element + " Value : " + visibleValue);
	}

	public String getSelectedValue(WebElement element) {
		String value = new Select(element).getFirstSelectedOption().getText();
		log.info("WebELement : " + element + " Value : "+ value);
		return value;
	}
	
	public void SelectUsingIndex(WebElement element,int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
		log.info("Locator : " + element + " Value : " + index);
	}
	
	public void SelectUsingVisibleText(WebElement element,String text) {
		Select select = new Select(element);
		select.selectByVisibleText(text);
		log.info("Locator : " + element + " Value : " + text);
	}
	
	public List<String> getAllDropDownValues(WebElement locator) {
		Select select = new Select(locator);
		List<WebElement> elementList = select.getOptions();
		List<String> valueList = new LinkedList<String>();
		
		for (WebElement element : elementList) {
			log.info(element.getText());
			valueList.add(element.getText());
		}
		return valueList;
	}
	
}
