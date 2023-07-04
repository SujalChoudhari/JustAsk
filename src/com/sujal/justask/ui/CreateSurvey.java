package com.sujal.justask.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import com.sujal.justask.util.Database;
import com.sujal.justask.util.Factory;

import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.UIManager;

public class CreateSurvey extends JFrame {

    private JPanel contentPane;
    private DefaultListModel<String> questionListModel;
    private JList<String> questionList;
    private JTextField textField;

    /**
     * Launch the application.
     */
    public void start() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CreateSurvey frame = new CreateSurvey();
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
    public CreateSurvey() {
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setTitle("New Survey - Just Ask");
        setBounds(100, 100, 447, 372);
        contentPane = Factory.createPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setBackground(Factory.BACKGROUND_DARK_COLOR);
        contentPane.setForeground(Factory.FOREGROUND_COLOR);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        
        JPanel surveyNamePanel =Factory.createPanel();
        surveyNamePanel.setLayout(new BoxLayout(surveyNamePanel, BoxLayout.X_AXIS));
        JLabel nameLabel = Factory.createLabel("Name: ");
        surveyNamePanel.add(nameLabel);
        nameLabel.setForeground(Factory.FOREGROUND_COLOR);
        JTextField surveyTextField = Factory.createTextField(12);
        surveyNamePanel.add(surveyTextField);
        getContentPane().add(surveyNamePanel);
        
        surveyNamePanel.setBackground(Factory.BACKGROUND_DARK_COLOR);
        surveyNamePanel.setForeground(Factory.FOREGROUND_COLOR);
        
        JPanel questionPanel =Factory.createPanel();
        contentPane.add(questionPanel);
        questionPanel.setLayout(new BorderLayout(0, 0));

        questionPanel.setBackground(Factory.BACKGROUND_DARK_COLOR);
        questionPanel.setForeground(Factory.FOREGROUND_COLOR);
        
        JScrollPane questionScrollPane = new JScrollPane();
        questionPanel.add(questionScrollPane, BorderLayout.CENTER);
        questionScrollPane.setBackground(Factory.BACKGROUND_DARK_COLOR);
        questionScrollPane.setForeground(Factory.FOREGROUND_COLOR);
        

        questionListModel = new DefaultListModel<>();
        questionList = new JList<>(questionListModel);
        questionList.setBackground(Factory.BACKGROUND_LIGHT_COLOR);
        questionList.setForeground(Factory.FOREGROUND_COLOR);
        
        questionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        questionScrollPane.setViewportView(questionList);

        JPanel buttonPanel = Factory.createPanel();
        buttonPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        buttonPanel.setBackground(Factory.BACKGROUND_DARK_COLOR);
        buttonPanel.setForeground(Factory.FOREGROUND_COLOR);
        contentPane.add(buttonPanel);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        
        JPanel questionHolder =Factory.createPanel();
        buttonPanel.add(questionHolder);
        questionHolder.setLayout(new BoxLayout(questionHolder, BoxLayout.X_AXIS));
        
        JLabel questionLabel = Factory.createLabel("Question:");
        questionLabel.setForeground(UIManager.getColor("ScrollBar.background"));
        questionHolder.add(questionLabel);
        
        textField = Factory.createTextField(12);
        questionHolder.add(textField);
        
        JPanel buttonHolder =Factory.createPanel();
        buttonPanel.add(buttonHolder);
        buttonHolder.setLayout(new BoxLayout(buttonHolder, BoxLayout.X_AXIS));
        
        JButton addButton = Factory.createButton("Add");
        buttonHolder.add(addButton);
        addButton.addActionListener(new ActionListener() {
        	 public void actionPerformed(ActionEvent e) {
                 String question = textField.getText().trim();
                 if (!question.isEmpty()) {
                     questionListModel.addElement(question);
                     textField.setText("");
                 }
             }
            });
        
        JButton removeButton = Factory.createButton("Remove");
        buttonHolder.add(removeButton);
        removeButton.addActionListener(new ActionListener() {
        	 public void actionPerformed(ActionEvent e) {
                 int selectedIndex = questionList.getSelectedIndex();
                 if (selectedIndex != -1) {
                     questionListModel.remove(selectedIndex);
                 }
             }
        });
        
        JButton btnSaveSurveys = Factory.createButton("Save Surveys");
        btnSaveSurveys.setAlignmentX(0.5f);
        btnSaveSurveys.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	    List<String> questions = new ArrayList<>();
        	    for (int i = 0; i < questionListModel.getSize(); i++) {
        	        String question = questionListModel.getElementAt(i);
        	        questions.add(question);
        	    }

        	    // Store questions in MongoDB
        	    Database.createSurvey(surveyTextField.getText(), questions);
        	    
        	    setVisible(false);

        	}
        });
        contentPane.add(btnSaveSurveys);
    }

}
