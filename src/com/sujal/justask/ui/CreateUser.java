package com.sujal.justask.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sujal.justask.util.Database;
import com.sujal.justask.util.Factory;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SpringLayout;
import javax.swing.JButton;

public class CreateUser extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateUser frame = new CreateUser();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CreateUser() {
		setTitle("New User - JustAsk");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 250);
		contentPane = Factory.createPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JPanel detailsPanel = Factory.createPanel();
		contentPane.add(detailsPanel);
		detailsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel = Factory.createPanel();
		detailsPanel.add(panel);
		
		JLabel usernameLabel = Factory.createLabel("Username: ");
		panel.add(usernameLabel);
		
		textField = Factory.createTextField(10);
		panel.add(textField);
		
		JPanel panel_1 = Factory.createPanel();
		detailsPanel.add(panel_1);
		
		JLabel passwordLabel = Factory.createLabel("Password");
		panel_1.add(passwordLabel);
		
		passwordField = Factory.createPasswordField(12);
		panel_1.add(passwordField);
		
		JPanel buttonPane = Factory.createPanel();
		contentPane.add(buttonPane);
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.X_AXIS));
		
		JButton createUserButton = Factory.createButton("Create");
		buttonPane.add(createUserButton);
		pack();
		createUserButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Database.createUser(textField.getText(), new String(passwordField.getPassword()), false);
				setVisible(false);
			}
		});
	}
	

}
