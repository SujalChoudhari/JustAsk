package com.sujal.justask.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.bson.Document;

import com.sujal.justask.ApplicationHandler;
import com.sujal.justask.util.Database;
import com.sujal.justask.util.Factory;
import javax.swing.BoxLayout;

public class UserPanel extends JPanel {

	ApplicationHandler mHandler;
	JPanel mSurveyView;
	JList<String> mSurveyList;
	List<JTextField> mResponses;
	JButton mSubmitButton;
	public UserPanel(ApplicationHandler window) {
		mHandler = window;
		
		setLayout(new BorderLayout());
        setBackground(Factory.BACKGROUND_DARK_COLOR);
        
        createSurveyListView();
        
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
				updateSurveyList();
			}
		});
        leftPanel.add(refreshButton, BorderLayout.NORTH);
        
        JPanel rightPanel = Factory.createPanel();
        add(rightPanel, BorderLayout.CENTER);
        rightPanel.setLayout(new BorderLayout(0, 0));
        
        mSurveyView = Factory.createPanel();
        rightPanel.add(mSurveyView);
        mSurveyView.setLayout(new BoxLayout(mSurveyView, BoxLayout.Y_AXIS));
        
        mResponses = new ArrayList<JTextField>(5);
        
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

		                // Fetch the survey questions and answers with usernames from the database
		                Document surveyData = Database.getSurveyQuestionsAndAnswers(selectedSurvey);
		                System.out.println(surveyData);

		                @SuppressWarnings("unchecked")
		                List<String> questionStrings = (List<String>) surveyData.get("questions");
		                @SuppressWarnings("unchecked")
		                List<List<Document>> responseStrings = (List<List<Document>>) surveyData.get("surveyResponses");

		                for (String question : questionStrings) {
		                    mSurveyView.add(Factory.createHeading(question));
		                    JTextField field = Factory.createTextField(12);
		                    mResponses.add(field);
		                    mSurveyView.add(field);
		                }

		                mSurveyView.revalidate();
		                mSurveyView.repaint();
		            }

		            mSubmitButton = Factory.createButton("Submit");
		            mSurveyView.add(mSubmitButton);

		            // Add ActionListener to the Submit button
		            mSubmitButton.addActionListener(new ActionListener() {
		                public void actionPerformed(ActionEvent e) {
		                    // Get the selected survey
		                    String selectedSurvey = mSurveyList.getSelectedValue();

		                    // Get the user responses from the text fields
		                    List<String> userResponses = new ArrayList<>();
		                    for (JTextField responseField : mResponses) {
		                        String response = responseField.getText();
		                        userResponses.add(response);
		                    }

		                    // Save the user responses to the database
		                    Database.addSurveyAnswers(selectedSurvey, mHandler.userIDString, userResponses);

		                    // Show success message or perform any other desired actions
		                    System.out.println("User responses saved!");

		                    // Clear the text fields
		                    for (JTextField responseField : mResponses) {
		                        responseField.setText("");
		                    }
		                }
		            });
		        }
		    });
		}

}
