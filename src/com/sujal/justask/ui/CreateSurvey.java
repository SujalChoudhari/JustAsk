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
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        
        JPanel surveyNamePanel = new JPanel();
        surveyNamePanel.add(new JLabel("Name: "));
        JTextField surveyTextField = new JTextField(24);
        surveyNamePanel.add(surveyTextField);
        getContentPane().add(surveyNamePanel);
        
        JPanel questionPanel = new JPanel();
        contentPane.add(questionPanel);
        questionPanel.setLayout(new BorderLayout(0, 0));

        JScrollPane questionScrollPane = new JScrollPane();
        questionPanel.add(questionScrollPane, BorderLayout.CENTER);

        questionListModel = new DefaultListModel<>();
        questionList = new JList<>(questionListModel);
        questionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        questionScrollPane.setViewportView(questionList);

        JPanel buttonPanel = new JPanel();
        contentPane.add(buttonPanel);

        JLabel lblNewLabel = new JLabel("Question:");
        buttonPanel.add(lblNewLabel);

        questionTextField = new JTextField();
        buttonPanel.add(questionTextField);
        questionTextField.setColumns(20);

        JButton addButton = new JButton("Add");
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

        JButton removeButton = new JButton("Remove");
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = questionList.getSelectedIndex();
                if (selectedIndex != -1) {
                    questionListModel.remove(selectedIndex);
                }
            }
        });
        buttonPanel.add(removeButton);

        JButton saveButton = new JButton("Save");
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
