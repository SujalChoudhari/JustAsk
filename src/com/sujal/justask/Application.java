/**
 * 
 */
package com.sujal.justask;

/**
 * The Main Application class for the GUI and back-end Interface
 */
public class Application {
	
	private static ApplicationHandler mHandler;
	
	public static void main(String[] args) {		
		mHandler = new ApplicationHandler();
		mHandler.showLoginWindow();
	}
}
