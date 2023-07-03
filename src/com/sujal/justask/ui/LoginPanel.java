package com.sujal.justask.ui;

import java.awt.Color;
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
import com.sujal.justask.util.Factory;
/**
 * Login Panel
 * This decides whether the user is an Administrator or a Consumer
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

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(Constants.BACKGROUND_DARK_COLOR);
        
        mMainPanel = new JPanel();
        mMainPanel.setLayout(new BoxLayout(mMainPanel, BoxLayout.X_AXIS));
        mMainPanel.setBackground(Constants.BACKGROUND_DARK_COLOR);
        
        mInputPanel = new JPanel();
        mInputPanel.setLayout(new BoxLayout(mInputPanel, BoxLayout.Y_AXIS));
        mInputPanel.setBackground(Constants.BACKGROUND_DARK_COLOR);
        
        mUserIdLabel = Factory.createLabel("User ID:");
        mUsernameInput = Factory.createTextField(12);
        
        mInputPanel.add(mUserIdLabel);
        mInputPanel.add(mUsernameInput);
        
        mPasswordLabel = Factory.createLabel("Password:");
        mPasswordInput = Factory.createPasswordField(12);
        
        mInputPanel.add(mPasswordLabel);
        mInputPanel.add(mPasswordInput);
        
        mSubtitleLabel = Factory.createFinePrint("New? Ask your Coordinator to add your credentials");
        mInputPanel.add(mSubtitleLabel);
        
        mSubmitButton = Factory.createButton("Submit");
        mInputPanel.add(mSubmitButton);
        
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(
                getClass().getResource(Constants.APP_LOGO))
                .getImage()
                .getScaledInstance(Constants.LOGO_WIDTH, Constants.LOGO_HEIGHT, Image.SCALE_DEFAULT));
        
        mImageDisplay = new JLabel(imageIcon);
        
        mMainPanel.add(mInputPanel);
        mMainPanel.add(mImageDisplay);
        
        add(mMainPanel);
    }
    
    
}
