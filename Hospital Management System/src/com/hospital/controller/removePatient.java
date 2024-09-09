package com.hospital.controller;

import com.hospital.Labels.labels;
import com.hospital.buttons.backButton;
import com.hospital.buttons.removeButton;
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
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class removePatient implements ActionListener {

    public static JPanel removeaPatient;
    JLabel patientNameLabel;
    JLabel patientIdLabel;
    JLabel usernameLabel;
    JLabel passwordLabel;

    JTextField usernameTF;
    JTextField passwordTF;
    JTextField patientIdTF;
    JTextField patientNameTF;
    JButton remove;
    public static JButton back;

    public removePatient() {

        // Initializations
        removeaPatient = new JPanel();
        patientNameLabel = new JLabel("Patient Name : ");
        patientIdLabel = new JLabel("Patient ID : ");
        patientIdTF = new JTextField();
        patientNameTF = new JTextField();
        usernameLabel = new JLabel("Username : ");
        passwordLabel = new JLabel("Password : ");
        usernameTF = new JTextField();
        passwordTF = new JTextField();

        // Buttons
        back = backButton.backbutton();

        remove = removeButton.removebutton();
        remove.addActionListener(this);

        // TextFields
        patientIdTF.setFont(Fonts.textFieldFont);
        patientNameTF.setFont(Fonts.textFieldFont);
        usernameTF.setFont(Fonts.textFieldFont);
        passwordTF.setFont(Fonts.textFieldFont);

        removeaPatient.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        Insets padding = new Insets(10, 10, 10, 10);
        gbc.insets = padding;

        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        labels title = new labels("Remove a Patient :");
        removeaPatient.add(title, gbc);

        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridy = 1;
        removeaPatient.add(patientIdLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        removeaPatient.add(patientIdTF, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        removeaPatient.add(patientNameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        removeaPatient.add(patientNameTF, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.NONE;
        removeaPatient.add(usernameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        removeaPatient.add(usernameTF, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.NONE;
        removeaPatient.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        removeaPatient.add(passwordTF, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.CENTER;
        removeaPatient.add(remove, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.CENTER;
        removeaPatient.add(back, gbc);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameTF.getText();
        String password = passwordTF.getText();
        String patientName = patientNameTF.getText();
        String patientId = patientIdTF.getText();

        Connection connection = null;

        if (e.getSource() == remove) {
            try {
                connection = DBconnect.connect();
                String query = Query.peekUserPass;
                PreparedStatement preparedStatement1 = connection.prepareStatement(query);
                preparedStatement1.setString(1, username);
                preparedStatement1.setString(2, password);

                ResultSet rs = preparedStatement1.executeQuery();

                if (rs.next()) {
                    try {
                        String query2 = Query.deletePatient;
                        PreparedStatement preparedStatement2 = connection.prepareStatement(query2);
                        preparedStatement2.setString(1, patientId);
                        preparedStatement2.setString(2, patientName);
                        if (preparedStatement2.executeUpdate() != 0) {
                            JOptionPane.showMessageDialog(removeaPatient, "Patient deleted Successfully..!!");
                            patientIdTF.setText("");
                            patientNameTF.setText("");
                            usernameTF.setText("");
                            passwordTF.setText("");
                        } else {
                            JOptionPane.showMessageDialog(removeaPatient, "Incorrect Patient Name or ID ..!!");
                        }

                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(removeaPatient, "Invalid Patient Name or ID ..!!");
                    }
                } else {
                    JOptionPane.showMessageDialog(removeaPatient, "Incorrect Username or Password");
                }

            } catch (Exception exception2) {
                JOptionPane.showMessageDialog(removeaPatient, "Connection Error..!!");
            } finally {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }

    }

}
