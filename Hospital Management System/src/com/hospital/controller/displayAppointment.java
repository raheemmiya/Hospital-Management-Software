package com.hospital.controller;

import com.hospital.buttons.backButton;
import com.hospital.util.DBconnect;
import com.hospital.util.Query;

import com.hospital.Labels.labels;

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

public class displayAppointment {
    String cols[] = { "AppointmentId", "DoctorId ", "DoctorName", "PatientId", "PatientName", "Date & Time" };
    String rowsAppointment[][];
    JTable table;
    int count = 0;
    public static JPanel displayAppointments;
    JScrollPane scrollPane;
    public static JButton back;

    public displayAppointment() {

        displayAppointments = new JPanel();
        displayAppointments.setLayout(new GridBagLayout());

        String query = Query.displayAppointments;
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

            rowsAppointment = new String[rowcount][cols.length];

            while (resultSet.next()) {
                for (int i = 0; i < cols.length; i++) {
                    rowsAppointment[count][i] = resultSet.getString(i + 1);
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

        table = new JTable(rowsAppointment, cols) {

            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        scrollPane = new JScrollPane(table);

        GridBagConstraints gbc = new GridBagConstraints();

        //title for displaying the Appointment
        labels title = new labels("Lists of Appointments : ");
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridx = 0; 
        gbc.gridy = 0; 
        displayAppointments.add(title,gbc);

        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        displayAppointments.add(scrollPane, gbc);

        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        displayAppointments.add(back, gbc);
    }

}
