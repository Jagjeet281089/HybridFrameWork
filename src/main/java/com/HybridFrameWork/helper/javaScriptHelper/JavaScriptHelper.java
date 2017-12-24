package com.HybridFrameWork.helper.javaScriptHelper;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.HybridFrameWork.helper.loggerHelper.LoggerHelper;

public class JavaScriptHelper {
	
	private WebDriver driver;

	public static final Logger log = LoggerHelper.getLogger(JavaScriptHelper.class);

	public JavaScriptHelper(WebDriver driver) {
		this.driver = driver;
		log.debug("JavaScriptHelper : " + this.driver.hashCode());
	}

	public Object executeScript(String script) {
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		log.info(script);
		return exe.executeScript(script);
	}

	public Object executeScript(String script, Object... args) {
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		log.info(script);
		return exe.executeScript(script, args);
	}

	public void scrollToElemet(WebElement element) {
		executeScript("window.scrollTo(arguments[0],arguments[1])", element.getLocation().x, element.getLocation().y);
		log.info("scrolled To Element: "+element);
	}

	public void scrollToElemetAndClick(WebElement element) {
		scrollToElemet(element);
		element.click();
		log.info("scrolled To Element and Clicked: "+element);
	}

	public void scrollIntoView(WebElement element) {
		executeScript("arguments[0].scrollIntoView()", element);
		log.info("scrolled Element to view: "+element);
	}

	public void scrollIntoViewAndClick(WebElement element) {
		scrollIntoView(element);
		element.click();
		log.info("scrolled Element to view and clicked: "+element);
	}

	public void scrollDownVertically() {
		executeScript("window.scrollTo(0, document.body.scrollHeight)");
		log.info("scrolled to end of the page.");
	}

	public void scrollUpVertically() {
		executeScript("window.scrollTo(0, -document.body.scrollHeight)");
		log.info("scrolled to top of the page.");
	}

	public void scrollDownByPixel(int pixelNumber) {
		executeScript("window.scrollBy(0,pixelNumber)");
		log.info("scrolled "+pixelNumber+" pixel down on the page.");
	}

	public void scrollUpByPixel(int pixelNumber) {
		executeScript("window.scrollBy(0,pixelNumber)");
		log.info("scrolled "+pixelNumber+" pixel up on the page.");
	}

	public void ZoomInBypercentage() {
		executeScript("document.body.style.zoom='40%'");
	}

	public void ZoomBy100percentage() {
		executeScript("document.body.style.zoom='100%'");
	}

	
	
}
