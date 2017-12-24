package com.HybridFrameWork.helper.loggerHelper;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.HybridFrameWork.utility.ResourceHelper;


// Now we need to register the log4j properties file, in order to use it. 
// As we were doing initially in the test base class.
// Now onwards all the classes will take help of this class'es method for registering log4j

@SuppressWarnings("rawtypes")
public class LoggerHelper {
	
	private static boolean root = false;
	
	public static Logger getLogger(Class className){
		if (root) {
			return Logger.getLogger(className);
		}
		
		//it uses a utility class'es method "ResourceHelper.getResourcePath"
		// to locate to the log4j file which is present in the resources folder.
		
		PropertyConfigurator.configure(ResourceHelper.getResourcePath("/src/main/resources/log4j.properties"));
		root = true;
		return Logger.getLogger(className);
	}
	
}
