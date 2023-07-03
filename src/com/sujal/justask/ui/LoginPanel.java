/**
 * 
 */
package com.sujal.justask.ui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


import com.sujal.justask.util.Constants;

/**
 * Login Panel
 * This decides weather the user is a Administrator or a Consumer
 */
public class LoginPanel extends JPanel {
	private JFrame mWindow;
	
	private JPanel mMainPanel;
	private JPanel mInputPanel;
	private JLabel mImageDisplay;
	
	private JLabel mUserIdLabel;
	private JTextField mUsernameInput;
	
	private JLabel mPasswordLabel;
	private JPasswordField mPasswordInput;
	
	private JLabel mSubtitleLabel;
	
	private JButton mSubmitButton;
	
	public LoginPanel(JFrame window) {
		mWindow = window;
		mWindow.setTitle("Just Ask - Login");

		this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
		this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		this.setBackground(Constants.BACKGROUND_DARK_COLOR);
		
		mMainPanel = new JPanel();
		mMainPanel.setLayout(new BoxLayout(mMainPanel,BoxLayout.X_AXIS));
		mMainPanel.setBackground(Constants.BACKGROUND_DARK_COLOR);
		
		mInputPanel = new JPanel();
		mInputPanel.setLayout(new BoxLayout(mInputPanel,BoxLayout.Y_AXIS));
		mInputPanel.setBackground(Constants.BACKGROUND_DARK_COLOR);
		
		mUserIdLabel = new JLabel("User ID:");
		mUserIdLabel.setFont(Constants.SUB_HEADING_FONT);
		mUserIdLabel.setForeground(Constants.FOREGROUND_COLOR);
		mUsernameInput = new JTextField(12);
		mUsernameInput.setMaximumSize(new Dimension(600,38));
		mUsernameInput.setFont(Constants.SUB_HEADING_FONT);
		mUsernameInput.setBackground(Constants.BACKGROUND_LIGHT_COLOR);
		mUsernameInput.setForeground(Constants.FOREGROUND_COLOR);

		
		mInputPanel.add(mUserIdLabel);
		mInputPanel.add(mUsernameInput);
		
		mPasswordLabel = new JLabel("Password:");
		mPasswordLabel.setFont(Constants.SUB_HEADING_FONT);
		mPasswordLabel.setForeground(Constants.FOREGROUND_COLOR);
		mPasswordInput = new JPasswordField(12);
		mPasswordInput.setMaximumSize(new Dimension(600,38));
		mPasswordInput.setFont(Constants.SUB_HEADING_FONT);
		mPasswordInput.setBackground(Constants.BACKGROUND_LIGHT_COLOR);
		mPasswordInput.setForeground(Constants.FOREGROUND_COLOR);
		
		
		mInputPanel.add(mPasswordLabel);
		mInputPanel.add(mPasswordInput);
		
		mSubtitleLabel = new JLabel("New? Ask your Co-ordinator to add your credentials");
		mSubtitleLabel.setForeground(Constants.FOREGROUND_COLOR);
		mInputPanel.add(mSubtitleLabel);
		
		mSubmitButton = new JButton("Submit");
		mSubmitButton.setBackground(Constants.BACKGROUND_LIGHT_COLOR);
		mSubmitButton.setForeground(Constants.FOREGROUND_COLOR);
		mSubmitButton.setFont(Constants.SUB_HEADING_FONT);
		mInputPanel.add(mSubmitButton);
		
		
		ImageIcon imageIcon = new ImageIcon(new ImageIcon(
				getClass()
				.getResource(Constants.APP_LOGO))
				.getImage()
				.getScaledInstance(
						Constants.LOGO_WIDTH, 
						Constants.LOGO_HEIGHT, 
						Image.SCALE_DEFAULT));
		
		mImageDisplay = new JLabel(imageIcon);
		
		mMainPanel.add(mInputPanel);
		mMainPanel.add(mImageDisplay);
		
		this.add(mMainPanel);
	}


}
