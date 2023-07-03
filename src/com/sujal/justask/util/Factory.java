package com.sujal.justask.util;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public final class Factory {

	public static JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(Constants.SUB_HEADING_FONT);
        label.setForeground(Constants.FOREGROUND_COLOR);
        return label;
    }
    
    public static JLabel createFinePrint(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif",0,12));
        label.setForeground(Constants.FOREGROUND_COLOR);
        return label;
    }
    
    public static JTextField createTextField(int columns) {
        JTextField textField = new JTextField(columns);
        textField.setMaximumSize(new Dimension(600, 38));
        textField.setFont(Constants.SUB_HEADING_FONT);
        textField.setBackground(Constants.BACKGROUND_LIGHT_COLOR);
        textField.setForeground(Constants.FOREGROUND_COLOR);
        return textField;
    }
    
    public static JPasswordField createPasswordField(int columns) {
        JPasswordField passwordField = new JPasswordField(columns);
        passwordField.setMaximumSize(new Dimension(600, 38));
        passwordField.setFont(Constants.SUB_HEADING_FONT);
        passwordField.setBackground(Constants.BACKGROUND_LIGHT_COLOR);
        passwordField.setForeground(Constants.FOREGROUND_COLOR);
        return passwordField;
    }
    
    public static JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(Constants.BACKGROUND_LIGHT_COLOR);
        button.setForeground(Constants.FOREGROUND_COLOR);
        button.setFont(Constants.SUB_HEADING_FONT);
        return button;
    }
}
