package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AboutScreen
        extends JFrame {
    private static AboutScreen _instance = null;

    public static void showAbout() {
        if (_instance == null) {
            AboutScreen about = new AboutScreen();
            about.setVisible(true);
        }
    }

    private AboutScreen() {
        this.setResizable(false);
        this.setSize(new Dimension(250, 250));
        this.setTitle("About");
        this.addWindowListener(new WindowAdapter(){

            @Override
            public void windowClosing(WindowEvent evt) {
                AboutScreen.this.formWindowClosing(evt);
            }
        });
        GridLayout layout = new GridLayout(0, 1);
        this.getContentPane().setLayout(layout);
        JLabel l = new JLabel("Created by Nazarii Pastukh");

    }

    private void formWindowClosing(WindowEvent evt) {
        _instance = null;
    }

}