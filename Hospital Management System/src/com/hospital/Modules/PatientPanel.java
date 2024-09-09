package com.hospital.Modules;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.hospital.MenuBar.Menu;
import com.hospital.buttons.panelButton;
import com.hospital.controller.HospitalNamePanel;
import com.hospital.controller.displayDoctor;
import com.hospital.controller.displayPatient;

public class PatientPanel {
    public static class PP implements ActionListener {
        JFrame patientFrame;
        JPanel patientPanel;
        public static JPanel cardPanel;

        JButton peekDoctors;
        JButton peekPatient;

        public static CardLayout c1;

        PP() {
            patientFrame = new JFrame();
            patientFrame.setTitle("Patient Panel");
            patientFrame.setLayout(new BorderLayout());
            patientFrame.setLocationRelativeTo(null);
            patientFrame.setBounds(400, 100, 900, 800);

            cardPanel = new JPanel();
            cardPanel.setBorder(BorderFactory.createEmptyBorder(50, 30, 20, 30));
            c1 = new CardLayout();
            cardPanel.setLayout(c1);

            patientPanel = new JPanel();
            patientPanel.setBorder(BorderFactory.createEmptyBorder(50, 30, 20, 30));
            patientPanel.setLayout(new GridLayout(4, 3, 30, 60));


            peekDoctors = new panelButton("List of Doctors ");
            peekDoctors.addActionListener(this);

            peekPatient = new panelButton("List of Patients ");
            peekPatient.addActionListener(this);

            patientPanel.add(peekDoctors);
            patientPanel.add(peekPatient);

            new HospitalNamePanel();
            new displayDoctor();
            new displayPatient();

            cardPanel.add(patientPanel, "Patient Panel");
            cardPanel.add(displayDoctor.displayDoctors, "Display Doctors");
            cardPanel.add(displayPatient.displayPatients, "Display Patients");

            patientFrame.add(HospitalNamePanel.namePanel, BorderLayout.NORTH);
            patientFrame.add(cardPanel, BorderLayout.CENTER);

            new Menu();
            patientFrame.setJMenuBar(Menu.bar);


            patientFrame.setVisible(true);
        }

        public ActionListener backButtonActionListener() {
            return e -> c1.show(cardPanel, "Patient Panel");
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            
            if(e.getSource() == peekDoctors) {
                c1.show(cardPanel, "Display Doctors");

                displayDoctor.back.addActionListener(backButtonActionListener());
            }  
            else if (e.getSource() == peekPatient) {
                c1.show(cardPanel, "Display Patients");

                displayPatient.back.addActionListener(backButtonActionListener());
            }

        }
    }

    public static void main(String[] args) {
        new PP();

    }
}
