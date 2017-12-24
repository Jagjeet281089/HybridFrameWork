package com.HybridFrameWork.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

//Use is of this class is to shortening the process of giving the file path.
// If use this class, we don't have to write System.getProperty("user.dir") every time


public class ResourceHelper {
	
	//Call this method with the path of the file/resource you want to read.
	// It calls the second method internally.
	public static String getResourcePath(String resource) {
		String path = getBaseResourcePath() + resource;
		return path;
	}

	//this method returns the user directory up to the project. Before /src.
	public static String getBaseResourcePath() {
		String path = System.getProperty("user.dir");
		//System.out.println(path);
		return path;
	}

	//Where ever we need a file that needs to be read as in the form of file input stream.
	public static InputStream getResourcePathInputStream(String path) throws FileNotFoundException{
		return new FileInputStream(ResourceHelper.getResourcePath(path));
	}

}
