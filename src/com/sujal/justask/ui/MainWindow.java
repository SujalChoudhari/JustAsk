/**
 * 
 */
package com.sujal.justask.ui;

import java.awt.*;

import javax.swing.JFrame;

import com.sujal.justask.util.Constants;

/**
 * 
 */
public class MainWindow extends JFrame {

	
	public MainWindow() throws HeadlessException {
		this("Application");
	}

	public MainWindow(String title) throws HeadlessException {
		super(title);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
	}
	
	public void init()
	{
		showLoginPage();
	}
	
	private void showLoginPage() {
		this.add(new LoginPanel(this));
	}
	
	public void render() {
		
		this.setVisible(true);
	}
	
}
