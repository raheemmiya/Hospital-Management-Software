package com.hospital.controller;

import com.hospital.Labels.labels;
import com.hospital.buttons.backButton;
import com.hospital.util.DBconnect;
import com.hospital.util.Query;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class displayReceptionist {

    String cols[] = { "Receptionists Id", "Name", "Phone Number", "Email", "Hired Date", "Address" };
    String rowsReceptionists[][];
    JTable table;
    int count = 0;
    public static JPanel displayReceptionists;
    JScrollPane scrollPane;
    public static JButton back;

    public displayReceptionist() {

        displayReceptionists = new JPanel();
        displayReceptionists.setLayout(new GridBagLayout());

        String query = Query.displayReceptionists;
        Connection connection = null;
        try {
            connection = DBconnect.connect();
            Statement statement = connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery(query);

            resultSet.last();
            int rowcount = resultSet.getRow();
            resultSet.beforeFirst();

            rowsReceptionists = new String[rowcount][cols.length];

            while (resultSet.next()) {
                for (int i = 0; i < cols.length; i++) {
                    rowsReceptionists[count][i] = resultSet.getString(i + 1);
                }
                count++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }

        // back button
        back = backButton.backbutton();

        table = new JTable(rowsReceptionists, cols) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        scrollPane = new JScrollPane(table);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx =0 ;
        gbc.gridy=0;
        labels title = new labels("List of Receptionists : ");
        displayReceptionists.add(title,gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        displayReceptionists.add(scrollPane, gbc);

        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        displayReceptionists.add(back, gbc);

    }

}
