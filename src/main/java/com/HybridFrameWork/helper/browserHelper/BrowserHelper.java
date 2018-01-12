package com.HybridFrameWork.helper.browserHelper;

import java.util.LinkedList;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.HybridFrameWork.helper.loggerHelper.LoggerHelper;


public class BrowserHelper {


	private WebDriver driver;
	public static final Logger log = LoggerHelper.getLogger(BrowserHelper.class);

	public BrowserHelper(WebDriver driver) {
		this.driver = driver;
		log.debug("BrowserHelper : " + this.driver.hashCode());
	}

	public void gotoPage(String url) {
		driver.get(url);
		log.info("Navigating to page: "+ url);
	}
	
	public void goBack() {
		driver.navigate().back();
		log.info("Navigating Backward from current page.");
	}

	public void goForward() {
		driver.navigate().forward();
		log.info("Navigating forward from current page.");
	}

	public void refresh() {
		driver.navigate().refresh();
		log.info("Refreshing the current page.");
	}

	public Set<String> getWindowHandlens() {
		log.info("");
		return driver.getWindowHandles();
	}

	public void SwitchToWindow(int index) {
		LinkedList<String> windowsId = new LinkedList<String>(getWindowHandlens());
		if (index < 0 || index > windowsId.size()){
			throw new IllegalArgumentException("Invalid Index : " + index);
		}
		driver.switchTo().window(windowsId.get(index));
		log.info("Switched to window:"+index);
	}

	
	public void switchToParentWindow() {
		LinkedList<String> windowsId = new LinkedList<String>(getWindowHandlens());
		driver.switchTo().window(windowsId.get(0));
		log.info("Moved to parent window.");
	}

	public void switchToParentWithChildClose() {
		LinkedList<String> windowsId = new LinkedList<String>(getWindowHandlens());
		for (int i = 1; i < windowsId.size(); i++) {
			log.info(windowsId.get(i));
			driver.switchTo().window(windowsId.get(i));
			driver.close();
		}
		switchToParentWindow();
		log.info("Switched to Parent window and closed all the child windows");
	}

	public void switchToFrame(String nameOrId) {
		driver.switchTo().frame(nameOrId);
		log.info("Switched to frame: "+ nameOrId);
	}

}
