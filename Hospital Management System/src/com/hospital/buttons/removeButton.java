package com.hospital.buttons;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class removeButton {
    public static JButton removebutton (){
        JButton remove; 
        remove = new JButton("Remove");
        remove.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 50));
        remove.setBackground(Color.decode("#00712D"));
        remove.setForeground(Color.white);
        remove.setFocusable(false);
        return remove;

    };
}
