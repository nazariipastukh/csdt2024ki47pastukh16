package code.runtime;

import corelogic.CheckerBoard;
import ui.UIFrame;

import javax.swing.*;

/**
 * @class Main
 * @brief Main class responsible for initializing and starting the application.
 * <p>
 * This class includes the main method to start the application, loading settings,
 * creating a user interface, and launching a thread for the game logic.
 */
public class Main {
    private static UIFrame ui;
    /**
     * @brief Static method to load and initialize the application settings.
     * <p>
     * If no saved game is found in the settings, a new CheckerBoard is created.
     *
     * @param args Command-line arguments (unused in this application).
     */
    public static void main(String[] args) {
        // Load application settings
        runtime.Settings settings = runtime.Settings.loadSettings();

        // Check if a saved game exists, create a new CheckerBoard if not
        if (settings.getSavedGame() == null) {
            settings.setSaveGame(new CheckerBoard(settings));
        }

        // Create and display the user interface using SwingUtilities.invokeLater
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                ui = new UIFrame(settings);
                settings.getSavedGame().setUI(Main.access$000().game);
            }
        });

        // Start a new thread for the game logic
        new Thread(settings.getSavedGame()).start();
    }

    /**
     * @brief Static method to access the UI frame.
     *
     * @return The UIFrame instance.
     */
    static UIFrame access$000() {
        return ui;
    }
}
