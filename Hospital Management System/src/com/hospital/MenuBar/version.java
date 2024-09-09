package com.hospital.MenuBar;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.hospital.buttons.okButton;
import com.hospital.fonts.Fonts;

public class version implements ActionListener {
    JFrame versionFrame;
    JLabel text1;
    JLabel text2;
    JButton okBtn;

    ImageIcon originalImageIcon;
    JLabel bannerLabel;

    version() {
        versionFrame = new JFrame();
        versionFrame.setTitle("About");
        versionFrame.setLayout(new GridBagLayout());
        versionFrame.setBounds(500, 200, 680, 650);
        versionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        GridBagConstraints gbc = new GridBagConstraints();

        text1 = new JLabel(
                "<html> The Hospital Management System (HMS) is a user-friendly software designed to manage hospital operations efficiently.  It handles patient records, appointments, billing, and staff management in one centralized platform. With secure data handling and 24/7 support, HMS enhances both patient care and hospital administration.  </html>");
        text2 = new JLabel(
                "<html> Hospital Management System <br> Version 1.0 <br> © Rahim Miya, All rights reserved. <br><br> This software and its user interface are protected by trademarks and other pending or existing <br> intellectual property rights. <br><br><br><br> This product is licensed under the terms provided by the developer to: <br> Authorized User </html>");

        text1.setFont(Fonts.versionLabel1);
        text2.setFont(Fonts.versionLabel2);

        originalImageIcon = new ImageIcon(
                "C:\\Users\\Raheem\\Desktop\\Codings\\My Projects\\Hospital System Project\\Hospital Management System\\src\\com\\hospital\\resources\\HospitalNameBanner.png");

        Image originalImage = originalImageIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(200, 100, Image.SCALE_SMOOTH);

        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        bannerLabel = new JLabel(resizedIcon);


        //button 
        okBtn = okButton.okbutton();
        okBtn.addActionListener(this);

        gbc.insets = new java.awt.Insets(100, 15, 10, 15); // Padding around components
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridx = 0;
        gbc.weightx = 0;
        gbc.weighty = 0;
        versionFrame.add(bannerLabel, gbc);
      
        gbc.insets = new java.awt.Insets(10, 15, 10, 15); // Padding around components
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 1;
        versionFrame.add(text1, gbc);

        gbc.weightx = 1.0;
        gbc.gridy = 2;
        versionFrame.add(text2, gbc);

        gbc.insets = new java.awt.Insets(10, 15, 60, 15); // Padding around components
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.NONE;
        versionFrame.add(okBtn,gbc);

        // versionFrame.pack();
        versionFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == okBtn) {
            versionFrame.dispose();
        }
    }

}
