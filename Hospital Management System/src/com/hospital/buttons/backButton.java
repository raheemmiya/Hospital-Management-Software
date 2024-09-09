package com.hospital.buttons;

import java.awt.Color;

import javax.swing.JButton;

public class backButton {

    
    // public static JButton backBtn = new JButton("<<-Back");
    // static {
    //     backBtn.setForeground(Color.white);
    //     backBtn.setBackground(Color.black);
    //     backBtn.setVisible(true);
    // }

    public static JButton backbutton(){

           JButton backBtn = new JButton("<<-Back");
            backBtn.setForeground(Color.white);
            backBtn.setBackground(Color.black);
            backBtn.setFocusable(false);
            backBtn.setVisible(true);
            return backBtn;
    }
}
