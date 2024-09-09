package com.hospital.Modules;

import com.hospital.buttons.backButton;
import com.hospital.buttons.panelButton;
import com.hospital.buttons.removeButton;
import com.hospital.controller.*;

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
import com.hospital.controller.addAppointment;
import com.hospital.controller.addDoctor;
import com.hospital.controller.addPatient;
import com.hospital.controller.addReceptionist;
import com.hospital.controller.displayAppointment;
import com.hospital.controller.displayDoctor;
import com.hospital.controller.displayPatient;
import com.hospital.controller.displayReceptionist;
import com.hospital.controller.removeAppointment;
import com.hospital.controller.removeDoctor;
import com.hospital.controller.removePatient;
import com.hospital.controller.removeReceptionist;

public class AdminPannel {
    public static class AP implements ActionListener {
        JFrame AdminFrame;
        JPanel AdminPanel;
        public static JPanel cardPanel;

        JButton addaDoctor;
        JButton addaPatient;
        JButton addaAppointment;
        JButton addaReceptionist;
        JButton removeaPatient;
        JButton removeaDoctor;
        JButton removeaAppointment;
        JButton removeaReceptionist;
        JButton peekDoctors;
        JButton peekPatient;
        JButton peekAppointments;
        JButton peekReceptionists;

        public static CardLayout c1;

        public AP() {
            AdminFrame = new JFrame();
            AdminFrame.setTitle("Admin Panel");
            AdminFrame.setLayout(new BorderLayout());
            AdminFrame.setLocationRelativeTo(null);
            AdminFrame.setBounds(400, 100, 900, 800);

            cardPanel = new JPanel();
            cardPanel.setBorder(BorderFactory.createEmptyBorder(50, 30, 20, 30));
            c1 = new CardLayout();
            cardPanel.setLayout(c1);

            AdminPanel = new JPanel();
            AdminPanel.setBorder(BorderFactory.createEmptyBorder(50, 30, 20, 30));
            AdminPanel.setLayout(new GridLayout(4, 3, 30, 60));

            addaDoctor = new panelButton("Add a Doctor ");
            addaDoctor.addActionListener(this);

            addaPatient = new panelButton("Add a Patient ");
            addaPatient.addActionListener(this);

            addaReceptionist = new panelButton("Add a Receptionist ");
            addaReceptionist.addActionListener(this);

            addaAppointment = new panelButton("Add a Appointment ");
            addaAppointment.addActionListener(this);

            removeaDoctor = new panelButton("Remove a Doctor ");
            removeaDoctor.addActionListener(this);

            removeaPatient = new panelButton("Remove a Patient ");
            removeaPatient.addActionListener(this);

            removeaReceptionist = new panelButton("Remove a Receptionist ");
            removeaReceptionist.addActionListener(this);

            removeaAppointment = new panelButton("Remove a Appointment");
            removeaAppointment.addActionListener(this);

            peekDoctors = new panelButton("List of Doctors ");
            peekDoctors.addActionListener(this);

            peekPatient = new panelButton("List of Patients ");
            peekPatient.addActionListener(this);

            peekAppointments = new panelButton("List of Appointment ");
            peekAppointments.addActionListener(this);

            peekReceptionists = new panelButton("List of Receptionists ");
            peekReceptionists.addActionListener(this);

            AdminPanel.add(addaDoctor);
            AdminPanel.add(removeaDoctor);
            AdminPanel.add(peekDoctors);
            AdminPanel.add(addaPatient);
            AdminPanel.add(removeaPatient);
            AdminPanel.add(peekPatient);
            AdminPanel.add(addaReceptionist);
            AdminPanel.add(removeaReceptionist);
            AdminPanel.add(peekReceptionists);
            AdminPanel.add(addaAppointment);
            AdminPanel.add(removeaAppointment);
            AdminPanel.add(peekAppointments);

            new HospitalNamePanel();
            new addAppointment();
            new addDoctor();
            new addPatient();
            new addReceptionist();
            new removeDoctor();
            new removePatient();
            new removeReceptionist();
            new removeAppointment();
            new displayDoctor();
            new displayPatient();
            new displayAppointment();
            new displayReceptionist();
            new removeButton();
            new backButton();

            cardPanel.add(AdminPanel, "Admin Panel");
            cardPanel.add(addDoctor.adddoctorPanel, "Add a doctor panel");
            cardPanel.add(addPatient.addPatientPanel, "Add a patient panel");
            cardPanel.add(addReceptionist.addReceptionistPanel, "Add a Receptionist panel");
            cardPanel.add(addAppointment.addappointmentPanel, "Add a appointment panel");
            cardPanel.add(removeDoctor.removeaDoctor, "Remove a Doctor Panel");
            cardPanel.add(removePatient.removeaPatient, "Remove a Patient Panel");
            cardPanel.add(removeReceptionist.removeaReceptionist, "Remove a Receptionist Panel");
            cardPanel.add(removeAppointment.removeaAppointment, "Remove a Appointment Panel");
            cardPanel.add(displayDoctor.displayDoctors, "Display Doctors");
            cardPanel.add(displayPatient.displayPatients, "Display Patients");
            cardPanel.add(displayReceptionist.displayReceptionists, "Display Receptionists");
            cardPanel.add(displayAppointment.displayAppointments, "Display Appointments");

            AdminFrame.add(HospitalNamePanel.namePanel, BorderLayout.NORTH);
            AdminFrame.add(cardPanel, BorderLayout.CENTER);

            new com.hospital.MenuBar.Menu();
            AdminFrame.setJMenuBar(Menu.bar);
            AdminFrame.setVisible(true);
        }

        public ActionListener backButtonActionListener() {
            return e -> c1.show(cardPanel, "Admin Panel");
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == addaDoctor) {
                c1.show(cardPanel, "Add a doctor panel");

                addDoctor.back.addActionListener(backButtonActionListener());

            } else if (e.getSource() == addaAppointment) {
                c1.show(cardPanel, "Add a appointment panel");

                addAppointment.back.addActionListener(backButtonActionListener());

            } else if (e.getSource() == addaPatient) {
                c1.show(cardPanel, "Add a patient panel");

                addPatient.back.addActionListener(backButtonActionListener());

            } else if (e.getSource() == addaReceptionist) {
                c1.show(cardPanel, "Add a Receptionist panel");

                addReceptionist.back.addActionListener(backButtonActionListener());
            } else if (e.getSource() == removeaDoctor) {
                c1.show(cardPanel, "Remove a Doctor Panel");

                removeDoctor.back.addActionListener(backButtonActionListener());
            } else if (e.getSource() == removeaPatient) {
                c1.show(cardPanel, "Remove a Patient Panel");

                removePatient.back.addActionListener(backButtonActionListener());
            } else if (e.getSource() == removeaAppointment) {
                c1.show(cardPanel, "Remove a Appointment Panel");

                removeAppointment.back.addActionListener(backButtonActionListener());

            } else if (e.getSource() == removeaReceptionist) {
                c1.show(cardPanel, "Remove a Receptionist Panel");

                removeReceptionist.back.addActionListener(backButtonActionListener());
            }

            else if (e.getSource() == peekDoctors) {
                c1.show(cardPanel, "Display Doctors");

                displayDoctor.back.addActionListener(backButtonActionListener());
            } else if (e.getSource() == peekPatient) {
                c1.show(cardPanel, "Display Patients");

                displayPatient.back.addActionListener(backButtonActionListener());
            } else if (e.getSource() == peekAppointments) {
                c1.show(cardPanel, "Display Appointments");

                displayAppointment.back.addActionListener(backButtonActionListener());
            } else if (e.getSource() == peekReceptionists) {
                c1.show(cardPanel, "Display Receptionists");

                displayReceptionist.back.addActionListener(backButtonActionListener());
            }

        }
    }

    public static void main(String[] args) {
        new AP();

    }
}
