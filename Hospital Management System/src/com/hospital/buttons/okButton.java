package com.hospital.buttons;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class okButton {
    public static JButton okbutton (){
        JButton okButton; 
        okButton = new JButton("Ok");
        okButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        okButton.setBackground(Color.decode("#00712D"));
        okButton.setForeground(Color.white);
        okButton.setFocusable(false);
        return okButton;

    };
}
