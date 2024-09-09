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

public class removeAppointment implements ActionListener {

    public static JPanel removeaAppointment;
    JLabel appointmentIdLabel;
    JLabel doctorIdLabel;
    JLabel patientIdLabel;
    JLabel usernameLabel;
    JLabel passwordLabel;

    JTextField usernameTF;
    JTextField passwordTF;
    JTextField appointmentIdTF;
    JTextField patientIdTF;
    JTextField doctorIdTF;
    JButton remove;
    public static JButton back;

    public removeAppointment() {

        // Initializations
        removeaAppointment = new JPanel();
        appointmentIdLabel = new JLabel("Appointment ID : ");
        doctorIdLabel = new JLabel("Doctor ID : ");
        patientIdLabel = new JLabel("Patient ID : ");
        usernameLabel = new JLabel("Username : ");
        passwordLabel = new JLabel("Password : ");
        
        appointmentIdTF = new JTextField();
        patientIdTF = new JTextField();
        doctorIdTF = new JTextField();
        passwordTF = new JTextField();
        usernameTF = new JTextField();


        // Buttons
        back= backButton.backbutton();
        
        remove = removeButton.removebutton();
        remove.addActionListener(this);

        // TextFields
        appointmentIdTF.setFont(Fonts.textFieldFont);
        doctorIdTF.setFont(Fonts.textFieldFont);
        patientIdTF.setFont(Fonts.textFieldFont);
        usernameTF.setFont(Fonts.textFieldFont);
        passwordTF.setFont(Fonts.textFieldFont);

        removeaAppointment.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        Insets padding = new Insets(10, 10, 10, 10);
        gbc.insets = padding;

        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridx= 0; 
        gbc.gridy = 0;
        labels title = new labels("Remove an Appointment :");
        removeaAppointment.add(title, gbc);

        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridy = 1;
        removeaAppointment.add(appointmentIdLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        removeaAppointment.add(appointmentIdTF, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        removeaAppointment.add(doctorIdLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        removeaAppointment.add(doctorIdTF, gbc);


        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.NONE;
        removeaAppointment.add(patientIdLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        removeaAppointment.add(patientIdTF, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.NONE;
        removeaAppointment.add(usernameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        removeaAppointment.add(usernameTF, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.NONE;
        removeaAppointment.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        removeaAppointment.add(passwordTF, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.CENTER;
        removeaAppointment.add(remove, gbc);

        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.CENTER;
        removeaAppointment.add(back, gbc);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameTF.getText();
        String password = passwordTF.getText();
        String appointmentId = appointmentIdTF.getText();
        String doctorId = doctorIdTF.getText();
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
                        String query2 = Query.deleteAppointment;
                        PreparedStatement preparedStatement2 = connection.prepareStatement(query2);
                        preparedStatement2.setString(1, appointmentId);
                        preparedStatement2.setString(2, patientId);
                        preparedStatement2.setString(3, doctorId);
                        if(preparedStatement2.executeUpdate()!=0){
                            JOptionPane.showMessageDialog(removeaAppointment, "Appointment deleted Successfully..!!");
                            appointmentIdTF.setText("");
                            doctorIdTF.setText("");
                            patientIdTF.setText("");
                            usernameTF.setText("");
                            passwordTF.setText("");
                        }else{
                            JOptionPane.showMessageDialog(removeaAppointment, "Incorrect ID's ..!!");
                        }
                        

                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(removeaAppointment, "Incorrect ID's");
                    }
                } else {
                    JOptionPane.showMessageDialog(removeaAppointment, "Incorrect Username or Password..!!");
                }

            } catch (Exception exception2) {
                JOptionPane.showMessageDialog(removeaAppointment, "Connection Error..!!");
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
