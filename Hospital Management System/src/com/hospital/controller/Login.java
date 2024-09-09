package com.hospital.controller;

import com.hospital.Modules.AdminPannel;
import com.hospital.Modules.DoctorPanel;
import com.hospital.Modules.PatientPanel;
import com.hospital.Modules.ReceptionistPanel;
import com.hospital.util.DBconnect;
import com.hospital.util.Query;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
    public static class LoginRegistration implements ActionListener {

        JFrame frame;
        JButton loginButton;
        JButton signupButton;
        JTextField loginUserTF;
        JTextField loginPassTF;
        JTextField signupUserTF;
        JTextField signupPassTf;
        @SuppressWarnings("rawtypes")
        JComboBox comboBox;
        String[] modules = { "Patient", "Doctor", "Receptionist", "Admin" };

        @SuppressWarnings({ "rawtypes", "unchecked" })
        LoginRegistration() {
            frame = new JFrame();
            frame.setSize(500, 400);
            frame.setLocationRelativeTo(null);
            frame.setTitle(" XYZ Hospital Software");

            JTabbedPane tabbedPane = new JTabbedPane();

            // Login Panel
            JPanel loginPanel = new JPanel();
            loginPanel.setLayout(new GridLayout(4, 2, 20, 20));
            loginPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            loginUserTF = new JTextField();
            loginUserTF.setFont(new Font("Comic sans", Font.BOLD, 25));

            loginPassTF = new JTextField();
            loginPassTF.setFont(new Font("Comic sans", Font.BOLD, 25));

            loginButton = new JButton("Login");
            loginButton.setFocusPainted(false);
            loginButton.addActionListener(this);

            comboBox = new JComboBox(modules);
            comboBox.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            loginPanel.add(new JLabel("Enter Your Role : "));
            loginPanel.add(comboBox);
            loginPanel.add(new JLabel("Username : "));
            loginPanel.add(loginUserTF);
            loginPanel.add(new JLabel("Password : "));
            loginPanel.add(loginPassTF);
            loginPanel.add(new JLabel());
            loginPanel.add(loginButton);

            tabbedPane.addTab("Login", loginPanel);

            frame.add(tabbedPane);
            frame.setVisible(true);
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            String loginUser = loginUserTF.getText();
            String loginPass = loginPassTF.getText();
            String selectedModule = comboBox.getSelectedItem().toString();
            Connection connection = null;
            if (e.getSource() == loginButton) {
                try {
                    connection = DBconnect.connect();
                    PreparedStatement preparedStatement = connection.prepareStatement(Query.login);
                    preparedStatement.setString(1, loginUser);
                    preparedStatement.setString(2, loginPass);
                    preparedStatement.setString(3, selectedModule);
                    ResultSet resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()) {
                        String role = resultSet.getString("role");

                        if ("Admin".equalsIgnoreCase(role)) {
                            frame.dispose();
                            new AdminPannel.AP();

                        } else if ("Doctor".equalsIgnoreCase(role)) {
                            frame.dispose();
                            new DoctorPanel.DP();
                        } else if ("Patient".equalsIgnoreCase(role)) {
                            frame.dispose();
                            new PatientPanel();
                        } else if ("Receptionist".equalsIgnoreCase(role)) {
                            frame.dispose();
                            new ReceptionistPanel();
                        }

                    } else {
                        JOptionPane.showMessageDialog(loginButton, "Invalid Username or Password...!!");
                    }
                } catch (Exception exp) {
                    JOptionPane.showMessageDialog(loginButton, "Invalid Username or Password...!!");
                    
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

    public static void main(String[] args) {
        new LoginRegistration();
    }
}
