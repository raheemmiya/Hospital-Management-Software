package com.hospital.MenuBar;

import java.awt.Desktop;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import javax.swing.*;

import com.hospital.buttons.okButton;

public class Contact {
    JFrame contactFrame;
    JLabel hospitalLabel, fbHospitalLink, instaHospitalLink, linkedinHospitalLink;
    JLabel makerLabel, fbMakerLink, instaMakerLink, linkedinMakerLink;
    JButton okBtn;
    JLabel hospitalLogo;

    Contact() {
        contactFrame = new JFrame();
        contactFrame.setTitle("Contact Info");
        contactFrame.setBounds(500, 200, 680, 600);
        contactFrame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //the main hospital logo making process
        
        ImageIcon originalImageIcon = new ImageIcon(
                "C:\\Users\\Raheem\\Desktop\\Codings\\My Projects\\Hospital System Project\\Hospital Management System\\src\\com\\hospital\\resources\\HospitalNameBanner.png");

        Image originalImage = originalImageIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(200, 100, Image.SCALE_SMOOTH);

        
        // Load main hospital logo (place the file path to your logo here)
        ImageIcon hospitalLogoIcon = new ImageIcon(new ImageIcon(resizedImage)
                .getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
        hospitalLogo = new JLabel(hospitalLogoIcon);
        

        // Add main hospital logo at the top center
        gbc.insets = new java.awt.Insets(10, 15, 10, 15); // Padding around components
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        contactFrame.add(hospitalLogo, gbc);
        
        // Hospital social links
        gbc.gridy = 1;
        gbc.gridx = 0;
        hospitalLabel = new JLabel("Hospital Social Links:");
        contactFrame.add(hospitalLabel, gbc);

        // Load and add social media icons with links for the hospital
        fbHospitalLink = createLinkLabelWithIcon("Facebook (Hospital)", "C:\\Users\\Raheem\\Desktop\\Codings\\My Projects\\Hospital System Project\\Hospital Management System\\src\\com\\hospital\\resources\\facebookLogo.png", "https://www.facebook.com/hospital");
        gbc.gridy = 2;
        contactFrame.add(fbHospitalLink, gbc);

        instaHospitalLink = createLinkLabelWithIcon("Instagram (Hospital)", "C:\\Users\\Raheem\\Desktop\\Codings\\My Projects\\Hospital System Project\\Hospital Management System\\src\\com\\hospital\\resources\\instagramLogo.png", "https://www.instagram.com/hospital");
        gbc.gridy = 3;
        contactFrame.add(instaHospitalLink, gbc);

        linkedinHospitalLink = createLinkLabelWithIcon("LinkedIn (Hospital)", "C:\\Users\\Raheem\\Desktop\\Codings\\My Projects\\Hospital System Project\\Hospital Management System\\src\\com\\hospital\\resources\\linkedin.png", "https://www.linkedin.com/company/hospital");
        gbc.gridy = 4;
        contactFrame.add(linkedinHospitalLink, gbc);

        // Maker social links
        gbc.gridy = 5;
        makerLabel = new JLabel("Software Maker Social Links:");
        contactFrame.add(makerLabel, gbc);

        fbMakerLink = createLinkLabelWithIcon("Facebook (Rahim Miya)", "C:\\Users\\Raheem\\Desktop\\Codings\\My Projects\\Hospital System Project\\Hospital Management System\\src\\com\\hospital\\resources\\facebookLogo.png", "https://www.facebook.com/rahim");
        gbc.gridy = 6;
        contactFrame.add(fbMakerLink, gbc);

        instaMakerLink = createLinkLabelWithIcon("Instagram (Rahim Miya)", "C:\\Users\\Raheem\\Desktop\\Codings\\My Projects\\Hospital System Project\\Hospital Management System\\src\\com\\hospital\\resources\\instagramLogo.png", "https://www.instagram.com/rahim");
        gbc.gridy = 7;
        contactFrame.add(instaMakerLink, gbc);

        linkedinMakerLink = createLinkLabelWithIcon("LinkedIn (Rahim Miya)", "C:\\Users\\Raheem\\Desktop\\Codings\\My Projects\\Hospital System Project\\Hospital Management System\\src\\com\\hospital\\resources\\linkedin.png", "https://www.linkedin.com/in/rahim");
        gbc.gridy = 8;
        contactFrame.add(linkedinMakerLink, gbc);

        // OK button at the bottom
        okBtn = okButton.okbutton();
        okBtn.addActionListener(e -> contactFrame.dispose());
        gbc.gridy = 9;
        contactFrame.add(okBtn, gbc);

        contactFrame.setVisible(true);
    }

    // Method to create clickable link labels with icons
    private JLabel createLinkLabelWithIcon(String text, String iconPath, String url) {
        ImageIcon icon = new ImageIcon(new ImageIcon(iconPath).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        JLabel linkLabel = new JLabel("<html><a href=''>" + text + "</a></html>", icon, JLabel.LEFT);
        linkLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        linkLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI(url));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        return linkLabel;
    }

}
