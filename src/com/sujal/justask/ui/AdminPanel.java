package com.sujal.justask.ui;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.bson.Document;

import com.sujal.justask.ApplicationHandler;
import com.sujal.justask.util.Database;
import com.sujal.justask.util.Factory;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminPanel extends JPanel {
    private ApplicationHandler mHandler;
    private JList<String> mSurveyList;
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
        
        JPanel panel = Factory.createPanel();
        add(panel, BorderLayout.CENTER);
        
        mSurveyView = Factory.createPanel();
        panel.add(mSurveyView);
        mSurveyView.setLayout(new BoxLayout(mSurveyView, BoxLayout.Y_AXIS));
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
        	    	mSurveyView.removeAll();
        	        mSurveyView.revalidate();
        	    	mSurveyView.repaint();
        	        // Get the selected survey
        	        String selectedSurvey = mSurveyList.getSelectedValue();

        	        // Fetch the survey questions and answers with username's from the database
        	        Document surveyData = Database.getSurveyQuestionsAndAnswers(selectedSurvey);
        	        System.out.println(surveyData);
        	        
        	        
        	        @SuppressWarnings("unchecked")
					List<String> questionStrings = (List<String>) surveyData.get("questions");
        	        @SuppressWarnings("unchecked")
					List<List<Document>> responseStrings = (List<List<Document>>) surveyData.get("surveyResponses");
        	        
        	        for(String question : questionStrings) {
        	        	
        	        	if(responseStrings.size() < questionStrings.size()) {
        	        		mSurveyView.add(Factory.createLabel("Not Enough Data"));
        	        		return;
        	        	}
        	        	mSurveyView.add(Factory.createHeading(question));
        	        	List<Document> nameList = (List<Document>) responseStrings.get(questionStrings.indexOf(question));
        				
        	        	for(Document response: nameList) {
        	        		mSurveyView.add(Factory.createLabel(
        	        				response.getString("user")
        	        				+ " : " + response.getString("response")
        	        				));
        	        	}
        	        }
        	        
        	        mSurveyView.revalidate();
        	        mSurveyView.repaint();
        	    }
        	}


        });

    }


    private void createUtilityButtons() {

        Dimension buttonSize = new Dimension(150, 30);
    }

}
