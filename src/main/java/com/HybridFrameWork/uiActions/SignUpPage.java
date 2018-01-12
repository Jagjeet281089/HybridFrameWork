package com.HybridFrameWork.uiActions;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.HybridFrameWork.helper.alertHelper.AlertHelper;
import com.HybridFrameWork.helper.browserHelper.BrowserHelper;
import com.HybridFrameWork.helper.genericHelper.GenericHelper;
import com.HybridFrameWork.helper.javaScriptHelper.JavaScriptHelper;
import com.HybridFrameWork.helper.loggerHelper.LoggerHelper;
import com.HybridFrameWork.helper.propertiesHelper.PropertiesHelper;
import com.HybridFrameWork.helper.waitHelper.WaitHelper;
import com.HybridFrameWork.testBase.TestBase;

public class SignUpPage {
	
	public static final Logger log = LoggerHelper.getLogger(SignUpPage.class);
	
	WebDriver driver;
	TestBase testbase;
	AlertHelper alert;
	BrowserHelper browser;
	GenericHelper genericHelp;
	JavaScriptHelper javaScript;
	PropertiesHelper properties;
	WaitHelper wait;
	
	
	public String Plus = "Plus";
	public String Premium = "Premium";
	public String Professional = "Professional";
	public String Enterprise = "Enterprise";
	String domainSignPageURL = "https://app.scheduleonce.com/SignUp.aspx";
	String Plus_URL = "?e=2";
	String Premium_URL = "?e=5";
	String Professional_URL = "?e=26";
	String Enterprise_URL = "?e=28";
	
	
	/*@FindBy (id="tzConfirmBtn")
	WebElement timeZoneContinueButton;
	@FindBy(xpath="//div[@id='TrackingIDContVal']/span[1]/small[1]")
	WebElement trackingIdText */		

	public SignUpPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this); // This is a construct that will initialize all the webelements defined above under page factory.	
		
		alert = new AlertHelper(driver);
		browser = new BrowserHelper(driver);
		genericHelp = new GenericHelper();
		javaScript = new JavaScriptHelper(driver);
		properties = new PropertiesHelper(driver);
		wait = new WaitHelper(driver);
		testbase = new TestBase();
		
		properties.loadSignUpPageProperties();
		wait.waitForElement(driver, 20 , properties.getWebElement("Signup_UserName"));
	}

//====================NAME FIELD============================================================
	
	public void enterName(String userName) {	
		properties.getWebElement("Signup_UserName").sendKeys(userName);
		log.info("User Name entered as : "+ userName);
	}
	
	public void generateNameFieldError() {
		clearForm();
		enterEmail(generateRandomEmail());
		enterPassword("qaz123wsx");
		enterRePassword("qaz123wsx");
		pressSubmit();
	}
	
	public boolean getNameFieldErrorElement() {
		generateNameFieldError();
		return genericHelp.isDisplayed(properties.getWebElement("SignUp_NameAlert"));
	}
	
	public String getNameFieldErrorText() {	
		generateNameFieldError();
		return genericHelp.readValueFromElement(properties.getWebElement("SignUp_NameAlert"));
	}
	
//====================EMAIL FIELD============================================================
	
	public void enterEmail(String emailAddress) {
		properties.getWebElement("Signup_Email").sendKeys(emailAddress);
		log.info("Email entered as : "+ emailAddress);
	}
	
	public void generateEmailRequiredFieldError() {
		enterName("Random Name");
		enterPassword("qaz123wsx");
		enterRePassword("qaz123wsx");
		pressSubmit();
	}
	
	public void generateInvalidEmailFieldError() {
		enterName("Random Name");
		enterPassword("qaz123wsx");
		enterRePassword("qaz123wsx");
		pressSubmit();
	}
	
	public boolean getEmailFieldErrorElement() {
		return genericHelp.isDisplayed(properties.getWebElement("SignUp_EmailAlert"));
	}
	
	public String getEmailFieldErrorText() {	
		return genericHelp.readValueFromElement(properties.getWebElement("SignUp_EmailAlert"));
	}

	
//====================PASSWORD FIELD============================================================
	
	public void enterPassword(String password) {
		properties.getWebElement("Signup_Password").sendKeys(password);
		log.info("Password entered as : "+ password);
	}
	
	public void enterRePassword(String repassword) {	
		properties.getWebElement("Signup_RePassword").sendKeys(repassword);
		log.info("Re-Password entered as : "+ repassword);
	}
	
	
//====================SIGNUP FUNCTIONS=======================================================
	
	public void clearForm() {
		properties.getWebElement("Signup_UserName").clear();
		properties.getWebElement("Signup_Email").clear();
		properties.getWebElement("Signup_Password").clear();
		properties.getWebElement("Signup_RePassword").clear();
		log.info("Everything Cleared.");
	}
	
	public HomePage doSignUp(String NameOfUser, String EmailOfUser, String PasswordForUser) {
		clearForm();
		enterName(NameOfUser);
		enterEmail(EmailOfUser);
		enterPassword(PasswordForUser);
		enterRePassword(PasswordForUser);
		//pressSubmit();
		return new HomePage(driver);
	}
	
	public void openSignUpPageAs(String accountType){
		if(accountType.equalsIgnoreCase(Plus)){
			browser.gotoPage(domainSignPageURL+Plus_URL);
			log.info("Opened SignUp page as Plus User.");
		}else if (accountType.equalsIgnoreCase(Premium)) {
			browser.gotoPage(domainSignPageURL+Premium_URL);
			log.info("Opened SignUp page as Premium User.");
		}else if (accountType.equalsIgnoreCase(Professional)) {
			browser.gotoPage(domainSignPageURL+Professional_URL);
			log.info("Opened SignUp page as Professional User.");
		}else if (accountType.equalsIgnoreCase(Enterprise)) {
			browser.gotoPage(domainSignPageURL+Enterprise_URL);
			log.info("Opened SignUp page as Enterprise User.");
		}
	}
	
	public void doSignUpAs(String accountType,String NameOfUser, String EmailOfUser, String PasswordForUser){
		if(accountType.equalsIgnoreCase(Plus)){
			openSignUpPageAs(accountType);
			doSignUp(NameOfUser, EmailOfUser, PasswordForUser);
		}else if (accountType.equalsIgnoreCase(Premium)) {
			openSignUpPageAs(accountType);
			doSignUp(NameOfUser, EmailOfUser, PasswordForUser);
		}else if (accountType.equalsIgnoreCase(Professional)) {
			openSignUpPageAs(accountType);
			doSignUp(NameOfUser, EmailOfUser, PasswordForUser);
		}else if (accountType.equalsIgnoreCase(Enterprise)) {
			openSignUpPageAs(accountType);
			doSignUp(NameOfUser, EmailOfUser, PasswordForUser);
		}
	}
	
//=======================OTHERS================================================================
	
		public void pressSubmit() {
			properties.getWebElement("Signup_Button").click();
			log.info("Submit Button Pressed.");
		}
		
		public String getHeaderText(){
			String headerText = properties.getWebElement("Signup_HeaderText").getText();
			return headerText;
		}
		
		public String generateRandomEmail(){
			String emailid = "Jagjeetsinghenuke+"+System.currentTimeMillis()+"@gmail.com";;
			return emailid;
		}
}
