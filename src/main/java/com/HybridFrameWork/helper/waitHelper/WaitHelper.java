package com.HybridFrameWork.helper.waitHelper;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.HybridFrameWork.helper.loggerHelper.LoggerHelper;

public class WaitHelper {

	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(WaitHelper.class);


	public WaitHelper(WebDriver driver) {
		this.driver = driver;
		log.debug("WaitHelper : " + this.driver.hashCode());
	}

	public void setImplicitWait(long timeout, TimeUnit unit) {
		log.info(timeout);
		//if time unit is not provided, it will consider as Seconds.
		driver.manage().timeouts().implicitlyWait(timeout, unit == null ? TimeUnit.SECONDS : unit);
	}

	public void setPageLoadTimeout(long timeout, TimeUnit unit) {
		log.info(timeout);
		driver.manage().timeouts().pageLoadTimeout(timeout, unit == null ? TimeUnit.SECONDS : unit);
	}

	private WebDriverWait getWait(int timeOutInSeconds, int pollingEveryInMiliSec) {
		log.debug("");
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.pollingEvery(pollingEveryInMiliSec, TimeUnit.MILLISECONDS);
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(ElementNotVisibleException.class);
		wait.ignoring(StaleElementReferenceException.class);
		wait.ignoring(NoSuchFrameException.class);
		return wait;
	}

	public void waitForElementVisible(WebElement locator, int timeOutInSeconds, int pollingEveryInMiliSec) {
		log.info(locator);
		WebDriverWait wait = getWait(timeOutInSeconds, pollingEveryInMiliSec);
		wait.until(ExpectedConditions.visibilityOf(locator));
	}

	public void waitForElement(WebDriver driver, WebElement element, long timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.visibilityOf(element));
		log.info("element found..."+element.getText());
	}

	public WebElement waitForElement(WebDriver driver,long time,WebElement element){
		WebDriverWait wait = new WebDriverWait(driver, time);
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}


	
	/////Later added.
	//There is no polling value, It continuously look for the element.
	public WebElement waitForElement(WebDriver driver, Long time, WebElement element){
		WebDriverWait wait = new WebDriverWait(driver, time);
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	//There is no polling value, It wait for the given amount of time for performing poling cycle. Also It will keep on ignoring the NoSuchElementException. 
	public WebElement waitForElementWithPollingInterval(WebDriver driver, Long time, WebElement element, Long pollingTime){
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.pollingEvery(pollingTime, TimeUnit.SECONDS);
		wait.ignoring(NoSuchElementException.class);
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void impliciteWait(long time){
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}





}
