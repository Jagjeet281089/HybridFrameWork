package com.HybridFrameWork.classTemplates;


import java.io.IOException;

import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.test.automation.uiAutomation.testBase.TestBase;
import com.test.automation.uiAutomation.uiActions.RegisterPage;

public class TestClass extends TestBase {
	RegisterPage registerPage;
	
	
	@BeforeClass
	public void setup() throws IOException {
		init();
	}

	@Test
	public void testName() {
		registerPage = new RegisterPage(driver);
		
		log("----------------Starting Test TC003_testName----------------");
		
		log("----------------Completed Test TC003_testName----------------");
	
	}

	@AfterClass
	public void endTest() {
		end();
	}

	public void log(String data) {
		log.info(data);
		Reporter.log(data);
	}

}

