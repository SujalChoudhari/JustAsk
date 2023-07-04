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

import com.sujal.justask.util.Factory;

import javax.swing.BoxLayout;

public class CreateSurvey extends JFrame {

    private JPanel contentPane;
    private DefaultListModel<String> questionListModel;
    private JList<String> questionList;
    private JTextField questionTextField;

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
        setBounds(100, 100, 507, 319);
        contentPane = Factory.createPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setBackground(Factory.BACKGROUND_DARK_COLOR);
        contentPane.setForeground(Factory.FOREGROUND_COLOR);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        
        JPanel surveyNamePanel = new JPanel();
        JLabel nameLabel = new JLabel("Name: ");
        surveyNamePanel.add(nameLabel);
        nameLabel.setForeground(Factory.FOREGROUND_COLOR);
        JTextField surveyTextField = Factory.createTextField(24);
        surveyNamePanel.add(surveyTextField);
        getContentPane().add(surveyNamePanel);
        
        surveyNamePanel.setBackground(Factory.BACKGROUND_DARK_COLOR);
        surveyNamePanel.setForeground(Factory.FOREGROUND_COLOR);
        
        JPanel questionPanel = new JPanel();
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

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Factory.BACKGROUND_DARK_COLOR);
        buttonPanel.setForeground(Factory.FOREGROUND_COLOR);
        contentPane.add(buttonPanel);

        JLabel questionLabel = new JLabel("Question:");
        questionLabel.setForeground(Factory.FOREGROUND_COLOR);
        buttonPanel.add(questionLabel);
        

        questionTextField = Factory.createTextField(12);
        buttonPanel.add(questionTextField);
        questionTextField.setColumns(20);

        JButton addButton = Factory.createButton("Add");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String question = questionTextField.getText().trim();
                if (!question.isEmpty()) {
                    questionListModel.addElement(question);
                    questionTextField.setText("");
                }
            }
        });
        buttonPanel.add(addButton);

        JButton removeButton = Factory.createButton("Remove");
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = questionList.getSelectedIndex();
                if (selectedIndex != -1) {
                    questionListModel.remove(selectedIndex);
                }
            }
        });
        buttonPanel.add(removeButton);

        JButton saveButton = Factory.createButton("Save");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<String> questions = new ArrayList<>();
                for (int i = 0; i < questionListModel.getSize(); i++) {
                    String question = questionListModel.getElementAt(i);
                    questions.add(question);
                }
                // TODO: Store questions (e.g., in MongoDB)
            }
        });
        buttonPanel.add(saveButton);
    }

}
