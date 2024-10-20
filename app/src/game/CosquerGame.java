package game;

import javax.swing.JFrame;

/**
 * Main class to start the game.
 */
public class CosquerGame {

    public static void main(String[] args) {
        // Create the game window (JFrame)
        JFrame window = new JFrame("Cosquer Cave Explorer");

        // Set the window size
        window.setSize(800, 600);

        // Close the application when the window is closed
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the game panel (the part that handles rendering and logic)
        GamePanel gamePanel = new GamePanel();

        // Add the game panel to the window
        window.add(gamePanel);

        // Make the window visible
        window.setVisible(true);

        // Start the game
        gamePanel.startGame();
    }
}
