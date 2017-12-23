package com.HybridFrameWork;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.HybridFrameWork.testBase.TestBase;



public class Test2 extends TestBase{
	
	@DataProvider(name = "testData")
	public Object[][] getLoginData(){
		Object[][] data = getData("TestData.xlsx", "LoginTestData");
		return data;
	}
	
	@Test(dataProvider = "testData")
	public void testLogin(String Login, String Password, String runMode){
		System.out.println(Login+","+Password+","+runMode);
	}
}
