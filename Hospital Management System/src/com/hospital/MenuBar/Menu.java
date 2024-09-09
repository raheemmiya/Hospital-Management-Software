package com.hospital.MenuBar;

import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;




public class Menu implements ActionListener{
    public static JMenuBar bar = new JMenuBar();
    JMenu aboutMenu = new JMenu("About");
    JMenu helpMenu  =new JMenu("Help");
    JMenuItem versionItem = new JMenuItem("Version");
    JMenuItem contactItem = new JMenuItem("Contact");
    

    public Menu(){
        aboutMenu.setMnemonic(KeyEvent.VK_A); 
        helpMenu.setMnemonic(KeyEvent.VK_H);
        versionItem.setMnemonic(KeyEvent.VK_V);
        contactItem.setMnemonic(KeyEvent.VK_C);

        versionItem.addActionListener(this);

        versionItem.addActionListener(this);
        contactItem.addActionListener(this);

        aboutMenu.add(versionItem);
        helpMenu.add(contactItem);
        bar.add(aboutMenu);
        bar.add(helpMenu);
        bar.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == versionItem) {
            new version();
        }
        if (e.getSource() == contactItem) {
            new Contact();
        }
    }
}
