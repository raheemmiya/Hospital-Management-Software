package com.hospital.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HospitalNamePanel {
    public static JPanel namePanel;

    public HospitalNamePanel() {

        // Hospital Name Panel
        namePanel = new JPanel(new BorderLayout());
        namePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));

        JLabel hospitalName = new JLabel("XYZ Medical College", JLabel.CENTER);
        hospitalName.setBorder(BorderFactory.createEmptyBorder(0, 0, 100, 0));
        hospitalName.setFont(new Font("Comic Sans", Font.BOLD, 40));

        ImageIcon logo = new ImageIcon(
                "C:\\Users\\Raheem\\Desktop\\Codings\\My Projects\\Hospital System Project\\Hospital Management System\\src\\com\\hospital\\resources\\logo.jpg");
        Image compressedIcon = logo.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon compressedImageIcon = new ImageIcon(compressedIcon);
        JLabel logolabel = new JLabel(compressedImageIcon);

        namePanel.add(hospitalName, BorderLayout.CENTER);
        namePanel.add(logolabel, BorderLayout.EAST);

        namePanel.setBackground(Color.decode("#3795BD"));

    }

}
