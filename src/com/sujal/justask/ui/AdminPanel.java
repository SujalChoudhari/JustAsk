package com.sujal.justask.ui;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.sujal.justask.ApplicationHandler;
import com.sujal.justask.util.Constants;
import com.sujal.justask.util.Factory;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminPanel extends JPanel {
    private ApplicationHandler mHandler;
    private JList<String> mSurveyList;
    private JButton mSendMailButton;
    private JButton mViewResponseButton;

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
        add(createUtilityPanel(), BorderLayout.CENTER);
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


    private void createSurveyListView() {
        String[] surveyList = {"Survey 1", "Survey 2", "Survey 3"};
        mSurveyList = new JList<>(surveyList);
        mSurveyList.setBackground(Factory.BACKGROUND_LIGHT_COLOR);
        mSurveyList.setForeground(Factory.FOREGROUND_COLOR);
        mSurveyList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        mSurveyList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String selectedSurvey = mSurveyList.getSelectedValue();
                    System.out.println(selectedSurvey);
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
