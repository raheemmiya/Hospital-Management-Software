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
//importing the controllers
import com.hospital.controller.HospitalNamePanel;
import com.hospital.controller.addAppointment;
import com.hospital.controller.addPatient;
import com.hospital.controller.displayAppointment;
import com.hospital.controller.displayDoctor;
import com.hospital.controller.displayPatient;
import com.hospital.controller.removeAppointment;
import com.hospital.controller.removePatient;

public class DoctorPanel {
    public static class DP implements ActionListener {
        JFrame DoctorFrame;
        JPanel doctorPanel;
        public static JPanel cardPanel;

        JButton addaPatient;
        JButton addaAppointment;
        JButton removeaPatient;
        JButton removeaAppointment;
        JButton peekDoctors;
        JButton peekPatient;
        JButton peekAppointments;

        public static CardLayout c1;

        public DP() {
            DoctorFrame = new JFrame();
            DoctorFrame.setTitle("Doctor Panel");
            DoctorFrame.setLayout(new BorderLayout());
            DoctorFrame.setLocationRelativeTo(null);
            DoctorFrame.setBounds(400, 100, 900, 800);

            cardPanel = new JPanel();
            cardPanel.setBorder(BorderFactory.createEmptyBorder(50, 30, 20, 30));
            c1 = new CardLayout();
            cardPanel.setLayout(c1);

            doctorPanel = new JPanel();
            doctorPanel.setBorder(BorderFactory.createEmptyBorder(50, 30, 20, 30));
            doctorPanel.setLayout(new GridLayout(4, 3, 30, 60));

            addaPatient = new panelButton("Add a Patient ");
            addaPatient.addActionListener(this);

            addaAppointment = new panelButton("Add a Appointment ");
            addaAppointment.addActionListener(this);

            removeaPatient = new panelButton("Remove a Patient ");
            removeaPatient.addActionListener(this);

            removeaAppointment = new panelButton("Remove a Appointment");
            removeaAppointment.addActionListener(this);

            peekDoctors = new panelButton("List of Doctors ");
            peekDoctors.addActionListener(this);

            peekPatient = new panelButton("List of Patients ");
            peekPatient.addActionListener(this);

            peekAppointments = new panelButton("List of Appointment ");
            peekAppointments.addActionListener(this);

            doctorPanel.add(addaPatient);
            doctorPanel.add(removeaPatient);
            doctorPanel.add(addaAppointment);
            doctorPanel.add(removeaAppointment);
            doctorPanel.add(peekAppointments);
            doctorPanel.add(peekDoctors);
            doctorPanel.add(peekPatient);

            new HospitalNamePanel();
            new addAppointment();
            new addPatient();
            new removePatient();
            new removeAppointment();
            new displayDoctor();
            new displayPatient();
            new displayAppointment();

            cardPanel.add(doctorPanel, "Doctor Panel");
            cardPanel.add(addAppointment.addappointmentPanel, "Add a appointment panel");
            cardPanel.add(addPatient.addPatientPanel, "Add a patient panel");
            cardPanel.add(removePatient.removeaPatient, "Remove a Patient Panel");
            cardPanel.add(removeAppointment.removeaAppointment, "Remove a Appointment Panel");
            cardPanel.add(displayDoctor.displayDoctors, "Display Doctors");
            cardPanel.add(displayPatient.displayPatients, "Display Patients");
            cardPanel.add(displayAppointment.displayAppointments, "Display Appointments");

            DoctorFrame.add(HospitalNamePanel.namePanel, BorderLayout.NORTH);
            DoctorFrame.add(cardPanel, BorderLayout.CENTER);

            new Menu();
            DoctorFrame.setJMenuBar(Menu.bar);

            DoctorFrame.setVisible(true);
        }

        public ActionListener backButtonActionListener() {
            return e -> c1.show(cardPanel, "Doctor Panel");
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == addaAppointment) {
                c1.show(cardPanel, "Add a appointment panel");

                addAppointment.back.addActionListener(backButtonActionListener());

            } else if (e.getSource() == addaPatient) {
                c1.show(cardPanel, "Add a patient panel");

                addPatient.back.addActionListener(backButtonActionListener());

            } else if (e.getSource() == removeaPatient) {
                c1.show(cardPanel, "Remove a Patient Panel");

                removePatient.back.addActionListener(backButtonActionListener());
            } else if (e.getSource() == removeaAppointment) {
                c1.show(cardPanel, "Remove a Appointment Panel");
                
                removeAppointment.back.addActionListener(backButtonActionListener());
            }
            else if(e.getSource() == peekDoctors) {
                c1.show(cardPanel, "Display Doctors");

                displayDoctor.back.addActionListener(backButtonActionListener());
            }  
            else if (e.getSource() == peekPatient) {
                c1.show(cardPanel, "Display Patients");

                displayPatient.back.addActionListener(backButtonActionListener());
            }
            else if (e.getSource() == peekAppointments) {
                    c1.show(cardPanel, "Display Appointments");

                displayAppointment.back.addActionListener(backButtonActionListener());    
            }

        }
    }

    public static void main(String[] args) {
        new DP();

    }
}
