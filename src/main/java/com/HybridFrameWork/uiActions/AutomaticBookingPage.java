package com.HybridFrameWork.uiActions;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;


public class AutomaticBookingPage {

	@FindBy (id="tzConfirmBtn")
	WebElement timeZoneContinueButton;

	@FindBy (id ="monthHeading")
	WebElement currentMonthName;

	@FindBy(xpath="//button[@type = 'button' and @class = 'next active']/span[1]")
	WebElement nextMonthsName;
	
	@FindBy(xpath="//button[@class='prev active']/span[@class='ng-binding']")
	WebElement prevMonthsName;
	
	@FindBy(xpath="//button[@aria-label='Confirm your booking' and @id='bookSlot']")
	WebElement continueButtonOnDateandTimeScreen;
	
	@FindBy(xpath="//div[@id='TrackingIDContVal']/span[1]/small[1]")
	WebElement trackingIdText;
	
	
	WebDriver driver;

	public AutomaticBookingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this); // This is a construct that will initialize all the webelements defined above under page factory.
	}
	
	
	public void makeAutomaticBookingAt(String Year, String Month, String Date, String Time){	
		submitTimeZonePopup();
		navigateToMonth(Month, Year);
		selectDate(Date);
		selectTime(Time);
		//submitMonthDateTime();
	}
	
	
//==================== OTHERS ====================	
	
	public void submitTimeZonePopup(){
		try {
			timeZoneContinueButton.click();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
	
	public void submitMonthDateTime(){
		try {
			continueButtonOnDateandTimeScreen.click();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
	
//====================MONTH Related====================
	
	public String getCurrentMonthName(){
		String monthName = currentMonthName.getText();
		String trimmedName = monthName.trim();
		
		return trimmedName;
	}
	
	public void selectNextMonth(){
				
		
		nextMonthsName.click();		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
		
	
	public void navigateToMonth(String month, String year){
		
		String expectedMonthToNavigate = month.trim()+" "+year.trim();	
		System.out.println("Expected Month to Navigate is: "+ expectedMonthToNavigate);
		String currentMonthName =  getCurrentMonthName();
		
		if(currentMonthName.equalsIgnoreCase(expectedMonthToNavigate)){
			//NA
		}
		else if(!currentMonthName.equalsIgnoreCase(expectedMonthToNavigate)){
			while(!currentMonthName.equalsIgnoreCase(expectedMonthToNavigate)){
				selectNextMonth();
				currentMonthName = getCurrentMonthName();
			}
		}
		
	}
	
//====================DATE RELATED====================
	
	public void selectDate(String date){
		driver.findElement(By.xpath("//button[@class='day ng-scope boldDay']/span[contains(text(),"+date+")]")).click();
		
	}
	
//====================TIME RELATED====================
	
	public void selectTime(String time){
		System.out.println("Expected time to be Selected: "+time);
		WebElement timeElement =  driver.findElement(By.xpath("//button[@class='timeSlotSingle ng-pristine ng-untouched ng-valid ng-binding ng-not-empty' and contains(text(),'"+time+"')]"));
		
		int x = timeElement.getLocation().x;
		int y = timeElement.getLocation().y;
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("scroll("+x+", "+y+")");
		
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOf(timeElement));
	
		timeElement.click();
	
	}
	
	
	
	

	
	
	public void selectFirstDate(){
		driver.findElement(By.xpath("//button[@type='button' and contains(@class,'timeSlotSingle ng-pristine ng-')][1]")).click();
	}
	
//====================Confirmation Page related====================
	
	public String getBookingID(){
		String trackIdText = trackingIdText.getText();
		return trackIdText;
	}
	

}
