package com.HybridFrameWork.testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;



public class TestBase {

	public WebDriver driver;
	public Properties OR;
	public File F1;
	public FileInputStream File;
	
	
	public static ExtentReports extent;
	public static ExtentTest test;
	public ITestResult result;
	

//========//========//========EXTENT REPORT FUNCTIONS//========//========//========//========//========//
	
	
	//Initialize the extent test reports, before any test starts.
	
	static {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		//False means report will not be overwritten and will be retained.
		extent = new ExtentReports(System.getProperty("user.dir") + "/src/main/java/com/HybridFrameWork/reports/test" + formater.format(calendar.getTime()) + ".html", false);
	}	
	
	//Perform different operations on the extent report as per the provided test status.
	public void getresult(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(LogStatus.PASS, result.getName() + " test is pass");
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(LogStatus.SKIP, result.getName() + " test is skipped and skip reason is:-" + result.getThrowable());
		} else if (result.getStatus() == ITestResult.FAILURE) {
			test.log(LogStatus.FAIL, result.getName() + " test is failed" + result.getThrowable());
			String screen = getScreenShot("");
			test.log(LogStatus.FAIL, test.addScreenCapture(screen));
		} else if (result.getStatus() == ITestResult.STARTED) {
			test.log(LogStatus.INFO, result.getName() + " test is started");
		}
	}
	
//========//========//========GLOBAL FUNCTIONS//========//========//========//========//========//
	
	//Before every test, Get the test name which is going to be started.
	@BeforeMethod()
	public void beforeMethod(Method result) {
		test = extent.startTest(result.getName());
		test.log(LogStatus.INFO, result.getName() + " test Started");
	}
	
	//After every test, pass the test result to the extent result processing function.
	@AfterMethod()
	public void afterMethod(ITestResult result) throws IOException {
		getresult(result);
	}

	//After all the tests, perform following
	@AfterClass(alwaysRun = true)
	public void endTest() {
		driver.quit();
		extent.endTest(test);
		extent.flush();
	}
		
//========//========//========Initializations FUNCTIONS//========//========//========//========//========//

	
	//Initialize the test
	public void init() throws IOException{
		selectBrowser("chrome");
		getURL("https://custapp.staticso2.com/PlusAccount2?name=Test%20Customer%20Name&email=Jagjeetsinghenuke@hotmail.com&skip=1");

	}
	
	//Selects Browser
	public void selectBrowser(String browser){
		if(browser.equalsIgnoreCase("firefox")){
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/drivers/geckodriver.exe");
			driver = new FirefoxDriver();	
		}
		else if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}
	}

	//Navigates to URL
	public void getURL(String url) {
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}


	
//========//========//========PROPERTY FILE FUNCTIONS//========//========//========//========//========//

	public void loadPropertiesFiles()throws IOException{
		OR = new Properties();
		
		F1 = new File(System.getProperty("user.dir") + "/src/main/java/com/HybridFrameWork/config/config.properties");
		File = new FileInputStream(F1);
		OR.load(File);
		
		F1 = new File(System.getProperty("user.dir") + "/src/main/java/com/HybridFrameWork/config/or.properties");
		File = new FileInputStream(F1);
		OR.load(File);
		
		F1 = new File(System.getProperty("user.dir") + "/src/main/java/com/HybridFrameWork/properties/HomePage.properties");
		File = new FileInputStream(F1);
		OR.load(File);
		
	}
	
	public void getPropertiesData() {
		
	}
	
	
//========//========//========LOCATOR FUNCTIONS//========//========//========//========//========//
	
	public WebElement getLocator(String locator) throws Exception {
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
	}
	
	
	
	public  List<WebElement> getLocators(String locator) throws Exception {
        String[] split = locator.split(":");
		String locatorType = split[0];
		String locatorValue = split[1];
		System.out.println("locatorType:-"+locatorType);
		System.out.println("locatorValue:-"+locatorValue);

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
	}
	
	
	public WebElement getWebElement(String locator) throws Exception{
		return getLocator(OR.getProperty(locator));
	}
	
	public List<WebElement> getWebElements(String locator) throws Exception{
		return getLocators(OR.getProperty(locator));
	}
	

//========//========//========UTILITY FUNCTIONS//========//========//========//========//========//
	
	public String getScreenShot(String imageName) throws IOException{
		if(imageName.equals("")){
			imageName = "blank";
		}
		
		File image = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String imagelocation = System.getProperty("user.dir")+"/src/main/java/com/HybridFrameWork/screenshot/";
		
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		String actualImageName = imagelocation+imageName+"_"+formater.format(calendar.getTime())+".png";
		
		File destFile = new File(actualImageName);
		FileUtils.copyFile(image, destFile);
		
		return actualImageName;
	}
	
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
	

	
	public static void main(String[] args) throws IOException {
		TestBase tb = new TestBase();
		
		tb.loadPropertiesFiles();
		System.out.println(tb.OR.getProperty("userName"));
		System.out.println(tb.OR.getProperty("password"));
	}

	
	
	
	
}
