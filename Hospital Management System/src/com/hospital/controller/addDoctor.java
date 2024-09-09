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

public class addDoctor implements ActionListener {

    public static JPanel adddoctorPanel;
    JTextField Name;
    JTextField Age;
    JTextField speciality;
    JButton Submit;
    public static JButton back;

    JRadioButton Male;
    JRadioButton Female;
    JRadioButton RatherNotSay;
    ButtonGroup genderGroup;

    String pName, pGender, pIlness;
    String pAge;

    public addDoctor() {

        Insets padding = new Insets(10, 10, 10, 10);

        // Pannel for doctor Registration
        adddoctorPanel = new JPanel();
        adddoctorPanel.setBorder(BorderFactory.createEmptyBorder(-30, 20, 20, 20));

        Name = new JTextField();
        Name.setFont(Fonts.textFieldFont);

        Age = new JTextField();
        Age.setFont(Fonts.textFieldFont);

        speciality = new JTextField();
        speciality.setFont(Fonts.textFieldFont);

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
        
        back = new JButton("<-Back");
        back = backButton.backbutton();
        back.addActionListener(this);

        Submit = submitButton.submitbutton();
        Submit.addActionListener(this);

        adddoctorPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        labels text = new labels("Add a Doctor : ");
        gbc.insets = padding;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        adddoctorPanel.add(text,gbc);

        // Name and NameLabel
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 1;
        adddoctorPanel.add(new JLabel("Doctor Name : "), gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 1;
        adddoctorPanel.add(Name, gbc);

        // Age and AgeLabel
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridy = 2;
        adddoctorPanel.add(new JLabel("Age :"), gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 2;
        adddoctorPanel.add(Age, gbc);

        // Gender and Gender Label
        gbc.gridx = 0;
        gbc.gridy = 3;
        adddoctorPanel.add(new JLabel("Gender :"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        adddoctorPanel.add(Male, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        adddoctorPanel.add(Female, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        adddoctorPanel.add(RatherNotSay, gbc);

        // speciality and speciality Label
        gbc.gridx = 0;
        gbc.gridy = 6;
        adddoctorPanel.add(new JLabel("Speciality :"), gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 6;
        adddoctorPanel.add(speciality, gbc);

        // Submitt Button
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        gbc.gridy = 7;
        adddoctorPanel.add(Submit, gbc);

        // Back Button
        // GridBagConstraints gbcBack = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 8;
        // gbcBack.weightx = 1.0;
        // gbcBack.weighty = 1.0;
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        gbc.insets = new Insets(10, 10, 10, 10); // Optional padding
        adddoctorPanel.add(back, gbc);

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
            pIlness = speciality.getText();

            if (e.getSource() == Submit) {
                if (isInteger(pAge)) {
                    try {
                        con = DBconnect.connect();
                        String query = Query.insertDoctors;
                        PreparedStatement preparedStatement = con.prepareStatement(query);
                        preparedStatement.setString(1, pName);
                        int pAgeInt = (int) (Double.parseDouble(pAge)); // assumes string is a valid number
                        preparedStatement.setInt(2, pAgeInt);
                        preparedStatement.setString(3, pGender);
                        preparedStatement.setString(4, pIlness);
                        preparedStatement.executeUpdate();
                        JOptionPane.showMessageDialog(adddoctorPanel, "Registration Successfull");

                    } catch (Exception exp) {

                        JOptionPane.showMessageDialog(adddoctorPanel, "Database Connection Error");
                    }

                } else {
                    JOptionPane.showMessageDialog(adddoctorPanel, "Invalid Age ");
                }
            }

        } catch (Exception exp1) {
            JOptionPane.showMessageDialog(adddoctorPanel, "Invalid Inputs ");
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
