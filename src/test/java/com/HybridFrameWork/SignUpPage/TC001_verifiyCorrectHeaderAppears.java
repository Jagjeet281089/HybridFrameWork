package com.HybridFrameWork.SignUpPage;

import org.testng.annotations.Test;

import com.HybridFrameWork.helper.assertionHelper.AssertionHelper;
import com.HybridFrameWork.helper.propertiesHelper.PropertiesHelper;
import com.HybridFrameWork.testBase.TestBase;
import com.HybridFrameWork.uiActions.SignUpPage;

public class TC001_verifiyCorrectHeaderAppears extends TestBase {
	
	
	SignUpPage sup;
	AssertionHelper assertionHelper;
	PropertiesHelper properties;
	
	@Test
	public void test1(){
		sup = new SignUpPage(driver);
		
		sup.openSignUpPageAs(sup.Plus);
		assertionHelper.verifyElementTextEquals(properties.getWebElement("Signup_HeaderText"), "Sign up for your Plus account free trial.");
		
		sup.openSignUpPageAs(sup.Premium);
		assertionHelper.verifyElementTextEquals(properties.getWebElement("Signup_HeaderText"), "Sign up for your Premium account free trial");
		
		sup.openSignUpPageAs(sup.Professional);
		assertionHelper.verifyElementTextEquals(properties.getWebElement("Signup_HeaderText"), "Sign up for your Professional account free trial");
		
		sup.openSignUpPageAs(sup.Enterprise);
		assertionHelper.verifyElementTextEquals(properties.getWebElement("Signup_HeaderText"), "Sign up for your Enterprise account free trial");
		
	}

}
