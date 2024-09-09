package com.hospital.buttons;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class submitButton {

    public static JButton submitbutton(){
    
        JButton Submit = new JButton("SUBMIT");
        Submit.setBackground(Color.decode("#00712D"));
        Submit.setForeground(Color.white);
        Submit.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 40));
        Submit.setFocusable(false);
        return Submit;
    }
}
