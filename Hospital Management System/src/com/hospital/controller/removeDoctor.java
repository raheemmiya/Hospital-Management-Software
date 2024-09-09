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

public class removeDoctor implements ActionListener {

    public static JPanel removeaDoctor;
    JLabel doctorNameLabel;
    JLabel doctorIdLabel;
    JLabel usernameLabel;
    JLabel passwordLabel;

    JTextField usernameTF;
    JTextField passwordTF;
    JTextField doctorIdTF;
    JTextField doctorNameTF;
    JButton remove;
    public static JButton back;

    public removeDoctor() {

        // Initializations
        removeaDoctor = new JPanel();
        doctorNameLabel = new JLabel("Doctor Name : ");
        doctorIdLabel = new JLabel("Doctor ID : ");
        doctorIdTF = new JTextField();
        doctorNameTF = new JTextField();
        usernameLabel = new JLabel("Username : ");
        passwordLabel = new JLabel("Password : ");
        usernameTF = new JTextField();
        passwordTF = new JTextField();

        // Buttons
        back = backButton.backbutton();

        remove = removeButton.removebutton();
        remove.addActionListener(this);

        // TextFields
        doctorIdTF.setFont(Fonts.textFieldFont);
        doctorNameTF.setFont(Fonts.textFieldFont);
        usernameTF.setFont(Fonts.textFieldFont);
        passwordTF.setFont(Fonts.textFieldFont);

        removeaDoctor.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        Insets padding = new Insets(10, 10, 10, 10);
        gbc.insets = padding;

        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        labels title = new labels("Remove a Doctor : ");
        removeaDoctor.add(title, gbc);

        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridy = 1;
        removeaDoctor.add(doctorIdLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        removeaDoctor.add(doctorIdTF, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        removeaDoctor.add(doctorNameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        removeaDoctor.add(doctorNameTF, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.NONE;
        removeaDoctor.add(usernameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        removeaDoctor.add(usernameTF, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.NONE;
        removeaDoctor.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        removeaDoctor.add(passwordTF, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.CENTER;
        removeaDoctor.add(remove, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.CENTER;
        removeaDoctor.add(back, gbc);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameTF.getText();
        String password = passwordTF.getText();
        String doctorName = doctorNameTF.getText();
        String doctorId = doctorIdTF.getText();

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
                        String query2 = Query.deleteDoctor;
                        PreparedStatement preparedStatement2 = connection.prepareStatement(query2);
                        preparedStatement2.setString(1, doctorId);
                        preparedStatement2.setString(2, doctorName);
                        if (preparedStatement2.executeUpdate() != 0) {
                            JOptionPane.showMessageDialog(removeaDoctor, "Doctor deleted Successfully..!!");
                            doctorIdTF.setText("");
                            doctorNameTF.setText("");
                            usernameTF.setText("");
                            passwordTF.setText("");
                        } else {
                            JOptionPane.showMessageDialog(removeaDoctor, "Incorrect Doctor Name or ID ..!!");
                        }

                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(removeaDoctor, "Invalid Doctor Name or ID ..!!");
                    }
                } else {
                    JOptionPane.showMessageDialog(removeaDoctor, "Incorrect Username or Password");
                }

            } catch (Exception exception2) {
                JOptionPane.showMessageDialog(removeaDoctor, "Connection Error..!!");
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
