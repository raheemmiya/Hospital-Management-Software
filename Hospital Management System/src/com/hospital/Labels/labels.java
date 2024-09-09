package com.hospital.Labels;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import com.hospital.fonts.Fonts;

public class  labels extends JLabel {
    public labels(String text){
       this.setText(text);
       this.setFont(Fonts.labels);
       this.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
    }
}
