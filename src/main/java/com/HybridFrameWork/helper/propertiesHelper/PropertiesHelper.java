package com.HybridFrameWork.helper.propertiesHelper;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.HybridFrameWork.helper.loggerHelper.LoggerHelper;

public class PropertiesHelper {

	private WebDriver driver;
	public Properties OR;
	public File F1;
	public FileInputStream File;


	public static final Logger log = LoggerHelper.getLogger(PropertiesHelper.class);

	public PropertiesHelper(WebDriver driver) {
		this.driver = driver;
		log.debug("PropertiesHelper : " + this.driver.hashCode());
	}


	public void loadHomePageProperties(){
		OR = new Properties();
		F1 = new File(System.getProperty("user.dir") + "/src/main/java/com/HybridFrameWork/properties/HomePage.properties");
		try {
			File = new FileInputStream(F1);
			OR.load(File);
			log.info(">>>>>> HomePage.properties file loaded. <<<<<<");
		} catch (Exception e) {
			log.info(">>>>>>Unable to Load HomePage.properties file.<<<<<<");
			e.printStackTrace();
		}
	}

	public void loadSignUpPageProperties(){
		OR = new Properties();
		F1 = new File(System.getProperty("user.dir") + "/src/main/java/com/HybridFrameWork/properties/SignupAndSignIn.properties");
		try {
			File = new FileInputStream(F1);
			OR.load(File);
			log.info(">>>>>> SignupPage.properties file loaded. <<<<<<");
		} catch (Exception e) {
			log.info(">>>>>>Unable to Load SignupPage.properties file.<<<<<<");
			e.printStackTrace();
		}
	}

	//========//========//========LOCATOR FUNCTIONS//========//========//========//========//========//

	public WebElement getLocator(String locator) {
		try {
			//System.out.println(locator);
			String[] split = locator.split(":");
			String locatorType = split[0];
			String locatorValue = split[1];
			//System.out.println("locatorType:-"+locatorType);
			//System.out.println("locatorValue:-"+locatorValue);
			if (locatorType.toLowerCase().equals("id"))
				return driver.findElement(By.id(locatorValue));
			else if (locatorType.toLowerCase().equals("name"))
				return driver.findElement(By.name(locatorValue));
			else if ((locatorType.toLowerCase().equals("classname"))|| (locatorType.toLowerCase().equals("class")))
				return driver.findElement(By.className(locatorValue));
			else if ((locatorType.toLowerCase().equals("tagname"))
					|| (locatorType.toLowerCase().equals("tag")))
				return driver.findElement(By.className(locatorValue));
			else if ((locatorType.toLowerCase().equals("linktext"))
					|| (locatorType.toLowerCase().equals("link")))
				return driver.findElement(By.linkText(locatorValue));
			else if (locatorType.toLowerCase().equals("partiallinktext"))
				return driver.findElement(By.partialLinkText(locatorValue));
			else if ((locatorType.toLowerCase().equals("cssselector"))
					|| (locatorType.toLowerCase().equals("css")))
				return driver.findElement(By.cssSelector(locatorValue));
			else if (locatorType.toLowerCase().equals("xpath"))
				return driver.findElement(By.xpath(locatorValue));
			else
				throw new Exception("Unknown locator type '" + locatorType + "'");
		} catch (Exception e) {
			log.info("Unable to get element "+locator+" from the Property file.");
			e.printStackTrace();
			return null;
		}
	}


	public  List<WebElement> getLocators(String locator) {
		try {
			String[] split = locator.split(":");
			String locatorType = split[0];
			String locatorValue = split[1];
			//System.out.println("locatorType:-"+locatorType);
			//System.out.println("locatorValue:-"+locatorValue);

			if (locatorType.toLowerCase().equals("id"))
				return driver.findElements(By.id(locatorValue));
			else if (locatorType.toLowerCase().equals("name"))
				return driver.findElements(By.name(locatorValue));
			else if ((locatorType.toLowerCase().equals("classname"))
					|| (locatorType.toLowerCase().equals("class")))
				return driver.findElements(By.className(locatorValue));
			else if ((locatorType.toLowerCase().equals("tagname"))
					|| (locatorType.toLowerCase().equals("tag")))
				return driver.findElements(By.className(locatorValue));
			else if ((locatorType.toLowerCase().equals("linktext"))
					|| (locatorType.toLowerCase().equals("link")))
				return driver.findElements(By.linkText(locatorValue));
			else if (locatorType.toLowerCase().equals("partiallinktext"))
				return driver.findElements(By.partialLinkText(locatorValue));
			else if ((locatorType.toLowerCase().equals("cssselector"))
					|| (locatorType.toLowerCase().equals("css")))
				return driver.findElements(By.cssSelector(locatorValue));
			else if (locatorType.toLowerCase().equals("xpath"))
				return driver.findElements(By.xpath(locatorValue));
			else
				throw new Exception("Unknown locator type '" + locatorType + "'");
		} catch (Exception e) {
			
			log.info("Unable to get elements with "+locator+" from the Property file.");
			e.printStackTrace();
			return null;
		}
	}


	public WebElement getWebElement(String locator) {
		return getLocator(OR.getProperty(locator));
	}

	public List<WebElement> getWebElements(String locator) {
		return getLocators(OR.getProperty(locator));
	}


}
