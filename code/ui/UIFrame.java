package ui;

import runtime.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

public class UIFrame
        extends JFrame {
    public ui.GameUI game;
    Settings settings;
    JMenuBar menuBar;
    JMenu menu;

    public UIFrame(Settings settings) {
        this.setTitle("Draughts");
        this.setVisible(true);
        this.setDefaultCloseOperation(3);
        this.settings = settings;
        this.addWindowListener(new WindowAdapter(){

            @Override
            public void windowClosing(WindowEvent evt) {
                UIFrame.this.formWindowClosing(evt);
            }
        });
        this.game = new ui.GameUI(settings);
        this.getContentPane().add(this.game);
        this.setMenus();
        this.setMinimumSize(new Dimension(250, 250));
        this.pack();
    }

    private void setMenus() {
        this.menuBar = new JMenuBar();
        this.setJMenuBar(this.menuBar);
        this.menu = new JMenu("Game");
        this.menuBar.add(this.menu);
        JMenuItem menuItem = new JMenuItem("New Game");
        menuItem.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                UIFrame.this.game.setupGame(true);
            }
        });
        this.menu.add(menuItem);
        menuItem = new JMenuItem("Undo Last Move");
        menuItem.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                UIFrame.this.game.undo();
            }
        });
        this.menu.add(menuItem);
        menuItem = new JMenuItem("Hint");
        menuItem.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                UIFrame.this.game.showHint();
            }
        });
        this.menu.add(menuItem);
        menuItem = new JMenuItem("Stats");
        menuItem.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                ui.StatsMenu.showStats(UIFrame.this.settings);
            }
        });
        this.menu.add(menuItem);
        menuItem = new JMenuItem("Options");
        menuItem.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                ui.OptionsMenu.showOptions(UIFrame.this);
            }
        });
        this.menu.add(menuItem);
        menuItem = new JMenuItem("Exit");
        menuItem.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                UIFrame.this.settings.saveSettings();
                System.exit(0);
            }
        });
        this.menu.add(menuItem);
        JMenu menu = new JMenu("Help");
        this.menuBar.add(menu);
        menuItem = new JMenuItem("Help File");
        menuItem.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    File help = new File("userguide.pdf");
                    if (help.exists()) {
                        if (Desktop.isDesktopSupported()) {
                            Desktop.getDesktop().open(help);
                        }
                    } else {
                        System.out.println("Could not locate user documentation.");
                    }
                }
                catch (Exception ex) {
                    System.out.println("Error Opening Helpfile: " + ex);
                }
            }
        });
        menu.add(menuItem);
        menuItem = new JMenuItem("About");
        menuItem.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                ui.AboutScreen.showAbout();
            }
        });
        menu.add(menuItem);
    }

    private void formWindowClosing(WindowEvent evt) {
        this.settings.saveSettings();
    }

}
