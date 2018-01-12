package com.HybridFrameWork;


import org.testng.annotations.Test;

import com.HybridFrameWork.testBase.TestBase;
import com.HybridFrameWork.uiActions.SignUpPage;


public class Test2 extends TestBase{
	
	
	SignUpPage sup;
	
	@Test
	public void test1(){
		sup = new SignUpPage(driver);
		sup.doSignUpAs(sup.Premium, "Jagjeet Singh", sup.generateRandomEmail(),"qaz123wsx");
	}
	
	
}
