package com.hospital.buttons;

import java.awt.Color;

import javax.swing.JButton;

import com.hospital.fonts.Fonts;

public class panelButton extends JButton {
    public panelButton(String text) {
        
        this.setText(text);
        this.setFont(Fonts.panelButton);
        this.setFocusable(false);
        this.setBackground(new Color(229799));
        this.setForeground(Color.green);
    }
}
