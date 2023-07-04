package com.sujal.justask.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public final class Factory {
	
	public static final Font HEADING_FONT = new Font("SansSerif", 0, 30);
	public static final Font SUB_HEADING_FONT = new Font("SansSerif", 0, 24);
	public static final Font BUTTON_FONT = new Font("SansSerif", 0, 18);
	public static final Font NORMAL_FONT = new Font("SansSerif", 0, 16);
	
	public static final Color BACKGROUND_DARK_COLOR = new Color(55, 55, 55);
	public static final Color BACKGROUND_LIGHT_COLOR = new Color(100,100,100);
	public static final Color FOREGROUND_COLOR = new Color(200, 200, 200);
	public static final Color WHITE_COLOR = new Color(255,255,255);

	public static JPanel createPanel() {
		JPanel panel = new JPanel();
		panel.setBackground(BACKGROUND_DARK_COLOR);
		panel.setForeground(FOREGROUND_COLOR);
		panel.setFont(NORMAL_FONT);
		return panel;
	}
	
	public static JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(NORMAL_FONT);
        label.setForeground(FOREGROUND_COLOR);
        label.setBackground(BACKGROUND_DARK_COLOR);
        return label;
    }
	
	public static JLabel createHeading(String text) {
		JLabel label = new JLabel(text);
        label.setFont(HEADING_FONT);
        label.setForeground(FOREGROUND_COLOR);
        return label;
	}
    
    public static JLabel createFinePrint(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif",0,12));
        label.setForeground(FOREGROUND_COLOR);
        return label;
    }
    
    public static JTextField createTextField(int columns) {
        JTextField textField = new JTextField(columns);
        textField.setMaximumSize(new Dimension(600, 38));
        textField.setMargin(new Insets(5, 5, 5, 5));
        textField.setFont(NORMAL_FONT);
        textField.setBackground(BACKGROUND_LIGHT_COLOR);
        textField.setForeground(FOREGROUND_COLOR);
        return textField;
    }
    
    public static JPasswordField createPasswordField(int columns) {
        JPasswordField passwordField = new JPasswordField(columns);
        passwordField.setMargin(new Insets(5, 5, 5, 5));
        passwordField.setMaximumSize(new Dimension(600, 38));
        passwordField.setFont(NORMAL_FONT);
        passwordField.setBackground(BACKGROUND_LIGHT_COLOR);
        passwordField.setForeground(FOREGROUND_COLOR);
        return passwordField;
    }
    
    public static JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(button.getMinimumSize());
        button.setBackground(BACKGROUND_LIGHT_COLOR);
        button.setForeground(FOREGROUND_COLOR);
        button.setFont(BUTTON_FONT);
        return button;
    }

}
