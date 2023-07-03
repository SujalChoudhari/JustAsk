/**
 * 
 */
package com.sujal.justask;


import com.sujal.justask.ui.MainWindow;
/**
 * The Main Application class for the GUI and back-end Interface
 */
public class Application {
	
	private static MainWindow mWindow;
	
	public static void main(String[] args) {		
		mWindow = new MainWindow("JustAsk");
		mWindow.init();
		mWindow.render();
	}
}
