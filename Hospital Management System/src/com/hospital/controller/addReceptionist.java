package com.hospital.controller;

import com.hospital.Labels.labels;
import com.hospital.buttons.backButton;
import com.hospital.buttons.submitButton;
import com.hospital.fonts.Fonts;
import com.hospital.util.DBconnect;
import com.hospital.util.Query;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class addReceptionist implements ActionListener {

    public static JPanel addReceptionistPanel;
    JTextField NameTF;
    JTextField phoneNoTF;
    JTextField emailTF;
    JTextField addressTF;

    JButton Submit;

    public static JButton back;

    String Name, email, address;
    int phoneNo;

    public addReceptionist() {

        JFrame addReceptionistFrame = new JFrame();
        addReceptionistFrame.setTitle("Patient Registration Form");
        addReceptionistFrame.setLocationRelativeTo(null);
        addReceptionistFrame.setBounds(400, 100, 900, 800);
        addReceptionistFrame.setLayout(new BorderLayout());

        Insets padding = new Insets(10, 10, 10, 10);

        addReceptionistPanel = new JPanel();
        addReceptionistPanel.setBorder(BorderFactory.createEmptyBorder(-30, 20, 20, 20));

        NameTF = new JTextField();
        NameTF.setFont(Fonts.textFieldFont);

        phoneNoTF = new JTextField();
        phoneNoTF.setFont(Fonts.textFieldFont);

        emailTF = new JTextField();
        emailTF.setFont(Fonts.textFieldFont);

        addressTF = new JTextField();
        addressTF.setFont(Fonts.textFieldFont);

        Submit = submitButton.submitbutton();
        Submit.addActionListener(this);

        addReceptionistPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = padding;
        
        //title for the panel 
        labels title = new labels("Add a Receptionist :");
        gbc.anchor= GridBagConstraints.NORTH;
        gbc.gridx = 0; 
        gbc.gridy = 0; 
        addReceptionistPanel.add(title,gbc);
        
        
        // Name and NameLabel
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 1;
        addReceptionistPanel.add(new JLabel("Receptionist Name : "), gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 1;
        addReceptionistPanel.add(NameTF, gbc);

        // phone number and TF
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridy = 2;
        addReceptionistPanel.add(new JLabel("Phone Number :"), gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 2;
        addReceptionistPanel.add(phoneNoTF, gbc);

        // email and TF
        gbc.gridx = 0;
        gbc.gridy = 3;
        addReceptionistPanel.add(new JLabel("Email :"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        addReceptionistPanel.add(emailTF, gbc);

        // Address and TF
        gbc.gridx = 0;
        gbc.gridy = 4;
        addReceptionistPanel.add(new JLabel("Address :"), gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 4;
        addReceptionistPanel.add(addressTF, gbc);

        // Submitt Button
        gbc.gridx = 1;
        gbc.gridy = 5;
        addReceptionistPanel.add(Submit, gbc);

        // Back Button
        back = backButton.backbutton();
        gbc.gridx = 1;
        gbc.gridy = 6;
        addReceptionistPanel.add(back, gbc);
    

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Connection con = null;
        try {
            Name = NameTF.getText();
            phoneNo = Integer.parseInt(phoneNoTF.getText());
            email = emailTF.getText();
            address = addressTF.getText();

            if (e.getSource() == Submit) {

                con = DBconnect.connect();
                String query = Query.insertReceptionists;
                PreparedStatement preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, Name);
                preparedStatement.setInt(2, phoneNo);
                preparedStatement.setString(3, email);
                preparedStatement.setString(4, address);
                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(addReceptionistPanel, "Registration Successfull");

            }

        } catch (Exception exp) {
            JOptionPane.showMessageDialog(addReceptionistPanel, "Invalid Inputs..!!");
            exp.printStackTrace();
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
