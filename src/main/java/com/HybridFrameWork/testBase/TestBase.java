package com.HybridFrameWork.testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;



public class TestBase {

	public WebDriver driver;
	public Properties OR;
	public File F1;
	public FileInputStream File;
	
	
	public static ExtentReports extent;
	public static ExtentTest test;
	public ITestResult result;
	
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
	}
	
	public void getPropertiesData() {
		
	}
	

//========//========//========UTILITY FUNCTIONS//========//========//========//========//========//
	
	public void getScreenShot(String imageName) throws IOException{
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
