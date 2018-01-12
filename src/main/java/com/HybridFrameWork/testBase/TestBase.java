package com.HybridFrameWork.testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.HybridFrameWork.excelReader.Excel_reader;
import com.HybridFrameWork.helper.loggerHelper.LoggerHelper;
import com.HybridFrameWork.helper.waitHelper.WaitHelper;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestBase {

	public static final Logger log = LoggerHelper.getLogger(TestBase.class);
	public WebDriver driver;

	public Properties OR;
	public File F1;
	public FileInputStream File;

	public static ExtentReports extent;
	public static ExtentTest test;
	public ITestResult result;
	public Excel_reader excelreader;



//========//========//========GLOBAL FUNCTIONS//========//========//========//========//========//

	@BeforeClass()
	public void launchBrowser(){
		loadConfigurationFiles();
		Config config = new Config(OR);

		selectBrowser(config.getBrowser());
		navigateToURL(config.getWebsite());
		WaitHelper waitHelper = new WaitHelper(driver);
		waitHelper.setImplicitWait(config.getImplicitWait(), TimeUnit.SECONDS);
		waitHelper.setPageLoadTimeout(config.getPageLoadTimeOut(), TimeUnit.SECONDS);
	}


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

	//Selects Browser
	public void selectBrowser(String browser){
		if(browser.equalsIgnoreCase("firefox")){
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/drivers/geckodriver.exe");
			driver = new FirefoxDriver();
			log.info("Selected browser Firefox.");
		}
		else if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			log.info("Selected browser Chrome.");
		}
	}

	//Navigates to a URL
	public void navigateToURL(String url) {
		driver.get(url);
		log.info("Opening the URL: "+ url);
	}


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


//========//========//========CONFIGURATION FILE FUNCTIONS AND LOGS//========//========//========//========//========//

	public void loadConfigurationFiles(){

		try {
			OR = new Properties();
			F1 = new File(System.getProperty("user.dir") + "/src/main/java/com/HybridFrameWork/config/config.properties");
			File = new FileInputStream(F1);
			OR.load(File);
			log.info(">>>>> Config. Configuration file loaded. <<<<<<");
		} catch (Exception e) {
			log.info(">>>>>Unable to load Config. Configuration file.<<<<<<");
			e.printStackTrace();
		}

	}

	public int getExplicitWait() {
		return Integer.parseInt(OR.getProperty("ExplicitWait"));
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


	//========//========//========Excel FUNCTIONS//========//========//========//========//========//

	public String[][] getData(String excelName, String sheetName){
		System.out.println(System.getProperty("user.dir"));
		String excellocation = System.getProperty("user.dir")+"/src/main/java/com/HybridFrameWork/data/"+excelName;
		System.out.println(excellocation);
		excelreader = new Excel_reader();
		return excelreader.getExcelData(excellocation, sheetName);
	}


	public static void main(String[] args) throws Exception {
		TestBase tb = new TestBase();
		tb.loadConfigurationFiles();
		//System.out.println(tb.OR.getProperty("Signup_UserName"));		
		System.out.println(tb.getWebElement("Signup_UserName"));
	}


}
