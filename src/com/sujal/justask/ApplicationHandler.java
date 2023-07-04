/**
 * 
 */
package com.sujal.justask;

import java.awt.*;

import javax.swing.JFrame;

import com.sujal.justask.ui.AdminPanel;
import com.sujal.justask.ui.LoginPanel;
import com.sujal.justask.util.Constants;

public class ApplicationHandler  {

	public JFrame loginFrame;
	public JFrame adminFrame;
			
	public void showLoginWindow() {
		loginFrame = new JFrame();
		loginFrame.setTitle("Login - JustAsk");
		loginFrame.setMinimumSize(new Dimension(Constants.WINDOW_WIDTH,Constants.WINDOW_HEIGHT));
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginFrame.add(new LoginPanel(this));
		loginFrame.pack();
		loginFrame.setVisible(true);
	}
	
	public void showAdminWindow() {
		adminFrame = new JFrame();
		adminFrame.setTitle("Admin - JustAsk");
		adminFrame.setMinimumSize(new Dimension(Constants.WINDOW_WIDTH,Constants.WINDOW_HEIGHT));
		adminFrame.setVisible(true);
		adminFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		adminFrame.add(new AdminPanel(this));
		adminFrame.pack();
		adminFrame.setVisible(true);
	}
	
	
}
