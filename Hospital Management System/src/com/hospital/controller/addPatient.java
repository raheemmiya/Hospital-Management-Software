package com.hospital.controller;

import com.hospital.Labels.labels;
import com.hospital.buttons.backButton;
import com.hospital.buttons.submitButton;
import com.hospital.fonts.Fonts;
import com.hospital.util.DBconnect;
import com.hospital.util.Query;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class addPatient implements ActionListener {

    public static JPanel addPatientPanel;
    JTextField Name;
    JTextField Age;
    JTextField Illness;
    JButton Submit;

    JRadioButton Male;
    JRadioButton Female;
    JRadioButton RatherNotSay;
    ButtonGroup genderGroup;

    public static JButton back;

    String pName, pGender, pIlness;
    String pAge;

    public addPatient() {

        Insets padding = new Insets(10, 10, 10, 10);

        // Pannel for Patient Registration
        addPatientPanel = new JPanel();
        addPatientPanel.setBorder(BorderFactory.createEmptyBorder(-30, 20, 20, 20));

        Name = new JTextField();
        Name.setFont(Fonts.textFieldFont);

        Age = new JTextField();
        Age.setFont(Fonts.textFieldFont);

        Illness = new JTextField();
        Illness.setFont(Fonts.textFieldFont);

        Male = new JRadioButton("Male");
        Male.addActionListener(this);
        Female = new JRadioButton("Female");
        Female.addActionListener(this);
        RatherNotSay = new JRadioButton("Rather not say");
        RatherNotSay.addActionListener(this);
        genderGroup = new ButtonGroup();
        genderGroup.add(Male);
        genderGroup.add(Female);
        genderGroup.add(RatherNotSay);

        Submit = submitButton.submitbutton();
        Submit.addActionListener(this);

        addPatientPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = padding;
        gbc.anchor = GridBagConstraints.NORTH;

        // title for the panel
        labels title = new labels("Add a Patient : ");
        gbc.gridx = 0;
        gbc.gridy =0 ;
        addPatientPanel.add(title, gbc);

        
        // Name and NameLabel
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 1;
        addPatientPanel.add(new JLabel("Patient Name : "), gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 1;
        addPatientPanel.add(Name, gbc);

        // Age and AgeLabel
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridy = 2;
        addPatientPanel.add(new JLabel("Age :"), gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 2;
        addPatientPanel.add(Age, gbc);

        // Gender and Gender Label
        gbc.gridx = 0;
        gbc.gridy = 3;
        addPatientPanel.add(new JLabel("Gender :"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        addPatientPanel.add(Male, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        addPatientPanel.add(Female, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        addPatientPanel.add(RatherNotSay, gbc);

        // Illness and Illness Label
        gbc.gridx = 0;
        gbc.gridy = 6;
        addPatientPanel.add(new JLabel("Illness :"), gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 6;
        addPatientPanel.add(Illness, gbc);

        // Submitt Button
        gbc.gridx = 1;
        gbc.gridy = 7;
        addPatientPanel.add(Submit, gbc);

        // Back Button
        back = backButton.backbutton();
        gbc.gridx = 1;
        gbc.gridy = 8;
        addPatientPanel.add(back, gbc);

    }

    public static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Connection con = null;
        try {
            pName = Name.getText();
            pAge = Age.getText();
            if (e.getSource() == Male) {
                pGender = "Male";
            } else if (e.getSource() == Female) {
                pGender = "Female";
            } else if (e.getSource() == RatherNotSay) {
                pGender = "Rather not say";
            }
            pIlness = Illness.getText();

            if (e.getSource() == Submit) {

                if (isInteger(pAge)) {
                    try {
                        con = DBconnect.connect();
                        String query = Query.insertPatients;
                        PreparedStatement preparedStatement = con.prepareStatement(query);
                        preparedStatement.setString(1, pName);
                        int pAgeInt = (int) (Double.parseDouble(pAge)); // assumes string is a valid number
                        preparedStatement.setInt(2, pAgeInt);
                        preparedStatement.setString(3, pGender);
                        preparedStatement.setString(4, pIlness);
                        preparedStatement.executeUpdate();
                        JOptionPane.showMessageDialog(addPatientPanel, "Registration Successfull");

                    } catch (Exception exp) {
                        exp.printStackTrace();
                        JOptionPane.showMessageDialog(addPatientPanel, "Database Connection Error");
                    }

                } else {
                    JOptionPane.showMessageDialog(addPatientPanel, "Invalid Age");
                }
            }

        } catch (Exception exp1) {
            JOptionPane.showMessageDialog(addPatientPanel, "Invalid Inputs ");
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }

    }

}
