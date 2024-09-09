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

public class removeReceptionist implements ActionListener {

    public static JPanel removeaReceptionist;
   

    JTextField usernameTF;
    JTextField passwordTF;
    JTextField receptionistIdTF;
    JTextField receptionistNameTF;
    JButton remove;
    public static JButton back;

    public removeReceptionist() {

        // Initializations
        removeaReceptionist = new JPanel();
        receptionistIdTF = new JTextField();
        receptionistNameTF = new JTextField();
        usernameTF = new JTextField();
        passwordTF = new JTextField();

        // Buttons
        back = backButton.backbutton();
    
        remove = removeButton.removebutton();
        remove.addActionListener(this);

        // TextFields
        receptionistIdTF.setFont(Fonts.textFieldFont);
        receptionistNameTF.setFont(Fonts.textFieldFont);
        usernameTF.setFont(Fonts.textFieldFont);
        passwordTF.setFont(Fonts.textFieldFont);

        removeaReceptionist.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        Insets padding = new Insets(10, 10, 10, 10);
        gbc.insets = padding;


        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        labels title = new labels("Remove a Receptionist : ");
        removeaReceptionist.add(title, gbc);

        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridy = 1;
        removeaReceptionist.add(new JLabel("Receptionist Id :") , gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        removeaReceptionist.add(receptionistIdTF, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        removeaReceptionist.add(new JLabel("Receptionist Name :"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        removeaReceptionist.add(receptionistNameTF, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.NONE;
        removeaReceptionist.add(new JLabel("Username :"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        removeaReceptionist.add(usernameTF, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.NONE;
        removeaReceptionist.add(new JLabel("Password :"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        removeaReceptionist.add(passwordTF, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.CENTER;
        removeaReceptionist.add(remove, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.CENTER;
        removeaReceptionist.add(back, gbc);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameTF.getText();
        String password = passwordTF.getText();
        String receptionistName = receptionistNameTF.getText();
        String receptionistId = receptionistIdTF.getText();

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
                        String query2 = Query.deleteReceptionist;
                        PreparedStatement preparedStatement2 = connection.prepareStatement(query2);
                        preparedStatement2.setString(1, receptionistId);
                        preparedStatement2.setString(2, receptionistName);
                        if(preparedStatement2.executeUpdate()!=0){
                            JOptionPane.showMessageDialog(removeaReceptionist, "Receptionist deleted Successfully..!!");
                            receptionistIdTF.setText("");
                            receptionistNameTF.setText("");
                            usernameTF.setText("");
                            passwordTF.setText("");
                        }else{
                            JOptionPane.showMessageDialog(removeaReceptionist, "Incorrect Receptionist's Name or ID ..!!");
                        }
                        

                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(removeaReceptionist, "Invalid Receptionist's Name or ID ..!!");
                    }
                } else {
                    JOptionPane.showMessageDialog(removeaReceptionist, "Incorrect Username or Password");
                }

            } catch (Exception exception2) {
                JOptionPane.showMessageDialog(removeaReceptionist, "Connection Error..!!");
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
