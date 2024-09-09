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
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

public class addAppointment implements ActionListener {

    JFrame addappointmentFrame;
    public static JPanel addappointmentPanel;
    JTextField doctorIDTF;
    JTextField patientIDTF;
    JTextField doctorNameTF;
    JTextField patientNameTF;
    JTextField Age;
    JTextField speciality;
    JButton Submit;
    public static JButton back;

    JRadioButton Male;
    JRadioButton Female;
    JRadioButton RatherNotSay;
    ButtonGroup genderGroup;

    String StringDoctorID, doctorName, StringPatientId, patientName;

    public addAppointment() {

   
        Insets padding = new Insets(10, 10, 10, 10);

        // Pannel for adding appointment
        addappointmentPanel = new JPanel();
        addappointmentPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        doctorIDTF = new JTextField();
        doctorIDTF.setFont(Fonts.textFieldFont);


        patientIDTF = new JTextField();
        patientIDTF.setFont(Fonts.textFieldFont);

        doctorNameTF = new JTextField();
        doctorNameTF.setFont(Fonts.textFieldFont);

        patientNameTF = new JTextField();
        patientNameTF.setFont(Fonts.textFieldFont);

        Submit = submitButton.submitbutton();
        Submit.addActionListener(this);

        addappointmentPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = padding;
        gbc.anchor = GridBagConstraints.WEST;

        // Title
        labels title = new labels("Add an Appointment : ");
        gbc.gridx = 0;
        gbc.gridy = 0;
        addappointmentPanel.add(title, gbc);

        // Doctor ID and IDLabel
        gbc.gridx = 0;
        gbc.gridy = 1;
        addappointmentPanel.add(new JLabel("Doctor ID : "), gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 1;
        addappointmentPanel.add(doctorIDTF, gbc);

        // Doctor Name and Name Label
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridy = 2;
        addappointmentPanel.add(new Label("Doctor Name : "), gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 2;
        addappointmentPanel.add(doctorNameTF, gbc);

        // Patient ID and ID label
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridy = 3;
        addappointmentPanel.add(new JLabel("Patient ID :"), gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 3;
        addappointmentPanel.add(patientIDTF, gbc);

        // patent Name and Name label
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridy = 4;
        addappointmentPanel.add(new JLabel("Patient Name : "), gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 4;
        addappointmentPanel.add(patientNameTF, gbc);

        // Submitt Button
        gbc.fill = GridBagConstraints.CENTER;
        gbc.gridx = 1;
        gbc.gridy = 5;
        addappointmentPanel.add(Submit, gbc);

        //back button 
        back = backButton.backbutton();
        back.addActionListener(this);
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        gbc.gridx = 1;
        gbc.gridy = 6;
        addappointmentPanel.add(back , gbc);

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
        StringDoctorID = doctorIDTF.getText();
        StringPatientId = patientIDTF.getText();
        doctorName = doctorNameTF.getText();
        patientName = patientNameTF.getText();

        if (e.getSource() == Submit) {
            if (isInteger(StringDoctorID) && isInteger(StringPatientId)) {
                int doctorID = Integer.valueOf(StringDoctorID);
                int patientId = Integer.valueOf(StringPatientId);
                Connection con = null ;
                try {

                    con = DBconnect.connect();

                    String checkQueryDoctorID = "SELECT Name from Doctors where DoctorID = ?";
                    PreparedStatement PsforDoctorID = con.prepareStatement(checkQueryDoctorID);
                    PsforDoctorID.setInt(1, doctorID);
                    ResultSet checkDoctorId = PsforDoctorID.executeQuery();

                    String checkQueryPatientID = "SELECT Name from patients where PatientID = ?";
                    PreparedStatement PSforPatientID = con.prepareStatement(checkQueryPatientID);
                    PSforPatientID.setInt(1, patientId);
                    ResultSet checkPatientID = PSforPatientID.executeQuery();

                    if (checkDoctorId.next() && checkPatientID.next()) {

                        String query = Query.insertAppointments;
                        PreparedStatement preparedStatement = con.prepareStatement(query);
                        preparedStatement.setInt(1, doctorID);
                        preparedStatement.setString(2, doctorName);
                        preparedStatement.setInt(3, patientId);
                        preparedStatement.setString(4, patientName);
                        preparedStatement.executeUpdate();
                        JOptionPane.showMessageDialog(addappointmentFrame, "Registration Successfull");
                    } else {
                        JOptionPane.showMessageDialog(addappointmentPanel, "Incorrect Info..!!");
                    }
                } catch (Exception exp) {
                    JOptionPane.showMessageDialog(addappointmentPanel, "Incorrect Doctor Name or Patient Name");
                } finally {
                    if (con != null) {
                        try {
                            con.close();
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(addappointmentPanel, "Invalid type of Inputs..!!");
            }
        }

    }

}


