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
import com.hospital.controller.addAppointment;
import com.hospital.controller.addBill;
import com.hospital.controller.addDoctor;
import com.hospital.controller.addPatient;
import com.hospital.controller.displayAppointment;
import com.hospital.controller.displayBill;
import com.hospital.controller.displayDoctor;
import com.hospital.controller.displayPatient;
import com.hospital.controller.removeAppointment;
import com.hospital.controller.removeDoctor;
import com.hospital.controller.removePatient;

public class ReceptionistPanel {
    public static class RP implements ActionListener {
        JFrame ReceptionistFrame;
        JPanel ReceptionistPanel;
        public static JPanel cardPanel;

        JButton addaDoctor;
        JButton addaPatient;
        JButton addaAppointment;
        JButton addaBill;
        JButton removeaPatient;
        JButton removeaDoctor;
        JButton removeaAppointment;
        JButton peekDoctors;
        JButton peekPatient;
        JButton peekAppointments;
        JButton peekBills;

        public static CardLayout c1;

        RP() {

            ReceptionistFrame = new JFrame("Receptionist Frame - XYZ Medical College");
            ReceptionistFrame.setLayout(new BorderLayout());
            ReceptionistFrame.setLocationRelativeTo(null);
            ReceptionistFrame.setBounds(400, 100, 900, 800);

            cardPanel = new JPanel();
            cardPanel.setBorder(BorderFactory.createEmptyBorder(50, 30, 20, 30));
            c1 = new CardLayout();
            cardPanel.setLayout(c1);

            ReceptionistPanel = new JPanel();
            ReceptionistPanel.setBorder(BorderFactory.createEmptyBorder(50, 30, 20, 30));
            ReceptionistPanel.setLayout(new GridLayout(4, 3, 30, 60));

            addaDoctor = new panelButton("Add a Doctor ");
            addaDoctor.addActionListener(this);

            addaPatient = new panelButton("Add a Patient ");
            addaPatient.addActionListener(this);

            addaAppointment = new panelButton("Add a Appointment ");
            addaAppointment.addActionListener(this);

            addaBill = new panelButton("Add a Bill ");
            addaBill.addActionListener(this);

            removeaDoctor = new panelButton("Remove a Doctor ");
            removeaDoctor.addActionListener(this);

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
            
            peekBills = new panelButton("Billings ");
            peekBills.addActionListener(this);


            ReceptionistPanel.add(addaDoctor);
            ReceptionistPanel.add(removeaDoctor);
            ReceptionistPanel.add(peekDoctors);
            ReceptionistPanel.add(addaPatient);
            ReceptionistPanel.add(removeaPatient);
            ReceptionistPanel.add(peekPatient);
            ReceptionistPanel.add(addaAppointment);
            ReceptionistPanel.add(removeaAppointment);
            ReceptionistPanel.add(peekAppointments);
            ReceptionistPanel.add(addaBill);
            ReceptionistPanel.add(peekBills);

            new HospitalNamePanel();
            new addAppointment();
            new addDoctor();
            new addPatient();
            new addBill();
            new removeDoctor();
            new removePatient();
            new removeAppointment();
            new displayDoctor();
            new displayPatient();
            new displayAppointment();
            new displayBill();

            cardPanel.add(ReceptionistPanel, "Receptionist Panel");
            cardPanel.add(addDoctor.adddoctorPanel, "Add a doctor panel");
            cardPanel.add(addPatient.addPatientPanel, "Add a patient panel");
            cardPanel.add(addAppointment.addappointmentPanel, "Add a appointment panel");
            cardPanel.add(addBill.addBillPanel, "Add a bill panel");
            cardPanel.add(removeDoctor.removeaDoctor, "Remove a Doctor Panel");
            cardPanel.add(removePatient.removeaPatient, "Remove a Patient Panel");
            cardPanel.add(removeAppointment.removeaAppointment, "Remove a Appointment Panel");
            cardPanel.add(displayDoctor.displayDoctors, "Display Doctors");
            cardPanel.add(displayPatient.displayPatients, "Display Patients");
            cardPanel.add(displayAppointment.displayAppointments, "Display Appointments");
            cardPanel.add(displayBill.displayBills, "Display Bills");

            ReceptionistFrame.add(HospitalNamePanel.namePanel, BorderLayout.NORTH);
            ReceptionistFrame.add(cardPanel, BorderLayout.CENTER);

            new Menu();
            ReceptionistFrame.setJMenuBar(Menu.bar);

            ReceptionistFrame.setVisible(true);
        }

        public ActionListener backButtonActionListener() {
            return e -> c1.show(cardPanel, "Receptionist Panel");
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

            } else if (e.getSource() == addaBill) {
                c1.show(cardPanel, "Add a bill panel");


                addBill.back.addActionListener(backButtonActionListener());
            }
            else if (e.getSource() == removeaDoctor) {
                c1.show(cardPanel, "Remove a Doctor Panel");

                removeDoctor.back.addActionListener(backButtonActionListener());

            } else if (e.getSource() == removeaPatient) {
                c1.show(cardPanel, "Remove a Patient Panel");

                removePatient.back.addActionListener(backButtonActionListener());

            } else if (e.getSource() == removeaAppointment) {
                c1.show(cardPanel, "Remove a Appointment Panel");

                removeAppointment.back.addActionListener(backButtonActionListener());

            } else if (e.getSource() == peekDoctors) {
                c1.show(cardPanel, "Display Doctors");

                displayDoctor.back.addActionListener(backButtonActionListener());

            } else if (e.getSource() == peekPatient) {
                c1.show(cardPanel, "Display Patients");

                displayPatient.back.addActionListener(backButtonActionListener());

            } else if (e.getSource() == peekAppointments) {
                c1.show(cardPanel, "Display Appointments");

                displayAppointment.back.addActionListener(backButtonActionListener());
            } else if (e.getSource() == peekBills) {
                c1.show(cardPanel, "Display Bills");

                displayBill.back.addActionListener(backButtonActionListener());
            }


        }
    }
    public static void main(String[] args) {
        new RP();
    }

}
