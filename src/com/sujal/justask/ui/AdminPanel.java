package com.sujal.justask.ui;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.sujal.justask.ApplicationHandler;
import com.sujal.justask.util.Database;
import com.sujal.justask.util.Factory;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminPanel extends JPanel {
    private ApplicationHandler mHandler;
    private JList<String> mSurveyList;
    private JButton mSendMailButton;
    private JButton mViewResponseButton;
    private JPanel mSurveyView;

    public AdminPanel(ApplicationHandler window) {
        mHandler = window;
        setLayout(new BorderLayout());
        setBackground(Factory.BACKGROUND_DARK_COLOR);

        createSurveyListView();
        createUtilityButtons();
        createMenu();

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBackground(Factory.BACKGROUND_DARK_COLOR);
        leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        leftPanel.setPreferredSize(new Dimension(200, getHeight()));

        JScrollPane scrollPane = new JScrollPane(mSurveyList);
        leftPanel.add(scrollPane, BorderLayout.CENTER);

        add(leftPanel, BorderLayout.WEST);
        
        JButton refreshButton = Factory.createButton("Refresh");
        refreshButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				updateSurveyList();
			}
		});
        leftPanel.add(refreshButton, BorderLayout.NORTH);
        
        mSurveyView = Factory.createPanel();
        add(mSurveyView, BorderLayout.CENTER);
        mSurveyView.setLayout(new BoxLayout(mSurveyView, BoxLayout.Y_AXIS));
        add(createUtilityPanel(), BorderLayout.EAST);
    }
    
    private void createMenu() {
        JMenuBar menuBar = new JMenuBar();
        add(menuBar, BorderLayout.NORTH);

        JMenu newMenu = new JMenu("New");
        menuBar.add(newMenu);

        JMenuItem newSurvey = new JMenuItem("Survey");
        newSurvey.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	new CreateSurvey().start();
            }
        });
        newMenu.add(newSurvey);

        JMenuItem newUser = new JMenuItem("User");
        newUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CreateUser().start();
            }
        });
        newMenu.add(newUser);

        JMenu mailMenu = new JMenu("Mail");
        menuBar.add(mailMenu);

        JMenuItem sendAll = new JMenuItem("All");
        sendAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle send all mail action
            }
        });
        mailMenu.add(sendAll);

        JMenuItem sendSelected = new JMenuItem("Send Selected");
        sendSelected.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle send selected mail action
            }
        });
        mailMenu.add(sendSelected);
    }
    private void updateSurveyList() {
        List<String> surveyList = Database.getAllSurveys();
        DefaultListModel<String> listModel = new DefaultListModel<>();
        
        for (String survey : surveyList) {
            listModel.addElement(survey); // Add the updated surveys to the list model
        }
        
        mSurveyList.setModel(listModel); // Set the updated model to the JList
    }

    private void createSurveyListView() {
    	
        // Fetch surveys from MongoDB
        List<String> surveyList = Database.getAllSurveys();
        
        // Convert the list to an array
        String[] surveyArray = surveyList.toArray(new String[0]);
        
        // Create the JList with the survey array
        mSurveyList = new JList<>(surveyArray);
        mSurveyList.setBackground(Factory.BACKGROUND_LIGHT_COLOR);
        mSurveyList.setForeground(Factory.FOREGROUND_COLOR);
        mSurveyList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
     // Add a ListSelectionListener to the mSurveyList JList
        mSurveyList.addListSelectionListener(new ListSelectionListener() {
        	public void valueChanged(ListSelectionEvent e) {
        	    if (!e.getValueIsAdjusting()) {
        	        // Get the selected survey
        	        String selectedSurvey = mSurveyList.getSelectedValue();

        	        // Fetch the survey questions and answers with usernames from the database
        	        Map<String, List<String>> surveyData = Database.getSurveyQuestionsAndAnswers(selectedSurvey);
        	        List<String> questions = surveyData.get("questions");
        	        List<String> answers = surveyData.get("answers");
        	        List<String> usernames = surveyData.get("usernames");

        	        // Clear the surveyView panel before displaying the questions and answers
        	        mSurveyView.removeAll();

        	        // Display the survey questions and answers
        	        for (int i = 0; i < questions.size(); i++) {
        	            String question = questions.get(i);
        	            JLabel questionLabel = Factory.createHeading("Question:\n" + question);
        	            mSurveyView.add(questionLabel);

        	            List<String> userResponses = new ArrayList<>();
        	            for (int j = 0; j <= i; j++) {
        	                String username = usernames.get(j);
        	                String answer = answers.get(j);
        	                mSurveyView.add(Factory.createLabel(username + ": " + answer));
        	            }
        	        }

        	        // Update the surveyView panel to reflect the changes
        	        mSurveyView.revalidate();
        	        mSurveyView.repaint();
        	    }
        	}


        });

    }


    private void createUtilityButtons() {
        mSendMailButton = Factory.createButton("Send Main");
        mViewResponseButton = Factory.createButton("View Responce");

        Dimension buttonSize = new Dimension(150, 30);
        mSendMailButton.setPreferredSize(buttonSize);
        mViewResponseButton.setPreferredSize(buttonSize);

        mSendMailButton.setFont(new Font("Arial", Font.PLAIN, 14));
        mViewResponseButton.setFont(new Font("Arial", Font.PLAIN, 14));
    }

    private JPanel createUtilityPanel() {
        JPanel utilityPanel = new JPanel();
        utilityPanel.setBackground(Factory.BACKGROUND_DARK_COLOR);
        utilityPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        utilityPanel.setLayout(new BoxLayout(utilityPanel, BoxLayout.X_AXIS));

        Box buttonBox = Box.createVerticalBox();
        buttonBox.setAlignmentX(Component.RIGHT_ALIGNMENT);
        buttonBox.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        buttonBox.add(Box.createVerticalGlue());
        buttonBox.add(mSendMailButton);
        buttonBox.add(Box.createVerticalStrut(10));
        buttonBox.add(mViewResponseButton);

        utilityPanel.add(buttonBox);

        return utilityPanel;
    }
}
