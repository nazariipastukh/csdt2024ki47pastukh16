package code.runtime;

import corelogic.CheckerBoard;
import ui.UIFrame;

import javax.swing.*;

public class Main {
    private static runtime.Settings settings;
    private static UIFrame ui;

    public static void main(String[] args) {
        settings = runtime.Settings.loadSettings();
        if (settings.getSavedGame() == null) {
            settings.setSaveGame(new CheckerBoard(settings));
        }
        SwingUtilities.invokeLater(new Runnable(){

            @Override
            public void run() {
                ui = new UIFrame(settings);
                settings.getSavedGame().setUI(Main.access$000().game);
            }
        });
        new Thread(settings.getSavedGame()).start();
    }

    static UIFrame access$000() {
        return ui;
    }
}
