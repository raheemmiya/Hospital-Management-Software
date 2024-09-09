package com.hospital.controller;

import com.hospital.Labels.labels;
import com.hospital.buttons.backButton;
import com.hospital.buttons.submitButton;
import com.hospital.fonts.Fonts;
import com.hospital.util.DBconnect;
import com.hospital.util.Query;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class addBill implements ActionListener {
    public static JPanel addBillPanel;

    JTextField patientIdTF;
    JTextField patientNameTF;
    JTextField totalTF;

    JButton Submit;
    public static JButton back;

    public addBill() {

        // initilizations
        Insets padding = new Insets(10, 10, 10, 10);

        addBillPanel = new JPanel();
        addBillPanel.setLayout(new GridBagLayout());
        addBillPanel.setBorder(BorderFactory.createEmptyBorder(50, 30, 90, 30));

        // to remove
        new HospitalNamePanel();

        // TextFields
        patientIdTF = new JTextField();
        patientIdTF.setFont(Fonts.textFieldFont);

        patientNameTF = new JTextField();
        patientNameTF.setFont(Fonts.textFieldFont);

        totalTF = new JTextField();
        totalTF.setFont(Fonts.textFieldFont);

        // Buttons
        Submit = submitButton.submitbutton();
        Submit.addActionListener(this);

        back = backButton.backbutton();

        // Layout Setups

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = padding;

        labels title = new labels("Add a Bill : ");
        gbc.anchor =GridBagConstraints.NORTH;
        gbc.gridx = 0; 
        gbc.gridy = 0; 
        addBillPanel.add(title, gbc);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        addBillPanel.add(new JLabel("Patient Id :"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addBillPanel.add(patientIdTF, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        addBillPanel.add(new JLabel("Patient Name : "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addBillPanel.add(patientNameTF, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.NONE;
        addBillPanel.add(new JLabel("Total Amount : "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addBillPanel.add(totalTF, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        addBillPanel.add(Submit, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        addBillPanel.add(back, gbc);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == Submit) {
            Connection connection = null;
            try {

                int patientId = Integer.parseInt(patientIdTF.getText());
                int Total = Integer.parseInt(totalTF.getText());
                String patientName = patientNameTF.getText();

                try {
                    connection = DBconnect.connect();
                    String query = Query.checkPatientId;
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setInt(1, patientId);
                    preparedStatement.setString(2, patientName);

                    ResultSet checkPatientId = preparedStatement.executeQuery();

                    if (checkPatientId.next()) {
                        String insertingQuery = Query.insertBilling;
                        PreparedStatement preparedStatement2 = connection.prepareStatement(insertingQuery);
                        preparedStatement2.setInt(1, patientId);
                        preparedStatement2.setInt(2, Total);
                        preparedStatement2.setString(3, patientName);
                        preparedStatement2.execute();

                        JOptionPane.showMessageDialog(addBillPanel, "Sucessfully inserted..!!");

                    } else {
                        JOptionPane.showMessageDialog(addBillPanel, "Incorrect Information...!!");
                    }
                    checkPatientId.close();

                } catch (Exception exp2) {
                    JOptionPane.showMessageDialog(addBillPanel, "Connection Error...!!");
                    exp2.printStackTrace();
                }
            } catch (Exception exp1) {
                JOptionPane.showMessageDialog(addBillPanel, "Invalid Information..!!");
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
